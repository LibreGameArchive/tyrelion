/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import tyrelion.loaders.SoundLoader;


/**
 * @author jahudi
 *
 */
public class SoundLoaderTest {

	SoundLoader loader;
	
	@Before
	public void setUp() {
		loader = new SoundLoader();
		loader.initCategories();
		loader.initSFX();
	}
	
	@Test
	public void testStructure() {	
		assertTrue(loader.getSoundMap().containsKey("player"));
		assertTrue(loader.getSoundMap().containsKey("ambience"));
	}
	
	@Test
	public void testNotEmpty() {
		assertTrue(loader.getSoundMap().get("player").containsKey("walk"));
		assertTrue(loader.getSoundMap().get("ambience").containsKey("thunder"));
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SoundLoaderTest.class);
	}
	
}
