package Uno.window.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIComponent {
	
	protected int x,y;
	protected int width,height;
	protected boolean hover;
	protected Rectangle hitbox;
	
	public UIComponent(int x,int y,int widht,int height)
	{
		this.x=x;
		this.y=y;
		this.width=widht;
		this.height=height;
		hitbox = new Rectangle(x,y,widht,height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void ClickAction();
	
	public boolean isCorKey(MouseEvent arg0,int key)
	{
		if(arg0.getButton()==key)
			return true;
		return false;
	}
	
	public void onHover(int X,int Y)
	{
		if(hitbox.contains(X, Y))
			hover=true;
		else
			hover=false;
	}
	
	public abstract void onMouseClicked(MouseEvent arg0);
	
	public abstract void onKeyTyped(KeyEvent e);
	
	public void updatePosition(int x,int y)
	{
		this.x=x;
		this.y=y;
		this.hitbox=new Rectangle(x,y,width,height);
	}
	
}
