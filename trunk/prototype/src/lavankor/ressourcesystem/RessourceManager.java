package lavankor.ressourcesystem;

import java.util.HashMap;

import lavankor.dialog.Dialog;
import lavankor.itemsystem.Item;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Sound;

public class RessourceManager {
	
	private static RessourceManager instance = null;

	private HashMap<Integer, Item> itemMap;
	private HashMap<Integer, Dialog> dialogMap;
	private HashMap<String, Animation> animationMap;
	private HashMap<String, Sound> soundMap;
	private HashMap<String, AngelCodeFont> fontMap;
	private MusicManager musicManager;
	
	public RessourceManager() {
		RessourceLoader loader = new RessourceLoader();
		animationMap = loader.loadAnimations();
		itemMap = loader.loadItems();		
		soundMap = loader.loadSounds();
		fontMap = loader.loadFonts();
		musicManager = new MusicManager();
		dialogMap = loader.loadDialogs();
		}
	
	public static RessourceManager getInstance() {
		if (instance == null) {
			instance = new RessourceManager();
		}
		return instance;
	}
	
	public Item getItem(Integer ID) {
		return itemMap.get(ID);
	}
	
	public Dialog getDialog(Integer ID) {
		return dialogMap.get(ID);
	}
	
	public Animation getAnimation(String animation) {
		return animationMap.get(animation);
	}
	
	public Sound getSound(String sound) {
		return soundMap.get(sound);
	}
	
	public AngelCodeFont getFont(String font) {
		return fontMap.get(font);
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}
}
