package lavankor;

import lavankor.avatar.HaendlerNPC;
import lavankor.avatar.NPC;
import lavankor.itemsystem.Item;
import lavankor.map.LavankorMap;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LevelLoader {

	private LavankorMap map;
	private GameMaster gameMaster;
	private InGame inGame;
	private GameContainer gameContainer;
	
	public LevelLoader(GameMaster gameMaster, GameContainer gameContainer, InGame inGame) {
		this.gameMaster = gameMaster;
		this.gameContainer = gameContainer;
		this.inGame = inGame;
	}
	
	public void loadLevel(int ID) {
		loadMap(ID);
		loadItemMap(ID);
		loadNpcMap(ID);
	}
	
	public void loadMap(int ID) {
		String source = "res/files/xml/maps.xml";
		// XML-Leser initialisieren
		XMLRead reader = new XMLRead(source);
		// Iterator anwenden
		XMLIterator xml_it = reader.iterator();
		Node current_elem = null;
		
		while(xml_it.hasNext()) {
			current_elem = xml_it.next();
			if (Integer.parseInt(reader.getAttributeValue(current_elem, "id")) == ID) {
				String mapname = reader.getText(reader.getElement(current_elem, "name"));
				int x = Integer.valueOf((reader.getAttributeValue(reader.getElement(current_elem, "player"), "x")));
				int y = Integer.valueOf((reader.getAttributeValue(reader.getElement(current_elem, "player"), "y")));
				GameMaster.getInstance().getPlayer().setPosX(x);
				GameMaster.getInstance().getPlayer().setPosY(y);
				try {
					map = new LavankorMap(new TiledMap("res/maps/"+mapname+".tmx"), gameContainer, gameMaster);
					gameMaster.setMap(map);
					inGame.setMap(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}	
	}
	
	public void loadItemMap(int ID) {
		String source = "res/files/xml/maps.xml";
		
		// XML-Leser initialisieren
		XMLRead reader = new XMLRead(source);
		
		// Iterator anwenden
		XMLIterator xml_it = reader.iterator();
		Node current_elem = null;
		
		while(xml_it.hasNext()) {
			current_elem = xml_it.next();
			if (Integer.parseInt(reader.getAttributeValue(current_elem, "id")) == ID) {
				//Über alle Elemente vom Typ Item iterieren
				NodeList nodes = current_elem.getChildNodes();
				for (int i = 0; i < nodes.getLength(); i++) {
					if (nodes.item(i).getNodeName().equals("item")) {
						Item item = RessourceManager.getInstance().getItem(Integer.parseInt(reader.getAttributeValue(nodes.item(i), "id")));
						int x = Integer.parseInt(reader.getAttributeValue(nodes.item(i), "x"));
						int y = Integer.parseInt(reader.getAttributeValue(nodes.item(i), "y"));
						gameMaster.getMap().getItemMap().add(item, x, y);
					}
				}
				break;
			}
		}
	}
	
	public void loadNpcMap(int ID) {
		String source = "res/files/xml/maps.xml";
		
		// XML-Leser initialisieren
		XMLRead reader = new XMLRead(source);
		
		// Iterator anwenden
		XMLIterator xml_it = reader.iterator();
		Node current_elem = null;
		
		while(xml_it.hasNext()) {
			current_elem = xml_it.next();
			if (Integer.parseInt(reader.getAttributeValue(current_elem, "id")) == ID) {
				//Über alle Elemente vom Typ NPC iterieren
				NodeList nodes = current_elem.getChildNodes();
				for (int i = 0; i < nodes.getLength(); i++) {
					if (nodes.item(i).getNodeName().equals("npc")) {
						String name = reader.getText(reader.getElement(nodes.item(i), "name"));
						int x = Integer.parseInt(reader.getAttributeValue(nodes.item(i), "x"));
						int y = Integer.parseInt(reader.getAttributeValue(nodes.item(i), "y"));
						int ang = Integer.parseInt(reader.getAttributeValue(nodes.item(i), "ang"));
						String art = reader.getText(reader.getElement(nodes.item(i), "art")); 
						
						NPC npc;
						if (art.equals("Haendler")){
							npc = new HaendlerNPC(name, RessourceManager.getInstance().getAnimation(RessourceStrings.ANIMATION_NPC2_WALK),
									gameMaster.getMap(),
									null,
									x, y, ang);
						} else {
							int dialog = Integer.parseInt(reader.getAttributeValue(nodes.item(i), "dialog"));
							npc = new NPC(name, RessourceManager.getInstance().getAnimation(RessourceStrings.ANIMATION_NPC1_WALK),
									gameMaster.getMap(),
									RessourceManager.getInstance().getDialog(dialog),
									x, y, ang);
						}
						
						
						
						gameMaster.getMap().getNpcMap().add(npc, x, y);
					}
				}
				break;
			}
		}
	}
}
