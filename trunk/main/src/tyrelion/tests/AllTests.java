/**
 * 
 */
package tyrelion.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jahudi
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tyrelion.tests");
		//$JUnit-BEGIN$
		//Stripe 1
		suite.addTest(MusicLoaderTest.suite());
		suite.addTest(InfoboxTest.suite());
		suite.addTest(MusicManagerTest.suite());
		suite.addTest(SoundManagerTest.suite());
		suite.addTest(SoundLoaderTest.suite());
		//Stripe 2
		suite.addTest(InteractionManagerTest.suite());
		suite.addTest(WorldItemTest.suite());
		suite.addTest(WorldItemMapTest.suite());
		//$JUnit-END$
		return suite;
	}

}
