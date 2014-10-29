package labs.three;

public class ContainsPredicate implements IPredicate<Auction> {
	
	private String searchString;
	
	public ContainsPredicate(String searchString) {
		this.searchString = searchString.toLowerCase();
	}

	@Override
	public boolean evaluate(Auction t) {
		String name = t.getName().toLowerCase();
		String desc = t.getDescription().toLowerCase();
		return name.contains(searchString) || desc.contains(searchString);
	}
	
	@Override
	public String toString() {
		return this.searchString;
	}

}
