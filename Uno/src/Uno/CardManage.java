package Uno;
import java.util.Random;
public class CardManage {

	private static Card card[] = new Card[108];
	private Random rand = new Random();
	CardManage()
	{
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

	}
	
	public void printCards()
	{
		for(Card l : card)
		{
			System.out.println("Numer:"+l.numRet()+"  Color:"+l.colorRet()); 
		}
	}
	
	public void cRand()
	{
		int fm=findLastAv();
		
		for(int i=0;i<fm;i++)
		{
			int p = rand.nextInt(fm);
			Card tmp = card[i];
			card[i]=card[p];
			card[p]=tmp;
		}
	}
	
	private int findLastAv()
	{
		for(int i=card.length-1;i>=0;i--)
		{
			if(card[i].numRet()!=-1)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int cardCount(char color)
	{
		int retVal=0;
		for(int i=0;i<card.length;i++)
		{
			if(card[i].colorRet()==color)
				retVal++;
		}
		return retVal;
	}
	
	private int cardCount(int type)
	{
		int retVal=0;
		for(int i=0;i<card.length;i++)
		{
			if(card[i].numRet()==type)
				retVal++;
		}
		return retVal;
	}
	
	public void cardData()
	{
		//do testowania czy niczego nie zabraknie itp
		System.out.println("Dane:");
		System.out.println("Ilosc czerwonych card:"+cardCount('U'));
		System.out.println("Ilosc niebieskich card:"+cardCount('V'));
		System.out.println("Ilosc zoltych card:"+cardCount('W'));
		System.out.println("Ilosc zielonych card:"+cardCount('X'));
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
}
