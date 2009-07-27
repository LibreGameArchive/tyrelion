package lavankor.character;

/** Implementiert ein Attribut, bestehend aus 
 * einem Namen, einem Wert und einem text zur Erklärung.
 * Desweiteren gibt es ein Kürzel um es einfacher ansprechen zu können.
 * @author diceware
 * @version 0.3a
 */
public class Attribut {

	/** Kürzel zur einfacheren Ansprache. */
	private final String kuerzel;
	
	/** Name des Attributes. */
	private final String name;
	
	/** Text zur Erklärung */
	private final String text;
	
	/** Wert des Attributes. */
	private int wert;
	
	/** Konstruktor der Klasse.
	 * @param kuerzel
	 * @param name
	 * @param text
	 * @param wert
	 */
	public Attribut(String kuerzel, String name, String text, int wert) {
		this.kuerzel = kuerzel;
		this.name = name;
		this.text = text;
		this.wert = wert;
	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public String getKuerzel() {
		return kuerzel;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	
}
