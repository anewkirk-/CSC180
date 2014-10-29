package labs.three;

public class IdMismatchException extends Exception {

	private static final long serialVersionUID = -4965332956581140583L;
	private Long auctionId;
	private Long passedId;
	
	public IdMismatchException(Long auctionId, Long passedId) {
		this.auctionId = auctionId;
		this.passedId = passedId;
	}

	public Long getAuctionId() {
		return this.auctionId;
	}
	
	public Long getPassedId() {
		return this.passedId;
	}
}
