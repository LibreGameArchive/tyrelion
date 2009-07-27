package lavankor.gui;

import lavankor.avatar.Player;
import lavankor.character.AbgAttribut;
import lavankor.character.Attribut;
import lavankor.character.Character;
import lavankor.character.Manoever;
import lavankor.character.Vor_Nachteil;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Diese Klasse implementiert die Anzeige der Charakterinformation auf verschiedenen Seiten. 
 *  Die Charakterinformationen werden automatisch aus dem übergebenen Charakter ausgelesen.
 * @author Administrator
 * @ersion 0.1a
 */
public class CharInfo {
	/** Hintergrundseite für die Charakterinformation */
	private Image charInfo;
	/** Hintergrundseite für die Kampfinformationsseite */
	private Image waffenInfo;
	/** Hintergrundseite für die Rüstungsinformationsseite */
	private Image ruestInfo;
	/** Hintergrundseite für die Abgeleiteten Attribute */
	private Image abgAttrInfo;
	/** Hintergrundseite für die Fertigkeitenseite */
	private Image fertInfo;
	/** Hintergrundseite für die Vor/Nachteile und Manöver */
	private Image vortInfo;
	
	/** aktuelle Anzeige 
	 * 
	 *  0 = Charakterinformation
	 *  1 = Kampfinformation
	 *  2 = Rüstungsinformation
	 *  3 = Abgeleitete Attribute
	 *  4 = Fertigkeiten
	 *  5 = Vorteile, Nachteile und Manöver
	 */
	private int anzeige = 0;
	
	/** Option, ob die Charakterinformation gerade angezeigt werden soll.*/
	private boolean showCharinfo = false;
	
	/** Zwischenspeicher für gerade angezeigte Fonts. */
	private AngelCodeFont font;
	
	/** Übergebener Charakter zum auslesen der Charakterdaten. */
	private Character character;
	
	/** Display für Buttons auf den Seiten */
	private Display display;
	
	/** Weiter-Button */
	private Button weiter;
	
	/** Zurück-Button */
	private Button zurueck;
	
