package Uno.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Uno.Card;
import Uno.game.handler.GameHandlerer;

public class CardLoader {

	public static BufferedImage[] laodRotatedCards(BufferedImage tmpImg)
	{
		BufferedImage[] cRotated = new BufferedImage[3];
		for(int i=0;i<3;i++)
			cRotated[i]=RotateNCards(tmpImg,i+1);
		return cRotated;
	}
	
	public static BufferedImage[] laodCards(GameHandlerer gameH)
	{
		BufferedImage[] cards = new BufferedImage[55];
		int w,h;
		w=gameH.getGameM().getSheetHolder().getCardSheet().getWidth()/8;
		h=gameH.getGameM().getSheetHolder().getCardSheet().getHeight()/2;
		
		BufferedImage tmpImg[] = new BufferedImage[16];
		
		int y=0;
		
		for(int i=0;i<16;i++)
		{
			if(i==8)
			{
				y=h;
			}
			tmpImg[i] = gameH.getCardImage((i%8)*w, y, w, h);
		}
		
		for(int i=0;i<13;i++)
		{
			for(int j=0;j<4;j++)
			{
				char a = 'a';
				if(j==0) a='r';
				else if(j==1) a='b';
				else if(j==2) a='y';
				else if(j==3) a='g';
				cards[(i*4)+j] = paintOver(new Card(i,a),tmpImg[i],tmpImg[i].getRGB(140, 15));
			}
				
		}
		
		int j=13;
		for(int i=52;i<55;i++)
		{
			cards[i] = tmpImg[j];
			j++;
		}
		
		return cards;
	}
	
	private static BufferedImage paintOver(Card card,BufferedImage cardIma,int colc)
	{
		Color color = new Color(0, 0, 0);;
		int getc;
		
		BufferedImage cardImage = new BufferedImage(cardIma.getWidth(),cardIma.getHeight(),cardIma.getType());
		Graphics g = cardImage.getGraphics();
	    g.drawImage(cardIma, 0, 0, null);
	    g.dispose();
		
		char tmp = card.colorRet();
		switch(tmp)
		{
		case 'r':
			color = new Color(255, 0, 0);
			break;
		
		case 'b':
			color = new Color(0, 0, 255);
			break;
			
		case 'y':
			color = new Color(255, 242, 0);
			break;
			
		case 'g':
			color = new Color(0, 255, 0);
			break;
			
		default:
			break;
		}
		
		for(int i=0;i<cardImage.getHeight();i++)
		{
			for(int j=0;j<cardImage.getWidth();j++)
			{
				getc = cardImage.getRGB(j, i);
				if(getc == colc)
					cardImage.setRGB(j, i, color.getRGB());
			}
		}
		return cardImage;
	}
	
	private static BufferedImage RotateNCards(BufferedImage image,int timesRotation)
	{
		int wIM = image.getWidth();
		int hIM = image.getHeight();
		
		if(timesRotation%2==1)
		{
			int tmp = wIM;
			wIM = hIM;
			hIM = tmp;
		}
		
		BufferedImage newImage = new BufferedImage(wIM,hIM,image.getType());
		
		Graphics2D g2 = newImage.createGraphics();
		if(timesRotation%4==3)
			g2.translate((hIM - wIM) / 2, (hIM - wIM) / 2);
		else if(timesRotation%4==1)
			g2.translate((wIM - hIM) / 2, (wIM - hIM) / 2);
		g2.rotate(Math.toRadians(timesRotation*90),wIM/2,hIM/2);
		g2.drawRenderedImage(image,null);
		
		return newImage;
		
	}
}
