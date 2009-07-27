package lavankor.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import lavankor.GameMaster;
import lavankor.avatar.Player;
import lavankor.character.Character;
import lavankor.haendler.DragnDrop;
import lavankor.haendler.ItemImpl;
import lavankor.haendler.Money;
import lavankor.haendler.Raster;
import lavankor.itemsystem.Item;
import lavankor.itemsystem.TestItem;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/** Erzeugt das Händlermenu in der GUI. 
 */
public class Haendler {
	
	/** Inventar und Händler-Bild */
	private Image bg_inventar;
	private Image bg_trader;
	
	/** Drag&Drop - Handler */
	private DragnDrop dnd;
	
	private ItemImpl selected;
	
	/** Option, ob das Händler-Menu gezeigt werden soll oder nicht.*/
	private boolean showHaendler = false;
	
	/** Werte für die DragnDrop-Steuerung */
	private boolean clicked,sorted=false;
	private int clicked_button = -1;
	private float last_x,last_y = -1;
	
	/** Zwischenspeicher für gerade angezeigte Fonts. */
	private AngelCodeFont font;
	
	/** Übergebener Charakter zum auslesen der Charakterdaten. */
	private Character character;
	
	/** Raster für Inventar und Händler*/
	private Raster raster_inventar,raster_haendler;
	
	/** Bestand des Haendlers */
	private ArrayList<Item> haendler_bestand = new ArrayList<Item>();
	
	private String type;
	
	
	public Haendler(Player player, String type) throws SlickException {
		this.character = player.getCharacter();
		this.type = type;
		
		bg_inventar = new Image("res/images/menu/menu_inventar_bg.jpg");
		bg_trader = new Image("res/images/menu/menu_haendler_bg.jpg");
		
		// Objekt der DragnDrop Klasse anmelden
		dnd = GameMaster.getInstance().getDnd();
		
		raster_haendler = dnd.getRaster(1);
		raster_inventar = dnd.getRaster(0);
		
		// Zu Versuchszwecken ein paar Items zum Händler legen
		/*haendler_bestand.add(new ItemImpl("res/images/items/weapons/bogen.bmp",100,400, new Money(10,12,77,111), raster_haendler, "Ein schoener Bogen, fehlt nur noch der Pfeil"));
		haendler_bestand.add(new ItemImpl("res/images/items/weapons/sichel.bmp",130,400, new Money(7,9,43,87), raster_haendler, "Eine Sichel, aber nicht zum Rasenmähen"));
		haendler_bestand.add(new ItemImpl("res/images/items/weapons/dolch.bmp",160,400, new Money(3,7,27,34), raster_haendler, "Ein Dolch in Ehren, kann niemand verwehren"));
		haendler_bestand.add(new ItemImpl("res/images/items/flasche.bmp",190,400, new Money(9,15,88,41), raster_haendler, "Das obligatorische Konterbier"));*/
		
		TestItem bogen_item = new TestItem(666,"Bogen",2,4,"Das ist ein Bogen",GameMaster.placeImage("res/images/items/weapons/bogen.bmp"),GameMaster.placeImage("res/images/items/weapons/dolch_icon.bmp"),100,400, new Money(4,7,8,3), raster_haendler);
		TestItem dolch_item = new TestItem(667,"Dolch",2,4,"Handwaffe Dolch",GameMaster.placeImage("res/images/items/weapons/dolch.bmp"),GameMaster.placeImage("res/images/items/weapons/dolch_icon.bmp"),100,400, new Money(2,1,5,4), raster_haendler);
		TestItem sichel_item = new TestItem(668,"Sichel",2,4,"Sichel des Todes",GameMaster.placeImage("res/images/items/weapons/sichel.bmp"),GameMaster.placeImage("res/images/items/weapons/dolch_icon.bmp"),100,400, new Money(5,4,6,2), raster_haendler);
		
		haendler_bestand.add(bogen_item);
		haendler_bestand.add(dolch_item);
		haendler_bestand.add(sichel_item);
		//haendler_bestand.add(new ItemImpl(null,160,400, new Money(3,7,27,34), raster_haendler));
		//haendler_bestand.add(new ItemImpl(null,190,400, new Money(9,15,88,41), raster_haendler));
	
	}

	/** Methode zur Abfrage ob angezeigt wird.
	 * @return true = CharInfo wird angezeigt; false = wird nicht angezeigt
	 */
	public boolean isShowDia() {
		return showHaendler;
	}

