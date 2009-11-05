/**
 * 
 */
package tyrelion;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.menu.MenuControls;
import tyrelion.menu.MenuCredits;
import tyrelion.menu.MenuLoad;
import tyrelion.menu.MenuMain;
import tyrelion.menu.MenuSettings;


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
		addState(new PreLoading());
		addState(new MenuMain());
		addState(new MenuLoad());
		addState(new MenuSettings());
		addState(new MenuControls());
		addState(new MenuCredits());
		addState(new ExpMode());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new Main("Tales of Tyrelion"));
			container.setDisplayMode(1152, 864, false);
			container.setShowFPS(false);
				container.setTargetFrameRate(40);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}