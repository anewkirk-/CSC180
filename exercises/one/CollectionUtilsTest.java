package exercises.one;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class CollectionUtilsTest extends TestCase {
	
	private ArrayList<String> col;
	private String aString = "a";
	
	public CollectionUtilsTest()
	{
		col = new ArrayList<String>();
		col.add(aString);
		col.add(aString);
		col.add("b");
		col.add("c");
		col.add("d");
	}

	@Test
	public void cardinalityTest1() 
	{		
		//there should be two copies of aString
		assertEquals(2, CollectionUtils.cardinality(aString, col));
	}
	
	@Test
	public void cardinalityNullObjectTest()
	{
		assertEquals(0, CollectionUtils.cardinality(null, col));
	}
	
	@Test
	public void cardinalityNullCollectionTest()
	{
		assertEquals(0, CollectionUtils.cardinality(aString, null));
	}
}
