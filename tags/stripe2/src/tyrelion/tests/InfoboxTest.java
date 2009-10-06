/**
 * 
 */
package tyrelion.tests;


import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import tyrelion.gui.Infobox;
import tyrelion.gui.Message;

/**
 * @author imladriel
 *
 */
public class InfoboxTest {

	Infobox infobox;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		infobox = new Infobox(null);
	}
	
	@Test
	public void testInfobox() {
		
		infobox.print("Test String");
		
		ArrayList<Message> messages = infobox.getMessages();
		
		assertFalse(messages.isEmpty());		
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(InfoboxTest.class);
	}
	
}
