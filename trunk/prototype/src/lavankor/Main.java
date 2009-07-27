package lavankor;

import lavankor.menu.MenuControl;
import lavankor.menu.MenuCredits;
import lavankor.menu.MenuMain;
import lavankor.menu.MenuOptions;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



/** Die "Start"-Klasse. Erstellt ein StateBasedGame, startet den Container und stellt einige globale
 * 	Variablen (Z.B. für Musik oder Schriften) bereit.
 *  @author Diceware
 *	@version 0.1a
 */
public class Main extends StateBasedGame{

	
	/** Konstruktor der Klasse. Ruft den Superkonstruktor mit dem Titel des Spiels als Parameter auf.
	 */
	public Main() {
        super("Tales of Lavankor");
    }

	/** Initialisiert die einzelnen States, legt die "keyboard"-Map an und initialisiert "font".
	 * 	@param container Der aktuelle GameContainer.
	 */
	public void initStatesList(GameContainer container) throws SlickException {
		
		//GameMaster initialisieren
		GameMaster.getInstance();
		
		//States initialisieren
        addState(new MenuMain()); 
        
        addState(new MenuOptions());
        
        addState(new MenuControl());
        
        addState(new MenuCredits());
        
        addState(new InGame());
         
        // Spiele-Daten aus XML-Dateien laden
     //   InitGameData igd = new InitGameData(gameMaster);
     //   igd.setData();
    } 
	
	/** Main-Methode der Klasse. Initialisiert den GameContainer, stellt die Auflösung ein und startet
	 * 	den Container.
	 * 	@param argv
	 */
	public static void main(String[] argv) {
		try { 
			
            AppGameContainer container = new AppGameContainer(new Main()); 
            container.setDisplayMode(1024,768,false);
            container.setShowFPS(false);
            container.setTargetFrameRate(40);
            container.start(); 
        } catch (SlickException e) { 
            e.printStackTrace(); 
        } 
    }
	
}
