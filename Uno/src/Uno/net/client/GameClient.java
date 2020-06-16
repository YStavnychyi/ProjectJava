package Uno.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Uno.Card;
import Uno.Deal;
import Uno.game.handler.GameHandlerer;
import Uno.net.packets.Packet;
import Uno.net.packets.Packet.PacketsIDs;
import Uno.net.packets.PacketDisconnect;
import Uno.net.packets.PacketGetCard;
import Uno.net.packets.PacketLogin;
import Uno.net.packets.PacketSetCard;
import Uno.net.packets.PacketSizeP;
import Uno.net.packets.PacketUpdate;

public class GameClient extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private GameHandlerer gameH;
	
	private boolean isTurn;
    private boolean isFinished;
    private Deal clientDeck;
    private Card centerCard;
    
    private String nameList;
    private String name;
	private int[] pDeckSize;
    
    
	public GameClient(String ip,int port,GameHandlerer gameH)
	{
		try {
			this.gameH = gameH;
			clientSocket = new Socket(ip,port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			isTurn = false;
			isFinished = false;
			nameList = "";
		} catch (UnknownHostException e) {
			System.out.println("[Client]:UknownHost IP:"+ip+"  Port:"+port);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void run()
	{
		boolean clientRunning = true;
		System.out.println("[Client] Started Client\n");
		try {
		String msg;
		while(clientRunning && (msg = in.readLine())!=null)
		{
			Packet packet = null;
			PacketsIDs id = Packet.getPacketID(Integer.parseInt(msg.substring(0, 1)));
			msg = msg.substring(1);
			switch(id)
			{
			case LOGIN:
				packet = new PacketLogin(msg);
				eventLogin((PacketLogin)packet);
				break;
			
			case UPDATE:
				packet = new PacketUpdate(msg);
				eventUpdate((PacketUpdate)packet);
				break;
			
			case DISCONNECT:	
				clientRunning = false;
				stopClient();
				break;
				
			case STARTGAME:
				eventStartGame();
				break;
				
			case GETCARD:
				packet = new PacketGetCard(msg);
				eventGetCard((PacketGetCard)packet);
				break;
				
			case SETCARD:
				packet = new PacketSetCard(msg);
				eventSetCard((PacketSetCard)packet);
				break;
				
			case SIZEP:
				packet = new PacketSizeP(msg);
				eventPSize((PacketSizeP)packet);
				break;
				
			case TURN:
				isTurn = true;
				break;
			default:
				System.out.println("[Client]:Invalid event\nMessage:"+msg);
				break;
			}
			
			
		}
			} catch (IOException e) {
				System.out.println("[Client]: IOException :" + clientSocket.getLocalAddress().toString());
				e.printStackTrace();
			}
	}
	
	public void SendMessageToServer(String msg)
	{
		out.println(msg);
	}
	
	
	public void stopClient()
	{
		try {
			SendMessageToServer(new PacketDisconnect().getData());
			
			System.out.println("[Client]:Stopped Client");
			nameList = "Disconnected,Restart,Client";
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getClientName()
	{
		return name;
	}
	
	public String getNameList()
	{
		return nameList;
	}
	
	private void eventLogin(PacketLogin packet)
	{
		name = packet.getName();
		System.out.println("[Client]Aquaried name:"+name);
	}
	
	private void eventUpdate(PacketUpdate packet)
	{
		nameList = packet.getNameList();
	}
	
	private void eventGetCard(PacketGetCard packet)
	{
		String cardData = packet.getCardData();
		if(!cardData.equals("-1"))
		{
			
			Card tmpCard = Card.getCardfromString(cardData);
			clientDeck.addCard(tmpCard);
			System.out.println("[Client]:Aquaried card:"+tmpCard.TransalteToString());
			
		}
		else
			System.out.println("[Client]No more cards in pile from server");
		
	}
	
	private void eventSetCard(PacketSetCard packet)
	{
		centerCard = Card.getCardfromString(packet.getCardData());
		System.out.println("[Client]:New center card:"+centerCard.TransalteToString());
	}
	
	private void eventStartGame()
	{
		clientDeck = new Deal();
		gameH.startGameClient();
	}
	
	public Deal getClientDeck()
	{
		return clientDeck;
	}
	
	public boolean isClientTurn()
	{
		return isTurn;
	}
	
	public void endClientTurn()
	{
		isTurn = false;
		////////////////////dodac wyslanie czy cos
	}
	
	public void sendTakeCardToServer()
	{
		System.out.println("[CLient]Sending request to get a card from pile");
		SendMessageToServer(new PacketGetCard(null).getData());
	}
	
	public Card getCenterCard()
	{
		return centerCard;
	}
	
	public void sendSelectedCardToServer(Card card,String col)
	{
		System.out.println("[Client]Sending card:"+card.TransalteToString());
		if(col!=null)
			System.out.println("With changed color on middle:"+col);
		for(int i=0;i<clientDeck.getSize();i++)
		{
			if(clientDeck.getCard(i).colorRet()==card.colorRet() && clientDeck.getCard(i).numRet() == card.numRet())
			{
				clientDeck.removeCard(i);
				break;
			}
			if(i==clientDeck.getSize()-1)
				System.out.println("[Client]An error with cards sending,din't find the one");
		}
		PacketSetCard packet = new PacketSetCard(card.TransalteToString(),col);
		SendMessageToServer(packet.getData());
	}
	
	private void eventPSize(PacketSizeP packet)
	{
		System.out.println("[Client]Recived update of PSize array:");
		
		pDeckSize = packet.getPSizeArray();
		for(int i=0;i<pDeckSize.length;i++)
			System.out.print(pDeckSize[i]+" ");
		System.out.println("END ARRAY");
	}
	
	public int getSizeOfPlayer(int iNum)
	{
		if(pDeckSize!=null)
		{
			String pList[] = nameList.split(",");
			int beg=0;
			int max_val =pList.length;
			if(max_val<=iNum)
				return -1;
			
			for(int i=0;i<max_val;i++)
			{
				if(pList[i].equals(name))
				{
					beg = i;
					break;
				}
			}
			
			
			return pDeckSize[(iNum+beg)%max_val];
			
			
		}
		return -1;
	}
	
	public void setClientFinishedGame()
	{
		isFinished = true;
	}
	
	public boolean getIsClientFinished()
	{
		return isFinished;
	}
}
