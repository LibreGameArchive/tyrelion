/**
 * 
 */
package lavankor.tests;

import junit.framework.TestCase;
import lavankor.dicesystem.Dicerolls;

/**
 * @author diceware
 * @version 0.1a
 */
public class TestDicerolls extends TestCase {
	
	

	public void setUp(){
		
	}
	
	/** 100 Mal testen ob das Würfelergebnis im Bereich der 
	 * maximal möglichen Augen auf realen Würfeln liegt.
	 */
	public void testRoll(){
		for (int i = 0;i<100;i++){
			int stufe = 12;
			int max = 20;
			int result;
			result = Dicerolls.roll(stufe);
			assertTrue(result<=max && result>=0);
			System.out.println(String.valueOf(result));
		}
	}
	
	
}
