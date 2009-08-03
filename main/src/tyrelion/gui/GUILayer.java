/**
 * 
 */
package tyrelion.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.menu.MenuCredits;
import tyrelion.menu.MenuLoad;
import tyrelion.menu.MenuMain;
import tyrelion.menu.MenuSettings;

/**
 * @author jahudi, daennart
 *
 */
public class GUILayer implements ComponentListener{
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
	/** Background for avatar-area */
	private Image gui_avatar;
	/** Background for avatar-area */
	private Image gui_menu;
	/** Background for avatar-area */
	private Image gui_minimap;
	/** Background for avatar-area */
	private Image gui_quickslots;
	/** Background for the infobox */
	private Image gui_infobox;
	
	/** MOA for ingame-menu-button */
	private MouseOverArea gui_btn_menu;
	/** MOA for questlog-button */
	private MouseOverArea gui_btn_questlog;
	
	/** MOA for ingame-menu-button */
	private MouseOverArea gui_btn_back;
	/** MOA for ingame-menu-button */
	private MouseOverArea gui_btn_save;
	/** MOA for ingame-menu-button */
	private MouseOverArea gui_btn_load;
	/** MOA for ingame-menu-button */
	private MouseOverArea gui_btn_settings;
	/** MOA for ingame-menu-button */
	private MouseOverArea gui_btn_quit;
	
	/** Should the menu be drawn */
	private boolean isShowMenu = false;

	public GUILayer(GameContainer container)
			throws SlickException {
		
		this.gameContainer = container;	
		
		initGUI();
		
      }

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		//Render images
		g.drawImage(gui_avatar, 10, 0);
		g.drawImage(gui_quickslots, 450, 699);
		g.drawImage(gui_minimap, 725, 512);
		g.drawImage(gui_infobox, 15, 620);
		
		//Render buttons
		gui_btn_menu.render(container, g);
		gui_btn_questlog.render(container, g);
		
		//Check if menu should be drawn and render it
		if (isShowMenu) showMenu(container, g);
		
	}
	
	private void initGUI() throws SlickException{
		//Initalisation of the GUI-images
		gui_avatar = new Image("res/img/gui/gui_avatar.png");
		gui_menu = new Image("res/img/gui/gui_menu.png");
		gui_minimap = new Image("res/img/gui/gui_minimap.png");
		gui_quickslots = new Image("res/img/gui/gui_quickslots.png");
		gui_infobox = new Image("res/img/gui/gui_infobox.png");
		
		//button initialisation
		gui_btn_menu = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_menu_1.png"), 808, 525, 29, 27, this);
		gui_btn_menu.setMouseOverImage(new Image("res/img/gui/gui_btn_menu_2.png"));
        
		gui_btn_questlog = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_questlog_1.png"), 380, 672, 195, 96, this);
		gui_btn_questlog.setMouseOverImage(new Image("res/img/gui/gui_btn_questlog_2.png"));
		
		initMenu();
	}
	
	private void initMenu() throws SlickException{
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons
		gui_btn_back = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_back_1.png"), 855, 200, 160, 30, this);
		gui_btn_back.setMouseOverImage(new Image("res/img/gui/gui_btn_back_2.png"));
		
		gui_btn_save = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_save_1.png"), 855, 230, 160, 30, this);
		gui_btn_save.setMouseOverImage(new Image("res/img/gui/gui_btn_save_2.png"));
        
		gui_btn_load = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_load_1.png"), 855, 260, 160, 30, this);
		gui_btn_load.setMouseOverImage(new Image("res/img/gui/gui_btn_load_2.png"));
		
		gui_btn_settings = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_settings_1.png"), 855, 290, 160, 30, this);
		gui_btn_settings.setMouseOverImage(new Image("res/img/gui/gui_btn_settings_2.png"));
		
		gui_btn_quit = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_quit_1.png"), 855, 320, 160, 30, this);
		gui_btn_quit.setMouseOverImage(new Image("res/img/gui/gui_btn_quit_2.png"));
	}
	
	private void showMenu(GameContainer container, Graphics g){
		g.drawImage(gui_menu, 819, 150);
		
		gui_btn_back.render(container, g);
		gui_btn_save.render(container, g);
		gui_btn_load.render(container, g);
		gui_btn_settings.render(container, g);
		gui_btn_quit.render(container, g);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == gui_btn_menu) isShowMenu=!isShowMenu;
	//	if (source == gui_btn_questlog) game.enterState(MenuMain.ID);
		if (source == gui_btn_back) isShowMenu=false;
		if (source == gui_btn_save) game.enterState(MenuCredits.ID);
		if (source == gui_btn_load) game.enterState(MenuLoad.ID);
		if (source == gui_btn_settings) game.enterState(MenuSettings.ID);
		if (source == gui_btn_quit) game.enterState(MenuMain.ID);
	}

}
