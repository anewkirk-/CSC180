

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
}