package Uno.net.packets;

public class PacketTurn extends Packet {
	public PacketTurn() {
		super(6);
	}

	@Override
	public String getData() {
		return "6";
	}
}
