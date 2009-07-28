/**
 * 
 */
package tyrelion;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.menu.*;


/**
 * @author jahudi
 *
 */
public class Main extends StateBasedGame {

	/**
	 * @param name
	 */
	public Main(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MenuMain());
		addState(new MenuLoad());
		addState(new MenuSettings());
		addState(new MenuControls());
		addState(new MenuCredits());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new Main("Tales of Tyrelion"));
			container.setDisplayMode(1024, 768, false);
			container.setShowFPS(false);
			container.setTargetFrameRate(40);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
