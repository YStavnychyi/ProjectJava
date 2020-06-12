package Uno.window.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Uno.window.screens.GameScreen;

public class UIHolder extends UIComponent{

	private int position = 0;
	private int maxVal = 0;
	private ArrayList<UICard> cardArray;
	private GameScreen gameS;
	
	public UIHolder(int x, int y, int widht, int height, ArrayList<UICard> cardArray, GameScreen gameS) {
		super(x, y, widht, height);
		this.cardArray = cardArray;
		this.gameS = gameS;
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
		if((int)e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			boolean check = gameS.commitAction();
			if(check)
			{
				System.out.println("XXXX Tried to send a card");
			}
			else
			{
				System.out.println("XXXX Invalid move");
			}
		}
				
	}

	public void changeAmount(int val)
	{
		maxVal += val;
	}
	
	public void changeToMax(int val)
	{
		maxVal = val;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public void resetSelect() {
		position=0;
	}
}
