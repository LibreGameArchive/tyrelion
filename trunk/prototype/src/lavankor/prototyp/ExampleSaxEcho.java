package lavankor.prototyp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ExampleSaxEcho extends DefaultHandler
{
  static final   String       sNEWLINE   = System.getProperty( "line.separator" );
  static private Writer       out        = null;
  private        StringBuffer textBuffer = null;

  // ---- main ----

  public static void main( String[] argv )
  {
    try {
      // Use an instance of ourselves as the SAX event handler
      DefaultHandler handler = new ExampleSaxEcho();
      // Parse the input with the default (non-validating) parser
      SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
      saxParser.parse( new File("res/files/MyXmlFile.xml"), handler );
      System.exit( 0 );
    } catch( Throwable t ) {
      t.printStackTrace();
      System.exit( 2 );
    }
  }

  // ---- SAX DefaultHandler methods ----

  public void startDocument()
  throws SAXException
  {
    echoString( sNEWLINE + "<?xml ...?>" + sNEWLINE + sNEWLINE );
  }

  public void endDocument()
  throws SAXException
  {
    echoString( sNEWLINE );
  }

  public void startElement( String namespaceURI,
                            String localName,   // local name
                            String qName,       // qualified name
                            Attributes attrs )
  throws SAXException
  {
    echoTextBuffer();
    
    // Namen eines Elementes herausfinden
    String eName = ( "".equals( localName ) ) ? qName : localName;
    echoString("*Anfang Element mit Namen " + eName + "*:");
    //echoString( "<" + eName );
    
    // Feld mit Attributen durchgehen// element name
    if( attrs != null )
    {
      for( int i=0; i<attrs.getLength(); i++ )
      {
    	// Name eines Attributs
        String aName = attrs.getLocalName( i ); // Attr name
        // 2. Versuch
        if( "".equals( aName ) )  aName = attrs.getQName( i );
        // Ausgabe des Attributs
        echoString( "\n    + Neues Attribut mit Name/Wert: " +aName + "/" + attrs.getValue( i ));
      }
    }
    //echoString( ">" );
  }

  public void endElement( String namespaceURI,
                          String localName,     // local name
                          String qName )        // qualified name
  throws SAXException
  {
    // Ende eines Elements
    String eName = ( "".equals( localName ) ) ? qName : localName;
    echoString("\n*Text-Inhalt des Elements " + eName +":*");
  	echoTextBuffer();
    echoString( " *Ende von " + eName + "*\n" );           // element name
  }

  public void characters( char[] buf, int offset, int len )
  throws SAXException
  {
    String s = new String( buf, offset, len );
    if( textBuffer == null )
      textBuffer = new StringBuffer( s );
    else
      textBuffer.append( s );
  }

  // ---- Helper methods ----

  // Display text accumulated in the character buffer
  private void echoTextBuffer()
  throws SAXException
  {
    if( textBuffer == null )  return;
    echoString( textBuffer.toString() );
    textBuffer = null;
  }

  // Wrap I/O exceptions in SAX exceptions, to
  // suit handler signature requirements
  private void echoString( String s )
  throws SAXException
  {
    try {
      if( null == out )
        out = new OutputStreamWriter( System.out, "UTF8" );
      out.write( s );
      out.flush();
    } catch( IOException ex ) {
      throw new SAXException( "I/O error", ex );
    }
  }
}
