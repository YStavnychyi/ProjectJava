package Uno.net.packets;

public abstract class Packet {
	@SuppressWarnings("unused")
	private int packetID;
	
	public static enum PacketsIDs
	{
		INVALID(0),LOGIN(1),UPDATE(2),SETCARD(3),GETCARD(4),SIZEP(5),TURN(6),STARTGAME(8),DISCONNECT(9);

		private int packetID;

		PacketsIDs(int pID) {
			this.packetID = pID;
		}
		
		public int getId() {
            return packetID;
        }
	}
	
	public Packet(int pID)
	{
		this.packetID = pID;
	}
	
	 
	 public String readData(String data) {
	        return data.substring(1);
	 }
	 
	 public static PacketsIDs getPacketID(int id)
	 {
		 for (PacketsIDs i : PacketsIDs.values()) {
	            if (i.getId() == id) {
	                return i;
	            }
	        } 
		 return PacketsIDs.INVALID;
	 }
	 
	 public abstract String getData();
}
