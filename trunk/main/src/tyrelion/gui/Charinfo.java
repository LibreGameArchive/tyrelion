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
import org.newdawn.slick.gui.AbstractComponent;

import tyrelion.InteractionManager;
import tyrelion.itemsystem.Food;
import tyrelion.objects.Player;

/**
 * @author daennart
 *
 */
public class Charinfo implements Observer{
	
	private GameContainer gameContainer;
	
	/** Background for the infobox */
	private Image background;
	
	private Food apple;
	
	private int posX = 0;
	private int posY = 0;
	
	boolean showCharinfo = false;

	public Charinfo(GameContainer container)
			throws SlickException {
		
		this.gameContainer = container;	
		
		background = new Image("res/img/gui/gui_charinfo.png");
		
		InteractionManager.getInstance().addObserver(this);
		}
      

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {		
		if (showCharinfo){
			posX = x;
			posY = y;
			//Render images
			g.drawImage(background, posX, posY);
			
			Player.getInstance().getCharacter().getInventory().render(g, posX+577, posY+236);
		}
	}
	
	
	
	
	


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable observable, Object input) {
		InteractionManager im = (InteractionManager) observable;
		
		if ("keyReleased".equals(input)){
			if (im.getKeyReleased_key() == Input.KEY_C) {
				showCharinfo = !showCharinfo;
			}
		}
		
		
	}

}
