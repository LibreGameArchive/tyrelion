/**
 * 
 */
package tyrelion.objects;

import java.util.Observer;

import tyrelion.InteractionManager;


/**
 * @author jahudi
 *
 */
public abstract class WorldObject implements Observer {
	
	protected int tileX;
	protected int tileY;
	
	public WorldObject(int x, int y) {
		tileX = x;
		tileY = y;
		InteractionManager.getInstance().addObserver(this);
	}
	
	public Boolean inRange(WorldObject object) {
		int dist;
		if (object instanceof Avatar) {
			dist = 2;
		} else {
			dist = 1;
		}
		
		int x = Math.abs(tileX - object.getTileX());
		int y = Math.abs(tileY - object.getTileY());
		if (Math.max(x, y) <= dist ) {
			return true;
		} else {
			return false;
		}
	}
	
	

	/**
	 * @return the tileX
	 */
	public int getTileX() {
		return tileX;
	}

	/**
	 * @return the tileY
	 */
	public int getTileY() {
		return tileY;
	}


}
