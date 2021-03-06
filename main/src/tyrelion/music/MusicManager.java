/**
 * 
 */
package tyrelion.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import tyrelion.loaders.MusicLoader;

/**
 * The MusicManager controls music playback, looping and the playback volume.
 * @author jahudi
 */
public class MusicManager {

	private static MusicManager instance = null;
	
	/**
	 * The MusicLoader used to initialize the available tracks and categories.
	 */
	private MusicLoader loader;
	
	/**
	 * Map of the detected Tracks ordered by the detected categories. Each array of Musics can be
	 * via its category as key.
	 */
	private HashMap<String, ArrayList<TyrelionMusic>> musicMap;
	
	/**
	 * The currently active track. Not neccessarily currently playing.
	 */
	private TyrelionMusic activeTrack;
	
	/**
	 * The volume represented by a float value between 0 and 1.
	 */
	private float volume = 0.5f;
	
	/**
	 * Creates a MusicManager object, calls the MusicLoader and initializes the musicMap and volume.
	 */
	public MusicManager() {
		loader = new MusicLoader();
		musicMap = loader.getMusicMap();
	}
	
	/**
	 * This method ensures, that there always will be just one object of MusicManager as long as this method is
	 * used to create them.
	 * @return The one and only instance of the MusicManager.
	 */
	public static MusicManager getInstance() {
		if (instance == null) {
			instance = new MusicManager();
		}
		return instance;
	}
	
	/**
	 * Chooses one randomly specified track from a given category.
	 * @param category The category to choose a track from.
	 * @return A random track of the given category as a TyrelionMusic object.
	 */
	public TyrelionMusic pickRandom(String category) {
		Random r = new Random();
		ArrayList<TyrelionMusic> selectedCategory = musicMap.get(category);
		return selectedCategory.get(r.nextInt(selectedCategory.size()));
	}
	
	/**
	 * Plays a random track from a given category. Playback will stop after this track ends.
	 * @param category The category to play a track from.
	 */
	public void play(String category) {
		if (activeTrack != null && activeTrack.playing()) {
			activeTrack.stop();
		}
		activeTrack = pickRandom(category);
		activeTrack.play();
		activeTrack.setVolume(volume);
	}
	
	/**
	 * Plays a random track from a given category. After the track ends a MusicListener will choose the next (random)
	 * track of the played category and start playback again.
	 * @param category The category to loop.
	 */
	public void loop(String category) {
		activeTrack = null;
		activeTrack = pickRandom(category);
		activeTrack.addListener(new MusicListener());
		activeTrack.play();
		activeTrack.setVolume(volume);
	}
	
	/**
	 * Sets the volume variable and if there is a activeTrack it also sets its volume.
	 * @param volume The desired volume as a float between 0 (mute) and 1 (max).
	 */
	public void setVolume(Float volume) {
		this.volume = volume;
		if (activeTrack != null) {
			activeTrack.setVolume(volume);
		}
	}

	/**
	 * @return the activeTrack
	 */
	public TyrelionMusic getActiveTrack() {
		return activeTrack;
	}

	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}
		
}
