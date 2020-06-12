package Uno.net.packets;

public class PacketDisconnect extends Packet {
	public PacketDisconnect() {
		super(9);
	}

	@Override
	public String getData() {
		return "9";
	}
}
