package Uno.net.packets;

public class PacketGetCard extends Packet {
	private String card_data;
	
	public PacketGetCard(String msg) {
		super(4);
		this.card_data = msg;
	}

	@Override
	public String getData() {
		return "4"+card_data;
	}
	
	public String getCardData()
	{
		return card_data;
	}
}
