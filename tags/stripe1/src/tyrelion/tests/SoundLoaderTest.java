/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tyrelion.sfx.SoundLoader;


/**
 * @author jahudi
 *
 */
public class SoundLoaderTest {

	@Test
	public void testSoundLoader() {
		SoundLoader loader = new SoundLoader();
		loader.initCategories();
		loader.initSFX();
		assertTrue(loader.getSoundMap().containsKey("player"));
		assertTrue(loader.getSoundMap().containsKey("ambience"));
		assertTrue(loader.getSoundMap().get("player").containsKey("walk"));
		assertTrue(loader.getSoundMap().get("ambience").containsKey("thunder"));
	}
	
}
