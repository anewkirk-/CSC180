package labs.three;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class InMemoryAuctionService implements IAuctionService {
	private HashMap<Long, Auction> auctionItems;
	private Stack<SearchOperator> opStack = new Stack<SearchOperator>();
	private Stack<IPredicate<Auction>> predStack = new Stack<IPredicate<Auction>>();
 
	public InMemoryAuctionService() {
		this.auctionItems = new HashMap<Long, Auction>();
		create(new Auction(0000L, "iPhone 5", 399.99));
		create(new Auction(0001L, "Samsung Galaxy Tab", 249.85));
		create(new Auction(0002L, "Google Nexus 5", 449.99));
		create(new Auction(0003L, "Google Nexus 10", 399.31));
	}
	
	public InMemoryAuctionService(String dataSource) {
		this.auctionItems = new HashMap<Long, Auction>();
		HtmlAuctionParser hap = new HtmlAuctionParser();
		try {
			List<Auction> auctions = hap.parsePage(dataSource);
			for(Auction a : auctions) {
				create(a);
			}
		} catch (IOException e) {
			System.out.println("[!] Unable to parse auction data.\n\n");
		}
		
	}

	@Override
	public Auction[] search(String criteria) {
		@SuppressWarnings({"rawtypes"})
		Collection res = CollectionUtils.filter(auctionItems.values(), parse(criteria));
		Auction[] toReturn = new Auction[res.size()];
		int count = 0;
		for(Object o : res) {
			toReturn[count] = (Auction) o;
			count++;
		}
		return removeExpired(toReturn);
		
	}
	
	private Auction[] removeExpired(Auction[] a) {
		ArrayList<Auction> result = new ArrayList<Auction>();
		for(Auction auc : a) {
			if(auc.getEndsBy().getTime() > Calendar.getInstance().getTime().getTime()) {
				result.add(auc);
			}
		}
		Object[] res = result.toArray();
		Auction[] auctions = new Auction[res.length];
		for(int i = 0; i < res.length; i++) {
			auctions[i] = (Auction)res[i];
		}
		return auctions;
	}

	public Auction create(Auction auction) {
		auctionItems.put(auction.getId(), auction);
		Auction res = null;
		try {
			res = retrieve(auction.getId());
		} catch (Exception e) {
		}
		return res;
	}

	public void delete(Long id) {
		if (auctionItems.containsKey(id)) {
			auctionItems.remove(id);
		}
	}

	@Override
	public Auction retrieve(Long id) throws ObjectNotFoundException {
		if (auctionItems.containsKey(id)) {
			return auctionItems.get(id);
		}
		throw new ObjectNotFoundException(id);
	}

	public Auction update(Auction auction, Long id)
			throws IdMismatchException {
		if (auction.getId() == id) {
			auctionItems.put(id, auction);
			return auctionItems.get(id);
		}
		throw new IdMismatchException(auction.getId(), id);
	}

	@Override
	public void bid(String username, Long itemId) throws ExpiredBidException {
		Auction a = auctionItems.get(itemId);
		if(a.getEndsBy().getTime() < Calendar.getInstance().getTime().getTime()) {
			throw new ExpiredBidException();
		}
		a.setCurrentBid(a.getCurrentBid() + 1.00);
		a.setOwner(username);
	}

	public HashMap<Long, Auction> getAuctionItems() {
		return this.auctionItems;
	}
	
	public IPredicate<Auction> parse(String searchString) {
		searchString = searchString.replace(" ", "");
		if(searchString.isEmpty()) {
			return new ContainsPredicate("");
		}
		while(searchString.length() > 0) {
			if(searchString.startsWith("OR")) {
				while(!opStack.empty() && opStack.peek() == SearchOperator.AND) {
					opStack.pop();
					IPredicate<Auction> p1 = predStack.pop();
					IPredicate<Auction> p2 = predStack.pop();
					AndPredicate ap = new AndPredicate(p1, p2);
					predStack.push(ap);
					//System.out.println("[+] pushed new AndPredicate onto predStack: " + ap);
					
				}
				opStack.push(SearchOperator.OR);
				searchString = searchString.substring(2);
				//System.out.println("[+] pushed new OR onto opStack");
			}
			else if(searchString.startsWith("AND")) {
				opStack.push(SearchOperator.AND);
				searchString = searchString.substring(3);
				//System.out.println("[+] pushed new AND onto opStack");
			}
			else {
				String keyword = "";
				int andIndex = searchString.indexOf("AND");
				int orIndex = searchString.indexOf("OR");
				int keywordEndIndex = -1;
				
				if(!(andIndex == -1 && orIndex == -1)) {
					if(andIndex > 0 && (andIndex < orIndex || orIndex == -1)) {
						keywordEndIndex = andIndex;
					}
					else if(orIndex > 0 &&  (orIndex < andIndex || andIndex == -1)) {
						keywordEndIndex = orIndex;
					}
					if(keywordEndIndex >= 0) {
						keyword = searchString.substring(0, keywordEndIndex);
					}
				}
				else {
					//keyword is just what's left of the searchString
					keyword = searchString;
				}
				if(keyword.length() > 0) {
					searchString = searchString.substring(keyword.length());
					ContainsPredicate contp = new ContainsPredicate(keyword);
					predStack.push(contp);
				}
			}
		}
		while(!opStack.empty()) {
			SearchOperator op = opStack.pop();
			IPredicate<Auction> p1 = predStack.pop();
			IPredicate<Auction> p2 = predStack.pop();
			switch(op) {
			case OR:
				predStack.push(new OrPredicate(p1, p2));
				break;
			case AND:
				predStack.push(new AndPredicate(p1, p2));
				break;
			default:
				break;
			}
		}
		return predStack.pop();
	}
}