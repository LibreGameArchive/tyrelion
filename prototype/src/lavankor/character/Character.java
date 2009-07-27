/**
 * 
 */
package lavankor.character;

import java.util.HashSet;
import java.util.Set;

import lavankor.haendler.Money;

/** Klasse die den Charakter aus rollenspieltechnischer Sicht enthält.
 * @author diceware
 * @version 0.3a
 */
public class Character {

	/** Name des Charakters */
	private String name;
	
	/** Rasse des Charakters */
	private Rasse rasse;
	
	/** Kultur des Charakters */
	private Kultur kultur;
	
	/** Alle Standardattribute des Charakters */
	private Attribute attribute;
	
	/** Lebenskraft des Charakters (enthält maximale und aktuelle LE) */
	private Lebenskraft le;
	
	/** Astralenergie des Charakters (enthält maximale und aktuelle AE) */
	private Astralenergie ae;
	
	/** Alle Fertigkeiten des Charakters */
	private Fertigkeiten fertigkeiten;
	
	/** Alle abgeleiteten Attribute des Charakters */
	private AbgAttribute abgAttribute;
	
	/** Effektive Behinderung des Charakters, berechnung noch nicht imlementiert. */
	private int be;
	
	// TODO
	private Inventar inventory = new Inventar();
	//private Ausruestung ausruestung;

	/** Vor und Nachteile des Charakters als Liste im Set gespeichert. */
	private Set<Vor_Nachteil> vor_nachteile;
	
	/** Manöver des Charakters als Liste im Set gespeichert. */
	private Set<Manoever> manoever;
	
	/** Der Geldbeutel des Characters */
	private Money money = new Money();


	/** Konstruktor der Klasse, übergibt alle Daten die nicht automatisch berechnet werden.
	 * Brechnet danach die Abgeleiteten Attribute und weitere automatisch abgeleitete Werte.
	 * @param name Name  des Charakters
	 * @param rasse Rasse  des Charakters
	 * @param kultur Kultur  des Charakters
	 * @param attribute Attribute des Charakters
	 * @param le Lebensenergie des Charakters
	 * @param ae Astralenergie des Charakters
	 * @param fertigkeiten Fertigkeiten des Charakters
	 * @param vor_nachteile Vor- und Nachteile als Set<Manoever>
	 * @param manoever Manöver als Set<Manoever>
	 */
	public Character(String name, Rasse rasse, Kultur kultur,
			Attribute attribute, 
			Fertigkeiten fertigkeiten, Set<Vor_Nachteil> vor_nachteile, Set<Manoever> manoever) {
		this.name = name;
		this.rasse = rasse;
		this.kultur = kultur;
		this.attribute = attribute;
		this.le = new Lebenskraft(this);
		this.ae = new Astralenergie(this);
		this.fertigkeiten = fertigkeiten;
		this.vor_nachteile = vor_nachteile;
		this.manoever = manoever;
		
		abgAttribute = new AbgAttribute(this);
		
		// Etwas Geld in den Geldbeutel legen
		money.add(new Money(10,15,3,7));
		
		calculate();
		
	}

	
	/** Diese Methode berechnet alle automatisch berechneten Wert neu. */
	public void calculate(){
		le.calcMax(this);
		ae.calcMax(this);
		abgAttribute.calculate();
	}

	public int getBe() {
		return be;
	}
	
	public Money getMoney()
	{
		return money;
	}

	public Attribute getAttribute() {
		return attribute;
	}


	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}


	public Lebenskraft getLe() {
		return le;
	}


	public void setLe(Lebenskraft le) {
		this.le = le;
	}


	public Astralenergie getAe() {
		return ae;
	}


	public void setAe(Astralenergie ae) {
		this.ae = ae;
	}


	public String getName() {
		return name;
	}


	public Rasse getRasse() {
		return rasse;
	}


	public Kultur getKultur() {
		return kultur;
	}


	public Fertigkeiten getFertigkeiten() {
		return fertigkeiten;
	}


	public AbgAttribute getAbgAttribute() {
		return abgAttribute;
	}


	public Set<Manoever> getManoever() {
		return manoever;
	}


	public void setBe(int be) {
		this.be = be;
	}

	public Set<Vor_Nachteil> getVor_nachteile() {
		return vor_nachteile;
	}
	
	//DUMMYS
	/** Hier wird der ein DummyCharakter erstellt.
	 * @return Dummy Charakter
	 */
	public static Character createDummy(){
		
		Character tempchar;
		
		String name = "Imladriel";
		Rasse rasse = Rasse.mensch;
		Kultur kultur = Kultur.tarlagan;
		Attribute attribute = new Attribute();
		Fertigkeiten fertigkeiten = new Fertigkeiten();
		Set<Vor_Nachteil> vor_nachteil = new HashSet<Vor_Nachteil>();
		Set<Manoever> manoever = new HashSet<Manoever>();
		
		
		
		attribute.setWert("BI", 10);
		attribute.setWert("CH", 12);
		attribute.setWert("FF", 14);
		attribute.setWert("GW", 16);
		attribute.setWert("KL", 9);
		attribute.setWert("KO", 11);
		attribute.setWert("KK", 10);
		attribute.setWert("SI", 15);
		attribute.setWert("WI", 13);
		
		manoever.add(Manoever.wuchtschlag);
		manoever.add(Manoever.defKampfstil);
		manoever.add(Manoever.finte);
		
		vor_nachteil.add(Vor_Nachteil.gutaussehend);
		vor_nachteil.add(Vor_Nachteil.schnelle_regeneration);
		vor_nachteil.add(Vor_Nachteil.schattenlaeufer);
		vor_nachteil.add(Vor_Nachteil.naiv);
		
		fertigkeiten.setWert("ueberreden", 7);
		
		tempchar = new Character(name, rasse, kultur, attribute, fertigkeiten, vor_nachteil, manoever);
		
		tempchar.calculate();
		
		return tempchar;
	}


	public Inventar getInventory() {
		return inventory;
	}


	public void setInventory(Inventar inventory) {
		this.inventory = inventory;
	}
	
	
	
}
