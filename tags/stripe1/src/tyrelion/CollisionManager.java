package tyrelion;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

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
		player.getShape().setCenterX(x*48+10);
		player.getShape().setCenterY(y*48+20);
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
	
	public void update(GameContainer container, int delta) {
		
		Input input = container.getInput();

		float playerX = player.getPlayerX();
		float playerY = player.getPlayerY();
		float newPlayerX;
		float newPlayerY;
		
		if(input.isKeyDown(Keyboard.KEY_A) || input.isControllerLeft(0)) {
			newPlayerX = playerX + -delta * Player.WALK_SPEED;
			if (!collided(newPlayerX, playerY)) {	
				player.setPlayerX(newPlayerX);
				player.setAnimation(Player.ANIM_LEFT);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_D) || input.isControllerRight(0)) {	
			newPlayerX = playerX + delta * Player.WALK_SPEED;
			if (!collided(newPlayerX, playerY)) {		
				player.setPlayerX(newPlayerX);
				player.setAnimation(Player.ANIM_RIGHT);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_W) || input.isControllerUp(0)){
			newPlayerY = playerY + -delta * Player.WALK_SPEED;
			if (!collided(playerX, newPlayerY)) {	
				player.setPlayerY(newPlayerY);
				player.setAnimation(Player.ANIM_UP);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_S) || input.isControllerDown(0)){
			newPlayerY = playerY + delta * Player.WALK_SPEED;
			if (!collided(playerX, newPlayerY)) {
				player.setPlayerY(newPlayerY);
				player.setAnimation(Player.ANIM_DOWN);
			}
		}
		
		player.update(container);
		
	}
	
	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Shape[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
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
	
}
