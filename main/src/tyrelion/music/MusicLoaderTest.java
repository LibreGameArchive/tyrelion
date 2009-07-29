/**
 * 
 */
package tyrelion.music;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jahudi
 *
 */
public class MusicLoaderTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testMusicLoader() {
		String[] result = { "menu" };
		MusicLoader loader = new MusicLoader();
		loader.initCategories();
		assertArrayEquals(result, loader.getCategories());
		loader.initMusic();
		assertFalse(loader.getMusicMap().isEmpty());
		assertFalse(loader.getMusicMap().get("menu").isEmpty());
		assertFalse(loader.getMusicMap().get("menu").get(0).equals(null));
		
	}
	
}
