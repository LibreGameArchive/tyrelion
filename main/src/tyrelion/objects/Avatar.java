/**
 * 
 */
package tyrelion.objects;

import java.io.File;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import tyrelion.map.TyrelionMap;

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
	
	public Avatar(int x, int y, String animation) throws SlickException {
		super(x, y);
		
		posX = x;
		posY = y;
		
		loadAnimations(animation);
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
	
	public void loadAnimations(String animation) {
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
		
		File root = new File("res/anim/"+animation);
		File[] anims = root.listFiles();
		
		if (anims != null){
			for (File elem : anims) {
				if (elem.isDirectory() && !elem.isHidden()) {
					File[] images = elem.listFiles();
					if (images != null) {
						for (File image : images) {
							if (image.isFile() && !elem.isHidden()) {
								try {
									if ("up".equals(elem.getName())) {
										up.addFrame(new Image(image.getAbsolutePath()), 10);
									}
									if ("down".equals(elem.getName())) {
										down.addFrame(new Image(image.getAbsolutePath()), 10);
									}
									if ("left".equals(elem.getName())) {
										left.addFrame(new Image(image.getAbsolutePath()), 10);
									}
									if ("right".equals(elem.getName())) {
										right.addFrame(new Image(image.getAbsolutePath()), 10);
									}
								} catch (SlickException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
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
	
	public void setX(int x) {
		posX = x;
		tileX = x;
	}
	
	public void setY(int y) {
		posY = y;
		tileY = y;
	}

	/**
	 * @return the shape
	 */
	public Shape getShape() {
		return shape;
	}

}
