package edu.neumont.csc180.auction;
import java.util.*;

public class InMemoryAuctionService implements IAuctionService {

	private HashMap<Integer, Auction> auctionItems;
	private static int nextId = 0;

	public InMemoryAuctionService() {
		this.auctionItems = new HashMap<Integer, Auction>();
		create(new Auction(0000, "iPhone 5", 399.99));
		create(new Auction(0001, "Samsung Galaxy Tab", 249.85));
		create(new Auction(0002, "Google Nexus 5", 449.99));
		create(new Auction(0003, "Google Nexus 10", 399.31));
	}

	@Override
	public Auction[] search(String criteria) {
		ArrayList<Auction> result = new ArrayList<Auction>();
		for (Auction a : auctionItems.values()) {
			if (a.getName().toLowerCase().contains(criteria.toLowerCase())) {
				result.add(a);
			}
		}
		Auction[] matches = new Auction[result.size()];
		for (int i = 0; i < result.size(); i++) {
			matches[i] = result.get(i);
		}
		return matches;
	}
	
	public Auction create(Auction auction){
		Auction a = new Auction(nextId, auction.getName(), auction.getCurrentBid());
		auctionItems.put(nextId, a);
		nextId++;
		Auction res = null;
		try {
			res = retreive(a.getId());
		} catch (Exception e) {}
		return res;
	}
	
	public void delete(Integer id) {
		if(auctionItems.containsKey(id)) {
			auctionItems.remove(id);
		}
	}
	
	public Auction retreive(Integer id) throws ObjectNotFoundException {
		if(auctionItems.containsKey(id)) {
			return auctionItems.get(id);
		}
		throw new ObjectNotFoundException(id);
	}
	
	public Auction update(Auction auction, Integer id) throws IdMismatchException {
		if(auction.getId() == id) {
			auctionItems.put(id, auction);
			return auctionItems.get(id);
		}
		throw new IdMismatchException(auction.getId(), id);
	}

	@Override
	public void bid(String username, int itemId) {
		Auction a = auctionItems.get(itemId);
		a.setCurrentBid(a.getCurrentBid() + 1.00);
		a.setOwner(username);
	}
	
	public HashMap<Integer, Auction> getAuctionItems()
	{
		return this.auctionItems;
	}
}
