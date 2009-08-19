/**
 * 
 */
package tyrelion.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import tyrelion.Player;
import tyrelion.TyrelionMap;

/**
 * @author imladriel
 *
 */
public class Minimap {
	
	private int posX = 1052;
	private int posY = 784;
	
	private int minimapWidth=40;
	private int minimapHeight=40;
	
	private int mapWidth;
	private int mapHeight;
	
	private int partSize = 5;

	private String[][] types;
	private Color[][] minimap;
	private static Color PLAYER = new Color(200,10,10);
	private static Color NOTHING = Color.black;
	private static Color GRASS = new Color(0x00125804);
	private static Color TREE = new Color(0x0065280b);
	private static Color HOUSE = Color.darkGray;
	private static Color MUD = new Color(0x007f3f00);
	private static Color WATER = Color.blue;
	private static Color WOOD = new Color(0x006b2100);
	private static Color WAGON = new Color(0x00eac9a2);
	private static Color STONE = new Color(0x00a1876e);
	
	private TyrelionMap map;
	private Player player;
	
	public Minimap(GameContainer gameContainer, TyrelionMap map, Player player){
		
		this.map = map;
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
		
		this.player = player;
		
		minimapWidth = 50;
		minimapHeight = 50;
		
		readTypes();
		
		generateMinimap();
	}
	
	
	public void readTypes(){
		types = new String[mapWidth][mapHeight];
		for (int x=0;x<mapWidth;x++){
			for (int y=0;y<mapHeight;y++){
				String type = "";
				// Tile Properties auslesen
				for (int i=0;i<9;i++){
					int tileID = map.getTileId(x, y, i);
					type = map.getTileProperty(tileID, "type", "failure");
					// Array einstellen
					if (type!="failure") types[x][y] = type;
				}
				if (type == "") types[x][y] = "nothing";
			}
		}
	}
	
	public void generateMinimap(){
		minimap = new Color[types.length][types[0].length];
		for (int x=0; x < types.length; x++){
			for (int y=0; y < types[x].length; y++){
				if (types[x][y].equals("tree")){
					minimap[x][y] = TREE;
				}
				if (types[x][y].equals("grass")){
					minimap[x][y] = GRASS;
				}	
				if (types[x][y].equals("house")){
					minimap[x][y] = HOUSE;
				}	
				if (types[x][y].equals("mud")){
					minimap[x][y] = MUD;
				}
				if (types[x][y].equals("water")){
					minimap[x][y] = WATER;
				}
				if (types[x][y].equals("wood")){
					minimap[x][y] = WOOD;
				}
				if (types[x][y].equals("wagon")){
					minimap[x][y] = WAGON;
				}
				if (types[x][y].equals("stone")){
					minimap[x][y] = STONE;
				}	
				if (types[x][y].equals("nothing")){
					minimap[x][y] = NOTHING;
				}
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
					
					g.setColor(minimap[i][j]);

					if (player.getTileX()==i && player.getTileY()==j) { 
						playerX=x;playerY=y;
					}
					
					g.fill(temp);
				}
				y++;
			}
			x++;
		}
		
		Rectangle player = new Rectangle(posX+playerX*partSize-3,posY+playerY*partSize,partSize+4,partSize+4);
		g.setColor(PLAYER);
		g.fill(player);
	}
}
