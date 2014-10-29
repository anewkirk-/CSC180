package labs.three;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class AuctionCreateState implements IState {

	private Scanner sc;
	private IAuctionService ias;
	private SimpleDateFormatConverter sdfc = new SimpleDateFormatConverter(
			new SimpleDateFormat("M/d/y"));
	private String username;
	private Random gen = new Random();

	public AuctionCreateState(Scanner sc, IAuctionService ias, String username) {
		this.sc = sc;
		this.ias = ias;
		this.username = username;
	}

	@Override
	public void show() {
		System.out.println(username
				+ ", please enter info for new auction listing on next turn.");
	}

	@Override
	public IState next() {

		String name = "";
		while (name.isEmpty() || name == null) {
			System.out.println("Enter item name:");
			System.out.print(">");
			name = sc.nextLine();
		}

		String desc = "";
		while (desc.trim().isEmpty() || desc == null) {
			System.out.println("Enter item description");
			System.out.print(">");
			desc = sc.nextLine();
		}

		double startPrice = 1.00;
		System.out.println("Enter a starting price, or nothing to start at $1");
		System.out.print("$");
		try {
			startPrice = Double.parseDouble(sc.nextLine());
		} catch (Exception e) {
		}

		System.out
				.println("Enter an end date for the auction, or nothing to end in 1 week.");
		System.out.println("(ex: 01/31/2014 or 01.31.2014)");
		System.out.println(">");
		String date = sc.nextLine();
		date = date.replaceFirst("(\\d{2})[/\\.](\\d{2})[/\\.](\\d{4})",
				"$1/$2/$3");
		Date endDate = sdfc.parse(date);
		Auction a = new Auction(genItemId(), name, desc, startPrice);
		if(endDate != null) {
			a.setTimeLeftInMillis(endDate.getTime());
		} else {
			System.out.println("Could not read date. Defaulting to 1 week from now.");
			a.setTimeLeftInMillis(System.currentTimeMillis() + 604800000);
		}
		ias.create(a);
		return new UserHomeState(sc, ias, username);
	}

	private long genItemId() {
		long res = -1;
		do {
			res = gen.nextLong();
		} while (res < 0);
		return res;
	}

}
