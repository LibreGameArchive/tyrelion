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
	private ArrayList<Message> messages;
	
	/** Lines visible in the box */
	private int visibleLineCount = 6;
	
	/** Mximum length of one line */
	private int maxLength = 30;
	
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
		
		messages = new ArrayList<Message>();
		
      }

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {
		posX = x;
		posY = y;
		
		//Render images
		g.drawImage(background, posX, posY);
		
		ArrayList<Message> output = transform(messages);
		
		for (int i=visibleLineCount;i>startLine;i--){
			
			if (output.size()>=i) {
				
				font.getEffects().clear();
				
				switch (output.get(i-1).getCategory()){
				case 1:		// Systemmessages
					font.getEffects().add(new ColorEffect(Color.YELLOW));
					break;
				case 2:		// Questmessages
					font.getEffects().add(new ColorEffect(Color.DARK_GRAY));
					break;
				case 3:		// Fightmessages
					font.getEffects().add(new ColorEffect(Color.RED));
					break;
				case 4:		// Itemmessages
					font.getEffects().add(new ColorEffect(Color.WHITE));
					break;
				case 5:		// Experiencemessages
					font.getEffects().add(new ColorEffect(Color.BLUE));
					break;
				case 6:		// Misc. messages
					font.getEffects().add(new ColorEffect(Color.BLACK));
					break;
				default:	// everything else
					font.getEffects().add(new ColorEffect(Color.MAGENTA));
				}
				
				font.drawString(posX+20, posY+15*(i), output.get(i-1).getText());
				
			}
				
		}
		
	}
	
	public void print(String text, int category){
		messages.add(new Message(text, category));
	}
	
	public void print(Message message){
		messages.add(message);
	}
	
	private ArrayList<Message> transform(ArrayList<Message> messages){
		
		ArrayList<Message> transformedMessages = new ArrayList<Message>();
		
		String tempString;
		
		for (Message elem : messages) {
			if (elem.getText().length()<maxLength) {
				transformedMessages.add(elem);
			} else {
				tempString = elem.getText();
				while (tempString.length()>0) {
					if (tempString.length()<maxLength) {
						transformedMessages.add(new Message(tempString, elem.getCategory()));
						tempString = "";
					} else {
						transformedMessages.add(new Message(tempString.substring(0, maxLength), elem.getCategory()));
						tempString = tempString.substring(maxLength+1);
					}
				}
			}
				
		}
		
		return transformedMessages;
	}
	
	private void scrollUp(){
		if (startLine<transform(messages).size()) startLine++;
	}
	
	private void scrollDown(){
		if (startLine>0) startLine--;
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		if (source == gui_btn_up) scrollUp();
		if (source == gui_btn_down) scrollDown();
	}

}
