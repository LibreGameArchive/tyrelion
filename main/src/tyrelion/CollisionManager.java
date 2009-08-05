/**
 * 
 */
package tyrelion;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

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
		Circle circle = new Circle(x*48, y*48, 24);
		boolean collided = false;
		for (Shape elem : tiles) {
			if (circle.intersects(elem)) {
				collided = true;
				break;
			}
		}
		return collided;
	}
	
	public void update(StateBasedGame game, int delta) {
		
		Circle circle = player.getCircle();
		
		Input input = game.getContainer().getInput();
		
		float playerX = player.getPlayerX();
		float playerY = player.getPlayerY();
		
		if (!collided(playerX + -delta * Player.WALK_SPEED, playerY)) {
		if(input.isKeyDown(Keyboard.KEY_LEFT) || input.isControllerLeft(0)) {	
			player.setPlayerX(playerX + -delta * Player.WALK_SPEED);
			circle.setCenterX(playerX*48);
			player.setAnimation(Player.ANIM_LEFT);
		}}
		  
		if (!collided(playerX + delta * Player.WALK_SPEED, playerY)) {
		if(input.isKeyDown(Keyboard.KEY_RIGHT) || input.isControllerRight(0)) {			
			player.setPlayerX(player.getPlayerX() + delta * Player.WALK_SPEED);
			circle.setCenterX(player.getPlayerX()*48);
			player.setAnimation(Player.ANIM_RIGHT);
		}}
		  
		if (!collided(playerX, playerY + -delta * Player.WALK_SPEED)) {
		if(input.isKeyDown(Keyboard.KEY_UP) || input.isControllerUp(0)){
			player.setPlayerY(player.getPlayerY() + -delta * Player.WALK_SPEED);
			circle.setCenterY(player.getPlayerY()*48);
			player.setAnimation(Player.ANIM_UP);
		}}
		  
		if (!collided(playerX, playerY + delta * Player.WALK_SPEED)) {
		if(input.isKeyDown(Keyboard.KEY_DOWN) || input.isControllerDown(0)){
			player.setPlayerY(player.getPlayerY() + delta * Player.WALK_SPEED);
			circle.setCenterY(player.getPlayerY()*48);
			player.setAnimation(Player.ANIM_DOWN);
		}}
		
		player.update();
		
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
