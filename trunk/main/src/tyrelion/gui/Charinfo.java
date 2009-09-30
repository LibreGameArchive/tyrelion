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
import tyrelion.FontManager;
import tyrelion.InteractionManager;
import tyrelion.TyrelionContainer;
import tyrelion.character.Inventory;
import tyrelion.character.Inventory.InventoryField;
import tyrelion.objects.Player;

/**
 * @author daennart
 *
 */
public class Charinfo implements Observer{
	
	/** Background for the infobox */
	private Image background;
	
	Inventory inventory;
	
	private int posX = 0;
	private int posY = 0;
	
	int mouseX;
	int mouseY;
	
	/** defines if a stack should be splitted when dragged */
	boolean split = false;
	
	boolean showCharinfo = false;
	
	boolean mousePressed = false;
	/** Item which flies with the cursor, if any */
	InventoryField itemAtCursor = null;
	/** Given item from dragging the inventory */
	InventoryField item = null;

	public Charinfo(GameContainer container)
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
			if (itemAtCursor!=null) itemAtCursor.render(g, mouseX-25, mouseY-25);
			
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable observable, Object input) {
		InteractionManager im = (InteractionManager) observable;
		
		if ("keyReleased".equals(input)){
			if (im.getKeyReleased_key() == Input.KEY_C) {
				CursorManager.getInstance().setCursor(CursorManager.SWORD, TyrelionContainer.getInstance().getContainer());
				showCharinfo = !showCharinfo;
				if (showCharinfo){
					TyrelionContainer.getInstance().getContainer().pause();
				} else { TyrelionContainer.getInstance().getContainer().resume(); }
			}
			
			if (im.getKeyReleased_key() == Input.KEY_ESCAPE) {
				CursorManager.getInstance().setCursor(CursorManager.SWORD, TyrelionContainer.getInstance().getContainer());
				if (showCharinfo){
					showCharinfo = false;
					TyrelionContainer.getInstance().getContainer().resume();
				}
			}
			
			if (im.getKeyReleased_key() == Input.KEY_LSHIFT || im.getKeyReleased_key() == Input.KEY_RSHIFT) {
				split = false;
			}
		}
		
		if ("keyPressed".equals(input)){
			if (im.getKeyPressed_key() == Input.KEY_LSHIFT || im.getKeyPressed_key() == Input.KEY_RSHIFT) {
				split = true;
			}
		}
		
		if ("mousePressed".equals(input)){
			
			int x = im.getMousePressed_x(); int y = im.getMousePressed_y();
			
			if (isMouseOverInventory(x, y)){
				
				item = inventory.isOverItem(x-posX-577, y-posY-236);
				
				mousePressed = true;
				if (item != null) {
					if(split && item.getCount()>1){
						item.toggleShow(split);
						itemAtCursor = inventory.new InventoryField(item.getItem());
					} else{
						item.toggleShow(false);
						itemAtCursor = item;
					}
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
					item.showIt();
					if (isMouseOverInventory(x, y)){
						int fieldX = (x-posX-577) / 56;
						int fieldY = (y-posY-236) / 55;
						inventory.drop(itemAtCursor.getContent(), fieldX, fieldY, split);
					} else {
						if (isMouseOutOfScreen(x, y)){
							inventory.drop(itemAtCursor.getContent(), -1, -1, split);
						}
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
	
	private boolean isMouseOutOfScreen(int x, int y){
		return ((x < posX+20) || (x > posX+780)	|| (y < posY+50) || (y > posY+610));
	}

}
	
