package Uno.window.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIHolder extends UIComponent{

	private int position = 0;
	private int maxVal = 0;
	private ArrayList<UICard> cardArray;
	
	public UIHolder(int x, int y, int widht, int height, ArrayList<UICard> cardArray) {
		super(x, y, widht, height);
		this.cardArray = cardArray;
	}

	@Override
	public void update() {}

	@Override
	public void render(Graphics g) {}

	@Override
	public void ClickAction() {}

	@Override
	public void onMouseClicked(MouseEvent arg0) {}

	@Override
	public void onKeyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'a')
			if(position>0)
			{
				cardArray.get(position).setVisibility(false);
				position--;
				cardArray.get(position).setVisibility(true);
			}
				
		if(e.getKeyChar() == 'd')
			if(position<maxVal-1)
			{
				cardArray.get(position).setVisibility(false);
				position++;
				cardArray.get(position).setVisibility(true);
			}
				
	}

	public void changeAmount(int val)
	{
		maxVal += val;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public void resetSelect() {
		position=0;
	}
}
