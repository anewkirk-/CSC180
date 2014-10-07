import java.util.*;

public class InMemoryAuctionService implements AuctionService {

	private ArrayList<Auction> auctionItems;

	public InMemoryAuctionService() {
		this.auctionItems = new ArrayList<Auction>();
		auctionItems.add(new Auction(0001, "iPhone 5", 399.99));
		auctionItems.add(new Auction(0002, "Samsung Galaxy Tab", 249.85));
		auctionItems.add(new Auction(0003, "Google Nexus 5", 449.99));
		auctionItems.add(new Auction(0004, "Google Nexus 10", 399.31));
	}

	@Override
	public Auction[] search(String criteria) {
		ArrayList<Auction> result = new ArrayList<Auction>();
		for (Auction a : auctionItems) {
			if (a.getName().contains(criteria)) {
				result.add(a);
			}
		}
		Auction[] matches = new Auction[result.size()];
		for (int i = 0; i < result.size(); i++) {
			matches[i] = result.get(i);
		}
		return matches;
	}

	@Override
	public void bid(String username, int itemId) {
		for(Auction a : auctionItems)
		{
			if(a.getId() == itemId)
			{
				a.setCurrentBid(a.getCurrentBid() + 1.00);
				a.setOwner(username);
			}
		}
	}
	
	public ArrayList<Auction> getAuctionItems()
	{
		return this.auctionItems;
	}
}
