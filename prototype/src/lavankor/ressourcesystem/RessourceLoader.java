package lavankor.ressourcesystem;

import java.util.ArrayList;
import java.util.HashMap;

import lavankor.GameMaster;
import lavankor.XMLIterator;
import lavankor.XMLRead;
import lavankor.dialog.Answer;
import lavankor.dialog.Dialog;
import lavankor.itemsystem.Item;
import lavankor.itemsystem.Nahkampfwaffe;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RessourceLoader {

	public HashMap<Integer, Item> loadItems() {
		HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
		String source = "res/files/xml/nahkampfwaffen.xml";
		
		// XML-Leser initialisieren
		XMLRead reader = new XMLRead(source);
		
		// Iterator anwenden
		XMLIterator xml_it = reader.iterator();
		Node current_elem = null;
		
		while(xml_it.hasNext())
		{
			current_elem = xml_it.next();
			// Items anlegen
			itemMap.put(Integer.parseInt(reader.getAttributeValue(current_elem, "id")), 
					new Nahkampfwaffe(
							Integer.parseInt(reader.getAttributeValue(current_elem, "id")), 
							reader.getText(reader.getElement(current_elem,"name")), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"hoehe"))), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"breite"))), 
							reader.getText(reader.getElement(current_elem,"text")), 
							GameMaster.placeImage(reader.getText(reader.getElement(current_elem,"bild"))), 
							GameMaster.placeImage(reader.getText(reader.getElement(current_elem,"icon"))), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"tp"))), 
							reader.getText(reader.getElement(current_elem,"kk")), 
							reader.getText(reader.getElement(current_elem,"haende")), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"at"))), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"pa"))), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"ini"))), 
							Integer.parseInt(reader.getText(reader.getElement(current_elem,"preis"))) ) );
		}
		return itemMap;
	}
	
	public HashMap<Integer, Dialog> loadDialogs() {
		HashMap<Integer, Dialog> dialogMap = new HashMap<Integer, Dialog>();
		String source = "res/files/xml/dialoge.xml";
		
		// XML-Leser initialisieren
		XMLRead reader = new XMLRead(source);
		
		// Iterator anwenden
		XMLIterator xml_it = reader.iterator();
		Node current_elem = null;
		
		while(xml_it.hasNext())
		{
			current_elem = xml_it.next();
			
			// Antworten ausfiltern
			ArrayList<Answer> answers = new ArrayList<Answer>();
			NodeList nodes = current_elem.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeName().equals("answer")) {
					String text = reader.getText(reader.getElement(nodes.item(i),"text"));
					int followUp = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"followUp"))); 
					int itemIn = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"itemIn")));
					int itemOut = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"itemOut")));
					String changeAtt_name = reader.getText(reader.getElement(nodes.item(i),"changeAtt_name")); 
					int changeAtt_amount = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"changeAtt_amount")));
					String changeFert_name = reader.getText(reader.getElement(nodes.item(i),"changeFert_name")); 
					int changeFert_amount = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"changeFert_amount")));
					int changeLE_amount = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"changeLE_amount")));
					int changeAE_amount = Integer.parseInt(reader.getText(reader.getElement(nodes.item(i),"changeAE_amount")));
					String challenge = reader.getText(reader.getElement(nodes.item(i),"challenge"));
					String quest_info = reader.getText(reader.getElement(nodes.item(i),"quest_info"));
					
					if (text.equals(" ")) { text=null; };
					if (changeAtt_name.equals(" ")) { changeAtt_name=null; };
					if (changeFert_name.equals(" ")) { changeFert_name=null; };
					if (challenge.equals(" ")) { challenge=null; };
					
					
					Answer answer = new Answer(text, followUp, itemIn, itemOut, changeAtt_name, changeAtt_amount, changeFert_name, changeFert_amount,
												changeLE_amount, changeAE_amount, challenge, quest_info);
					answers.add(answer);
				}
				
				
			}
			Dialog dialog = new Dialog(
					Integer.parseInt(reader.getAttributeValue(current_elem, "id")), 
					reader.getText(reader.getElement(current_elem,"text")), 
					GameMaster.placeImage(reader.getText(reader.getElement(current_elem,"picture"))),
					answers);
			
			dialogMap.put(Integer.parseInt(reader.getAttributeValue(current_elem, "id")), dialog);
						
		}
		return dialogMap;
	}
	
	public HashMap<String, Animation> loadAnimations() {
		HashMap<String, Animation> animationMap = new HashMap<String, Animation>();
		// Neuen SpriteSheet für die Laufanimation anlegen
		try {
			SpriteSheet sheet_walk = new SpriteSheet("res/animations/laufen.bmp",32,32, new Color(255,0,204));
		
			/* Die Spieleranimation initialisieren und AutoUpdate auf False setzen, damit die Animation nur
			 * bei Steuerungseingaben abgespielt wird. */
			Animation player_walk = new Animation();
			for (int frame=0;frame<8;frame++) {
				player_walk.addFrame(sheet_walk.getSprite(frame,0), 150);
			}
			player_walk.setAutoUpdate(false);
			
			// Die Laufanimation zur Liste hinzufügen
			animationMap.put("walk", player_walk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// Neuen SpriteSheet für die Laufanimation anlegen
			SpriteSheet sheet_npc_walk = new SpriteSheet("res/animations/laufen.bmp",32,32, new Color(255,0,204));
			
			/* Die Spieleranimation initialisieren und AutoUpdate auf False setzen, damit die Animation nur
			 * bei Steuerungseingaben abgespielt wird. */
			Animation npc_walk = new Animation();
			for (int frame=0;frame<8;frame++) {
				npc_walk.addFrame(sheet_npc_walk.getSprite(frame,0), 150);
			}
			npc_walk.setAutoUpdate(false);
			
			animationMap.put("npc_walk", npc_walk);
			
			// Neuen SpriteSheet für die Laufanimation anlegen
			SpriteSheet sheet_npc1_walk = new SpriteSheet("res/animations/laufen_npc1.bmp",32,32, new Color(255,0,204));
			
			/* Die Spieleranimation initialisieren und AutoUpdate auf False setzen, damit die Animation nur
			 * bei Steuerungseingaben abgespielt wird. */
			Animation npc1_walk = new Animation();
			for (int frame=0;frame<8;frame++) {
				npc1_walk.addFrame(sheet_npc1_walk.getSprite(frame,0), 150);
			}
			npc1_walk.setAutoUpdate(false);
			
			animationMap.put("npc1_walk", npc1_walk);
			
			// Neuen SpriteSheet für die Laufanimation anlegen
			SpriteSheet sheet_npc2_walk = new SpriteSheet("res/animations/laufen_npc2.bmp",32,32, new Color(255,0,204));
			
			/* Die Spieleranimation initialisieren und AutoUpdate auf False setzen, damit die Animation nur
			 * bei Steuerungseingaben abgespielt wird. */
			Animation npc2_walk = new Animation();
			for (int frame=0;frame<8;frame++) {
				npc2_walk.addFrame(sheet_npc2_walk.getSprite(frame,0), 150);
			}
			npc2_walk.setAutoUpdate(false);
			
			animationMap.put("npc2_walk", npc2_walk);
			
			// Neuen SpriteSheet für die Trinkanimation anlegen
			SpriteSheet sheet_drink = new SpriteSheet("res/animations/trinken.bmp",32,32, new Color(255,0,204));
			
			/* Die Spieleranimation initialisieren und AutoUpdate auf True setzen, sowie den Looping auf true setzen. */
			Animation player_drink = new Animation();
			for (int frame=0;frame<5;frame++) {
				player_drink.addFrame(sheet_drink.getSprite(frame,0), 150);
			}
			player_drink.setAutoUpdate(true);
			player_drink.setLooping(true);
			
			// Die Trinkanimation zur Liste hinzufügen
			animationMap.put("drink", player_drink);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return animationMap;
	}
	
	public HashMap<String, Sound> loadSounds() {
		HashMap<String, Sound> soundMap = new HashMap<String, Sound>();
		try {
			soundMap.put("walk", new Sound("res/sounds/walk.ogg"));
			soundMap.put("run", new Sound("res/sounds/run.ogg"));
			soundMap.put("drink", new Sound("res/sounds/drink.ogg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soundMap;
	}
	
	public HashMap<String, AngelCodeFont> loadFonts() {
		HashMap<String, AngelCodeFont> fontMap = new HashMap<String, AngelCodeFont>();
		AngelCodeFont temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/elphin_12_black.fnt","fonts/elphin_12_black.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        } 
		fontMap.put("12_black", temp);
		temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/elphin_12_white.fnt","fonts/elphin_12_white.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        }
		fontMap.put("12_white", temp);
		
		temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/arial_12_white_bold.fnt","fonts/arial_12_white_bold.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        }
		fontMap.put("arial_12_white_bold", temp);
		
		temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/elphin_18_black.fnt","fonts/elphin_18_black.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        }
		fontMap.put("18_black", temp);
		temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/elphin_24_black.fnt","fonts/elphin_24_black.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        }
		fontMap.put("24_black", temp);
		temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/elphin_32_black.fnt","fonts/elphin_32_black.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        }
		fontMap.put("32_black", temp);
		temp = null;
		try {
			temp = new AngelCodeFont("res/fonts/elphin_48_black.fnt","fonts/elphin_48_black.png");
		} catch (SlickException e) { 
            e.printStackTrace(); 
        }
		fontMap.put("48_black", temp);
		return fontMap;
	}
	
	public HashMap<String, Music> loadMusics() {
		HashMap<String, Music> musicMap = new HashMap<String, Music>();
		try {
			musicMap.put("into_the_red", new Music("res/music/Into The Red.ogg"));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return musicMap;
	}
	
	public Music loadMusicMenu() {
		Music music = null;
		try {
			music = new Music("res/music/Into The Red.ogg", true);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return music;
	}
	
	public Music loadMusicCredits() {
		Music music = null;
		try {
			music = new Music("res/music/Epilogue.ogg", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return music;
	}
	
	public HashMap<Integer, Music> loadMusicNavNormal() {
		HashMap<Integer, Music> map = new HashMap<Integer, Music>();
		try {
			map.put(0, new Music("res/music/nav_normal/The City.ogg", true));
			map.put(1, new Music("res/music/nav_normal/The Quest.ogg", true));
			map.put(2, new Music("res/music/nav_normal/Remember Green.ogg", true));
			map.put(3, new Music("res/music/nav_normal/The Map.ogg", true));
			map.put(4, new Music("res/music/nav_normal/The Forest.ogg", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public HashMap<Integer, Music> loadMusicNavTension() {
		HashMap<Integer, Music> map = new HashMap<Integer, Music>();
		try {
			map.put(0, new Music("res/music/nav_tension/Dungeons.ogg", true));
			map.put(1, new Music("res/music/nav_tension/The Cursed.ogg", true));
			map.put(2, new Music("res/music/nav_tension/Through The Mist.ogg", true));
			map.put(3, new Music("res/music/nav_tension/Ghost Ship.ogg", true));
			map.put(4, new Music("res/music/nav_tension/Going Down.ogg", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public HashMap<Integer, Music> loadMusicFight() {
		HashMap<Integer, Music> map = new HashMap<Integer, Music>();
		try {
			map.put(0, new Music("res/music/fight/Ambush.ogg", true));
			map.put(1, new Music("res/music/fight/Just Kill It.ogg", true));
			map.put(2, new Music("res/music/fight/Starting The Hunt.ogg", true));
			map.put(3, new Music("res/music/fight/Wild Rage.ogg", true));
			map.put(4, new Music("res/music/fight/Flesh And Bones.ogg", true));
			map.put(5, new Music("res/music/fight/Riders From The East.ogg", true));
			map.put(6, new Music("res/music/fight/The Battle Of Dragons.ogg", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public HashMap<Integer, Music> loadMusicTavern() {
		HashMap<Integer, Music> map = new HashMap<Integer, Music>();
		try {
			map.put(0, new Music("res/music/tavern/Quayside Pub.ogg", true));
			map.put(1, new Music("res/music/tavern/The Tavern.ogg", true));
			map.put(2, new Music("res/music/tavern/At The Fair.ogg", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
