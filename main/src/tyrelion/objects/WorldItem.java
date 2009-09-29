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
import tyrelion.gui.Message;
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
	public void update(Observable interactionManager, Object listenerType) {
		InteractionManager im = (InteractionManager) interactionManager;
		
		if("mouseClicked".equals(listenerType)) {
			int button = im.getMouseClicked_button();
			int x = im.getMouseClicked_x();
			int y = im.getMouseClicked_y();

			if (button == Input.MOUSE_RIGHT_BUTTON && Player.getInstance().inRange(this)) {
				if (isOver(x, y)) {
					rightClickAction();
				}
			}
		}
		
		if ("mouseMoved".equals(listenerType)) {
			int newX = im.getMouseMoved_newx();
			int newY = im.getMouseMoved_newy();
			int oldX = im.getMouseMoved_oldx();
			int oldY = im.getMouseMoved_oldy();

			GameContainer container = TyrelionContainer.getInstance().getContainer();
			
			if (isOver(newX, newY)) {
				if (Player.getInstance().inRange(this)) {
					CursorManager.getInstance().setCursor(CursorManager.HAND, container);
				} else {
					CursorManager.getInstance().setCursor(CursorManager.HAND_LOCKED, container);
				}
			} else if (isOver(oldX, oldY)) {
				CursorManager.getInstance().setCursor(CursorManager.SWORD, container);
			}
		}
	}
	
	public boolean isOver(int x, int y) {
		Point p = CoordinatesTranslator.getInstance().translateCoordinates(x, y);
		
		if (p.x == tileX && p.y == tileY) {
			return true;
		} else {
			return false;
		}
	}
		
	public void delete() {
		TyrelionContainer.getInstance().getMap().getItems().removeItem(this);
		InteractionManager.getInstance().deleteObserver(this);
	}

	/* (non-Javadoc)
	 * @see tyrelion.objects.WorldObject#leftClickAction()
	 */
	@Override
	public void leftClickAction() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see tyrelion.objects.WorldObject#rightClickAction()
	 */
	@Override
	public void rightClickAction() {
		Player.getInstance().getCharacter().getInventory().addItem(getItem());
		delete();
		TyrelionContainer.getInstance().getInfobox().print("Du hast erhältst folgenden Gegenstand: "
				+ item.getName(), Message.ITEM);
		CursorManager.getInstance().setCursor(CursorManager.SWORD,
				TyrelionContainer.getInstance().getContainer());
	}

}
