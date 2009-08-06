/**
 * 
 */
package tyrelion;

import java.awt.Color;
import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.ShadowEffect;

/**
 * @author imladriel
 *
 */
public class FontManager{
	
	public static String FANCY = "vinque_30";
	
	public static String SIMPLE = "garamond_14";
	
	private static FontManager instance = null;
	
	private HashMap<String, UnicodeFont> fonts;
	
	public FontManager() throws SlickException{
		
		fonts = new HashMap<String, UnicodeFont>();
		
		fonts.put("vinque_30", new UnicodeFont("res/fonts/vinque.ttf", 30, false, false));
		fonts.put("garamond_14", new UnicodeFont("res/fonts/jGara/jGara2.ttf", 14, false, false));
		
		for (UnicodeFont elem : fonts.values()) {
			elem.getEffects().add(new ColorEffect(Color.black));
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
		
		UnicodeFont tempFont = getFont(font, size);

		tempFont.getEffects().add(new ColorEffect(color));
		
		return tempFont;
		
	}
	
	/** Returns requested font in specified size.*/
	public UnicodeFont getFont(String font, int size){
		
		UnicodeFont tempFont;
		
			try {
				tempFont = new UnicodeFont(getFont(font).getFontFile(), size, false, false);
			} catch (SlickException e) {
				return fonts.get("garamond_14");
			}
		
		return tempFont;
		
	}
	
	/** Returns requested font in specified color.*/
	public UnicodeFont getFont(String font, Color color){
		
		UnicodeFont tempFont;
		
			try {
				tempFont = new UnicodeFont(getFont(font).getFontFile(), getFont(font).getFont().getSize(), false, false);
			} catch (SlickException e) {
				return fonts.get("garamond_14");
			}

		tempFont.getEffects().add(new ColorEffect(color));
		
		return tempFont;
		
	}
	
	/** Returns requested font in black.*/
	public UnicodeFont getFont(String font){
		
		if (fonts.containsKey(font))
			return fonts.get(font); 
		else
			return fonts.get("garamond_14");
		
	}
	
	/** Adds a font to the font.database */
	public void addFont(String key, UnicodeFont font){
		fonts.put(key, font);
	}
	
}
