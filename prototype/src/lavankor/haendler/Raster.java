package lavankor.haendler;

import java.util.ArrayList;

import lavankor.itemsystem.Item;

public class Raster {

	/**Das Raster an sich*/
	private ItemImpl[][] raster = null;
	
	/**Offsets zum linken, oberen Rand des Hintergrundbildes*/
	private int offx;
	private int offy;
	
	/**Box-Maße im Raster*/
	private int box_width;
	private int box_height;
	
	public Raster(int x, int y, int null_x, int null_y, int box_width, int box_height) {
		// TODO Automatisch erstellter Konstruktoren-Stub
		raster = new ItemImpl[x][y];
		this.offx = null_x;
		this.offy = null_y;
		this.box_width = box_width;
		this.box_height = box_height;
	}
	
	public Raster(ItemImpl[][] raster, int null_x, int null_y, int box_width, int box_height)
	{
		this.raster = raster;
		this.offx = null_x;
		this.offy = null_y;
		this.box_width = box_width;
		this.box_height = box_height;
	}
	
	/**Gibt das Raster an sich zurück*/
	public ItemImpl[][] getRaster()
	{
		return raster;
	}
	
	/**Setter für Raster*/
	public void setRaster(ItemImpl[][] raster)
	{
		this.raster = raster;
	}
	
	/**Gibt das x-Offset des Rasters zurück*/
	public int getOffx()
	{
		return offx;
	}
	
	/**Gibt das y-Offset des Rasters zurück*/
	public int getOffy()
	{
		return offy;
	}
	
	/**Gibt die Breite einer Box im Raster zurück*/
	public int getBox_width()
	{
		return box_width;
	}
	
	/**Gibt die Höhe einer Box im Raster zurück*/
	public int getBox_height()
	{
		return box_height;
	}
	
	/**Gibt eine Liste der Items zurück, die sich in diesem Raster befinden*/
	public ArrayList<Item> getItems()
	{
		ArrayList<Item> erg = new ArrayList<Item>();
		
		for(int i=0;i<raster.length;i++)
		{
			for(int j=0;j<raster[i].length;j++)
			{
				if(raster[i][j]!=null)
				{
					if(!erg.contains(raster[i][j].getItem())) erg.add(raster[i][j].getItem());
				}
			}
		}
		
		return erg;
	}
}
