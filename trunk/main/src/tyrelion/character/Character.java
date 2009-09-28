/**
 * 
 */
package tyrelion.character;

/**
 * @author imladriel
 *
 */
public class Character {

	private Inventory inventory;
	
	public Character(){
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
