import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class InMemoryAuctionServiceTest {
	
	private InMemoryAuctionService imas;
	
	public InMemoryAuctionServiceTest() {
		this.imas = new InMemoryAuctionService();
	}
	
	@Test
	public void test_bid()
	{
		imas.bid("testUser", 0001);
		Auction a1 = imas.getAuctionItems().get(0);
		assertTrue(a1.getOwner().equals("testUser"));
		assertTrue(a1.getCurrentBid() - 400.99 <= 0.01);
		
		imas.bid("testUser2", 0001);
		assertTrue(a1.getOwner().equals("testUser2"));
		assertTrue(a1.getCurrentBid() - 401.99 <= 0.01);
	}
	
	@Test
	public void test_search()
	{
		assertTrue(imas.search("Nexus").length == 2);
		assertTrue(imas.search("asdf").length == 0);
		assertTrue(imas.search("iPhone").length == 1);
	}

}
