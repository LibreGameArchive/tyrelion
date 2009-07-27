/**
 * 
 */
package lavankor.questsystem;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author diceware
 * @version 0.1a
 */
public class QuestObserver extends Observable implements Observer {

	Map<Integer, List<Quest>> dialogNotifier;
	
	Map<Integer, List<Quest>> itemNotifier;
	
	List<Quest>[][] positionNotifier;	
	
	


	@Override
	public void notifyObservers() {
		super.notifyObservers(this);
	}




	/* (Kein Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		// TODO Automatisch erstellter Methoden-Stub

	}

}
