package lavankor;

import java.awt.Point;

import lavankor.avatar.HaendlerNPC;
import lavankor.avatar.NPC;
import lavankor.avatar.Player;
import lavankor.dialog.Dialog;
import lavankor.gui.Haendler;
import lavankor.gui.OutputBox;
import lavankor.haendler.DragnDrop;
import lavankor.haendler.Raster;
import lavankor.itemsystem.Item;
import lavankor.map.ItemMap;
import lavankor.map.LavankorMap;
import lavankor.map.NpcMap;
import lavankor.questsystem.dummy_quest;
import lavankor.ressourcesystem.RessourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameMaster {
	
	private static GameMaster instance = null;
	
	
	private Player player;
	private LavankorMap map;
	private OutputBox outBox;
	private Dialog activeDialog = null;
	private DragnDrop dnd;
	private Haendler activeHaendler = null;
	
	public GameMaster() throws SlickException {
        player = new Player(this);
        dnd = new DragnDrop(new Raster(12,10,50+14,50+15,30,30),new Raster(10,12,400 + 50 + 14,50 + 15,30,30));
	}
	
	public static GameMaster getInstance() {
		if ( instance==null ){
			try { instance =  new GameMaster(); } catch (SlickException e) { e.printStackTrace(); } }
		return instance;
	}
	
	public static Image placeImage(String path) {
		Image image = null;
		try { image =  new Image("res/images/bilderror.jpg"); } catch (SlickException e) { e.printStackTrace(); };
		try { image =  new Image(path, new Color(255, 0, 204)); } catch (SlickException e) { e.printStackTrace(); };
		return image;
	}

	public Point translateCoordinates(int x, int y) {
		float mouseX;
		float mouseY;
		float targetX;
		float targetY;
		float tempX = x / (new Float(map.getTileSize()));
		float tempY = y / (new Float(map.getTileSize()));
		int offsetX = (int)(((int)tempX - tempX) * map.getTileSize());
		int offsetY = (int)(((int)tempY - tempY) * map.getTileSize());
		mouseX = (x-offsetX) / (new Float(map.getTileSize()));
		mouseY = (y-offsetY) / (new Float(map.getTileSize()));
		
		// absolute in relative Position und Ziel-Tile berechnen
		if (mouseX < player.getPosX()) {
			targetX = player.getPosX() - new Float(map.getLeftOffsetInTiles()) + mouseX;
		} else {
			targetX = player.getPosX() + mouseX - new Float(map.getLeftOffsetInTiles());
		}
		if (mouseY < player.getPosY()) {
			targetY = player.getPosY() - new Float(map.getTopOffsetInTiles()) + mouseY;
		} else {
			targetY = player.getPosY() + mouseY - new Float(map.getTopOffsetInTiles());
		}
		return new Point((int)targetX, (int)targetY);
	}
	
	public float calcDist(float x, float y) {
		float a = Math.abs(x - player.getPosX());
		float b = Math.abs(y - player.getPosY());
		return (float) Math.sqrt(a*a + b*b);
	}
	
	public void rightClick(int x, int y) {
		Point p = translateCoordinates(x, y);
		ItemMap itemMap = map.getItemMap();
		NpcMap npcMap = map.getNpcMap();
		if (calcDist(p.x, p.y) <= 3 ) {
			for (int i = p.x-3; i < p.x+3; i++) {
				for (int j = p.y-3; j < p.y+3; j++) {
					if (npcMap.get(i, j) != null) {
						NPC npc = npcMap.get(i, j);
						if ((int)npc.getPosX() == p.x && (int)npc.getPosY() == p.y) {
							
							if (npc instanceof HaendlerNPC) {
								activeHaendler = ((HaendlerNPC) npc).getHaendler();
								activeHaendler.setShowDia(true);
							} else {
								if (npc.getDialog()!=null){
									npc.getDialog().setShowDialog(true);
									activeDialog = npc.getDialog();
								}
							
							}
						}
					}
				}
			}	
			for (int i = p.x-3; i < p.x+3; i++) {
				for (int j = p.y-3; j < p.y+3; j++) {
					if (itemMap.get(i, j) != null) {
						Item item = itemMap.get(i, j);
						if (i == p.x && j == p.y) {
							player.getCharacter().getInventory().addItem(item.getItem_Impl());
							itemMap.remove(i, j);
							print(player.getCharacter().getName() + " hat folgenden Gegenstand erhalten: "
									+ item.getName());
							
							
						}
					}
				}
			}
		} else if (itemMap.get(p.x, p.y) != null || npcMap.get(p.x, p.y) != null) {
			player.getPathfinder().startMovement(x, y);
		}
	}
	
	public void print(String text) {
		outBox.print(text);
	}
	
	public void print(String text, Color color) {
		outBox.print(text, color);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public LavankorMap getMap() {
		return map;
	}

	public void setMap(LavankorMap map) {
		this.map = map;
		player.setMap(map);
	}

	public Dialog getActiveDialog() {
		return activeDialog;
	}

	public void setActiveDialog(Dialog activeDialog) {
		this.activeDialog = activeDialog;
	}

	public OutputBox getOutBox() {
		return outBox;
	}

	public void setOutBox(OutputBox outBox) {
		this.outBox = outBox;
	}	
	
	public DragnDrop getDnd()
	{
		return dnd;
	}

	public Haendler getActiveHaendler() {
		return activeHaendler;
	}

	public void setActiveHaendler(Haendler activeHaendler) {
		this.activeHaendler = activeHaendler;
	}
}
