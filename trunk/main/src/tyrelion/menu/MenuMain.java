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
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.music.MusicManager;

/**
 * @author jahudi
 *
 */
public class MenuMain extends BasicGameState  implements ComponentListener{

	public static final int ID = 1;
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
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
				
		LoadingList.setDeferredLoading(true);
		
		gameContainer.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);
		
		loading = new Image("res/img/menu/main/loading.jpg");
		background = new Image("res/img/menu/main/mainmenu_bg.png");
	
		initButtonField();
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
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
			renderButtonField(container, g);
			
			
		}
		
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
				MusicManager.getInstance().play("menu");
			}
		}

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
