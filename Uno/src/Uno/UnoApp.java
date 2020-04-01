package Uno;

public class UnoApp {

	public static void main(String[] args) throws CloneNotSupportedException {
			System.out.println("Test");
			CardManage c = new CardManage();		
			c.printCards();
			c.cardData();
			c.cRand();
			c.cardData();
			c.validCardAmount();
			int maxx=12;
			Card tmp_card;
			for(int i=0;i<maxx;i++)
			{
				tmp_card = c.giveCard();
				c.getBackCard(tmp_card);///jest tu gdzies b³ad w stawianiem spowrotem na g³ówna talie just so you know
			}
			
			c.cardData();
			c.validCardAmount();
	} 

}
