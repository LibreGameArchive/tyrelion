/**
 * 
 */
package tyrelion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
	
	private NpcContainer npcs;
	private WorldItemContainer items;
	
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
		npcs = new NpcContainer(width, height);
		items = new WorldItemContainer(width, height);
	}
	
	public void render(Player player, Graphics g) {
		
		this.render(player.getTileOffsetX() - (Player.SIZE / 2),
				player.getTileOffsetY() - (Player.SIZE / 2), 
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
	
	public void renderNpcs(Player player, Graphics g) {
		int startX = player.getTileX() - leftOffsetInTiles;
		int startY = player.getTileY() - topOffsetInTiles;
		int endX = player.getTileX() + leftOffsetInTiles;
		int endY = player.getTileY() + topOffsetInTiles;
		
		if (startX < 0) { startX = 0; }
		if (startY < 0) { startY = 0; }
		if (endX > width) { endX = width; }
		if (endY > height) { endY = height; }
		
		npcs.drawNpcs(startX, startY, endX, endY, g);
	}
	
	public void renderItems(Player player, Graphics g) {
		int startX = player.getTileX() - leftOffsetInTiles;
		int startY = player.getTileY() - topOffsetInTiles;
		int endX = player.getTileX() + leftOffsetInTiles;
		int endY = player.getTileY() + topOffsetInTiles;
		
		if (startX < 0) { startX = 0; }
		if (startY < 0) { startY = 0; }
		if (endX > width) { endX = width; }
		if (endY > height) { endY = height; }
		
		items.drawItems(startX, startY, endX, endY, g);
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

	/**
	 * @return the npcs
	 */
	public NpcContainer getNpcs() {
		return npcs;
	}

	/**
	 * @return the items
	 */
	public WorldItemContainer getItems() {
		return items;
	}

}
