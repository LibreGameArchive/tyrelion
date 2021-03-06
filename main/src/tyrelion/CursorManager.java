/**
 * 
 */
package tyrelion;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.CursorLoader;

/**
 * @author jahudi
 *
 */
public class CursorManager {
	
	private static CursorManager instance = null;

	public static final int SWORD = 5;
	public static final int BUBBLE = 1;
	public static final int BUBBLE_LOCKED = 2;
	public static final int HAND = 3;
	public static final int HAND_LOCKED = 4;
	public static final int ARROW = 0;
	
	private int currentCursor;
	
	private Cursor sword;
	private Cursor arrow;
	private Cursor bubble;
	private Cursor bubble_locked;
	private Cursor hand;
	private Cursor hand_locked;
	
	public static CursorManager getInstance() {
		if (instance == null) {
			instance = new CursorManager();
		}
		return instance;
	}
	
	public CursorManager() {
		try {
			arrow = CursorLoader.get().getCursor("res/img/mouse/cursor_arrow.png", 1, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sword = CursorLoader.get().getCursor("res/img/mouse/cursor_sword.png", 2, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bubble = CursorLoader.get().getCursor("res/img/mouse/cursor_bubble.png", 13, 28);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bubble_locked = CursorLoader.get().getCursor("res/img/mouse/cursor_bubble_locked.png", 13, 28);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hand = CursorLoader.get().getCursor("res/img/mouse/cursor_hand.png", 12, 20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			hand_locked = CursorLoader.get().getCursor("res/img/mouse/cursor_hand_locked.png", 12, 20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCursor(int cursor, GameContainer container) {
		switch (cursor) {
		case SWORD:
			try {
			 	container.setMouseCursor(sword, 2, 2);
			 	currentCursor = SWORD;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case BUBBLE:
			try {
				container.setMouseCursor(bubble, 13, 28);
				currentCursor = BUBBLE;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case BUBBLE_LOCKED:
			try {
				container.setMouseCursor(bubble_locked, 13, 28);
				currentCursor = BUBBLE_LOCKED;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case HAND:
			try {
				container.setMouseCursor(hand, 12, 20);
				currentCursor = HAND;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case HAND_LOCKED:
			try {
				container.setMouseCursor(hand_locked, 12, 20);
				currentCursor = HAND_LOCKED;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case ARROW:
			try {
				container.setMouseCursor(arrow, 1, 1);
				currentCursor = ARROW;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			try {
				container.setMouseCursor(arrow, 1, 1);
				currentCursor = ARROW;
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * @return the currentCursor
	 */
	public int getCurrentCursor() {
		return currentCursor;
	}
	
}
