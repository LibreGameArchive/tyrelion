/**
 * 
 */
package tyrelion.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author jahudi
 *
 */
public class MusicManager {

	private static MusicManager instance = null;
	
	private MusicLoader loader;
	private HashMap<String, ArrayList<TyrelionMusic>> musicMap;
	private TyrelionMusic activeTrack;
	private float volume;
	
	public MusicManager() {
		loader = new MusicLoader();
		musicMap = loader.getMusicMap();
		volume = 1.0f;
	}
	
	public static MusicManager getInstance() {
		if (instance == null) {
			return new MusicManager();
		} else {
			return instance;
		}
	}
	
	public TyrelionMusic pickRandom(String category) {
		Random r = new Random();
		ArrayList<TyrelionMusic> selectedCategory = musicMap.get(category);
		return selectedCategory.get(r.nextInt(selectedCategory.size()));
	}
	
	public void play(String category) {
		if (activeTrack != null && activeTrack.playing()) {
			activeTrack.stop();
		}
		activeTrack = pickRandom(category);
		activeTrack.setVolume(volume);
		activeTrack.play();
	}
	
	public void loop(String category) {
		if (activeTrack != null && activeTrack.playing()) {
			activeTrack.stop();
		}
		activeTrack = pickRandom(category);
		activeTrack.addListener(new MusicListener());
		activeTrack.setVolume(volume);
		activeTrack.play();
	}
	
	public void setVolume(Float volume) {
		this.volume = volume;
		if (activeTrack != null) {
			activeTrack.setVolume(volume);
		}
	}
		
}