	/** Container als Umgebung für das Display */
	private GameContainer container;
	
	
	/** Konstruktor der Klasse. Hier werden die Bilder initialisiert.
	 * @param player Übergebeneer Spieler. Enthält auch die Charakterdaten.
	 * @throws SlickException
	 */
	public CharInfo(GameContainer container, Player player) throws SlickException {
		this.character = player.getCharacter();

		charInfo = new Image("res/images/charinfo/char_screen.jpg");
		waffenInfo = new Image("res/images/charinfo/char_waffen.jpg");
		ruestInfo = new Image("res/images/charinfo/char_verteidigung.jpg");
		abgAttrInfo = new Image("res/images/charinfo/char_abgAttr.jpg");
		fertInfo = new Image("res/images/charinfo/char_fertigkeiten.jpg");
		vortInfo = new Image("res/images/charinfo/char_vor_nach_man.jpg");
		
		display = new Display(container);
		this.container = container;
		
		Container content = new Container();
        content.setSize(400, 530);
        content.setLocation(50, 50);
		
		weiter = new Button(">>");
		weiter.pack();
		weiter.setForeground(new Color(112,51,0));
		weiter.setWidth(50);
		weiter.setHeight(30);
		weiter.setLocation(350, 500);
        weiter.setPadding(5);
        weiter.setBorderRendered(true);
        weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawNext();
			}
        }); 
        content.add(weiter);
        
        zurueck = new Button("<<");
        zurueck.pack();
        zurueck.setForeground(new Color(112,51,0));
        zurueck.setWidth(50);
        zurueck.setHeight(30);
        zurueck.setLocation(0, 500);
        zurueck.setPadding(0);        
        zurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawPrev();
			}
        }); 
        content.add(zurueck);
        
        display.add(content);
	}
	
	
	/** Anzeigen der nächsten Informationsseite. */
	public void drawNext(){
		anzeige = (anzeige + 1) % 6;
	}
	
	/** Anzeigen der vorherigen Informationsseite. */
	public void drawPrev(){
		anzeige = (anzeige - 1) % 6;
		if (anzeige<0) {anzeige *= -1;}
	}
	
	
	/** Zeichnet die gerade ausgewählte Informationsseite,
	 * abhängig vom Zustand von showCharInfo.
	 * @param g Graphics in denen angezeigt wird.
	 */
	public void draw(Graphics g) {	
		
		//Charinfo anzeigen, wenn die zugeordnete taste gedrückt wurde.
		if (showCharinfo) {

			if (anzeige==0) {
				drawChar(g);
				weiter.setVisible(true);
				zurueck.setVisible(false);
				display.render(container, g);
			}
			if (anzeige==1) {
				drawWeapons(g);
				weiter.setVisible(true);
				zurueck.setVisible(true);
				display.render(container, g);
			}
			if (anzeige==2) {
				drawArmour(g);
				weiter.setVisible(true);
				zurueck.setVisible(true);
				display.render(container, g);
			}
			if (anzeige==3) {
				drawAbgAttr(g);
				weiter.setVisible(true);
				zurueck.setVisible(true);
				display.render(container, g);
			}
			if (anzeige==4) {
				drawFert(g);
				weiter.setVisible(true);
				zurueck.setVisible(true);
				display.render(container, g);
			}
			if (anzeige==5) {
				drawVort(g);
				weiter.setVisible(false);
				zurueck.setVisible(true);
				display.render(container, g);
			}
			
			
		}
	}

	/** Methode zur Abfrage ob angezeigt wird.
	 * @return true = CharInfo wird angezeigt; false = wird nicht angezeigt
	 */
	public boolean isShowCharinfo() {
		return showCharinfo;
	}

	/** Ändern der Anzeigeoption ob angezeigt wird.
	 * @param showCharinfo true = anzeigen; false = nicht anzeigen
	 */
	public void setShowCharinfo(boolean showCharinfo) {
		this.showCharinfo = showCharinfo;
	}
	
	
	/** Methode zum anzeigen der Charakterinformation,
	 * diese beinhaltet den Namen, die Rasse und Kultur, sowie die LE uns AE und die Eigenschaften.
	 * @param g Graphics in denen angezeigt wird.
	 * @param aFont zu nutzende Schriftart
	 */
	public void drawChar(Graphics g) {
		g.drawImage(charInfo,50,50);

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_48_BLACK);
		font.drawString(230, 100, character.getName(), Color.black);
		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_32_BLACK);
		font.drawString(350, 370, Integer.toString(character.getLe().getMax()), Color.black);
		font.drawString(350, 460, Integer.toString(character.getAe().getMax()), Color.black);
		font.drawString(270, 190, character.getRasse().getName(), Color.black);
		font.drawString(270, 260, character.getKultur().getName(), Color.black);
		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		int posY = 308;
		for (String name: character.getAttribute().getAttribute().keySet()) {
			Attribut elem = character.getAttribute().get(name);
			font.drawString(200, posY, String.valueOf(elem.getWert()));
			posY += 24;
		}
	}
	
	public void drawWeapons(Graphics g) {
		g.drawImage(waffenInfo,50,50);

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_48_BLACK);
		font.drawString(340, 80, Integer.toString(character.getAbgAttribute().getWert("Attacke")));
		font.drawString(340, 160, Integer.toString(character.getAbgAttribute().getWert("Parade")));
		font.drawString(340, 240, Integer.toString(character.getAbgAttribute().getWert("Fernkampf")));
	}
	
	public void drawArmour(Graphics g) {
		g.drawImage(ruestInfo,50,50);

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_48_BLACK);
		font.drawString(105, 477, Integer.toString(character.getAbgAttribute().get("Parade").getWert()));
		font.drawString(167, 477, Integer.toString(character.getBe()));
		font.drawString(380, 477, Integer.toString(character.getBe()+character.getAbgAttribute().get("Parade").getWert()));
	}
	
	public void drawAbgAttr(Graphics g) {
		g.drawImage(abgAttrInfo,50,50);

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		int posY = 132;
		for (String name: character.getAbgAttribute().getAbgAttribute().keySet()) {
			AbgAttribut elem = character.getAbgAttribute().get(name);
			font.drawString(230, posY, String.valueOf(elem.getWert()));
			posY+=24;
		}
		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_48_BLACK);
		font.drawString(345, 205, Integer.toString(character.getAttribute().getWert("GW")+character.getAttribute().getWert("SI")+character.getFertigkeiten().getWert("aufmerksamkeit")));
	}
	
	public void drawFert(Graphics g) {
		g.drawImage(fertInfo,50,50);

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		font.drawString(215, 135, String.valueOf(character.getFertigkeiten().getWert("aufmerksamkeit")));
		font.drawString(215, 165, String.valueOf(character.getFertigkeiten().getWert("ausweichen")));
		font.drawString(215, 195, String.valueOf(character.getFertigkeiten().getWert("handel")));
		font.drawString(215, 225, String.valueOf(character.getFertigkeiten().getWert("koerperbeherrschung")));
		font.drawString(215, 255, String.valueOf(character.getFertigkeiten().getWert("menschenkenntnis")));
		font.drawString(215, 285, String.valueOf(character.getFertigkeiten().getWert("schleichen")));
		font.drawString(405, 135, String.valueOf(character.getFertigkeiten().getWert("schloesser_knacken")));
		font.drawString(405, 165, String.valueOf(character.getFertigkeiten().getWert("sich_verstecken")));
		font.drawString(405, 195, String.valueOf(character.getFertigkeiten().getWert("suchen")));
		font.drawString(405, 225, String.valueOf(character.getFertigkeiten().getWert("ueberreden")));
		font.drawString(405, 255, String.valueOf(character.getFertigkeiten().getWert("verbergen")));
		font.drawString(405, 285, String.valueOf(character.getFertigkeiten().getWert("wunden_heilen")));
		font.drawString(245, 381, String.valueOf(character.getFertigkeiten().getWert("bogen_und_armbrust")));
		font.drawString(245, 411, String.valueOf(character.getFertigkeiten().getWert("hiebwaffen")));
		font.drawString(245, 441, String.valueOf(character.getFertigkeiten().getWert("klingenwaffen")));
		font.drawString(245, 472, String.valueOf(character.getFertigkeiten().getWert("messer_und_dolche")));
		font.drawString(245, 503, String.valueOf(character.getFertigkeiten().getWert("waffenloser_kampf")));
	}
	
	public void drawVort(Graphics g) {
		g.drawImage(vortInfo,50,50);

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		int posY = 140;
		for (Vor_Nachteil elem: character.getVor_nachteile()) {
			font.drawString(80, posY, elem.getName());
			font.drawString(360, posY, "Stufe "+String.valueOf(elem.getStufe()));
			posY += 24;
		}
		
		posY = 385;
		for (Manoever elem: character.getManoever()) {
			font.drawString(80, posY, elem.getName());
			posY += 24;
		}
		
	}
}
