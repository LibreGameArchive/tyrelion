package lavankor.haendler;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ItemOLD {
	
	/**Das Bild zu einem Item*/
	private Image img;
	
	/**Die Fläche zu einem Item*/
	private Rectangle rect;
	
	/**Pfad des Bildes*/
	private String path;
	
	/** Wert des Items */
	private Money money = new Money();
	
	/** Raster zu dem das Item gehört */
	private Raster parent_raster;
	
	/**Beschreibung des Items*/
	private String desc;
	
	public ItemOLD(String path, float x, float y, Money money, Raster parent_raster, String desc) {
		// TODO Automatisch erstellter Konstruktoren-Stub
		
		// Bild belegen
		try
		{
			this.path = path;
			img = new Image(path, new Color(255,0,204));
		}
		catch(SlickException sex) {}
		
		// Fläche belegen
		rect = new Rectangle(x, y, img.getWidth(), img.getHeight());
		this.money = money;
		this.parent_raster = parent_raster;
		
		// Beschreibung speichern
		this.desc = desc;
	}
	
	/**Gibt die Breite eines Items zurück*/
	public float getWidth()
	{
		return rect.getWidth();
	}

	/**Gibt die Breite eines Items zurück*/
	public float getHeight()
	{
		return rect.getHeight();
	}
	
	/**Gibt die X-Position eines Items zurück*/
	public float getX()
	{
		return rect.getX();
	}
	
	public Raster getParent_Raster()
	{
		return parent_raster;
	}
	
	/**Getter für die Beschreibung des Items*/
	public String getDesc()
	{
		return desc;
	}
	
	public void setParent_Raster(Raster parent_raster)
	{
		this.parent_raster = parent_raster;
	}
	
	/**Gibt den Geldwert eines Items zurück*/
	public Money getMoney()
	{
		return money;
	}
	
	/**Gibt die Y-Position eines Items zurück*/
	public float getY()
	{
		return rect.getY();
	}
	
	/**Gibt das Bild von einem Item zurück*/
	public Image getImage()
	{
		return img;
	}
	
	/**Gibt die Fläche von einem Item zurück*/
	public Rectangle getRectangle()
	{
		return rect;
	}
	
	/**Setzt ein neues Bild*/
	public void setImg(Image im)
	{
		img = im;
	}
	
	/**Setzt die X-Position eines Items*/
	public void setX(float x)
	{
		rect.setX(x);
	}
	
	/**Setzt die Y-Position eines Items*/
	public void setY(float y)
	{
		rect.setY(y);
	}
	
	/**Setzt die X-Position eines Items*/
	public void setPos(float x, float y)
	{
		setX(x);
		setY(y);
	}
	
	/**Gibt den Pfad eines Bildes zurück*/
	public String getPath()
	{
		return path;
	}
}
