/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author jahudi
 *
 */
public class Npc {
	
	private Animation animation;
	
	private String helloText;
	private Boolean isShowingHello = false;
	
	private int posX;
	private int posY;
	
	public Npc(int posX, int posY) throws SlickException {
		this.posX = posX;
		this.posY = posY;
		helloText = "Seyd gegrüßt!";
		animation = new Animation();
		animation.addFrame(new Image("res/anim/test_anim/up/up.png", new Color(0x00cc00ff)), 1);
	}
	
	public void render(Graphics g) throws SlickException {
		animation.draw(posX * 48 - 36, posY * 48 - 48);
		if (isShowingHello) {
			drawHelloText(g);
		}
	}
	
	public void drawHelloText(Graphics g) throws SlickException {
		FontManager.getInstance().drawString(g, posX*48 -  60, posY*48 - 70, helloText, FontManager.SIMPLE, FontManager.LARGE , Color.white);
	}
	
	public void toggleShowHello() {
		if (isShowingHello) {
			isShowingHello = false;
		} else {
			isShowingHello = true;
		}
	}
	
	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * @return the isShowingHello
	 */
	public Boolean getIsShowingHello() {
		return isShowingHello;
	}

	/**
	 * @param isShowingHello the isShowingHello to set
	 */
	public void setIsShowingHello(Boolean isShowingHello) {
		this.isShowingHello = isShowingHello;
	}
	
}
