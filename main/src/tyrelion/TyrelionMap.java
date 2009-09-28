/**
 * 
 */
package tyrelion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;


/**
 * @author jahudi
 *
 */
public class TyrelionMap extends TiledMap {

	/** The size of the tiles - used to determine the amount to draw */
	public static final int TILE_SIZE = 48;
	
	/** The width of the display in tiles */
	private int widthInTiles;
	/** The height of the display in tiles */
	private int heightInTiles;
	/** The offset from the centre of the screen to the top edge in tiles */
	private int topOffsetInTiles;
	/** The offset from the centre of the screen to the left edge in tiles */
	private int leftOffsetInTiles;
	
	/**
	 * @param ref
	 * @param container
	 * @throws SlickException
	 */
	public TyrelionMap(String ref, GameContainer container)
			throws SlickException {
		super(ref);
		widthInTiles = container.getWidth() / TILE_SIZE;
		heightInTiles = container.getHeight() / TILE_SIZE;
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
		initCollisionBoxes();
	}
	
	public void render(Player player) {
		
		this.render(player.getTileOffsetX() - (Player.PLAYER_SIZE / 2),
				player.getTileOffsetY() - (Player.PLAYER_SIZE / 2), 
				player.getTileX() - leftOffsetInTiles, 
				player.getTileY() - topOffsetInTiles,
				widthInTiles + 3, heightInTiles + 3);

	}
	
	public void initCollisionBoxes(){
		
		Shape[][] tiles = new Shape[this.getWidth()][this.getHeight()];
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				for (int l = 0; l < this.getLayerCount(); l++) {
					int tileID = this.getTileId(i, j, l);
					String value = this.getTileProperty(tileID, "blocked", "false");
					if ("true".equals(value)) {
						tiles[i][j] = new Rectangle(i*48-24, j*48-24, 48, 48); 
					}
				}
			}
		}
		
		CollisionManager.getInstance().setTiles(tiles);
		
	}

	/**
	 * @return the tileSize
	 */
	public static int getTileSize() {
		return TILE_SIZE;
	}

	/**
	 * @return the widthInTiles
	 */
	public int getWidthInTiles() {
		return widthInTiles;
	}

	/**
	 * @return the heightInTiles
	 */
	public int getHeightInTiles() {
		return heightInTiles;
	}

	/**
	 * @return the topOffsetInTiles
	 */
	public int getTopOffsetInTiles() {
		return topOffsetInTiles;
	}

	/**
	 * @return the leftOffsetInTiles
	 */
	public int getLeftOffsetInTiles() {
		return leftOffsetInTiles;
	}

}
