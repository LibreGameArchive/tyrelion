/**
 * 
 */
package experimental;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import com.slickset.AnimatedActor;
import com.slickset.collision.DynamicShape;

/**
 * @author jahudi
 *
 */
public class PlayerTest extends AnimatedActor {

	public final static int ANIM_UP = 0;
	public final static int ANIM_DOWN = 1;
	public final static int ANIM_LEFT = 2;
	public final static int ANIM_RIGHT = 3;
	
	private float WALK_SPEED = 0.003f;
	
	private float playerX;
	private float playerY;
	
	public PlayerTest(Animation[] animations, float x, float y,
			DynamicShape shape, float mass, boolean staticBody) {
		super(animations, x, y, shape, mass, staticBody);
		setAnimation(ANIM_UP);
		playerX = (x)/48;
		playerY = (y)/48;
	}

	/* (non-Javadoc)
	 * @see com.slickset.AnimatedActor#selectAnimation(int, int, int, int)
	 */
	@Override
	protected void selectAnimation(int oldStatus, int newStatus, int oldDirection, int newDirection) {
		switch(newDirection)
	      {
	         case UP:
	            setAnimation(ANIM_UP);
	            break;
	            
	         case DOWN:
	            setAnimation(ANIM_DOWN);
	            break;
	            
	         case LEFT:
	            setAnimation(ANIM_LEFT);
	            break;
	            
	         case RIGHT:
	            setAnimation(ANIM_RIGHT);
	            break;   
	      }
	}
	
	public void render(Graphics g, float x, float y) {
		super.render(g, x, y);
	}
	
	public void update(StateBasedGame game, int delta) {
		
		//setXVelocity(0);
		//setYVelocity(0);
		  
		Input input = game.getContainer().getInput();
		  
		if(input.isKeyDown(Keyboard.KEY_LEFT) || input.isControllerLeft(0)) {	
			playerX += -delta * WALK_SPEED;
			//setXVelocity(-WALK_SPEED);
			setDirection(LEFT);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_RIGHT) || input.isControllerRight(0)) {
			playerX += delta * WALK_SPEED;
			//setXVelocity(WALK_SPEED);
			setDirection(RIGHT);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_UP) || input.isControllerUp(0)){
			playerY += -delta * WALK_SPEED;
			//setYVelocity(-WALK_SPEED);
			setDirection(UP);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_DOWN) || input.isControllerDown(0)){
			playerY += delta * WALK_SPEED;
			//setYVelocity(WALK_SPEED);
			setDirection(DOWN);
		} 
		
		super.update(game, delta);
	}

	/**
	 * @param playerX the playerX to set
	 */
	public void setPlayerX(float playerX) {
		this.playerX = playerX;
	}

	/**
	 * @return the playerX
	 */
	public float getPlayerX() {
		return playerX;
	}

	/**
	 * @param playerY the playerY to set
	 */
	public void setPlayerY(float playerY) {
		this.playerY = playerY;
	}

	/**
	 * @return the playerY
	 */
	public float getPlayerY() {
		return playerY;
	}

}