	/** Ändern der Anzeigeoption ob angezeigt wird.
	 * @param showCharinfo true = anzeigen; false = nicht anzeigen
	 */
	public void setShowDia(boolean showHaendlerinfo) {
		this.showHaendler = showHaendlerinfo;
	}
	
	/**Methode zum Zeichnen eines Item-Inhalts*/
	public void drawItems(Graphics g, ArrayList<Item> liste)
	{
		// Zeichnet alle Items
		Iterator<Item> it = liste.iterator();
		ItemImpl cur = null;
		
		while(it.hasNext())
		{
			cur = it.next().getItem_Impl();
			g.drawImage(cur.getImage(), cur.getX(), cur.getY());
			// Und in Händler-Menu einordnen
			if(!sorted)
			{
				selected = cur;
				if(type.equals("haendler"))
				{
					autoMove(cur,1);
				}
				else {
					autoMove(cur,0);
				}
			}
			cur = null;
		}
		if(!sorted) selected = null;
		sorted = true;
	}
	
	/** Methode zum anzeigen des Inventars
	 * @param g Graphics in denen angezeigt wird.
	 * @param aFont zu nutzende Schriftart
	 */
	public void draw(Graphics g) {
		// Zeichnen der Hintergrundbilder
		g.drawImage(bg_inventar,50,50);
		if(type.equals("haendler")) g.drawImage(bg_trader,bg_inventar.getWidth()+50,50);
		 
		// Zeichnet das Raster auf den Hintergrund
		dnd.drawRaster(g,null,dnd.getRaster(0));
		if(type.equals("haendler")) dnd.drawRaster(g,null,dnd.getRaster(1));
		
		if(type.equals("haendler")) drawItems(g,haendler_bestand);
		
		drawItems(g,GameMaster.getInstance().getDnd().getRaster(0).getItems());

		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_32_BLACK);
		// Geldbestand ausgeben
		font.drawString(80, 480, String.valueOf(character.getMoney().getDukaten()), Color.black);
		font.drawString(160, 480, String.valueOf(character.getMoney().getSilbertaler()), Color.black);
		font.drawString(222, 480, String.valueOf(character.getMoney().getHeller()), Color.black);
		font.drawString(270, 480, String.valueOf(character.getMoney().getKreuzer()), Color.black);
		
		//g.drawString("Ausgewählt: " + String.valueOf(character.getInventory().getInv().size()), 20, 200);
		//g.drawString("Koord x: " + r + "y: " + t, 10, 260);
		//g.drawString("Anzahl Inventar: " + character.getInventory().getInv().size(), 10, 280);
		
