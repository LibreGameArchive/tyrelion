/**
 * 
 */
package tyrelion.gui;


import java.awt.Color;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import tyrelion.FontManager;
import tyrelion.menu.MenuCredits;
import tyrelion.menu.MenuLoad;
import tyrelion.menu.MenuMain;
import tyrelion.menu.MenuSettings;

/**
 * @author daennart
 *
 */
public class Infobox implements ComponentListener{
	
	private StateBasedGame game;
	private GameContainer gameContainer;
	
	/** Font for text. */
	private UnicodeFont font;
	
	/** Collection of Messages */
	private ArrayList<String> messages;
	
	/** Lines visible in the box */
	private int visibleLineCount = 2;
	
	/** Mximum length of one line */
	private int maxLength = 60;
	
	/** First line to be rendered */
	private int startLine = 0 ;
	
	
	/** Background for the infobox */
	private Image background;
	
	/** MOA for scrolling messages upward */
	private MouseOverArea gui_btn_up;
	/** MOA for scrolling messages downward */
	private MouseOverArea gui_btn_down;
	
	private int posX = 0;
	private int posY = 0;
	

	public Infobox(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		this.game = game;
		this.gameContainer = container;	
		
		font = FontManager.getInstance().getFont(FontManager.SIMPLE);
		
		background = new Image("res/img/gui/gui_infobox.png");
		
      }

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {
		posX = x;
		posY = y;
		
		//Render images
		g.drawImage(background, posX, posY);
		
		for (int i=startLine;i==5;i++){
			font.drawString(posX+20, posY+20*i, messages.get(i));
		}
		
	}
	
	public void print(String text){
		messages.add(text);
	}
	
	private ArrayList<String> transform(ArrayList<String> messages){
		
		ArrayList<String> transformedMessages = new ArrayList<String>();
		
		
		
		return transformedMessages;
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == gui_btn_up) game.enterState(MenuMain.ID);
		if (source == gui_btn_down) game.enterState(MenuMain.ID);
	}

}
