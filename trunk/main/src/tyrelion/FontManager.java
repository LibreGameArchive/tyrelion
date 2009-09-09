/**
 * 
 */
package tyrelion;



import java.util.HashMap;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * @author imladriel
 *
 */
public class FontManager{
	
	public static int FANCY = 1;
	public static int SIMPLE = 2;
	
	public static int SMALL = 1;
	public static int MEDIUM = 2;
	public static int LARGE = 3;
	
	private int defaultStyle = SIMPLE;
	
	private Color defaultColor = Color.black;
	
	private float defaultSize = MEDIUM;
	
	private static FontManager instance = null;
	
	private HashMap<Integer, AngelCodeFont> fancy_fonts;
	private HashMap<Integer, AngelCodeFont> simple_fonts;
	
	public FontManager() throws SlickException{
		
		fancy_fonts = new HashMap<Integer, AngelCodeFont>();
		simple_fonts = new HashMap<Integer, AngelCodeFont>();
		initFonts();
	}
	
	public static FontManager getInstance() throws SlickException{
		if (instance == null) {
			instance = new FontManager();
		}
		return instance;
	}
	
	private void initFonts() throws SlickException{
		fancy_fonts.put(1, new AngelCodeFont("res/fonts/fancy18.fnt","res/fonts/fancy18.png"));
		fancy_fonts.put(2, new AngelCodeFont("res/fonts/fancy24.fnt","res/fonts/fancy24.png"));
		fancy_fonts.put(3, new AngelCodeFont("res/fonts/fancy36.fnt","res/fonts/fancy36.png"));
		
		simple_fonts.put(1, new AngelCodeFont("res/fonts/simple11.fnt","res/fonts/simple11_0.png"));
		simple_fonts.put(2, new AngelCodeFont("res/fonts/simple14.fnt","res/fonts/simple14_0.png"));
		simple_fonts.put(3, new AngelCodeFont("res/fonts/simple18.fnt","res/fonts/simple18_0.png"));
	}
	
	/** Draws String. */
	public void drawString(Graphics g, float x, float y, String text, int style, float size, Color color){
		Font oldFont = g.getFont();
		Color oldColor = g.getColor();
		
		Font newFont = chooseFonts(style).get((int) size);
		
		g.setFont(newFont);
		g.setColor(color);
		
		g.drawString(text, x, y);
		
		g.setFont(oldFont);
		g.setColor(oldColor);
	}
	
	public void drawString(Graphics g, float x, float y, String text, int style, float size){
		drawString(g, x, y, text, style, size, defaultColor);
	}
	
	public void drawString(Graphics g, float x, float y, String text, int style, Color color){
		drawString(g, x, y, text, style, defaultStyle, color);
	}
	
	public void drawString(Graphics g, float x, float y, String text, float size, Color color){
		drawString(g, x, y, text, defaultStyle, size, color);
	}
	
	public void drawString(Graphics g, float x, float y, String text, int style){
		drawString(g, x, y, text, style, defaultSize, defaultColor);
	}
	
	public void drawString(Graphics g, float x, float y, String text, float size){
		drawString(g, x, y, text, defaultStyle, size, defaultColor);
	}
	
	public void drawString(Graphics g, float x, float y, String text, Color color){
		drawString(g, x, y, text, defaultStyle, defaultSize, color);
	}
	
	public void drawString(Graphics g, float x, float y, String text){
		drawString(g, x, y, text, defaultStyle, defaultSize, defaultColor);
	}
	
	/** Returns requested AngelCodeFont.*/
	public Font getFont(int style, float size){
		return chooseFonts(style).get(size);
	}
	
	/** Returns all Fonts as a Clone.*/
	public HashMap<Integer, AngelCodeFont> getFonts(){
		HashMap<Integer, AngelCodeFont> temp = (HashMap<Integer, AngelCodeFont>) fancy_fonts.clone();
		temp.putAll(simple_fonts);
		return temp;
	}
	
	/** Adds a font to the font.database */
	public void addFont(int style, AngelCodeFont font, int size){
		chooseFonts(style).put(size, font);
	}
	
	/** Chooses requested fontmap.*/
	private HashMap<Integer, AngelCodeFont> chooseFonts(int style){
		switch (style){
		case 1:
			return fancy_fonts;
		case 2:
			return simple_fonts;
		default:
			return simple_fonts;
		}
	}
	
}
