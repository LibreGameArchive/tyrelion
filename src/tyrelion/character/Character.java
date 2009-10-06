/**
 * 
 */
package tyrelion.character;

import org.newdawn.slick.SlickException;

/**
 * @author imladriel
 *
 */
public class Character {

	private Inventory inventory;
	
	public Character() throws SlickException{
		inventory = new Inventory();
	}
	
	/**
	 * @return the inventar
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @param inventar the inventar to set
	 */
	public void setInventory(Inventory inventar) {
		this.inventory = inventar;
	}
	
	
	
}
