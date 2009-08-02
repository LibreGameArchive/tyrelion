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
import org.newdawn.slick.tiled.TiledMap;

import com.slickset.ActorGroup;
import com.slickset.Camera;
import com.slickset.Scene;
import com.slickset.collision.Box;

/**
 * @author jahudi
 *
 */
public class MapTest extends BasicGameState {

	public static final int ID = 6;
	
	TiledMap map;
	
	ActorGroup players;
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
		map = new TiledMap("res/maps/testmap.tmx", "res/maps");
		
		Animation anim = new Animation();
		anim.addFrame(new Image("res/anim/test.png", new Color(0x00cc00ff)), 10);
		
		Animation[] anims = { anim };
		
		players = new ActorGroup("players");
		player = new PlayerTest(anims, 100, 100, new Box(48, 48), 80f, true);
		
		scene = new Scene(game);
		scene.addGroup(players);
		cam = new Camera(0, 0);
		scene.setCamera(cam);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		map.render(0, 0);
		player.render(g, 100, 100);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

}
