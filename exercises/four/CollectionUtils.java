package exercises.four;

import java.util.*;

public class CollectionUtils {

	@SuppressWarnings("rawtypes")
	public static int cardinality(Object obj, Collection col) 
	{
		int count = 0;
		if(col != null){
			for(Object o : col) {
				if(o != null) {
					if(o.equals(obj)) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	public static <T> Collection<T> filter(Collection<T> col, Predicate<T> p) {
		ArrayList<T> result = new ArrayList<T>();
		for(T t : col) {
			if(p.evaluate(t)) {
				result.add(t);
			}
		}
		return result;
	}
}