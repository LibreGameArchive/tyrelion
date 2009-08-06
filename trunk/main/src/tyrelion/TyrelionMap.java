/**
 * 
 */
package tyrelion;

import java.util.ArrayList;

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
	 * @param tileSetsLocation
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
		
		ArrayList<Shape> tiles = new ArrayList<Shape>();
		
		for (int x = 0; x < super.getWidth(); x++) {
			tiles.add(new Rectangle(x * 48 -24, -1 *48 -24, 48, 48));
			tiles.add(new Rectangle(x * 48 -24, super.getHeight() * 48 -24, 48, 48));
			for (int y = 0; y < super.getHeight(); y++){
				tiles.add(new Rectangle(-1 * 48 -24, y * 48 -24, 48, 48));
				tiles.add(new Rectangle(super.getWidth() * 48 -24, y * 48 -24, 48, 48));
				int tileID = super.getTileId(x, y, 1);
				String value = super.getTileProperty(tileID, "blocked", "false");
				if ("true".equals(value)) {
					tiles.add(new Rectangle(x*48-24, y*48-24, 48, 48));
				}
			}
		}
		
		for (int y = 0; y < super.getHeight(); y++) {
	
		}
		
		CollisionManager.getInstance().setTiles(tiles);
		
	}

}
