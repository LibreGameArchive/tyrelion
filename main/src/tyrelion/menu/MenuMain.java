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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jahudi
 *
 */
public class MenuMain extends BasicGameState  implements ComponentListener{

	public static final int ID = 1;
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
	private UnicodeFont font_head;
	
	/** Hintergrundgrafik des Ladebildschirm (Spielstart). */
	private Image loading;
	/** Hintergrundgrafik des Hauptmenüs. */
	private Image background;
	/** Hintergrundgrafik des Buttonbereichs. */
	private Image button_field_background;
	
	/** MOA für den Button "Neues Spiel" */
	private MouseOverArea btn_new;
	/** MOA für den Button "Alten Spielstand laden" */
	private MouseOverArea btn_load;
	/** MOA für den Button "Einstellungen" */
	private MouseOverArea btn_set;
	/** MOA für den Button "Mitwirkende" */
	private MouseOverArea btn_cred;
	/** MOA für den Button "Spiel beenden" */
	private MouseOverArea btn_quit;
	
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
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		this.game = game;
		this.gameContainer = container;
				
		font_head = new UnicodeFont("/res/fonts/vinque.ttf", 30, false, false);
		font_head.getEffects().add(new ColorEffect(java.awt.Color.black));
		
		loading = new Image("res/img/splashscreens/loadingscreen.png");
		
		LoadingList.setDeferredLoading(true);
		
		gameContainer.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);
		
		background = new Image("res/img/menu/main/mainmenu_bg.png");
	
		initButtonField();
		
      	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		if (nextResource != null) {
			showLoading(container, g);
		}

		// Wenn alles geladen wurde, den eigentlichen State starten
		if (started) {	
			
			g.clear();
			g.drawImage(background, 0, 0);
			
			renderButtonField(container, g);
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
			if (!started) {
				started = true;
			}
		}
		
		font_head.loadGlyphs(1000);

	}
	
	public void keyReleased(int i, char c) {
		
		switch (i) {			
			case Input.KEY_ESCAPE:
				gameContainer.exit();
				break;
	
			default:
				break;
		}
		
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
		font_head.drawString(620, 300, "Eisen wird geschmolzen...");
		if (status > segment) font_head.drawString(585, 350, "Weltenform wird gegossen...");
		if (status > 2*segment) font_head.drawString(625, 400, "Gebirge werden geformt...");
		if (status > 3*segment) font_head.drawString(600, 450, "Meere werden ausgehoben...");
		if (status > 4*segment) font_head.drawString(610, 500, "Wälder werden gepflanzt...");
		if (status > 5*segment) font_head.drawString(537, 550, "Leben wird in die Welt gesetzt...");
	}
	
	private void initButtonField() throws SlickException{
		//Hintergrund Grafik für den Button-Bereich festlegen
		button_field_background = new Image("res/img/menu/main/mainmenu_box.png");
		
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons
        btn_new = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_new_1.png"), 430, 405, 400, 50, this);
        btn_new.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_new_2.png"));
        
        btn_load = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_load_1.png"), 430, 455, 400, 50, this);
        btn_load.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_load_2.png"));
        
        btn_set = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_opt_1.png"), 430, 505, 400, 50, this);
        btn_set.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_opt_2.png"));
        
        btn_cred = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_cred_1.png"), 430, 555, 400, 50, this);
        btn_cred.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_cred_2.png"));
        
        btn_quit = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_quit_1.png"), 430, 605, 400, 50, this);
        btn_quit.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_quit_2.png"));
	}
	
	private void renderButtonField(GameContainer container, Graphics g){
		//Rendern des HIntergrundes für die Buttons
		g.drawImage(button_field_background, 410, 360);
		
		//Rendern der Buttons
		btn_new.render(container, g);
		btn_load.render(container, g);
		btn_set.render(container, g);
		btn_cred.render(container, g);
		btn_quit.render(container, g);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == btn_new) gameContainer.exit();
		if (source == btn_load) game.enterState(MenuLoad.ID);
		if (source == btn_set) game.enterState(MenuSettings.ID);
		if (source == btn_cred) game.enterState(MenuCredits.ID);
		if (source == btn_quit) gameContainer.exit();
	}

}
