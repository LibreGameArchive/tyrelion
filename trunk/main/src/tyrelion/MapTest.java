/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.gui.GUILayer;
import tyrelion.music.MusicManager;

/**
 * @author jahudi
 *
 */
public class MapTest extends BasicGameState {

	public static final int ID = 6;
	
	/** The map that we're going to drive around */
	private TyrelionMap map;
	
	/** The animation representing the player's tank */
	private PlayerTest player;
	
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
		
		player = new PlayerTest();
		
		map = new TyrelionMap("res/maps/testmap.tmx", "res/maps", player, container);
		
		guiLayer = new GUILayer(container, game);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		map.render(container, game, g);
		
		guiLayer.render(container, g);
		
		// draw entities relative to the player that must appear in the centre of the screen
		g.translate(512 - (int) (player.getX() * 48), 368 - (int) (player.getY() * 48));
		
		player.render(g);
		
		
		// draw other entities here if there were any
		g.resetTransform();
		g.setColor(Color.red);
		g.drawString("playerX: " + Float.toString(player.getX()*48), 25, 632);
		g.drawString("playerY: " + Float.toString(player.getY()*48), 25, 647);
		g.drawString("playerTileX: " + Integer.toString(player.getTileX()), 25, 662);
		g.drawString("playerTileY: " + Integer.toString(player.getTileY()), 25, 677);
		g.drawString("playerTileOffsetX: " + Integer.toString(player.getTileOffsetX()), 25, 692);
		g.drawString("playerTileOffsetY: " + Integer.toString(player.getTileOffsetY()), 25, 707);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		player.update(game, delta);
		
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		MusicManager.getInstance().loop("expNormal");
	}
	
}
