/**
 * 
 */
package tyrelion.sfx;

import java.util.HashMap;

import org.newdawn.slick.Sound;

import tyrelion.loaders.SoundLoader;

/**
 * @author jahudi
 *
 */
public class SoundManager {

	private static SoundManager instance;
	
	private float volume = 1f;
	private float pitch = 1f;
	
	private SoundLoader loader;
	
	private Sound playOnce;
	
	private HashMap<String, HashMap<String, Sound>> soundMap;
	
	public SoundManager() {
		loader = new SoundLoader();
		soundMap = loader.getSoundMap();
	}
	
	public static SoundManager getInstance() {
		if (instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}
	
	public HashMap<String, Sound> getSounds(String category) {
		return soundMap.get(category);
	}
	
	public Sound getSFX(String category, String name) {
		return soundMap.get(category).get(name);
	}
	
	public void play(String category, String name) {
		getSFX(category, name).play(pitch, volume);
	}
	
	public void playOnce(String category, String name, float vol, float pit) {
		if (playOnce == null || !playOnce.playing()) {
			playOnce = getSFX(category, name);
			playOnce.play(pit, vol);
		}		
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}

	/**
	 * @param pitch the pitch to set
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * @return the pitch
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * @return the playOnce
	 */
	public Sound getPlayOnce() {
		return playOnce;
	}
	
}
