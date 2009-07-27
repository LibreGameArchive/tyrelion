/**
 * 
 */
package lavankor.itemsystem;

import org.newdawn.slick.Image;

/**
 * @author diceware
 * @version 0.1a
 */
public class Fernkampfwaffe extends Item {
	
	private int tp;
	private int laden;
	private String haende;
	private int at;
	private int ini;
	private int preis;


	public Fernkampfwaffe(int UID, String name, int height, int width,
			String text, Image bild, Image icon, int tp, int laden, String haende,
			int at, int ini, int preis) {
		super(UID, name, height, width, text, bild, icon);
		this.tp = tp;
		this.haende = haende;
		this.at = at;
		this.ini = ini;
		this.preis = preis;
	}
	
	public Item copy(){
		return new Fernkampfwaffe(UID, name, height, width, text, img, icon, tp, laden, haende, at, ini, preis);
	}

}
