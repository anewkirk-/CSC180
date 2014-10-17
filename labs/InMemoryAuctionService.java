package labs.two;


import java.util.*;

public class InMemoryAuctionService implements IAuctionService {
	private HashMap<Integer, Auction> auctionItems;
	private Stack<SearchOperator> opStack = new Stack<SearchOperator>();
	private Stack<IPredicate<Auction>> predStack = new Stack<IPredicate<Auction>>();
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
		@SuppressWarnings({"rawtypes"})
		Collection res = CollectionUtils.filter(auctionItems.values(), parse(criteria));
		Auction[] toReturn = new Auction[res.size()];
		int count = 0;
		for(Object o : res) {
			toReturn[count] = (Auction) o;
			count++;
		}
		return toReturn;
		
	}

	public Auction create(Auction auction) {
		Auction a = new Auction(nextId, auction.getName(),
				auction.getCurrentBid());
		auctionItems.put(nextId, a);
		nextId++;
		Auction res = null;
		try {
			res = retrieve(a.getId());
		} catch (Exception e) {
		}
		return res;
	}

	public void delete(Integer id) {
		if (auctionItems.containsKey(id)) {
			auctionItems.remove(id);
		}
	}

	@Override
	public Auction retrieve(Integer id) throws ObjectNotFoundException {
		if (auctionItems.containsKey(id)) {
			return auctionItems.get(id);
		}
		throw new ObjectNotFoundException(id);
	}

	public Auction update(Auction auction, Integer id)
			throws IdMismatchException {
		if (auction.getId() == id) {
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

	public HashMap<Integer, Auction> getAuctionItems() {
		return this.auctionItems;
	}
	
	public IPredicate<Auction> parse(String searchString) {
		searchString = searchString.replace(" ", "");
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
					
//					System.out.println("[!] andIndex = " + andIndex);
//					System.out.println("[!] orIndex = " + orIndex);
//					System.out.println("[!] keywordEndIndex = " + keywordEndIndex);
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
					//System.out.println("[+] pushed new Contains onto predStack: " + contp);
				}
			}
//			System.out.println("[!] searchString length: " + searchString.length());
//			System.out.println("[!] searchString = " + searchString);
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
				//System.out.println("\n[!]Something broke!\n");
				break;
			}
		}
		//System.out.println("\n[+] done.\n");
//		while(!predStack.empty()) {
//			System.out.println(predStack.pop());
//		}
		return predStack.pop();
	}
}