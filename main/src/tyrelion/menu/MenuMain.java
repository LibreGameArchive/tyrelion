/**
 * 
 */
package tyrelion.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.ExpMode;
import tyrelion.music.MusicManager;

/**
 * @author jahudi, daennart
 *
 */
public class MenuMain extends BasicGameState  implements ComponentListener{

	public static final int ID = 1;
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
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
	
	private boolean menuStarted = false;
	
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
		
		background = new Image("res/img/menu/main/mainmenu_bg.png");
		
		initGUI();
		
      }

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.clear();
		g.drawImage(background, 0, 0);
		renderGUI(container, g);
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}
	
	private void initGUI() throws SlickException{
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
	
	private void renderGUI(GameContainer container, Graphics g){
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
		if (source == btn_new) { menuStarted = false; game.enterState(ExpMode.ID); }
		//if (source == btn_load) game.enterState(MenuLoad.ID);
		if (source == btn_set) game.enterState(MenuSettings.ID);
		if (source == btn_cred) game.enterState(MenuCredits.ID);
		if (source == btn_quit) gameContainer.exit();
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		if (menuStarted == false) {
			MusicManager.getInstance().loop("menu");
			menuStarted = true;
		}
	}

}
