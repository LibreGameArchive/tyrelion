/**
 * 
 */
package tyrelion.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author imladriel
 *
 */
public class DisabledScreen{
	
	/** Shade for disabling background */
	private Image shade;
	
	

	public DisabledScreen()
			throws SlickException {shade = new Image("res/img/interaction/disabled_screen.png");
		}
      

	public void render(Graphics g)
			throws SlickException {		
			//Render and disables rest
			g.drawImage(shade, 0, 0);
	}
	
	
}
	
