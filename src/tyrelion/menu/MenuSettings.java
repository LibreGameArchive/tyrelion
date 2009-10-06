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

import tyrelion.music.MusicManager;

/**
 * @author jahudi, daennart
 *
 */
public class MenuSettings extends BasicGameState implements ComponentListener{

	public static final int ID = 3;
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	private Display display;
	
	/** Backgroundimage for settings-menu */
	private Image background;
	/** Hintergrundgrafik des Buttonbereichs. */
	private Image button_field_background;
	/** Heading for the button field. */
	private Image button_field_header;
	/** Heading for the Volumecontrol */
	private Image volume_header;
	/** Heading for the Fullscreen */
	private Image fullscreen_header;
	
	/** MOA for "Fullscreen"-Button toggled on */
	private MouseOverArea btn_fs_on;
	/** MOA for "Fullscreen"-Button toggled off */
	private MouseOverArea btn_fs_off;
	/** MOA for "Controls"-Button */
	private MouseOverArea btn_keys;
	
	/** MOA for "Back to main"-Button  */
	private MouseOverArea btn_back;
	
	/** Volumecontrol */
	private Slider volume_slider;
	
	/** Fullscreen-Mode (should be outsourced in future)*/
	private boolean isFullscreen = false;

	
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
		
		background = new Image("res/img/menu/settings/settings_bg.png");
	
		display = new Display(container);
		
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
		//Transmit slider-position to MusicManager
		MusicManager.getInstance().setVolume(volume_slider.getValue());
	}
	
	public void keyReleased(int i, char c) {
		if (i == Input.KEY_ESCAPE) {
			game.enterState(MenuMain.ID);
		}
	}
	
	private void initGUI() throws SlickException{		
		//Hintergrund Grafik für den Button-Bereich festlegen
		button_field_background = new Image("res/img/menu/main/mainmenu_box.png");
		
		//Überschrift für den Button-Bereich festlegen
		button_field_header = new Image("res/img/menu/settings/settings_header.png");
		
		//Volume heading
		volume_header = new Image("res/img/menu/settings/settings_volume.png");
		//Fullscreen heading
		fullscreen_header = new Image("res/img/menu/settings/settings_fs.png");
		
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons
		btn_fs_on = new MouseOverArea(gameContainer, new Image("res/img/menu/settings/buttons/settings_fs_off_1.png"), 615, 610, 30, 30, this);
        btn_fs_on.setMouseOverImage(new Image("res/img/menu/settings/buttons/settings_fs_off_2.png"));
        btn_fs_off = new MouseOverArea(gameContainer, new Image("res/img/menu/settings/buttons/settings_fs_on_1.png"), 615, 610, 30, 30, this);
        btn_fs_off.setMouseOverImage(new Image("res/img/menu/settings/buttons/settings_fs_on_2.png"));
        btn_keys = new MouseOverArea(gameContainer, new Image("res/img/menu/settings/buttons/settings_button_controls_1.png"), 430, 605, 400, 50, this);
        btn_keys.setMouseOverImage(new Image("res/img/menu/settings/buttons/settings_button_controls_2.png"));        
        btn_back = new MouseOverArea(gameContainer, new Image("res/img/menu/main/buttons/mainmenu_button_back_1.png"), 590, 700, 400, 50, this);
        btn_back.setMouseOverImage(new Image("res/img/menu/main/buttons/mainmenu_button_back_2.png"));
        
        Container slider = new Container();
        slider.setSize(250, 10);
        slider.setLocation(575, 575);
        
        volume_slider = new Slider(Slider.HORIZONTAL);
        volume_slider.setLocation(0, 0);
        volume_slider.setHeight(10);
        volume_slider.setWidth(250);
        volume_slider.setOpaque(true);
        volume_slider.setBackground(new Color(0x00762900));
        volume_slider.setForeground(new Color(0x00461800));
        volume_slider.setValue(0.5f);
        
        slider.add(volume_slider);
       
        display.add(slider);
	}
	
	private void renderGUI(GameContainer container, Graphics g){
		//Rendern des HIntergrundes für die Buttons
		g.drawImage(button_field_background, 410, 360);
		
		//Rendern der Überschriften
		g.drawImage(button_field_header, 430, 405);
		g.drawImage(volume_header, 430, 460);
		g.drawImage(fullscreen_header, 520, 597);
		
		//Rendern der Buttons
		btn_keys.render(container, g);
		btn_back.render(container, g);
		if (isFullscreen) {
			btn_fs_off.render(container, g);
		} else {
			btn_fs_on.render(container, g);
		}
		
		display.render(container, g);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == btn_fs_on || source == btn_fs_off) try{toggleFS();} catch (SlickException e) {}
		if (source == btn_keys) game.enterState(MenuControls.ID);
		if (source == btn_back) game.enterState(MenuMain.ID);
	}
	
	public void toggleFS() throws SlickException{
		gameContainer.setFullscreen(!isFullscreen);
		isFullscreen = !isFullscreen;
	}

}

