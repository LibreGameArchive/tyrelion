/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author jahudi
 *
 */
public class Npc extends Avatar{
	
	private String helloText;
	private Boolean isShowingHello = false;
	
	public Npc(int x, int y) throws SlickException {
		super(x, y);
		
		helloText = "Seyd gegrüßt!";
		
		animations[ANIM_UP].addFrame(new Image("res/anim/test_anim/up/up.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_DOWN].addFrame(new Image("res/anim/test_anim/down/down.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_LEFT].addFrame(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_RIGHT].addFrame(new Image("res/anim/test_anim/right/right.png", new Color(0x00cc00ff)), 1);
		setAnimation(ANIM_RIGHT);
	}
	
	public void render(Graphics g) {
		super.render(g);
		if (isShowingHello) {
			try {
				drawHelloText(g);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void drawHelloText(Graphics g) throws SlickException {
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
}
