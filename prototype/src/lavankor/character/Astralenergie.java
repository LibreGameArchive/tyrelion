package lavankor.character;

import java.lang.Integer;

/**
 * @author Daennart
 *
 * Astralenergie berechnet sich (CH+KL+KO)/2 + Bonus durch Rasse
 *
 */
public class Astralenergie {

		/** Akteller Stand der Astralenergie. */
		private int aktuell;
		/** Maximale Astralenergie. */
		private int max;
		
		/**
		 * 
		 */
		public Astralenergie(Character character) {
			calcMax(character);
			setAktuell(getMax());
		}
		
		/** Aktuellen Stand setzen, falls nicht über maximal;
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

		/** Methode erhöht die Astralenergie um einen Betrag, 
		 *  falls diese dadurch nicht über das Maximun steigt.
 		 * @param anzahl Wert der Erhöhung
		 */
		public void up(Integer anzahl) {
		 if (max-aktuell>=max-anzahl) {aktuell += anzahl;} else {aktuell=max;}
		}
		
		/** Methode verringert die Astralenergie um einen Betrag, 
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
			max = Math.round((character.getAttribute().getWert("CH") + character.getAttribute().getWert("KL") + character.getAttribute().getWert("KO"))/2);
		}

}
