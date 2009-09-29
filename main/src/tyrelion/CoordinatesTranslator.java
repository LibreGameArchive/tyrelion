/**
 * 
 */
package tyrelion;

import java.awt.Point;

import org.newdawn.slick.SlickException;

import tyrelion.map.TyrelionMap;
import tyrelion.objects.Player;

/**
 * @author jahudi
 *
 */
public class CoordinatesTranslator {
	
	private static CoordinatesTranslator instance = null;
	
	private TyrelionMap map;
	private Player player;
	
	public static CoordinatesTranslator getInstance() {
		if (instance == null) {
			instance = new CoordinatesTranslator();
		}
		return instance;
	}
	
	public Point translateCoordinates(int x, int y) {
		float mouseX;
		float mouseY;
		float targetX;
		float targetY;
		float tempX = x / (new Float(map.getTileSize()));
		float tempY = y / (new Float(map.getTileSize()));
		int offsetX = (int)(((int)tempX - tempX) * map.getTileSize());
		int offsetY = (int)(((int)tempY - tempY) * map.getTileSize());
		mouseX = (x-offsetX) / (new Float(map.getTileSize()));
		mouseY = (y-offsetY) / (new Float(map.getTileSize()));
		
		// absolute in relative Position und Ziel-Tile berechnen
		if (mouseX < player.getTileX()) {
			targetX = player.getTileX() - new Float(map.getLeftOffsetInTiles()) + mouseX;
		} else {
			targetX = player.getTileX() + mouseX - new Float(map.getLeftOffsetInTiles());
		}
		if (mouseY < player.getTileY()) {
			targetY = player.getTileY() - new Float(map.getTopOffsetInTiles()) + mouseY;
		} else {
			targetY = player.getTileY() + mouseY - new Float(map.getTopOffsetInTiles());
		}
		return new Point((int)targetX, (int)targetY);
	}
	
	/**
	 * @param map the map to set
	 */
	public void setMap(TyrelionMap map) {
		this.map = map;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the map
	 */
	public TyrelionMap getMap() {
		return map;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
