public class Auction {
	
	private int id;
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
	public String toString()
	{
		return "[" + id + " | " + name + " | " + owner + " | " + currentBid;
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
