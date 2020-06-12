package Uno;

import Uno.game.GameManagement;

public class UnoApp {

	public static void main(String[] args) throws InterruptedException{

		GameManagement game = new GameManagement();
		game.startThread();
		
		//Player p = new Player();

	}
}
