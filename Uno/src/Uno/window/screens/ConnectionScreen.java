package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButton;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UITextEnter;
import Uno.window.ui.manager.UIManagerS;

public class ConnectionScreen extends Screens{

	private Font font = new Font("TimesRoman",Font.BOLD,50);
	public ConnectionScreen(GameHandlerer gameH) {
		super(gameH);
		
		uiList = new UIManagerS(gameH);
		addComponents();
	}

	private void addComponents()
	{
		String text[]= {"Host","Connect:","Go Back Menu"};
		int wD[]=new int[text.length];
		for(int i=0;i<text.length;i++)
			wD[i]=gameH.getFontMetrics(font).stringWidth(text[i]);
		int hD=font.getSize();
		Color bcolors[] = {Color.green,Color.white};
		
		int x=gameH.getWidht()/2;
		int y=gameH.getHeight()/2;
		
		uiList.addComponent(new UIButton(50,y-200,wD[0],hD,text[0],bcolors,font,new UIClicker() {

			@Override
			public void ClickAction() {
				gameH.setUIM(gameH.getGameM().getGameScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getGameScreen());
				
			}}));
		
		uiList.addComponent(new UIButton(50,y,wD[1],hD,text[1],bcolors,font,new UIClicker() {

			@Override
			public void ClickAction() {

				System.out.println("Conect");
			}}));
		
		
		uiList.addComponent(new UIButton(x-100,y*2-100,wD[2],hD,text[2],bcolors,font,new UIClicker() {

			@Override
			public void ClickAction() {
				gameH.setUIM(gameH.getGameM().getMenuScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getMenuScreen());
				
			}}));
		
		uiList.addComponent(new UITextEnter(300,y,300,hD,font));
	}
	
	@Override
	public void update() {
		
		uiList.update();
	}

	@Override
	public void render(Graphics g) {
		
		
		
		g.setColor(Color.orange);
		g.fillRect(0, 0, widht, height);
		
		uiList.render(g);
		
	}

}
