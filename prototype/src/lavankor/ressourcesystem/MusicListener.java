package lavankor.ressourcesystem;

import java.util.Observable;
import java.util.Observer;

public class MusicListener implements Observer {

	public void update(Observable o, Object arg) {
		if (!((MusicManager) o).getActive().playing()) {
			String cat = (String) arg;
			if (cat.equals("nav_normal")) {
				((MusicManager) o).playNavNormal();
			}
			if (cat.equals("nav_tension")) {
				((MusicManager) o).playNavTension();
			}
			if (cat.equals("fight")) {
				((MusicManager) o).playFight();
			}
			if (cat.equals("tavern")) {
				((MusicManager) o).playTavern();
			}
		}
	}
}
