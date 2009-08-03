/**
 * 
 */
package experimental;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import com.slickset.collision.Circle;

/**
 * @author jahudi
 *
 */
public class MapTest extends BasicGameState {

	public static final int ID = 6;

	/** The size of the tank sprite - used for finding the centre */
	private static final int PLAYER_SIZE = 48;
	/** The size of the tiles - used to determine the amount to draw */
	private static final int TILE_SIZE = 48;
	/** The speed the tank moves at */
	private static final float PLAYER_MOVE_SPEED = 0.003f;
	
	/** The player's x position in tiles */
	private float playerX;
	/** The player's y position in tiles */
	private float playerY;
	
	/** The width of the display in tiles */
	private int widthInTiles;
	/** The height of the display in tiles */
	private int heightInTiles;
	
	/** The offset from the centre of the screen to the top edge in tiles */
	private int topOffsetInTiles;
	/** The offset from the centre of the screen to the left edge in tiles */
	private int leftOffsetInTiles;
	
	/** The map that we're going to drive around */
	private TiledMap map;
	
	/** The animation representing the player's tank */
	private Animation player;
	
	private PlayerTest playerTest;
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		map = new TiledMap("res/maps/testmap.tmx", "res/maps");
		
		// caculate some layout values for rendering the tilemap. How many tiles
		// do we need to render to fill the screen in each dimension and how far is
		// it from the centre of the screen
		widthInTiles = container.getWidth() / TILE_SIZE;
		heightInTiles = container.getHeight() / TILE_SIZE;
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
		
		player = new Animation();
		player.addFrame(new Image("res/anim/test_anim/right/right.png", new Color(0x00cc00ff)), 1);
		
		Animation[] anims = {player,player,player,player};
		
		playerTest = new PlayerTest(anims, 480, 480, new Circle(24), 1f, false);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {		

		playerX = playerTest.getPlayerX();
		playerY = playerTest.getPlayerY();
		
		// draw the appropriate section of the tilemap based on the centre (hence the -(PLAYER_SIZE/2)) of
		// the player
		int playerTileX = (int) playerX;
		int playerTileY = (int) playerY;
		
		// caculate the offset of the player from the edge of the tile. As the player moves around this
		// varies and this tells us how far to offset the tile based rendering to give the smooth
		// motion of scrolling
		int playerTileOffsetX = (int) ((playerTileX - playerX) * TILE_SIZE);
		int playerTileOffsetY = (int) ((playerTileY - playerY) * TILE_SIZE);
		
		// render the section of the map that should be visible. Notice the -1 and +3 which renders
		// a little extra map around the edge of the screen to cope with tiles scrolling on and off
		// the screen
		map.render(playerTileOffsetX - (PLAYER_SIZE / 2), playerTileOffsetY - (PLAYER_SIZE / 2), 
				   playerTileX - leftOffsetInTiles - 1, 
				   playerTileY - topOffsetInTiles - 1,
				   widthInTiles + 3, heightInTiles + 3);
		
		// draw entities relative to the player that must appear in the centre of the screen
		g.translate(512 - (int) (playerX * 48), 368 - (int) (playerY * 48));
		
		playerTest.render(g, playerX*48-24, playerY * 48-24);
		
		// draw other entities here if there were any
		g.resetTransform();
		g.setColor(Color.red);
		g.drawString("playerX: " + Float.toString(playerX), 0, 0);
		g.drawString("playerY: " + Float.toString(playerY), 0, 15);
		g.drawString("playerTileX: " + Integer.toString(playerTileX), 0, 30);
		g.drawString("playerTileY: " + Integer.toString(playerTileY), 0, 45);
		g.drawString("widthInTiles: " + Integer.toString(widthInTiles), 0, 60);
		g.drawString("heightInTiles: " + Integer.toString(heightInTiles), 0, 75);
		g.drawString("leftOffsetInTiles: " + Integer.toString(leftOffsetInTiles), 0, 90);
		g.drawString("topOffsetInTiles: " + Integer.toString(topOffsetInTiles), 0, 105);
		g.drawString("playerTileOffsetX: " + Integer.toString(playerTileOffsetX), 0, 120);
		g.drawString("playerTileOffsetY: " + Integer.toString(playerTileOffsetY), 0, 135);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		playerTest.update(game, delta);
		
	}
	
}
