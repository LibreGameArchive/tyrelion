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
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMap;

import com.slickset.ActorGroup;
import com.slickset.Camera;
import com.slickset.CollisionManager;
import com.slickset.Scene;
import com.slickset.collision.Circle;
import com.slickset.collision.PairwiseStrategy;
import com.slickset.layer.ColorLayer;
import com.slickset.layer.ParallaxLayer;
import com.slickset.layer.TileLayer;
import com.slickset.tile.Tile;

/**
 * @author jahudi
 *
 */
public class MapTest extends BasicGameState {

	public static final int ID = 6;

	private TiledMap map;
	
	private ActorGroup players;
	private ActorGroup tiles;
	private PlayerTest player;
	private Scene scene;
	private Camera cam;
	private TiledLayer back;
	
	/** The width of the display in tiles */
	private int widthInTiles;
	/** The height of the display in tiles */
	private int heightInTiles;
	
	/** The offset from the centre of the screen to the top edge in tiles */
	private int topOffsetInTiles;
	/** The offset from the centre of the screen to the left edge in tiles */
	private int leftOffsetInTiles;
	
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
		
		players = new ActorGroup("player");
		tiles = new ActorGroup("tiles");
		
		Animation[] animations = new Animation[4];
		
		Animation down = new Animation();
		down.addFrame(new Image("res/anim/test_anim/down/down.png", new Color(0x00cc00ff)), 1);
		Animation left = new Animation();
		left.addFrame(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), 1);
		Animation right = new Animation();
		right.addFrame(new Image("res/anim/test_anim/right/right.png", new Color(0x00cc00ff)), 1);
		Animation up = new Animation();
		up.addFrame(new Image("res/anim/test_anim/up/up.png", new Color(0x00cc00ff)), 1);
				
		animations[PlayerTest.ANIM_DOWN] = down;
		animations[PlayerTest.ANIM_LEFT] = left;
		animations[PlayerTest.ANIM_RIGHT] = right;
		animations[PlayerTest.ANIM_UP] = up;
			
		player = new PlayerTest(animations, 720, 576, new Circle(24), 1f, false);
		players.addActor(player);
		
		Tile tile1 = new Tile(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), new Circle(24));
		Tile tile2 = new Tile(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), new Circle(24));
		Tile tile3 = new Tile(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), new Circle(24));
		tiles.addActor(tile1);
		tiles.addActor(tile2);
		tiles.addActor(tile3);
		TileLayer tLayer = new TileLayer(32, 32, 48);
		tLayer.getData().setTile(tile1, 5, 5);
		tLayer.getData().setTile(tile2, 7, 15);
		tLayer.getData().setTile(tile3, 15, 10);
		
		back = new TiledLayer(1536, 1536, 48, map);
				
		ParallaxLayer parallax = new ParallaxLayer(1536, 1536);
		parallax.addBackgroundLayer(back);
		parallax.addForegroundLayer(tLayer);
		
		scene = new Scene(game);
		
		scene.setLayer(parallax);
		scene.addGroup(tiles);
		scene.addGroup(players);
		
		cam = new Camera(0, 0);
		scene.setCamera(cam);
		
		CollisionManager col = new CollisionManager(players, tiles, new PairwiseStrategy());
		scene.addCollisionManager(col);
		
		// caculate some layout values for rendering the tilemap. How many tiles
		// do we need to render to fill the screen in each dimension and how far is
		// it from the centre of the screen
		widthInTiles = container.getWidth() / 48;
		heightInTiles = container.getHeight() / 48;
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {		
		
		int playerTileX = (int)(player.getX()/48);
		int playerTileY = (int)(player.getY()/48);
		
		int playerTileOffsetX = (int) ((playerTileX - (player.getX()/48)) * 48);
		int playerTileOffsetY = (int) ((playerTileY - (player.getY()/48)) * 48);
		
		back.render(playerTileOffsetX-24, playerTileOffsetY-24, playerTileX-leftOffsetInTiles-1, playerTileY-topOffsetInTiles-1,
				widthInTiles+3, heightInTiles+3);
		scene.render(g);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		scene.getCamera().centerCamera(player);
		scene.update(game, delta);
		
	}
	
}
