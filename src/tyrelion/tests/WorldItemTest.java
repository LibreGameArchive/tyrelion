/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import tyrelion.InteractionManager;
import tyrelion.objects.WorldItem;

/**
 * @author jahudi
 *
 */
public class WorldItemTest {
	
	private WorldItem item;
	private WorldItem item2;
	private WorldItem item3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item = new WorldItem(3, 5, null);
		item2 = new WorldItem(4, 6, null);
		item3 = new WorldItem(7, 9, null);
	}

	/**
	 * Test method for {@link tyrelion.objects.WorldItem#WorldItem(int, int, tyrelion.itemsystem.Item)}.
	 */
	@Test
	public void testWorldItem() {
		assertEquals(3, item.getTileX());
		assertEquals(5, item.getTileY());
	}

	/**
	 * Test method for {@link tyrelion.objects.WorldObject#WorldObject(int, int)}.
	 */
	@Test
	public void testWorldObject() {
		assertEquals(InteractionManager.getInstance().countObservers(), 6);
	}

	/**
	 * Test method for {@link tyrelion.objects.WorldObject#inRange(tyrelion.objects.WorldObject)}.
	 */
	@Test
	public void testInRange() {
		assertTrue(item.inRange(item2));
		assertFalse(item.inRange(item3));
		assertFalse(item2.inRange(item3));
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(WorldItemTest.class);
	}
	
}
