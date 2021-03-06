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

import tyrelion.music.MusicManager;

/**
 * @author jahudi
 *
 */
public class MusicManagerTest {

	private MusicManager manager;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = MusicManager.getInstance();
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(MusicManager.getInstance());
		assertEquals(MusicManager.getInstance(), manager);
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#pickRandom(java.lang.String)}.
	 */
	@Test
	public void testPickRandom() {
		assertNotNull(MusicManager.getInstance().pickRandom("menu"));
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#play(java.lang.String)}.
	 */
	@Test
	public void testPlay() {
		manager.setVolume(0f);
		manager.play("menu");
		assertTrue(manager.getActiveTrack().playing());
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#loop(java.lang.String)}.
	 */
	@Test
	public void testLoop() {
		manager.setVolume(0f);
		manager.loop("menu");
		assertTrue(manager.getActiveTrack().playing());
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#setVolume(java.lang.Float)}.
	 */
	@Test
	public void testSetVolume() {
		float volume = 0f;
		manager.play("menu");
		manager.setVolume(volume);
		assertEquals(volume, manager.getVolume(), 0);
		assertEquals(volume, manager.getActiveTrack().getVolume(), 0);
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(MusicManagerTest.class);
	}

}
