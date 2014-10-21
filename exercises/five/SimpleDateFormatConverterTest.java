package exercises.five;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class SimpleDateFormatConverterTest {
	
	private SimpleDateFormatConverter sdfc = new SimpleDateFormatConverter(new SimpleDateFormat("E d MMM y"));

	@Test
	public void testFormat() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		c.set(Calendar.MONTH, Calendar.OCTOBER);
		c.set(Calendar.DAY_OF_MONTH, 21);
		c.set(Calendar.YEAR, 2014);
		
		assertEquals("Tue 21 Oct 2014" , sdfc.format(c.getTime()));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		c.set(Calendar.MONTH, Calendar.MAY);
		c.set(Calendar.DAY_OF_MONTH, 12);
		c.set(Calendar.YEAR, 1994);
		
		assertEquals("Thu 12 May 1994", sdfc.format(c.getTime()));
	}
	
	@Test
	public void testParse() {
		String bday = "Thu 12 May 1994";
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		c.set(Calendar.MONTH, Calendar.MAY);
		c.set(Calendar.DAY_OF_MONTH, 12);
		c.set(Calendar.YEAR, 1994);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date bdate = c.getTime();
		
		assertEquals(bdate, sdfc.parse(bday));
	}
}