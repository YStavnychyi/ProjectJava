package Uno.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Uno.Card;
import Uno.CardHandler;
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
	private CardHandler cardH; //for testing
	private UICard centerCard;
	private ArrayList<UICard> cardArray;
	private UIHolder uiHolder;
	
	
	String p1="Player1",p2="Player2",p3="Player3",p4="Player4";
	int pc2=5,pc3=1,pc4=20;
	
	private int cWidth =70,cHeight = 100;
	
	public GameScreen(GameHandlerer gameH) {
		super(gameH);
		
		cardH = new CardHandler(); //for testing
		
		cards = new BufferedImage[55];
		cRotated = new BufferedImage[3];

		cards = gameH.loadCards();
		cRotated = gameH.loadRotatedCards(cards[54]);
		
		back=new BufferedImage[2];
		back[0] = gameH.getButtonImage(500, 200, 50, 50);
		back[1] = gameH.getButtonImage(550, 200, 50, 50);
		
		uiList = new UIManagerS(gameH);
		
		cardArray =new ArrayList<UICard>();

		addComponents();
		
		cardH.shuffleDeck();
	}

	private void addComponents()
	{
		uiList.addComponent( new UITakeCard(40,40,cWidth,cHeight,cards[54],true, new UIClicker()
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
		
		centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,new Card(0,'r'),cards);
		
		uiList.addComponent(centerCard);
		
		uiHolder = new UIHolder(0,0,0,0,cardArray);
		
		uiList.addComponent(uiHolder);
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
		uiList.removeComponent(centerCard);
		centerCard = new UICard(widht/2-(cWidth/2),height/2-(cHeight/2),cWidth,cHeight,card,cards);
		uiList.addComponent(centerCard);
	}
	
	private void takeCard()
	{
		Card card;
		card = cardH.getLast();
		if(card!=null)
		{
			cardH.removeLast();
			addCardtoUI(card);
			uiHolder.changeAmount(1);

		}
		rearangeCards();
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
	
}

