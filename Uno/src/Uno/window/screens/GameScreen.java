package Uno.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import Uno.Card;
import Uno.CardHandler;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UICard;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UITakeCard;
import Uno.window.ui.manager.UIManagerS;

public class GameScreen extends Screens{


	private BufferedImage cards[];
	private BufferedImage cRotated[];
	private CardHandler cardH; //for testing
	
	String p1="Player1",p2="Player2",p3="Player3",p4="Player4";
	int pc2=5,pc3=1,pc4=20;
	
	private int cWidth =70,cHeight = 100;
	
	public GameScreen(GameHandlerer gameH) {
		super(gameH);
		
		cardH = new CardHandler(); //for testing
		
		cards = new BufferedImage[16];
		cRotated = new BufferedImage[3];
		loadCards();
		
		uiList = new UIManagerS(gameH);
		addComponents();
	}
	
	private BufferedImage RotateNCards(BufferedImage image,int timesRotation)
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
	
	private void loadCards()
	{
		int w,h;
		w=gameH.getGameM().getSheetHolder().getCardSheet().getWidth()/8;
		h=gameH.getGameM().getSheetHolder().getCardSheet().getHeight()/2;
		
		int y=0;
		
		for(int i=0;i<16;i++)
		{
			if(i==8)
			{
				y=h;
			}
			cards[i] = gameH.getCardImage((i%8)*w, y, w, h);
		}
		for(int i=0;i<3;i++)
			cRotated[i]=RotateNCards(cards[15],i+1);
	}

	private void addComponents()
	{
		uiList.addComponent( new UITakeCard(40,40,cWidth,cHeight,cards[15],true, new UIClicker()
				{
					@Override
					public void ClickAction() {
						System.out.println("Dobierz");
					}
				}));
	}

	public void update() {

	}


	public void render(Graphics g) {
		g.drawImage(cards[15], 40, 40,cWidth,cHeight, null);		
		
		g.setColor(Color.white);
		g.drawString(p1, widht/2-30, height - 60);
		
		g.drawString(p2, 30, height/2-20);
		g.drawString("Card Left:"+pc2, 30, height/2-10);
		drawPacketCards(g,250,100,cHeight,cWidth,pc2,0);
		
		g.drawString(p3, widht/2-30, 50);
		g.drawString("Card Left:"+pc3, widht/2-30, 40);
		drawPacketCards(g,widht/2-30,60,cWidth,cHeight,pc3,1);
		
		g.drawString(p4, widht -100, height/2-20);
		g.drawString("Card Left:"+pc4,widht -100, height/2-10);
		drawPacketCards(g,250,height-10,cHeight,cWidth,pc4,2);
		
		
		
	}
	
	private void drawPacketCards(Graphics g,int x,int y,int wNum,int hNum,int num,int iNum)
	{
		if(num>10)
			num=10;
		int xx,yy;
		
		for(int i=0;i<num;i++)
		{
			if(iNum%2==0)
			{
				xx=y;
				yy=x+((i-num/2)*(wNum/3) /2);
			}
			else
			{
				yy=y;
				xx=x+((i-num/2)*(wNum/3) /2);
			}
			
			g.drawImage(cRotated[iNum],xx,yy,wNum,hNum, null);
		}
	}

}
