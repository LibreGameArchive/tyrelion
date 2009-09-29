/**
 * 
 */
package tyrelion.objects;

import java.awt.Point;
import java.util.Observable;

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
	
	private String helloText;
	private Boolean isShowingHello = false;
	
	public Npc(int x, int y) throws SlickException {
		super(x, y);
		
		shape = new Rectangle(x*48-24, y*48-24, 48, 48);
		
		helloText = "Seyd gegrüßt!";
		
		animations[ANIM_UP].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_DOWN].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_LEFT].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_RIGHT].addFrame(new Image("res/anim/priest_anim/priest_se.png", new Color(0x00cc00ff)), 1);
		setAnimation(ANIM_RIGHT);
	}
	
	public void render(Graphics g) {
		super.render(g);
		if (isShowingHello) {
			drawHelloText(g);
		}
	}
	
	public void drawHelloText(Graphics g) {
		FontManager.getInstance().drawString(g, tileX*TyrelionMap.TILE_SIZE-60,
				tileY*TyrelionMap.TILE_SIZE-70, helloText, FontManager.SIMPLE,
				FontManager.LARGE , Color.white);
	}
	
	public void toggleShowHello() {
		if (isShowingHello) {
			isShowingHello = false;
		} else {
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
	@Override
	public void update(Observable arg0, Object arg1) {
		InteractionManager im = (InteractionManager) arg0;
		
		if("mouseClicked".equals(arg1)) {
			int button = im.getMouseClicked_button();
			int x = im.getMouseClicked_x();
			int y = im.getMouseClicked_y();
			
			Point p = CoordinatesTranslator.getInstance().translateCoordinates(x, y);

			if (button == Input.MOUSE_RIGHT_BUTTON && Player.getInstance().inRange(this)) {
				if (p.x == tileX && (p.y == tileY || p.y == tileY-1)) {
					toggleShowHello();
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
			
			if (newP.x == tileX && (newP.y == tileY || newP.y == tileY-1)) {
				if (Player.getInstance().inRange(this)) {
					CursorManager.getInstance().setCursor(CursorManager.BUBBLE, container);
				} else {
					CursorManager.getInstance().setCursor(CursorManager.BUBBLE_LOCKED, container);
				}
			} else if (oldP.x == tileX && (oldP.y == tileY || oldP.y == tileY-1)) {
				CursorManager.getInstance().setCursor(CursorManager.SWORD, container);
			}
		}
	}
}
