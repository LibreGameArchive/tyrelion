package lavankor.control;

import java.util.HashMap;
import java.util.Map;

import lavankor.LavankorException;

import org.newdawn.slick.Input;

/**	Stellt Variablen für die Tastatursteuerung global zur Verfügung.
 * 	@author Diceware
 *	@version 0.1a
 */
public class Control {
	
	private static Control instance = null;
	
	private Map<String, Integer> keyMap;
	
	public static final String FORWARD = "Vorwaerts";
	public static final String BACK = "Zurueck";
	public static final String RIGHT = "Rechts";
	public static final String LEFT = "Links";
	public static final String INVENTORY = "Inventar";
	public static final String EQUIPMENT = "Ausruestung";
	public static final String CHARINFO = "Charakterinformation";
	public static final String QUESTLOG = "Questlogbuch";
	// Zu Testzwecken auch eine Taste für Händler-Menu belegen - Kann später rausgelöscht werden!
	public static final String HAENDLER = "Haendler";
	
	/** Konstruktor der Klasse. Instanziiert die KeyMap und weist Standdardbelegungen zu.
	 * Dazu werden wird die Input-Klasse aus dem Slick-Framework genutzt um die Tastenkennzahlen zu erfassen.
	 */
	public Control(){
		
		keyMap = new HashMap<String, Integer>();
		
		setKey("Vorwaerts", Input.KEY_W);
		setKey("Zurueck", Input.KEY_S);
		setKey("Rechts", Input.KEY_D);
		setKey("Links", Input.KEY_A);
		setKey("Inventar", Input.KEY_I);
		setKey("Ausruestung", Input.KEY_U);
		setKey("Charakterinformation", Input.KEY_C);
		setKey("Questlogbuch", Input.KEY_L);
		// Händler
		setKey("Haendler", Input.KEY_H);
		
	}
	
	public static Control getInstance() {
		if (instance == null) {
			instance = new Control();
		}
		return instance;
	}
	
	/**
	 * @return keyMap
	 */
	public Map<String, Integer> getKeyMap() {
		return keyMap;
	}
	/**
	 * @param keyMap Festzulegende Tastatur-Belegung
	 */
	public void setKeyMap(Map<String, Integer> keyMap) {
		this.keyMap = keyMap;
	}


	/** Abfragemethode zur Tastenbelegungeiner bestimmten Aktion.
	 * Falls die Aktion nicht bekannt ist wird eine LavankorException geschmissen.
	 * 
	 * @param action
	 * @return Die Kennzahl der Taste für die Aktion.
	 * @throws LavankorException
	 */
	public int getKey(String action) throws LavankorException {
		
		if (keyMap.containsKey(action)) {
			return keyMap.get(action);
		} else{
			throw new LavankorException("Diese Aktion ist nicht bekannt.");
		}
		
	}
	

	/** Methode die es möglich macht einer Aktion eine Taste zuzuweisen.
	 * Existiert die aktion noch nicht, wird sie erstellt.
	 * 
	 * @param action Name der Aktion
	 * @param key Taste (Kennzahl der Taste) die belegt werden soll.
	 */
	public void setKey(String action, int key) {

		keyMap.put(action, key);		
		
	}


	
	
	
	


}
