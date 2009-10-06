/**
 * 
 */
package tyrelion;

import java.util.Observable;

/**
 * @author imladriel
 *
 */
public class InteractionManager extends Observable{
	
	private static InteractionManager instance = null;
	
	public static InteractionManager getInstance(){
		if (instance == null) {
			instance = new InteractionManager();
		}
		return instance;
	}
	
	
	/**
	 * Notification that a key was pressed
	 * 
	 * @param key The key code that was pressed (@see org.newdawn.slick.Input)
	 * @param c The character of the key that was pressed
	 */
	public void keyPressed(int key, char c){
		keyPressed_key = key;
		keyPressed_c = c;
		setChanged();
		notifyObservers("keyPressed");
	}
	
	private int keyPressed_key;
	/** getter for method key pressed
	 * @return the keyPressed_key
	 */
	public int getKeyPressed_key() {
		return keyPressed_key;
	}

	private char keyPressed_c;
	/** getter for method key pressed
	 * @return the keyPressed_c
	 */
	public char getKeyPressed_c() {
		return keyPressed_c;
	}
	
	
	

	/**
	 * Notification that a key was released
	 * 
	 * @param key The key code that was released (@see org.newdawn.slick.Input)
	 * @param c The character of the key that was released
	 */
	public void keyReleased(int key, char c){
		keyReleased_key = key;
		keyReleased_c = c;
		setChanged();
		notifyObservers("keyReleased");
	}
	private int keyReleased_key;
	/** getter for method keyReleased
	 * @return the keyReleased_key
	 */
	public int getKeyReleased_key() {
		return keyReleased_key;
	}

	private char keyReleased_c;
	/** getter for method keyReleased
	 * @return the keyReleased_c
	 */
	public char getKeyReleased_c() {
		return keyReleased_c;
	}
	
	
	
	
	/**
	 * Notification that a mouse button was clicked. Due to double click
	 * handling the single click may be delayed slightly. For absolute notification
	 * of single clicks use mousePressed().
	 * 
	 * To be absolute this method should only be used when considering double clicks
	 * 
	 * @param button The index of the button (starting at 0)
	 * @param x The x position of the mouse when the button was pressed
	 * @param y The y position of the mouse when the button was pressed
	 * @param clickCount The number of times the button was clicked
	 */
	public void mouseClicked(int button, int x, int y, int clickCount){
		mouseClicked_button = button;
		mouseClicked_x = x;
		mouseClicked_y = y;
		mouseClicked_clickCount = clickCount;
		setChanged();
		notifyObservers("mouseClicked");
	}
	
	private int mouseClicked_button;
	/** getter for method mouseClicked
	 * @return the mouseClicked_button
	 */
	public int getMouseClicked_button() {
		return mouseClicked_button;
	}

	private int mouseClicked_x;
	/** getter for method mouseClicked
	 * @return the mouseClicked_x
	 */
	public int getMouseClicked_x() {
		return mouseClicked_x;
	}
	
	private int mouseClicked_y;
	/** getter for method mouseClicked
	 * @return the mouseClicked_y
	 */
	public int getMouseClicked_y() {
		return mouseClicked_y;
	}
	
	private int mouseClicked_clickCount;
	/** getter for method mouseClicked
	 * @return the mouseClicked_clickCount
	 */
	public int getMouseClicked_clickCount() {
		return mouseClicked_clickCount;
	}
	
	
	

	/**
	 * Notification that a mouse button was pressed
	 * 
	 * @param button The index of the button (starting at 0)
	 * @param x The x position of the mouse when the button was pressed
	 * @param y The y position of the mouse when the button was pressed
	 */
	public void mousePressed(int button, int x, int y){
		mousePressed_button = button;
		mousePressed_x = x;
		mousePressed_y = y;
		setChanged();
		notifyObservers("mousePressed");
	}
	
	private int mousePressed_button;
	/** getter for method mousePressed
	 * @return the mousePressed_button
	 */
	public int getMousePressed_button() {
		return mousePressed_button;
	}
	
	private int mousePressed_x;
	/** getter for method mousePressed
	 * @return the mousePressed_x
	 */
	public int getMousePressed_x() {
		return mousePressed_x;
	}
	
	private int mousePressed_y;
	/** getter for method mousePressed
	 * @return the mousePressed_y
	 */
	public int getMousePressed_y() {
		return mousePressed_y;
	}

	
	
	
	/**
	 * Notification that a mouse button was released
	 * 
	 * @param button The index of the button (starting at 0)
	 * @param x The x position of the mouse when the button was released
	 * @param y The y position of the mouse when the button was released
	 */
	public void mouseReleased(int button, int x, int y){
		mouseReleased_button = button;
		mouseReleased_x = x;
		mouseReleased_y = y;
		setChanged();
		notifyObservers("mouseReleased");
	}
	
	private int mouseReleased_button;
	/** getter for method mouseReleased
	 * @return the mouseReleased_button
	 */
	public int getMouseReleased_button() {
		return mouseReleased_button;
	}

	private int mouseReleased_x;
	/** getter for method mouseReleased
	 * @return the mouseReleased_button
	 */
	public int getMouseReleased_x() {
		return mouseReleased_x;
	}
	
	private int mouseReleased_y;
	/** getter for method mouseReleased
	 * @return the mouseReleased_y
	 */
	public int getMouseReleased_y() {
		return mouseReleased_y;
	}
	
	

	
	
	/**
	 * Notification that mouse cursor was moved
	 * 
	 * @param oldx The old x position of the mouse
	 * @param oldy The old y position of the mouse
	 * @param newx The new x position of the mouse
	 * @param newy The new y position of the mouse
	 */
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		mouseMoved_oldx = oldx;
		mouseMoved_oldy = oldy;
		mouseMoved_newx = newx;
		mouseMoved_newy = newy;
		setChanged();
		notifyObservers("mouseMoved");
	}

	private int mouseMoved_oldx;
	/** getter for method mouseMoved
	 * @return the mouseMoved_oldx
	 */
	public int getMouseMoved_oldx() {
		return mouseMoved_oldx;
	}
	
	private int mouseMoved_oldy;
	/** getter for method mouseMoved
	 * @return the mouseMoved_oldy
	 */
	public int getMouseMoved_oldy() {
		return mouseMoved_oldy;
	}
	
	private int mouseMoved_newx;
	/** getter for method mouseMoved
	 * @return the mouseMoved_newx
	 */
	public int getMouseMoved_newx() {
		return mouseMoved_newx;
	}
	
	private int mouseMoved_newy;
	/** getter for method mouseMoved
	 * @return the mouseMoved_newy
	 */
	public int getMouseMoved_newy() {
		return mouseMoved_newy;
	}
	
	
}
