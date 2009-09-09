/**
 * 
 */
package tyrelion.gui;





import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import tyrelion.FontManager;

/**
 * @author daennart
 *
 */
public class Infobox implements ComponentListener{
	
	private GameContainer gameContainer;
	
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

	public Infobox(GameContainer container)
			throws SlickException {
		
		this.gameContainer = container;	
		
		messages = new ArrayList<Message>();
		
		if (container!=null){
		background = new Image("res/img/gui/gui_infobox.png");
		
		//Jeweils Zuweisung von MOAs und Rollover-Images für die Buttons
        gui_btn_up = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_up_1.png"), posX+200, posY+20, 20, 20, this);
        gui_btn_up.setMouseOverImage(new Image("res/img/gui/gui_btn_up_2.png"));
        
        gui_btn_down = new MouseOverArea(gameContainer, new Image("res/img/gui/gui_btn_down_1.png"), posX+200, posY+50, 20, 20, this);
        gui_btn_down.setMouseOverImage(new Image("res/img/gui/gui_btn_down_2.png"));
		}
      }

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {		
		posX = x;
		posY = y;
		//Render images
		g.drawImage(background, posX, posY);
		//Fit messages in output frame
		ArrayList<Message> output = transform(messages);
		//Place and render buttons
		gui_btn_up.setLocation(posX+220, posY+25);
		gui_btn_down.setLocation(posX+220, posY+75);
		if (output.size()>visibleLineCount){
			if (startLine>0) gui_btn_up.render(container, g);
			if (startLine<(output.size()-visibleLineCount)) gui_btn_down.render(container, g);
		}
		int pos = visibleLineCount;
		Color color = Color.black;
		for (int i=visibleLineCount+startLine;i>startLine;i--){
			if (output.size()>=i) {
				switch (output.get(i-1).getCategory()){
				case 1:		// Systemmessages
					color = Color.black;
					break;
				case 2:		// Questmessages
					color = Color.darkGray;
					break;
				case 3:		// Fightmessages
					color = Color.red;
					break;
				case 4:		// Itemmessages
					color = new Color(0x00762900);
					break;
				case 5:		// Experiencemessages
					color = new Color(0x00020657);
					break;
				default:	// everything else
					color = Color.magenta;
				}
				FontManager.getInstance().drawString(g, (float) posX+20,(float) posY+15*(pos), output.get(i-1).getText(), FontManager.SMALL, color);
			}
			pos--;
		}
	}
	
	public void print(Message message){
		messages.add(message);
		if (transform(messages).size()>visibleLineCount) startLine=transform(messages).size()-visibleLineCount;
	}
	
	public void print(String text, int category){
		print(new Message(text, category));
	}
	
	public void print(String text){
		print(text, Message.MISC);
	}
	
	private ArrayList<Message> transform(ArrayList<Message> messages){
		ArrayList<Message> transformedMessages = new ArrayList<Message>();
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Message> getMessages(){
		return (ArrayList<Message>) messages.clone();
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
