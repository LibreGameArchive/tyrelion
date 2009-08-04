/**
 * 
 */
package tyrelion;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


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
	
	private float x = 0;
	private float y = 0;
	private int tileX;
	private int tileY;
	private int tileOffsetX;
	private int tileOffsetY;
	
	
	public Player() throws SlickException{
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
	}
	
	public void render() {
		animations[currentAnimation].draw(x*48-24, y*48-24);
	}
	
	public void update(StateBasedGame game, int delta) {
		
		tileX = Math.round(x);
		tileY = Math.round(y);
		
		tileOffsetX = (int) ((tileX - x) * TyrelionMap.TILE_SIZE);
		tileOffsetY = (int) ((tileY - y) * TyrelionMap.TILE_SIZE);
		
		Input input = game.getContainer().getInput();
		  
		if(input.isKeyDown(Keyboard.KEY_LEFT) || input.isControllerLeft(0)) {	
			x += -delta * WALK_SPEED;
			setAnimation(ANIM_LEFT);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_RIGHT) || input.isControllerRight(0)) {
			x += delta * WALK_SPEED;
			setAnimation(ANIM_RIGHT);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_UP) || input.isControllerUp(0)){
			y += -delta * WALK_SPEED;
			setAnimation(ANIM_UP);
		}
		  
		if(input.isKeyDown(Keyboard.KEY_DOWN) || input.isControllerDown(0)){
			y += delta * WALK_SPEED;
			setAnimation(ANIM_DOWN);
		}

	}

	public void setAnimation(int i) {
		currentAnimation = i;
	}

	/**
	 * @return the playerX
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the playerY
	 */
	public float getY() {
		return y;
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

}
