package Uno.window.screens;

import java.awt.Color;
import java.awt.Graphics;

import Uno.game.GameManagement;

public class GameScreen extends Screens{


	public GameScreen(GameManagement gameManger) {
		super(gameManger);
	}


	public void update() {

		
	}


	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 100, 100);
		
	}

}
