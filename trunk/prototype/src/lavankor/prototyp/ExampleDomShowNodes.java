package lavankor.prototyp;

//ExampleDomShowNodes.java

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class ExampleDomShowNodes
{
  public static void main( String[] argv )
  {
    if( argv.length != 1 )
    {
      System.err.println( "Usage:   java ExampleDomShowNodes <TagName>" );
      System.err.println( "Example: java ExampleDomShowNodes Button" );
      System.exit( 1 );
    }
    try {
      // ---- Parse XML file ----
      DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
      DocumentBuilder        builder  = factory.newDocumentBuilder();
      Document               document = builder.parse( new File("res/files/xml/fertigkeiten.xml") );
      // ---- Get list of nodes to given element tag name ----
      NodeList ndList = document.getElementsByTagName("fertigkeit");
      printNodesFromList( ndList );  // printNodesFromList see below
      // ---- Error handling ----
    } catch( SAXParseException spe ) {
        System.out.println( "\n** Parsing error, line " + spe.getLineNumber()
                                            + ", uri "  + spe.getSystemId() );
        System.out.println( "   " + spe.getMessage() );
        Exception e = ( spe.getException() != null ) ? spe.getException() : spe;
        e.printStackTrace();
    } catch( SAXException sxe ) {
        Exception e = ( sxe.getException() != null ) ? sxe.getException() : sxe;
        e.printStackTrace();
    } catch( ParserConfigurationException pce ) {
        pce.printStackTrace();
    } catch( IOException ioe ) {
        ioe.printStackTrace();
    }
  }

  // ---- Helper methods ----

  private static void printObjIfVisible( String sValName, Object obj )
  {
    if( null == obj )  return;
    String s = obj.toString();
    if( null != s && 0 < s.trim().length() && !s.trim().equals( "\n" ) )
      System.out.println( sValName + s );
  }

  public static void printNodeInfos( String sNodeName, Node node )
  {
    System.out.println(  "\n---------------------- " + sNodeName );
    if( null != node )
    {
      printObjIfVisible(   "getNodeType()        = ", "" + node.getNodeType() );
      printObjIfVisible(   "getNodeName()        = ", node.getNodeName() );
      printObjIfVisible(   "getLocalName()       = ", node.getLocalName() );
      printObjIfVisible(   "getNodeValue()       = ", node.getNodeValue() );
      if( node.hasAttributes() )
        printObjIfVisible( "getAttributes()      = ", node.getAttributes() );
      if( node.hasChildNodes() ) {
        printObjIfVisible( "getChildNodes()      = ", node.getChildNodes() );
        printObjIfVisible( "getFirstChild()      = ", node.getFirstChild() );
      }
      printObjIfVisible(   "getPreviousSibling() = ", node.getPreviousSibling() );
      printObjIfVisible(   "getNextSibling()     = ", node.getNextSibling() );
    }
    System.out.println(    "----------------------\n" );
  }

  public static void printNodesFromList( NodeList ndList )
  {
    for( int i=0; i<ndList.getLength(); i++ )
      printNodeInfos( "ndList.item("+i+")", ndList.item(i) );
  }
}
