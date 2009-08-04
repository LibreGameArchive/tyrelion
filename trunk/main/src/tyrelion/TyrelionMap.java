/**
 * 
 */
package tyrelion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
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
	
	private PlayerTest player;
	
	/**
	 * @param ref
	 * @param tileSetsLocation
	 * @throws SlickException
	 */
	public TyrelionMap(String ref, String tileSetsLocation, PlayerTest player, GameContainer container)
			throws SlickException {
		super(ref, tileSetsLocation);
		this.player = player;
		widthInTiles = container.getWidth() / TILE_SIZE;
		heightInTiles = container.getHeight() / TILE_SIZE;
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		
		this.render(player.getTileOffsetX() - (PlayerTest.PLAYER_SIZE / 2),
				player.getTileOffsetY() - (PlayerTest.PLAYER_SIZE / 2), 
				player.getTileX() - leftOffsetInTiles - 1, 
				player.getTileY() - topOffsetInTiles - 1,
				widthInTiles + 3, heightInTiles + 3);

	}

}
