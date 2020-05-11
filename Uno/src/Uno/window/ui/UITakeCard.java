package Uno.window.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UITakeCard extends UIComponent{

	private boolean isTurn;
	private BufferedImage card;
	private UIClicker clicker;
	
	
	public UITakeCard(int x, int y, int widht, int height,BufferedImage card,boolean isTurn,UIClicker clicker) {
		super(x, y, widht, height);
		this.card=card;
		this.clicker=clicker;
		this.isTurn = isTurn;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(card,x,y,width,height, null);
		
	}

	@Override
	public void ClickAction() {
		clicker.ClickAction();
		
	}

	@Override
	public void onMouseClicked(MouseEvent arg0) {
		if(hover && isTurn && isCorKey(arg0,MouseEvent.BUTTON1))
		{
			ClickAction();
		}
		
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
