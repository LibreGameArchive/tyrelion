/**
 * 
 */
package tyrelion.tests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tyrelion.gui.Infobox;
import tyrelion.gui.Message;

/**
 * @author imladriel
 *
 */
public class InfoboxTest {

	Message message;
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
		assertTrue(messages.contains(message));
		
	}
	
}
