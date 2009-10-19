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
	
	public static final int SIZE = 128;
	public static final float WALK_SPEED = 0.003f;
	
	public static final int ANIM_RUNNING_UP = 0;
	public static final int ANIM_RUNNING_DOWN = 1;
	public static final int ANIM_RUNNING_LEFT = 2;
	public static final int ANIM_RUNNING_RIGHT = 3;
	public static final int ANIM_STANDING_UP = 4;
	public static final int ANIM_STANDING_DOWN = 5;
	public static final int ANIM_STANDING_LEFT = 6;
	public static final int ANIM_STANDING_RIGHT = 7;
	public static final int ANIM_TALKING_RIGHT = 8;
	public static final int ANIM_TALKING_UP = 9;
	public static final int ANIM_TALKING_DOWN = 10;
	public static final int ANIM_TALKING_LEFT = 11;
	
	
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
		animations = new Animation[12];
		Animation running_up = new Animation();
		Animation running_down = new Animation();
		Animation running_left = new Animation();
		Animation running_right = new Animation();
		Animation standing_up = new Animation();
		Animation standing_down= new Animation();
		Animation standing_left = new Animation();
		Animation standing_right = new Animation();
		Animation talking_up = new Animation();
		Animation talking_down= new Animation();
		Animation talking_left = new Animation();
		Animation talking_right = new Animation();
		animations[ANIM_RUNNING_UP] = running_up;
		animations[ANIM_RUNNING_DOWN] = running_down;
		animations[ANIM_RUNNING_LEFT] = running_left;
		animations[ANIM_RUNNING_RIGHT] = running_right;
		animations[ANIM_STANDING_UP] = standing_up;
		animations[ANIM_STANDING_DOWN] = standing_down;
		animations[ANIM_STANDING_LEFT] = standing_left;
		animations[ANIM_STANDING_RIGHT] = standing_right;
		animations[ANIM_TALKING_UP] = talking_up;
		animations[ANIM_TALKING_DOWN] = talking_down;
		animations[ANIM_TALKING_LEFT] = talking_left;
		animations[ANIM_TALKING_RIGHT] = talking_right;
		setAnimation(ANIM_STANDING_DOWN);
		
		File root = new File("res/anim/"+animation);
		File[] categories = root.listFiles();
		
		if (categories != null){
			for (File category : categories) {
				if (category.isDirectory() && !category.isHidden()) {
					File[] anims = category.listFiles();
					for (File elem : anims) {
						File[] images = elem.listFiles();
						if (images != null) {
							for (File image : images) {
								if (image.isFile() && !image.isHidden()) {
									if ("running".equals(category.getName())) {
										try {
											if ("up".equals(elem.getName())) {
												running_up.addFrame(new Image(image.getAbsolutePath()), 90);
											}
											if ("down".equals(elem.getName())) {
												running_down.addFrame(new Image(image.getAbsolutePath()), 90);
											}
											if ("left".equals(elem.getName())) {
												running_left.addFrame(new Image(image.getAbsolutePath()), 90);
											}
											if ("right".equals(elem.getName())) {
												running_right.addFrame(new Image(image.getAbsolutePath()), 90);
											}
										} catch (SlickException e) {
											e.printStackTrace();
										}
									} else if ("standing".equals(category.getName())) {
										try {
											if ("up".equals(elem.getName())) {
												standing_up.addFrame(new Image(image.getAbsolutePath()), 90);
											}
											if ("down".equals(elem.getName())) {
												standing_down.addFrame(new Image(image.getAbsolutePath()), 90);
											}
											if ("left".equals(elem.getName())) {
												standing_left.addFrame(new Image(image.getAbsolutePath()), 90);
											}
											if ("right".equals(elem.getName())) {
												standing_right.addFrame(new Image(image.getAbsolutePath()), 90);
											}
										} catch (SlickException e) {
											e.printStackTrace();
										}
									} else if ("talking".equals(category.getName())) {
										try {
											if ("up".equals(elem.getName())) {
												talking_up.addFrame(new Image(image.getAbsolutePath()), 180);
											}
											if ("down".equals(elem.getName())) {
												talking_down.addFrame(new Image(image.getAbsolutePath()), 180);
											}
											if ("left".equals(elem.getName())) {
												talking_left.addFrame(new Image(image.getAbsolutePath()), 180);
											}
											if ("right".equals(elem.getName())) {
												talking_right.addFrame(new Image(image.getAbsolutePath()), 180);
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
		}
	}
	
	public int calcAngleToPlayer() {
		float px = Player.getInstance().getPosX();
		float py = Player.getInstance().getPosY();
		float nx = posX;
		float ny = posY;
		float a;
		float b;
		double alpha;
		int angle = 0;
		
		if (px <= nx && py <= ny) {
			a = ny - py;
			b = nx - px;
			alpha = Math.toDegrees(Math.atan(a/b));
			angle = (int)(270 + alpha);
		} else if (px <= nx && py >= ny) {
			a = py - ny;
			b = nx - px;
			alpha = Math.toDegrees(Math.atan(a/b));
			angle = (int)(270 - alpha);
		} else if (px >= nx && py <= ny) {
			a = ny - py;
			b = px - nx;
			alpha = Math.toDegrees(Math.atan(a/b));
			angle = (int)(90 - alpha);
		} else if (px >= nx && py >= ny) {
			a = py - ny;
			b = px - nx;
			alpha = Math.toDegrees(Math.atan(a/b));
			angle = (int)(90 + alpha);
		}
		
		return angle;
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
