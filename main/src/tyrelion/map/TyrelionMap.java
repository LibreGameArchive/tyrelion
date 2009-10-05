/**
 * 
 */
package tyrelion.map;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import tyrelion.CollisionManager;
import tyrelion.loaders.ItemLoader;
import tyrelion.loaders.NpcLoader;
import tyrelion.objects.Npc;
import tyrelion.objects.Player;
import tyrelion.objects.WorldItem;


/**
 * @author jahudi
 *
 */
public class TyrelionMap extends TiledMap {

	/** The size of the tiles - used to determine the amount to draw */
	public static final int TILE_SIZE = 48;
	
	/** The width of the display in tiles */
	private int widthInTiles;
	/** The height of the display in tiles */
	private int heightInTiles;
	/** The offset from the centre of the screen to the top edge in tiles */
	private int topOffsetInTiles;
	/** The offset from the centre of the screen to the left edge in tiles */
	private int leftOffsetInTiles;
	
	/** Start of the displayed area (x-coord) */
	private int displayStartX;
	/** Start of the displayed area (y-coord) */
	private int displayStartY;
	/** End of the displayed area (x-coord) */
	private int displayEndX;
	/** End of the displayed area (y-coord) */
	private int displayEndY;
	
	private NpcMap npcs;
	private WorldItemMap items;
	
	/**
	 * @param ref
	 * @param container
	 * @throws SlickException
	 */
	public TyrelionMap(String ref, GameContainer container)
			throws SlickException {
		super(ref);
		widthInTiles = container.getWidth() / TILE_SIZE;
		heightInTiles = container.getHeight() / TILE_SIZE;
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
		initCollisionBoxes();
		npcs = new NpcMap(width, height);
		items = new WorldItemMap(width, height);
		loadItems("res/xml/itemMap.xml");
		loadNpcs("res/xml/npcMap.xml");
	}
	
	public void render(Player player, Graphics g) {
		
		this.render(player.getTileOffsetX() - (Player.SIZE / 2),
				player.getTileOffsetY() - (Player.SIZE / 2), 
				player.getTileX() - leftOffsetInTiles, 
				player.getTileY() - topOffsetInTiles,
				widthInTiles + 3, heightInTiles + 3);
	}
	
	public void initCollisionBoxes(){
		
		Shape[][] tiles = new Shape[this.getWidth()][this.getHeight()];
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				for (int l = 0; l < this.getLayerCount(); l++) {
					int tileID = this.getTileId(i, j, l);
					String value = this.getTileProperty(tileID, "blocked", "false");
					if ("true".equals(value)) {
						tiles[i][j] = new Rectangle(i*48-24, j*48-24, 48, 48); 
					}
				}
			}
		}
		
		CollisionManager.getInstance().setTiles(tiles);
		
	}
	
	private void updateDisplayedArea(){
		Player player = Player.getInstance();
		displayStartX = player.getTileX() - leftOffsetInTiles;
		displayStartY = player.getTileY() - topOffsetInTiles;
		displayEndX = player.getTileX() + leftOffsetInTiles;
		displayEndY = player.getTileY() + topOffsetInTiles;
		
		if (displayStartX < 0) { displayStartX = 0; }
		if (displayStartY < 0) { displayStartY = 0; }
		if (displayEndX > width) { displayEndX = width; }
		if (displayEndY > height) { displayEndY = height; }
	}
	
	public void renderNpcs(Graphics g) {
		updateDisplayedArea();
		npcs.drawNpcs(displayStartX, displayStartY, displayEndX, displayEndY, g);
	}
	
	public void updateNpcs(){
		updateDisplayedArea();
		npcs.updateNpcs(displayStartX, displayStartY, displayEndX, displayEndY);
	}
	
	public void renderNpcBubbles(Graphics g){
		updateDisplayedArea();
		npcs.drawNpcBubbles(displayStartX, displayStartY, displayEndX, displayEndY, g);
	}
	
	public void renderItems(Graphics g) {
		updateDisplayedArea();
		items.drawItems(displayStartX, displayStartY, displayEndX, displayEndY, g);
	}
	
	public void loadItems(String filename) {		
		try {
			Document itemMap = new SAXBuilder().build(filename);
			List<?> childs = itemMap.getRootElement().getChildren();
			
			for (int i = 0; i < childs.size(); i++) {
				Element e = (Element) childs.get(i);
				int id = e.getAttribute("id").getIntValue();
				int posX = e.getAttribute("posX").getIntValue();
				int posY = e.getAttribute("posY").getIntValue();
				
				items.addItem(new WorldItem(posX, posY, ItemLoader.getInstance().getItem(id)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadNpcs(String filename) {
		try {
			Document npcMap = new SAXBuilder().build(filename);
			List<?> childs = npcMap.getRootElement().getChildren();
			
			for (int i = 0; i < childs.size(); i++) {
				Element e = (Element) childs.get(i);
				int id = e.getAttribute("id").getIntValue();
				int posX = e.getAttribute("posX").getIntValue();
				int posY = e.getAttribute("posY").getIntValue();
				
				Npc npc = NpcLoader.getInstance().getNpc(id);
				npc.setX(posX);
				npc.setY(posY);
				
				npcs.addNpc(npc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the tileSize
	 */
	public static int getTileSize() {
		return TILE_SIZE;
	}

	/**
	 * @return the widthInTiles
	 */
	public int getWidthInTiles() {
		return widthInTiles;
	}

	/**
	 * @return the heightInTiles
	 */
	public int getHeightInTiles() {
		return heightInTiles;
	}

	/**
	 * @return the topOffsetInTiles
	 */
	public int getTopOffsetInTiles() {
		return topOffsetInTiles;
	}

	/**
	 * @return the leftOffsetInTiles
	 */
	public int getLeftOffsetInTiles() {
		return leftOffsetInTiles;
	}

	/**
	 * @return the npcs
	 */
	public NpcMap getNpcs() {
		return npcs;
	}

	/**
	 * @return the items
	 */
	public WorldItemMap getItems() {
		return items;
	}

}
