package tyrelion.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

import tyrelion.map.TyrelionMap;
import tyrelion.objects.Player;

/**
 * @author imladriel
 *
 */
public class Minimap {
	
	private int posX = 952;
	private int posY = 664;
	
	private int minimapWidth=40;
	private int minimapHeight=40;
	
	private int mapWidth;
	private int mapHeight;
	
	private int partSize = 5;

	private String[][] types;
	private Color[][] minimap;
	private static org.newdawn.slick.Color PLAYER = new org.newdawn.slick.Color(200,10,10);
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
	
	private Image minimapImage;
	private int completeMapWidth = 0;
	private int completeMapHeight = 0;
	
	public Minimap(GameContainer gameContainer, TyrelionMap map, Player player){
		
		this.map = map;
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
		
		this.player = player;
		
		minimapWidth = 50;
		minimapHeight = 50;
		
		readTypes();
		
		completeMapWidth = map.getWidth()*partSize;
		completeMapHeight = map.getHeight()*partSize;
		minimapImage = generateMapImage(map);
		
	}
	
	private Image generateMapImage(TyrelionMap map){
		BufferedImage bufImg = new BufferedImage(completeMapWidth, completeMapHeight, BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics g = bufImg.createGraphics();
		
		int posX = 0; int posY = 0;
		
		for (int tileX=0; tileX < types.length; tileX++){
			for (int tileY=0; tileY < types[tileX].length; tileY++){
				posX = tileX * partSize;
				posY = tileY * partSize;
				if (types[tileX][tileY].equals("tree")){
					g.setColor(TREE);
					g.fillRect(posX, posY, partSize, partSize);
				}
				if (types[tileX][tileY].equals("grass")){
					g.setColor(GRASS);
					g.fillRect(posX, posY, partSize, partSize);
				}	
				if (types[tileX][tileY].equals("house")){
					g.setColor(HOUSE);
					g.fillRect(posX, posY, partSize, partSize);
				}	
				if (types[tileX][tileY].equals("mud")){
					g.setColor(MUD);
					g.fillRect(posX, posY, partSize, partSize);
				}
				if (types[tileX][tileY].equals("water")){
					g.setColor(WATER);
					g.fillRect(posX, posY, partSize, partSize);
				}
				if (types[tileX][tileY].equals("wood")){
					g.setColor(WOOD);
					g.fillRect(posX, posY, partSize, partSize);
				}
				if (types[tileX][tileY].equals("wagon")){
					g.setColor(WAGON);
					g.fillRect(posX, posY, partSize, partSize);
				}
				if (types[tileX][tileY].equals("stone")){
					g.setColor(STONE);
					g.fillRect(posX, posY, partSize, partSize);
				}	
				if (types[tileX][tileY].equals("nothing")){
					g.setColor(NOTHING);
					g.fillRect(posX, posY, partSize, partSize);
				}
			}
		}
		
		Texture texture = null;
		Image slickImage = null;
		try {
			texture = BufferedImageUtil.getTexture("", bufImg);
			slickImage = new Image(texture.getImageWidth(), texture.getImageHeight() ); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		slickImage.setTexture(texture) ;
		
		return slickImage;
	}
	
	public Image getPart(){
		
		int centerX = player.getTileX();
		int centerY = player.getTileY();
		
		int x = (centerX-(minimapWidth/2))*5;
		int y = (centerY-(minimapHeight/2))*5;
		
		int partWidth = minimapWidth*5;
		int partHeight = minimapHeight*5;
		
		return minimapImage.getSubImage(x, y, partWidth, partHeight);
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
	
	
	
	public void render(Graphics g){
		g.drawImage(getPart(), posX, posY);
		
		//Circle player = new Circle(posX+(minimapWidth/2)*partSize-1,posY+(minimapHeight/2)*partSize,partSize);
		//g.setColor(PLAYER);
		//g.fill(player);
	}
}
