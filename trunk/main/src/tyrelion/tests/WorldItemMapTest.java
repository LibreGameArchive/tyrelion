/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
	WorldItem item2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		map = new WorldItemMap(5, 5);
		item = new WorldItem(1, 2, null);
		item2 = new WorldItem(4, 4, null);
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#addItem(tyrelion.objects.WorldItem)}.
	 */
	@Test
	public void testAddItem() {
		map.addItem(item);
		assertEquals(item, map.getItems()[1][2]);
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#removeItem(tyrelion.objects.WorldItem)}.
	 */
	@Test
	public void testRemoveItemWorldItem() {
		map.addItem(item);
		assertEquals(item, map.getItems()[1][2]);
		map.removeItem(item);
		assertNull(map.getItems()[1][2]);
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#removeItem(int, int)}.
	 */
	@Test
	public void testRemoveItemIntInt() {
		map.addItem(item);
		assertEquals(item, map.getItems()[1][2]);
		map.removeItem(1, 2);
		assertNull(map.getItems()[1][2]);
	}

	/**
	 * Test method for {@link tyrelion.map.WorldItemMap#getItem(int, int)}.
	 */
	@Test
	public void testGetItem() {
		map.addItem(item);
		assertEquals(item, map.getItems()[1][2]);
		assertEquals(item, map.getItem(1, 2));
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(WorldItemMapTest.class);
	}

}
