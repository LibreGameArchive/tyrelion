package lavankor.control;

import lavankor.avatar.NPC;
import lavankor.map.LavankorMap;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.heuristics.ClosestSquaredHeuristic;

public class NpcMover {

	/** Bewegungsstatus. True falls Bewegung im Gange. Sonst False. */
	private boolean isMoving = false;
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
	/** Der Spieler, der sich bewegen soll. */
	private NPC npc;
	
	/** Der Konstruktor belegt einige Variablen und stellt der AStarPathFinder ein.
	 * 	@param map Die Map, in der der Spieler sich bewegen soll. 
	 * 	@param player Der Spieler, der sich bewegen soll.
	 */
	public NpcMover(LavankorMap map, NPC npc) {
		// Variablen initialisieren
		this.npc = npc;
		mover = new MoverImpl();
		pathMap = new TileBasedMapImpl(map.getMap());
		// PathFinder einstellen (17 ~ Suchweite für den Pfad)
		pathfinder = new AStarPathFinder(pathMap, 100, true, new ClosestSquaredHeuristic());
	}
	
	/** Berechnet aus der Position des Mauszeigers den Korrekten Ziel-Tile und den Pfad dorthin
	 * 	@param x Die X-Position des Mauszeigers in Pixel.
	 * 	@param y Die Y-Position des Mauszeigers in Pixel.
	 */
	public void makePath(int x, int y) {
		path = pathfinder.findPath(mover, (int)npc.getPosX(), (int)npc.getPosY(), x, y);
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
				int a = (int)npc.getPosX() - stepX;
				int b = (int)npc.getPosY() - stepY;
				
				if (a > 0) {
					if (b > 0) {
						npc.setAng((360 - (float) Math.toDegrees(Math.atan(a/b))) % 360);
					} else {
						npc.setAng((180 + (90 - (float) Math.toDegrees(Math.atan(-b/a)))) % 360);
					}
				}
				if (a < 0) {
					if (b > 0) {
						npc.setAng(((float) Math.toDegrees(Math.atan(-a/b))) % 360);
					} else {
						npc.setAng((90 + (float) Math.toDegrees(Math.atan(-b/-a))) % 360);
					}
				}
				if (a == 0 && b < 0) {
					npc.setAng(180);
				}
				if (a == 0 && b > 0) {
					npc.setAng(0);
				}
				/* Ende: Spieler drehen */
			
				// Bewegungsvektor aktualisieren
				npc.updateMovementVector();
				
				// Falls Bewegung möglich ist, Spieler bewegen
				if (npc.tryMove(delta)) {
					npc.getAnimation().update(delta);
				}
				// Wenn der Spieler einen Step abgeschlossen hat, den StepIndex erhöhen.
				if ((int)npc.getPosX() == stepX && (int)npc.getPosY() == stepY) {
					stepIndex++;
				}
			} else {
				// Der Spieler ist am Ende des Pfades angelangt. StepIndex auf 0 und isMoving auf false setzen.
				stepIndex = 0;
				isMoving = false;
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
