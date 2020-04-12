package Uno.game;

import Uno.window.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import java.awt.image.BufferedImage;



public class GameManagement implements Runnable{
	
	private AppWindow window;
	private Thread thread;
	private BufferStrategy buffer;
	private Graphics g;
	private boolean live;
	
	
	private BufferedImage tmp;
	private int x,y;
	
	
	public GameManagement()
	{
		window = new AppWindow();
		
		tmp = ImagesLoader.loadImage("/+2.jpg");
		x=0;y=0;
	}
	
	public synchronized void startThread()
	{
		if(live==false)
		{
			live=true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stopThread()
	{
		if(live==true)
		{
			live=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		
	}
	
	private void render()
	{
		buffer = window.canvasRet().getBufferStrategy();
		if(buffer == null)
		{
			window.canvasRet().createBufferStrategy(3);
			return;
		}
		
		g = buffer.getDrawGraphics();
		
		g.clearRect(0, 0, 800, 600);
		
		
		g.setColor(Color.red);
		g.fillRect(0, 0, 800, 600);
		
		g.drawImage(tmp, x, y, null);
		
		
		buffer.show();
		g.dispose();
	}
	
	private void update()
	{
		this.x++;
		if(x>1200)
			this.x=-100;
	}

	@Override
	public void run() {
		while(live==true)
		{
			render();
			update();
		}
		stopThread();
	}
}
