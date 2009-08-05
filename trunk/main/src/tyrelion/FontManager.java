/**
 * 
 */
package tyrelion;

import java.awt.Color;
import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * @author imladriel
 *
 */
public class FontManager{
	
	public static String FANCY = "vinque_30";
	
	public static String SIMPLE = "garamond_11";
	
	private static FontManager instance = null;
	
	private HashMap<String, UnicodeFont> fonts;
	
	public FontManager() throws SlickException{
		
		fonts = new HashMap<String, UnicodeFont>();
		
		fonts.put("vinque_30", new UnicodeFont("res/fonts/vinque.ttf", 30, false, false));
		fonts.put("garamond_11", new UnicodeFont("res/fonts/jGara/jGara2.ttf", 11, false, false));
		
		for (UnicodeFont elem : fonts.values()) {
			elem.getEffects().add(new ColorEffect(Color.black));
			elem.addGlyphs("abcdefghijklmnopqrstovwxyzABCDEFGHIJKLMNOPQRSTUVWXYZäöü.");
			elem.loadGlyphs(1000);
		}
	}
	
	public static FontManager getInstance() throws SlickException{
		if (instance == null) {
			instance = new FontManager();
		}
		return instance;
	}
	
	/** Returns requested font in specified color and size.*/
	public UnicodeFont getFont(String font, Color color, int size){
		
		UnicodeFont tempFont;
		
			try {
				tempFont = new UnicodeFont(getFont(font).getFontFile(), size, false, false);
			} catch (SlickException e) {
				return fonts.get("garamond_11");
			}

		tempFont.getEffects().add(new ColorEffect(color));
		
		return tempFont;
		
	}
	
	/** Returns requested font in specified size.*/
	public UnicodeFont getFont(String font, int size){
		
		UnicodeFont tempFont;
		
			try {
				tempFont = new UnicodeFont(getFont(font).getFontFile(), size, false, false);
			} catch (SlickException e) {
				return fonts.get("garamond_11");
			}
		
		return tempFont;
		
	}
	
	/** Returns requested font in specified color.*/
	public UnicodeFont getFont(String font, Color color){
		
		UnicodeFont tempFont;
		
			try {
				tempFont = new UnicodeFont(getFont(font).getFontFile(), getFont(font).getFont().getSize(), false, false);
			} catch (SlickException e) {
				return fonts.get("garamond_11");
			}

		tempFont.getEffects().add(new ColorEffect(color));
		
		return tempFont;
		
	}
	
	/** Returns requested font in black.*/
	public UnicodeFont getFont(String font){
		
		if (fonts.containsKey(font))
			return fonts.get(font); 
		else
			return fonts.get("garamond_11");
		
	}
	
	/** Adds a font to the font.database */
	public void addFont(String key, UnicodeFont font){
		fonts.put(key, font);
	}
	
}
