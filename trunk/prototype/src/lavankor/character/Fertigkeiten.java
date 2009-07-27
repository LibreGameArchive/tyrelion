package lavankor.character;

import java.util.HashMap;
import java.util.Map;

/** Diese Klasse stellt eine Sammlung der Fertigkeiten zur Verfügung.
 *  Von hier aus werden die Fertigkeiten des Charakters verwaltet.
 * @author diceware
 * @version 0.3a
 */
public class Fertigkeiten {

	/** Map als Datenbank für die Fertigkeiten.
	 *  Als Schlüssel dient der Name der Fertigkeit und der Wert ist eine 
	 *  vollständige Instanz einer Fertigkeit.
	 */
	private Map<String, Fertigkeit> fertigkeiten;
	
	
	/** Konstruktor der Klasse.
	 *  Instatiierung der Fertigkeiten-Map und Initialisierung der Fertigkeiten
	 * */
	public Fertigkeiten() {
		fertigkeiten = new HashMap<String, Fertigkeit>();
		initFertigkeiten();
	}
	
	public Fertigkeit get(String fert){
		return fertigkeiten.get(fert);
	}
	
	public String getName(String fert){
		return fertigkeiten.get(fert).getName();
	}
	
	public String getText(String fert){
		return fertigkeiten.get(fert).getText();
	}
	
	public int getWert(String fert){
		return fertigkeiten.get(fert).getWert();
	}
	
	public void setWert(String fert, int wert){
		fertigkeiten.get(fert).setWert(wert);
	}
	
	/** Initialisiert die Fertigkeiten.
	 * Hier werden die Fertigkeiten eingepflegt und jeweils 
	 * ein Codename, ein Name und eine Textbeschreibung angelegt.
	 * Daraufhin wird die jewilige Fertigkeit der Map hinzugefügt.
	 */
	public void initFertigkeiten(){
		
		String codename;
		String name;
		String text;
		
		
		//aufmerksamkeit einpflegen
		codename = "aufmerksamkeit";
		name="Aufmerksamkeit";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//ausweichen einpflegen
		codename = "ausweichen";
		name="Ausweichen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//bogen_und_armbrust einpflegen
		codename = "bogen_und_armbrust";
		name="Bogen & Armbrust";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//handel einpflegen
		codename = "handel";
		name="Handel";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//hiebwaffen einpflegen
		codename = "hiebwaffen";
		name="Hiebwaffen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//klingenwaffen einpflegen
		codename = "klingenwaffen";
		name="Klingenwaffen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//koerperbeherrschung einpflegen
		codename = "koerperbeherrschung";
		name="Körperbeherrschung";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//menschenkenntnis einpflegen
		codename = "menschenkenntnis";
		name="Menschenkenntnis";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//messer_und_dolche einpflegen
		codename = "messer_und_dolche";
		name="Messer & Dolche";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//schleichen einpflegen
		codename = "schleichen";
		name="Schleichen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//schloesser_knacken einpflegen
		codename = "schloesser_knacken";
		name="Schloesser knacken";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//sich_verstecken einpflegen
		codename = "sich_verstecken";
		name="Sich verstecken";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//suchen einpflegen
		codename = "suchen";
		name="Suchen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//ueberreden einpflegen
		codename = "ueberreden";
		name="Ueberreden";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//verbergen einpflegen
		codename = "verbergen";
		name="Verbergen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//waffenloser_kampf einpflegen
		codename = "waffenloser_kampf";
		name="Waffenloser Kampf";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
		
		//wunden_heilen einpflegen
		codename = "wunden_heilen";
		name="Wunden heilen";
		text="Beschreibung der Fertigkeit.";
		fertigkeiten.put(codename, new Fertigkeit(codename, name, text));
	}

	public Map<String, Fertigkeit> getFertigkeiten() {
		return fertigkeiten;
	}
	
	
	

}
