package lavankor.itemsystem;

import lavankor.haendler.Money;
import lavankor.haendler.Raster;

import org.newdawn.slick.Image;

public class TestItem extends Item {

	public TestItem(int UID, String name, int height, int width, String text,
			Image bild, Image icon) {
		super(UID, name, height, width, text, bild, icon);
		// TODO Auto-generated constructor stub
	}

	public TestItem(int UID, String name, int height, int width, String text,
			Image bild, Image icon, Money money) {
		super(UID, name, height, width, text, bild, icon,  money);
		// TODO Auto-generated constructor stub
	}
	
	public TestItem(int UID, String name, int height, int width, String text,
			Image bild, Image icon, float x, float y, Money money, Raster raster) {
		super(UID, name, height, width, text, bild, icon, x, y, money, raster);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
