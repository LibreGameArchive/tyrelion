package lavankor.prototyp;

//ExampleJdomWrite.java

import java.io.File;

//import org.jdom.Document;
//import org.jdom.input.SAXBuilder;
//import org.jdom.output.Format;
//import org.jdom.output.XMLOutputter;

public class ExampleJdomWrite
{
  public static void main( String[] args )
  {
    try {
      // ---- Read XML file ----
      //SAXBuilder builder = new SAXBuilder();
      //Document doc = builder.build( new File("res/files/MyXmlFile.xml") );
      // ---- Modify XML data ----
      //  ... do anything with XML data
      // ---- Write XML file ----
      //XMLOutputter fmt = new XMLOutputter();
      //fmt.setFormat( Format.getPrettyFormat() );  // only for nicer formatting
      //fmt.output( doc, System.out );
    } catch( Exception ex ) {
      ex.printStackTrace();
    }
  }
}
