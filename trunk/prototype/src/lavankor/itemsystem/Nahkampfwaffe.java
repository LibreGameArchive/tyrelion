package lavankor.itemsystem;

import lavankor.haendler.Money;

import org.newdawn.slick.Image;

/**
 * @author diceware
 * @version 0.1a
 */
public class Nahkampfwaffe extends Item {
	
	private int tp;
	private String kk;
	private String haende;
	private int at;
	private int pa;
	private int ini;
	private int preis;

	public Nahkampfwaffe(int UID, String name, int height, int width,
			String text, Image bild, Image icon, int tp, String kk, String haende,
			int at, int pa, int ini, int preis) {
		super(UID, name, height, width, text, bild, icon);
		this.tp = tp;
		this.kk = kk;
		this.haende = haende;
		this.at = at;
		this.pa = pa;
		this.ini = ini;
		this.preis = preis;
	}
	
	public Nahkampfwaffe(int UID, String name, int height, int width,
			String text, Image bild, Image icon, int tp, String kk, String haende,
			int at, int pa, int ini, int preis, Money money) {
		super(UID, name, height, width, text, bild, icon, money);
		this.tp = tp;
		this.kk = kk;
		this.haende = haende;
		this.at = at;
		this.pa = pa;
		this.ini = ini;
		this.preis = preis;
	}
	
	public Item copy(){
		return new Nahkampfwaffe(UID, name, height, width, text, img, icon, tp, kk, haende, at, pa, ini, preis, new Money(preis));
	}

}
