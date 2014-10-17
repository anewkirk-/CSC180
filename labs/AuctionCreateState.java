package labs.two;

import java.util.Scanner;

public class AuctionCreateState implements IState {
	
	private Scanner sc;
	private IAuctionService ias;
	private String username;
	
	public AuctionCreateState(Scanner sc, IAuctionService ias, String username) {
		this.sc = sc;
		this.ias = ias;
		this.username = username;
	}

	@Override
	public void show() {
		System.out.println(username + ", please enter info for new auction listing on next turn.");
	}

	@Override
	public IState next() {
		
		String name = "";
		while(name.isEmpty() || name == null) {
			System.out.println("Enter item name:");
			System.out.print(">");
			name = sc.nextLine();
		}
		
		String desc = "";
		while(desc.trim().isEmpty() || desc == null) {
			System.out.println("Enter item description");
			System.out.print(">");
			desc = sc.nextLine();
		}
		
		double startPrice = 1.00;
		System.out.println("Enter a starting price, or nothing to start at $1");
		System.out.print("$");
		try {
			startPrice = Double.parseDouble(sc.nextLine());
		} catch(Exception e) {}
		
		Auction a = new Auction(0, name, desc, startPrice);
		ias.create(a);
		return new UserHomeState(sc, ias, username);
	}

}
