package tyrelion;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import tyrelion.map.TyrelionMap;
import tyrelion.objects.Player;

/**
 * @author jahudi
 *
 */
public class CollisionManager {
	
	private static CollisionManager instance = null;

	private Shape[][] tiles;
	private Player player;
	private TyrelionMap map;

	public static CollisionManager getInstance() {
		if (instance == null) {
			instance = new CollisionManager();
		}
		return instance;
	}
	
	public boolean collided(float x, float y) {
		player.getShape().setCenterX(x*48);
		player.getShape().setCenterY(y*48+10);
		boolean collided = false;
		int tileX;
		int tileY;
		
		if (playerOutOfMap(x, y)) {
			return true;
		}
		
		for (int i = player.getTileX() - 1; i <= player.getTileX() + 1; i++) {
			for (int j = player.getTileY() - 1; j <= player.getTileY() + 1; j++) {				
				
				if (i < 0) {
					tileX = 0;
				} else if (i >= map.getWidth()) {
					tileX = map.getWidth()-1;
				} else {
					tileX = i;
				}
				
				if (j < 0) {
					tileY = 0;
				} else if (j >= map.getHeight()) {
					tileY = map.getHeight()-1;
				} else {
					tileY = j;
				}
				
				if (tiles[tileX][tileY] != null) {
					if (player.getShape().intersects(tiles[tileX][tileY])) {
						collided = true;
						break;
					}
				}
				if (map.getNpcs().getNpc(tileX, tileY) != null) {
					if (player.getShape().intersects(map.getNpcs().getNpc(tileX, tileY).getShape())) {
						collided = true;
						break;
					}
				}
			}
		}
		return collided;
	}
	
	public boolean playerOutOfMap(float x, float y) {
		if (x + 0.5 < 0 || y + 0.5 < 0 || x > 63 || y + 0.3 > 63) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Shape[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * @return the tiles
	 */
	public Shape[][] getTiles() {
		return tiles;
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
	
}
