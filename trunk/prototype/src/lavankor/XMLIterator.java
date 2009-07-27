package lavankor;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**Dieser Iterator liest alle Elemente ein, die direkt unter dem Root-Element eines XML-Files liegen*/
public class XMLIterator implements Iterator {

	private NodeList nlist = null;
	private ArrayList alist = null;
	private int counter;
	
	public XMLIterator(NodeList nlist)
	{
		this.nlist = nlist;
		counter = 1;
	}
	
	public XMLIterator(ArrayList alist)
	{
		this.alist = alist;
		counter = 0;
	}
	
	public boolean hasNext() {
		// Unterscheiden zw. Array- und NodeList
		boolean erg = false;
		
		if(nlist!=null && counter<(nlist.getLength())) erg = true;
		if(alist!=null && counter<(alist.size())) erg = true;
		
		return erg;
	}

	public Node next() {

		Node next_elem = null;
		
		// Nächste Element für NodeList
		if(nlist!=null)
		{
			if(hasNext())
			{
				next_elem = nlist.item(counter);
				counter+=2;
			}
		}
		
		// Nächstes Element für ArrayList
		if(alist!=null)
		{
			if(hasNext())
			{
				next_elem = (Node)alist.get(counter);
				counter += 1;
			}
		}
		
		return next_elem;
	}

	public void remove() {
		// TODO Automatisch erstellter Methoden-Stub

	}

}
