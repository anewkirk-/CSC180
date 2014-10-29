package labs.three;

import static org.junit.Assert.*;
import org.junit.Test;

public class InMemoryAuctionServiceSearchTest {
	
	private InMemoryAuctionService imas;
	
	public InMemoryAuctionServiceSearchTest() {
		imas = new InMemoryAuctionService();
	}

	@Test
	public void test_search() {
		int expected = 1;
		int actual = imas.search("iPhone").length;
		assertEquals(expected, actual);
		
		expected = 2;
		actual = imas.search("Nexus").length;
		assertEquals(expected, actual);
		
		expected = 0;
		actual = imas.search("XBOX").length;
		assertEquals(expected, actual);
	}
}