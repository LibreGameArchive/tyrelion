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

import tyrelion.gui.GUILayer;
import tyrelion.gui.Infobox;
import tyrelion.gui.Message;
import tyrelion.music.MusicManager;
import tyrelion.sfx.SoundManager;

/**
 * @author jahudi
 *
 */
public class ExpMode extends BasicGameState {

	public static final int ID = 6;
	
	/** The map that we're going to drive around */
	private TyrelionMap map;
	
	/** The animation representing the player's tank */
	private Player player;
	
	private GUILayer guiLayer;
	
	private Infobox infobox;
	
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
		
		player = new Player(0, 0);
		
		map = new TyrelionMap("res/maps/testmap.tmx", container);
		
		guiLayer = new GUILayer(container, game);
		
		infobox = new Infobox(container);
		
		infobox.print("Dies ist eine Systemmeldung!", Message.SYSTEM);
		infobox.print("Du hast ein Item erhalten!", Message.ITEM);
		infobox.print("Du hast eine Quest bestanden!", Message.QUEST);
		infobox.print("Du hast für einem Ork 50 Schadenspunkte zugefügt und ihn damit getötet!", Message.FIGHT);
		infobox.print("Du hast 250 EP erhalten!", Message.EXPERIENCE);
		infobox.print("Enim ad minim veniam quis nostrud exerci tation. Me lius quod ii legunt saepius claritas est etiam processus dynamicus qui sequitur mutationem consuetudium lectorum.", Message.SYSTEM);
		infobox.print("Leider konntest du die Quest nicht beenden...", Message.QUEST);
		infobox.print("Das ist eine Meldung die nirgend wo hinein passte!", Message.MISC);
		infobox.print("Du hast einen rostigen Dolch gefunden!", Message.ITEM);
		infobox.print("Du hast gegen die Schildkröte verloren!", Message.FIGHT);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		map.render(player);
		
		guiLayer.render(container, g);
		
		infobox.render(container, g, 15, 716);
		
		// draw entities relative to the player that must appear in the centre of the screen
		g.translate(576 - (int) (player.getX() * 48), 432 - (int) (player.getY() * 48));
		
		player.render(g);
		
		if (debug) {
			for (Shape elem : CollisionManager.getInstance().getTiles()) {
				g.draw(elem);
			}
			player.renderShape(g);
			
			// draw other entities here if there were any
			g.resetTransform();
			g.setColor(Color.red);
			
			g.drawString("playerX: " + Float.toString(player.getX()), 25, 532);
			g.drawString("playerY: " + Float.toString(player.getY()), 25, 547);
			g.drawString("playerTileX: " + Integer.toString(player.getTileX()), 25, 562);
			g.drawString("playerTileY: " + Integer.toString(player.getTileY()), 25, 577);
			g.drawString("playerTileOffsetX: " + Integer.toString(player.getTileOffsetX()), 25, 592);
			g.drawString("playerTileOffsetY: " + Integer.toString(player.getTileOffsetY()), 25, 607);
		}
		g.resetTransform();

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		CollisionManager.getInstance().update(container, delta);
				
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		MusicManager.getInstance().loop("expNormal");
	}
	
	public void keyReleased(int i, char c) {
		if (i == Input.KEY_T) {
			SoundManager.getInstance().play("ambience", "thunder");
		}
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
