package lavankor.itemsystem;

import java.awt.Point;

import lavankor.GameMaster;
import lavankor.haendler.DragnDrop;
import lavankor.haendler.ItemImpl;
import lavankor.haendler.Money;
import lavankor.haendler.Raster;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Item {

	protected final String name;
	
	protected final int height;
	protected final int width;
	
	protected final String text;
	
	protected final Image img;
	
	protected final Image icon;
	
	protected int UID;
	
	protected ItemImpl item_impl;
	
	private boolean showName = false;

	/**
	 * @param name
	 * @param height
	 * @param width
	 * @param text
	 */
	public Item(int UID, String name, int height, int width, String text, Image bild, Image icon) {
		this.UID = UID;
		this.name = name;
		this.height = height;
		this.width = width;
		this.text = text;
		this.img = bild;
		this.icon = icon;
	}
	
	// ALLE Items müssen mit diesem Konstruktor aufgerufen werden, sonst gibts ne NullPointerException
	public Item(int UID, String name, int height, int width, String text, Image bild, Image icon, float x, float y, Money money, Raster raster) {
		this(UID,name,height,width,text,bild,icon);
		item_impl = new ItemImpl(this,x,y,money,raster);
	}
	
	public Item(int UID, String name, int height, int width, String text, Image bild, Image icon, Money money) {
		this(UID,name,height,width,text,bild,icon);
		
		DragnDrop dnd = GameMaster.getInstance().getDnd();
		Raster raster = dnd.getRaster(0);
		
		Point frei = null;
		Point kaesten = dnd.getRasterSize(bild,dnd.getRaster(0));
		
		if(raster!=null)
		{
			frei = dnd.findEmpties(raster, (int)kaesten.getX(), (int)kaesten.getY());
		
		}
		
		if(frei!=null)
		{
			//xy = dnd.getBox(raster, (int)frei.getX(), (int)frei.getY());
			item_impl = new ItemImpl(this,(int)frei.getX() + 64,(int)frei.getY() + 65,money,raster);
		}
	}
	
	public void draw(Graphics g, float x, float y) {
		//Namen anzeigen
		if (showName) {
			g.setColor(Color.lightGray);
			g.drawString(name, x-name.length(), y-15);
		}
		
		g.drawImage(icon, x, y);
	}

	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getText() {
		return text;
	}

	public Image getImage() {
		return img;
	}

	public Image getIcon() {
		return icon;
	}
	
	/**Fügt das dazugehörige, handlebare ItemImpl ein*/
	public void setItem_Impl(ItemImpl item_impl)
	{
		this.item_impl = item_impl;
	}
	
	public ItemImpl getItem_Impl()
	{
		return item_impl;
	}
	
	public abstract Item copy();

	public int getUID() {
		return UID;
	}

	public void setUID(int uid) {
		UID = uid;
	}
	
	public void showName(){
		showName = true;
	}
	
	public void hideName(){
		showName = false;
	}
	
	
	
}
