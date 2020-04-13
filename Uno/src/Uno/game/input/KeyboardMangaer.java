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
			if(!pressed == true)
			{
				keys[tmp]=true;
				pressed = true;
			}
			else 
			{
				keys[tmp]=false;
			}
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
		UP = (keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]);
		DOWN = (keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]);
		LEFT = (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]);
		RIGHT = (keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]);
		ENTER = keys[KeyEvent.VK_ENTER];
		
	}
	
	

}
