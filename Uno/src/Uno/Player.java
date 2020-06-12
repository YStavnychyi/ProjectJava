package Uno;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Player {


	static Random ran = new Random();
	static Deal play1 = new Deal();
	static Deal play2 = new Deal();
	static Deal play3 = new Deal();
	static Deal play4 = new Deal();
	static CardHandler deck = new CardHandler();
	static Scanner scan = new Scanner(System.in);
	static Deal discardPile = new Deal();
	static int numEnemy = 0;
	static boolean reverse = false;
	static boolean skip = false;
	static boolean gameEnded = false;
	static boolean draw2 = false;
	static boolean draw4 = false;
	
	
	public Player() throws InterruptedException
	{
		deck.shuffleDeck();
		boolean playerNumGot = false;
		do {
			try {
				System.out.print("Ilu graczy chcesz zagrac?(1-3) ");
				numEnemy = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
				scan.nextLine();
			}
			if (numEnemy <= 3 && numEnemy >= 1) {
				playerNumGot = true;
			} else {
				System.out.println("Wprowadz liczbe pomiedzy 1 i 3");
				playerNumGot = false;
			}
		} while (!playerNumGot);
		if (numEnemy == 1) {
			for (int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
			}
		} else if (numEnemy == 2) {
			for (int i = 0; i <= 6; i++) {
				play1.addCard(deck);
				play2.addCard(deck);
				play3.addCard(deck);
			}
		} else if (numEnemy == 3) {
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
				//sleep(1000);
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
                if(numEnemy == 1) {
                	if (reverse && skip || !reverse && skip) {
                        currentPlayer = 1;
                    } else if (reverse && !skip || !reverse && !skip) {
                        currentPlayer = 2;
                        if (draw2) {
                            draw2(play2);
                        }
                        if (draw4) {
                            draw4(play2);
                        }
                    }
                }else if(numEnemy == 2) {
                	if (reverse && skip) {
                        currentPlayer = 2;
                    } else if (!reverse && skip) {
                        currentPlayer = 3;
                    } else if (reverse && !skip) {
                        currentPlayer = 3;
                        if (draw2) {
                            draw2(play3);
                        }
                        if (draw4) {
                            draw4(play3);
                        }
                    }else if (!reverse && !skip) {
	                    currentPlayer = 2;
	                    if (draw2) {
	                        draw2(play2);
	                    }
	                    if (draw4) {
	                        draw4(play2);
	                    }
                    }
                }else if(numEnemy == 3) {
					if (reverse && skip || !reverse && skip) {
	                    currentPlayer = 3;
	                } else if (!reverse && !skip) {
	                    currentPlayer = 2;
	                    if (draw2) {
	                        draw2(play2);
	                    }
	                    if (draw4) {
	                        draw4(play2);
	                    }
	                } else if (reverse && !skip) {
	                    currentPlayer = 4;
	                    if (draw2) {
	                        draw2(play4);
	                    }
	                    if (draw4) {
	                        draw4(play4);
	                    }
	                } else {
	                    System.out.println("ERROR");
	                }
                }
	            checkDraw(deck, discardPile);
	            System.out.println();
	            break;
			}
			while(currentPlayer == 2) {
				int enemyNumber = 1;
				enemyGame(play2, skip, draw2, draw4, uno, reverse, gameEnded, enemyNumber);
				if (numEnemy == 1) { 
                    if (reverse && skip || !reverse && skip) {
                        currentPlayer = 2;
                    } else if (reverse && !skip || !reverse && !skip) {
                        currentPlayer = 1;
                        if (draw2) {
                            draw2(play1);
                        }
                        if (draw4) {
                            draw4(play1);
                        }
                    }
                }else if(numEnemy == 2) {
                	if (reverse && skip) {
                        currentPlayer = 3;
                    } else if (!reverse && skip) {
                        currentPlayer = 1;
                    } else if (reverse && !skip) {
                        currentPlayer = 1;
                        if (draw2) {
                            draw2(play1);
                        }
                        if (draw4) {
                            draw4(play1);
                        }
                    }else if (!reverse && !skip) {
                        currentPlayer = 3;
                        if (draw2) {
                            draw2(play3);
                        }
                        if (draw4) {
                            draw4(play3);
                        }
                    }
                }else if(numEnemy == 3) {
                	if (reverse && skip || !reverse && skip) {
                        currentPlayer = 4;
                    } else if (!reverse && !skip) {
                        currentPlayer = 3;
                        if (draw2) {
                            draw2(play3);
                        }
                        if (draw4) {
                            draw4(play3);
                        }
                    } else if (reverse && !skip) {
                        currentPlayer = 1;
                        if (draw2) {
                            draw2(play1);
                        }
                        if (draw4) {
                            draw4(play1);
                        }
                    }
                }
				checkDraw(deck, discardPile);
				break;
			}
			while (currentPlayer == 3) {
                int enemyNumber = 2;
                enemyGame(play3, skip, draw2, draw4, uno, reverse, gameEnded, enemyNumber);
                if (numEnemy == 2) { 
                    if (reverse && skip) {
                        currentPlayer = 3;
                    } else if (!reverse && skip) {
                        currentPlayer = 1;
                    } else if (reverse && !skip) {
                        currentPlayer = 2;
                        if (draw2) {
                            draw2(play2);
                        }
                        if (draw4) {
                            draw4(play2);
                        }
                    } else if (!reverse && !skip) {
                        currentPlayer = 1;
                        if (draw2) {
                            draw2(play4);
                        }
                        if (draw4) {
                            draw4(play4);
                        }
                    }
                } else if (numEnemy == 3) { //player 3
                    if (reverse && skip || !reverse && skip) {
                        currentPlayer = 1;
                    } else if (!reverse && !skip) {
                        currentPlayer = 4;
                        if (draw2) {
                            draw2(play4);
                        }
                        if (draw4) {
                            draw4(play4);
                        }
                    } else if (reverse && !skip) {
                        currentPlayer = 2;
                        if (draw2) {
                            draw2(play2);
                        }
                        if (draw4) {
                            draw4(play2);
                        }
                    }
                }
                checkDraw(deck, discardPile);
                break;
            }
			while (currentPlayer == 4) {
                int enemyNumber = 3;
                enemyGame(play4, skip, draw2, draw4, uno, reverse, gameEnded, enemyNumber);
                if (reverse && skip || !reverse && skip) {
                    currentPlayer = 2;
                } else if (!reverse && !skip) {
                    currentPlayer = 1;
                    if (draw2) {
                        draw2(play2);
                    }
                    if (draw4) {
                        draw4(play2);
                    }
                } else if (reverse && !skip) {
                    currentPlayer = 3;
                    if (draw2) {
                        draw2(play4);
                    }
                    if (draw4) {
                        draw4(play4);
                    }
                }
                checkDraw(deck, discardPile);
                break;
            }
	} while (!gameEnded);
	}
	
	public static void enemyGame(Deal enemy, boolean skip, boolean draw2, boolean draw4, boolean uno, boolean reverse,
			boolean gameEnded, int enemyNumber) throws InterruptedException {
		skip = false;
		int special;
		draw2 = false;
		draw4 = false;
		int choice = 0;
		int cardPlayed = 0;
		boolean drawCard = false;
		boolean unoCalled = false;
		do {
			// sleep(2000);
			uno = checkUno(enemy);
			Card dCard = discardPile.getLast();
			choice = getEnemyChoice(dCard, enemy, unoCalled);
			int elem = choice - 1;
			if (choice == (enemy.getSize() + 1)) {
				if (!drawCard) {
					System.out.println("Player " + enemyNumber + " has drawn a card");
					enemy.addCard(deck);
					drawCard = true;
				} else if (drawCard) {
					System.out.println("Player " + enemyNumber + " has ended their turn without playing a card");
					cardPlayed = 1;
				}
			} else if (choice == (enemy.getSize() + 2)) {
				if (!unoCalled) {
					System.out.println("Player " + enemyNumber + " Calls Uno");
					unoCalled = true;
				}
			} else if (Card.numRet(enemy.getCard(elem)) == 13) {
				discardPile.addCard(wildEnemyColor(13, enemy));
				System.out.println("Player " + enemyNumber + " played " + discardPile.getLast());
				enemy.removeCard(elem);// remove the card from the player deck. it's no longer needed there
				cardPlayed = 1;
			} else if (Card.numRet(enemy.getCard(elem)) == 14) {
				discardPile.addCard(wildEnemyColor(14, enemy));
				System.out.println("Player " + enemyNumber + " played " + discardPile.getLast());
				draw4 = true;
				enemy.removeCard(choice - 1);
				cardPlayed = 1;
			} else if (Card.colorRet(enemy.getCard(elem)) == Card.colorRet(discardPile.getLast())
					|| Card.numRet(enemy.getCard(choice - 1)) == Card.numRet(discardPile.getLast())) {
				discardPile.addCard(enemy.getCard(elem));
				switch (Card.numRet(enemy.getCard(elem))) {
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
					} else {

					}
					break;
				default:
					break;
				}
				System.out.println("Player " + enemyNumber + " played " + discardPile.getLast());
				enemy.removeCard(choice - 1);
				cardPlayed = 1;
			}
		} while (cardPlayed == 0);
		if (enemy.getSize() == 1 && !unoCalled) {
			System.out.println("Player " + enemyNumber + " did not call uno. +2");
			enemy.addCard(deck);
			enemy.addCard(deck);
		}
		if (enemy.getSize() == 0) {
			System.out.println();
			System.out.println("Player " + enemyNumber + " won");
			gameEnded = true;
			System.exit(0);
		}
		if (reverse && skip) {
			special = 1;
		} else if (!reverse && skip) {
			special = 2;
		} else if (reverse && !skip) {
			if (draw2) {
				special = 4;
			} else if (draw4) {
				special = 5;
			} else {
				special = 3;
			}
		} else if (!reverse && !skip) {
			if (draw2) {
				special = 7;
			} else if (draw4) {
				special = 8;
			} else {
				special = 6;
			}
		}
	}

	public static int getEnemyChoice(Card dCard, Deal enemy, boolean unoCalled) {
		int choice = 0;
		boolean hWild = false;
		boolean hSkip = false;
		boolean hReverse = false;
		boolean hDTwo = false;
		boolean hDFour = false;
		boolean hPlayable = false;
		hWild = hasWild(dCard, enemy);
		hSkip = hasSkip(dCard, enemy);
		hReverse = hasReverse(dCard, enemy);
		hDTwo = hasDrawTwo(dCard, enemy);
		hDFour = hasDrawFour(dCard, enemy);
		hPlayable = hasPlayable(dCard, enemy);
		if (enemy.getSize() == 2 && !unoCalled) {
			choice = enemy.getSize() + 2;
		} else if (hDTwo) {
			choice = findDTwo(dCard, enemy);
			choice++;
		} else if (hSkip) {
			choice = findSkip(enemy, dCard);
			choice++;
		} else if (hReverse) {
			choice = findReverse(dCard, enemy);
			choice++;
		} else if (hPlayable) {
			choice = findPlayable(dCard, enemy);
			choice++;
		} else if (hWild && !hDTwo && !hSkip && !hReverse && !hPlayable) {
			choice = findWild(enemy);
			choice++;
		} else if (hDFour && !hWild && !hDTwo && !hSkip && !hReverse && !hPlayable) {
			choice = findDrawFour(enemy);
			choice++;
		} else {
			choice = enemy.getSize() + 1;
		}
		return choice;
	}

	public static int findWild(Deal enemy) {
		int elem = 0;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 13) {
				elem = i;
			}
		}
		return elem;
	}

	public static int findDTwo(Card dCard, Deal enemy) {
		int elem = 0;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 11 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				elem = i;
			}
		}
		return elem;
	}

	public static int findSkip(Deal enemy, Card dCard) {
		int elem = 0;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 10 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				elem = i;
			}
		}
		return elem;
	}

	public static int findReverse(Card dCard, Deal enemy) {
		int elem = 0;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 12 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				elem = i;
			}
		}
		return elem;
	}

	public static int findDrawFour(Deal enemy) {
		int elem = 0;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 14) {
				elem = i;
			}
		}
		return elem;
	}

	public static int findPlayable(Card dCard, Deal enemy) {
		int elem = 0;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) < 10 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				elem = i;
			}
		}
		return elem;
	}

	public static boolean hasWild(Card dCard, Deal enemy) {
		boolean hWild = false;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 13) {
				hWild = true;
			}
		}
		return hWild;
	}

	public static boolean hasSkip(Card dCard, Deal enemy) {
		boolean hSkip = false;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 10 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				hSkip = true;
			}
		}
		return hSkip;
	}

	public static boolean hasReverse(Card dCard, Deal enemy) {
		boolean hReverse = false;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 12 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				hReverse = true;
			}
		}
		return hReverse;
	}

	public static boolean hasDrawTwo(Card dCard, Deal enemy) {
		boolean hDTwo = false;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 11 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				hDTwo = true;
			}
		}
		return hDTwo;
	}

	public static boolean hasDrawFour(Card dCard, Deal enemy) {
		boolean hDrawFour = false;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) == 14) {
				hDrawFour = true;
			}
		}
		return hDrawFour;
	}

	public static boolean hasPlayable(Card dCard, Deal enemy) {
		boolean hPlayable = false;
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.numRet(enemy.getCard(i)) < 10 && Card.colorRet(enemy.getCard(i)) == Card.colorRet(dCard)) {
				hPlayable = true;
			}
		}
		return hPlayable;
	}

	public static Card wildEnemyColor(int cardNumber, Deal enemy) {
		int blue = 0;
		int red = 0;
		int yellow = 0;
		int green = 0;
		char cColor = 'a';
		for (int i = 0; i < enemy.getSize(); i++) {
			if (Card.colorRet(enemy.getCard(i)) == 'b') {
				blue++;
			} else if (Card.colorRet(enemy.getCard(i)) == 'r') {
				red++;
			} else if (Card.colorRet(enemy.getCard(i)) == 'y') {
				yellow++;
			} else if (Card.colorRet(enemy.getCard(i)) == 'g') {
				green++;
			}
		}
		if (blue > green && blue > yellow && blue > red) {
			cColor = 'b';
		} else if (green > blue && green > yellow && green > red) {
			cColor = 'g';
		} else if (yellow > green && yellow > blue && yellow > red) {
			cColor = 'y';
		} else if (red > green && red > yellow && red > blue) {
			cColor = 'r';
		} else {
			cColor = 'b';
		}

		return new Card(cardNumber, cColor);
	}

	public static void checkDraw(CardHandler deck, Deal discardPile) {
		if (deck.getSize() <= 4) {
			System.out.println();
			System.out.println("Shuffling Deck");
			System.out.println();
			for (int i = 0; i < discardPile.getSize(); i++) {
				if (Card.numRet(discardPile.getLast()) == 13 && Card.colorRet(discardPile.getLast()) != 'a') {
					discardPile.removeCard(discardPile.getSize() - 1);
					discardPile.addCard(new Card(13, 'a'));
				} else if (Card.numRet(discardPile.getLast()) == 14 && Card.colorRet(discardPile.getLast()) != 'a') {
					discardPile.removeCard(discardPile.getSize() - 1);
					discardPile.addCard(new Card(14, 'a'));
				} else {
					deck.addCard(discardPile.getLast());
					discardPile.removeCard(discardPile.getSize() - 1);
				}
			}
			deck.shuffleDeck();
		} else {
		}
	}

	public static void draw2(Deal play) {
		for (int i = 0; i <= 1; i++) {
			play.addCard(deck);
		}
	}

	public static void draw4(Deal play) {
		for (int i = 0; i <= 3; i++) {
			play.addCard(deck);
		}
	}

	public static int getCardNumber() {// metoda dla sprawdzenia danych wejsciowych
		int choice = 0;
		do {
			try {
				System.out.print("Ktora karte chcesz zagrac?: ");
				choice = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Error");
				scan.hasNextLine();
			}
		} while (choice == 0);
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
