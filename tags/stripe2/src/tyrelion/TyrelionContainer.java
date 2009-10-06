/**
 * 
 */
package tyrelion;

import org.newdawn.slick.GameContainer;

import tyrelion.gui.Infobox;
import tyrelion.map.TyrelionMap;

/**
 * @author jahudi
 *
 */
public class TyrelionContainer {
	
	private static TyrelionContainer instance = null;
	
	private GameContainer container;
	private TyrelionMap map;
	private Infobox infobox;
	
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

	/**
	 * @return the infobox
	 */
	public Infobox getInfobox() {
		return infobox;
	}

	/**
	 * @param infobox the infobox to set
	 */
	public void setInfobox(Infobox infobox) {
		this.infobox = infobox;
	}

}
