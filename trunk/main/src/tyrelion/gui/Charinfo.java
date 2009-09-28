/**
 * 
 */
package tyrelion.gui;





import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;

import tyrelion.Player;
import tyrelion.itemsystem.Food;

/**
 * @author daennart
 *
 */
public class Charinfo implements ComponentListener{
	
	private GameContainer gameContainer;
	
	/** Background for the infobox */
	private Image background;
	
	private Food apple;
	
	private int posX = 0;
	private int posY = 0;

	public Charinfo(GameContainer container)
			throws SlickException {
		
		this.gameContainer = container;	
		
		background = new Image("res/img/gui/gui_charinfo.png");
		}
      

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {		
		posX = x;
		posY = y;
		//Render images
		g.drawImage(background, posX, posY);
		
		Player.getInstance().getCharacter().getInventory().render(g, posX+570, posY+230);
	}
	
	

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		//if (source == gui_btn_up) scrollUp();
	}

}
