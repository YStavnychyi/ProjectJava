package Uno.window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class AppWindow{
	private JFrame window;
	private Canvas canvas;
	
	
	public AppWindow(int widht,int height)
	{
		
		window =new JFrame("Uno - Project");
		window.setSize(widht, height);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(widht,height));
		canvas.setFocusable(false);//bez tego nie dziala input
		
		window.add(canvas);
	}
	
	public Canvas canvasRet()
	{
		return canvas;
	}
	
	public JFrame windowRet()
	{
		return window;
	}
	
	public void resizeWindow(int widht,int height)
	{
		window.setBounds(0, 0, widht, height);
		canvas.setBounds(0, 0, widht, height);
	}
	
	public void exitWindow()
	{
		window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING));
	}
	
}
