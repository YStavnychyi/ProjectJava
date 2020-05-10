package Uno;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import Uno.game.GameManagement;

public class UnoApp {

	static Random ran = new Random();
	static Deal play1 = new Deal();
	static Deal play2 = new Deal();
	static Deal play3 = new Deal();
	static Deal play4 = new Deal();
	static CardHandler deck = new CardHandler();
	static Scanner scan = new Scanner(System.in);
	static Deal discardPile = new Deal();
	static int numPlayer = 0;
	static boolean reverse = false;
	static boolean skip = false;
	static boolean gameEnded = false;
	static boolean draw2 = false;
	static boolean draw4 = false;

	public static void main(String[] args) {

		GameManagement game = new GameManagement();
		game.startThread();
		deck.shuffleDeck();
		boolean playerNumGot = false;
		do {
			try {
				System.out.print("Ilu graczy chcesz zagrac?(1-3) ");
				numPlayer = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
				scan.nextLine();
			}
			if (numPlayer <= 3 && numPlayer >= 1) {
				playerNumGot = true;
			} else {
				System.out.println("Wprowadz liczbe pomiedzy 1 i 3");
				playerNumGot = false;
			}
		} while (!playerNumGot);
		if (numPlayer == 1) {
			for (int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
			}
		} else if (numPlayer == 2) {
			for (int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
				play3.addCard(deck);
			}
		} else if (numPlayer == 3) {
			for (int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
				play3.addCard(deck);
				play4.addCard(deck);
			}
		}

		discardPile.addCard(deck);// adds the top of the deck to the discard pile "face up"
		if (Card.numRet(discardPile.getLast()) == 14) {
			discardPile.addCard(wildColor(14));
		} else if (Card.numRet(discardPile.getLast()) == 13) {
			discardPile.addCard(wildColor(13));
		}

		int currentPlayer = 1;
		boolean uno = false;

		do {// until the game has ended, do the following
			while (currentPlayer == 1) {
				skip = false;
				draw2 = false;
				draw4 = false;
				// sleep(1000);
				boolean unoCalled = false;// will be set to true if uno is called
				int cardPlayed = 0;
				int choice = 0;
				Boolean drawCard = false; // used to denote if the player has drawn a card or not
				do {
					uno = checkUno(play1);
					printHand(play1, drawCard, uno, unoCalled);
					printDiscard();
					choice = getCardNumber();
					while(choice > play1.getSize()+2 || choice < 0) {
						System.out.println("Erorr");
						choice = getCardNumber();
					}
					int elem = choice - 1;
					if(choice == (play1.getSize() + 1)) {
						if(!drawCard) {
							play1.addCard(deck);
							drawCard = true;
						}else if (drawCard) {
							cardPlayed = 1;
						}
					}else if (choice == (play1.getSize() + 2)) {
						if(!unoCalled) {
							System.out.println("UNO!!");
							unoCalled = true;
						}else if (unoCalled || !uno) {
							System.out.println("Wybierz prawidlowa karte!");
						}
					}else if(Card.colorRet(play1.getCard(elem)) == 'a' && Card.numRet(play1.getCard(elem)) == 13) {
						discardPile.addCard(wildColor(13));
						play1.removeCard(elem);
						cardPlayed = 1;
					}else if (Card.colorRet(play1.getCard(elem)) == 'a' && Card.numRet(play1.getCard(elem)) == 14) {
						discardPile.addCard(wildColor(14));
						draw4 = true;
						play1.removeCard(elem);
						cardPlayed = 1;
					}else if (Card.colorRet(play1.getCard(elem)) == Card.colorRet(discardPile.getLast()) || Card.numRet(play1.getCard(elem)) == Card.numRet(discardPile.getLast())) {
						discardPile.addCard(play1.getCard(elem));
						switch (Card.numRet(play1.getCard((elem)))) {
                        case 10:
                            skip = true;
                            break;
                        case 11:
                            draw2 = true;
                            break;
                        case 12:
                            if (reverse) {
                                reverse = false;
                            } else if (!reverse) {
                                reverse = true;
                            }
                            break;
                        default:
                            break;
                    }
                    play1.removeCard(elem);
                    cardPlayed = 1;
					}else {
						System.out.println("Wybierz prawidlowa karte!");
					}
				} while (cardPlayed == 0);
				if (play1.getSize() == 1 && !unoCalled) {
                    System.out.println("Gracz 1 nie wywolal uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
				if (play1.getSize() > 1 && unoCalled) {
                    System.out.println("Gracz 1 falszywie nazwal uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
                if (play1.getSize() == 0) {
                    System.out.println();
                    System.out.println("Gracz 1 wygral");
                    gameEnded = true;
                    System.exit(0);
                }
			}
		} while (!gameEnded);

	}
	
	public static int getCardNumber() {// metoda dla sprawdzenia danych wejsciowych
		int choice = 0;
		do {
			try {
				System.out.print("Ktora karte chcesz zagrac?: ");
				choice = scan.nextInt();
				scan.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Error");
				scan.hasNextLine();
			}
		}while (choice == 0);
		return choice;
	}

	public static void printDiscard() {
		System.out.print("Discard Pile: ");
		System.out.println(discardPile.getLast());
	}

	public static Card wildColor(int cardNumber) {
		System.out.print("Jakiego koloru ma by talia?");
		String input = scan.nextLine();
		char color = 'a';
		switch (input.charAt(0)) {
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
		return new Card(cardNumber, color);
	}

	public static void printHand(Deal play, boolean drawCard, boolean uno, boolean unoCalled) {
		int display = 0;
		for (int i = 0; i < play.getSize(); i++) {
			display = i + 1;
			System.out.println(display + ". " + play.getCard(i));
		}
		display++;
		if (!drawCard) {
			System.out.println(display + ". Draw Card");
		} else if (drawCard) {
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
