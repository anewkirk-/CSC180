package edu.neumont.csc180.auction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AuctionTest {
	
	private Auction a;
	
	@Before
	public void setUp() {
		a = new Auction(0, "", 5d);
	}
	
	@Test
	public void test_CustomProperty() {
		String expected = "property value";
		a.setProperty("PropertyName", expected);
		String actual = (String) a.getProperty("PropertyName");
		assertEquals(expected, actual);
	}
}
