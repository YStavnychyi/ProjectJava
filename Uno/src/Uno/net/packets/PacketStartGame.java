package Uno.net.packets;

public class PacketStartGame extends Packet {
	public PacketStartGame() {
		super(8);
	}

	@Override
	public String getData() {
		return "8";
	}
}
