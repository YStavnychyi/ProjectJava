package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButtonImage;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UINumericShow;
import Uno.window.ui.manager.UIManagerS;

public class SettingsScreen extends Screens{

	private BufferedImage plus[],minus[],back[];
	private Font font = new Font("TimesRoman",Font.BOLD,50);
	
	private UINumericShow sound,music;
	
	
	public SettingsScreen(GameHandlerer gameH) {
		super(gameH);
		
		uiList = new UIManagerS(gameH);
		minus = new BufferedImage[2];
		minus[0] = gameH.getButtonImage(400, 200, 50, 50);
		minus[1] = gameH.getButtonImage(400, 200, 50, 50);
		
		plus = new BufferedImage[2];
		plus[0] = gameH.getButtonImage(450, 200, 50, 50);
		plus[1] = gameH.getButtonImage(450, 200, 50, 50);
		
		back=new BufferedImage[2];
		back[0] = gameH.getButtonImage(500, 200, 50, 50);
		back[1] = gameH.getButtonImage(550, 200, 50, 50);
		
		addComponents();
	}
	
	private void addComponents()
	{
		sound = new UINumericShow(100,100,0,0,10,font);
		music = new UINumericShow(100,300,0,0,10,font);
		
		int wD = plus[0].getWidth();
		int hD = plus[0].getHeight();
		
		uiList.addComponent(sound);
		uiList.addComponent(music);
		
		uiList.addComponent(new UIButtonImage(200, 100, wD, hD, plus, new UIClicker() {

			@Override
			public void ClickAction() {
				if(sound.getMaxValue()>sound.getValue())
					sound.setValue(sound.getValue()+1);
				
			}} ));
		
		uiList.addComponent(new UIButtonImage(200, 150, wD, hD, minus, new UIClicker() {

			@Override
			public void ClickAction() {
				if(0<sound.getValue())
					sound.setValue(sound.getValue()-1);
				
			}} ));
		
		uiList.addComponent(new UIButtonImage(200, 300, wD, hD, plus, new UIClicker() {

			@Override
			public void ClickAction() {
				if(music.getMaxValue()>music.getValue())
					music.setValue(music.getValue()+1);
				
			}} ));
		
		uiList.addComponent(new UIButtonImage(200, 350, wD, hD, minus, new UIClicker() {

			@Override
			public void ClickAction() {
				if(0<music.getValue())
					music.setValue(music.getValue()-1);
				
			}} ));
		
		uiList.addComponent(new UIButtonImage(widht-70,height-100, wD, hD, back, new UIClicker() {
			@Override
			public void ClickAction() {
				gameH.setUIM(gameH.getGameM().getMenuScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getMenuScreen());
				
			}} ));
		
	}
	
	public void update() {
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawString("Sound Level:",100,80);
		g.drawString("Music Level:",100,280);
		
		
		uiList.render(g);
	}

}
