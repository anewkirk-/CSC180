package labs.two;

import java.util.Scanner;

public class SearchResultsState implements IState {
	
	private Scanner sc;
	private IAuctionService ias;
	private String username;
	private String searchString;
	private String consoleOutOne = "========================================";
	private String consoleOutTwo = "===          Search Results          ===";
	
	public SearchResultsState(Scanner sc, IAuctionService ias, String username, String searchString) {
		this.sc = sc;
		this.ias = ias;
		this.username = username;
		this.searchString = searchString;
	}

	@Override
	public void show() {
		Auction[] matches = ias.search(searchString);
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
		System.out.println("Enter the item id to increase the bid by $1. Otherwise, enter another search: (Hit enter to go back to home page)");
	}

	@Override
	public IState next() {
		String response = sc.nextLine();
		if(response == null || response.isEmpty()) {
			return new UserHomeState(sc, ias, username);
		}
		else {
			int itemId;
			try {
				itemId = Integer.parseInt(response);
				ias.bid(username, itemId);
				return new UserHomeState(sc, ias, username);
			} catch (Exception e) {
				return new SearchResultsState(sc, ias, username, response);
			}
		}
	}

}