package edu.neumont.csc180.auction;

import static org.junit.Assert.*;
import org.junit.Test;

public class InMemoryAuctionServiceTest {

	private InMemoryAuctionService imas = new InMemoryAuctionService();

	@Test
	public void test_Create() throws ObjectNotFoundException {
		Auction expected = new Auction(4, "test item", 100.00);
		Auction actual = imas.create(expected);
		assertEquals(expected, actual);
		
		assertTrue(actual.getId() == 4);
	}
	
	@Test
	public void test_Update() throws IdMismatchException {
		Auction expected = new Auction(1, "iPhone 6", 799.99);
		Auction actual = imas.update(expected, 1);
		assertEquals(expected, actual);
		
		assertTrue(actual.getId() == 1);
	}
	
	@Test
	public void test_Delete() throws ObjectNotFoundException {
		System.out.println(imas.getAuctionItems());
		int numItems = imas.getAuctionItems().size();
		imas.delete(6);
		int expected = numItems - 1;
		int actual = imas.getAuctionItems().size();
		assertEquals(expected, actual);
		
	}

}
