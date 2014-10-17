package labs.two;

public class OrPredicate implements IPredicate<Auction> {
	
	private IPredicate<Auction> p1;
	private IPredicate<Auction> p2;

	public OrPredicate(IPredicate<Auction> p1, IPredicate<Auction> p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public boolean evaluate(Auction auction) {
		return(p1.evaluate(auction) || p2.evaluate(auction));
	}
	
	@Override
	public String toString() {
		return "[" + p1 + " OR " + p2 + "]";
	}

}
