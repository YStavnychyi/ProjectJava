package Uno.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Uno.window.ui.manager.UIManagerS;

public class KeyboardMangaer implements KeyListener{

	//private boolean keys[];
	//private boolean pressed[];
	
	//public boolean UP,DOWN,LEFT,RIGHT,ENTER;
	private UIManagerS curUIM;

	
	public KeyboardMangaer()
	{
		//keys=new boolean[256];
		//pressed = new boolean[256];
		//for(int i=0;i<pressed.length;i++)
		//	pressed[i]=false;
	}
	
	public void keyTyped(KeyEvent e) {
		if(curUIM!=null)
		{
			curUIM.keyTyped(e);
		}
	}


	public void keyPressed(KeyEvent e) {
		/*
		int tmp = e.getKeyCode();
		if(tmp<256)
		{
				keys[tmp]=true;
		}
			*/
	}

	public void keyReleased(KeyEvent e) {
		/*
		int tmp = e.getKeyCode();
		if(tmp<256)
		{
			keys[tmp]=false;
			pressed[tmp] = false;
		}	*/
		
	}
	
	public void update()
	{
		/*
		UP = (isKeyOnce(KeyEvent.VK_UP) || isKeyOnce(KeyEvent.VK_W));
		DOWN = (isKeyOnce(KeyEvent.VK_DOWN) || isKeyOnce(KeyEvent.VK_S));
		LEFT = (isKeyOnce(KeyEvent.VK_LEFT) || isKeyOnce(KeyEvent.VK_A));
		RIGHT = (isKeyOnce(KeyEvent.VK_RIGHT) || isKeyOnce(KeyEvent.VK_D));
		ENTER = isKeyOnce(KeyEvent.VK_ENTER);
		*/
		
	}
	
	/*
	private boolean isKeyOnce(int key)
	{
		if(keys[key]==true && pressed[key]==false)
		{
			pressed[key] = true;
			return true;
		}
		else return false;
	}
	*/
	public void setUIM(UIManagerS uim)
	{
		this.curUIM = uim;
	}
	

}
