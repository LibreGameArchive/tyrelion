package tyrelion.map;
import org.newdawn.slick.Graphics;

import tyrelion.objects.WorldItem;


/**
 * 
 */

/**
 * @author jahudi
 *
 */
public class WorldItemMap {

	private WorldItem[][] items;
	
	public WorldItemMap(int x, int y) {
		items = new WorldItem[x][y];
	}
	
	public void addItem(WorldItem item) {
		items[item.getTileX()][item.getTileY()] = item;
	}
	
	public void removeItem(WorldItem item) {
		items[item.getTileX()][item.getTileY()] = null;
	}
	
	public void removeItem(int x, int y) {
		items[x][y] = null;
	}
	
	public WorldItem getItem(int x, int y) {
		return items[x][y];
	}
	
	public void drawItems(int startX, int startY, int endX, int endY, Graphics g) {
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (getItem(i, j) != null) {
					getItem(i, j).render(g);
				}
			}
		}
	}

	/**
	 * @return the items
	 */
	public WorldItem[][] getItems() {
		return items;
	}
}
