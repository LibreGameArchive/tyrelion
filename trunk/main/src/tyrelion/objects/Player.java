/**
 * 
 */
package tyrelion.objects;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import tyrelion.CollisionManager;
import tyrelion.character.Character;
import tyrelion.sfx.SoundManager;


/**
 * @author jahudi
 *
 */
public class Player extends Avatar{
	
	private static Player instance = null;
	
	/** Role-playing charakter of this player */
	public Character character;
	
	public Player(int x, int y) throws SlickException{
		super(x, y);

		shape = new Rectangle(x*48-20, y*48-10, 40, 40);
		
		animations[ANIM_UP].addFrame(new Image("res/anim/test_anim/up/up.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_DOWN].addFrame(new Image("res/anim/test_anim/down/down.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_LEFT].addFrame(new Image("res/anim/test_anim/left/left.png", new Color(0x00cc00ff)), 1);
		animations[ANIM_RIGHT].addFrame(new Image("res/anim/test_anim/right/right.png", new Color(0x00cc00ff)), 1);
		setAnimation(ANIM_RIGHT);
		
		character = new Character();
	}
	
	public static Player getInstance() throws SlickException{
		if (instance == null) {
			instance = new Player(0, 0);
		}
		return instance;
	}
	
	public void update(GameContainer container, int delta) {
		super.update(container);
		
		Input input = container.getInput();
		CollisionManager col = CollisionManager.getInstance();
		
		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) ||
				input.isKeyDown(Input.KEY_A) ||input.isKeyDown(Input.KEY_D)) {
			SoundManager.getInstance().playOnce("player", "walk");
		}
		
		float newPlayerX;
		float newPlayerY;
		
		if(input.isKeyDown(Keyboard.KEY_A)) {
			newPlayerX = posX + -delta * WALK_SPEED;
			if (!col.collided(newPlayerX, posY)) {	
				setPosX(newPlayerX);
				setAnimation(ANIM_LEFT);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_D)) {	
			newPlayerX = posX + delta * WALK_SPEED;
			if (!col.collided(newPlayerX, posY)) {		
				setPosX(newPlayerX);
				setAnimation(ANIM_RIGHT);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_W)){
			newPlayerY = posY + -delta * WALK_SPEED;
			if (!col.collided(posX, newPlayerY)) {	
				setPosY(newPlayerY);
				setAnimation(ANIM_UP);
			}
		}
		  
		if(input.isKeyDown(Keyboard.KEY_S)){
			newPlayerY = posY + delta * WALK_SPEED;
			if (!col.collided(posX, newPlayerY)) {
				setPosY(newPlayerY);
				setAnimation(Player.ANIM_DOWN);
			}
		}
		
	}
	
	public void renderShape(Graphics g) {
		g.draw(shape);
	}

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}

}
