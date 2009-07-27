/*package lavankor.haendler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import lavankor.prototyp.Main_Short;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuTrader extends BasicGameState {

	private final static int ID = 6;
	
	private Main_Short mainGame;
	private GameContainer gameContainer;
	
	private Image hintergrund;
	private Image inventar;
	
	private ItemImpl item1;
	private ItemImpl item2;
	private ItemImpl item3;
	private ItemImpl item4;
	
	//Raster in dem die Items liegen
	private Graphics g = null;
	private Raster raster1,raster2;
	
	private boolean clicked = false;
	private ItemImpl selected = null;
	private boolean overlaps = false;
	private boolean treffer = false;
	
	private int clicked_button = -1;
	private float last_x,last_y=-1;
	
	private DragnDrop dnd;
	
	//Liste mit den Bildern
    ArrayList<ItemImpl> items = new ArrayList<ItemImpl>();
	
	public MenuTrader() {
		// TODO Automatisch erstellter Konstruktoren-Stub
		super();

		item1 = new ItemImpl("res/images/items/bogen.jpg",100,400);
		item2 = new ItemImpl("res/images/items/sichel.jpg",130,400);
		item3 = new ItemImpl("res/images/items/weapons/dolch.jpg",160,400);
		item4 = new ItemImpl("res/images/items/flasche.jpg",190,400);
		
		// Map mit Rectangles und Images füllen
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		
		
	}

	@Override
	public int getID() {
		// TODO Automatisch erstellter Methoden-Stub
		return ID;
	}

	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		// TODO Automatisch erstellter Methoden-Stub
		mainGame = (Main_Short)game;
		gameContainer = gc;
		g = gc.getGraphics();

		// Grafiken belegen
		hintergrund = new Image("res/images/menu/menu_haendler_bg.jpg");
		inventar = new Image("res/images/menu/menu_inventar_bg.jpg");
		
		// Die Raster anlegen
		raster1 = new Raster(12,10,14,15,30,30);
		raster2 = new Raster(10,12,inventar.getWidth() + 14,15,30,30);
		
		// Objekt der DragnDrop Klasse anmelden
		dnd = new DragnDrop(raster1,raster2);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		
		// Inventar des Spielers zeichnen
		g.drawImage(inventar, 0, 0);
		
		// TODO Automatisch erstellter Methoden-Stub
		g.drawImage(hintergrund, inventar.getWidth(), 0);
		
		// Zeichnet das Raster auf den Hintergrund
		dnd.drawRaster(g,null,dnd.getRaster(0));
		dnd.drawRaster(g,null,dnd.getRaster(1));
		
		// Zeichnet ein Bild
		g.drawImage(item1.getImage(), item1.getX(), item1.getY());
		g.drawImage(item2.getImage(), item2.getX(), item2.getY());
		g.drawImage(item3.getImage(), item3.getX(), item3.getY());
		g.drawImage(item4.getImage(), item4.getX(), item4.getY());
	}

	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Automatisch erstellter Methoden-Stub

	}
	
	public void keyPressed(int key, char c) {

		// Bei Klick auf Escape ganzes Spiel verlassen
		if(Input.KEY_ESCAPE==key)
		{
			gameContainer.exit();
		}
		
	}

	public ItemImpl getItem(int x, int y)
	{
		Iterator<ItemImpl> it = items.iterator();
		ItemImpl erg = null;
		int z = 0;
		
		while(it.hasNext())
		{
			ItemImpl ItemImpl = it.next();
			Rectangle akt = ItemImpl.getRectangle();
			
			// Prüfen, ob eines der Bilder angeklickt wurde
			if(akt.contains(x, y)) 
			{
				erg = ItemImpl;
			}
		}
		
		return erg;
	}

	public void overlaps(Raster raster, float x1, float x2, float y1, float y2)
	{
		if(clicked)
		{
			overlaps = (((x1 >= raster.getOffx() && x1 <= (raster.getBox_width() * raster.getRaster()[0].length + raster.getOffx())) || (x2 >= raster.getOffx() && x2 <= (raster.getBox_width() * raster.getRaster()[0].length + raster.getOffx()))) && ((y1 >= raster.getOffy() && y1 <= (raster.getBox_width() * raster.getRaster().length + raster.getOffy())) || (y2 >= raster.getOffx() && y2 <= (raster.getBox_width() * raster.getRaster().length + raster.getOffy()))));
			
		}
	}

	public void moveItemSlim(float x, float y, ItemImpl ItemImpl)
	{
		g.drawImage(ItemImpl.getImage(), x, y);
		ItemImpl.setPos(x, y);
	}

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

	public Raster delItem(Raster raster, ItemImpl ItemImpl)
	{
		// Alte Position des Items löschen
		for(int i=0;i<raster.getRaster().length;i++)
			{
			for(int j=0;j<raster.getRaster()[0].length;j++)
			{
				if(raster.getRaster()[i][j]==ItemImpl) raster.getRaster()[i][j] = null;
			}
		}
		
		return raster;
	}
	
	@Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {    
		
		// Wenn ein ItemImpl mit der linken Maustaste über das Raster gezogen wurde, dann prüfen, ob dort frei ist
		if(selected!=null && clicked && clicked_button==Input.MOUSE_LEFT_BUTTON) {
    		// Das Image an die neue Position ziehen
    		g.drawImage(selected.getImage(), newx, newy);
    			
    		// Rechteck verschieben
    		selected.setPos(selected.getX() +newx-oldx,selected.getY() +newy-oldy);
    			
    		overlaps(dnd.getRaster(0),selected.getX(),selected.getX()+selected.getWidth(),selected.getY(),selected.getY() + selected.getHeight()); 
    		
    		// Position aus Raster löschen, wenn ItemImpl Raster verlässt
    		if(!overlaps)
    		{
    			delItem(dnd.getRaster(0),selected);
    		}
    	}
    }
	
	public void mousePressed(int button, int x, int y) {
		
		// Bild auswählen, auf das geklickt wurde
		selected = getItem(x,y);
		clicked = true;
		clicked_button = button;
		Point kaesten = null;
		
		// Alte Mausposition speichern
		last_x = x;
		last_y = y;
		
		// Bei Rechtsklick das ItemImpl auf seine Position ziehen lassen
		if(button==Input.MOUSE_RIGHT_BUTTON)
		{
			if(selected!=null && clicked_button==Input.MOUSE_RIGHT_BUTTON)
	    	{
	    		if(clicked) {
	    			kaesten = dnd.getRasterSize(selected,dnd.getRaster(1));

	        		Point p = dnd.findEmpties(dnd.getRaster(1),(int)kaesten.getX(),(int)kaesten.getY());

	        		ArrayList<Point> frei  = dnd.istFrei(dnd.getRaster(1), selected, selected.getRectangle(),(int)p.getX(),(int)p.getY());

	        		// Falls Platz für das ItemImpl vorhanden ist, dann dort einfügen
	        		if(p!=null)
	        		{
	        			Raster raster = dnd.getRaster(1);
	        			raster = moveItem((float)p.getX(),(float)p.getY(),selected,raster,frei);	        			
	        		}
	        		
	        		
	    		}  
	    	}
		}
	}
	
	@Override
    public void mouseReleased(int button, int x, int y) {
        if(button == Input.MOUSE_LEFT_BUTTON) {

        	// Wenn Bild über Raster losgelassen wurde, dann in Position bringen
        	if(selected!=null)
        	{        		
        		// Anzahl der Kästchen berechnen, die das Bild breit und hoch ist
        		Point kaesten = dnd.getRasterSize(selected, dnd.getRaster(0));
        		ArrayList<Point> frei  = dnd.istFrei(dnd.getRaster(0), selected, selected.getRectangle(),-1,-1); 
        		
        		Raster raster = dnd.getRaster(0);
        		
        		// Prüfen, ob alles frei
        		if(frei.size()==(int)kaesten.getX() * (int)kaesten.getY())
        		{
        			// ItemImpl an die gewünschte Position ziehen
        			raster = moveItem((float)frei.get(0).getX(),(float)frei.get(0).getY(),selected,raster,frei);
        		}
        		// Falls nichts frei ist, dann das ItemImpl an die alte Position ziehen, falls es sich über dem Raster befindet
        		else {
        			overlaps(raster,selected.getX(),selected.getX()+selected.getWidth(),selected.getY(),selected.getY()+selected.getHeight());
        			if(overlaps) moveItemSlim(last_x,last_y,selected);
        		}
        	}
        	
        	selected = null;
        	clicked = false;
        	clicked_button = -1;
        }
    }
}*/
