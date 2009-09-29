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
import tyrelion.objects.Player;

/**
 * @author daennart
 *
 */
public class Charinfo implements Observer{
	
	private Graphics graphics;
	
	/** Background for the infobox */
	private Image background;
	
	Inventory inventory;
	
	private int posX = 0;
	private int posY = 0;
	
	int mouseX;
	int mouseY;
	
	boolean showCharinfo = false;
	
	boolean mousePressed = false;
	/** Item which flies with the cursor, if any */
	InventoryField itemAtCursor = null;
	/** Given item from dragging the inventory */
	InventoryField item = null;

	public Charinfo()
			throws SlickException {
		
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
			
			inventory.render(g, posX+577, posY+236);
			if (itemAtCursor!=null) itemAtCursor.render(graphics, mouseX-25, mouseY-25);
			
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
			
			if (isMouseOverInventory(x, y)){
				
				item = inventory.isOverItem(x-posX-577, y-posY-236);
				
				mousePressed = true;
				if (item != null) {
					item.toggleShow();
					itemAtCursor = item;
				}
			}
		}
		
		if ("mouseMoved".equals(input)){
			//get current mouse position
			mouseX = im.getMouseMoved_newx();
			mouseY = im.getMouseMoved_newy();
		}
		
		if ("mouseReleased".equals(input)){
			int x = im.getMouseReleased_x(); int y = im.getMouseReleased_y();
			mousePressed = false;
				if (item != null) {
					item.toggleShow();
					if (isMouseOverInventory(x, y)){
						inventory.drop(itemAtCursor.getContent(), x-posX-577, y-posY-236);
					}
					itemAtCursor = null;
					item = null;
				}
			}
		
	}
	
	/** returns true if mouse position is over the inventory */
	private boolean isMouseOverInventory(int x, int y){
		return ((x >= posX+577) && (x <= posX+577+56*4-3)	&& (y >= posY+236) && (y <= posY+236+54*6-3));
	}

}
	
