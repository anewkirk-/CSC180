import java.util.Scanner;

public class UserHomeState implements IState {
	
	private Scanner sc;
	private String username;
	private InMemoryAuctionService imas;
	
	public UserHomeState(Scanner sc, InMemoryAuctionService imas, String username) {
		this.sc = sc;
		this.imas = imas;
		this.username = username;
	}

	@Override
	public void show() {
		System.out.println(username + ", what would you like to search for? (hit enter to log out)");
	}

	@Override
	public IState next() {
		String searchString = sc.nextLine();
		if(searchString == null || searchString.isEmpty()) {
			return null;
		}
		return new SearchResultsState(sc, imas, username, searchString);
	}
	
	@Override
	public String toString() {
		return "UserHomeState:" + username;
	}

}
