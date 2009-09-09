/**
 * 
 */
package tyrelion.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import tyrelion.music.MusicLoader;

/**
 * @author jahudi
 *
 */
public class MusicLoaderTest {

	MusicLoader loader;
	
	@Before
	public void setUp() throws Exception {
		loader = new MusicLoader();
		loader.initCategories();
	}
	
	@Test
	public void testStructure() {
		ArrayList<String> result = null;
		File root = new File("res/music");
		File[] files = root.listFiles();
		if (files != null) {
			result = new ArrayList<String>();
			for (File elem : files) {
				if (elem.isDirectory() && !elem.isHidden()) {
					result.add(elem.getName());
				}
			}
		}
		assertEquals(result, loader.getCategories());		
	}
	
	@Test
	public void testNotEmpty() {
		loader.initMusic();
		assertFalse(loader.getMusicMap().isEmpty());
		assertFalse(loader.getMusicMap().get("menu").isEmpty());
		assertFalse(loader.getMusicMap().get("menu").get(0).equals(null));
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(MusicLoaderTest.class);
	}
	
}
