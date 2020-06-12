package Uno.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Uno.game.handler.GameHandlerer;

public class WindowHandlerer implements WindowListener{

	private GameHandlerer gameH;
	
	public WindowHandlerer(GameHandlerer gameH)
	{
		this.gameH = gameH;
		this.gameH.getGameM().getFrame().addWindowListener(this);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		gameH.stopServer();
		gameH.stopClient();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
