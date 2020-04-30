package Uno.window.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UIButton extends UIComponent{

	private String text;
	private Color[] color;
	private UIClicker clicker;
	private Font font;
	
	public UIButton(int x, int y, int widht, int height,String text,Color[] color,Font font,UIClicker clicker) 
	{
		super(x, y, widht, height);
		this.text=text;
		this.color=color;
		this.clicker=clicker;
		this.font=font;
		
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setFont(font);
		if(hover)
			g.setColor(color[0]);
		else
			g.setColor(color[1]);
		g.drawString(text, x, y + font.getSize());
		//g.drawRect(x, y, width, height);
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
