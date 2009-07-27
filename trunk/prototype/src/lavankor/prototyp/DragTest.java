package lavankor.prototyp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class DragTest extends BasicGame {

	public DragTest() {
        super("DragTest");
    }
   
    Rectangle box1 = new Rectangle(100, 100, 50, 50);
    Rectangle box2 = new Rectangle(100, 100, 60, 60);
    
    // Zielquadrat z
    Rectangle z1 = new Rectangle(400,200, 100, 100);
    Rectangle z2 = new Rectangle(50,300, 100, 250);
    
    /**Map mit den zu verschiebenden Elementen*/
    HashMap<Rectangle,Rectangle> boxes = new HashMap<Rectangle,Rectangle>();

    /**Liste mit den Ablageplätzen*/
    ArrayList<Rectangle> places = new ArrayList<Rectangle>();

    Rectangle selected = null;
    boolean clicked = false;
    boolean treffer = false;
    float edges[][] = new float[4][2];
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Automatisch erstellter Methoden-Stub
		gc.getGraphics().setBackground(Color.white);
		
		// Rechtecke in die Box stecken
		boxes.put(box1, z1);
		boxes.put(box2, z2);
		places.add(z1);
		places.add(z2);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Automatisch erstellter Methoden-Stub
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Automatisch erstellter Methoden-Stub
        g.setColor(Color.black);
        g.setLineWidth(2);
        g.draw(z1);
        g.draw(z2);
        
        // Boxen zeichnen
        Set<Rectangle> box_set = boxes.keySet();
        Iterator it = box_set.iterator();
        
        while(it.hasNext())
        {
        	g.draw((Rectangle)it.next());
        }
        
		savePos(g);
		treffer = overlaps(g);
		
		if(treffer) g.setColor(Color.green);
    	g.drawString(String.valueOf("Treffer: " + treffer), 10, 500);
	}
	
	@Override
    public void mousePressed(int button, int x, int y) {

        if(button == Input.MOUSE_LEFT_BUTTON) {
        	// Prüfen, ob einer der Kästchen angeklickt wurde

        	// Box auswählen, die gerade angeklickt wird
            Set<Rectangle> box_set = boxes.keySet();
            Iterator it = box_set.iterator();
            
            while(it.hasNext())
            {
            	Rectangle box = (Rectangle)it.next();
            	if(box.contains(x, y)) {
            		selected = box;
            		break;
            	}
            }
            
            clicked = true;
        }
    }
   
    @Override
    /**Sobald das kleine Kästchen sich noch im großen befindet, dann wird beim Loslassen
     * der Maustaste das Kästchen automatisch in die Mitte des großen Kästchens gezogen
     */
    public void mouseReleased(int button, int x, int y) {
        if(button == Input.MOUSE_LEFT_BUTTON) {
        	
        	if(treffer && selected!=null)
        	{
        		// Jetzt Kästchen in die Mitte ziehen
        		
        		// "Verantwortlichen" großen Kasten suchen
        		Rectangle master = boxes.get(selected);
        		
        		if(master!=null)
        		{
        			// Korrekte Position berechnen
        			float o = (master.getWidth() - selected.getWidth()) / 2; 
        			float a = (master.getHeight() - selected.getHeight()) / 2;
        		
        			if(treffer)
        			{
        				// kleines Kästchen in den großen Kasten ziehen
        				selected.setX(master.getX() + o);
        				selected.setY(master.getY() + a);
        			}
        		}
        	}
        	
            selected = null;
            treffer = false;
        }
    }
    
    /**Prüft, ob das kleine Kästchen im großen Kasten steht
     * und färbt anschließend das kleine Kästchen grün */
    public boolean overlaps(Graphics g)
    {
    	boolean treffer = false;
    	// Übergeordneter Kasten
		Rectangle sup = boxes.get(selected);
    	
    	for(int i=0;i<edges.length;i++)
    	{
    		Iterator<Rectangle> it = places.iterator();
    		Rectangle master = null;

    		
    		while(it.hasNext())
    		{
    			master = (Rectangle)it.next();
    			
    			if(master.contains(edges[i][0], edges[i][1]) && sup==master)
    			{
    				treffer = true;
    				break;
    			}
    		}
    	}
    	
    	//treffer = z.contains(edges[0][0], edges[0][1]);

    	
    	return treffer;
    }
   
    /**Speichert die vier Koordinaten des kleines Quadrats */
    public void savePos(Graphics g)
    {
    	if(selected!=null)
    	{
    	
    		// Alle Ecken des Quadrats speichern
    		// linke obere ecke
    		edges[0][0] = selected.getX();
    		edges[0][1] = selected.getY();
		
    		// rechte obere ecke
    		edges[1][0] = selected.getX() + selected.getWidth();
    		edges[1][1] = selected.getY();
		
    		// rechte untere ecke
    		edges[2][0] = selected.getX() + selected.getWidth();
    		edges[2][1] = selected.getY() + selected.getHeight();
		
    		// linke untere ecke
    		edges[3][0] = selected.getX();
    		edges[3][1] = selected.getY() + selected.getHeight();
		
    		g.drawString("(" + String.valueOf(edges[0][0]) + "," + String.valueOf(edges[0][1]) + ")", 500, 0);
    		g.drawString("(" + String.valueOf(edges[1][0]) + "," + String.valueOf(edges[1][1]) + ")", 500, 20);
    		g.drawString("(" + String.valueOf(edges[2][0]) + "," + String.valueOf(edges[2][1]) + ")", 500, 40);
    		g.drawString("(" + String.valueOf(edges[3][0]) + "," + String.valueOf(edges[3][1]) + ")", 500, 60);
    	}
    }
    
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {    
    	
    	if(selected!=null)
    	{
    		if(clicked) {
    			selected.setX(selected.getX()+newx-oldx);
    			selected.setY(selected.getY()+newy-oldy);
    		}  
    	}
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new DragTest());
            container.setDisplayMode(800,600,false);
            container.setShowFPS(true);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
