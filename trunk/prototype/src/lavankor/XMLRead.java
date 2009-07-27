package lavankor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLRead {
	
	/**Liste mit allen Elementen eines XML-Dokuments*/
	public NodeList nlist=null;
	
	public Node root_elem;
	
	public XMLRead(String path) {
		
		try {
		      DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
		      DocumentBuilder        builder  = factory.newDocumentBuilder();
		      Document               document = builder.parse(path);

		      // Das RootElement
		      root_elem = document.getDocumentElement();
		      
		      nlist = root_elem.getChildNodes();
		      
		      //if(root_elem.hasChildNodes()) nlist = root_elem.getChildNodes();
		 } 
		
		catch( SAXParseException spe ) {
			System.out.println( "\n**Fehler beim Parsen! Zeile: " + spe.getLineNumber() + ", uri "  + spe.getSystemId() );
			System.out.println( "   " + spe.getMessage() );
			
			Exception e = ( spe.getException() != null ) ? spe.getException() : spe;
			e.printStackTrace();
		} 
		catch( SAXException sxe ) {
			Exception e = ( sxe.getException() != null ) ? sxe.getException() : sxe;
			e.printStackTrace();
		} 
		
		catch( ParserConfigurationException pce ) {
			pce.printStackTrace();
		} 
		
		catch( IOException ioe ) {
			ioe.printStackTrace();
		}
		
	}
	
	/**Gibt einen Iterator zurück, der alle Elemente zurück gibt, die direkt unter dem Wurzel-Element stehen
	 * @return XMLIterator
	 * @throws NoSuchElementException
	 */
	public XMLIterator iterator() throws NoSuchElementException
	{
		if(nlist!=null)
		{
			return new XMLIterator(nlist);
		}
		else {
			throw new NoSuchElementException();
		}
	}
	
	/**Gibt gesuchte Elemente anhand eines bestimmten Namens zurück
	 * Falls im durchsuchten Document mehrere Einträge des Elements stehen, werden alle diese Einträge
	 * zu diesem Element in einer ArrayListe gespeichert
	 * Existiert nur ein Element, hat die zurückgegebene ArrayList nur einen Eintrag
	 * Existiert das gesuchte Element überhaupt nicht, so wird eine NoSuchElementException geworfen
	 * @return ArrayList
	 * @param ename:String (Name des gesuchten Elements)
	 * @throws NoSuchElementException
	 */
	/*
	public ArrayList<Node> getElements(String ename) throws NoSuchElementException
	{
		ArrayList<Node> erg = new ArrayList<Node>();
		
		if(nlist!=null)
		{
			// Liste der Nodes durchlaufen
			for(int i=0;i<nlist.getLength();i++)
			{
				if(nlist.item(i).getNodeName().equals(ename)) erg.add(nlist.item(i));
			}
		}
		
		if(erg.size()>0) return erg;
		throw new NoSuchElementException();
	}
	
	*/
	
	/**Mit Hilfe dieses Iterators ist es möglich durch alle Unterelemente eines Knotens zu iterieren
	 * @return XMLIterator
	 * @param father:Node 
	 * @throws NoSuchElementException
	 */
	public XMLIterator getElements(Node father) throws NoSuchElementException
	{
		if(father.hasChildNodes())
		{
			XMLIterator it = new XMLIterator(father.getChildNodes());	
			return it;
		}
		throw new NoSuchElementException();
	}
	
	/** Ähnlich wie getElements(Node father), nur dass hier ein Filter eingegeben werden kann, der einen Iterator mit bestimmten Element-Namen
	 * zurück gibt
	 * @param father:Node
	 * @param node_name:String
	 * @return XMLIterator
	 * @throws NoSuchElementException
	 */
	public XMLIterator getElements(Node father, String node_name) throws NoSuchElementException
	{
		XMLIterator it = getElements(father);
		ArrayList neue_liste = new ArrayList();
		Node current = null;
		
		// Nun durch die einzelnen Elemente iterieren und bei Namensübereinstimmung neue Liste beschreiben
		while(it.hasNext())
		{
			current = it.next();
			if(current.getNodeName().equals(node_name)) neue_liste.add(current);
		}
		
		if(neue_liste.size()>0) return new XMLIterator(neue_liste);
		throw new NoSuchElementException();
	}

	
	/**Sucht ein bestimmtes Element und gibt das erste vorkommende Element mit diesem Namen zurück
	 * Es durchsucht Über-Elemente nach dem gewünschten Unterelement
	 * @param elem:Node
	 * @param ename:String
	 * @return Node
	 * @throws NoSuchElementException
	 */
	public Node getElement(Node elem, String ename) throws NoSuchElementException
	{		
		NodeList clist = elem.getChildNodes();
		Node erg = null;
		
		for(int i=0;i<clist.getLength();i++)
		{
			if(clist.item(i).getNodeName().equals(ename)) erg = clist.item(i);
		}
		
		if(erg!=null) return erg;
		throw new NoSuchElementException();
	}
	
	/**Gibt zu einem Element ein bestimmtes Attribut zurück
	 * @param elem:Node
	 * @param aname:String
	 * @return Attribute
	 * @throws NoSuchElementException
	 */
	public Node getAttribute(Node elem, String aname) throws NoSuchElementException
	{
		Node erg = null;
		
		// Liste von Attributen anfordern
		NamedNodeMap amap = elem.getAttributes();
		
		for(int i=0;i<amap.getLength();i++)
		{
			if(amap.item(i).getNodeName().equals(aname)) erg = amap.item(i);
		}
		
		if(erg!=null) return erg;
		throw new NoSuchElementException();
	}
	
	/**Gibt zu einem Element und einem Attribut-Namen den Wert des Attributs zurück
	 * @param elem:Node Das Element, zu dem das Attribut gehört
	 * @param aname:String Name des Attributs, dessen Wert zurückgegeben werden soll
	 * @return String Wert des Attributs
	 * @throws NoSuchElementException
	 */
	public String getAttributeValue(Node elem, String aname) throws NoSuchElementException
	{
		// Attribute holen
		Node attr = getAttribute(elem,aname);
		
		if(attr.getNodeValue()!=null) return attr.getNodeValue();
		throw new NoSuchElementException();
	}
	
	/**Gibt den Text innerhalb eines Element-Tags zurück
	 * @param elem:Node Element, dessen Text zurückgegeben werden soll
	 * @return String
	 * @throws NoSuchElementException
	 */
	public String getText(Node elem) throws NoSuchElementException
	{
		if(elem.hasChildNodes())
		{
			return elem.getFirstChild().getNodeValue();
		}
		else {
			throw new NoSuchElementException();
		}
	}
}
