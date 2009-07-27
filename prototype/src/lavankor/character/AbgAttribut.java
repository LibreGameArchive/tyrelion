package lavankor.character;



/** Klasse implementiert ein Abgeleitetes Attribut.
 *  Ein Abgeleitetes Attribut besteht aus einer Formel der Form (Att1,Att2,Att3)/div-BE
 *  ausserdem einem Namen und einem Erklärungstext.
 * @author diceware
 * @version 0.4a
 */
public class AbgAttribut {
	
	/** Charakter dem die Eigenschaft gehört. */
	private Character character;
	
	/** Name des abgeleiteten Attributes.	 */
	private final String name;
	
	/** Mutterattribute	 */
	private final Attribut att1;
	private final Attribut att2;
	private final Attribut att3;
	
	/** Behinderungsabzug in Form des Multiplikators ( Bsp. 0.5F für -1/2BE )	 */
	private final float be;
	
	/** Wert des Attributes	 */
	private int wert = 0;
	
	/** Divisor der Formel	 */
	private final int div;
	
	/** Beschreibung des abgeleiteten Attributes	 */
	private final String text;
	
	/** Konstruktor der Klasse.
	 * @param character Charakterdem die eigenschaft gehört
	 * @param name Name des abgeleiteten Attributes
	 * @param att1 1. Mutterattribut
	 * @param att2 2. Mutterattribut
	 * @param att3 3. Mutterattribut
	 * @param div Divisor der Formel
	 * @param be Behinderungsabzug in Form des Multiplikators ( Bsp. 0,5 für -1/2BE )
	 */
	public AbgAttribut(Character character, String name, Attribut att1, Attribut att2, Attribut att3,
			int div, float be) {
		this.character = character;
		this.name = name;
		this.att1 = att1;
		this.att2 = att2;
		this.att3 = att3;
		this.div = div;
		this.be = be;
		calculate();
		
		text = "Beschreibung des abgeleiteten Attributes";
	}
	
	/**Berechnung des Attributes, unter Einbeziehung der Attribute ses Charakters.	 */
	public void calculate(){
		wert = Math.round(( att1.getWert() + att2.getWert() + att3.getWert() ) / div) - Math.round(character.getBe()*be);
	}
	
	/** ausgabe der Formel als String.
	 * @return Formel des Attributes. Beispielform "( BI/CH/FF ) - BE"
	 */
	public String getFormel(){
		String s = "( " + att1 + "/" + att2 + "/" +att3 + " ) / " + div;
		String bes = Integer.toString((int) be) + " BE";		
		if (be==0.0) bes = "";
		if (be==0.5) bes = "1/2 BE";
		if (be==1.0) bes = "BE";
		if ( be != 0 ) s += "-" + bes;
		return  s;
	}
	public String getName() {
		return name;
	}

	public int getWert() {
		return wert;
	}

	public String getText() {
		return text;
	}

}
