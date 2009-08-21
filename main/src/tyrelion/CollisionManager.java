package tyrelion;

import java.util.ArrayList;

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

	private ArrayList<Shape> tiles;
	private Player player;
	
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
		for (Shape elem : tiles) {
			if (player.getShape().intersects(elem)) {
				collided = true;
				break;
			}
		}
		return collided;
	}
	
	public void update(GameContainer container, int delta) {
		
		Input input = container.getInput();

		float playerX = player.getPlayerX();
		float playerY = player.getPlayerY();
		float newPlayerX;
		float newPlayerY;
		
		if(input.isKeyDown(Keyboard.KEY_LEFT) || input.isControllerLeft(0)) {
			newPlayerX = playerX + -delta * Player.WALK_SPEED;
			if (!collided(newPlayerX, playerY)) {	
				player.setPlayerX(newPlayerX);
				player.setAnimation(Player.ANIM_LEFT);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_RIGHT) || input.isControllerRight(0)) {	
			newPlayerX = playerX + delta * Player.WALK_SPEED;
			if (!collided(newPlayerX, playerY)) {		
				player.setPlayerX(newPlayerX);
				player.setAnimation(Player.ANIM_RIGHT);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_UP) || input.isControllerUp(0)){
			newPlayerY = playerY + -delta * Player.WALK_SPEED;
			if (!collided(playerX, newPlayerY)) {	
				player.setPlayerY(newPlayerY);
				player.setAnimation(Player.ANIM_UP);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_DOWN) || input.isControllerDown(0)){
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
	public void setTiles(ArrayList<Shape> tiles) {
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
	public ArrayList<Shape> getTiles() {
		return tiles;
	}
	
}
