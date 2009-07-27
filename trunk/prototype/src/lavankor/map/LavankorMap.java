package lavankor.map;

import lavankor.GameMaster;
import lavankor.avatar.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

/** Diese Klasse enthält alle Informationen und Methoden, die für die Darstellung und Berechnung
 * 	der Karte, auf der sich der Spieler bewegt, notwendig sind.
 * 	@author Diceware
 *	@version 0.1a
 */
public class LavankorMap {

	/** Die TileMap, auf der die LavankorMap basiert. */
	private TiledMap map;
	/** Die Kollisionsmap als zweidimensionales Array. */
	private boolean[][] blocked;
	/** Die Größe eines Tiles der Map in Pixel */
	private int tileSize;
	/** Die Breite des Bildschirms in Tiles. */
	private int widthInTiles;
	/** Die Höhe des Bildschirms in Tiles. */
	private int heightInTiles;
	/** Der Abstand von der Bildmitte zum oberen Bildschirmrand in Tiles. */
	private int topOffsetInTiles;
	/** Der Abstand von der Bildmitte zum linken Bildschirmrand in Tiles. */
	private int leftOffsetInTiles;
	
	private GameMaster gameMaster;
	
	private ItemMap itemMap;
	private NpcMap npcMap;
	
	/** Der Konstruktor initialisiert die Variablen, stellt einige grundlegende Berechnungen an
	 * 	und ruft createCollisions auf.
	 * 	@param map Die TileMap, auf der die LavankorMap basiert.
	 * 	@param container Der GameContainer, in dem die Map erstellt wird. 
	 */
	public LavankorMap(TiledMap map, GameContainer container, GameMaster gameMaster) {
		// Variablen initialisieren
		this.map = map;
		tileSize = map.getTileHeight();
		// Berechnungen
		widthInTiles = container.getWidth() / tileSize;
		heightInTiles = container.getHeight() / tileSize;
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
		// Kollisionsmap erstellen
		createCollisions();
		itemMap = new ItemMap(map.getWidth(), map.getHeight());	
		npcMap = new NpcMap(map.getWidth(), map.getHeight());
		this.gameMaster = gameMaster; 
	}
	
	/** Erstellt die Kollisionsmap basierend auf den TileProperties der TileMap.
	 */
	public void createCollisions() {
		// Array Grenzen einstellen
		blocked = new boolean[map.getWidth()][map.getHeight()];
		for (int x=0;x<map.getWidth();x++) {
			for (int y=0;y<map.getHeight();y++) {
				// Tile Properties auslesen
				int tileID = map.getTileId(x, y, 0);
				String value = map.getTileProperty(tileID, "blocked", "true");
				// Array einstellen
				if ("true".equals(value)) {
					blocked[x][y] = true;
				}
			}
		}
	}
	
	public void render(Player player) {
		/* Abstand des Spielers zum Rand des Tiles, in dem er sich momentan befindet (wichtig für
		 * Smooth-Scrolling. */
		int playerTileOffsetX = (int) ((player.getTileX() - player.getPosX()) * tileSize);
		int playerTileOffsetY = (int) ((player.getTileY() - player.getPosY()) * tileSize);
		
		/* Den benötigte Ausschnitt der TileMap rendern. Damit die Anzeige der Map bei Bewegung flüssig
		 * bleibt wird etwas mehr von der Map gerendert, als tatsächlich nötig.
		 */
		map.render(playerTileOffsetX - (player.getSize() / 2),
					playerTileOffsetY - (player.getSize() / 2), 
					player.getTileX() - leftOffsetInTiles - 1, 
					player.getTileY() - topOffsetInTiles - 1,
					widthInTiles + 3, heightInTiles + 3);
	}
	
	public void drawItems(Graphics g) {
		Player player = gameMaster.getPlayer();
		int xMin = player.getTileX() - leftOffsetInTiles - 1;
		int yMin = player.getTileY() - topOffsetInTiles - 1;
		int xMax = player.getTileX() + leftOffsetInTiles + 1;
		int yMax = player.getTileY() + topOffsetInTiles + 1;
		
		if (xMin < 0) {
			xMin = 0;
		}
		if (yMin < 0) {
			yMin = 0;
		}
		
		if (xMax > map.getWidth()) {
			xMax = map.getWidth();
		}
		if (yMax > map.getHeight()) {
			yMax = map.getHeight();
		}
		
		for (int i = (int)xMin; i < xMax; i++) {
			for (int j = (int)yMin; j < yMax; j++) {
				if (itemMap.get(i, j) != null) {
					itemMap.get(i, j).draw(g, i*32-32, j*32-32);
				}
			}
		}
	}
	
	public void drawNpcs(Graphics g) {
		Player player = gameMaster.getPlayer();
		int xMin = player.getTileX() - leftOffsetInTiles - 1;
		int yMin = player.getTileY() - topOffsetInTiles - 1;
		int xMax = player.getTileX() + leftOffsetInTiles + 1;
		int yMax = player.getTileY() + topOffsetInTiles + 1;
		
		if (xMin < 0) {
			xMin = 0;
		}
		if (yMin < 0) {
			yMin = 0;
		}
		if (xMax > map.getWidth()) {
			xMax = map.getWidth();
		}
		if (yMax > map.getHeight()) {
			yMax = map.getHeight();
		}
		
		for (int i = (int)xMin; i < xMax; i++) {
			for (int j = (int)yMin; j < yMax; j++) {
				if (npcMap.get(i, j) != null) {
					npcMap.get(i, j).draw(g);
				}
			}
		}
	}
	
	
	
	/** Prüft ob eine bestimmte Koordinate der Map geblockt ist.
	 * 	@param x Die zu prüfende X-Koordinate (in Tiles).
	 * 	@param y Die zu prüfende Y-Koordinate (in Tiles).
	 * 	@return True, falls entsprechende Position geblockt. Sonst False.
	 */
	public boolean blocked(float x, float y) {
		return blocked[(int) (x)][(int) (y)];
	}

	// Es folgen Getter und Setter, die wegen Trivialität nicht kommentiert werden.
	
	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public void setWidthInTiles(int widthInTiles) {
		this.widthInTiles = widthInTiles;
	}

	public int getHeightInTiles() {
		return heightInTiles;
	}

	public void setHeightInTiles(int heightInTiles) {
		this.heightInTiles = heightInTiles;
	}

	public int getTopOffsetInTiles() {
		return topOffsetInTiles;
	}

	public void setTopOffsetInTiles(int topOffsetInTiles) {
		this.topOffsetInTiles = topOffsetInTiles;
	}

	public int getLeftOffsetInTiles() {
		return leftOffsetInTiles;
	}

	public void setLeftOffsetInTiles(int leftOffsetInTiles) {
		this.leftOffsetInTiles = leftOffsetInTiles;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public ItemMap getItemMap() {
		return itemMap;
	}

	public void setItemMap(ItemMap itemMap) {
		this.itemMap = itemMap;
	}

	public NpcMap getNpcMap() {
		return npcMap;
	}

	public void setNpcMap(NpcMap npcMap) {
		this.npcMap = npcMap;
	}

	public boolean[][] getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean[][] blocked) {
		this.blocked = blocked;
	}

}
