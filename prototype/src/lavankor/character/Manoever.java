package lavankor.character;

import java.lang.String;

public class Manoever {
	
	private final String codename;
	private final String name;
	private final String text;
	/**
	 * @param name
	 * @param text
	 */
	public Manoever(String codename, String name, String text) {
		this.codename = codename;
		this.name = name;
		this.text = text;
	}
	
	/** Probemanöver Defenisver Kampfstil, bis extern eingebunden*/
	public final static Manoever defKampfstil = new Manoever("defKampfstil", "Defensiver Kampfstil", "Erklärung Defensiver Kampfstil");
	/** Probemanöver Wuchtschlag, bis extern eingebunden*/
	public final static Manoever wuchtschlag = new Manoever("wuchtschlag", "Wuchtschlag", "Erklärung Wuchtschlag");
	/** Probemanöver Finte, bis extern eingebunden*/
	public final static Manoever finte = new Manoever("finte", "Finte", "Erklärung Finte");
	/** Probemanöver Todesstoß, bis extern eingebunden*/
	public final static Manoever todesstoß = new Manoever("todesstoß", "Todesstoß", "Erklärung Todesstoß");
	
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
