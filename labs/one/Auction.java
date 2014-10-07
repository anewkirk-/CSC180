public class Auction {
	
	private final int id;
	private double currentBid;
	private String owner;
	private String name;
	
	public Auction(int id, String name, double currentBid)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return ((Auction)obj).id == id;
	}
	
	@Override
	public int hashCode()
	{
		return new Integer(id).hashCode();
	}

	@Override
	public String toString()
	{
		if(owner != null) {
			return "[" + id + " | " + name + " | " + currentBid + " | " + owner;
		}
		return "[" + id + " | " + name + " | " + currentBid;
	}
	
	public int getId()
	{
		return id;
	}
	
	public double getCurrentBid()
	{
		return currentBid;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setCurrentBid(double bid)
	{
		this.currentBid = bid;
	}
	
	public void setOwner(String username)
	{
		this.owner = username;
	}
}
