/**
 * 
 */
package tyrelion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import tyrelion.map.TyrelionMap;

/**
 * @author jahudi
 *
 */
public class TyrelionContainer {
	
	private static TyrelionContainer instance = null;
	
	private GameContainer container;
	private TyrelionMap map;
	
	public static TyrelionContainer getInstance() {
		if (instance == null) {
			instance = new TyrelionContainer();
		}
		return instance;
	}

	/**
	 * @return the container
	 */
	public GameContainer getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(GameContainer container) {
		this.container = container;
	}

	/**
	 * @return the map
	 */
	public TyrelionMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(TyrelionMap map) {
		this.map = map;
	}

}
