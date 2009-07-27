package lavankor.avatar;

import java.util.Observable;

import lavankor.ressourcesystem.RessourceManager;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public abstract class MoveableAvatar extends Observable{
	
	/** Die aktuelle X-Position des Spielers in Tiles. */
	protected float posX = 0;
	/** Die aktuelle Y-Position des Spielers in Tiles. */
	protected float posY = 0;
	/** Die Richtung, in die der Spieler schaut (in Grad!). */
	protected float ang;
	/** Die Geschwindigkeit, mit der sich der Spieler bewegen kann. */
	protected float player_move_speed = 0.003f;
	/** Die Geschwindigkeit, mit der sich der Spieler um die eigene Achse drehen kann. */
	protected static final float PLAYER_ROTATE_SPEED = 0.2f;
	/** Die X-Komponente des Bewegungsvektors des Spielers. */
	protected float dirX;
	/** Die Y-Komponente des Bewegungsvektors des Spielers. */
	protected float dirY;
	/** Die momentan aktive Animation. */
	protected Animation animation;
	
	protected SoundManager soundManager;
	
	public abstract boolean tryMove(int delta);
	
	/** Stellt die Spielfigur an der aktuellen Position mit korrekter Blickrichtung dar.
	 * 	@param g Der Graphics-Kontext, in dem die Figur dargestellt werden soll.
	 */
	public void draw(Graphics g) {
		// Position von Tile-Koordinaten in globale umrechnen
		int cx = (int) (posX * 32);
		int cy = (int) (posY * 32);
		
		// Spieler in die richtige Richtung drehen und darstellen
		g.rotate(cx, cy, ang);
		animation.draw(cx-16, cy-16);
		g.rotate(cx,cy,-ang);
	}
	
	public void updateMovementVector() {
		/* Den Winkel ins Bogenmaß umrechnen und mit Sinus- bzw. Kosinusfunktion den X- bzw. Y-Anteil
		 * des Vektors berechnen */
		dirX = (float) Math.sin(Math.toRadians(ang));
		dirY = (float) -Math.cos(Math.toRadians(ang));
	}
	
	/** Dreht die Spielfigur nach links.
	 */
	public void turnLeft(int delta) {
		if (ang > 0) {
			// Winkel reduzieren
			ang = (ang - delta * PLAYER_ROTATE_SPEED) % 360;
		} else {
			ang = 360;
		}
		updateMovementVector();
	}
	
	/** Dreht die Spielfigur nach rechts.
	 */
	public void turnRight(int delta) {
		// Winkel erhöhen
		ang = (ang + delta * PLAYER_ROTATE_SPEED) % 360;
		updateMovementVector();
	}
	
	/** Bewegt die Spielfigur vorwärts (falls möglich)
	 * 	@param map Die Map, auf der der Spieler bewegt wird.
	 */
	public void moveForward(int delta) {
		// Prüfen ob Bewegung möglich; wenn ja: Ausführen und Animation updaten
		if (tryMove(delta)) {
			animation.update(delta);
		}
	}
	
	/** Bewegt die Spielfigur rückwärts (falls möglich)
	 * 	@param map Die Map, auf der der Spieler bewegt wird.
	 */
	public void moveBackward(int delta) {
		// Prüfen ob Bewegung möglich; wenn ja: Ausführen und Animation updaten
		if (tryMove(-delta)) {
			animation.update(delta);
		}
	}
	
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getAng() {
		return ang;
	}

	public void setAng(float ang) {
		this.ang = ang;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(String string) {
		animation = RessourceManager.getInstance().getAnimation(string);
	}

	public float getDirX() {
		return dirX;
	}

	public void setDirX(float dirX) {
		this.dirX = dirX;
	}

	public float getDirY() {
		return dirY;
	}

	public void setDirY(float dirY) {
		this.dirY = dirY;
	}

	public float getMove_speed() {
		return player_move_speed;
	}

	public void setMove_speed(float player_move_speed) {
		this.player_move_speed = player_move_speed;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}
	
}
