/**
 * 
 */
package tyrelion.tests;

import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;

import tyrelion.InteractionManager;

/**
 * @author jahudi
 *
 */
public class InteractionManagerTest {
	
	private InteractionManager manager;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = InteractionManager.getInstance();
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertEquals(manager, InteractionManager.getInstance());
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#keyPressed(int, char)}.
	 */
	@Test
	public void testKeyPressed() {
		manager.keyPressed(Input.KEY_A, 'a');
		assertEquals('a', manager.getKeyPressed_c());
		assertEquals(Input.KEY_A, manager.getKeyPressed_key());
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#keyReleased(int, char)}.
	 */
	@Test
	public void testKeyReleased() {
		manager.keyReleased(Input.KEY_B, 'b');
		assertEquals('b', manager.getKeyReleased_c());
		assertEquals(Input.KEY_B, manager.getKeyReleased_key());
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#mouseClicked(int, int, int, int)}.
	 */
	@Test
	public void testMouseClicked() {
		manager.mouseClicked(Input.MOUSE_LEFT_BUTTON, 512, 768, 2);
		assertEquals(Input.MOUSE_LEFT_BUTTON, manager.getMouseClicked_button());
		assertEquals(512, manager.getMouseClicked_x());
		assertEquals(768, manager.getMouseClicked_y());
		assertEquals(2, manager.getMouseClicked_clickCount());
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#mousePressed(int, int, int)}.
	 */
	@Test
	public void testMousePressed() {
		manager.mousePressed(Input.MOUSE_RIGHT_BUTTON, 268, 480);
		assertEquals(Input.MOUSE_RIGHT_BUTTON, manager.getMousePressed_button());
		assertEquals(268, manager.getMousePressed_x());
		assertEquals(480, manager.getMousePressed_y());
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#mouseReleased(int, int, int)}.
	 */
	@Test
	public void testMouseReleased() {
		manager.mouseReleased(Input.MOUSE_MIDDLE_BUTTON, 300, 600);
		assertEquals(Input.MOUSE_MIDDLE_BUTTON, manager.getMouseReleased_button());
		assertEquals(300, manager.getMouseReleased_x());
		assertEquals(600, manager.getMouseReleased_y());
	}

	/**
	 * Test method for {@link tyrelion.InteractionManager#mouseMoved(int, int, int, int)}.
	 */
	@Test
	public void testMouseMoved() {
		manager.mouseMoved(100, 200, 101, 201);
		assertEquals(100, manager.getMouseMoved_oldx());
		assertEquals(200, manager.getMouseMoved_oldy());
		assertEquals(101, manager.getMouseMoved_newx());
		assertEquals(201, manager.getMouseMoved_newy());
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(InteractionManagerTest.class);
	}

}