		if(selected!=null && type.equals("haendler"))
		{
			//g.drawString("Kosten des Gegenstands: " + String.valueOf(selected.getMoney().getTotalAmount()), 20, 220);
			//g.drawString("Bezahlbar? " + character.getMoney().bezahlbar(selected.getMoney()),20,240);
			
			// Zeichnet die Beschreibung in der Händlerinformation
			font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
			font.drawString(470, 440, selected.getDesc(), Color.black);
			font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_18_BLACK);
			font.drawString(470, 470, "Kosten: " + selected.getMoney().toString(), Color.black);
		}
	}
	
	/**Gibt das Bild zurück, welches angeklickt wurde */
	public ItemImpl getItem(int x, int y)
	{
		Iterator<Item> it = haendler_bestand.iterator();
		ItemImpl erg = null;
		ItemImpl cur = null;
		Rectangle akt = null;
		
		while(it.hasNext())
		{
			cur = it.next().getItem_Impl();
			akt = cur.getRectangle();
			
			// Prüfen, ob eines der Bilder angeklickt wurde
			if(akt.contains(x, y)) 
			{
				erg = cur;
			}
		}
		
		Iterator<Item> it2 = character.getInventory().getInv().iterator();
		
		while(it2.hasNext())
		{
			cur = it2.next().getItem_Impl();
			akt = cur.getRectangle();
			
			// Prüfen, ob eines der Bilder angeklickt wurde
			if(akt.contains(x, y)) 
			{
				erg = cur;
			}
		}
		
		return erg;
	}
	
	/**Prüft, ob sich ein angeklicktes Bild über dem Raster befindet
	 * @param x1 x-Koordinate der linken, oberen Ecke
	 * @param x2 x-Koordinate der rechten, unteren Ecke
	 * @param y1 y-Koordinate der linken, oberen Ecke
	 * @param y2 y-Koordinate der rechten, unteren Ecke*/
	public boolean overlaps(Raster raster, float x1, float x2, float y1, float y2)
	{
		if(clicked)
		{
			return (((x1 >= raster.getOffx() && x1 <= (raster.getBox_width() * raster.getRaster()[0].length + raster.getOffx())) && (x2 >= raster.getOffx() && x2 <= (raster.getBox_width() * raster.getRaster()[0].length + raster.getOffx()))) && ((y1 >= raster.getOffy() && y1 <= (raster.getBox_width() * raster.getRaster().length + raster.getOffy())) && (y2 >= raster.getOffx() && y2 <= (raster.getBox_width() * raster.getRaster().length + raster.getOffy()))));
			
		}
		else
		{
			return false;
		}
	}
	
	/**Einfaches Bewegen eines Items (ohne dabei Felder zu markieren)*/
	public void moveItemSlim(float x, float y, ItemImpl item)
	{
		item.setPos(x,y);
		//g.drawImage(ItemImpl.getImage(), x, y);
	}
	
	/**Bewegt ein ItemImpl an einen Ort
	 * @param x Position der Box in x-Richtung
	 * @param y Position der Box in y-Richtung
	 * @param ItemImpl Das Image-ItemImpl, welches verschoben werden soll
	 * @param raster Das dazugehörige Raster
	 * @param hits Eine Liste mit den Feldern, die von dem ItemImpl belegt werden
	 * */
	public Raster moveItem(float x, float y, ItemImpl ItemImpl, Raster raster, ArrayList<Point> hits)
	{
		
		float n,m;
		m = raster.getOffx() + y * raster.getBox_width();
		n = raster.getOffy() + x * raster.getBox_height();

		moveItemSlim(m,n,selected);
		
		if(selected!=null)
		{
		
			// Alte Position löschen
			raster = delItem(raster,selected);
		
			// Raster als belegt markieren
			Point akt = null;
		
			Iterator<Point> it = hits.iterator();
			while(it.hasNext())
			{
				akt = (Point)it.next();
				raster.getRaster()[(int)akt.getX()][(int)akt.getY()] = selected;
			}
		}
		
		return raster;
	}
	
	/**Löscht ein ItemImpl aus dem Raster*/
	public Raster delItem(Raster raster, ItemImpl itemimpl)
	{
		// Alte Position des Items löschen
		for(int i=0;i<raster.getRaster().length;i++)
		{
			for(int j=0;j<raster.getRaster()[0].length;j++)
			{
				if(raster.getRaster()[i][j]==itemimpl) raster.getRaster()[i][j] = null;
			}
		}
		
		return raster;
	}
	
	public void mouseWasMoved(Graphics g, int oldx, int oldy, int newx, int newy) {    
		
		// Wenn ein ItemImpl mit der linken Maustaste über das Raster gezogen wurde, dann prüfen, ob dort frei ist
		if(selected!=null && clicked && clicked_button==Input.MOUSE_LEFT_BUTTON) {
    		// Das Image an die neue Position ziehen
    		g.drawImage(selected.getImage(), newx, newy);
    			
    		// Rechteck verschieben
    		selected.setPos(selected.getX() +newx-oldx,selected.getY() +newy-oldy);

    		// Position aus Raster löschen, wenn ItemImpl Raster verlässt
    		if(!overlaps(dnd.getRaster(0),selected.getX(),selected.getX()+selected.getWidth(),selected.getY(),selected.getY() + selected.getHeight()))
    		{
    			delItem(dnd.getRaster(0),selected);
    		}
    	}
    }
	
	public void mouseWasPressed(int button, int x, int y) {
		
		// Bild auswählen, auf das geklickt wurde
		selected = getItem(x,y);
		clicked = true;
		clicked_button = button;
		
		
		// Alte Mausposition speichern
		last_x = x;
		last_y = y;
		
		// Bei Rechtsklick das ItemImpl auf seine Position ziehen lassen
		if(button==Input.MOUSE_RIGHT_BUTTON)
		{
			if(selected!=null && clicked_button==Input.MOUSE_RIGHT_BUTTON)
	    	{
	    		if(clicked) {

	    			autoMove(selected,1);
	    			// Geld hinzubuchen
	    			if(selected.getParent_Raster()==raster_inventar) character.getMoney().add(selected.getMoney());
	    			
	    			// Übergeordneten Frame speichern
	    			selected.setParent_Raster(raster_haendler);
	    			
	    			// ItemImpl aus dem Inventar-Menu löschen
	    			raster_inventar = delItem(raster_inventar,selected);
	    			
	    			// speichert das aktuelle Inventar
        			saveInventar(selected,1);
	    		}  
	    	}
		}
	}
	
	/** Methode, um einen Gegenstand (ItemImpl) automatisch einen Platz in einem Raster (rasternr)  zuweisen
	 * @param sel
	 * @param rasternr
	 */
	public void autoMove(ItemImpl sel, int rasternr)
	{
		Point kaesten = dnd.getRasterSize(sel,dnd.getRaster(rasternr));

		Point p = dnd.findEmpties(dnd.getRaster(rasternr),(int)kaesten.getX(),(int)kaesten.getY());

		ArrayList<Point> frei  = dnd.istFrei(dnd.getRaster(rasternr), sel, sel.getRectangle(),(int)p.getX(),(int)p.getY());

		// Falls Platz für das ItemImpl vorhanden ist, dann dort einfügen
		if(p!=null)
		{
			Raster raster = dnd.getRaster(rasternr);
			raster = moveItem((float)p.getX(),(float)p.getY(),sel,raster,frei);	        			
		}
	}
	
    public void mouseWasReleased(int button, int x, int y) {
        if(button == Input.MOUSE_LEFT_BUTTON) {

        	// Wenn Bild über Raster losgelassen wurde, dann in Position bringen
        	if(selected!=null)
        	{        		
        		
        		// Prüfen, ob im korrekten Bereich
        		if(overlaps(raster_inventar,selected.getX(),selected.getX()+selected.getWidth(),selected.getY(),selected.getY()+selected.getHeight()) || overlaps(raster_haendler,selected.getX(),selected.getX()+selected.getWidth(),selected.getY(),selected.getY()+selected.getHeight()))
        		{

        		// Anzahl der Kästchen berechnen, die das Bild breit und hoch ist
        		Point kaesten = dnd.getRasterSize(selected, dnd.getRaster(0));
        		ArrayList<Point> frei  = dnd.istFrei(dnd.getRaster(0), selected, selected.getRectangle(),-1,-1); 
        		
        		Raster raster = dnd.getRaster(0);
        		
        		// Prüfen, ob alles frei
        		if(frei.size()==(int)kaesten.getX() * (int)kaesten.getY())
        		{
        			// Geld abbuchen und ItemImpl in Inventar ziehen, wenn genug Geld auf Konto
        			if(selected.getParent_Raster()==raster_haendler && character.getMoney().bezahlbar(selected.getMoney())) 
        			{
        				character.getMoney().sub(selected.getMoney());
        				
        				// ItemImpl an die gewünschte Position ziehen
            			raster = moveItem((float)frei.get(0).getX(),(float)frei.get(0).getY(),selected,raster,frei);
            			
            			// Übergeordnetes Raster speichern
            			selected.setParent_Raster(raster_inventar);
            			
            			// ItemImpl aus dem Händler-Menu löschen
            			raster_haendler = delItem(raster_haendler,selected);      
            			
            			// speichert das aktuelle Inventar
            			saveInventar(selected,0);
        			}
        			//Falls das ItemImpl nur innerhalb des Inventars bewegt wird
        			else if(selected.getParent_Raster()==raster_inventar)
        			{
        				// ItemImpl an die gewünschte Position ziehen
            			raster = moveItem((float)frei.get(0).getX(),(float)frei.get(0).getY(),selected,raster,frei);
            			
            			// speichert das aktuelle Inventar
            			saveInventar(selected,0);
        			}
        			// Andernfalls an die alte Position zurückschieben
        			else {
        				moveItemSlim(last_x,last_y,selected);
        			}
        			
        			
        		}
        		// Falls nichts frei ist, dann das ItemImpl an die alte Position ziehen, falls es sich über dem Raster befindet
        		else {
        			if(overlaps(raster,selected.getX(),selected.getX()+selected.getWidth(),selected.getY(),selected.getY()+selected.getHeight())) moveItemSlim(last_x,last_y,selected);
        		}
        		
        		}
        		else {
        			moveItemSlim(last_x,last_y,selected);
        		}
        	}
        	
        	selected = null;
        	clicked = false;
        	clicked_button = -1;
        }
    }
    
    public void saveInventar(ItemImpl item, int raster)
    {
    	// Das aktuelle Inventar beim Character speichern
		//character.getInventory().setDnd(dnd);
		if(raster==0) 
		{
			character.getInventory().addItem(item,true);
			haendler_bestand.remove(item.getItem());
		}
		else {
			haendler_bestand.add(item.getItem());
			character.getInventory().remItem(item.getItem());
		}
    }
}
