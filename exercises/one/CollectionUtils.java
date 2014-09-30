package exercises.one;

import java.util.*;

public class CollectionUtils {

	public static int cardinality(Object obj, Collection col) 
	{
		int count = 0;
		if(col != null){
			for(Object o : col) {
				if(o != null) {
					if(o == obj) {
						count++;
					}
				}
			}
		}
		return count;
	}
}