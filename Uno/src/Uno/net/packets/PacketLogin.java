package Uno.net.packets;

public class PacketLogin extends Packet{
	private String name;
	
	public PacketLogin(String msg) {
		super(1);
		name = msg;
	}
	
	//Client
	public PacketLogin() {
		super(1);
		name = null;
	}
	

	@Override
	public String getData() {
		return "1"+name;
	}
	
	public String getName()
	{
		return name;
	}
}
