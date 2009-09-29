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

import tyrelion.InteractionManager;
import tyrelion.character.Inventory;
import tyrelion.character.Inventory.InventoryField;
import tyrelion.itemsystem.Food;
import tyrelion.objects.Player;

/**
 * @author daennart
 *
 */
public class Charinfo implements Observer{
	
	private GameContainer gameContainer;
	private Graphics graphics;
	
	/** Background for the infobox */
	private Image background;
	
	Inventory inventory;
	
	private Food apple;
	
	private int posX = 0;
	private int posY = 0;
	
	int tempx;
	int tempy;
	
	boolean showCharinfo = false;
	
	boolean gedrückt = false;
	InventoryField itemAtCursor = null;
	InventoryField item = null;

	public Charinfo(GameContainer container)
			throws SlickException {
		
		this.gameContainer = container;	
		
		background = new Image("res/img/gui/gui_charinfo.png");
		
		inventory = Player.getInstance().getCharacter().getInventory();
		
		InteractionManager.getInstance().addObserver(this);
		}
      

	public void render(GameContainer container, Graphics g, int x, int y)
			throws SlickException {		
		if (showCharinfo){
			posX = x;
			posY = y;
			//Render images
			g.drawImage(background, posX, posY);
			
			g.drawRect(posX+577, posY+236, 56*4, 54*6);
			
			inventory.render(g, posX+577, posY+236);

			if (gedrückt) graphics.drawString("X: "+Integer.toString(tempx-posX-577)+" Y: "+Integer.toString(tempy-posX-236), 500, 500);
			if (gedrückt) graphics.drawString("X: "+Integer.toString((tempx-posX-577) / 56)+" Y: "+Integer.toString((tempy-posX-236) / 54), 500, 550);
			if (itemAtCursor!=null) itemAtCursor.render(graphics, tempx-25, tempy-25);
			
			graphics = g;
		}
	}
	
	public void createClickableInventory(Inventory inventory){
		
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
		
		if ("mousePressed".equals(input)){
			
			int x = im.getMousePressed_x(); int y = im.getMousePressed_y();
			
			if ((x >= posX+577) && (x <= posX+577+56*4-3)	&& (y >= posY+236) && (y <= posY+236+54*6-3)){
				
				item = inventory.isOverItem(x-posX-577, y-posY-236);
				
				gedrückt = true;
				if (item != null) {
					item.toggleShow();
					itemAtCursor = item;
				}
			}
		}
		
		if ("mouseMoved".equals(input)){
			tempx = im.getMouseMoved_newx();
			tempy = im.getMouseMoved_newy();
		}
		
		if ("mouseReleased".equals(input)){
			int x = im.getMouseReleased_x(); int y = im.getMouseReleased_y();
			gedrückt = false;
				if (item != null) {
					item.toggleShow();
					if ((x >= posX+577) && (x <= posX+577+56*4-3)	&& (y >= posY+236) && (y <= posY+236+54*6-3)){
						inventory.drop(itemAtCursor.getContent(), x-posX-577, y-posY-236);
					}
					itemAtCursor = null;
					item = null;
				}
			}
		
	}	

}
	
