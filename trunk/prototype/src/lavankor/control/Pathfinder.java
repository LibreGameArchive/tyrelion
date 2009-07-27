package lavankor.control;

import lavankor.GameMaster;
import lavankor.avatar.Player;
import lavankor.map.LavankorMap;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.heuristics.ClosestSquaredHeuristic;

/** Diese Klasse ist zuständig für die Maussteurung. Sie berechnet einen Pfad von der Start- zur
 * 	Zielposition des Spielers und führt die einzelnen Schritte der Bewegung aus.
 * 	@author Diceware
 *	@version 0.1a
 */
public class Pathfinder {
	
	/** Die X-Koordinate des Mauszeigers in Tiles (aber absolut!). */
	private float mouseX;
	/** Die Y-Koordinate des Mauszeigers in Tiles (aber absolut!). */
	private float mouseY;
	/** Bewegungsstatus. True falls Bewegung im Gange. Sonst False. */
	private boolean isMoving = false;
	/** Die X-Koordinate (relativ) des Ziel-Tiles. */
	private float targetX;
	/** Die Y-Koordinate (relativ) des Ziel-Tiles. */
	private float targetY;
	/** Die TileMap für den AStarPathFinder. */
	private TileBasedMapImpl pathMap;
	/** Der Mover (Token) für den Pfad. */
	private MoverImpl mover;
	/** Der Pfad, den die Spielfigur nehmen soll. */
	private Path path;
	/** Ein einzelner Schritt des Pfads. */
	private Path.Step step;
	/** Der Pfadfindungsalgorithmus. */
	private AStarPathFinder pathfinder;
	/** Der Index des aktuellen Steps. */
	private int stepIndex;
	/** Die Map, in der der Spieler sich bewegen soll. */
	private LavankorMap map;
	/** Der Spieler, der sich bewegen soll. */
	private Player player;
	
	/** Der Konstruktor belegt einige Variablen und stellt der AStarPathFinder ein.
	 * 	@param map Die Map, in der der Spieler sich bewegen soll. 
	 * 	@param player Der Spieler, der sich bewegen soll.
	 */
	public Pathfinder(GameMaster gameMaster) {
		// Variablen initialisieren
		map = gameMaster.getMap();
		player = gameMaster.getPlayer();
		mover = new MoverImpl();
		pathMap = new TileBasedMapImpl(map.getMap());
		// PathFinder einstellen
		pathfinder = new AStarPathFinder(pathMap, 100, true, new ClosestSquaredHeuristic());
	}
	
	public void startMovement(int x, int y) {
		makePath(x, y);
		setStepIndex(0);
		setMoving(true);
		player.setMoving(true);
	}
	
	/** Berechnet aus der Position des Mauszeigers den Korrekten Ziel-Tile und den Pfad dorthin
	 * 	@param x Die X-Position des Mauszeigers in Pixel.
	 * 	@param y Die Y-Position des Mauszeigers in Pixel.
	 */
	public void makePath(float x, float y) {
		// Mausposition von Pixel in Tiles umrechnen
		mouseX = x / (new Float(map.getTileSize()));
		mouseY = y / (new Float(map.getTileSize()));
		
		// absolute in relative Position und Ziel-Tile berechnen
		if (mouseX < player.getPosX()) {
			targetX = player.getPosX() - new Float(map.getLeftOffsetInTiles()) + mouseX;
		} else {
			targetX = player.getPosX() + mouseX - new Float(map.getLeftOffsetInTiles());
		}
		if (mouseY < player.getPosY()) {
			targetY = player.getPosY() - new Float(map.getTopOffsetInTiles()) + mouseY;
		} else {
			targetY = player.getPosY() + mouseY - new Float(map.getTopOffsetInTiles());
		}
		// Pfad finden
		path = pathfinder.findPath(mover, player.getTileX(), player.getTileY(),
									(int) targetX - 1, (int) targetY - 1);
	}

	/** Lässt die Spielfigur den berechneten Pfad entlang laufen.
	 */
	public void movePlayer(int delta) {
		if (path != null) {
			if (stepIndex < path.getLength()) {
				// Den aktuellen Step holen und Koordinaten einstellen
				step = path.getStep(stepIndex);
				int stepX = step.getX();
				int stepY = step.getY();
				
				/* Start: Spieler drehen
				 * Das Drehen der Spielfigur geschieht durch Berechnung von Dreiecken und bestimmten
				 * Winkeln mit Hilfe des Arcus Tangens.
				 */
				int a = player.getTileX() - stepX;
				int b = player.getTileY() - stepY;
				
				if (a > 0) {
					if (b > 0) {
						player.setAng((360 - (float) Math.toDegrees(Math.atan(a/b))) % 360);
					} else {
						player.setAng((180 + (90 - (float) Math.toDegrees(Math.atan(-b/a)))) % 360);
					}
				}
				if (a < 0) {
					if (b > 0) {
						player.setAng(((float) Math.toDegrees(Math.atan(-a/b))) % 360);
					} else {
						player.setAng((90 + (float) Math.toDegrees(Math.atan(-b/-a))) % 360);
					}
				}
				if (a == 0 && b < 0) {
					player.setAng(180);
				}
				if (a == 0 && b > 0) {
					player.setAng(0);
				}
				/* Ende: Spieler drehen */
			
				// Bewegungsvektor aktualisieren
				player.updateMovementVector();
				
				// Falls Bewegung möglich ist, Spieler bewegen
				if (player.tryMove(delta)) {
					player.getAnimation().update(delta);
				}
				// Wenn der Spieler einen Step abgeschlossen hat, den StepIndex erhöhen.
				if (player.getTileX() == stepX && player.getTileY() == stepY) {
					stepIndex++;
				}
			} else {
				// Der Spieler ist am Ende des Pfades angelangt. StepIndex auf 0 und isMoving auf false setzen.
				stepIndex = 0;
				isMoving = false;
				player.setMoving(false);
			}
		}
	}
	
	// Es folgen Getter und Setter, die wegen Trivialität nicht kommentiert werden.
	
	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public int getStepIndex() {
		return stepIndex;
	}

	public void setStepIndex(int stepIndex) {
		this.stepIndex = stepIndex;
	}
	
}
