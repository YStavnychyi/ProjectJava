package Uno.game.handler;

import java.awt.Font;
import java.awt.FontMetrics;

import Uno.game.GameManagement;
import Uno.game.input.KeyboardMangaer;
import Uno.game.input.MouseManager;
import Uno.window.screens.Screens;
import Uno.window.ui.manager.UIManagerS;

public class GameHandlerer {

	private GameManagement gameMan;
	
	
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
}
