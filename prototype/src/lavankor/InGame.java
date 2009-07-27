package lavankor;



import lavankor.avatar.NPC;
import lavankor.avatar.Player;
import lavankor.control.Control;
import lavankor.control.Pathfinder;
import lavankor.dialog.Dialog;
import lavankor.gui.GUI;
import lavankor.map.LavankorMap;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/** Test-Klasse zur Realisierung des Scrollings, der Spielerbewegung, TileMaps und Kollisionsabfrage.
 * 	@author Diceware
 *	@version 0.2a
 */
public class InGame extends BasicGameState {
	
	/** Die ID dieses Game States. */
	public static final int ID = 5;
	private GameMaster gameMaster;
	/** Der GameContainer, in dem sich dieser State befindet. */
	private GameContainer gameContainer;
	/** Die Map dieses Levels */
	private LavankorMap map;
	/** Die Spielfigur samt Animationen und Funktionen. */
	private Player player;
	/** Das GUI. */
	private GUI gui;
	private LevelLoader levelLoader;
	
	//Für Soundtest
	private boolean walking;
	
	//Für Kollisionsboxen
	private boolean showCollisions;
	private float boxX;
	private float boxY;
	
	/** Konstruktor der Klasse. Ruft lediglich den Superkonstruktor auf.
	 */
	public InGame() {
		super();
	}

	/** Liefert die ID dieses States.
	 *  @return Die ID dieses GameStates als int.
	 */
	public int getID() {
		return ID;
	}

	/** Initialisiert diesen State. Füllt Variablen, legt die TileMap an, erstellt die Kollsisionsmap
	 * 	und die Spieleranimation.
	 * 	@param container Der GameContainer, in dem sich dieser State befindet.
	 * 	@param game Das StateBasedGame bzw. genauer Main, in dem sich dieser State befindet.
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Variablen Initialisieren
		gameContainer = container;
		gameMaster = GameMaster.getInstance();
		levelLoader = new LevelLoader(gameMaster, gameContainer, this);
		levelLoader.loadLevel(2);
		player = gameMaster.getPlayer();
		player.setPathfinder(new Pathfinder(gameMaster));
		gui = new GUI(gameMaster, container);
		gameMaster.setOutBox(gui.getOutbox());
		
		// Den Bewegungsvektor (und damit die Spielfigur) aktualisieren
		player.updateMovementVector();
		
		//Für Soundtest
		walking = true;
		
		//Für Kollisionsboxen
		showCollisions = false;
	}

	/** Die Update-Funktion dieses States. Wird von SLick automatisch nach einer bestimmten Zeit
	 * 	ausgeführt. Fragt die Tastatureingaben des Spielers ab.
	 * 	@param container Der GameContainer, in dem sich dieser State befindet.
	 * 	@param game Das StateBasedGame bzw. genauer Main, in dem sich dieser State befindet.
	 *  @param delta Die Zeit, nach der Update ausgeführt wird in Millisekunden (Standard: 1 ms).
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// Der Spieler drückt die Taste für "nach links drehen". Aktualisiert "ang".
		if (container.getInput().isKeyDown(Control.getInstance().getKey(Control.LEFT))) {
			player.turnLeft(delta);
		}
		// s.o. nur für "nach rechts drehen"
		if (container.getInput().isKeyDown(Control.getInstance().getKey(Control.RIGHT))) {
			player.turnRight(delta);
		}
		// Versucht den Spieler in die Richtung zu bewegen, in die ergerade schaut.
		if (container.getInput().isKeyDown(Control.getInstance().getKey(Control.FORWARD))) {
			player.moveForward(delta);
		}
		// s.o. nur Rückwärts
		if (container.getInput().isKeyDown(Control.getInstance().getKey(Control.BACK))) {
			player.moveBackward(delta);
		}
		// Wird ausgeführt, falls Mausbewegung zur Zeit aktiv
		if (player.getPathfinder().isMoving()) {
			player.getPathfinder().movePlayer(delta);
		}
		
		for (int i = 0; i < gameMaster.getMap().getNpcMap().getLenghtX(); i++) {
			for (int j = 0; j < gameMaster.getMap().getNpcMap().getLenghtY(); j++) {
				if (gameMaster.getMap().getNpcMap().get(i, j) != null) {
					if (gameMaster.getMap().getNpcMap().moving(i, j)) {
						gameMaster.getMap().getNpcMap().get(i, j).getNpcMover().movePlayer(delta);
					}
				}
			}
		}
		
		if (player.isMoving()) {
			if (!player.getSoundManager().isPlaying()) {
				if (walking) {
					player.getSoundManager().play(RessourceStrings.SOUND_WALK);
				} else {
					player.getSoundManager().play(RessourceStrings.SOUND_RUN);
				}
			}
		} else {
			player.getSoundManager().stop();
		}
		
		RessourceManager.getInstance().getMusicManager().notifyListener();
	}
	
	/** Die Render-Funktion dieses States. Wird von Slick automatisch in jedem Frame ausgeführt. Der
	 * 	anzuzeigende Ausschnitt aus der TileMap wird berechnet und dann (etwas über den Bildschrimrand
	 * 	hinaus) gerendert.
	 * 	@param container Der GameContainer, in dem sich dieser State befindet.
	 * 	@param game Das StateBasedGame bzw. genauer Main, in dem sich dieser State befindet.
	 * 	@param g Die Graphics des Containers.
	 */
	@SuppressWarnings("static-access")
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// Position des Spielers in Tiles (von Float zu int -> Runden)
		player.setTileX((int) (player.getPosX()));
		player.setTileY((int) (player.getPosY()));
		
