/**
 * 
 */
package tyrelion.music;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.SlickException;

/**
 * This class checks the "music" folder for its contents. It creates a list of categories and a map
 * of the detected tracks ordered by the categories.
 * @author jahudi
 */
public class MusicLoader {
	
	/**
	 * A list of all detected categories (subdirectories of "music"). 
	 */
	private ArrayList<String> categories;
	
	/**
	 * Map of the detected Tracks ordered by the detected categories. Each array of Musics can be
	 * via its category as key.
	 */
	private HashMap<String, ArrayList<TyrelionMusic>> musicMap;

	/**
	 * Creates an new MusicLoader an initializes the categories and music.
	 */
	public MusicLoader() {
		initCategories();
		initMusic();
	}
	
	/**
	 * Creates a list of categories for the game musics. The list is based on the directory structure
	 * of the "music" directory.
	 */
	public void initCategories() {
		File root = new File("res/music");
		File[] files = root.listFiles();
		if (files != null) {
			for (File elem : files) {
				if (elem.isDirectory()) {
					categories = new ArrayList<String>();
					categories.add(elem.getName());
				}
			}
		}
	}
	
	/**
	 * Fills the "musicMap" with all the music files in the "music" directory ordered by categories. The
	 * categories are used as key.
	 */
	public void initMusic() {
		musicMap = new HashMap<String, ArrayList<TyrelionMusic>>();
		for (String elem : categories) {
			musicMap.put(elem, scanMusic(elem));
		}
	}
	
	
	/**
	 * @param category the name of the category to scan for music
	 * @return an ArrayList of TyrelionMusic objects of the specified category
	 */
	public ArrayList<TyrelionMusic> scanMusic(String category) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.endsWith(".ogg");
			}
		};
		File dir = new File("res/music/"+category);
		File[] files = dir.listFiles(filter);
		ArrayList<TyrelionMusic> musics = new ArrayList<TyrelionMusic>();
		for (File elem : files) {
			try {
				musics.add(new TyrelionMusic(elem.getAbsolutePath(), category, true));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return musics;
	}


	/**
	 * @return the musicMap
	 */
	public HashMap<String, ArrayList<TyrelionMusic>> getMusicMap() {
		return musicMap;
	}

	/**
	 * @return the categories
	 */
	public ArrayList<String> getCategories() {
		return categories;
	}
	
}
