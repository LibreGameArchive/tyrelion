/**
 * 
 */
package tyrelion.sfx;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * @author jahudi
 *
 */
public class SoundLoader {

	private ArrayList<String> categories;
	
	private HashMap<String, HashMap<String, Sound>> soundMap;
	
	public SoundLoader() {
		initCategories();
		initSFX();
	}
	
	public void initCategories() {
		File root = new File("res/sfx");
		File[] files = root.listFiles();
		if (files != null) {
			categories = new ArrayList<String>();
			for (File elem : files) {
				if (elem.isDirectory() && !elem.isHidden()) {
					categories.add(elem.getName());
				}
			}
		}
	}
	
	public HashMap<String, Sound> scanSFX(String category) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.endsWith(".ogg");
			}
		};
		File dir = new File("res/sfx/"+category+"/");
		File[] files = dir.listFiles(filter);
		HashMap<String, Sound> sfxMap = new HashMap<String, Sound>();
		for (File elem : files) {
			try {
				String name = elem.getName().replaceFirst(".ogg", "");
				sfxMap.put(name, new Sound(elem.getAbsolutePath()));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sfxMap;
	}
	
	public void initSFX() {
		soundMap = new HashMap<String, HashMap<String, Sound>>();
		for (String elem : categories) {
			soundMap.put(elem, scanSFX(elem));
		}
	}

	/**
	 * @return the soundMap
	 */
	public HashMap<String, HashMap<String, Sound>> getSoundMap() {
		return soundMap;
	}

	/**
	 * @return the categories
	 */
	public ArrayList<String> getCategories() {
		return categories;
	}
	
}
