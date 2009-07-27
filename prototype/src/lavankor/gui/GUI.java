package lavankor.gui;

import lavankor.GameMaster;
import lavankor.character.Character;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;

/** Diese Klasse implementiert das GraphicalUserInterface, 
 * bestehend aus der Minimap auf der rechten Seite und dem 
 * Charakteravatar inklusive lE und AE Anzeige.
 * 
 * @author diceware
 * @version 0.1a
 */
public class GUI {

	/** Die Informationsfenster für die Charakterdaten. */
	private CharInfo charinfo;
	
	/**Inventar-fenster*/
	private Haendler inventar;
	
	/**Ausgabebox*/
	private OutputBox outbox;
	
	/**MiniMap*/
	private MiniMap minimap;
		
	/** Bild für die Avatar anzeige mit Aussparungen für die LE und AE (noch Rahmen & Bild)*/
	private Image avatar;
	
	/** Das Rechteck, welches die Lebensenergie repräsentiert.*/
	private RoundedRectangle leben;
	/** Das Rechteck, welches die Magieenergie repräsentiert.*/
	private RoundedRectangle astral;
	
	
	/** Das Spritesheet das die Grundgrafiken zum GUI enthält.*/
	private PackedSpriteSheet gui;
	
	/**Variable für sie Zwischenspeicherung des aktuellen Characters.*/
	private Character character;
	
	private GameContainer container;
	
	/** Konstrukotr der Klasse, initialisiert die Grafiken und wandelt die Sprite Sheets in Grafiken um.
	 * @param player Aktueller Spieler dessen GUI dargestelllt wird.
	 * @throws SlickException
	 */
	public GUI(GameMaster gameMaster, GameContainer container) throws SlickException {
		this.character = gameMaster.getPlayer().getCharacter();
		this.container = container;
		
		gui = new PackedSpriteSheet("res/images/interface.def",new Color(255,0,204));
		avatar = gui.getSprite("char_holz_kopie");
		
		charinfo = new CharInfo(container, gameMaster.getPlayer());
		minimap = new MiniMap(container, gameMaster.getMap(), gameMaster.getPlayer());
		inventar = new Haendler(gameMaster.getPlayer(), "inventar");
		GameMaster.getInstance().setActiveHaendler(new Haendler(gameMaster.getPlayer(), "haendler"));
		outbox = new OutputBox();
		
		leben = new RoundedRectangle(25, 459, 15, 152, 5, 5);		
		astral = new RoundedRectangle(64, 459, 15, 152, 5, 5);
	}
	
	
	/** Zeichnet die GUI in die Graphics aus dem Spiel.
	 * @param g Zu nutzende Graphics
	 */
	public void draw(Graphics g) {
		//GUI zeichnen
		g.drawImage(avatar, 0, 418);
		g.setColor(new Color(153,0,0));
		leben.setHeight(getLEbar());
		leben.setY(459+152-getLEbar());
		g.fill(leben);
		g.setColor(new Color(0,0,153));
		astral.setHeight(getAEbar());
		astral.setY(459+152-getAEbar());
		g.fill(astral);
		//eventuell CharInfo anzeigen
		if (charinfo.isShowCharinfo()) {
			charinfo.draw(g); 
		}
		//eventuell Inventar anzeigen
		if (inventar.isShowDia()) {
			inventar.draw(g);
		}
		//eventuell Händler-Menu anzeigen
		Haendler haendler = GameMaster.getInstance().getActiveHaendler();
		if (haendler.isShowDia()) {
			haendler.draw(g);
		}
		outbox.draw(container, g);
		minimap.draw(g);
	}
	
	
	/** Gibt die Höhe der Lebensenergieanzeige auf Basis der aktuellen
	 * Lebensenergie des Spieler zurück.
	 * @return Höhe der Leiste
	 */
	private int getLEbar(){
		return Math.round(character.getLe().getAktuell()*152/character.getLe().getMax());
	}
	
	/** Gibt die Höhe der Magieenergieanzeige auf Basis der aktuellen
	 * Magieenergie des Spieler zurück.
	 * @return Höhe der Leiste
	 */
	private int getAEbar(){
		return Math.round(character.getAe().getAktuell()*152/character.getAe().getMax());
	}


	public CharInfo getCharinfo() {
		return charinfo;
	}


	public Haendler getInventar() {
		return inventar;
	}

	public Haendler getHaendler() {
		return GameMaster.getInstance().getActiveHaendler();
	}

	public OutputBox getOutbox() {
		return outbox;
	}


	public MiniMap getMinimap() {
		return minimap;
	}

	
}
