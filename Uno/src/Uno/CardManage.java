package Uno;
import java.util.Random;
public class CardManage{

	private static Card card[] = new Card[108];
	private static Card placed_cards[] = new Card[10];
	private Random rand = new Random();
	CardManage()
	{
		//tworzenie tablicy card
		
		//4 kolory
		//po 1 zero
		//po 2 1,2,3,4,5,6,7,8,9
		//po 2 +2,skip,reverse
		//4 wild i 4 +4
		int k=0;
		for(int i=0;i<4;i++)//zera
		{
			card[i] = new Card(k,(char)(i+85));
		}
		k++;
		for(int i=1;i<25;i+=2)//wszytskei po 2
		{
			for(int j=0;j<8;j+=2)
			{
				card[(4*i)+j] = new Card(k,(char)(((j%8)/2)+85));
				card[((4*i)+j)+1] = new Card(k,(char)(((j%8)/2)+85));
			}
			k++;
		}//wild
		for(int i=100;i<108;i+=2)
		{
			card[i]=new Card(k,(char)90);
			card[i+1]=new Card(k+1,(char)90);
		}
		
		fill_placed_notuse();
	}
	
	///////////PRYWATNE KLASY
	
	private void fill_placed_notuse()
	{
		for(int i=0;i<10;i++)
			placed_cards[i]=new Card(-1,'0');
	}
	
	private int findLastAv()
	{
		//ostatnia karta liczona od konca która nie jest numer -1
		for(int i=card.length-1;i>=0;i--)
		{
			if(card[i].numRet()!=-1)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int cardCount(int type)
	{
		//zwraca ilosc card za pomoca numeru
		int retVal=0;
		for(int i=0;i<card.length;i++)
		{
			if(card[i].numRet()==type)
				retVal++;
		}
		return retVal;
	}
	
	private int cardCount(char color)
	{
		//zwraca ilosc card za pomoca coloru
		int retVal=0;
		for(int i=0;i<card.length;i++)
		{
			if(card[i].colorRet()==color)
				retVal++;
		}
		return retVal;
	}
	
	private int findFirstNotCard()
	{
		//pierwsza nieuzywana karta
		for(int i=0;i<10;i++)
		{
			if(placed_cards[i].numRet()==-1)
			{
				return i;
			}
		}
		return -1;
	}
	
	private void addToPile() throws CloneNotSupportedException
	{
		//daje z kart ze tmp na karty dla graczy
		int last_card = findLastAv() + 1;
		int maxx=findFirstNotCard()+1;
				for(int i=0;i<maxx;i++)
				{
					card[last_card+i] = (Card) placed_cards[i].clone();
				}

				fill_placed_notuse();
	} 
	
	////////////////////END
	
	
	////////////////KLASY DO TESTOW////////////
	
	public void validCardAmount()
	{
		int i=findLastAv()+1;
		System.out.println("Ilosc kart:"+i);
	}
	
	public void printCards()
	{
		//komunikat o karcie
		for(Card l : card)
		{
			System.out.println("Numer:"+l.numRet()+"  Color:"+l.colorRet()); 
		}
	}
	
	public void cardData()
	{
		//do testowania czy niczego nie zabraknie itp
		System.out.println("Dane:");
		System.out.println("Ilosc czerwonych card:"+cardCount('U'));
		System.out.println("Ilosc niebieskich card:"+cardCount('V'));
		System.out.println("Ilosc zoltych card:"+cardCount('W'));
		System.out.println("Ilosc zielonych card:"+cardCount('X'));
		System.out.println("Ilosc czarnych card:"+cardCount('Z'));
		System.out.println("Ilosc 0 card:"+cardCount(0));
		System.out.println("Ilosc 1 card:"+cardCount(1));
		System.out.println("Ilosc 2 card:"+cardCount(2));
		System.out.println("Ilosc 3 card:"+cardCount(3));
		System.out.println("Ilosc 4 card:"+cardCount(4));
		System.out.println("Ilosc 5 card:"+cardCount(5));
		System.out.println("Ilosc 6 card:"+cardCount(6));
		System.out.println("Ilosc 7 card:"+cardCount(7));
		System.out.println("Ilosc 8 card:"+cardCount(8));
		System.out.println("Ilosc 9 card:"+cardCount(9));
		System.out.println("Ilosc +2 card:"+cardCount(10));
		System.out.println("Ilosc skip card:"+cardCount(11));
		System.out.println("Ilosc reverse card:"+cardCount(12));
		System.out.println("Ilosc wild card:"+cardCount(13));
		System.out.println("Ilosc +4 card:"+cardCount(14));
		
	}
	
	//////////////////////END
	
	
	
	public void cRand()
	{
		//losowanie tablicy card
		int fm=findLastAv();
		
		for(int i=0;i<fm;i++)
		{
			int p = rand.nextInt(fm);
			Card tmp = card[i];
			card[i]=card[p];
			card[p]=tmp;
		}
	}
	
	
	public Card giveCard() throws CloneNotSupportedException
	{
		//wysyla karty graczowi i zdejmuje je z tablicy
		int i=findLastAv();
		if(i!=-1)
		{
			Card tmp = (Card) card[i].clone();
			card[i] = new Card(-1,'0');
			return tmp;
		}
			
		else
			return null;
	}
	
	public void getBackCard(Card backcard) throws CloneNotSupportedException
	{
		//zwraca karte ktora jest na srodku i przelosowuje carty jezeli napelni tymczasowy buffer
		int pos = 0;
		pos=findFirstNotCard();
		placed_cards[pos] = (Card) backcard.clone();
		
			if(pos==9)
			{
				addToPile();
				cRand();
			}
				
	}
}
