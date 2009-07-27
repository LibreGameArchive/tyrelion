package lavankor.avatar;

import lavankor.GameMaster;
import lavankor.character.Character;
import lavankor.control.Pathfinder;
import lavankor.map.LavankorMap;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.SlickException;

/**	Diese Klasse dient zum Speichern, Berechnen und Anzeigen jeglicher Informationen,
 * 	die für den Spieler, dessen Charakter und Bewegung notwendig sind. 
 * 	@author Diceware	
 *	@version 0.1a
 */
public class Player extends MoveableAvatar{
	
	/** Die Größe des Spielers in Pixel (Spieler ist size*size Pixel groß). */
	private static final int SIZE = 32;
	/** Die X-Koordinate des Tile, in dem sich der Spieler momentan befindet. */
	private int tileX;
	/** Die X-Koordinate des Tile, in dem sich der Spieler momentan befindet. */
	private int tileY;
	private LavankorMap map;
	private Pathfinder pathfinder;
	/** Der Charakter des Spielers (Content) */
	private Character character;
	private boolean moving = false;
	
	/**	Der Konstruktor legt die HashMap für die Animationen an und ruft initAnimations auf.
	 * @throws SlickException Falls ein Fehler beim erstellen der Animationen auftaucht.
	 */
	public Player(GameMaster gameMaster) {
		map = gameMaster.getMap();
		setCharacter(Character.createDummy());
		setAnimation(RessourceStrings.ANIMATION_WALK);
		soundManager = new SoundManager();
	}
	
	/** Prüft ob eine bestimmte Bewegung des Spielers erlaubt (nicht geblockt) ist
	 * 	@param map Die Map, auf der der Spieler bewegt wird.
	 * 	@return True, falls Bewegung möglich. Sonst False.
	 */
	public boolean tryMove(int delta) {
		float x = dirX * player_move_speed * delta;
		float y = dirY * player_move_speed * delta;
		float newX = posX + x;
		float newY = posY + y;
		
		// Prüfen ob neue Koordinaten blockiert
		if (map.blocked(newX, newY)) {
			if (map.blocked(newX, posY)) {
				if (map.blocked(posX, newY)) {
					// Garkeine Bewegung möglich
					return false;
				} else {
					// X-Bewegung nichtmöglich, Spieler gleitet in Y-Richtung
					posY = newY;				
					return true;
				}
			} else {
				// Y-Bewegung nichtmöglich, Spieler gleitet in Y-Richtung
				posX = newX;
				return true;
			}
		} else {
			// Bewegung möglich, Spielerposition akualisieren
			posX = newX;
			posY = newY;
			return true;
		}
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
	
	public int getSize() {
		return SIZE;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public LavankorMap getMap() {
		return map;
	}

	public void setMap(LavankorMap map) {
		this.map = map;
	}

	public Pathfinder getPathfinder() {
		return pathfinder;
	}

	public void setPathfinder(Pathfinder pathfinder) {
		this.pathfinder = pathfinder;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
}
