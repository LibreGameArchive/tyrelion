package lavankor.haendler;

import java.awt.Point;

import lavankor.itemsystem.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ItemImpl {
	
	/**Die Fläche zu einem Item*/
	private Rectangle rect;
	
	/** Wert des Items */
	private Money money = new Money();
	
	/** Raster zu dem das Item gehört */
	private Raster parent_raster;
	
	/**Das dazugehörige HauptItem*/
	private Item item;
	
	public ItemImpl(Item item, float x, float y, Money money, Raster parent_raster) {
		// TODO Automatisch erstellter Konstruktoren-Stub
		this.item = item;
		
		// Fläche belegen
		rect = new Rectangle(x, y, item.getImage().getWidth(), item.getImage().getHeight());
		this.money = money;
		this.parent_raster = parent_raster;
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
		return item.getText();
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
		return item.getImage();
	}
	
	/**Gibt die Fläche von einem Item zurück*/
	public Rectangle getRectangle()
	{
		return rect;
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
		getImage().draw(x, y);
	}
	
	public void setPos(Point p)
	{
		float x,y;
		x = (float)p.getX();
		y = (float)p.getY();
		setPos(x,y);
	}
	
	public Item getItem()
	{
		return item;
	}
}
