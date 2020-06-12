package Uno.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;

import Uno.Card;
import Uno.CardHandler;
import Uno.net.packets.PacketDisconnect;
import Uno.net.packets.PacketGetCard;
import Uno.net.packets.PacketSetCard;
import Uno.net.packets.PacketSizeP;
import Uno.net.packets.PacketStartGame;
import Uno.net.packets.PacketTurn;
import Uno.net.packets.PacketUpdate;

public class GameServer extends Thread{
	
	private ServerSocket serverSocket;
	private ArrayList<ClientHandlerer> clientList;
	private boolean isCon;
	
	private int nameNum;
	private String nameList;
	private boolean isGameStarted;
	private CardHandler deck;
	private Card centerCard;
	private Random random;
	
	private int[] pSize;
	private int turnNum;
	private boolean isReverse;
	
	public GameServer(int port)
	{
		try {
			serverSocket = new ServerSocket(port);
			clientList = new ArrayList<ClientHandlerer>();
			nameList = "";
			isGameStarted = false;
			deck = new CardHandler();
			nameNum =1;
			random = new Random();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("static-access")
	public void run()
	{
		isCon = true;
		System.out.println("[Server]:Started aquaring connections");
		while(isCon && !serverSocket.isClosed())
		{
			try {
				if(clientList.size()<4 && !isGameStarted)
				{
					String pName = "Player"+nameNum;
					clientList.add(new ClientHandlerer(serverSocket.accept(),this,pName));
					clientList.get(clientList.size()-1).start();
					
					System.out.println("[Server]A new connection with:"+clientList.get(clientList.size()-1).getAdrress() 
							+ "\n And aquried name:"+clientList.get(clientList.size()-1).getPlayerName() );
					nameList += pName + ",";
					System.out.println("[Server]Updated nameList:"+nameList);
					nameNum++;
					
					this.sleep(1000);
					sendToAllClients(new PacketUpdate(nameList).getData());
				}
				else
				{
					System.out.println("[Server]:Closed aquaring connections");
					isCon = false;
				}
					
			} catch (java.net.SocketException e) {
				if(e.getMessage().equals("socket closed"))
					System.out.println("[Server]Closed socket ...");
				else
					e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
			
	}
	
	public boolean getIsGameStarted()
	{
		return isGameStarted;
	}
	
	public void stopServer()
	{
		try {
			isCon = false;
			sendToAllClients(new PacketDisconnect().getData());
			serverSocket.close();
			this.join();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendToAllClients(String msg)
	{
		for(ClientHandlerer c : clientList)
		{
			c.sendMessageToClient(msg);
		}
	}
	
	public void startGame()
	{
		System.out.println("[Server]Starting the game!");
		isGameStarted = true;
		sendToAllClients(new PacketStartGame().getData());
		turnNum = -1;
		isReverse=false;
		deck.shuffleDeck();
		
		centerCard = deck.getLast();
		deck.removeLast();
		if(centerCard.colorRet()=='a')
		{
			int rand = random.nextInt()%4;
			if(rand<0)
				rand*=-1;
			char coll = 'r';
			switch(rand)
			{
			case 0:
				coll = 'b';
				break;
				
			case 1:
				coll = 'y';
				break;
				
			case 2:
				coll = 'g';
				break;
				
			case 3:
				coll = 'r';
				break;
			}
			centerCard = new Card(centerCard.numRet(),coll);
		}
		
		deck.shuffleDeck();
		
		
		for(int i=0;i<6;i++)
		{
			for(ClientHandlerer c : clientList)
			{
				sendCardToClient(c,false);
			}
		}
		pSize = new int[clientList.size()];
		updatePSize();
		
		System.out.println("[Server]Starting center card:"+centerCard.TransalteToString());
		sendToAllClients(new PacketSetCard(centerCard.TransalteToString(),null).getData());
		
		sendToAllClients(new PacketSizeP(pSize).getData());
		
		nextTurn(false);
	}
	
	private void updatePSize()
	{
		int i=0;
		for(ClientHandlerer c : clientList)
		{
			pSize[i] = c.getDeckSize();
			System.out.println("[Server]Asigned Psize at "+i+" with "+pSize[i]);
			i++;
		}
	}
	
	public void sendCardToClient(ClientHandlerer c,boolean changeT)
	{
		Card tmpcard = deck.getLast();
		String msgD = "";
		if(tmpcard==null)
		{
			msgD = new PacketGetCard("-1").getData();
			c.sendMessageToClient(msgD);
			return;
		}
		else
			msgD = new PacketGetCard(tmpcard.TransalteToString()).getData();
		c.sendMessageToClient(msgD);
		c.addToDeck(tmpcard);
		deck.removeLast();
		
		if(changeT)
		{
			nextTurn(false);
			updatePSize();
			sendToAllClients(new PacketSizeP(pSize).getData());
		}
		
	}
	
	public void clientDisconnect(ClientHandlerer client)
	{	
		if(isGameStarted)
		{
			for(int i=0;i<client.getDeckSize();i++)
				deck.addCard(client.removeFromDeckAtX(i));
		}
		
		if(!clientList.remove(client))
			System.out.println("[Server]Error removing client:Cannot find");
		else
			System.out.println("[Server]Removed player");
		
		if(!isGameStarted)
		{
			nameList = "";
		
			for(ClientHandlerer c : clientList)
				nameList+=c.getPlayerName();
		
			sendToAllClients(new PacketUpdate(nameList).getData());
			
			if(!isCon)
				start();
		}	
	}
	
	public void reciveCardBack(ClientHandlerer c,String msg)
	{
		PacketSetCard packet = new PacketSetCard(msg);
		Card tmpCard = Card.getCardfromString(packet.getCardData());
		deck.addCard(tmpCard);
		c.removeFromDeck(tmpCard);
		if(c.getDeckSize()==0)
			c.setFinishPlayer();
		if(tmpCard.colorRet() == 'a')
		{
			centerCard = new Card(tmpCard.numRet(),packet.getChosenColor().charAt(0));
		}
		else
			centerCard = new Card(tmpCard.numRet(),tmpCard.colorRet());
		sendToAllClients(new PacketSetCard(centerCard.TransalteToString(),null).getData());
		
		if(tmpCard.numRet()>9)
		{
			switch(tmpCard.numRet())
			{
			
			case 10:
				nextTurn(true);
				deck.shuffleDeck();
				nextPDrawAmount(c,2);
				break;
				
			case 11:
				nextTurn(true);
				break;
				
			case 12:
				isReverse=!isReverse;
				break;
			case 14:
				nextTurn(true);
				deck.shuffleDeck();
				nextPDrawAmount(c,4);
				break;
			}
		}
		nextTurn(false);
		updatePSize();
		sendToAllClients(new PacketSizeP(pSize).getData());
		deck.shuffleDeck();
	}
	
	private void nextTurn(boolean skip)
	{
		if(isReverse==true)
		{
			turnNum--;
			if(turnNum<0)
				turnNum = clientList.size()-1;
		}
			
		else
		{
			turnNum++;
			turnNum = turnNum%clientList.size();
		}
		if(clientList.get(turnNum).getIsFinished())
		{
			nextTurn(skip);
		}
			
		if(!skip)
			clientList.get(turnNum).sendMessageToClient(new PacketTurn().getData());
	}
	
	private void nextPDrawAmount(ClientHandlerer c,int amount)
	{
		int nP = 0;
		for(int i=0;i<clientList.size();i++)
		{
			if(c.equals(clientList.get(i)))
			{
				nP=i;
			}	
		}
		nP++;
		nP=nP%clientList.size();
		for(int i=0;i<amount;i++)
			sendCardToClient(clientList.get(nP),false);
	}
	
}
