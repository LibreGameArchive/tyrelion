/**
 * 
 */
package tyrelion.itemsystem;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import tyrelion.TyrelionMap;
import tyrelion.WorldObject;

/**
 * @author Basti
 *
 */
public abstract class Item extends WorldObject{

	public static final int SIZE = 48;
	
	/** the unique ID of the Item */
	private final int uid;
	
	/** the name of the item */
	private final String name;
	
	/** the image for the item rendered in th world */
	private final Image image_world;
	
	/** the image for the item rendered in the inventory */
	private final Image image_inv;



	/**
	 * @param uID
	 * @param name
	 * @param imageWorld
	 * @param imageInv
	 */
	public Item(int x, int y, int uid, String name, Image image_world, Image image_inv) {
		super(x, y);
		this.uid = uid;
		this.name = name;
		this.image_world = image_world;
		this.image_inv = image_inv;
	}
	
	public void render(Graphics g) {
		image_world.draw(tileX*TyrelionMap.TILE_SIZE-SIZE/2, tileY*TyrelionMap.TILE_SIZE-SIZE/2);
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
