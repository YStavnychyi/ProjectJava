package Uno;

import Uno.window.AppWindow;

public class UnoApp {

	public static void main(String[] args) throws CloneNotSupportedException {
			System.out.println("Test");
			CardManage c = new CardManage();		
			//c.printCards();
			//c.cardData();
			c.cRand();
			c.cardData();
			c.validCardAmount();
			int maxx=20;
			Card tmp_card;
			for(int i=0;i<maxx;i++)
			{
				tmp_card = c.giveCard();
				c.getBackCard(tmp_card);
			}
			
			c.cardData();
			c.validCardAmount();
			/*
			AppWindow w = new AppWindow();
			w.showUno();*/
			
	} 

}
