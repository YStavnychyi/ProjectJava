package Uno.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Uno.Card;
import Uno.CardHandler;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButtonImage;
import Uno.window.ui.UICard;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UITakeCard;
import Uno.window.ui.manager.UIManagerS;

public class GameScreen extends Screens{


	private BufferedImage cards[];
	private BufferedImage cRotated[];
	private BufferedImage back[];
	private CardHandler cardH; //for testing
	private UICard centerCard;
	private ArrayList<UICard> cardArray;
	
	
	String p1="Player1",p2="Player2",p3="Player3",p4="Player4";
	int pc2=5,pc3=1,pc4=20;
	
	private int cWidth =70,cHeight = 100;
	
	public GameScreen(GameHandlerer gameH) {
		super(gameH);
		
		cardH = new CardHandler(); //for testing
		
		cards = new BufferedImage[16];
		cRotated = new BufferedImage[3];
		loadCards();
		
		back=new BufferedImage[2];
		back[0] = gameH.getButtonImage(500, 200, 50, 50);
		back[1] = gameH.getButtonImage(550, 200, 50, 50);
		
		uiList = new UIManagerS(gameH);
		
		cardArray =new ArrayList<UICard>();

		addComponents();
		
		cardH.shuffleDeck();
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
		for(int i=0;i<3;i++)
			cRotated[i]=RotateNCards(tmpImg[15],i+1);
		
		for(int i=0;i<52;i++)
		{
			for(int j=0;j<4;j++)
			{
				char a = 'a';
				if(j==0) a='r';
				else if(j==1) a='b';
				else if(j==2) a='y';
				else if(j==3) a='g';
				cards[(i*4)+j] = tmpImg[(i*4)+j];
			}
				
		}
	}

	private void addComponents()
	{
		uiList.addComponent( new UITakeCard(40,40,cWidth,cHeight,cards[15],true, new UIClicker()
				{
					@Override
					public void ClickAction() {
						takeCard();
					}
				}));
		
		uiList.addComponent(new UIButtonImage(widht-70,height-100, 50, 50, back, new UIClicker() {
			@Override
			public void ClickAction() {
				Object options[]= {"Yes","No"};
				int n= JOptionPane.showOptionDialog(gameH.getGameM().getFrame(), "Are you sure you want to exit?", "Go back to menu",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, options, options[1]);
				
				if(n==0)
				{
					gameH.setUIM(gameH.getGameM().getMenuScreen().getUIList());
					gameH.setCurrentScreen(gameH.getGameM().getMenuScreen());
				}
				
				
			}} ));
		
		centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,new Card(0,'r'),cards,null);
		
		uiList.addComponent(centerCard);
	}

	public void update() {

	}


	public void render(Graphics g) {	
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
		
		uiList.render(g);
		
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

	private void exchangeCenter(Card card)
	{
		uiList.removeComponent(centerCard);
		centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,card,cards,null);
		uiList.addComponent(centerCard);
	}
	
	private void takeCard()
	{
		Card card;
		card = cardH.getLast();
		if(card!=null)
		{
			System.out.println(card.colorRet());
			cardH.removeLast();
			addCardtoUI(card);

		}
		rearangeCards();
	}
	
	private void addCardtoUI(Card card)
	{
		int wD =widht/2-30;
		int hD = height - 140;
		
		
		cardArray.add(new UICard(wD,hD,cWidth,cHeight,card,cards,new UIClicker() {
			@Override
			public void ClickAction() {
				if(card.numRet() == centerCard.getNum() || card.colorRet() == centerCard.getCol() || card.colorRet()=='a')
				{
					centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,card,cards,null);
				}
				
			}}));
		uiList.addComponent(cardArray.get(cardArray.size()-1));
	}
	
	private void rearangeCards()
	{
		int count = cardArray.size();
		
		int position;
		int j=0;
		
		for(int i=0;i<count;i++)
		{
			int tmp=-1;
			if(i%2==1)
			{
				tmp=1;
				j++;
			}
				
			position = widht/2-30 + (j*cWidth)*tmp;
			cardArray.get(i).updatePosition(position, height-180);
		}
	}
	
	
	private BufferedImage paintOver(Card card,BufferedImage cardImage,int colc)
	{
		Color color = new Color(0, 0, 0);;
		//int colc = cardImage.getRGB(50, 20);
		int getc;
		
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
		
		if(tmp == 'r');
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

	
}

