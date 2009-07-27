package lavankor.character;

import java.util.HashMap;
import java.util.Map;

public class Attribute {
	
	/** Map zur Speicherung der Attribute, als Schlüssel dient das jeweilige Kürzel.*/
	private Map<String, Attribut> attribute;

	/** Konstruktor der Klasse.
	 * Instatiierung der Map und Initialisierung der Attribute. 
	 */
	public Attribute() {
		
		attribute = new HashMap<String, Attribut>();
		
		initAttribute();
		
	}
	
	public Attribut get(String att){
		return attribute.get(att);
	}
	
	public int getWert(String att){
		return attribute.get(att).getWert();
	}
	
	public String getName(String att){
		return attribute.get(att).getName();
	}
	
	public String getText(String att){
		return attribute.get(att).getText();
	}
	
	public void setWert(String att, int wert){
		attribute.get(att).setWert(wert);
	}
	
	/** Diese methode pflegt die Standardattribute in die Map ein.
	 * Standartwerte jeweils 0. 
	 */
	private void initAttribute(){
		
		String kuerzel;
		String name;
		String text;
		int wert;
		
		//BI einpflegen
		kuerzel="BI";
		name="Bildung";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//CH einpflegen
		kuerzel="CH";
		name="Charisma";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//FF einpflegen
		kuerzel="FF";
		name="Fingerfertigkeit";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//GW einpflegen
		kuerzel="GW";
		name="Gewandtheit";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//KL einpflegen
		kuerzel="KL";
		name="Klugheit";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//KO einpflegen
		kuerzel="KO";
		name="Konstitution";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//KK einpflegen
		kuerzel="KK";
		name="Körperkraft";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//SI einpflegen
		kuerzel="SI";
		name="Sinnesschärfe";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
		//WI einpflegen
		kuerzel="WI";
		name="Willenskraft";
		text="Hier wird das Attribut beschrieben.";
		wert=0;
		attribute.put(kuerzel, new Attribut(kuerzel, name, text,wert));
		
	}

	public Map<String, Attribut> getAttribute() {
		return attribute;
	}
}
