package Uno.window.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UIButtonImage extends UIComponent{
	
	private BufferedImage image[];
	private UIClicker clicker;

	public UIButtonImage(int x, int y, int widht, int height,BufferedImage image[],UIClicker clicker) {
		super(x, y, widht, height);
		this.image=image;
		this.clicker=clicker;
	}

	@Override
	public void update() {

		
	}

	@Override
	public void render(Graphics g) {
		
		if(hover)
			g.drawImage(image[0], x, y, null);
		else
			g.drawImage(image[1], x, y, null);
		
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

		
	}

}
