/**
 * 
 */
package tyrelion.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import tyrelion.Player;
import tyrelion.TyrelionMap;

/**
 * @author imladriel
 *
 */
public class Minimap {
	
	private GameContainer gameContainer;
	
	private int posX = 1052;
	private int posY = 784;
	
	private int minimapWidth=40;
	private int minimapHeight=40;
	
	private int mapWidth;
	private int mapHeight;
	
	private int partSize = 5;

	private String[][] types;
	private static Color PLAYER = new Color(200,10,10);
	private static Color NOTHING = Color.black;
	private static Color GRASS = new Color(0x00125804);
	private static Color TREE = new Color(0x0065280b);
	
	private TyrelionMap map;
	private Player player;
	
	public Minimap(GameContainer gameContainer, TyrelionMap map, Player player){
		this.gameContainer = gameContainer;
		
		this.map = map;
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
		
		this.player = player;
		
		minimapWidth = 50;
		minimapHeight = 50;
		
		readTypes();
	}
	
	
	public void readTypes(){
		types = new String[mapWidth][mapHeight];
		for (int x=0;x<mapWidth;x++){
			for (int y=0;y<mapHeight;y++){
				// Tile Properties auslesen
				
				int tileID = map.getTileId(x, y, 0);
				String type = map.getTileProperty(tileID, "type", "grass");
				// Array einstellen
				types[x][y] = type;
				
				int blockedTileID = map.getTileId(x, y, 1);
				String value = map.getTileProperty(blockedTileID, "blocked", "false");
				// Array einstellen
				if (value.equals("true")) types[x][y] = "tree";
			}
		}
	}
	
	
	
	public void render(Graphics g){
		int playerX = 0; int playerY = 0;
		int x = -minimapWidth/2; 
		for (int i=player.getTileX()-minimapWidth/2; i<=player.getTileX()+minimapWidth/2; i++){
			int y = -minimapHeight/2;	
			for (int j=player.getTileY()-minimapHeight/2; j<=player.getTileY()+minimapHeight/2; j++){
				Rectangle temp = new Rectangle(posX+x*partSize,posY+y*partSize,partSize+1,partSize+1);
				if (j>=0 && i>=0 && i<mapWidth && j<mapHeight) {
					
					if (types[i][j].equals("tree")){
						g.setColor(TREE);
					}
					if (types[i][j].equals("grass")){
						g.setColor(GRASS);
					}					
					if (types[i][j].equals("nothing")){
						g.setColor(NOTHING);
					}
					if (player.getTileX()==i && player.getTileY()==j) { 
						playerX=x;playerY=y;
					}
					 
				
				} else { g.setColor(NOTHING); }
				y++; g.fill(temp);
			}
			x++;
		}
		
		Rectangle player = new Rectangle(posX+playerX*partSize-3,posY+playerY*partSize-3,partSize+4,partSize+4);
		g.setColor(PLAYER);
		g.fill(player);
	}
}
