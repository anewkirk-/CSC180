package labs.two;
public interface IAuctionService {
	
	public Auction[] search(String criteria);
	public void bid(String username, int itemId);
	public Auction create(Auction a);
	public void delete(Integer id);
	public Auction retrieve(Integer id) throws ObjectNotFoundException;
	public Auction update(Auction auction, Integer id) throws IdMismatchException;
	
}