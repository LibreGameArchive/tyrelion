package lavankor.character;


/**
 * @author diceware
 * @version 0.3a
 * Lebenskraft berechnet sich (KO+KO+KK)/2 + Bonus durch Rasse
 *
 */
public class Lebenskraft {

	/** Akteller Stand der Lebensenergie. */
	private int aktuell;
	/** Maximale Lebensenergie. */
	private int max;
	
	/**
	 * 
	 */
	public Lebenskraft(Character character) {
		calcMax(character);
		setAktuell(getMax());
	}
	
	/** Aktuellen Stand setzen, falls nicht über maximal.
	 * @param akt Neuer Stand
	 */
	public void setAktuell(int akt){
		if (akt<=max) aktuell=akt;
	}
	
	public int getAktuell() {
		return aktuell;
	}

	public int getMax() {
		return max;
	}

	/** Methode erhöht die Lebensenergie um einen Betrag, 
	 *  falls diese dadurch nicht über das Maximun steigt.
		 * @param anzahl Wert der Erhöhung
	 */
	public void up(Integer anzahl) {
	 if (max-aktuell>=max-anzahl) {aktuell += anzahl;} else {aktuell=max;}
	}
	
	/** Methode verringert die Lebensenergie um einen Betrag, 
	 *  falls diese dadurch nicht unter Null liegt.
		 * @param anzahl Wert der Verringerung
	 */
	public void down(Integer anzahl) {
	if (aktuell>=anzahl) {aktuell -= anzahl;} else {aktuell=0;}
	}
	
	/** Maximaler Wert wird aus den Charkater-Werten berechnet.
	 * @param character Zugehöriger Charakter
	 */
	public void calcMax(Character character){
		max = Math.round((character.getAttribute().getWert("KO") + character.getAttribute().getWert("KO") + character.getAttribute().getWert("KK"))/2);
		if (character.getKultur().getName().equals("Tarlagan")) { max += 22; }
	}



}
