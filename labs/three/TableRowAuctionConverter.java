package labs.three;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TableRowAuctionConverter {
	
	private SimpleDateFormatConverter sdfc = new SimpleDateFormatConverter(new SimpleDateFormat("E @ ha"));
	private SimpleDateFormatConverter sdfcAlt = new SimpleDateFormatConverter(new SimpleDateFormat("M/d/y @ ha"));

	public String format(Auction fromObject) {
		String result = "";
		result += formatProp(fromObject.getId().toString(), 20) + " ";
		result += formatProp(fromObject.getName(), 50) + " ";
		String cBid = Double.toString(fromObject.getCurrentBid());
		result += formatProp(cBid, 15) + " ";
		result += formatProp(fromObject.getOwner(), 10) + " ";
		Date endsBy = fromObject.getEndsBy();
		if(endsBy.getTime() <= System.currentTimeMillis() + 604800000) {
			result += formatProp(sdfc.format(endsBy), 18) + " ";
		} else {
			result += formatProp(sdfcAlt.format(endsBy), 18) + " ";
		}
		
		return result;
	}
	
	private String formatProp(String prop, int finalLen) {
		if(prop == null) {
			prop = "-";
		}
		if(prop.length() > finalLen) {
			prop = prop.substring(0, finalLen - 3) + "...";
		} else {
			while(prop.length() < finalLen) {
				prop += " ";
			}
		}
		return prop;
	}
	
	public String formatAll(Auction[] a) {
		String result = "ID                   Name                                               Current Bid     Owner      Ends By\n";
		for(Auction auc : a) {
			result += this.format(auc) + "\n";
		}
		return result;
	}

}
