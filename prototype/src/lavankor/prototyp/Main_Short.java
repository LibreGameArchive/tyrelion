package lavankor.prototyp;

import lavankor.control.Control;
//import lavankor.haendler.MenuTrader;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/** Die "Start"-Klasse. Erstellt ein StateBasedGame, startet den Container und stellt einige globale
 * 	Variablen (Z.B. für Musik oder Schriften) bereit.
 *  @author Diceware
 *	@version 0.1a
 */
public class Main_Short extends StateBasedGame {
	
	private Control keySet;
	
	/** Konstruktor der Klasse. Ruft den Superkonstruktor mit dem Titel des Spiels als Parameter auf.
	 */
	public Main_Short() { 
        super("Tales of Lavankor"); 
    }

	/** Initialisiert die einzelnen States, legt die "keyboard"-Map an und initialisiert "font".
	 * 	@param container Der aktuelle GameContainer.
	 */
	public void initStatesList(GameContainer container) {
		//States initialisieren
        //addState(new MenuTrader()); 
        
        //Keyboardbelegung instatiieren.
        keySet= new Control(); 
    } 
	
	/** Main-Methode der Klasse. Initialisiert den GameContainer, stellt die Auflösung ein und startet
	 * 	den Container.
	 * 	@param argv
	 */
	public static void main(String[] argv) {
		try { 
			
            AppGameContainer container = new AppGameContainer(new Main_Short()); 
            container.setDisplayMode(1024,768,true); 
            container.start(); 
        } catch (SlickException e) { 
            e.printStackTrace(); 
        } 
    }
}
