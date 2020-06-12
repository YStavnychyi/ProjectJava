package Uno.net.packets;

public class PacketSetCard extends Packet {
	private String cardData;
	private String chosenColor;
	
	public PacketSetCard(String cardData,String chosenColor) {
		super(3);
		this.cardData = cardData;
		this.chosenColor = chosenColor;
	}
	
	public PacketSetCard(String fullMsg)
	{
		super(3);
		String tmp[] = fullMsg.split(",");
		this.cardData = tmp[0];
		if(tmp.length==1)
			this.chosenColor=null;
		else
			this.chosenColor = tmp[1];
		
	}

	@Override
	public String getData() {
		return "3"+cardData+","+chosenColor;
	}

	public String getCardData()
	{
		return cardData;
	}
	
	public String getChosenColor()
	{
		return chosenColor;
	}
}
