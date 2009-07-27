package lavankor.avatar;

import lavankor.control.NpcMover;
import lavankor.dialog.Dialog;
import lavankor.map.LavankorMap;
import lavankor.map.NpcMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class NPC extends MoveableAvatar {
	
	private NpcMover npcMover;
	private Dialog dialog;
	private LavankorMap map;
	private String name;
	
	private boolean showName = false;
	
	public NPC(String name, Animation animation, LavankorMap map, Dialog dialog, float posX, float posY, float ang) {
		this.name = name;
		super.animation = animation;
		this.dialog = dialog;
		super.posX = posX;
		super.posY = posY;
		super.ang = ang;
		this.map = map;
		npcMover = new NpcMover(map, this);
		boolean[][] blocked = map.getBlocked();
		blocked [(int)posX-1][(int)posY-1] = true;
		map.setBlocked(blocked);
		super.updateMovementVector();
	}
	
	/** Stellt die Spielfigur an der aktuellen Position mit korrekter Blickrichtung dar.
	 * 	@param g Der Graphics-Kontext, in dem die Figur dargestellt werden soll.
	 */
	public void draw(Graphics g) {
		
		
		// Position von Tile-Koordinaten in globale umrechnen
		int cx = (int) (posX * 32);
		int cy = (int) (posY * 32);
		
		//Namen anzeigen
		if (showName) {
			g.setColor(Color.lightGray);
			g.drawString(name, cx-name.length()*5, cy-50);
		}
		
		// Spieler in die richtige Richtung drehen und darstellen
		g.rotate(cx-16, cy-16, ang);
		animation.draw(cx-32, cy-32);
		g.rotate(cx-16,cy-16,-ang);
	}
	
	public void startDialog(GameContainer container, Graphics g) {
		dialog.draw(container, g);
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
		
		boolean[][] blocked = map.getBlocked();
		NpcMap npcMap = map.getNpcMap();
		
		// Prüfen ob neue Koordinaten blockiert
		if (map.blocked(newX, newY)) {
			if (map.blocked(newX, posY)) {
				if (map.blocked(posX, newY)) {
					// Garkeine Bewegung möglich
					return false;
				} else {
					// X-Bewegung nichtmöglich, Spieler gleitet in Y-Richtung
					blocked[(int)posX-1][(int)posY-1] = false;
					npcMap.remove((int)posX, (int)posY);
					posY = newY;
					blocked[(int)posX-1][(int)newY-1] = true;
					npcMap.add(this, (int)posX, (int)newY);
					map.setBlocked(blocked);
					map.setNpcMap(npcMap);
					return true;
				}
			} else {
				// Y-Bewegung nichtmöglich, Spieler gleitet in Y-Richtung
				blocked[(int)posX-1][(int)posY-1] = false;
				npcMap.remove((int)posX, (int)posY);
				posX = newX;
				blocked[(int)newX-1][(int)posY-1] = true;
				npcMap.add(this, (int)newX, (int)posY);
				map.setBlocked(blocked);
				map.setNpcMap(npcMap);
				return true;
			}
		} else {
			// Bewegung möglich, Spielerposition akualisieren
			blocked[(int)posX-1][(int)posY-1] = false;
			npcMap.remove((int)posX, (int)posY);
			posX = newX;
			posY = newY;
			blocked[(int)newX-1][(int)newY-1] = true;
			npcMap.add(this, (int)newX, (int)newY);
			map.setBlocked(blocked);
			map.setNpcMap(npcMap);
			return true;
		}
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	public NpcMover getNpcMover() {
		return npcMover;
	}

	public void setNpcMover(NpcMover npcMover) {
		this.npcMover = npcMover;
	}
	
	public void moveTo(int x, int y) {
		npcMover.makePath(x, y);
		npcMover.setStepIndex(0);
		npcMover.setMoving(true);
	}

	public String getName() {
		return name;
	}
	
	public void showName(){
		showName = true;
	}
	
	public void hideName(){
		showName = false;
	}
	
}
