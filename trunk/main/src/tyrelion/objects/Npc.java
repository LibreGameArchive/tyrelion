/**
 * 
 */
package tyrelion.objects;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import tyrelion.CoordinatesTranslator;
import tyrelion.CursorManager;
import tyrelion.FontManager;
import tyrelion.InteractionManager;
import tyrelion.TyrelionContainer;
import tyrelion.map.TyrelionMap;

/**
 * @author jahudi
 *
 */
public class Npc extends Avatar{
	
	private String[] helloText = { "Seyd gegrüßt!", "Hallo, wie geht es Euch?", "Wisst Ihr schon das" +
			"Neueste?", "Schöner Tag oder?" } ;
	private int activeText;
	private Boolean isShowingHello = false;
	
	public Npc(int x, int y) throws SlickException {
		super(x, y);
		
		shape = new Rectangle(x*48-24, y*48-24, 48, 48);
		
		animations[ANIM_UP].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_DOWN].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_LEFT].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_RIGHT].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		setAnimation(ANIM_RIGHT);
		
		activeText = new Random().nextInt(helloText.length);
	}
	
	public void render(Graphics g) {
		super.render(g);
		if (isShowingHello) {
			drawHelloText(g);
		}
	}
	
	public void drawHelloText(Graphics g) {
		FontManager.getInstance().drawString(g, tileX*TyrelionMap.TILE_SIZE-60,
				tileY*TyrelionMap.TILE_SIZE-70, helloText[activeText], FontManager.SIMPLE,
				FontManager.LARGE , Color.white);
	}
	
	public void toggleShowHello() {
		Random r = new Random();
		if (isShowingHello) {
			isShowingHello = false;
		} else {
			activeText = r.nextInt(helloText.length);
			isShowingHello = true;
		}
	}

	/**
	 * @return the isShowingHello
	 */
	public Boolean getIsShowingHello() {
		return isShowingHello;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		InteractionManager im = (InteractionManager) arg0;
		
		if("mouseClicked".equals(arg1)) {
			int button = im.getMouseClicked_button();
			int x = im.getMouseClicked_x();
			int y = im.getMouseClicked_y();

			if (button == Input.MOUSE_RIGHT_BUTTON && Player.getInstance().inRange(this)) {
				if (isOver(x, y)) {
					rightClickAction();
				}
			}
		}
		
		if ("mouseMoved".equals(arg1)) {
			int newX = im.getMouseMoved_newx();
			int newY = im.getMouseMoved_newy();
			int oldX = im.getMouseMoved_oldx();
			int oldY = im.getMouseMoved_oldy();

			GameContainer container = TyrelionContainer.getInstance().getContainer();
			
			if (isOver(newX, newY)) {
				if (Player.getInstance().inRange(this)) {
					CursorManager.getInstance().setCursor(CursorManager.BUBBLE, container);
				} else {
					CursorManager.getInstance().setCursor(CursorManager.BUBBLE_LOCKED, container);
				}
			} else if (isOver(oldX, oldY)) {
				CursorManager.getInstance().setCursor(CursorManager.SWORD, container);
			}
		}
	}
	
	public boolean isOver(int x, int y) {
		Point p = CoordinatesTranslator.getInstance().translateCoordinates(x, y);
		
		if (p.x == tileX && (p.y == tileY || p.y == tileY-1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void delete() {
		TyrelionContainer.getInstance().getMap().getNpcs().removeNpc(this);
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
		toggleShowHello();
	}
	
}