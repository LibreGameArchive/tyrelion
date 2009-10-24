/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.gui.DisabledScreen;
import tyrelion.gui.GUILayer;
import tyrelion.loaders.ItemLoader;
import tyrelion.map.TyrelionMap;
import tyrelion.music.MusicManager;
import tyrelion.objects.Player;
import tyrelion.sfx.SoundManager;

/**
 * @author jahudi
 *
 */
public class ExpMode extends BasicGameState {

	public static final int ID = 6;
	
	private TyrelionMap map;
	
	private Player player;
	
	private GUILayer guiLayer;
	
	private DisabledScreen disabledScreen;

	private boolean debug = false;
	
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
		player = Player.getInstance();
		player.setPosX(2);
		player.setPosY(14);

		map = new TyrelionMap("res/maps/arthlet.tmx", container);
		
		TyrelionContainer.getInstance().setContainer(container);
		TyrelionContainer.getInstance().setMap(map);
		
		CoordinatesTranslator.getInstance().setMap(map);
		CoordinatesTranslator.getInstance().setPlayer(player);
		
		CollisionManager.getInstance().setMap(map);
		CollisionManager.getInstance().setPlayer(player);
		
		guiLayer = new GUILayer(container, game);
		
		disabledScreen = new DisabledScreen();
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		map.render(player, g);
		
		
		
		// draw entities relative to the player that must appear in the centre of the screen
		g.translate(576 - (int) (player.getPosX() * 48), 432 - (int) (player.getPosY() * 48));
		
		map.renderNpcs(g);
		map.renderItems(g);
		
		player.render(g);
		
		map.renderNpcBubbles(g);

		if (debug) {
			Shape[][] tiles = CollisionManager.getInstance().getTiles();
			for (int i = 0; i < map.getWidth(); i++) {
				for (int j = 0; j < map.getHeight(); j++) {
					if (tiles[i][j] != null) {
						g.draw(tiles[i][j]);
					}
				}
			}
			player.renderShape(g);
			
			// draw other entities here if there were any
			g.resetTransform();
			g.setColor(Color.red);
			
			g.drawString("playerX: " + Float.toString(player.getPosX()), 25, 532);
			g.drawString("playerY: " + Float.toString(player.getPosY()), 25, 547);
			g.drawString("playerTileX: " + Integer.toString(player.getTileX()), 25, 562);
			g.drawString("playerTileY: " + Integer.toString(player.getTileY()), 25, 577);
			g.drawString("playerTileOffsetX: " + Integer.toString(player.getTileOffsetX()), 25, 592);
			g.drawString("playerTileOffsetY: " + Integer.toString(player.getTileOffsetY()), 25, 607);
			
		}
		g.resetTransform();

		if (container.isPaused()) disabledScreen.render(g);
		
		guiLayer.render(container, g);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		player.update(container, delta);
		
		map.updateNpcs();
				
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		MusicManager.getInstance().loop("expNormal");
	}
	
	public void keyReleased(int i, char c) {
		InteractionManager.getInstance().keyReleased(i, c);
		
		
		if (i == Input.KEY_T) {
			SoundManager.getInstance().play("ambience", "thunder");
		}
		
		if (i == Input.KEY_M) {				
				Player.getInstance().getCharacter().getInventory().addItem(ItemLoader.getInstance().getItem(0));
		}
	}
	
	public void keyPressed(int i, char c) {
		InteractionManager.getInstance().keyPressed(i, c);
	}
	
	public void mouseClicked(int button, int x, int y, int clickCount) {
		InteractionManager.getInstance().mouseClicked(button, x, y, clickCount);
	}
	
	public void mousePressed(int button, int x, int y){
		InteractionManager.getInstance().mousePressed(button, x, y);
	}
	
	public void mouseReleased(int button, int x, int y){
		InteractionManager.getInstance().mouseReleased(button, x, y);
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		InteractionManager.getInstance().mouseMoved(oldx, oldy, newx, newy);
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
}
