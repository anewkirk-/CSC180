package labs.three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAuctionParser {
	
	public List<Auction> parsePage(String filePath) throws IOException {
		ArrayList<Auction> result = new ArrayList<Auction>();
		
		Path p = Paths.get(filePath);
		byte[] b = Files.readAllBytes(p);
		String html = new String(b).replaceAll("\r\n", "");
		
		Pattern auctionPattern = Pattern.compile("id=\"item.*?(?=Item:)");
		Matcher auctionMatcher = auctionPattern.matcher(html);
		while(auctionMatcher.find()) {
			result.add(parseAuction(auctionMatcher.group(0)));
		}
		
		return result;
	}
	
	public Auction parseAuction(String auction) {
		String name;
		double currentBid;
		int numBids;
		long endTime;
		long id;
		
		Pattern idPattern = Pattern.compile("listingid=\"(\\d+)");
		Matcher idMatcher = idPattern.matcher(auction);
		if(idMatcher.find()) {
			id = Long.parseLong(idMatcher.group(1));
		} else {
			id = 0;
		}
		
		Pattern namePattern = Pattern.compile("alt=\"(.+?)(?=\")");
		Matcher nameMatcher = namePattern.matcher(auction);
		if(nameMatcher.find()) {
			name = nameMatcher.group(1);
		} else {
			name = "Mystery item!";
		}
		
		Pattern cBidPattern = Pattern.compile("g-b\">\\$(\\d+,?\\d+?\\.\\d{2})");
		Matcher cBidMatcher = cBidPattern.matcher(auction);
		if(cBidMatcher.find()) {
			currentBid = Double.parseDouble(cBidMatcher.group(1).replace(",", ""));
		} else {
			currentBid = 1d;
		}
		
		Pattern numBidsPattern = Pattern.compile("(\\d+) bid");
		Matcher numBidsMatcher = numBidsPattern.matcher(auction);
		if(numBidsMatcher.find()) {
			numBids = Integer.parseInt(numBidsMatcher.group(1));
		} else {
			numBids = 1;
		}
		
		Pattern endTimePattern = Pattern.compile("timems=\"(\\d+)");
		Matcher endTimeMatcher = endTimePattern.matcher(auction);
		if(endTimeMatcher.find()) {
			endTime = Long.parseLong(endTimeMatcher.group(1));
		} else {
			endTime = System.currentTimeMillis() + 604800000;
		}
		
		Auction result = new Auction(id, name, currentBid);
		result.setTimeLeftInMillis(endTime);
		result.setNumberOfBidsRemaining(numBids);
		return result;
	}

}
