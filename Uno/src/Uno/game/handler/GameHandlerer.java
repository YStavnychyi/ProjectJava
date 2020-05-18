package Uno.game.handler;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import Uno.game.GameManagement;
import Uno.game.input.KeyboardMangaer;
import Uno.game.input.MouseManager;
import Uno.game.input.SoundManager;
import Uno.window.screens.Screens;
import Uno.window.ui.manager.UIManagerS;
import Uno.window.ImagesLoader;

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
}
