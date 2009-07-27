package lavankor.gui;

import java.util.Iterator;

import lavankor.avatar.Player;
import lavankor.character.Character;
import lavankor.haendler.DragnDrop;
import lavankor.haendler.ItemImpl;
import lavankor.haendler.Raster;
import lavankor.itemsystem.Item;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Diese Klasse implementiert die Anzeige des Inventars auf verschiedenen Seiten. 
 */
public class Inventar {
	
	/** Hintergrund-Bild */
	private Image bg_image;
	
	/** Option, ob die Charakterinformation gerade angezeigt werden soll.*/
	private boolean showInventar = false;
	
	/** Zwischenspeicher für gerade angezeigte Fonts. */
	private AngelCodeFont font;
	
	/** Übergebener Charakter zum auslesen der Charakterdaten. */
	private Character character;

	/**Inventar-Raster*/
	private Raster raster_inventar;
	
	/**DragnDrop-Handle*/
	private DragnDrop dnd;
	
	
	public Inventar(GameContainer container, Player player) throws SlickException {
		character = player.getCharacter();

		bg_image = new Image("res/images/menu/menu_inventar_bg.jpg");
		
		// Die Raster anlegen
		raster_inventar = new Raster(12,10,50+14,50+15,30,30);
		
		// Objekt der DragnDrop Klasse anmelden
		/*if(character.getInventory().getDnd()!=null)
		{
			dnd = character.getInventory().getDnd();
		}
		else {
			dnd = new DragnDrop(raster_inventar);
		}*/
	}

	/** Methode zur Abfrage ob angezeigt wird.
	 * @return true = CharInfo wird angezeigt; false = wird nicht angezeigt
	 */
	public boolean isShowDia() {
		return showInventar;
	}

	/** Ändern der Anzesigeoption ob angezeigt wird.
	 * @param showCharinfo true = anzeigen; false = nicht anzeigen
	 */
	public void setShowDia(boolean showCharinfo) {
		this.showInventar = showCharinfo;
	}
	
	
	/** Methode zum anzeigen des Inventars
	 * @param g Graphics in denen angezeigt wird.
	 * @param aFont zu nutzende Schriftart
	 */
	public void draw(Graphics g) {
		// Zeichnen der Hintergrundbilder
		g.drawImage(bg_image,50,50);
		 
		// Zeichnet das Raster auf den Hintergrund
		dnd.drawRaster(g,null,dnd.getRaster(0));
		
		// Zeichnet alle Items
		Iterator<Item> it = character.getInventory().getInv().iterator();
		ItemImpl cur = null;
		int l = 0;
		
		while(it.hasNext())
		{
			cur = it.next().getItem_Impl();
			g.drawString("X: " + cur.getX() + ", Y: " + cur.getY(), 20, 200 + l);
			g.drawImage(cur.getImage(), cur.getX(), cur.getY());
			l += 20;
		}

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_32_BLACK);
		// Geldbestand ausgeben
		font.drawString(80, 480, String.valueOf(character.getMoney().getDukaten()), Color.black);
		font.drawString(160, 480, String.valueOf(character.getMoney().getSilbertaler()), Color.black);
		font.drawString(222, 480, String.valueOf(character.getMoney().getHeller()), Color.black);
		font.drawString(270, 480, String.valueOf(character.getMoney().getKreuzer()), Color.black);
	}
	
	
}
