package Uno.window.ui.manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIComponent;

public class UIManagerS {
	
	private ArrayList<UIComponent> uiList;
	private GameHandlerer gameH;
	
	public UIManagerS(GameHandlerer gameH)
	{
		this.gameH=gameH;
		uiList=new ArrayList<UIComponent>();
	}
	
	public void render(Graphics g)
	{
		try//not sure if good solution
		{
		for(UIComponent i: uiList)
			i.render(g);
		}
		catch(java.util.ConcurrentModificationException e)
		{
			
		}
	}
	
	public void update()
	{
		for(UIComponent i: uiList)
			i.update();
		
	}
	
	public void mouseMoved(MouseEvent arg0)
	{
		for(UIComponent i: uiList)
			i.onHover(arg0.getX(), arg0.getY());
	}
	
	public void mouseClicked(MouseEvent arg0)
	{
		try//not sure if good solution
		{
		for(UIComponent i: uiList)
		{
			i.onMouseClicked(arg0);
		}
		}
		catch(java.util.ConcurrentModificationException e)
		{
			
		}
			
	}
	
	public void keyTyped(KeyEvent e)
	{
		for(UIComponent i: uiList)
			i.onKeyTyped(e);
	}
	
	public void addComponent(UIComponent c)
	{
		uiList.add(c);
	}
	
	public void removeComponent(UIComponent c)
	{
		uiList.remove(c);
	}
	
	public UIComponent getComponent(int i)
	{
		return uiList.get(i);
	}
	

}
