package Uno.window.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UINumericShow extends UIComponent{

	private Font font;
	private int maxValue;
	private int currentValue;
	
	public UINumericShow(int x, int y, int widht, int height,int maxValue,Font font) {
		super(x, y, widht, height);
		this.maxValue=maxValue;
		this.currentValue=maxValue;
		this.font=font;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.drawString(""+currentValue, x, y+font.getSize());
		
	}

	@Override
	public void ClickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getValue()
	{
		return currentValue;
	}
	
	public void setValue(int currentValue)
	{
		this.currentValue =currentValue;
	}
	
	public int getMaxValue()
	{
		return maxValue;
	}
}
