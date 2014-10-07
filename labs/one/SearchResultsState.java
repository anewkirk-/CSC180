import java.util.Scanner;

public class SearchResultsState implements IState {
	
	private Scanner sc;
	private InMemoryAuctionService imas;
	private String username;
	private String searchString;
	private String consoleOutOne = "========================================";
	private String consoleOutTwo = "===          Search Results          ===";
	
	public SearchResultsState(Scanner sc, InMemoryAuctionService imas, String username, String searchString) {
		this.sc = sc;
		this.imas = imas;
		this.username = username;
		this.searchString = searchString;
	}

	@Override
	public void show() {
		Auction[] matches = imas.search(searchString);
		System.out.println(username + ", here are your search results:");
		System.out.println(consoleOutOne);
		System.out.println(consoleOutTwo);
		System.out.println(consoleOutOne);
		System.out.println("Number of matches:" + matches.length);
		for(int i = 0; i < matches.length; i++) {
			Auction a = matches[i];
			if(a != null) {
				System.out.println(a.toString());	
			}
		}
		System.out.println(consoleOutOne);
		System.out.println("Enter the item id to increase the bid by $1. Otherwise, enter another search: (Hit enter to go back to home page");
	}

	@Override
	public IState next() {
		String response = sc.nextLine();
		if(response == null || response.isEmpty()) {
			return new UserHomeState(sc, imas, username);
		}
		else {
			int itemId;
			try {
				itemId = Integer.parseInt(response);
				imas.bid(username, itemId);
				return new UserHomeState(sc, imas, username);
			} catch (Exception e) {
				return new SearchResultsState(sc, imas, username, response);
			}
		}
	}

}