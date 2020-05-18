package Uno;

import java.util.ArrayList;
import java.util.Collections;

public class CardHandler {
	private ArrayList<Card> cards;
	
	public CardHandler() {
		cards = new ArrayList<Card>();
		cards.add(new Card(0,'r'));
        cards.add(new Card(1, 'r'));
        cards.add(new Card(1, 'r'));
        cards.add(new Card(2, 'r'));
        cards.add(new Card(2, 'r'));
        cards.add(new Card(3, 'r'));
        cards.add(new Card(3, 'r'));
        cards.add(new Card(4, 'r'));
        cards.add(new Card(4, 'r'));
        cards.add(new Card(5, 'r'));
        cards.add(new Card(5, 'r'));
        cards.add(new Card(6, 'r'));
        cards.add(new Card(6, 'r'));
        cards.add(new Card(7, 'r'));
        cards.add(new Card(7, 'r'));
        cards.add(new Card(8, 'r'));
        cards.add(new Card(8, 'r'));
        cards.add(new Card(9, 'r'));
        cards.add(new Card(9, 'r'));
        cards.add(new Card(10, 'r'));//skip
        cards.add(new Card(10, 'r'));//skip
        cards.add(new Card(11, 'r'));//draw two
        cards.add(new Card(11, 'r'));//draw two
        cards.add(new Card(12, 'r'));//reverse
        cards.add(new Card(12, 'r'));//reverse
        cards.add(new Card(13, 'a'));//wild
        cards.add(new Card(14, 'a'));//wild +4
        cards.add(new Card(0,'b'));
        cards.add(new Card(1, 'b'));
        cards.add(new Card(1, 'b'));
        cards.add(new Card(2, 'b'));
        cards.add(new Card(2, 'b'));
        cards.add(new Card(3, 'b'));
        cards.add(new Card(3, 'b'));
        cards.add(new Card(4, 'b'));
        cards.add(new Card(4, 'b'));
        cards.add(new Card(5, 'b'));
        cards.add(new Card(5, 'b'));
        cards.add(new Card(6, 'b'));
        cards.add(new Card(6, 'b'));
        cards.add(new Card(7, 'b'));
        cards.add(new Card(7, 'b'));
        cards.add(new Card(8, 'b'));
        cards.add(new Card(8, 'b'));
        cards.add(new Card(9, 'b'));
        cards.add(new Card(9, 'b'));
        cards.add(new Card(10, 'b'));//skip
        cards.add(new Card(10, 'b'));//skip
        cards.add(new Card(11, 'b'));//draw two
        cards.add(new Card(11, 'b'));//draw two
        cards.add(new Card(12, 'b'));//reverse
        cards.add(new Card(12, 'b'));//reverse
        cards.add(new Card(13, 'a'));//wild
        cards.add(new Card(14, 'a'));//wild +4
        cards.add(new Card(0,'g'));
        cards.add(new Card(1, 'g'));
        cards.add(new Card(1, 'g'));
        cards.add(new Card(2, 'g'));
        cards.add(new Card(2, 'g'));
        cards.add(new Card(3, 'g'));
        cards.add(new Card(3, 'g'));
        cards.add(new Card(4, 'g'));
        cards.add(new Card(4, 'g'));
        cards.add(new Card(5, 'g'));
        cards.add(new Card(5, 'g'));
        cards.add(new Card(6, 'g'));
        cards.add(new Card(6, 'g'));
        cards.add(new Card(7, 'g'));
        cards.add(new Card(7, 'g'));
        cards.add(new Card(8, 'g'));
        cards.add(new Card(8, 'g'));
        cards.add(new Card(9, 'g'));
        cards.add(new Card(9, 'g'));
        cards.add(new Card(10, 'g'));//skip
        cards.add(new Card(10, 'g'));//skip
        cards.add(new Card(11, 'g'));//draw two
        cards.add(new Card(11, 'g'));//draw two
        cards.add(new Card(12, 'g'));//reverse
        cards.add(new Card(12, 'g'));//reverse
        cards.add(new Card(13, 'a'));//wild
        cards.add(new Card(14, 'a'));//wild +4
        cards.add(new Card(0,'y'));
        cards.add(new Card(1, 'y'));
        cards.add(new Card(1, 'y'));
        cards.add(new Card(2, 'y'));
        cards.add(new Card(2, 'y'));
        cards.add(new Card(3, 'y'));
        cards.add(new Card(3, 'y'));
        cards.add(new Card(4, 'y'));
        cards.add(new Card(4, 'y'));
        cards.add(new Card(5, 'y'));
        cards.add(new Card(5, 'y'));
        cards.add(new Card(6, 'y'));
        cards.add(new Card(6, 'y'));
        cards.add(new Card(7, 'y'));
        cards.add(new Card(7, 'y'));
        cards.add(new Card(8, 'y'));
        cards.add(new Card(8, 'y'));
        cards.add(new Card(9, 'y'));
        cards.add(new Card(9, 'y'));
        cards.add(new Card(10, 'y'));//skip
        cards.add(new Card(10, 'y'));//skip
        cards.add(new Card(11, 'y'));//draw two
        cards.add(new Card(11, 'y'));//draw two
        cards.add(new Card(12, 'y'));//reverse
        cards.add(new Card(12, 'y'));//reverse
        cards.add(new Card(13, 'a'));//wild
        cards.add(new Card(14, 'a'));//wild +4
	}
	
	public void shuffleDeck() { //tasuj talie
		Collections.shuffle(cards);
		Collections.shuffle(cards);
		Collections.shuffle(cards);
	}
	
	public Card getLast() { // otrzymac ostatnia karte
		
		if(cards.size()==0) return null;
		return cards.get(cards.size() - 1);
	}
	
	public void addCard(Card addedCard) {
		cards.add(addedCard);
	}
	
	public void printDeck() { //talia
		for(int i = 0; i < cards.size(); i = i+1) {
			Card sd = new Card(cards.get(i).numRet(),cards.get(i).colorRet());
			System.out.println(sd.toString());
		}
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public void removeLast() {
		if(cards.size()!=0)
			cards.remove(cards.size() - 1);
	}
}
