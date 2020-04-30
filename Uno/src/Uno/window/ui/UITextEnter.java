package Uno.window.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UITextEnter extends UIComponent{

	private String text;
	private Font font;
	private boolean isFocused;
	
	public UITextEnter(int x, int y, int widht, int height,Font font) {
		super(x, y, widht, height);
		this.font=font;
		this.isFocused=false;
		this.text="aaaa";

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.cyan);
		g.drawRect(x, y, width, height);
		g.setColor(Color.white);
		
		if(!text.isEmpty())
			g.drawString(text,x,y+font.getSize());
		
	}

	@Override
	public void ClickAction() {
		isFocused=true;
		
	}

	@Override
	public void onMouseClicked(MouseEvent arg0) {
		if(hover&& isCorKey(arg0,MouseEvent.BUTTON1))
			ClickAction();
		else if(!hover)
			isFocused=false;
		
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		if(isFocused)
		{
			if(e.getKeyChar()==(char)KeyEvent.VK_BACK_SPACE)
			{
				if(text.length()!=0)
				{
					text = text.substring(0,text.length()-1);
				}
			}
			else
			{
					text+=e.getKeyChar();
					System.out.println((int)e.getKeyChar());
			}
		}
		
	}

}
