/**
 * 
 */
package tyrelion;


/**
 * @author jahudi
 *
 */
public abstract class WorldObject {
	
	protected int tileX;
	protected int tileY;
	
	public WorldObject(int x, int y) {
		tileX = x;
		tileY = y;
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
