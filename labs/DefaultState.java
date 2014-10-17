package labs.two;
import java.util.Scanner;

public class DefaultState implements IState {
	
	private Scanner sc;
	private IAuctionService ias;
	
	public DefaultState(Scanner sc, IAuctionService ias) {
		this.sc = sc;
		this.ias = ias;
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
			return new UserHomeState(sc, ias, username);
		}
	}

}
