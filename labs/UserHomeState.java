package labs.two;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHomeState implements IState {
	
	private Scanner sc;
	private String username;
	private IAuctionService ias;
	private String spacer = "============================";
	
	public UserHomeState(Scanner sc, IAuctionService ias, String username) {
		this.sc = sc;
		this.ias = ias;
		this.username = username;
	}

	@Override
	public void show() {}

	@Override
	public IState next() {
		ArrayList<String> options = new ArrayList<String>();
		options.add("Search");
		options.add("Create");
		options.add("Quit");
		
		int selected = GetMenuSelection(options);
		switch(selected) {
		
		case 0:
//			System.out.println("search selected");
			System.out.println(username + ", what would you like to search for?");
			String searchString = sc.nextLine();
			if(searchString == null || searchString.isEmpty()) {
				
				//debug
				//System.out.println("Empty search string entered, returning null.");
				return null;
			}
			return new SearchResultsState(sc, ias, username, searchString);
		case 1:
//			System.out.println("Create selected");
			return new AuctionCreateState(sc, ias, username);
		case 2:
//			System.out.println("quit selected");
			return null;
		default:
			return this.next();
		}
	}
	
	@Override
	public String toString() {
		return "UserHomeState:" + username;
	}
	
	public int GetMenuSelection(ArrayList<String> options) {
		System.out.println(spacer);
		System.out.println(username + ", select an option:");
		System.out.println(spacer);
		int count = 0;
		for(String s : options) {
			System.out.println("[" + count + "] " + s);
			count++;
		}
		System.out.println(spacer);
		int selection = -1;
		while(selection < 0 || selection > options.size() - 1) {
			try {
				selection = sc.nextInt();
				sc.nextLine();
			} catch(Exception e) {}
		}
		return selection;
	}

}
