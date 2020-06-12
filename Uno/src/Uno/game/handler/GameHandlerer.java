package Uno.game.handler;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import Uno.Card;
import Uno.Deal;
import Uno.game.GameManagement;
import Uno.game.input.KeyboardMangaer;
import Uno.game.input.MouseManager;
import Uno.game.input.SoundManager;
import Uno.net.client.GameClient;
import Uno.net.server.GameServer;
import Uno.window.screens.ConnectionScreen;
import Uno.window.screens.Screens;
import Uno.window.ui.manager.UIManagerS;
import Uno.window.CardLoader;
import Uno.window.ImagesLoader;

public class GameHandlerer {

	private GameManagement gameMan;
	private GameServer gameServer;
	private GameClient gameClient;
	
	public GameHandlerer(GameManagement gm)
	{
		this.gameMan=gm;
	}
	
	public int getHeight()
	{
		return gameMan.getHeight();
	}
	
	public int getWidht()
	{
		return gameMan.getWidth();
	}
	
	public GameManagement getGameM()
	{
		return gameMan;
	}
	
	public KeyboardMangaer getKeyM()
	{
		return gameMan.getKeyManager();
	}
	
	public MouseManager getMouseM()
	{
		return gameMan.getMouseManager();
	}
	
	public void setUIM(UIManagerS uim)
	{
		gameMan.getMouseManager().setUIM(uim);
		gameMan.getKeyManager().setUIM(uim);
	}
	
	public FontMetrics getFontMetrics(Font font)
	{
		return gameMan.getFontMetrics(font);
	}
	
	public void setCurrentScreen(Screens screen)
	{
		gameMan.setCurrentScreen(screen);
	}
	
	public BufferedImage getImage(String path)
	{
		return ImagesLoader.loadImage(path);
	}
	
	public BufferedImage getCardImage(int x,int y,int w,int h)
	{
		return ImagesLoader.cropOutImage(x, y, w, h, gameMan.getSheetHolder().getCardSheet());
	}
	
	public BufferedImage getButtonImage(int x,int y,int w,int h)
	{
		return ImagesLoader.cropOutImage(x, y, w, h, gameMan.getSheetHolder().getButtonSheet());
	}
	
	public SoundManager getSoundManager()
	{
		return gameMan.getSoundManager();
	}
	
	public BufferedImage[] loadCards()
	{
		return CardLoader.laodCards(this);
	}
	
	public BufferedImage[] loadRotatedCards(BufferedImage tmpImg)
	{
		return CardLoader.laodRotatedCards(tmpImg);
	}
	
	public void CreateServer()
	{
		
		if(gameServer==null)
		{
			gameServer = new GameServer(1331);
			gameServer.start();
		}
		
	}
	
	public void CreateClient(String ip)
	{
		
		if(gameClient==null)
		{
			gameClient = new GameClient(ip,1331,this);
			gameClient.start();
			gameClient.SendMessageToServer("1");
		}
		
	}
	
	public void stopServer()
	{
		
		if(gameServer!=null)
		{
			gameServer.stopServer();
			gameServer = null;
		}
		
	}
	
	public void stopClient()
	{
		
		if(gameClient!=null)
		{
			gameClient.stopClient();
			gameClient = null;
		}
		
	}
	
	public void startGameServer()
	{
		
		if(gameServer!=null)
		{
			gameServer.startGame();
			gameMan.newGame();
			
		}
		
	}
	
	private ConnectionScreen getConnectionScreen()
	{
		return (ConnectionScreen)gameMan.connectionScreen;
	}
	
	public void startGameClient()
	{
		if(gameServer==null)
		{
			gameMan.newGame();
			getConnectionScreen().disposeOptionDialog();
			getConnectionScreen().startGameForClient();
		}		
	}
	
	public String getClientNameAt(int index)
	{
		
		if(gameClient!=null)
		{
			String[] names = gameClient.getNameList().split(",");
			if(names.length-1>=index)
			{
				return names[index];
			}
		}
		return "";
		
	}
	

	public Deal getClientDeck()
	{
		return gameClient.getClientDeck();
	}
	
	public boolean isClientTurn()
	{
		return gameClient.isClientTurn();
	}
	
	public void endClientTurn()
	{
		gameClient.endClientTurn();
	}
	
	public void clientTakeCard()
	{
		gameClient.sendTakeCardToServer();
	}
	
	public Card getCenterCard()
	{
		return gameClient.getCenterCard();
	}
	
	public void SetCard(Card card,String col)
	{
		gameClient.sendSelectedCardToServer(card, col);
	}
	
	public int getPDeckSize(int iNum)
	{
		return gameClient.getSizeOfPlayer(iNum);
	}
	
	public String getClientName()
	{
		return gameClient.getClientName();
	}
	
	public boolean getIsClientFinished()
	{
		return gameClient.getIsClientFinished();
	}
	
	public void setClientFinished()
	{
		gameClient.setClientFinishedGame();
	}
}
