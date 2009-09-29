/**
 * 
 */
package tyrelion.objects;

import java.awt.Point;
import java.util.Observable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import tyrelion.CoordinatesTranslator;
import tyrelion.CursorManager;
import tyrelion.InteractionManager;
import tyrelion.TyrelionContainer;
import tyrelion.itemsystem.Item;
import tyrelion.map.TyrelionMap;

/**
 * @author jahudi
 *
 */
public class WorldItem extends WorldObject {
	
	public static final int SIZE = 48;
	
	private Item item;
	
	public WorldItem(int x, int y, Item item) {
		super(x, y);
		this.item = item;
	}
	
	public void render(Graphics g) {
		item.getImage_world().draw(tileX*TyrelionMap.TILE_SIZE-SIZE/2, tileY*TyrelionMap.TILE_SIZE-SIZE/2);
	}
	
	public Item getItem	() {
		return item;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		InteractionManager im = (InteractionManager) arg0;
		
		if("mouseClicked".equals(arg1)) {
			int button = im.getMouseClicked_button();
			int x = im.getMouseClicked_x();
			int y = im.getMouseClicked_y();
			
			Point p = CoordinatesTranslator.getInstance().translateCoordinates(x, y);

			if (button == Input.MOUSE_RIGHT_BUTTON && Player.getInstance().inRange(this)) {
				if (p.x == getTileX() && p.y == getTileY()) {
					Player.getInstance().getCharacter().getInventory().addItem(getItem());
					delete();
					CursorManager.getInstance().setCursor(CursorManager.SWORD,
							TyrelionContainer.getInstance().getContainer());
				}
			}
		}
		
		if ("mouseMoved".equals(arg1)) {
			int newX = im.getMouseMoved_newx();
			int newY = im.getMouseMoved_newy();
			int oldX = im.getMouseMoved_oldx();
			int oldY = im.getMouseMoved_oldy();
			
			Point newP = CoordinatesTranslator.getInstance().translateCoordinates(newX, newY);
			Point oldP = CoordinatesTranslator.getInstance().translateCoordinates(oldX, oldY);
			GameContainer container = TyrelionContainer.getInstance().getContainer();
			
			if (newP.x == tileX && newP.y == tileY) {
				if (Player.getInstance().inRange(this)) {
					CursorManager.getInstance().setCursor(CursorManager.HAND, container);
				} else {
					CursorManager.getInstance().setCursor(CursorManager.HAND_LOCKED, container);
				}
			} else if (oldP.x == tileX && oldP.y == tileY) {
				CursorManager.getInstance().setCursor(CursorManager.SWORD, container);
			}
		}
	}
	
	public void delete() {
		TyrelionContainer.getInstance().getMap().getItems().removeItem(this);
		InteractionManager.getInstance().deleteObserver(this);
	}

}