		map.render(player);
		
		// "Apply a translation to everything drawn to the context"
		g.translate((container.getWidth()/2+16) - (int) (player.getPosX() * map.getTileSize()),
					(container.getHeight()/2+16) - (int) (player.getPosY() * map.getTileSize()));
		
		// Darstellen des Spielers
		map.drawItems(g);
		map.drawNpcs(g);
		player.draw(g);

		// "Reset the transformation on this graphics context"
		g.resetTransform();

		// GUI rendern
		gui.draw(g);
				
		//Dummy für Dialog
		Dialog activeDialog = gameMaster.getActiveDialog();
		if (activeDialog!=null && activeDialog.isShowDialog()) { activeDialog.draw(container, g); }
		
		//Für Kollisionsboxen
		if (showCollisions) {
			for (int x=0;x<map.getMap().getWidth();x++) {
				for (int y=0;y<map.getMap().getHeight();y++) {
					if (map.blocked(x, y)) {
							boxX = x - (player.getPosX() - map.getLeftOffsetInTiles());
							boxY = y - (player.getPosY() - map.getTopOffsetInTiles());
						g.drawRect(boxX*32+16, boxY*32+16, 32, 32);
					}
				}
			}
		}
			
	}

	/** Fängt Tastatureingaben unabhängig bzw. vor Update ab. Wir z.B. benutzt zum Rennen oder
	 * 	Spiel verlassen.
	 *  @param key Der Integer-Wert der Taste, die gedrückt wurde.
	 *  @param c Falls zutreffend: der Buchstabe der gedrückten Taste.
	 */
	@SuppressWarnings("static-access")
	public void keyPressed(int key, char c) {
		// Spieler will rennen -> player_move_speed erhöhen
		if (key==Input.KEY_RSHIFT) { 
			player.setMove_speed(0.008f);
			player.getAnimation().setSpeed(2f);
			walking = false;
			player.getSoundManager().stop();
		}
		// Spiel verlassen
		if (key==Input.KEY_ESCAPE) {
			if (gui.getInventar().isShowDia()){ 
				gui.getInventar().setShowDia(false);
			} else { 
				if (gui.getHaendler().isShowDia()){ 
					gui.getHaendler().setShowDia(false); 
				} else { 
					if (gui.getCharinfo().isShowCharinfo()){ 
						gui.getCharinfo().setShowCharinfo(false); 
					} else { 
						if (GameMaster.getInstance().getActiveDialog()!=null && GameMaster.getInstance().getActiveDialog().isShowDialog()){ 
							GameMaster.getInstance().getActiveDialog().setShowDialog(false);
						} else { 
				gameContainer.exit(); }}}}
		}
		// Trinken
		if (key==Input.KEY_T) { 	
			player.setAnimation(RessourceStrings.ANIMATION_DRINK);
			player.getSoundManager().play(RessourceStrings.SOUND_DRINK);
		}
		// Charinfo anzeigen
		if (key == Control.getInstance().getKey(Control.CHARINFO)){
			gui.getCharinfo().setShowCharinfo(!gui.getCharinfo().isShowCharinfo());
		}
		//Charinfo durchschalten
		if (gui.getCharinfo().isShowCharinfo()){
			if (key==Input.KEY_RIGHT){
			gui.getCharinfo().drawNext();
			}
			if (key==Input.KEY_LEFT){
			gui.getCharinfo().drawPrev();
			}
		}
		
		// Inventar anzeigen
		if (key == Control.getInstance().getKey(Control.INVENTORY)){
			gui.getInventar().setShowDia(!gui.getInventar().isShowDia());
		}
		
		// Händler anzeigen
		if (key == Control.getInstance().getKey(Control.HAENDLER)){
			gui.getHaendler().setShowDia(!gui.getHaendler().isShowDia());
		}
		
		if (key == Control.getInstance().getKey(Control.FORWARD) || key == Control.getInstance().getKey(Control.BACK)){
			player.setMoving(true);
			player.getPathfinder().setMoving(false);
			player.getPathfinder().setStepIndex(0);
		}
		
		if (key == Control.getInstance().getKey(Control.LEFT) || key == Control.getInstance().getKey(Control.RIGHT)){
			player.getPathfinder().setMoving(false);
			player.getPathfinder().setStepIndex(0);
		}
		
		//Für Kollisionsboxen
		if (key==Input.KEY_Q) { 
			showCollisions = !showCollisions;
		}
		
		if (key==Input.KEY_L) { 
			gameMaster.getMap().getNpcMap().get("Ruwen").moveTo(54, 102);
		}
		
		if (key==Input.KEY_1) { 
			RessourceManager.getInstance().getMusicManager().playNavNormal();
		}
		
		if (key==Input.KEY_2) { 
			RessourceManager.getInstance().getMusicManager().playNavTension();
		}
		
		if (key==Input.KEY_3) { 
			RessourceManager.getInstance().getMusicManager().playFight();
		}
		
		if (key==Input.KEY_4) { 
			RessourceManager.getInstance().getMusicManager().playTavern();
		}
		
	}

	/** Fängt Tastatureingaben unabhängig bzw. vor Update ab. Wir z.B. benutzt zum Rennen oder
	 * 	Spiel verlassen.
	 *  @param key Der Integer-Wert der Taste, die gedrückt wurde.
	 *  @param c Falls zutreffend: der Buchstabe der gedrückten Taste.
	 */
	public void keyReleased(int key, char c) {
		// Spieler willnicht mehr rennen -> player_movement_speed verringern
		if (key==Input.KEY_RSHIFT) { 
			player.setMove_speed(0.003f);
			player.getAnimation().setSpeed(1f);
			walking = true;
			player.getSoundManager().stop();
		}
		// nicht mehr trinken
		if (key==Input.KEY_T) { 
			player.setAnimation(RessourceStrings.ANIMATION_WALK);
			player.getAnimation().restart();
			player.getSoundManager().stop();
		}
		//Für Soundtest
		if (key == Control.getInstance().getKey(Control.FORWARD) || key == Control.getInstance().getKey(Control.BACK)){
			player.setMoving(false);
		}
	}
	
	/** Fängt Mausklicks ab. Wird hier benutzt zum starten der Mausbewegung.
	 *	@param button Der Integer-Wert der Maustaste, die gedrückt wurde.
	 *	@param x Die X-Position (in Pixeln) an der die Taste gedrückt wurde.
	 *	@param y Die Y-Position (in Pixeln) an der die Taste gedrückt wurde.
	 */
	public void mousePressed(int button, int x, int y) {
		// Linke Maustaste wird gedrückt, Spieler will sich an angeklickte Stelle bewegen
		if (button == Input.MOUSE_LEFT_BUTTON) {
			// Bewegungspfad erstellen und Bewegung starten
			// Wenn der Spieler das Händler-Menu aufgerufen hat, dann Character nicht bei Mausklick bewegen 
			if(gui.getHaendler().isShowDia()) 
			{
				gui.getHaendler().mouseWasPressed(button, x, y);
			}
			else {
				player.getPathfinder().startMovement(x, y);
			}

		}
		if (button == Input.MOUSE_RIGHT_BUTTON) {
			
			// Wenn der Spieler das Händler-Menu aufgerufen hat, dann Character nicht bei Mausklick bewegen
			if(gui.getHaendler().isShowDia()) 
			{
				gui.getHaendler().mouseWasPressed(button, x, y);
			}
			else {
				gameMaster.rightClick(x, y);
			}
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		
		// Loslassen eines Gegenstandes im Händler-Menu
		if(gui.getHaendler().isShowDia())
		{
			gui.getHaendler().mouseWasReleased(button, x, y);
		}
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		
		// Wenn die Maus im Händler-Menu bewegt wurde
		if(gui.getHaendler().isShowDia())
		{
			gui.getHaendler().mouseWasMoved(gameContainer.getGraphics(),oldx, oldy, newx, newy);
		}
		
		int tileX = GameMaster.getInstance().translateCoordinates(newx, newy).x;
		int tileY = GameMaster.getInstance().translateCoordinates(newx, newy).y;
		
		if (GameMaster.getInstance().getMap().getNpcMap().get(tileX, tileY)!=null){
			GameMaster.getInstance().getMap().getNpcMap().get(tileX,tileY).showName();
		} else { GameMaster.getInstance().getMap().getNpcMap().hideNames(); }
		
		if (GameMaster.getInstance().getMap().getItemMap().get(tileX, tileY)!=null){
			GameMaster.getInstance().getMap().getItemMap().get(tileX,tileY).showName();
		} else { GameMaster.getInstance().getMap().getItemMap().hideNames(); }
		
	}

	public LavankorMap getMap() {
		return map;
	}

	public void setMap(LavankorMap map) {
		this.map = map;
	}
	
}
