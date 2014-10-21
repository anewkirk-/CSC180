package exercises.five;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableRowAuctionConverterTest {
	
	private TableRowAuctionConverter trac = new TableRowAuctionConverter();

	@Test
	public void testFormat() {
		String expected = "1234 | Test Item | A dummy auction item | 99.99:anewkirk";
		
		Auction a = new Auction(1234, "Test Item", 99.99);
		a.setOwner("anewkirk");
		a.setDescription("A dummy auction item");
		
		String actual = trac.format(a);
		
		assertEquals(expected, actual);
	}

}
