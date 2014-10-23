package exercises.six;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author anewkirk
 */
public class RegexTools {

	public static List<Number> extractNumbers(String input) {
		List<Number> res = new ArrayList<Number>();
		Pattern p = Pattern.compile("[0-9]+(\\.[0-9]+)*");
		Matcher m = p.matcher(input);
		while(m.find()) {
			String match = m.group(0);
			if(match.indexOf('.') != -1) {
				res.add(Double.parseDouble(match));
			} else {
				res.add(Integer.parseInt(match));
			}
		}
		return res;
	}

	public static List<String> extractOperators(String input) {
		List<String> res = new ArrayList<String>();
		Pattern p = Pattern.compile("\\+|-|\\*|/|\\^|%");
		Matcher m = p.matcher(input);
		while(m.find()) {
			res.add(m.group(0));
		}
		return res;
	}

	public static List<String> extractEmails(String input) {
		List<String> res = new ArrayList<String>();
		Pattern p = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+");
		Matcher m = p.matcher(input);
		while(m.find()) {
			res.add(m.group(0));
		}
		return res;
	}

	public static List<String> extractListBody(String input) {
		List<String> res = new ArrayList<String>();
		Pattern p = Pattern.compile("(?<=<li>).*?(?=</li>)");
		Matcher m = p.matcher(input);
		while(m.find()) {
			res.add(m.group(0));
		}
		return res;
	}

}
