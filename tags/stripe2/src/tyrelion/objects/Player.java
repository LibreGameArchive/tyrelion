/**
 * 
 */
package tyrelion.objects;

import java.util.Observable;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import tyrelion.CollisionManager;
import tyrelion.InteractionManager;
import tyrelion.character.Character;
import tyrelion.map.TyrelionMap;
import tyrelion.sfx.SoundManager;

/**
 * @author jahudi
 *
 */
public class Player extends Avatar{
	
	private static Player instance = null;
	
	/** Role-playing charakter of this player */
	public Character character;
	
	private TyrelionMap map;
	
	public Player(int x, int y) throws SlickException{
		super(x, y, "player");

		shape = new Rectangle(x*48-20, y*48-10, 40, 40);
		
		character = new Character();
	}
	
	public static Player getInstance() {
		if (instance == null) {
			try {
				instance = new Player(0, 0);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public void update(GameContainer container, int delta) {
		super.update(container);
		
		if (!container.isPaused()){
			Input input = container.getInput();
			CollisionManager col = CollisionManager.getInstance();
			
			if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) ||
					input.isKeyDown(Input.KEY_A) ||input.isKeyDown(Input.KEY_D)) {
				SoundManager.getInstance().playOnce("player", "walk", 1f, 2f);
			}
			
			float newPlayerX;
			float newPlayerY;
		
		
			if(input.isKeyDown(Keyboard.KEY_A)) {
				newPlayerX = posX + -delta * WALK_SPEED;
				if (!col.collided(newPlayerX, posY)) {	
					setPosX(newPlayerX);
					setAnimation(ANIM_RUNNING_LEFT);
				}
			}
			  
			if(input.isKeyDown(Keyboard.KEY_D)) {	
				newPlayerX = posX + delta * WALK_SPEED;
				if (!col.collided(newPlayerX, posY)) {		
					setPosX(newPlayerX);
					setAnimation(ANIM_RUNNING_RIGHT);
				}
			}
			  
			if(input.isKeyDown(Keyboard.KEY_W)){
				newPlayerY = posY + -delta * WALK_SPEED;
				if (!col.collided(posX, newPlayerY)) {	
					setPosY(newPlayerY);
					setAnimation(ANIM_RUNNING_UP);
				}
			}
			  
			if(input.isKeyDown(Keyboard.KEY_S)){
				newPlayerY = posY + delta * WALK_SPEED;
				if (!col.collided(posX, newPlayerY)) {
					setPosY(newPlayerY);
					setAnimation(Player.ANIM_RUNNING_DOWN);
				}
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

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		InteractionManager im = InteractionManager.getInstance();
		
		if("keyReleased".equals(arg)) {
			int key = im.getKeyReleased_key();
			switch (key) {
			case Input.KEY_W:	
				setAnimation(ANIM_STANDING_UP);
				break;
			
			case Input.KEY_D:
				setAnimation(ANIM_STANDING_RIGHT);
				break;
				
			case Input.KEY_S:
				setAnimation(ANIM_STANDING_DOWN);
				break;
				
			case Input.KEY_A:
				setAnimation(ANIM_STANDING_LEFT);
				break;
			}
		}
	}

	/**
	 * @return the map
	 */
	public TyrelionMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(TyrelionMap map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see tyrelion.objects.WorldObject#leftClickAction()
	 */
	@Override
	public void leftClickAction() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tyrelion.objects.WorldObject#rightClickAction()
	 */
	@Override
	public void rightClickAction() {
		// TODO Auto-generated method stub
		
	}

}
