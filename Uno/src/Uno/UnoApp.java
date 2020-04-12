package Uno;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//import Uno.game.GameManagement;

public class UnoApp {
	
	static Random ran = new Random();
	static Deal play1 = new Deal();
	static Deal play2 = new Deal();
	static Deal play3 = new Deal();
	static Deal play4 = new Deal();
	static CardHandler deck = new CardHandler();
	static Scanner scan = new Scanner(System.in);
	static Deal discardCards = new Deal();
	static int numPlayer = 0;
	static boolean reverse = false;
	static boolean skip = false;
	static boolean gameEnded = false;
	static boolean draw2 = false;
	static boolean draw4 = false;
	
	public static void main(String[] args){
	
		//GameManagement game = new GameManagement();
		//game.startThread();
		
		deck.shuffleDeck();
		boolean playerNumGot = false;
		do {
			try {
				System.out.print("Ilu graczy chcesz zagrac?(1-3) ");
				numPlayer = scan.nextInt();
				scan.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Invalid Input");
                scan.nextLine();
			}
			if(numPlayer <= 3 && numPlayer >= 1) {
				playerNumGot = true;
			}else{
				System.out.println("Wprowadz liczbe pomiedzy 1 i 3");
				playerNumGot = false;
			}
		}while(!playerNumGot);
		if(numPlayer == 1) {
			for(int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
			}
		}else if (numPlayer == 2) {
			for(int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
				play3.addCard(deck);
			}
		}else if (numPlayer == 3) {
			for(int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
				play3.addCard(deck);
				play4.addCard(deck);
			}
		}
		
		discardCards.addCard(deck);// adds the top of the deck to the discard pile "face up"
		if(Card.numRet(discardCards.getLast()) == 14) {
			discardCards.addCard(wildColor(14));
		}else if (Card.numRet(discardCards.getLast()) == 13) {
			discardCards.addCard(wildColor(13));
		}
		
		int currentPlayer = 1;
		boolean uno = false;

		do {//until the game has ended, do the following
			while(currentPlayer == 1) {
				skip = false;
				draw2 = false;
				draw4 = false;
				//sleep(1000);
				boolean unoCalled = false;//will be set to true if uno is called
				int cardPlayed = 0;
				int choice = 0;
				Boolean drawCard = false; //used to denote if the player has drawn a card or not
				do {
					uno = checkUno(play1);
					//printHand(play1,drawCard,uno,unoCalled);mam error
				}
			}
		}
		
	}
	
	public static Card wildColor(int cardNumber) {
		System.out.print("Jakiego koloru ma by talia?");
		String input = scan.nextLine();
		char color = 'a';
		switch(input.charAt(0)) {
		case 'b':
			color = 'b';
			break;
		case 'r':
			color = 'r';
			break;
		case 'g':
			color = 'g';
			break;
		case 'y':
            color = 'y';
            break;
        default:
            System.out.println("Type: (b)lue, (g)reen, (r)ed or (y)ellow");
            wildColor(cardNumber);
            break;
		}
		return new Card(cardNumber,color);
	}
	
	public static void printHand(Deal play, boolean drawCard, boolean uno, boolean unoCalled) {
		int display = 0;
		for(int i = 0; i < play.getSize(); i++) {
			display = i + 1;
			System.out.println(display + ". " + play.getCard(i));
		}
		display++;
		if(!drawCard) {
			System.out.println(display + ". Draw Card");
		}else if (drawCard) {
            System.out.println(display + ". End Turn");
        }
        display++;
        if (uno && !unoCalled) {
            System.out.println(display + ". Uno");
        } else if (uno && unoCalled) {
            System.out.println("Uno Called");
        }
	}
	
	public static boolean checkUno(Deal play) {
        boolean uno = false;
        if (play.getSize() == 2) {
            uno = true;
        } else {
            uno = false;
        }
        return uno;
    }

}
