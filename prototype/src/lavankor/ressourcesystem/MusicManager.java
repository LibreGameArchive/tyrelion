package lavankor.ressourcesystem;

import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

import org.newdawn.slick.Music;

public class MusicManager extends Observable {

	private Music active;
	private String activeCat;
	private Music menu;
	private Music credits;
	private HashMap<Integer, Music> nav_normal;
	private HashMap<Integer, Music> nav_tension;
	private HashMap<Integer, Music> fight;
	private HashMap<Integer, Music> tavern;
	
	public MusicManager() {
		RessourceLoader loader = new RessourceLoader();
		try {
			menu = loader.loadMusicMenu();
			credits = loader.loadMusicCredits();
			nav_normal = loader.loadMusicNavNormal();
			nav_tension = loader.loadMusicNavTension();
			fight = loader.loadMusicFight();
			tavern = loader.loadMusicTavern();
		} catch (Exception e) {
			e.printStackTrace();
		}
		addObserver(new MusicListener());
	}
	
	public Music getActive() {
		return active;
	}
	
	public void notifyListener() {
		setChanged();
		notifyObservers(activeCat);
	}
	
	public void playMenu() {
		try {
			active = menu;
			active.loop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playCredits() {
		try {
			active = credits;
			active.loop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playNavNormal() {
		try {
			Random r = new Random();
			active = nav_normal.get(r.nextInt(nav_normal.size()));
			activeCat = "nav_normal";
			active.play();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void playNavTension() {
		try {
			Random r = new Random();
			active = nav_tension.get(r.nextInt(nav_tension.size()));
			activeCat = "nav_tension";
			active.play();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public void playFight() {
		try {
			Random r = new Random();
			active = fight.get(r.nextInt(fight.size()));
			activeCat = "fight";
			active.play();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void playTavern() {
		try {
			Random r = new Random();
			active = tavern.get(r.nextInt(tavern.size()));
			activeCat = "tavern";
			active.play();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
