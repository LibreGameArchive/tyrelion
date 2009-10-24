/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import tyrelion.map.WorldItemMap;
import tyrelion.objects.WorldItem;

/**
 * @author jahudi
 *
 */
public class WorldItemMapTest {

	WorldItemMap map;
	WorldItem item;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		map = new WorldItemMap(5, 5);
		item = new WorldItem(1, 2, null);
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#addItem(tyrelion.objects.WorldItem)}.
	 */
	@Test
	public void testAddItem() {
		map.addItem(item);
		assertEquals(item, map.getFirstItem(1, 2));
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#removeItem(tyrelion.objects.WorldItem)}.
	 */
	@Test
	public void testRemoveItem() {
		map.addItem(item);
		map.removeItem(item);
		assertNotSame(map.getFirstItem(1, 2), item);
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#getItem(int, int)}.
	 */
	@Test
	public void testGetItem() {
		map.addItem(item);
		assertEquals(item, map.getItems()[1][2].get(0));
		assertEquals(item, map.getFirstItem(1, 2));
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(WorldItemMapTest.class);
	}

}
