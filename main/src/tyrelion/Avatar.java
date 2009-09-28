/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

/**
 * @author jahudi
 *
 */
public abstract class Avatar extends WorldObject {
	
	public static final int SIZE = 70;
	public static final float WALK_SPEED = 0.003f;
	
	public static final int ANIM_UP = 0;
	public static final int ANIM_DOWN = 1;
	public static final int ANIM_LEFT = 2;
	public static final int ANIM_RIGHT = 3;
	
	protected int currentAnimation;
	
	protected Animation[] animations;
	
	protected float posX;
	protected float posY;
	protected int tileOffsetX;
	protected int tileOffsetY;
	
	protected Shape shape;
	
	public Avatar(int x, int y) {
		super(x, y);
		
		posX = x;
		posY = y;
		
		animations = new Animation[4];
		Animation up = new Animation();
		Animation down = new Animation();
		Animation left = new Animation();
		Animation right = new Animation();
		animations[ANIM_UP] = up;
		animations[ANIM_DOWN] = down;
		animations[ANIM_LEFT] = left;
		animations[ANIM_RIGHT] = right;
		setAnimation(ANIM_RIGHT);
	}
	
	public void render(Graphics g) {
		animations[currentAnimation].draw(posX*TyrelionMap.TILE_SIZE-SIZE/2,
				posY*TyrelionMap.TILE_SIZE-SIZE/2);
	}
	
	public void update(GameContainer container) {
		tileX = Math.round(posX);
		tileY = Math.round(posY);
		
		tileOffsetX = (int) ((tileX - posX) * TyrelionMap.TILE_SIZE);
		tileOffsetY = (int) ((tileY - posY) * TyrelionMap.TILE_SIZE);		
	}
	
	public void setAnimation(int i) {
		currentAnimation = i;
	}

	/**
	 * @return the animations
	 */
	public Animation[] getAnimations() {
		return animations;
	}

	/**
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * @return the posY
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * @return the tileOffsetX
	 */
	public int getTileOffsetX() {
		return tileOffsetX;
	}

	/**
	 * @return the tileOffsetY
	 */
	public int getTileOffsetY() {
		return tileOffsetY;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}

}
