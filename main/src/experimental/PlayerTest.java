/**
 * 
 */
package experimental;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.slickset.AnimatedActor;
import com.slickset.collision.DynamicShape;

/**
 * @author jahudi
 *
 */
public class PlayerTest extends AnimatedActor {

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 */
	public PlayerTest(Animation[] animations, float x, float y,
			DynamicShape shape, float mass, boolean staticBody) {
		super(animations, x, y, shape, mass, staticBody);
		setAnimation(0);
	}

	/* (non-Javadoc)
	 * @see com.slickset.AnimatedActor#selectAnimation(int, int, int, int)
	 */
	@Override
	protected void selectAnimation(int oldStatus, int newStatus, int oldDirection, int newDirection) {
		setAnimation(0);
	}
	
	public void render(Graphics g, float x, float y) {
		super.render(g, x, y);
	}
	
	public void update(StateBasedGame game, int delta) {
		super.update(game, delta);
	}

}
