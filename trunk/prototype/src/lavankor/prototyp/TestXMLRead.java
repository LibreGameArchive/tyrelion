package lavankor.prototyp;

import java.util.ArrayList;

import lavankor.XMLIterator;
import lavankor.XMLRead;

import org.w3c.dom.Node;

public class TestXMLRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Automatisch erstellter Methoden-Stub

		XMLRead reader = new XMLRead("res/files/xml/maps.xml");
		
		// Iterator anwenden
		XMLIterator xml_it = reader.iterator();
		Node current_elem = null;
		Node cur = null;
		
		while(xml_it.hasNext())
		{
			current_elem = xml_it.next();
			System.out.println("Element namens \"" + current_elem.getNodeName() + "\"");
			
			// Iterator für Unterelemente
			XMLIterator it = reader.getElements(current_elem,"item");
			while(it.hasNext())
			{
				cur = it.next();
				System.out.println("Element \"" + cur.getNodeName() + "\"");
			}
		}
	}

}
