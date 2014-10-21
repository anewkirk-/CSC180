package exercises.five;

/**
 * This converter will convert the auction into a string that prints the Auction
 * *nicely* when rendered as a table row.
 * 
 * @author anewkirk
 */
public class TableRowAuctionConverter implements Converter<Auction> {

	@Override
	public Auction parse(String fromString) throws IllegalArgumentException {
		Auction res;
		try {
			String[] info = fromString.split(" \\| ");
			Integer id = Integer.parseInt(info[0]);
			String name = info[1];
			String desc = info[2];
			String[] bidInfo = info[3].split(":");
			double currentBid = Double.parseDouble(bidInfo[0]);
			String owner = bidInfo[1];
			res = new Auction(id, name, currentBid);
			res.setOwner(owner);
			res.setDescription(desc);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid auction string: \"" + fromString + "\"");
		}
		return res;
	}

	@Override
	public String format(Auction fromObject) {
		String name = fromObject.getName();
		String desc = fromObject.getDescription();
		String owner = fromObject.getOwner();
		Integer id = fromObject.getId();
		double currentBid = fromObject.getCurrentBid();
		String s = " | ";
		String info = id + s + name + s + desc + s + currentBid + ":" + owner;
		return info;
	}

}
