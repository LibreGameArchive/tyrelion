/**
 * 
 */
package tyrelion;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.menu.MenuMain;

/**
 * @author jahudi
 *
 */
public class PreLoading extends BasicGameState {
	
	public static final int ID = 0;

	private Image loading;
	
	private UnicodeFont font_head;	
	
	/** Die Ressource, die als nächstes geladen werden soll. */
	private DeferredResource nextResource;
	
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
	public void init(GameContainer gameContainer, StateBasedGame game)
			throws SlickException {
		
		font_head = new UnicodeFont("/res/fonts/vinque.ttf", 30, false, false);
		font_head.getEffects().add(new ColorEffect(java.awt.Color.black));
		
		loading = new Image("res/img/splashscreens/loadingscreen.png");
		LoadingList.setDeferredLoading(true);
		gameContainer.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		if (nextResource != null) {
			showLoading(container, g);
		}
		
		font_head.addGlyphs("abcdefghijklmnopqrstovwxyzABCDEFGHIJKLMNOPQRSTUVWXYZäöü.");

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
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
			game.enterState(MenuMain.ID);
		}
		
		font_head.loadGlyphs(1000);

	}
	
	private void showLoading(GameContainer container, Graphics g){
		// Ladebildschirm anzeigen
		g.drawImage(loading, 0, 0);
		g.setColor(Color.black);
		
		// Fortschritt berechnen
		int total = LoadingList.get().getTotalResources();
		int segment = total/6;
		int status = (LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources());
		
		// Aktuellen "Pseudo"-Status anzeigen
		font_head.drawString(760, 360, "Eisen wird geschmolzen...");
		if (status > segment) font_head.drawString(725, 410, "Weltenform wird gegossen...");
		if (status > 2*segment) font_head.drawString(765, 460, "Gebirge werden geformt...");
		if (status > 3*segment) font_head.drawString(740, 510, "Meere werden ausgehoben...");
		if (status > 4*segment) font_head.drawString(750, 560, "Wälder werden gepflanzt...");
		if (status > 5*segment) font_head.drawString(677, 610, "Leben wird in die Welt gesetzt...");
	}

}
