package lavankor.control;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/** Implementierung des TileBasedMap Interfaces. Entspricht einer Kollsionsmap und stellt
 * 	weitere Funktionen für Pathfinding zur Verfügung. 
 * 	@author Diceware
 */
public class TileBasedMapImpl implements TileBasedMap {

	/** Die TileMap, aus der die TileBasedMap erzeugt werden soll. */
	private TiledMap map;
	
	/** Die Kollsisionsmap. */
	private boolean[][] blocked;
	
	/** Konstruktor der Klasse. Belegt die Variable map.
	 * 	@param map Die TileMap, aus der die TileBasedMap erzeugt werden soll.
	 */
	public TileBasedMapImpl(TiledMap map) {
		this.map = map;
	}
	
	/** Prüft ob eine bestimmte Position auf der Map für einen bestimmten Mover blockiert ist.
	 * 	In unserem Fall spielt der Mover keine Rolle.
	 *  @param mover Der Mover, für den die Blockierung geprüft werden soll.
	 *  @param x Die X-Position, die geprüft werden soll.
	 *  @param y Die Y-Position, die geprüft werden soll.
	 *  @return True falls die Postion blockiert ist, sonst False.
	 */
	public boolean blocked(Mover mover, int x, int y) {
		
		// Kollisionsmap anlegen (s. ScrollTest)
		blocked = new boolean[map.getWidth()][map.getHeight()];
		for (int i=0;i<map.getWidth();i++) {
			for (int j=0;j<map.getHeight();j++) {
				int tileID = map.getTileId(i, j, 0);
				String value = map.getTileProperty(tileID, "blocked", "true");
				if ("true".equals(value)) {
					blocked[i][j] = true;
				}
			}
		}
		// Wert bei x,y ausgeben
		return blocked[x][y];
	}

	/** Ermittelt die Kosten für einen bestimmten Zug und gibt diese aus. Bei uns immer 0,
	 *  da Bewegungen hier nichts kosten.
	 *	@param mover Der Mover, für den werden soll.
	 *	@param sx Die X-Koordinate der Startposition.
	 *	@param sy Die Y-Koordinate der Startposition.
	 *	@param tx Die X-Koordinate der Zielposition.
	 *	@param ty Die Y-Koordinate der Zieltposition.
	 *	@return Die Kosten des Zuges als Float. Hier immer 0.
	 */
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 0;
	}

	/** Ermittelt die Höhe der Map in Tiles.
	 * 	@return Die Höhe der TileMap in Tiles.
	 */
	public int getHeightInTiles() {
		return map.getHeight();
	}

	/** Ermittelt die Breite der Map in Tiles.
	 * 	@return Die Breite der TileMap in Tiles.
	 */
	public int getWidthInTiles() {
		return map.getWidth();
	}

	/** Beschreibt, was passieren soll, wenn der Pathfinder eine Position besucht hat.
	 * 	Hat bei uns keine Verwendung.
	 * 	@param x Die X-Koordinate.
	 *  @param y Die Y-Koordinate.
	 */
	public void pathFinderVisited(int x, int y) {
	}
}
