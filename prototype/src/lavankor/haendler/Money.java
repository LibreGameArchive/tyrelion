package lavankor.haendler;

import java.util.ArrayList;

public class Money {

	private int dukaten, silbertaler, heller, kreuzer;
	
	public Money() {
		// TODO Auto-generated constructor stub
	}
	
	public Money(int preis) {
		// TODO Auto-generated constructor stub
		this();
		add(new Money(0,0,0,preis));
	}
	
	public Money(int dukaten, int silbertaler, int heller, int kreuzer)
	{
		this.dukaten = dukaten;
		this.silbertaler = silbertaler;
		this.heller = heller;
		this.kreuzer = kreuzer;
	}
	
	public void add(Money money)
	{
		int new_amount = getTotalAmount() + getTotalAmount(money);
		int rest;
		
		// In Geldeinheiten umrechne
		this.dukaten = new_amount / 1000;
		rest = new_amount % 1000;
		
		this.silbertaler = rest / 100;
		rest = rest % 100;
		
		this.heller = rest / 10;
		rest = rest % 10;
		
		this.kreuzer = rest;
	}
	
	public void sub(Money money)
	{
		int new_amount = getTotalAmount() - getTotalAmount(money);
		int rest;
		
		// In Geldeinheiten umrechne
		this.dukaten = new_amount / 1000;
		rest = new_amount % 1000;
		
		this.silbertaler = rest / 100;
		rest = rest % 100;
		
		this.heller = rest / 10;
		rest = rest % 10;
		
		this.kreuzer = rest;
	}
	
	public boolean bezahlbar(Money money)
	{
		return getTotalAmount() >= getTotalAmount(money);
	}
	
	public int getDukaten()
	{
		return dukaten;
	}
	
	public int getSilbertaler()
	{
		return silbertaler;
	}
	
	public int getTotalAmount(Money money)
	{
		return money.getDukaten() * 1000 + money.getSilbertaler() * 100 + money.getHeller() * 10 + money.getKreuzer();
	}
	
	public int getTotalAmount()
	{
		return getTotalAmount(this);
	}
	
	public String toString()
	{
		return dukaten + " DK, " + silbertaler + " ST, " + heller + " HE, " + kreuzer + " KR";
	}
	
	public int getHeller()
	{
		return heller;
	}
	
	public int getKreuzer()
	{
		return kreuzer;
	}

}
