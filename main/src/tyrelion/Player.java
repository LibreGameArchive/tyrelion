/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;


/**
 * @author jahudi
 *
 */
public class Player {

	public static final int ANIM_UP = 0;
	public static final int ANIM_DOWN = 1;
	public static final int ANIM_LEFT = 2;
	public static final int ANIM_RIGHT = 3;
	
	/** The size of the tank sprite - used for finding the centre */
	public static final int PLAYER_SIZE = 48;
	
	public static final float WALK_SPEED = 0.003f;
	
	private int currentAnimation;
	
	private Animation[] animations;
	
	private Circle shape;
	
	private float playerX;
	private float playerY;
	private int tileX;
	private int tileY;
	private int tileOffsetX;
	private int tileOffsetY;
	
	
	public Player(float x, float y) throws SlickException{
		shape = new Circle(x*48, y*48, 24);
		this.playerX = x;
		this.playerY = y;
		animations = new Animation[4];
		Animation up = new Animation();
		up.addFrame(new Image("res/anim/test_anim/up/up.png", new Color(0x00cc00ff)), 1);
		Animation down = new Animation();
		down.addFrame(new Image("res/anim/test_anim/down/down.png", new Color(0x00cc00ff)), 1);
		Animation left = new Animation();
		left.addFrame(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), 1);
		Animation right = new Animation();
		right.addFrame(new Image("res/anim/test_anim/right/right.png", new Color(0x00cc00ff)), 1);
		
		animations[Player.ANIM_UP] = up;
		animations[Player.ANIM_DOWN] = down;
		animations[Player.ANIM_LEFT] = left;
		animations[Player.ANIM_RIGHT] = right;
		setAnimation(ANIM_RIGHT);
		
		CollisionManager.getInstance().setPlayer(this);
	}
	
	public void render(Graphics g) {
		animations[currentAnimation].draw(playerX*48-24, playerY*48-24);
		g.setColor(Color.red);
	}
	
	public void update() {
		
		tileX = Math.round(playerX);
		tileY = Math.round(playerY);
		
		tileOffsetX = (int) ((tileX - playerX) * TyrelionMap.TILE_SIZE);
		tileOffsetY = (int) ((tileY - playerY) * TyrelionMap.TILE_SIZE);

	}
	
	public void renderShape(Graphics g) {
		g.draw(shape);
	}

	public void setAnimation(int i) {
		currentAnimation = i;
	}

	/**
	 * @return the playerX
	 */
	public float getX() {
		return playerX;
	}

	/**
	 * @return the playerY
	 */
	public float getY() {
		return playerY;
	}

	/**
	 * @return the playerTileX
	 */
	public int getTileX() {
		return tileX;
	}

	/**
	 * @return the playerTileY
	 */
	public int getTileY() {
		return tileY;
	}

	/**
	 * @return the playerTileOffsetX
	 */
	public int getTileOffsetX() {
		return tileOffsetX;
	}

	/**
	 * @return the playerTileOffsetY
	 */
	public int getTileOffsetY() {
		return tileOffsetY;
	}

	/**
	 * @return the circle
	 */
	public Circle getShape() {
		return shape;
	}

	/**
	 * @return the playerX
	 */
	public float getPlayerX() {
		return playerX;
	}

	/**
	 * @param playerX the playerX to set
	 */
	public void setPlayerX(float playerX) {
		this.playerX = playerX;
	}

	/**
	 * @return the playerY
	 */
	public float getPlayerY() {
		return playerY;
	}

	/**
	 * @param playerY the playerY to set
	 */
	public void setPlayerY(float playerY) {
		this.playerY = playerY;
	}

}
