/**
 * 
 */
package tyrelion.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Music;

/**
 * @author jahudi
 *
 */
public class MusicManager {

	private static MusicManager instance = null;
	
	private MusicLoader loader;
	private HashMap<String, ArrayList<Music>> musicMap;
	
	public MusicManager() {
		loader = new MusicLoader();
		musicMap = loader.getMusicMap();
	}
	
	public static MusicManager getInstance() {
		if (instance == null) {
			return new MusicManager();
		} else {
			return instance;
		}
	}
	
	public Music pickRandom(String category) {
		Random r = new Random();
		ArrayList<Music> selectedCategory = musicMap.get(category);
		return selectedCategory.get(r.nextInt(selectedCategory.size()));
	}
	
	public void play(String category) {
		Music title = pickRandom(category);
		title.play();
	}
	
}
