/**
 * 
 */
package experimental;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import tyrelion.gui.GUILayer;
import tyrelion.music.MusicManager;

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
	private float playerX = 15;
	/** The player's y position in tiles */
	private float playerY = 15;
	
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
	
	private GUILayer guiLayer;
	
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
		
		guiLayer = new GUILayer(container, game);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
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
		
		guiLayer.render(container, g);
		
		// draw entities relative to the player that must appear in the centre of the screen
		g.translate(512 - (int) (playerX * 48), 368 - (int) (playerY * 48));
		
		drawPlayer(g, playerX, playerY);
		
		
		// draw other entities here if there were any
		g.resetTransform();
		g.setColor(Color.red);
		g.drawString("playerX: " + Float.toString(playerX), 25, 632);
		g.drawString("playerY: " + Float.toString(playerY), 25, 647);
		g.drawString("playerTileX: " + Integer.toString(playerTileX), 25, 662);
		g.drawString("playerTileY: " + Integer.toString(playerTileY), 25, 677);
		g.drawString("playerTileOffsetX: " + Integer.toString(playerTileOffsetX), 25, 692);
		g.drawString("playerTileOffsetY: " + Integer.toString(playerTileOffsetY), 25, 707);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			playerX += -delta * PLAYER_MOVE_SPEED;
		}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			playerX += delta * PLAYER_MOVE_SPEED;
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			playerY += -delta * PLAYER_MOVE_SPEED;
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			playerY += delta * PLAYER_MOVE_SPEED;
		}
		
	}
	
	public void drawPlayer(Graphics g, float xpos, float ypos) {
		int cx = (int) (xpos * 48);
		int cy = (int) (ypos * 48);
		player.draw(cx-24,cy-24);
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		MusicManager.getInstance().loop("expNormal");
	}
	
}
