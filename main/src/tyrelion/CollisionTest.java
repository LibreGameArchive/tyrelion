/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jahudi
 *
 */
public class CollisionTest extends BasicGameState {

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	Circle circle = new Circle(24, 24, 24);
	Rectangle rect = new Rectangle(240, 240, 48, 48);

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.red);
		g.draw(circle);
		g.draw(rect);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if (!circle.intersects(rect)) {
				circle.setX(circle.getX() + 0.1f * delta);
			} else {
				circle.setX(circle.getX()-3);
			}
		}
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			if (!circle.intersects(rect)) {
				circle.setX(circle.getX() - 0.1f * delta);
			} else {
				circle.setX(circle.getX()+3);
			}
		}
		
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			if (!circle.intersects(rect)) {
				circle.setY(circle.getY() + 0.1f * delta);
			} else {
				circle.setY(circle.getY()-3);
			}
		}
		
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			if (!circle.intersects(rect)) {
				circle.setY(circle.getY() - 0.1f * delta);
			} else {
				circle.setY(circle.getY()+3);
			}
		}

	}

}
