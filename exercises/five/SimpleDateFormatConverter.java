package exercises.five;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author anewkirk
 */
public class SimpleDateFormatConverter implements Converter<Date> {
	
	private SimpleDateFormat sdfForParsing;
	private SimpleDateFormat sdfForFormatting;
	private static SimpleDateFormat defaultSdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");	
	
	public SimpleDateFormatConverter() {
		sdfForParsing = sdfForFormatting = defaultSdf;
	}
	
	public SimpleDateFormatConverter(SimpleDateFormat sdf) {
		if(sdf != null) {
			sdfForParsing = sdfForFormatting = sdf;
		}
		else {
			sdfForParsing = sdfForFormatting = defaultSdf;
		}
	}
	
	public SimpleDateFormatConverter(SimpleDateFormat forParsing, SimpleDateFormat forFormatting) {
		if(forParsing != null) {
			sdfForParsing = forParsing;
		} else {
			sdfForParsing = defaultSdf;
		}
		
		if(forFormatting != null) {
			sdfForFormatting = forFormatting;
		} else {
			sdfForFormatting = defaultSdf;
		}
	}

	@Override
	public Date parse(String fromString) {
		try {
			return this.sdfForParsing.parse(fromString);
		} catch (ParseException e) {
			//nothing to do here, if it fails then null is returned.
		}
		return null;
	}

	@Override
	public String format(Date fromObject) {
		return this.sdfForFormatting.format(fromObject);
	}

}
