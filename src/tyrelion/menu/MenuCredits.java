/**
 * 
 */
package tyrelion.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author jahudi, daennart
 *
 */
public class MenuCredits extends BasicGameState implements ComponentListener{

	public static final int ID = 5;
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
	/** Hintergrundgrafik des Hauptmenüs. */
	private Image background;
	
	/** MOA für den Button "zurück" */
	private MouseOverArea btn_back;
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
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
		
		gameContainer.setMouseCursor("res/img/mouse/cursor_sword.png", 2, 2);
		
		background = new Image("res/img/menu/credits/credits_bg.png");
	
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
		// TODO Auto-generated method stub

	}
	
	public void keyReleased(int i, char c) {
		if (i == Input.KEY_ESCAPE) {
			game.enterState(MenuMain.ID);
		}
	}
	
	private void initGUI() throws SlickException{		
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons        
        btn_back = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_back_1.png"), 590, 700, 400, 50, this);
        btn_back.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_back_2.png"));
	}
	
	private void renderGUI(GameContainer container, Graphics g){
		//Rendern der Buttons
		btn_back.render(container, g);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == btn_back) game.enterState(MenuMain.ID);
	}

}
