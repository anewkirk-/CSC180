package exercises.four;

public class ContainsPredicate implements Predicate<Auction> {
	
	private String searchString;
	
	public ContainsPredicate(String searchString) {
		this.searchString = searchString.toLowerCase();
	}

	@Override
	public boolean evaluate(Auction t) {
		String name = t.getName().toLowerCase();
		String desc = "";// t.getDescription().toLowerCase();
		return name.contains(searchString) || desc.contains(searchString);
	}

}
