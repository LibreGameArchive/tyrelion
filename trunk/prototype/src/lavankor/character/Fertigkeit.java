package lavankor.character;

import java.lang.String;

/** Implementiert eine Fertigkeit, bestehend aus 
 * einem Namen, einem Wert und einem text zur Erklärung.
 * Desweiteren gibt es einen Codenamen um es einfacher ansprechen zu können.
 * @author diceware
 * @version 0.3a
 */
public class Fertigkeit {

	private final String codename;
	private final String name;
	private final String text;
	private int wert;
	/** Konstruktor der Klasse.
	 * @param kuerzel Bezeichnung für die programmier-interne Ansprache.
	 * @param name Bezeichnung der Fertigkeit.
	 * @param text Beschreibung der Fertigkeit.
	 */
	public Fertigkeit(String codename, String name, String text) {
		this.codename = codename;
		this.name = name;
		this.text = text;
		this.wert = 0;
	}
 
	public int getWert() {
		return wert;
	}
 
	public void setWert(int wert) {
		this.wert = wert;
	}
 
	public String getCodename() {
		return codename;
	}
 
	public String getName() {
		return name;
	}
 
	public String getText() {
		return text;
	}

}
