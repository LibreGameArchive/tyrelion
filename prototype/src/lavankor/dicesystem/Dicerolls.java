/**
 * 
 */
package lavankor.dicesystem;

import java.util.Random;
import lavankor.character.Character;

/** In dieser Klasse können über statische Methoden Würfelwürfe simuliert werde,
 * zudem kann eine gezielte Probe auf bis zu zwei Werte eines Charakters
 * ausgeführt werden.
 * @author diceware
 * @version 0.1a
 */
public class Dicerolls {
	
	/** Simuliert eine Probe auf zwei Werte eines Charakters und vergleicht sie mit 
	 * dem Erfolgswert. Als Rückgabe gibt es die übrig gebliebenen Punkte, bei einem Fehklschllag können
	 * diese auch negativ sein.
	 * @param character Der Chracter der die Probe macht, wird benötigt, um die Werte anzufragen.
	 * @param first Erster Wert, kann ein Attribut, ein angeleitetes Attribut oder eine Fertigkeit sein. (Wird als String-Bezeichnung übergeben.)
	 * @param second Zweiter Wert, kann ein Attribut, ein angeleitetes Attribut oder eine Fertigkeit sein. (Wird als String-Bezeichnung übergeben.)
	 * @param ew Erfolgswert, gegen den die Probe erfolgt.
	 * @return Übrige Punkte, kann auch negativ sein, dann ist es ein Fehlschlag.
	 */
	public static int roll(Character character, String first, String second, int ew){
		int result = 0;
		int first_value = 0;
		int second_value = 0;
		
		if (first!=null){
			if (character.getAttribute().getAttribute().containsKey(first)){
				first_value = character.getAttribute().getWert(first);
			} else {
				if (character.getAbgAttribute().getAbgAttribute().containsKey(first)){
					first_value = character.getAbgAttribute().getWert(first);
				} else {
					if (character.getFertigkeiten().getFertigkeiten().containsKey(first)){
						first_value = character.getFertigkeiten().getWert(first);
					}
			}
		}
		}
			
		if (second!=null){
			if (character.getAttribute().getAttribute().containsKey(second)){
				second_value = character.getAttribute().getWert(second);
			} else {
				if (character.getAbgAttribute().getAbgAttribute().containsKey(second)){
					second_value = character.getAbgAttribute().getWert(second);
				} else {
					if (character.getFertigkeiten().getFertigkeiten().containsKey(second)){
						second_value = character.getAbgAttribute().getWert(second);
					}
			}
		}
		}
		
		int lvl = first_value + second_value;
		int value = roll(lvl);
		result = value - ew;
		return result;
	}
	
	
	/** Simuliert einen Würfelwurf auf einer bestimmte Würfelstufe.
	 * @param lvl Stufe des Wurfes.
	 * @return gibt das Ergebnis des WUrfes zurück.
	 */
	public static int roll(int lvl){
		int result = 0;
		
		switch (lvl) {
		case 1: result = w2(); break;
		case 2: result = w3(); break;
		case 3: result = w4(); break;
		case 4: result = w6(); break;
		
		case 5: result = w8(); break;
		case 6: result = w10(); break;
		case 7: result = w12(); break;
		case 8: result = w6() + w6(); break;
		
		case 9: result = w8() + w6(); break;
		case 10: result = w10() + w6(); break;
		case 11: result = w10() + w8(); break;
		case 12: result = w10() + w10(); break;
		
		case 13: result = w12() + w10(); break;
		case 14: result = w12() + w12(); break;
		case 15: result = w12() + w6() + w6(); break;
		case 16: result = w12() + w8() + w6(); break;
		
		case 17: result = w12() + w10() + w6(); break;
		case 18: result = w12() + w10() + w8(); break;
		case 19: result = w12() + w10() + w10(); break;
		case 20: result = w12() + w12() + w10(); break;
		
		case 21: result = w12() + w12() + w12(); break;
		case 22: result = w12() + w12() + w6() + w6(); break;
		case 23: result = w12() + w12() + w8() + w6(); break;
		case 24: result = w12() + w12() + w10() + w6(); break;
		
		case 25: result = w12() + w12() + w10() + w8(); break;
		case 26: result = w12() + w12() + w10() + w10(); break;
		case 27: result = w12() + w12() + w12() + w10(); break;
		case 28: result = w12() + w12() + w12() + w12(); break;
		}
		
		return result;
	}
	
	/** Simuliert einen Wurf mit einem "zweiseitigen Würfel" (Münzwurf)
	 * @return Würfelergebnis
	 */
	public static int w2(){		
		Random random;
        random = new Random();
        int result = random.nextInt(2) + 1;
		return result;
	}
	
	/** Simuliert einen Wurf mit einem "dreiseitigen Würfel" (W6 durch 2)
	 * @return Würfelergebnis
	 */
	public static int w3(){		
		Random random;
        random = new Random();
        int result = random.nextInt(3) + 1;
		return result;
	}
	
	/** Simuliert einen Wurf mit einem vierseitigen Würfel
	 * @return Würfelergebnis
	 */
	public static int w4(){		
		Random random;
        random = new Random();
        int result = random.nextInt(4) + 1;
		return result;
	}
	
	/** Simuliert einen Wurf mit einem sechsseitigen Würfel
	 * @return Würfelergebnis
	 */
	public static int w6(){		
		Random random;
        random = new Random();
        int result = random.nextInt(6) + 1;
		return result;
	}

	/** Simuliert einen Wurf mit einem achtseitigen Würfel
	 * @return Würfelergebnis
	 */
	public static int w8(){		
		Random random;
        random = new Random();
        int result = random.nextInt(8) + 1;
		return result;
	}
	
	/** Simuliert einen Wurf mit einem zehnseitigen Würfel
	 * @return Würfelergebnis
	 */
	public static int w10(){		
		Random random;
        random = new Random();
        int result = random.nextInt(10) + 1;
		return result;
	}
	
	/** Simuliert einen Wurf mit einem zwölfseitigen Würfel
	 * @return Würfelergebnis
	 */
	public static int w12(){		
		Random random;
        random = new Random();
        int result = random.nextInt(12) + 1;
		return result;
	}
	
	
	
	
}
