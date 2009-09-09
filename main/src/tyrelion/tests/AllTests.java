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
		suite.addTest(MusicLoaderTest.suite());
		suite.addTest(InfoboxTest.suite());
		suite.addTest(MusicManagerTest.suite());
		suite.addTest(SoundManagerTest.suite());
		suite.addTest(SoundLoaderTest.suite());
		//$JUnit-END$
		return suite;
	}

}
