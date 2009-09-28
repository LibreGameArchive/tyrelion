/**
 * 
 */
package tyrelion;



import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import tyrelion.gui.Charinfo;
import tyrelion.gui.GUILayer;
import tyrelion.gui.Infobox;
import tyrelion.gui.Message;
import tyrelion.gui.Minimap;
import tyrelion.itemsystem.Food;
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
	
	private Minimap minimap;
	
	private Charinfo charinfo;
	private boolean showCharinfo = false;

	private boolean debug = false;
	
	private GameContainer container;

	private Npc npc;
	private Food apple;
	private WorldItem worldApple;
	
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
		this.container = container;
		
		player = Player.getInstance();
		player.setPosX(2);
		player.setPosY(14);
		
		map = new TyrelionMap("res/maps/arthlet.tmx", container);
		
		CollisionManager.getInstance().setMap(map);
		
		guiLayer = new GUILayer(container, game);
		
		minimap = new Minimap(container, map, player);
		
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
		
		charinfo = new Charinfo(container);
		
		npc = new Npc(2, 16);
		apple = new Food(1233, "Krasser Apfel", new Image("res/img/items/apple_world.png"),
				new Image("res/img/items/apple_inv.png"), true);
		worldApple = new WorldItem(6, 17, apple);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		map.render(player);
		
		
		
		// draw entities relative to the player that must appear in the centre of the screen
		g.translate(576 - (int) (player.getPosX() * 48), 432 - (int) (player.getPosY() * 48));
		
		npc.render(g);
		worldApple.render(g);
		
		player.render(g);
		
		
		
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

		
		
		minimap.render(g);
		
		guiLayer.render(container, g);
		
		infobox.render(container, g, 15, 716);
		
		if (showCharinfo) charinfo.render(container, g, 100, 100);
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
		
		if (i == Input.KEY_C) {
			showCharinfo = !showCharinfo;
		}
		
		if (i == Input.KEY_M) {
			try {
				Player.getInstance().getCharacter().getInventory().addItem(new Food(1233, "Krasser Apfel", new Image("res/img/items/apple_world.png"),
						new Image("res/img/items/apple_inv.png"), true));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void mouseClicked(int button, int x, int y, int clickCount) {
		Point p = translateCoordinates(x, y);
		
		if (button == Input.MOUSE_RIGHT_BUTTON && player.inRange(npc)) {
			if (p.x == npc.getPosX() && (p.y == npc.getPosY() || p.y == npc.getPosY() - 1)) {
				npc.toggleShowHello();
			}
		}
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		Point p = translateCoordinates(newx, newy);
		
		if(p.x == npc.getTileX() && (p.y == npc.getTileY() || p.y == npc.getTileY()-1)) {
			if (player.inRange(npc)) {
				try {
					container.setMouseCursor("res/img/mouse/cursor_bubble.png", 13, 28);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					container.setMouseCursor("res/img/mouse/cursor_bubble_locked.png", 13, 28);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if(p.x == worldApple.getTileX() && p.y == worldApple.getTileY()) {
			if (player.inRange(worldApple)) {
				try {
					container.setMouseCursor("res/img/mouse/cursor_hand.png", 12, 20);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					container.setMouseCursor("res/img/mouse/cursor_hand_locked.png", 12, 20);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				container.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public Point translateCoordinates(int x, int y) {
		float mouseX;
		float mouseY;
		float targetX;
		float targetY;
		float tempX = x / (new Float(map.getTileSize()));
		float tempY = y / (new Float(map.getTileSize()));
		int offsetX = (int)(((int)tempX - tempX) * map.getTileSize());
		int offsetY = (int)(((int)tempY - tempY) * map.getTileSize());
		mouseX = (x-offsetX) / (new Float(map.getTileSize()));
		mouseY = (y-offsetY) / (new Float(map.getTileSize()));
		
		// absolute in relative Position und Ziel-Tile berechnen
		if (mouseX < player.getTileX()) {
			targetX = player.getTileX() - new Float(map.getLeftOffsetInTiles()) + mouseX;
		} else {
			targetX = player.getTileX() + mouseX - new Float(map.getLeftOffsetInTiles());
		}
		if (mouseY < player.getTileY()) {
			targetY = player.getTileY() - new Float(map.getTopOffsetInTiles()) + mouseY;
		} else {
			targetY = player.getTileY() + mouseY - new Float(map.getTopOffsetInTiles());
		}
		return new Point((int)targetX, (int)targetY);
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
