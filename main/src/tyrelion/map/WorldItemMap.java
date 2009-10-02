package tyrelion.map;
import java.util.ArrayList;

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

	private ArrayList<WorldItem>[][] items;
	
	@SuppressWarnings("unchecked")
	public WorldItemMap(int x, int y) {
		items = new ArrayList[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				items[i][j] = new ArrayList<WorldItem>(5);
			}
		}
	}
	
	public void addItem(WorldItem item) {
		items[item.getTileX()][item.getTileY()].add(0, item);
	}
	
	public void removeItem(WorldItem item) {
		items[item.getTileX()][item.getTileY()].remove(item);
	}
	
	public ArrayList<WorldItem> getItems(int x, int y) {
		return items[x][y];
	}
	
	public WorldItem getFirstItem(int x, int y) {
		ArrayList<WorldItem> list = getItems(x, y);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}

	}
	
	public void drawItems(int startX, int startY, int endX, int endY, Graphics g) {
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (getFirstItem(i, j) != null) {
					getFirstItem(i, j).render(g);
				}
			}
		}
	}

	/**
	 * @return the items
	 */
	public ArrayList<WorldItem>[][] getItems() {
		return items;
	}
}
