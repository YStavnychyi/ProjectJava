package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButtonImage;
import Uno.window.ui.UIClicker;
import Uno.window.ui.manager.UIManagerS;

public class MenuScreen extends Screens{
	
	private Font font = new Font("TimesRoman",Font.BOLD,50);
	private BufferedImage start[],settings[],exit[];
	
	public MenuScreen(GameHandlerer gameH) {
		super(gameH);
		
		uiList = new UIManagerS(gameH);
		gameH.setUIM(uiList);
		
		start = new BufferedImage[2];
		start[0] = gameH.getButtonImage(0, 0, 200, 100);
		start[1] = gameH.getButtonImage(0, 100, 200, 100);
		
		settings = new BufferedImage[2];
		settings[0] = gameH.getButtonImage(400, 100, 200, 100);
		settings[1] = gameH.getButtonImage(200, 0, 200, 100);
		
		exit = new BufferedImage[2];
		exit[0] = gameH.getButtonImage(200, 100, 200, 100);
		exit[1] = gameH.getButtonImage(0, 200, 200, 100);
		
		addComponents();
	}
	
	private void addComponents()
	{		
		int wD=start[0].getWidth();
		int hD=start[0].getHeight();
		
		int x=gameH.getWidht()/2;
		int y=gameH.getHeight()/2;
		
		uiList.addComponent(new UIButtonImage(x-130,y-120,wD,hD,start,new UIClicker() {

			@Override
			public void ClickAction() {
				
				gameH.setUIM(gameH.getGameM().getConnectionScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getConnectionScreen());
				
				
			}}));
		
		uiList.addComponent(new UIButtonImage(x-130,y,wD,hD,settings,new UIClicker() {

			@Override
			public void ClickAction() {
				System.out.println("I am pressed");
				
			}}));
		
		uiList.addComponent(new UIButtonImage(x-130,y+130,wD,hD,exit,new UIClicker() {

			@Override
			public void ClickAction() {
				System.out.println("I am pressed");
				
			}}));
	}
	
	public void update() {	
		uiList.update();
	}
	public void render(Graphics g) {
			g.setFont(font);
			
			//setWH(); //pamietac jezeli zmienny ekran dac w inne miejsce
			
			//g.setColor(Color.red);
			//g.fillRect(0, 0, widht, height);
			
			g.setColor(Color.orange);
			g.drawString("Uno", widht/2-80, 100);
			
			uiList.render(g);
	}

}
