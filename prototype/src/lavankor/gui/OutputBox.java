/**
 * 
 */
package lavankor.gui;

import java.util.ArrayList;

import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Administrator
 *
 */
public class OutputBox {
	
	/** Speicher der anzuzeigenden Informationen */
	private ArrayList<String> lines;
	
	private boolean collapsed = false;
	
	private int visibleLineCount = 2;
	
	private int maxLength = 60;
	
	private int startLine = 0 ;
	
	private Display display;
	
	private Container content;
	
	private Button upBtn;
	
	private Button downBtn;
	
	private Button colBtn;
	
	private Color textColor = Color.white;
	
	private int posX = 350;
	private int posY = 700;
	
	/** Der Rahmen des Feldes */
	private Image bg_image;
	/** Das Rechteck stellt den schwarzen Hintergrund dar.*/
	private Rectangle bg;
	/** Option, ob die Ausgabebox gerade angezeigt werden soll.*/
	private boolean showBox = true;
	/** Das Spritesheet das die Grundgrafiken zum GUI enthält.*/
	private PackedSpriteSheet gui;
	
	/**
	 * 
	 */
	public OutputBox() throws SlickException {
		
		gui = new PackedSpriteSheet("res/images/interface.def",new Color(255,0,204));
		bg_image = gui.getSprite("status_rahmen_klein");
		
		bg = new Rectangle(posX,posY, 300, 50);
		
		lines = new ArrayList<String>();
		
		content = new Container();
        content.setSize(320, 150);
        content.setLocation(posX, posY);
        
        try {
        	colBtn = new Button(new Image("res/images/dialog/scrollbtn.jpg"));
        	colBtn.setLocation(300, 0);
        	colBtn.setPadding(0);
        	colBtn.setDownImage(new Image("res/images/dialog/scrollbtn_2.jpg"));
        	colBtn.pack();
        	colBtn.setText("^");
        	colBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					collapse();
				}
			});
        	colBtn.setVisible(true);
			content.add(colBtn);
			
			upBtn = new Button(new Image("res/images/dialog/scrollbtn.jpg"));
			upBtn.setLocation(275, 10);
			upBtn.setPadding(0);
			upBtn.setDownImage(new Image("res/images/dialog/scrollbtn_2.jpg"));
			upBtn.pack();
			upBtn.setText("^");
			upBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					scrollUp(1);
				}
			});
			content.add(upBtn);
			
			downBtn = new Button(new Image("res/images/dialog/scrollbtn.jpg"));
			downBtn.setLocation(275, 125);
			downBtn.setPadding(0);
			downBtn.setDownImage(new Image("res/images/dialog/scrollbtn_2.jpg"));
			downBtn.pack();
			downBtn.setText("v");
			downBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					scrollDown(1);
				}
			});
			content.add(downBtn);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void print(String info){
		textColor = Color.white;
		
		if (info.length()<maxLength){
			lines.add(info);
		} else { 
			lines.addAll(transform(info, maxLength));
		}
		
		if (lines.size()>=visibleLineCount){ 
			startLine = lines.size()-visibleLineCount; 
		} else { 
			startLine=0; 
		}
	}
	
	public void print(String info, Color color){
		textColor = color;
		
		if (info.length()<maxLength){
			lines.add(info);
		} else { 
			lines.addAll(transform(info, maxLength));
		}
		
		if (lines.size()>=visibleLineCount){ 
			startLine = lines.size()-visibleLineCount; 
		} else { 
			startLine=0; 
		}
	}
	
	public void collapse(){
		if (!collapsed) {
			posY = 600;
			content.setLocation(posX,posY);
			content.setSize(320, 150);
			bg_image = gui.getSprite("status_rahmen");
			visibleLineCount = 6;
			colBtn.setText("v");
			bg.setHeight(150);
			bg.setLocation(posX,posY);
			downBtn.setLocation(275, 125);
			if (lines.size()>=visibleLineCount){ 
				startLine = lines.size()-visibleLineCount; 
			} else { 
				startLine=0; 
			}
		} else {
			posY = 700;
			content.setLocation(posX,posY);
			content.setSize(320, 50);
			bg_image = gui.getSprite("status_rahmen_klein");
			visibleLineCount = 2;
			colBtn.setText("^");
			bg.setHeight(50);
			bg.setLocation(posX,posY);
			downBtn.setLocation(275, 25);
			if (lines.size()>=visibleLineCount){ 
				startLine = lines.size()-visibleLineCount; 
			} else { 
				startLine=0; 
			}
		}
		collapsed = !collapsed;
	}
	
	
	/** Methode zum anzeigen der Ausgabebox
	 * @param g Graphics in denen angezeigt wird.
	 */
	public void draw (GameContainer container, Graphics g) {
		
		g.setColor(new Color(0,0,0,0.7f));
		g.fill(bg);
		g.drawImage(bg_image,posX,posY);
		g.setColor(Color.white);
		
		g.setFont(RessourceManager.getInstance().getFont(RessourceStrings.FONT_ARIAL_12_WHITE_BOLD));
		
		if (startLine==0) {
			upBtn.setVisible(false);
		} else { 
			upBtn.setVisible(true);
		}
		
		if (lines.size()>visibleLineCount+startLine && lines.size()>visibleLineCount){
			downBtn.setVisible(true);
		} else { 
			downBtn.setVisible(false);
		}
		
		int posY = this.posY+10;
		for (String elem: showTextPart(lines,visibleLineCount)){
			g.setColor(textColor);
			g.drawString(elem, posX+10, posY);
			posY+=22;
		}
		
		
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
	
	/** Methode zur Abfrage ob angezeigt wird.
	 * @return true = Box wird angezeigt; false = wird nicht angezeigt
	 */
	public boolean isShowBox() {
		return showBox;
	}

	/** Ändern der Anzeigeoption ob angezeigt wird.
	 * @param showBox true = anzeigen; false = nicht anzeigen
	 */
	public void setShowBox(boolean showBox) {
		this.showBox = showBox;
	}
	
	
}
