package Uno.window.screens;

import java.awt.Graphics;

import Uno.game.GameManagement;

public abstract class Screens {
	
	private static Screens cScreen = null;
	
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
	
	protected GameManagement gameManger;
	
	public Screens(GameManagement gameManger)
	{
		this.gameManger = gameManger;
	}
}
