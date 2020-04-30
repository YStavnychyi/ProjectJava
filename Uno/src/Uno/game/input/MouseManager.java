package Uno.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Uno.window.ui.manager.UIManagerS;

public class MouseManager implements MouseListener,MouseMotionListener {
	
	private int mouseX,mouseY;
	private UIManagerS curUIM;
	
	public MouseManager()
	{

	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		
		if(curUIM!=null)
		{
			curUIM.mouseMoved(arg0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(curUIM!=null)
		{
			curUIM.mouseClicked(arg0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public void setUIM(UIManagerS uim)
	{
		this.curUIM = uim;
	}

}
