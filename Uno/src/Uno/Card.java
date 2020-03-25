package Uno;

public class Card {
    private int cardNumber;
    private char cardColor;
    
    public Card(int cNumber, char cColor){
        cardNumber = cNumber;
        cardColor = cColor;
    }
    public int numRet(){
        return cardNumber;
    }
    public char colorRet(){
        return cardColor;
    }
    public static char colorRet(Card cards){
        return cards.colorRet();
    }
    public static int numRet(Card cards){
        return cards.numRet();
    }

    public String toString(){
        String cColor = null;
        String cNumber = null;
        if(cardColor == 'r'){
            cColor = "Red";
        }else if(cardColor == 'b'){
            cColor = "Blue";
        }else if(cardColor == 'y'){
            cColor = "Yellow";
        }else if(cardColor == 'g'){
            cColor = "Green";
        }else if(cardColor =='a'){
            cColor = "Any";
        }
        if(cardNumber <= 9){
            cNumber = Integer.toString(cardNumber);
        }else if(cardNumber == 10){
            cNumber = "Skip";
        }else if(cardNumber == 11){
            cNumber = "Draw Two";
        }else if(cardNumber == 12){
            cNumber = "Reverse";
        }else if(cardNumber == 13){
            cNumber = "Wild";
        }else if(cardNumber == 14){
            cNumber = "Wild Draw Four";
        }
        return (cColor + " - " + cNumber);
    }
}