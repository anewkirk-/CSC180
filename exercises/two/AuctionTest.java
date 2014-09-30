import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AuctionTest {
	
	@Test
	public void test_properties()
	{
		Auction a = new Auction(4321, "AuctionName", 0.01);
		assertTrue(a.getId() == 4321);
		assertTrue(a.getName().equals("AuctionName"));
		assertTrue(a.getCurrentBid() - 0.01 < 0.0001);
	}

}
