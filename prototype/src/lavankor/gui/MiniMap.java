/**
 * 
 */
package lavankor.gui;

import lavankor.avatar.HaendlerNPC;
import lavankor.avatar.Player;
import lavankor.map.LavankorMap;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author Administrator
 *
 */
public class MiniMap {
	
	private int posX;
	private int posY;
	
	private int minimapWidth=50;
	private int minimapHeight=50;
	
	private int partSize = 4;

	private String[][] types;
	
	private LavankorMap map;
	private TiledMap tiles;
	private Player player;
	
	private int width;
	private int height;
	
	/** Das Spritesheet das die Grundgrafiken zum GUI enthält.*/
	private PackedSpriteSheet gui;
	
	private Display display;
	private GameContainer container;
	
	private Image rahmen;
	private Image btn_pic;
	private Image btn_over;
	private Image btn_down;
	
	/**
	 * 
	 */
	public MiniMap(GameContainer container, LavankorMap map, Player player) throws SlickException {
		this.map = map;
		tiles = map.getMap();
		width = tiles.getWidth();
		height = tiles.getHeight();
		this.player = player;
		posX = 1024 - minimapWidth/2*partSize -2;
		posY = 768 - minimapHeight/2*partSize -2;
		gui = new PackedSpriteSheet("res/images/interface.def",new Color(255,0,204));
		rahmen = gui.getSprite("minimap");
		btn_pic = new Image("res/images/btn_menu.jpg");
		btn_over = new Image("res/images/btn_menu3.jpg");
		btn_down = new Image("res/images/btn_menu2.jpg");
		readTypes();
		
		this.container = container;
		display = new Display(container);
		
		Container content = new Container();
        content.setSize(220, 270);
        content.setLocation(posX-minimapWidth/2*partSize-14, posY-minimapHeight/2*partSize-64);
		
        // Buttons erstellen und positionieren
        Button btn = new Button(btn_pic);
        btn.setLocation(112, 5);
        btn.setPadding(0);
        btn.setRolloverImage(btn_over);
        btn.setDownImage(btn_down);
        btn.pack();
        btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
			}
        }); 
        content.add(btn);
        
        display.add(content);  
	}

	public void readTypes(){
		types = new String[width+minimapWidth/2][height+minimapHeight/2];
		for (int x=0;x<width;x++){
			for (int y=0;y<height;y++){
				// Tile Properties auslesen
				int tileID = tiles.getTileId(x, y, 0);
				String value = tiles.getTileProperty(tileID, "type", "nothing");
				// Array einstellen
				types[x][y] = value;
			}
		}
		for (int i=width;i<width+minimapWidth/2;i++){
			for (int j=height;j<height+minimapHeight/2;j++){
				types[i][j]="nothing";
			}
		}
	}
	
	public void draw(Graphics g){
		int playerX = 0; int playerY = 0;
		int x = -minimapWidth/2; 
		for (int i=player.getTileX()-minimapWidth/2; i<=player.getTileX()+minimapWidth/2; i++){
			
			int y = -minimapHeight/2;	
			for (int j=player.getTileY()-minimapHeight/2; j<=player.getTileY()+minimapHeight/2; j++){
				Rectangle temp = new Rectangle(posX+x*partSize-9,posY+y*partSize-9,partSize+2,partSize+2);
				if (j>=0 && i>=0 && i<width && j<height) {
					
					if (types[i][j].equals("grass")){
						g.setColor(new Color(50,100,0));
					}
					if (types[i][j].equals("wood")){
						g.setColor(new Color(100,40,0));
					}
					if (types[i][j].equals("stone")){
						g.setColor(new Color(90,80,70));
					}
					if (types[i][j].equals("lehm")){
						g.setColor(new Color(140,85,40));
					}
					if (types[i][j].equals("forrest")){
						g.setColor(new Color(30,60,0));
					}
					if (types[i][j].equals("huette")){
						g.setColor(new Color(190,125,80));
					}
					if (player.getTileX()==i && player.getTileY()==j) { 
						playerX=x;playerY=y;
					}
					 
				
				} else { g.setColor(new Color(0,0,0)); }
				y++; g.fill(temp);
			}
			x++;
		}
		x = -minimapWidth/2;
		for (int i=player.getTileX()-minimapWidth/2; i<=player.getTileX()+minimapWidth/2; i++){
			
			int y = -minimapHeight/2;	
			for (int j=player.getTileY()-minimapHeight/2; j<=player.getTileY()+minimapHeight/2; j++){
				if (j>=0 && i>=0 && i<width && j<height) {
					Rectangle temp = new Rectangle(posX+x*partSize-9,posY+y*partSize-9,partSize+2,partSize+2);
					if (map.getNpcMap().get(i, j)!=null){
						if (map.getNpcMap().get(i, j) instanceof  HaendlerNPC){
							g.setColor(new Color(0,0,255));	
						} else {
						g.setColor(new Color(255,255,0));
						}
						g.fill(temp); 
					}
				
				}
				y++;
			}
			x++;
		}
		
		Rectangle player = new Rectangle(posX+playerX*partSize-9,posY+playerY*partSize-9,partSize+4,partSize+4);
		g.setColor(new Color(200,10,10));
		g.fill(player);
		
		g.setColor(Color.black);
		g.drawRect(posX-minimapWidth/2*partSize-9, posY-minimapHeight/2*partSize-9, minimapWidth*partSize+partSize+2, minimapHeight*partSize+partSize+2);
		g.setColor(new Color(0,0,0, 0.7f));
		g.fill(new Rectangle(posX-minimapWidth/2*partSize-9, posY-minimapHeight/2*partSize-30, 102, 16));
		g.setColor(Color.lightGray);
		g.drawString("Glimstayn", posX-minimapWidth/2*partSize+10, posY-minimapHeight/2*partSize-32);
		display.render(container, g);
		g.drawImage(rahmen, posX-minimapWidth/2*partSize-14-2, posY-minimapHeight/2*partSize-64-2);
		
	}

}
