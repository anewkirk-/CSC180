package exercises.six;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexToolsTest {

	@Test
	public void testExtractNumbers() {
		String input = "3 + 2 / 4.0 * 1 ^ 17.34";
		String expected = "[3, 2, 4.0, 1, 17.34]";
		String actual = RegexTools.extractNumbers(input).toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExtractOperators() {
		String input = "3 + 2 / 4.0 * 1 ^ 17.34";
		String expected = "[+, /, *, ^]";
		String actual = RegexTools.extractOperators(input).toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExtractEmails() {
		String input = "Lorem ipsum dolor sit amet, consectetur test@example.com adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim ";
		String expected = "[test@example.com]";
		String actual = RegexTools.extractEmails(input).toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExtractListBody() {
		String input = "<ol> <li>Coffee</li> <li>Tea</li> <li>Milk</li> </ol>";
		String expected = "[Coffee, Tea, Milk]";
		String actual = RegexTools.extractListBody(input).toString();
		assertEquals(expected, actual);
	}

}
