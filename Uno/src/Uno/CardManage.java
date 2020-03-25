package Uno;

public class CardManage {

	private static Card card[] = new Card[108];
	
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
				card[(4*i)+j] = new Card(k,(char)(j%4+85));
				card[((4*i)+j)+1] = new Card(k,(char)(j%4+85));
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
		
	}
}
