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
	public Inventory getInventar() {
		return inventory;
	}

	/**
	 * @param inventar the inventar to set
	 */
	public void setInventar(Inventory inventar) {
		this.inventory = inventar;
	}
	
	
	
}
