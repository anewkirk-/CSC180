package labs.three;
public interface IAuctionService {
	
	public Auction[] search(String criteria);
	public void bid(String username, Long itemId) throws ExpiredBidException;
	public Auction create(Auction a);
	public void delete(Long id);
	public Auction retrieve(Long id) throws ObjectNotFoundException;
	public Auction update(Auction auction, Long id) throws IdMismatchException;
	
}