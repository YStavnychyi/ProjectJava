package Uno.game;

import Uno.game.input.KeyboardMangaer;
import Uno.window.*;
import Uno.window.screens.*;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class GameManagement implements Runnable{
	
	private AppWindow window;//okno
	private Thread thread;//thread
	private BufferStrategy buffer;//buffer do obrazow(3)
	private Graphics g;//grafika na ktorej sie rysuje
	private boolean live;//czy gra dziala 
	private KeyboardMangaer keyM;
	
	private Screens gameScreen;//okno do gry
	private Screens menuScreen;//okno do gry
	private Screens settingsScreen;//okno do gry
	
	public int HEIGHT,WIDHT;
	private int screenDimension;
	
	
	public GameManagement()
	{ 
		
		screenDimension=0;
		ScreeDimSet(screenDimension);
		
		keyM=new KeyboardMangaer();
		window = new AppWindow(WIDHT,HEIGHT);//tworzone okno
		window.windowRet().addKeyListener(keyM);
		
		menuScreen = new MenuScreen(this);//stworzenie okna na MENU
		gameScreen = new GameScreen(this);//stworzenie okna na Gry
		settingsScreen = new SettingsScreen(this);//stworzenie okna na ustawien
		
		setCurrentScreen(menuScreen);//ustawienie ekranu na Menu
	}
	
	public synchronized void startThread()
	{
		//do rozpoczecia Threta i gry
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
		//Update rzeczy któe siê dziej¹ (zmiana pozycji itp
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
	
	private void ScreeDimSet(int sd)
	{
		//Zmiana rozmiaru okna
		switch(sd)
		{
		default:
			WIDHT=800;
			HEIGHT=600;
			break;
			
		case -1:
			WIDHT=640;
			HEIGHT=480;
			break;
			
		case 1:
			WIDHT=1024;
			HEIGHT=768;
			break;
		}
	}
	
	public KeyboardMangaer getKeyManager()
	{
		return keyM;
	}
	
	public void setCurrentScreen(Screens screen)
	{
		Screens.setScreen(screen);
	}
	
	
	
}
