package lavankor.character;

import java.lang.String;

public class Rasse {

	private final String name;
	private final String text;
	/**
	 * @param name
	 * @param mod
	 * @param text
	 */
	public Rasse(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	public final static Rasse mensch = new Rasse("Mensch", "Beschreibung der Rasse Mensch");
	
	public String getName() {
		return name;
	}
	
	public String getText() {
		return text;
	}
	
	

}
