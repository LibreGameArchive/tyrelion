/**
 * 
 */
package tyrelion;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.menu.MenuMain;
import tyrelion.music.MusicManager;
import tyrelion.sfx.SoundManager;

/**
 * @author jahudi
 *
 */
public class PreLoading extends BasicGameState {
	
	public static final int ID = 0;

	private Image loading;
	
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
			throws SlickException {loading = new Image("res/img/splashscreens/loadingscreen.png");
		LoadingList.setDeferredLoading(true);
		gameContainer.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);
		MusicManager.getInstance();
		SoundManager.getInstance();
		CollisionManager.getInstance();

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		if (nextResource != null) {
			showLoading(container, g);
		}
		
	//	font_head.addGlyphs("abcdefghijklmnopqrstovwxyzABCDEFGHIJKLMNOPQRSTUVWXYZäöü.");

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

	}
	
	private void showLoading(GameContainer container, Graphics g) throws SlickException{
		// Ladebildschirm anzeigen
		g.drawImage(loading, 0, 0);
		
		// Fortschritt berechnen
		int total = LoadingList.get().getTotalResources();
		int segment = total/6;
		int status = (LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources());
		
		FontManager fm = FontManager.getInstance();
		
		// Aktuellen "Pseudo"-Status anzeigen
		fm.drawString(g, 726, 360, "Eisen wird geschmolzen...", FontManager.FANCY, FontManager.LARGE);
		if (status > segment) fm.drawString(g, 687, 410, "Weltenform wird gegossen...", FontManager.FANCY, FontManager.LARGE);
		if (status > 2*segment) fm.drawString(g, 735, 460, "Gebirge werden geformt...", FontManager.FANCY, FontManager.LARGE);
		if (status > 3*segment) fm.drawString(g, 703, 510, "Meere werden ausgehoben...", FontManager.FANCY, FontManager.LARGE);
		if (status > 4*segment) fm.drawString(g, 716, 560, "Wälder werden gepflanzt...", FontManager.FANCY, FontManager.LARGE);
		if (status > 5*segment) fm.drawString(g, 634, 610, "Leben wird in die Welt gesetzt...", FontManager.FANCY, FontManager.LARGE);
	}

}
