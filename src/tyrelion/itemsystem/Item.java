/**
 * 
 */
package tyrelion.itemsystem;

import org.newdawn.slick.Image;

/**
 * @author Basti
 *
 */
public abstract class Item {
	
	/** the unique ID of the Item */
	private final int uid;
	
	/** the name of the item */
	private final String name;
	
	/** the image for the item rendered in th world */
	private final Image image_world;
	
	/** the image for the item rendered in the inventory */
	private final Image image_inv;
	
	/** true if item is stackable */
	private final boolean stackable;

	/**
	 * @param uID
	 * @param name
	 * @param imageWorld
	 * @param imageInv
	 */

	public Item(int uid, String name, Image image_world, Image image_inv, boolean stackable) {
		this.uid = uid;
		this.name = name;
		this.image_world = image_world;
		this.image_inv = image_inv;
		this.stackable = stackable;
	}
	
	/**
	 * @return if item is stackable
	 */
	public boolean isStackable(){
		return stackable;
	}
	
	/**
	 * @return the uID
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the image_world
	 */
	public Image getImage_world() {
		return image_world;
	}

	/**
	 * @return the image_inv
	 */
	public Image getImage_inv() {
		return image_inv;
	}
	

}
