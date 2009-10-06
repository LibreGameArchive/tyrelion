/**
 * 
 */
package tyrelion.objects;

import java.awt.Point;
import java.util.ArrayList;
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
	
	private String[] helloText;
	private int activeText;
	private Boolean isShowingHello = false;
	private final int uid;
	
	private Image bubbleImg;
	private int bubbleLength = 15;
	
	public Npc(int id, int x, int y, String animation, String[] helloText) throws SlickException {
		super(x, y, animation);
		
		uid = id;
		
		this.helloText = helloText;
		
		shape = new Rectangle(x*48-24, y*48-32, 48, 62);
		
		activeText = new Random().nextInt(helloText.length);
		
		bubbleImg = new Image("res/img/interaction/bubble.png");
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	public void drawBubble(Graphics g){
		if (isShowingHello) {
			g.drawImage(bubbleImg, tileX*TyrelionMap.TILE_SIZE-35, tileY*TyrelionMap.TILE_SIZE-105);
			drawHelloText(g);
		}
	}
	
	public void drawHelloText(Graphics g) {
		int pos = 0;
		for (String elem:breakLine(helloText[activeText])){
		FontManager.getInstance().drawString(g, tileX*TyrelionMap.TILE_SIZE-20,
				tileY*TyrelionMap.TILE_SIZE-95+(pos*20), elem, FontManager.SIMPLE,
				FontManager.MEDIUM , Color.white);
		pos++;
		}
	}
	
	private ArrayList<String> breakLine(String text){
		String temp = "";
		
		char[] letters = text.toCharArray();
		ArrayList<String> words = new ArrayList<String>();
		for (int i=0; i<text.length();i++) {
			if (letters[i]==(" ".charAt(0))) { words.add(temp); temp=""; } else { temp+=letters[i]; }
		}
		words.add(temp);
		
		ArrayList<String> broken = new ArrayList<String>();
		String line = "";
		int count = 0;
		for (String elem: words) {
			count += elem.length();
			if (count<=bubbleLength) {
				line += elem + " ";	
				count += 1;
			} else {
				broken.add(line);
				line=elem + " ";
				count=elem.length()+1;
			}
		}
		if (!line.equals("")) {
			broken.add(line);
		}
		
		return broken;
	}
	
	public void toggleShowHello() {
		Random r = new Random();
		if (isShowingHello) {
			isShowingHello = false;
			stopTalking();
		} else {
			activeText = r.nextInt(helloText.length);
			isShowingHello = true;
			talkToPlayer(calcAngleToPlayer());
		}
	}
	
	public void update(){
		if (!Player.getInstance().inRange(this)) {
			if (isShowingHello) {
				isShowingHello = false;
				stopTalking();
			}
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
		shape.setCenterX(tileX*48);
		shape.setCenterY(tileY*48);
		if (!TyrelionContainer.getInstance().getContainer().isPaused()){
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
					CursorManager.getInstance().setCursor(CursorManager.ARROW, container);
				}
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

	public void talkToPlayer(int angle) {
		if (angle >= 0 && angle < 45 || angle > 315 && angle <= 360) {
			setAnimation(ANIM_TALKING_UP);
		} else if (angle >= 45 && angle <= 135) {
			setAnimation(ANIM_TALKING_RIGHT);
		} else if (angle > 135 && angle < 225) {
			setAnimation(ANIM_TALKING_DOWN);
		} else if (angle >= 225 && angle <= 315) {
			setAnimation(ANIM_TALKING_LEFT);
		}
	}
	
	public void stopTalking() {
		switch (currentAnimation) {
			case ANIM_TALKING_DOWN:
				setAnimation(ANIM_STANDING_DOWN);
				break;
				
			case ANIM_TALKING_LEFT:
				setAnimation(ANIM_STANDING_LEFT);
				break;
				
			case ANIM_TALKING_RIGHT:
				setAnimation(ANIM_STANDING_RIGHT);
				break;
				
			case ANIM_TALKING_UP:
				setAnimation(ANIM_STANDING_UP);
				break;
	
			default:
				setAnimation(ANIM_STANDING_DOWN);
				break;
		}
	}
	
	/* (non-Javadoc)
	 * @see tyrelion.objects.WorldObject#rightClickAction()
	 */
	@Override
	public void rightClickAction() {
		toggleShowHello();
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	
}
