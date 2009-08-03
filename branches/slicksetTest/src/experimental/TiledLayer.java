/**
 * 
 */
package experimental;

import org.newdawn.slick.tiled.TiledMap;

import com.slickset.layer.TileLayer;

/**
 * @author jahudi
 *
 */
public class TiledLayer extends TileLayer {

	TiledMap map;
	
	/**
	 * @param width
	 * @param height
	 * @param tileSize
	 */
	public TiledLayer(int width, int height, int tileSize, TiledMap map) {
		super(width, height, tileSize);
		this.map = map;
	}

	public void render(int x, int y, int sx, int sy, int width, int height) {
		map.render(x, y, sx, sy, width, height);
	}
	
	
}
