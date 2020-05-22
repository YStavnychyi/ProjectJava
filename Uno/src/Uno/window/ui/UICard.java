package Uno.window.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Uno.Card;

public class UICard extends UIComponent{
	
	private Card card;
	private BufferedImage cardImage;
	private boolean invisible;
	
	public UICard(int x, int y, int widht, int height,Card card,BufferedImage cardsI[]) {
		super(x, y, widht, height);
		this.card=card;
		this.cardImage = cardsI[getIndex(card.numRet(),card.colorRet())];
		invisible=false;

	}
	
	private int getIndex(int n,char c)
	{
		if(n>=13)
			return 52+(n-13);
		int k=0;
		if(c=='b') k=1;
		else if(c=='y') k=2;
		else if(c=='g') k=3;
		return n*4+k;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(Graphics g) {
		if(!invisible)
			g.drawImage(cardImage,x,y,width,height, null);
		
	}
	@Override
	public void ClickAction() {
		
	}
	@Override
	public void onMouseClicked(MouseEvent arg0) {

		
	}
	@Override
	public void onKeyTyped(KeyEvent e) {
	}
	
	public int getNum()
	{
		return card.numRet();
	}
	
	public char getCol()
	{
		return card.colorRet();
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public BufferedImage getImage()
	{
		return cardImage;
	}
	
	public void setVisibility(boolean vis)
	{
		this.invisible = vis;
	}
}
