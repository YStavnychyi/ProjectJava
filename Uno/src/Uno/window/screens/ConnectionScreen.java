package Uno.window.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Uno.game.handler.GameHandlerer;
import Uno.window.ui.UIButton;
import Uno.window.ui.UIButtonImage;
import Uno.window.ui.UIClicker;
import Uno.window.ui.UITextEnter;
import Uno.window.ui.manager.UIManagerS;

public class ConnectionScreen extends Screens{

	private Font font = new Font("TimesRoman",Font.BOLD,30);
	
	private BufferedImage host[],connect[];
	private UITextEnter textIP;
	
	private JLabel label;
	
	public ConnectionScreen(GameHandlerer gameH) {
		super(gameH);
		
		host = new BufferedImage[2];
		host[0] = gameH.getButtonImage(200, 300, 200, 100);
		host[1] = gameH.getButtonImage(400, 0, 200, 100);
		
		connect = new BufferedImage[2];
		connect[0] = gameH.getButtonImage(200, 200, 200, 100);
		connect[1] = gameH.getButtonImage(0, 300, 200, 100);
		
		uiList = new UIManagerS(gameH);
		addComponents();
	}

	private void addComponents()
	{
		String text[]= {"Host","Connect:","Go Back Menu"};
		int wD[]=new int[text.length];
		for(int i=0;i<text.length;i++)
			wD[i]=gameH.getFontMetrics(font).stringWidth(text[i]);
		int hD=font.getSize();
		Color bcolors[] = {Color.green,Color.white};
		
		int w = host[0].getWidth(),h = host[0].getHeight();
		
		int x=gameH.getWidht()/2;
		int y=gameH.getHeight()/2;
		
		uiList.addComponent(new UIButtonImage(50,y-200,w,h,host,new UIClicker() {

			@Override
			public void ClickAction() {
				gameH.CreateServer();
				gameH.CreateClient("localhost");
		
				
				int n=create_Dialog(true);
				
				if(n==0)
				{
					
					gameH.startGameServer();
					gameH.setUIM(gameH.getGameM().getGameScreen().getUIList());
					gameH.setCurrentScreen(gameH.getGameM().getGameScreen());
					
				
				}
				else
				{
					gameH.stopClient();
					gameH.stopServer();
					
					
				}
				
				
			}}));
		
		uiList.addComponent(new UIButtonImage(50,y,w,h,connect,new UIClicker() {

			@Override
			public void ClickAction() {

				String ip = textIP.getText();
				
				gameH.CreateClient(ip);
				
				int n=create_Dialog(false);
				if(n==0)
				{
					gameH.stopClient();
				}
			}}));
		
		
		uiList.addComponent(new UIButton(x-100,y*2-100,wD[2],hD,text[2],bcolors,font,new UIClicker() {

			@Override
			public void ClickAction() {
				gameH.setUIM(gameH.getGameM().getMenuScreen().getUIList());
				gameH.setCurrentScreen(gameH.getGameM().getMenuScreen());
				
			}}));
		textIP=new UITextEnter(300,y+30,420,hD,font,24);
		uiList.addComponent(textIP);
	}
	
	@Override
	public void update() {
		
		if(label!=null)
			label.setText("<html>ConnectedPlayers:<br/>>"
					+gameH.getClientNameAt(0)+"<br/>>"
					+gameH.getClientNameAt(1)+"<br/>>"
					+gameH.getClientNameAt(2)+"<br/>>"
					+gameH.getClientNameAt(3)+"<br/> Game Lobby</html>");
		
		uiList.update();
	}

	@Override
	public void render(Graphics g) {
		
		
		
		//g.setColor(Color.orange);
		//g.fillRect(0, 0, widht, height);
		
		uiList.render(g);
		
	}
	
	private void create_Label()
	{
		label = new JLabel("<html>ConnectedPlayers:<br/>>"
				+gameH.getClientNameAt(0)+"<br/>>"
				+gameH.getClientNameAt(1)+"<br/>>"
				+gameH.getClientNameAt(2)+"<br/>>"
				+gameH.getClientNameAt(3)+"<br/> Game Lobby</html>");
	}
	
	private int create_Dialog(boolean isHost)
	{
		create_Label();
		
		Object options[];
		if(isHost)
			{
				options = new Object[2];
				options[0] = "Start";
				options[1] = "Cancel";
			}
		else
		{
			options = new Object[1];
			options[0]= "Disconnect";
		}
			
		
		int n=JOptionPane.showOptionDialog(gameH.getGameM().getFrame(), label
				, "Go back to menu",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, options, options[options.length-1]);
		
		label= null;
		
		return n;
	}

	public void disposeOptionDialog()
	{
		Window win = SwingUtilities.getWindowAncestor(label);
		if(win!=null)
			win.dispose();
		
	}
	
	public void startGameForClient()
	{
		gameH.setUIM(gameH.getGameM().getGameScreen().getUIList());
		gameH.setCurrentScreen(gameH.getGameM().getGameScreen());
	}
}
