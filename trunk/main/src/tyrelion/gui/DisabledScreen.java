/**
 * 
 */
package tyrelion.gui;





import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import tyrelion.CursorManager;
import tyrelion.InteractionManager;
import tyrelion.TyrelionContainer;
import tyrelion.character.Inventory;
import tyrelion.character.Inventory.InventoryField;
import tyrelion.objects.Player;

/**
 * @author imladriel
 *
 */
public class DisabledScreen{
	
	private GameContainer conatainer;
	
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
	
