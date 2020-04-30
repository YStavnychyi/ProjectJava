package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButton;
import Uno.window.ui.UIClicker;
import Uno.window.ui.manager.UIManagerS;

public class MenuScreen extends Screens{
	
	private Font font = new Font("TimesRoman",Font.BOLD,50);
	
	public MenuScreen(GameHandlerer gameH) {
		super(gameH);
		
		uiList = new UIManagerS(gameH);
		gameH.setUIM(uiList);
		
		addComponents();
	}
	
	private void addComponents()
	{
		Color bcolors[] = {Color.green,Color.white};
		String text[] = {"Start","Settings","Exit"};
		int wD[]= new int[3];
		for(int i=0;i<wD.length;i++)
			wD[i]=gameH.getFontMetrics(font).stringWidth(text[i]);
		int hD=font.getSize();
		
		int x=gameH.getWidht()/2;
		int y=gameH.getHeight()/2;
		
		uiList.addComponent(new UIButton(x-100,y-120,wD[0],hD,text[0],bcolors,font,new UIClicker() {

			@Override
			public void ClickAction() {
				
				gameH.setUIM(gameH.getGameM().getConnectionScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getConnectionScreen());
				
				
			}}));
		
		uiList.addComponent(new UIButton(x-140,y,wD[1],hD,text[1],bcolors,font,new UIClicker() {

			@Override
			public void ClickAction() {
				System.out.println("I am pressed");
				
			}}));
		
		uiList.addComponent(new UIButton(x-80,y+130,wD[2],hD,text[2],bcolors,font,new UIClicker() {

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
			
			g.setColor(Color.red);
			g.fillRect(0, 0, widht, height);
			
			g.setColor(Color.orange);
			g.drawString("Uno", widht/2-80, 100);

			
			uiList.render(g);
	}

}
