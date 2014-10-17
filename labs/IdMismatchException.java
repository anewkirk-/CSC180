package labs.two;

public class IdMismatchException extends Exception {

	private static final long serialVersionUID = -4965332956581140583L;
	private Integer auctionId;
	private Integer passedId;
	
	public IdMismatchException(Integer auctionId, Integer passedId) {
		this.auctionId = auctionId;
		this.passedId = passedId;
	}

	public Integer getAuctionId() {
		return this.auctionId;
	}
	
	public Integer getPassedId() {
		return this.passedId;
	}
}
