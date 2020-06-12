package Uno.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Uno.Card;
import Uno.Deal;
import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButtonImage;
import Uno.window.ui.UICard;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UIHolder;
import Uno.window.ui.UITakeCard;
import Uno.window.ui.manager.UIManagerS;

public class GameScreen extends Screens{


	private BufferedImage cards[];
	private BufferedImage cRotated[];
	private BufferedImage back[];
	private UICard centerCard;
	private ArrayList<UICard> cardArray;
	private UIHolder uiHolder;
	private Deal dealP1;
	
	
	String p2="Player2",p3="Player3",p4="Player4";
	int pc2=5,pc3=1,pc4=20;
	
	private int finishedCounter;
	
	private int cWidth =70,cHeight = 100;
	
	public GameScreen(GameHandlerer gameH) {
		super(gameH);
		
		cards = new BufferedImage[55];
		cRotated = new BufferedImage[3];

		cards = gameH.loadCards();
		cRotated = gameH.loadRotatedCards(cards[54]);
		
		back=new BufferedImage[2];
		back[0] = gameH.getButtonImage(500, 200, 50, 50);
		back[1] = gameH.getButtonImage(550, 200, 50, 50);
		
		uiList = new UIManagerS(gameH);
		
		cardArray =new ArrayList<UICard>();
		dealP1 = gameH.getClientDeck();
		
		assignNames();
		addComponents();
	}

	private void addComponents()
	{
		uiList.addComponent( new UITakeCard(40,40,cWidth,cHeight,cards[54],true, new UIClicker()
				{
					@Override
					public void ClickAction() {
						if(gameH.isClientTurn())
						{
							gameH.clientTakeCard();
							gameH.endClientTurn();
						}
						
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
					gameH.stopClient();
					gameH.stopServer();
					gameH.setUIM(gameH.getGameM().getMenuScreen().getUIList());
					gameH.setCurrentScreen(gameH.getGameM().getMenuScreen());
				}
				
				
			}} ));
		
		centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,new Card(0,'r'),cards);
		
		uiList.addComponent(centerCard);
		
		uiHolder = new UIHolder(0,0,0,0,cardArray,this);
		
		uiList.addComponent(uiHolder);
	}

	public void update() {
		
		if(gameH.getCenterCard()!=null)
			exchangeCenter(gameH.getCenterCard());
		
		pc2 = gameH.getPDeckSize(1);
		pc3 = gameH.getPDeckSize(2);
		pc4 = gameH.getPDeckSize(3);
		
		UpdateDeck();
	}


	public void render(Graphics g) {	
		g.setColor(Color.white);
		g.drawString(gameH.getClientName(), widht/2-30, height - 60);
		g.drawString("CardsLeft:"+dealP1.getSize(), widht/2+20, height - 60);
		
		if(pc2!=-1)
		{
			g.drawString(p2, 30, height/2-20);
			g.drawString("Card Left:"+pc2, 30, height/2-10);
			drawPacketCards(g,250,100,cHeight,cWidth,pc2,0);
		}
		
		if(pc3!=-1)
		{
			g.drawString(p3, widht/2-30, 50);
			g.drawString("Card Left:"+pc3, widht/2-30, 40);
			drawPacketCards(g,widht/2-30,60,cWidth,cHeight,pc3,1);
		}
		
		if(pc4!=-1)
		{
			g.drawString(p4, widht -100, height/2-20);
			g.drawString("Card Left:"+pc4,widht -100, height/2-10);
			drawPacketCards(g,250,height-10,cHeight,cWidth,pc4,2);
		}
		
		
		if(gameH.isClientTurn())
			g.drawString("Your Turn", widht/2+95, height-60);
		if(gameH.getIsClientFinished())
			g.drawString("You have finished:"+finishedCounter, widht/2+95, height-60);
		
		uiList.render(g);
		
		if(centerCard.getNum()>=13)
			g.drawString(""+centerCard.getCol(), widht/2, height/2+70);
		
		drawOnTop(g);
		
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
		if(centerCard.getCol()!=card.colorRet() || centerCard.getNum()!=card.numRet())
		{
			uiList.removeComponent(centerCard);
			centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,card,cards);
			uiList.addComponent(centerCard);
		}
		
	}
	
	private void addCardtoUI(Card card)
	{
		int wD =widht/2-30;
		int hD = height - 140;
		
		
		cardArray.add(new UICard(wD,hD,cWidth,cHeight,card,cards));
		uiList.addComponent(cardArray.get(cardArray.size()-1));
	}
	
	private void rearangeCards()
	{
		int count = cardArray.size();
		
		int size = (count/10) + 1;
		
		int position;
		int j=-(count/2);
		
		for(int i=0;i<count;i++)
		{
			j++;	
			position = ((widht/2-30)-(cWidth/2)) + (j*(cWidth/size));
			cardArray.get(i).updatePosition(position, height-180);
		}
	}
	
	private void drawOnTop(Graphics g)
	{
		if(!cardArray.isEmpty())
		{
			int i = uiHolder.getPosition();
			BufferedImage tmp = cardArray.get(i).getImage();
			int xx = cardArray.get(i).getX();
			int yy = cardArray.get(i).getY();
			g.drawImage(tmp,xx,yy-40,cWidth,cHeight,null);
		}
		
	}
	
	public boolean commitAction()
	{
		if(gameH.isClientTurn())
		{
			int pos = uiHolder.getPosition();
			char colorS = cardArray.get(pos).getCol();
			int numberS = cardArray.get(pos).getNum();
			if(colorS == 'a')
			{
				
				char colorN = 'r';
				Object options[] = {"Red","Yellow","Green","Blue"};
				int n=JOptionPane.showOptionDialog(gameH.getGameM().getFrame(), "Chose a color"
						, "Le choice",
						JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, 
						null, options, options[options.length-1]);
				if(n==1) colorN='y';
				else if(n==2) colorN='g';
				else if(n==3) colorN='b';
				gameH.SetCard(new Card(numberS,colorS), Character.toString(colorN));
				gameH.endClientTurn();
				return true;
			}
		
			char colorC = centerCard.getCol();
			int numberC = centerCard.getNum();
		
			
		
			if(colorC==colorS || numberC==numberS)
			{
				gameH.SetCard(new Card(numberS,colorS),null);
				gameH.endClientTurn();
				return true;
			}
		}
		return false;
	}
	
	private void UpdateDeck()
	{
		if(dealP1!=null && dealP1.getSize() != cardArray.size())
		{
		for(UICard c : cardArray)
			uiList.removeComponent(c);
		
		uiHolder.resetSelect();
		cardArray.clear();
		int sizeOfDeck = dealP1.getSize();
		for(int i=0;i<sizeOfDeck;i++)
		{
			Card tmpCard = dealP1.getCard(i);
			addCardtoUI(tmpCard);
		}
		checkIfFinished();
		uiHolder.changeToMax(sizeOfDeck);
		rearangeCards();
		}
	}
	
	private void checkIfFinished()
	{
		if(dealP1.getSize()==0)
		{
			finishedCounter=1;
			for(int i=1;i<4;i++)
			{
				if(gameH.getPDeckSize(i)==0)
				{
					finishedCounter++;
				}
			}
			gameH.setClientFinished();
		}
	}
	
	private void assignNames()
	{
		String [] tmpStr = new String[4];
		int max=0;
		int beg=0;
		for(int i=0;i<4;i++)
		{
			tmpStr[i] = gameH.getClientNameAt(i);
			if(tmpStr[i].equals(gameH.getClientName()))
				beg=i;
			if(tmpStr[i].equals(""))
			{
				max=i;
				break;
			}
		}
		beg = (beg+1)%max;
		p2 = tmpStr[beg];
		beg = (beg+1)%max;
		p3 = tmpStr[beg];
		beg = (beg+1)%max;
		p4 = tmpStr[beg];
			
		
	}
}

