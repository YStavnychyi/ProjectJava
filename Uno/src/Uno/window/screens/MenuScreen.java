package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import Uno.game.GameManagement;

public class MenuScreen extends Screens{
	
	private int selected = 0;
	private Font font;
	private Color color[];
	
	private boolean pressed;
	public MenuScreen(GameManagement gameManger) {
		super(gameManger);
		font = new Font("TimesRoman",Font.BOLD,50);
		
		color = new Color[3];
		pressed = false;
	}
	
	public void update() {
		if(selected>0 && gameManger.getKeyManager().UP) selected--;
		if(selected<2 && gameManger.getKeyManager().DOWN) selected++;
		if(selected == 2 && gameManger.getKeyManager().ENTER) System.out.println("TMP EXIT TEST");
		
		color[0] = Color.orange;
		color[1] = Color.orange;
		color[2] = Color.orange;
		color[selected] = Color.white;
	}
	public void render(Graphics g) {
			g.setFont(font);
			
			g.setColor(Color.red);
			g.fillRect(0, 0, gameManger.WIDHT, gameManger.HEIGHT);
			
			g.setColor(color[0]);
			g.drawRect(gameManger.WIDHT/2-150, gameManger.HEIGHT/2-150, 250, 80);
			g.drawString("START", gameManger.WIDHT/2-110, gameManger.HEIGHT/2-90);
			
			g.setColor(color[1]);
			g.drawRect(gameManger.WIDHT/2-150, gameManger.HEIGHT/2, 250, 80);
			g.drawString("SETTINGS",gameManger.WIDHT/2-150, gameManger.HEIGHT/2+60);
			
			g.setColor(color[2]);
			g.drawRect(gameManger.WIDHT/2-150, gameManger.HEIGHT/2+150, 250, 80);
			g.drawString("EXIT",gameManger.WIDHT/2-80, gameManger.HEIGHT/2+210);
			
			g.setColor(Color.orange);
			g.drawString("Uno", gameManger.WIDHT/2-80, 100);
	}

}
