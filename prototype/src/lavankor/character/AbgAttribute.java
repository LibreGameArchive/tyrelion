package lavankor.character;

import java.util.HashMap;
import java.util.Map;

/** Diese Klasse stellt eine Sammlung der abgeleiteten Attribute zur Verfügung.
 *  Von hier aus werden die Attribute berechnet und verwaltet.
 * @author diceware
 * @version 0.3a
 */
public class AbgAttribute {

	/** Map als Datenbank für die Attribute.
	 *  Als Schlüssel dient der Name des Attributes und der Wert ist eine 
	 *  vollständige Instanz eines AbgAttribut.
	 */
	private Map<String, AbgAttribut> abgAttribute;
	
	/** Charakter dem die Attribute zugeordnet sind. */
	private Character character;
	
	
	/** Konstruktor der Klasse. Hier werden die Charakterattribute übergeben und daraus ein erstes mal die 
	 * abgeleiteten attribute generiert.
	 * @param attribute Die Attribute des Charakters.
	 */
	public AbgAttribute(Character character) {
		this.character = character;
		abgAttribute = new HashMap<String, AbgAttribut>();
		initAbgAttribute();
		calculate();
	}
	
	/** Berechnet zu jedem AbgAttribut in der Map den jeweiligen Wert. */
	public void calculate() {
		for (String att: abgAttribute.keySet()) {
			abgAttribute.get(att).calculate();
		}
	}
	
	/** Gibt ein abgeleiteten Attributes zurück.
	 * @param name Name des abgeleiteten Attributes
	 * @return Das abgeleitete Attribut als Objekt.
	 */
	public AbgAttribut get(String name){
		return abgAttribute.get(name);
	}
	
	/** Gibt direkt den Wert eines abgeleiteten Attributes zurück.
	 * @param name Name des abgeleiteten Attributes
	 * @return Wert des abgeleiteten Attributes
	 */
	public int getWert(String name){
		return abgAttribute.get(name).getWert();
	}
	
	/** Initialisiert die abgeleiteten Attribute.
	 * Hier werden die Attribute eingepflegt und jeweils 
	 * ein Name, drei Attribute, ein Divisor und die jeweilige Behinderung 
	 * eingefügt.
	 * Daraufhin wird das jewilige Attribut der Map hinzugefügt.
	 */
	public void initAbgAttribute(){
		
		Attribute attribute = character.getAttribute();
		
		String name; 
		Attribut att1; 
		Attribut att2; 
		Attribut att3;
		int div; 
		float be = 0.5F;
		
		//Attacke einpflegen
		name = "Attacke";       
		att1 = attribute.get("GW");
		att2 = attribute.get("KK");
		att3 = attribute.get("WI");
		div = 3;
		be = 1;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Ausdauer einpflegen
		name = "Ausdauer";       
		att1 = attribute.get("KL");
		att2 = attribute.get("KO");
		att3 = attribute.get("KO");
		div = 3;
		be = 1;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Beweglichkeit einpflegen
		name = "Beweglichkeit";       
		att1 = attribute.get("GW");
		att2 = attribute.get("GW");
		att3 = attribute.get("SI");
		div = 3;
		be = 1;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Darbietung einpflegen
		name = "Darbietung";       
		att1 = attribute.get("CH");
		att2 = attribute.get("KL");
		att3 = attribute.get("WI");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Fernkampf einpflegen
		name = "Fernkampf";       
		att1 = attribute.get("FF");
		att2 = attribute.get("SI");
		att3 = attribute.get("SI");
		div = 3;
		be = 0.5F;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Handhabung einpflegen
		name = "Handhabung";       
		att1 = attribute.get("FF");
		att2 = attribute.get("FF");
		att3 = attribute.get("KL");
		div = 3;
		be = 0.5F;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Heilkunde einpflegen
		name = "Heilkunde";       
		att1 = attribute.get("BI");
		att2 = attribute.get("CH");
		att3 = attribute.get("FF");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Heimlichkeit einpflegen
		name = "Heimlichkeit";       
		att1 = attribute.get("GW");
		att2 = attribute.get("SI");
		att3 = attribute.get("WI");
		div = 3;
		be = 0.5F;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Kommunikation einpflegen
		name = "Kommunikation";       
		att1 = attribute.get("CH");
		att2 = attribute.get("CH");
		att3 = attribute.get("WI");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Konzentration einpflegen
		name = "Konzentration";       
		att1 = attribute.get("KL");
		att2 = attribute.get("KO");
		att3 = attribute.get("WI");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Parade einpflegen
		name = "Parade";       
		att1 = attribute.get("GW");
		att2 = attribute.get("KK");
		att3 = attribute.get("SI");
		div = 3;
		be = 1;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Schaetzen einpflegen
		name = "Schaetzen";       
		att1 = attribute.get("BI");
		att2 = attribute.get("KL");
		att3 = attribute.get("SI");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Sportlichkeit einpflegen
		name = "Sportlichkeit";       
		att1 = attribute.get("GW");
		att2 = attribute.get("KK");
		att3 = attribute.get("KO");
		div = 3;
		be = 1;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Tierhaltung einpflegen
		name = "Tierhaltung";       
		att1 = attribute.get("CH");
		att2 = attribute.get("KL");
		att3 = attribute.get("WI");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Wahrnehmung einpflegen
		name = "Wahrnehmung";       
		att1 = attribute.get("KL");
		att2 = attribute.get("SI");
		att3 = attribute.get("SI");
		div = 3;
		be = 0.5F;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
		
		//Wissen einpflegen
		name = "Wissen";       
		att1 = attribute.get("BI");
		att2 = attribute.get("BI");
		att3 = attribute.get("KL");
		div = 3;
		be = 0;
		abgAttribute.put(name, new AbgAttribut(character, name, att1, att2, att3, div, be));
	}

	public Map<String, AbgAttribut> getAbgAttribute() {
		return abgAttribute;
	}
	
	

}
