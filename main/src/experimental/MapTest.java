/**
 * 
 */
package experimental;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.slickset.ActorGroup;
import com.slickset.Camera;
import com.slickset.CollisionManager;
import com.slickset.Scene;
import com.slickset.collision.Circle;
import com.slickset.collision.PairwiseStrategy;
import com.slickset.layer.ImageLayer;
import com.slickset.layer.ParallaxLayer;
import com.slickset.layer.TileLayer;
import com.slickset.tile.Tile;

/**
 * @author jahudi
 *
 */
public class MapTest extends BasicGameState {

	public static final int ID = 6;
	
	ActorGroup players;
	ActorGroup tiles;
	PlayerTest player;
	Scene scene;
	Camera cam;
	
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
		
		Animation up = new Animation();
		up.addFrame(new Image("res/anim/test_anim/up/up.png", new Color(0x00cc00ff)), 1000);
		
		Animation down = new Animation();
		down.addFrame(new Image("res/anim/test_anim/down/down.png", new Color(0x00cc00ff)), 1000);
		
		Animation left = new Animation();
		left.addFrame(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), 1000);
		
		Animation right = new Animation();
		right.addFrame(new Image("res/anim/test_anim/right/right.png", new Color(0x00cc00ff)), 1000);
		
		Animation[] anims = new Animation[4];
		anims[PlayerTest.ANIM_DOWN] = down;
		anims[PlayerTest.ANIM_UP] = up;
		anims[PlayerTest.ANIM_LEFT] = left;
		anims[PlayerTest.ANIM_RIGHT] = right;
		
		
		players = new ActorGroup("players");
		player = new PlayerTest(anims, 0, 0, new Circle(24), 1f, false);
		players.addActor(player);
		
		tiles = new ActorGroup("tiles");
		
		Tile tile = new Tile(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), new Circle(24));
		TileLayer tLayer = new TileLayer(48, 48, 48);
		tLayer.getData().setTile(tile, 6, 6);
		
		tiles.addActor(tile);
		
		ImageLayer back = new ImageLayer(new Image("res/maps/test.png"));
		ParallaxLayer parallax = new ParallaxLayer(2304, 2304);
		parallax.addBackgroundLayer(back);
		
		scene = new Scene(game);
		scene.setLayer(tLayer);
		scene.setLayer(parallax);
		scene.addGroup(players);
		scene.addGroup(tiles);
		cam = new Camera();
		scene.setCamera(cam);
		
		CollisionManager col = new CollisionManager(players, tiles, new PairwiseStrategy());
		scene.addCollisionManager(col);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {		
		scene.render(g);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		scene.getCamera().centerCamera(player);
		scene.update(game, delta);
		player.update(game, delta);

	}

}
