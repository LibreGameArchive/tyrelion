package lavankor.dialog;

import java.util.ArrayList;
import java.util.Observable;

import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/** Diese Klasse repräsentiert einen Dialog.
 * Sie kann sich selbst zeichnen und nach einer Auswahl der Antwort zum nächsten Dialog weiterschalten.
 * @author diceware
 * @version 0.1a
 */
public class Dialog extends Observable{
	
	private final int ID;
	
	private Image background;

	private String text = "";
	
	private int textLineCount = 0;
	
	private int visibleLineCount = 8;
	
	private int startLine = 0 ;
	
	private Image picture;
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	private Display display;
	
	private Container content;
	
	private Button upBtn;
	
	private Button downBtn;
	
	private static boolean showDialog;
	
	/** Konstruktor der Klasse.
	 * @param text Der Text, der vom Dialogpartner gesprochen wird.
	 * @param picture Das Bild, das im ialogfenster angezeigt wird.
	 * @param answers Ein Set aus möglichen Antworten des Spielers.
	 */
	public Dialog(Integer id, String text, Image picture, ArrayList<Answer> answers) {
		this.ID = id;
		this.text = text;
		this.picture = picture;
		showDialog = false;
		
		try {
			background = new Image("res/images/dialog/dialog.jpg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		textLineCount = transform(text,20).size();
		
        
        try {
			upBtn = new Button(new Image("res/images/dialog/scrollbtn.jpg"));
			upBtn.setLocation(215, 15);
			upBtn.setPadding(0);
			upBtn.setDownImage(new Image("res/images/dialog/scrollbtn_2.jpg"));
			upBtn.pack();
			upBtn.setText("^");
			upBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					scrollUp(1);
				}
			});
			
			downBtn = new Button(
					new Image("res/images/dialog/scrollbtn.jpg"));
			downBtn.setLocation(213, 231);
			downBtn.setPadding(0);
			downBtn.setDownImage(new Image("res/images/dialog/scrollbtn_2.jpg"));
			downBtn.pack();
			downBtn.setText("v");
			downBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					scrollDown(1);
				}
			});
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
		content = new Container();
        content.setSize(400, 400);
        content.setLocation(550, 50);
		content.add(upBtn);
		content.add(downBtn);
		int posY = 275;
		for (final Answer elem: answers){
			try {
			Button btn = new Button(new Image("res/images/dialog/dialogbutton20.jpg"));
			btn.setLocation(25, posY);
	        btn.setPadding(0);
			btn.setDownImage(new Image("res/images/dialog/dialogbutton20_2.jpg"));
	        btn.pack(); 
	        btn.setText(elem.toString());
	        btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					elem.press();
				}
	        }); 
	        buttons.add(btn);
	        content.add(btn);
	        posY+=30;
	        } catch (SlickException e) {
				e.printStackTrace();
			}
		}
	 	
        
	}

	private void initButtons(){
		for(Button btn: buttons){
			btn.setFont(RessourceManager.getInstance().getFont(RessourceStrings.FONT_12_BLACK));
		}
	}
	
	
	public void draw (GameContainer container, Graphics g) {
		
		g.drawImage(background, 550,50);
		
		if (picture!=null) {
			g.drawImage(picture, 790, 70);
		}
		
		g.setFont(RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK));
		
		if (startLine==0) {
			upBtn.setVisible(false);
		} else { 
			upBtn.setVisible(true);
		}
		
		if (textLineCount>visibleLineCount+startLine && textLineCount>visibleLineCount){
			downBtn.setVisible(true);
		} else { 
			downBtn.setVisible(false);
		}
		
		int posY = 60;
		for (String elem: showTextPart(transform(text,20),visibleLineCount)){
			g.drawString(elem, 575, posY);
			posY+=30;
		}
		
		initButtons();
		display = new Display(container);
		display.add(content);
		display.render(container, g);
		
	}
	
	public ArrayList<String> showTextPart(ArrayList<String> text, int lines){
		
		ArrayList<String> output = new ArrayList<String>();
		int shownLines;
		if (lines>text.size()) { shownLines = text.size(); } else { shownLines = lines;}
		for (int i = startLine; i-startLine<shownLines; i++){
			output.add(text.get(i));
		}
		
		return output;
	}
	
	public void scrollDown(int lines){
		startLine += lines;
	}
	
	public void scrollUp(int lines){
		if (startLine>=lines) {
			startLine -= lines;	
		} else {
			startLine = 0;
		}
		
	}
	
	public static ArrayList<String> transform(String t, int length){
		String temp = "";
		char[] letters = t.toCharArray();
		ArrayList<String> txt = new ArrayList<String>();
		for (int i=0; i<t.length();i++) {
			if (letters[i]==(" ".charAt(0))) { txt.add(temp); temp=""; } else { temp+=letters[i]; }
		}
		txt.add(temp);
		
		ArrayList<String> output = new ArrayList<String>();
		String line = "";
		int count = 0;
		for (String elem: txt) {
			count += elem.length();
			if (count<=length) {
				line += elem + " ";	
				count += 1;
			} else {
				output.add(line);
				line=elem + " ";
				count=elem.length()+1;
			}
		}
		if (!line.equals("")) {
			output.add(line);
		}
		
		return output;
	}


	public boolean isShowDialog() {
		return showDialog;
	}


	@SuppressWarnings("static-access")
	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
	
	public static final void close() {
		showDialog = false;
	}

	public int getID() {
		return ID;
	}

}
