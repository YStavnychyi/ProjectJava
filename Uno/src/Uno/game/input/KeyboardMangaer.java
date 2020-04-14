package Uno.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardMangaer implements KeyListener{

	private boolean keys[];
	
	public boolean UP,DOWN,LEFT,RIGHT,ENTER;
	private boolean pressed;

	
	public KeyboardMangaer()
	{
		keys=new boolean[256];
		pressed=false;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}


	public void keyPressed(KeyEvent e) {
		int tmp = e.getKeyCode();
		if(tmp<256)
		{
				keys[tmp]=true;
		}
			
	}

	public void keyReleased(KeyEvent e) {
		int tmp = e.getKeyCode();
		if(tmp<256)
		{
			keys[tmp]=false;
		}	
		pressed = false;
	}
	
	public void update()
	{
		UP = (isKeyOnce(KeyEvent.VK_UP) || isKeyOnce(KeyEvent.VK_W));
		DOWN = (isKeyOnce(KeyEvent.VK_DOWN) || isKeyOnce(KeyEvent.VK_S));
		LEFT = (isKeyOnce(KeyEvent.VK_LEFT) || isKeyOnce(KeyEvent.VK_A));
		RIGHT = (isKeyOnce(KeyEvent.VK_RIGHT) || isKeyOnce(KeyEvent.VK_D));
		ENTER = isKeyOnce(KeyEvent.VK_ENTER);
		
	}
	
	private boolean isKeyOnce(int key)
	{
		if(keys[key]==true && pressed==false)
		{
			pressed = true;
			return true;
		}
		else return false;
	}
	

}
