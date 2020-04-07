package Uno.window;

import javax.swing.*;

public class AppWindow{
	private JFrame window;
	private double scale;
	
	public AppWindow()
	{
		scale = 1.0;
		window =new JFrame("Uno - Project");
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void showUno()
	{
		window.setVisible(true);
	}
}
