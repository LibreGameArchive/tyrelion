/**
 * 
 */
package lavankor.questsystem;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author diceware
 * @version 0.1a
 */
public class Quest implements Observer{

	Map<Integer, Step> steps;
	
	int activeStep = 0;
	
	public void activateStep(int step){
		//TODO
	}
	
	public void deactivateStep(int step){
		//TODO
	}

	public void update(Observable o, Object arg) {
		// TODO Automatisch erstellter Methoden-Stub
		
	}
}
