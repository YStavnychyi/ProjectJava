package Uno.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.manager.UIManagerS;

public class SettingsScreen extends Screens{

	public SettingsScreen(GameHandlerer gameH) {
		super(gameH);
		
		uiList = new UIManagerS(gameH);
		addComponents();
	}
	
	private void addComponents()
	{
		
	}
	
	public void update() {
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.yellow);
		//g.fillRect(0, 0, 100, 100);
	}

}
