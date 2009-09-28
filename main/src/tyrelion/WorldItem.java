/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Graphics;

import tyrelion.itemsystem.Item;

/**
 * @author jahudi
 *
 */
public class WorldItem extends WorldObject {
	
	public static final int SIZE = 48;
	
	private Item item;
	
	public WorldItem(int x, int y, Item item) {
		super(x, y);
		this.item = item;
	}
	
	public void render(Graphics g) {
		item.getImage_world().draw(tileX*TyrelionMap.TILE_SIZE-SIZE/2, tileY*TyrelionMap.TILE_SIZE-SIZE/2);
	}
	
	public Item getItem	() {
		return item;
	}

}
