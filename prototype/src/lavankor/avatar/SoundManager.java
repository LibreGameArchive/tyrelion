package lavankor.avatar;

import java.util.HashMap;

import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.Sound;

public class SoundManager {

	private HashMap<String, Sound> soundMap;
	
	public SoundManager() {
		soundMap = new HashMap<String, Sound>();
		soundMap.put("walk", RessourceManager.getInstance().getSound(RessourceStrings.SOUND_WALK));
		soundMap.put("run", RessourceManager.getInstance().getSound(RessourceStrings.SOUND_RUN));
		soundMap.put("drink", RessourceManager.getInstance().getSound(RessourceStrings.SOUND_DRINK));
	}
	
	public boolean isPlaying() {
		boolean playing = false;
		for (Sound elem:soundMap.values()) {
			if (elem.playing()) {
				playing = true;
			}
		}
		return playing;
	}
	
	public void play(String sound) {
		soundMap.get(sound).loop(1f, 0.4f);
	}
	
	public void stop() {
		for (Sound elem:soundMap.values()) {
			if (elem.playing()) {
				elem.stop();
			}
		}
	}
	
}
