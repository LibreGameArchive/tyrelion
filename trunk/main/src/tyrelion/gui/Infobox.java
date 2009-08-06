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
import org.newdawn.slick.font.effects.ShadowEffect;
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
	private int maxLength = 35;
	
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
	
	private UnicodeFont font1;
	private UnicodeFont font2;
	private UnicodeFont font3;
	private UnicodeFont font4;
	private UnicodeFont font5;
	private UnicodeFont font6;
	private UnicodeFont fontd;

	public Infobox(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		this.game = game;
		this.gameContainer = container;	
		
		
		
		messages = new ArrayList<Message>();
		
		background = new Image("res/img/gui/gui_infobox.png");
		
		font1 = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		font1.getEffects().add(new ColorEffect(Color.BLACK));
		
		font2 = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		font2.getEffects().add(new ColorEffect(Color.DARK_GRAY));
		
		font3 = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		font3.getEffects().add(new ColorEffect(Color.RED));
		
		font4 = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		font4.getEffects().add(new ColorEffect(new Color(0x00762900)));
		
		font5 = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		font5.getEffects().add(new ColorEffect(new Color(0x00020657)));
		
		font6 = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		font6.getEffects().add(new ColorEffect(Color.MAGENTA));
		
		fontd = new UnicodeFont(FontManager.getInstance().getFont(FontManager.SIMPLE).getFontFile(), 14, false, false);
		fontd.getEffects().add(new ColorEffect(Color.MAGENTA));
		
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons
        gui_btn_up = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_up_1.png"), posX+200, posY+20, 20, 20, this);
        gui_btn_up.setMouseOverImage(new Image("res/img/gui/gui_btn_up_2.png"));
        
        gui_btn_down = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_down_1.png"), posX+200, posY+50, 20, 20, this);
        gui_btn_down.setMouseOverImage(new Image("res/img/gui/gui_btn_down_2.png"));
		
      }

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {
		posX = x;
		posY = y;
		//Render images
		g.drawImage(background, posX, posY);
		//Fit messages in output frame
		ArrayList<Message> output = transform(messages);
		//PLace and render buttons
		gui_btn_up.setLocation(posX+220, posY+25);
		gui_btn_down.setLocation(posX+220, posY+75);
		if (output.size()>visibleLineCount){
			if (startLine>0) gui_btn_up.render(container, g);
			if (startLine<(output.size()-visibleLineCount)) gui_btn_down.render(container, g);
		}
		int pos = visibleLineCount;
		for (int i=visibleLineCount+startLine;i>startLine;i--){
			if (output.size()>=i) {
				switch (output.get(i-1).getCategory()){
				case 1:		// Systemmessages
					font1.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					font1.loadGlyphs();
					break;
				case 2:		// Questmessages
					font2.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					font2.loadGlyphs();
					break;
				case 3:		// Fightmessages
					font3.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					font3.loadGlyphs();
					break;
				case 4:		// Itemmessages
					font4.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					font4.loadGlyphs();
					break;
				case 5:		// Experiencemessages
					font5.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					font5.loadGlyphs();
					break;
				case 6:		// Misc. messages
					font6.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					font6.loadGlyphs();
					break;
				default:	// everything else
					fontd.drawString((float) posX+20,(float) posY+15*(pos), output.get(i-1).getText());
					fontd.loadGlyphs();
				}
				pos--;
			}
		}
	}
	
	public void print(String text, int category){
		messages.add(new Message(text, category));
		if (transform(messages).size()>visibleLineCount) startLine=transform(messages).size()-visibleLineCount;
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
				for (String line : breakLine(elem.getText()))
				transformedMessages.add(new Message(line, elem.getCategory()));
			}
		}
		return transformedMessages;
	}
	
	private ArrayList<String> breakLine(String text){
		String temp = "";
		
		char[] letters = text.toCharArray();
		ArrayList<String> words = new ArrayList<String>();
		for (int i=0; i<text.length();i++) {
			if (letters[i]==(" ".charAt(0))) { words.add(temp); temp=""; } else { temp+=letters[i]; }
		}
		words.add(temp);
		
		ArrayList<String> broken = new ArrayList<String>();
		String line = "";
		int count = 0;
		for (String elem: words) {
			count += elem.length();
			if (count<=maxLength) {
				line += elem + " ";	
				count += 1;
			} else {
				broken.add(line);
				line=elem + " ";
				count=elem.length()+1;
			}
		}
		if (!line.equals("")) {
			broken.add(line);
		}
		
		return broken;
	}
	
	private void scrollUp(){
		startLine--;
	}
	
	private void scrollDown(){
		startLine++;
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
