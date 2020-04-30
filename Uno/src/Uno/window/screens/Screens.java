package Uno.window.screens;

import java.awt.Graphics;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.manager.UIManagerS;

public abstract class Screens {
	
	private static Screens cScreen = null;
	protected UIManagerS uiList;
	
	public static Screens getScreen()
	{
		return cScreen;
	}
	
	public static void setScreen(Screens tmp)
	{
		cScreen = tmp;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	protected GameHandlerer gameH;
	
	public Screens(GameHandlerer gameH)
	{
		this.gameH = gameH;
		setWH();
	}
	
	protected int widht,height;
	
	protected void setWH()
	{
		widht=gameH.getWidht();
		height=gameH.getHeight();
	}
	
	public UIManagerS getUIList()
	{
		return uiList;
	}
	
}
