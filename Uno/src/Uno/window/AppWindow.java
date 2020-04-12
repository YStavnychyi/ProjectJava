package Uno.window;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

enum GAME_SCREEN{
	GAME,
	MAIN,
	SETTING
};

public class AppWindow{
	private JFrame window;
	private Canvas canvas;
	
	
	private GAME_SCREEN game_screen;
	
	public AppWindow()
	{
		game_screen=GAME_SCREEN.MAIN;
		
		
		window =new JFrame("Uno - Project");
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(800,600));
		
		window.add(canvas);
	}
	
	public Canvas canvasRet()
	{
		return canvas;
	}
	
	public void render()
	{
		if(game_screen==GAME_SCREEN.MAIN)
		{
			
		}
	}
	
	
}
