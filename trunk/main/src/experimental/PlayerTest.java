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
	
	private float WALK_SPEED = 2.5f;
	
	public PlayerTest(Animation[] animations, float x, float y,
			DynamicShape shape, float mass, boolean staticBody) {
		super(animations, x, y, shape, mass, staticBody);
		setAnimation(ANIM_UP);
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
		
		setXVelocity(0);
		setYVelocity(0);
		  
		Input input = game.getContainer().getInput();
		  
		if(input.isKeyDown(Keyboard.KEY_LEFT) || input.isControllerLeft(0))
		{
		   setXVelocity(-WALK_SPEED);
		   setDirection(LEFT);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_RIGHT) || input.isControllerRight(0))
		{
		   setXVelocity(WALK_SPEED);
		   setDirection(RIGHT);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_UP) || input.isControllerUp(0))
		{
		   setYVelocity(-WALK_SPEED);
		   setDirection(UP);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_DOWN) || input.isControllerDown(0))
		{
		   setYVelocity(WALK_SPEED);
		   setDirection(DOWN);
		} 
		
		super.update(game, delta);
	}

}
