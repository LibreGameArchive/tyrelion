/**
 * 
 */
package tyrelion.itemsystem;

import org.newdawn.slick.Image;

/**
 * @author imladriel
 *
 */
public class Food extends Item {

	/**
	 * @param uid
	 * @param name
	 * @param imageWorld
	 * @param imageInv
	 */

	public Food(int x, int y, int uid, String name, Image imageWorld, Image imageInv, boolean stackable) {
		super(x, y, uid, name, imageWorld, imageInv, stackable);
	}

}
