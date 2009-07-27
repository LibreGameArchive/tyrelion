package lavankor.character;


import java.lang.String;
public class Kultur {

	private String name;
	private String text;
	
	public Kultur(String name, String text) {
		this.name = name;
		this.text = text;
	}
	
	public final static Kultur tarlagan = new Kultur("Tarlagan", "Beschreibung der Rasse Mensch");
	
	public String getName() {
		return name;
	}
	
	public String getText() {
		return text;
	}
}
