package Uno.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Uno.Card;
import Uno.Deal;
import Uno.net.packets.Packet;
import Uno.net.packets.PacketLogin;
import Uno.net.packets.Packet.PacketsIDs;

public class ClientHandlerer extends Thread{
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private GameServer gameServer;
	
	private String name;
	private boolean isFinished;
	private Deal deck;
	
	public ClientHandlerer(Socket socket,GameServer gameServer,String name)
	{
		this.clientSocket = socket;
		this.gameServer = gameServer;
		this.name = name;
		deck = new Deal();
	}
	
	public void run()
	{
		try {
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sendMessageToClient(new PacketLogin(name).getData());
		
		String msg;
		
		try {
			while((msg = in.readLine())!=null)
			{
				PacketsIDs id = Packet.getPacketID(Integer.parseInt(msg.substring(0, 1)));
				
				msg = msg.substring(1);
				switch(id)
				{
				case LOGIN:
					System.out.println("[Server]["+name+"] LOGGED");
					break;
				
				case DISCONNECT:
					System.out.println("[Server]["+name+"] Disconnecting");
					gameServer.clientDisconnect(this);
					stopClient();
					break;
				
				case STARTGAME:
					gameServer.startGame();
					break;
					
				case GETCARD:
					gameServer.sendCardToClient(this,true);
					break;
					
				case SETCARD:
					gameServer.reciveCardBack(this,msg);
					break;
					
				default:
					System.out.println("[Server]["+name+"] Invalid Packet\n Message:"+msg);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void stopClient()
	{
		try {
			in.close();
			out.close();
			clientSocket.close();
			this.join();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageToClient(String msg)
	{
		out.println(msg);
	}
	
	public String getAdrress()
	{
		return clientSocket.getInetAddress().toString();
	}
	
	public String getPlayerName()
	{
		return name;
	}
	
	public void addToDeck(Card card)
	{
		deck.addCard(card);
	}
	
	public int getDeckSize()
	{
		return deck.getSize();
	}
	
	public Card removeFromDeckAtX(int i)
	{
		Card tmpCard = deck.getCard(i);
		deck.removeCard(i);
		return tmpCard;
	}
	
	public void removeFromDeck(Card card)
	{
		Card tmpCard = null;
		for(int i=0;i<deck.getSize();i++)
		{
			if(deck.getCard(i).colorRet()==card.colorRet() && deck.getCard(i).numRet() == card.numRet())
			{
				tmpCard = deck.getCard(i);
				System.out.println("[Server]["+name+"] Removed from deck:"+tmpCard.TransalteToString());
				deck.removeCard(i);
				break;
			}
			if(i==deck.getSize()-1)
				System.out.println("[Server]["+name+"]Could not find and remove card:"+card.TransalteToString());
		}
	}
	
	public boolean getIsFinished()
	{
		return isFinished;
	}
	
	public void setFinishPlayer()
	{
		isFinished = true;
	}
}
