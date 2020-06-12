package Uno.net.packets;

public class PacketSizeP extends Packet {
int pSize[];
	
	public PacketSizeP(int[] pSize) {
		super(5);
		this.pSize = pSize;
	}
	
	public PacketSizeP(String msg)
	{
		super(5);
		String tmp[] = msg.split(",");
		pSize = new int[tmp.length];
		for(int i=0;i<tmp.length;i++)
			pSize[i] = Integer.parseInt(tmp[i]);
	}

	@Override
	public String getData() {
		String tmp = "";
		for(int i=0;i<pSize.length;i++)
			tmp+=pSize[i]+",";
		return "5"+tmp;
	}
	
	public int[] getPSizeArray()
	{
		return pSize;
	}
}
