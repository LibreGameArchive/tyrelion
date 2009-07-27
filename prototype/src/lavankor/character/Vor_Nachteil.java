package lavankor.character;

import java.lang.String;

public class Vor_Nachteil {
	
	private final String codename;
	private final String name;
	private final String text;
	/** Ausprägungsstufe des Vor- oder Nachteils, standardmäßig 1. */
	private int stufe = 1;
	
	/** Konstruktor der Klasse.
	 * @param name
	 * @param text
	 */
	public Vor_Nachteil(String codename, String name, String text) {
		this.codename = codename;
		this.name = name;
		this.text = text;
	}
	
	//Vorteilsdefinitionen (vorübergehend)
	
	/** Probevorteil Gutaussehend, bis extern eingebunden*/
	public final static Vor_Nachteil gutaussehend = new Vor_Nachteil("gutaussehend", "Gutaussehend", "Erklärung Vorteil Gutaussehend");
	/** Probevorteil Schnelle Regeneration, bis extern eingebunden*/
	public final static Vor_Nachteil schnelle_regeneration = new Vor_Nachteil("schnelle_regeneration", "Schnelle Regeneration", "Erklärung Vorteil Schnelle Regeneration");
	/** Probevorteil Menschenfreund, bis extern eingebunden*/
	public final static Vor_Nachteil menschenfreund = new Vor_Nachteil("menschenfreund", "Menschenfreund", "Erklärung Vorteil Menschenfreund");
	/** Probevorteil Schattenläufer, bis extern eingebunden*/
	public final static Vor_Nachteil schattenlaeufer = new Vor_Nachteil("schattenlaeufer", "Schattenläufer", "Erklärung Vorteil Schattenläufer");
	/** Probevorteil Heilende Hände, bis extern eingebunden*/
	public final static Vor_Nachteil heilende_haende = new Vor_Nachteil("heilende_haende", "Heilende Hände", "Erklärung Vorteil Heilende Hände");
	
	//Nachteilsdefinitionen (vorübergehend)
	
	/** Probevorteil Hässlich, bis extern eingebunden*/
	public final static Vor_Nachteil haesslich = new Vor_Nachteil("haesslich", "Hässlich", "Erklärung Vorteil Hässlich");
	/** Probevorteil Schwaches Immunsystem Regeneration, bis extern eingebunden*/
	public final static Vor_Nachteil schwaches_immunsystem = new Vor_Nachteil("schwaches_immunsystem", "Schwaches Immunsystem", "Erklärung Vorteil Schwaches Immunsystem");
	/** Probevorteil Naiv, bis extern eingebunden*/
	public final static Vor_Nachteil naiv = new Vor_Nachteil("naiv", "Naiv", "Erklärung Vorteil Naiv");
	/** Probevorteil Tollpatsch, bis extern eingebunden*/
	public final static Vor_Nachteil tollpatsch = new Vor_Nachteil("tollpatsch", "Tollpatsch", "Erklärung Vorteil Tollpatsch");
	/** Probevorteil Angsthase, bis extern eingebunden*/
	public final static Vor_Nachteil angsthase = new Vor_Nachteil("angsthase", "Angsthase", "Erklärung Vorteil Angsthase");
	
	public String getCodename() {
		return codename;
	}
	
	public String getName() {
		return name;
	}
	
	public String getText() {
		return text;
	}

	public int getStufe() {
		return stufe;
	}

	public void setStufe(int stufe) {
		this.stufe = stufe;
	}

}
