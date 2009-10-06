/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import tyrelion.sfx.SoundManager;

/**
 * @author jahudi
 *
 */
public class SoundManagerTest {

	private SoundManager manager;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = SoundManager.getInstance();
	}

	/**
	 * Test method for {@link tyrelion.sfx.SoundManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(SoundManager.getInstance());
		assertEquals(SoundManager.getInstance(), manager);
	}

	/**
	 * Test method for {@link tyrelion.sfx.SoundManager#playOnce(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testPlayOnce() {
		manager.setVolume(0f);
		manager.playOnce("ambience", "thunder", 1f, 1f);
		assertTrue(manager.getPlayOnce().playing());
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SoundManagerTest.class);
	}
	
}
