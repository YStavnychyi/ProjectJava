package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButton;
import Uno.window.ui.UIButtonImage;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UITextEnter;
import Uno.window.ui.manager.UIManagerS;

public class ConnectionScreen extends Screens{

	private Font font = new Font("TimesRoman",Font.BOLD,30);
	
	private BufferedImage host[],connect[];
	private UITextEnter textIP;
	
	public ConnectionScreen(GameHandlerer gameH) {
		super(gameH);
		
		host = new BufferedImage[2];
		host[0] = gameH.getButtonImage(200, 300, 200, 100);
		host[1] = gameH.getButtonImage(400, 0, 200, 100);
		
		connect = new BufferedImage[2];
		connect[0] = gameH.getButtonImage(200, 200, 200, 100);
		connect[1] = gameH.getButtonImage(0, 300, 200, 100);
		
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
		
		int w = host[0].getWidth(),h = host[0].getHeight();
		
		int x=gameH.getWidht()/2;
		int y=gameH.getHeight()/2;
		
		uiList.addComponent(new UIButtonImage(50,y-200,w,h,host,new UIClicker() {

			@Override
			public void ClickAction() {
				gameH.setUIM(gameH.getGameM().getGameScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getGameScreen());
				
			}}));
		
		uiList.addComponent(new UIButtonImage(50,y,w,h,connect,new UIClicker() {

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
		textIP=new UITextEnter(300,y+30,420,hD,font,24);
		uiList.addComponent(textIP);
	}
	
	@Override
	public void update() {
		
		uiList.update();
	}

	@Override
	public void render(Graphics g) {
		
		
		
		//g.setColor(Color.orange);
		//g.fillRect(0, 0, widht, height);
		
		uiList.render(g);
		
	}

}
