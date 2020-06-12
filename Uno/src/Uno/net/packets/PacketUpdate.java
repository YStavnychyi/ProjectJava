package Uno.net.packets;

public class PacketUpdate extends Packet {
	private String nameList;
	
	public PacketUpdate(String nameList) {
		super(2);
		this.nameList=nameList;
	}

	@Override
	public String getData() {
		return "2"+nameList;
	}
	
	public String getNameList()
	{
		return nameList;
	}
}
