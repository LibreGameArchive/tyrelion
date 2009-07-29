/**
 * 
 */
package tyrelion.menu;

import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.Slider;

import org.newdawn.slick.Color;
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
 * @author jahudi
 *
 */
public class MenuSettings extends BasicGameState implements ComponentListener{

	public static final int ID = 3;
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
	/** Hintergrundgrafik des Hauptmenüs. */
	private Image background;
	/** Hintergrundgrafik des Buttonbereichs. */
	private Image button_field_background;
	/** Überschrift des Buttonbereichs. */
	private Image button_field_header;
	/** Überschrift der Lautstärkeregelung. */
	private Image volume_header;
	
	/** MOA für den Button "Tastaturbelegung" */
	private MouseOverArea btn_keys;
	
	/** MOA für den Button "zurück" */
	private MouseOverArea btn_back;
	
	private Slider slider;
	private Display display;
	
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
		
		background = new Image("res/img/menu/settings/settings_bg.png");
	
		display = new Display(container);
		
		initButtonField();
		

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.clear();
		g.drawImage(background, 0, 0);
		
		renderButtonField(container, g);
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
	
	private void initButtonField() throws SlickException{		
		//Hintergrund Grafik für den Button-Bereich festlegen
		button_field_background = new Image("res/img/menu/main/mainmenu_box.png");
		
		//Hintergrund Grafik für den Button-Bereich festlegen
		button_field_header = new Image("res/img/menu/settings/settings_header.png");
		
		//Hintergrund Grafik für den Button-Bereich festlegen
		volume_header = new Image("res/img/menu/settings/settings_volume.png");
		
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons
        btn_keys = new MouseOverArea(gameContainer, new Image("res/img/menu/settings/buttons/settings_button_controls_1.png"), 430, 605, 400, 50, this);
        btn_keys.setMouseOverImage(new Image("res/img/menu/settings/buttons/settings_button_controls_2.png"));        
        btn_back = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_back_1.png"), 590, 700, 400, 50, this);
        btn_back.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_back_2.png"));
        
        Container content = new Container();
        content.setSize(250, 10);
        content.setLocation(505, 530);
        
        slider = new Slider(Slider.HORIZONTAL);
        slider.setLocation(0, 0);
        slider.setHeight(10);
        slider.setWidth(250);
        slider.setOpaque(true);
        slider.setBackground(new Color(0x00762900));
        slider.setForeground(new Color(0x00461800));
        slider.setValue(0.5f);
        
        content.add(slider);
       
        display.add(content);
	}
	
	private void renderButtonField(GameContainer container, Graphics g){
		//Rendern des HIntergrundes für die Buttons
		g.drawImage(button_field_background, 410, 360);
		
		//Rendern der Überschriften
		g.drawImage(button_field_header, 430, 405);
		g.drawImage(volume_header, 430, 460);
		
		//Rendern der Buttons
		btn_keys.render(container, g);
		btn_back.render(container, g);
		
		display.render(container, g);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == btn_keys) game.enterState(MenuControls.ID);
		if (source == btn_back) game.enterState(MenuMain.ID);
	}

}

