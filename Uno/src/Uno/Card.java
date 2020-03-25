package Uno;

public class Card {
	private int cardNumber;
    private char cardColor;
    
    public Card(int cNumber, char cColor){
        cardNumber = cNumber;
        cardColor = cColor;
    }
    
    public int numRet()
    {
    	return cardNumber;
    }
    
    public char colorRet()
    {
    	return cardColor; 
    }
}
