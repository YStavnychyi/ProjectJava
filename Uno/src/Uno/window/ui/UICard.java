package Uno.window.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Uno.Card;

public class UICard extends UIComponent{
	
	private Card card;
	private BufferedImage cardImage;
	private UIClicker clicker;
	
	public UICard(int x, int y, int widht, int height,Card card,BufferedImage cardsI[],UIClicker clicker) {
		super(x, y, widht, height);
		this.card=card;
		this.cardImage = cardsI[card.numRet()];
		this.clicker=clicker;
		
		paintCard();

	}
	
	private void paintCard()
	{
		Color color = new Color(0, 0, 0);;
		int colc = cardImage.getRGB(50, 20);
		int getc;
		
		char tmp = card.colorRet();
		switch(tmp)
		{
		case 'r':
			color = new Color(255, 0, 0);
			break;
		
		case 'b':
			color = new Color(0, 0, 255);
			break;
			
		case 'y':
			color = new Color(255, 242, 0);
			break;
			
		case 'g':
			color = new Color(0, 255, 0);
			break;
			
		default:
			break;
		}
		
		if(tmp == 'r');
		for(int i=0;i<cardImage.getHeight();i++)
		{
			for(int j=0;j<cardImage.getWidth();j++)
			{
				getc = cardImage.getRGB(j, i);
				if(getc == colc)
					cardImage.setRGB(j, i, color.getRGB());
			}
		}
			
				
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(Graphics g) {

		g.drawImage(cardImage,x,y,width,height, null);
		
	}
	@Override
	public void ClickAction() {
		clicker.ClickAction();
		
	}
	@Override
	public void onMouseClicked(MouseEvent arg0) {
		if(hover&& isCorKey(arg0,MouseEvent.BUTTON1))
			ClickAction();
		
	}
	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
