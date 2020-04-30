package Uno.game;

import Uno.game.handler.GameHandlerer;
import Uno.game.input.*;
import Uno.window.*;
import Uno.window.screens.*;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class GameManagement implements Runnable{
	
	private AppWindow window;//okno
	private Thread thread;//thread
	private BufferStrategy buffer;//buffer do obrazow(3)
	private Graphics g;//grafika na ktorej sie rysuje
	private boolean live;//czy gra dziala 
	private KeyboardMangaer keyM;//klawiatura
	private MouseManager mouseM;//myszka
	private GameHandlerer gameH;//handlerer
	
	public Screens gameScreen;//okno do gry
	public Screens menuScreen;//okno do gry
	public Screens settingsScreen;//okno do gry
	public Screens connectionScreen;//okno do polczen
	
	private int HEIGHT,WIDHT;
	
	
	public GameManagement()
	{ 
		WIDHT=800;
		HEIGHT=600;
		
		keyM=new KeyboardMangaer();
		mouseM=new MouseManager();
	}
	
	private void setUp()
	{
		gameH = new GameHandlerer(this);
		
		window = new AppWindow(WIDHT,HEIGHT);//tworzone okno
		window.windowRet().addKeyListener(keyM);
		window.windowRet().addMouseListener(mouseM);
		window.windowRet().addMouseMotionListener(mouseM);
		window.canvasRet().addMouseListener(mouseM);
		window.canvasRet().addMouseMotionListener(mouseM);
		
		menuScreen = new MenuScreen(gameH);//stworzenie okna na MENU
		gameScreen = new GameScreen(gameH);//stworzenie okna na Gry
		settingsScreen = new SettingsScreen(gameH);//stworzenie okna na ustawien
		connectionScreen = new ConnectionScreen(gameH);//stworzenie okna do polaczen do gier
		
		setCurrentScreen(menuScreen);//ustawienie ekranu na Menu
	}
	
	public synchronized void startThread()
	{
		//do rozpoczecia Threta i gry
		setUp();
		if(live==false)
		{
			live=true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stopThread()
	{
		//Do zakonczenia
		if(live==true)
		{
			live=false;
			window.exitWindow();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		
	}
	
	private void render()
	{
		//Rysowanie na ekran
		buffer = window.canvasRet().getBufferStrategy();
		if(buffer == null)
		{
			window.canvasRet().createBufferStrategy(3);
			return;
		}
		
		g = buffer.getDrawGraphics();
		
		g.clearRect(0, 0, WIDHT, HEIGHT);
			
		if(Screens.getScreen()!=null)
			Screens.getScreen().render(g);
		
		buffer.show();
		g.dispose();
	}
	
	private void update()
	{
		//Update rzeczy kt�e si� dziej� (zmiana pozycji itp
		keyM.update();
		if(Screens.getScreen()!=null)
			Screens.getScreen().update();
	}

	@Override
	public void run() {
		//Game Loop
		int FPS=60;
		double timePerTick = 1000000000/FPS;
		double DELTA = 0;
		long CURRENT;
		long LAST = System.nanoTime();
		
		long TIMER = 0;
		long TICKS = 0;
		
		while(live==true)
		{
			CURRENT = System.nanoTime();
			DELTA += (CURRENT-LAST)/timePerTick;
			TIMER += CURRENT-LAST;
			LAST=CURRENT;
			
			if(DELTA >=1)
			{
				render();
				update();
				TICKS++;
				DELTA--;
			}
			
			if(TIMER>=1000000000)
			{
				System.out.println("FPS:"+TICKS);
				TICKS=0;
				TIMER=0;
			}
			
		}
		stopThread();
	}

	
	public KeyboardMangaer getKeyManager()
	{
		return keyM;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseM;
	}
	
	public void setCurrentScreen(Screens screen)
	{
		Screens.setScreen(screen);
	}
	
	public FontMetrics getFontMetrics(Font font)
	{
		return window.canvasRet().getFontMetrics(font);
	}
	
	public int getWidth()
	{
		return WIDHT;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public Screens getMenuScreen()
	{
		return menuScreen;
	}
	
	public Screens getGameScreen()
	{
		return gameScreen;
	}
	
	public Screens getConnectionScreen()
	{
		return connectionScreen;
	}
	
	public Screens getSettingsScreen()
	{
		return settingsScreen;
	}
	
}
