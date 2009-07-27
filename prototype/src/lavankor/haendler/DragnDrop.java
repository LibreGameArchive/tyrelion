package lavankor.haendler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class DragnDrop {

	// Liste mit den verfügbaren Feldern
	ArrayList<Raster> rasters = new ArrayList<Raster>();
	
	public DragnDrop(Raster raster) {
		// TODO Automatisch erstellter Konstruktoren-Stub
		rasters.add(raster);
	}
	
	public DragnDrop(Raster raster1, Raster raster2) {
		// TODO Automatisch erstellter Konstruktoren-Stub
		rasters.add(raster1);
		rasters.add(raster2);
	}
	
	public DragnDrop(ArrayList<Raster> raster_list) {
		// TODO Automatisch erstellter Konstruktoren-Stub
		rasters = raster_list;
	}

	/**Diese Methode löscht alle Einträge aus dem Raster*/
	public Image[][] clearRaster(Image[][] raster)
	{
		for(int i=0;i<raster.length;i++)
		{
			for(int j=0;j<raster[i].length;j++)
			{
				raster[i][j]=null;
			}
		}
		
		return raster;
	}
	
	/**Gibt ein best. Raster zurück*/
	public Raster getRaster(int idx)
	{
		return rasters.get(idx);
	}
	
	/**Zeichnet ein Raster auf der Ebene mit Hilfe von Rectangle
	 * @param x x-Koordinate für Raster-Ursprung
	 * @param y y-Koordinate für Raster-Ursprung*/
	public void drawRaster(Graphics g, Color col, Raster raster)
	{
		int pos_x = raster.getOffx();
		int pos_y = raster.getOffy();
		
		if(col!=null) 
		{
			g.setLineWidth(2);
			g.setColor(col);
		}
		else {
			g.setLineWidth(0.1F);
		}
		
		for(int i=0;i<raster.getRaster().length;i++)
		{
			for(int j=0;j<raster.getRaster()[i].length;j++)
			{
				g.drawRect(pos_x, pos_y, raster.getBox_width(), raster.getBox_height());
				//if(raster.getRaster()[i][j]!=null) g.fillRect(pos_x, pos_y, raster.getBox_width(), raster.getBox_height());
				pos_x += raster.getBox_width();
			}
			pos_x = raster.getOffx();
			pos_y += raster.getBox_height();
		}
	}
	
	/**Diese Methode findet einen leeren Platz für ein einzufügendes Image
	 * @param raster Das betreffende Raster
	 * @param width Breite des Images in Kästchen
	 * @param height Höhe des Images in Kästchen
	 * @return Point(box_x,box_y) Enthält die Position der Anfangsbox, an der das Raster eingefügt werden soll
	 */
	public Point findEmpties(Raster raster, int width, int height)
	{
		int box_x = -1;
		int akt_zeile = -1;
		int end;
		boolean erfolg_x = false;
		boolean erfolg_y = true;
		int start = 0;
		
		// Mitzähler, der Bescheid gibt, wenn Groesse erreicht
		int size_x;
		
		while(start<=raster.getRaster().length) {	
		
		erfolg_y = true;
			
		for(int i=start;i<raster.getRaster().length;i++)
		{
			if(erfolg_x) break;
			size_x = 0;
			box_x = -1;
			
			for(int j=0;j<raster.getRaster()[i].length;j++)
			{
				if(erfolg_x) break;
				
				// Kasten ist leer
				if(raster.getRaster()[i][j]==null)
				{
					if(!erfolg_x && box_x==-1) 
					{
						box_x = j;
					}
					size_x++;
				}
				
				// Falls genug Platz verfügbar
				if(size_x==width) {
					size_x = 0;
					akt_zeile = i;
					erfolg_x = true;
					break;
				}
				
				// Kasten nicht leer, dann weiter mit nächstem Kästchen
				if(raster.getRaster()[i][j]!=null) 
				{
					size_x = 0;
					box_x = -1;
					erfolg_x = false;
				}

			}
			
		}

		// Wenn zu diesem Zeitpunkt erfolg_x==True, dann ist in horizontaler Dimension Platz für das ItemImpl
		// Es muss jetzt nur noch geprüft werden, ob auch vertikal in der benötigten Höhe Platz ist
		// Ansonsten ist gar kein Platz in der Matrix
		
		end = akt_zeile + height - 1;
		
		if(end > (raster.getRaster().length-1))
		{
			erfolg_y = false;
		}
		else if(width+box_x > raster.getRaster()[0].length)
		{
			erfolg_y = false;
		}
		else {
			if(erfolg_x) 
			{
				for(int k=(akt_zeile+1);k<=end;k++)
				{
					for(int l=box_x;l<(width+box_x);l++)
					{
						if(raster.getRaster()[k][l]!=null) erfolg_y = false;
					}
				}
			}
		}

		
		if(erfolg_y && erfolg_x) break;
			start++; 
		}
		
		Point erg = null;		
		
		if (erfolg_y && erfolg_x)
		{
			erg = new Point(akt_zeile,box_x);
		}
		
		return erg;
	}
	
	/**Funktion prüft, ob der Platz, über dem ein ItemImpl gezogen wurde, frei ist
	 * und gibt eine Liste mit Koordinaten von den zu belegenden Kästchen zurück
	 * @param x Wenn x==-1 dann wird als Ausgangspunkt die 1. Box im Raster verwendet
	 * @param y Wenn y==-1 siehe x!
	 * */
	public ArrayList<Point> istFrei(Raster raster, ItemImpl ItemImpl, Rectangle rect, int x, int y)
	{
		// Liste der Felder, die überlappen
		ArrayList<Point> hits = new ArrayList<Point>();		
		int a,b;
		
		if(x==-1 && y==-1)
		{
			// Position der Startbox
			Point p = getBox(raster,rect);
			a = (int)p.getX();
			b = (int)p.getY();
		}
		else {
			a = x;
			b = y;
		}
		
		// Größe der Box in Kästchen
		Point m = getRasterSize(ItemImpl,raster);
		int d = (int)m.getX();
		int c = (int)m.getY();
		
		if((a+c)<=raster.getRaster().length && (b+d)<=raster.getRaster()[0].length)
		{
			for(int i=a;i<(a+c);i++)
			{
				for(int j=b;j<(b+d);j++)
				{
					if(raster.getRaster()[i][j]==null) hits.add(new Point(i,j));
				}
			}
		}
		
		return hits;
	}

	/**Gibt die Anzahl der Kästchen zurück, die ein ItemImpl benötigt*/
	public Point getRasterSize(ItemImpl ItemImpl, Raster raster)
	{
		double a,b;
		
		a = (double)ItemImpl.getWidth() / (double)raster.getBox_width();
		b = (double)ItemImpl.getHeight() / (double)raster.getBox_height();
		
		a = Math.ceil(a);
		b = Math.ceil(b);
		
		return new Point((int)a,(int)b);
	}
	
	/**Gibt die Anzahl der Kästchen zurück, die ein Bild benötigt*/
	public Point getRasterSize(Image img, Raster raster)
	{
		double a,b;
		
		a = (double)img.getWidth() / (double)raster.getBox_width();
		b = (double)img.getHeight() / (double)raster.getBox_height();
		
		a = Math.ceil(a);
		b = Math.ceil(b);
		
		return new Point((int)a,(int)b);
	}
	
	/**Gibt zu einem beliebigen Raster-Element die Koordinaten an*/
	public Point getBox(Raster raster, Rectangle ItemImpl)
	{
		Point erg = null;

		int box_x, box_y = -1;
		
		// Kästchen bestimmen, über dem sich die Maus befindet
		box_y = ((int)ItemImpl.getX() - raster.getOffx()) / raster.getBox_width();
		box_x = ((int)ItemImpl.getY() - raster.getOffy()) / raster.getBox_height();
		
		return erg = new Point(box_x,box_y);
	}
	
	/**Gibt die genauen Koordinaten zu einem bestimmten Kästchen an
	 * @param raster
	 * @param box_x
	 * @param box_y
	 * @return
	 */
	public Point getBox(Raster raster, int box_x, int box_y)
	{
		
		int kor_x, kor_y = -1;
		
		kor_x = (box_y) * raster.getBox_width();
		kor_y = (box_x) * raster.getBox_height();
		
		return new Point(kor_x + raster.getOffx(),kor_y + raster.getOffy());
	}
	
	public Raster addItem(ItemImpl itemimpl, Raster raster, ArrayList<Point> hits)
	{	
		// Raster als belegt markieren
		Point akt = null;
	
		Iterator<Point> it = hits.iterator();
		while(it.hasNext())
		{
			akt = (Point)it.next();
			raster.getRaster()[(int)akt.getX()][(int)akt.getY()] = itemimpl;
		}
		
		return raster;
	}
}
