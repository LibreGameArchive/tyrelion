package lavankor.map;

import lavankor.itemsystem.Item;

public class ItemMap extends LayerMap<Item>{

	public ItemMap(int x, int y) {
		lengthX = x;
		lengthY = y;
		matrix = new Item[x][y];
	}
	
	public void add(Item item, int x, int y) {
		matrix[x][y] = item.copy();
	}
	
	public void hideNames(){
		for (int i = 0; i < lengthX; i++) {
			for (int j = 0; j < lengthY; j++) {
				if (matrix[i][j] != null) {
					matrix[i][j].hideName();
				}
			}
		}
	}
}
