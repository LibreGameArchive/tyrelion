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

/**
 * @author daennart
 *
 */
public class Charinfo implements ComponentListener{
	
	private GameContainer gameContainer;
	
	/** Background for the infobox */
	private Image background;
	
	private int posX = 0;
	private int posY = 0;

	public Charinfo(GameContainer container)
			throws SlickException {
		
		this.gameContainer = container;	
		
		}
      

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {		
		posX = x;
		posY = y;
		//Render images
		g.drawImage(background, posX, posY);
		
	}
	
	

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		//Abfrage des aktivierten Buttons und ausführen der zugehörigen Aktion
		//if (source == gui_btn_up) scrollUp();
	}

}
