/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

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
		manager = new MusicManager();
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(manager.getInstance());
		assertEquals(manager.getInstance(), manager.getInstance());
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#pickRandom(java.lang.String)}.
	 */
	@Test
	public void testPickRandom() {
		assertNotNull(manager.getInstance().pickRandom("menu"));
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#play(java.lang.String)}.
	 */
	@Test
	public void testPlay() {
		manager.play("menu");
		assertTrue(manager.getActiveTrack().playing());
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#loop(java.lang.String)}.
	 */
	@Test
	public void testLoop() {
		manager.loop("menu");
		assertTrue(manager.getActiveTrack().playing());
	}

	/**
	 * Test method for {@link tyrelion.music.MusicManager#setVolume(java.lang.Float)}.
	 */
	@Test
	public void testSetVolume() {
		float volume = 0.7f;
		manager.play("menu");
		manager.setVolume(volume);
		assertEquals(volume, manager.getVolume(), 0);
		assertEquals(volume, manager.getActiveTrack().getVolume(), 0);
	}

}
