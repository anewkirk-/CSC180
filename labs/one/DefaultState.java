import java.util.Scanner;

public class DefaultState implements IState {
	
	private Scanner sc;
	private InMemoryAuctionService imas;
	
	public DefaultState(Scanner sc, InMemoryAuctionService imas) {
		this.sc = sc;
		this.imas = imas;
	}

	@Override
	public void show() {
		System.out.println("New user, would you like to log in? (hit enter to decline)");
	}

	@Override
	public IState next() {
		String username = sc.nextLine();
		if(username == null || username.length() == 0) {
			return null;
		} 
		else {
			return new UserHomeState(sc, imas, username);
		}
	}

}
