/**
 * 
 */
package tyrelion.menu;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jahudi
 *
 */
public class MenuMain extends BasicGameState {

	public static final int ID = 1;
	
	private StateBasedGame game;
	private Image loading;
	private Image background;
	private Image button_field;
	
	/** Die Ressource, die als nächstes geladen werden soll. */
	private DeferredResource nextResource;
	/** True, wenn alle Ressourcen geladen und das Rendering gestartet wurde. */
	private boolean started;
	
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
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		this.game = game;
		
		LoadingList.setDeferredLoading(true);
		
		container.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);
		
		loading = new Image("res/img/menu/main/loading.jpg");
		background = new Image("res/img/menu/main/mainmenu_bg.png");
		button_field = new Image("res/img/menu/main/mainmenu_box.png");
		
        
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		if (nextResource != null) {
			// Ladebildschirm anzeigen
			g.drawImage(loading, 0, 0);
			g.setColor(new Color(70, 20, 0));
			// Anzeigen, welche Ressource momentan geladen wird
			g.drawString("Loading: "+nextResource.getDescription(), 255, 683);
			
			// Die Dimensionen des Ladebalkens berechnen
			int total = LoadingList.get().getTotalResources();
			int segment = 537/total;
			int loaded = segment * (LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources());
			
			// Den Ladebalken anzeigen
			g.fillRoundRect(244, 704, loaded, 14, 10);
			g.drawRoundRect(244, 704, 537, 14, 10);
		}

		// Wenn alles geladen wurde, den eigentlichen State starten
		if (started) {		
			g.clear();
			g.drawImage(background, 0, 0);
			g.drawImage(button_field, 410, 360);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		// Wenn noch Ressourcen zum Laden vorhanden sind, diese Laden
		if (nextResource != null) {
			try {
				nextResource.load();
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			nextResource = null;
		}
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			if (!started) {
				started = true;
			}
		}

	}
	
	@Override
	public void keyReleased(int i, char c) {
		
		switch (i) {			
			case Input.KEY_ESCAPE:
				game.getContainer().exit();
				break;
	
			default:
				break;
		}
		
	}

}
