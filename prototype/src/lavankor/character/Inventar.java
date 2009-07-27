package lavankor.character;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lavankor.GameMaster;
import lavankor.haendler.DragnDrop;
import lavankor.haendler.ItemImpl;
import lavankor.haendler.Raster;
import lavankor.itemsystem.Item;
import lavankor.questsystem.dummy_quest;
import lavankor.ressourcesystem.RessourceManager;


public class Inventar {

	private ArrayList<Item> inv = new ArrayList<Item>();
	
	public String show = "hallo";
	
//	private Item[][] field = new Item[12][10];

	/**
	 * 
	 */
	public Inventar() {
		
//		for (int i = 0; i<=12; i++) {
//			for (int j = 0; j<=10; j++) {
//				field[i][j]=null;
//			}
//		}
		
		inv.clear();
	}
	
	public void addItem(ItemImpl item, boolean done)
	{
		if(done) inv.add(item.getItem());
	}
	
	public void addItem(ItemImpl item){
		
		if(!inv.contains(item.getItem()))
		{
			if (item.getItem().getUID()==3){
				dummy_quest.dolch_gefunden();
			}
		
			inv.add(item.getItem());
		
			DragnDrop dnd = GameMaster.getInstance().getDnd();
		
			Raster raster = dnd.getRaster(0);
		
			Point kaesten = dnd.getRasterSize(item,raster);

			Point p = dnd.findEmpties(raster,(int)kaesten.getX(),(int)kaesten.getY());

			ArrayList<Point> frei  = dnd.istFrei(raster, item, item.getRectangle(),(int)p.getX(),(int)p.getY());
		
			// Dem Item die neuen Koordinaten geben
			Point box = frei.get(0);
		
			Point neu_pos = dnd.getBox(raster, (int)box.getX(), (int)box.getY());
		
			show = neu_pos.toString();
		
			//item.setPos(neu_pos);
		
			// Gegenstand speichern
			Point akt = null;
		
			Iterator<Point> it = frei.iterator();
			while(it.hasNext())
			{
				akt = (Point)it.next();
				raster.getRaster()[(int)akt.getX()][(int)akt.getY()] = item;
			}
		
		}
	}
	
	public void dropItem(String name){
		int i = 0;
		for (Item elem:inv){
			if (elem.getName().equals(name)) {
				inv.remove(i);
				GameMaster.getInstance().print(GameMaster.getInstance().getPlayer().getCharacter().getName() + " hat folgenden Gegenstand verloren: " + name);
				break;
			}
			i++;
		}
		
	}
	public void dropItem(Integer itemID){
		int i = 0;
		for (Item elem:inv){
			if (elem.getUID()==itemID) {
				inv.remove(i);
				GameMaster.getInstance().print(GameMaster.getInstance().getPlayer().getCharacter().getName() + " hat folgenden Gegenstand verloren: " + RessourceManager.getInstance().getItem(itemID));
				break;
			}
			i++;
		}
	}
	
	public List<String> listAll(){
		List<String> temp = new ArrayList<String>();
		for (Item elem:inv){
			temp.add(elem.getName());
		}
		return temp;
	}
	
	public ArrayList<Item> getInv()
	{
		return inv;
	}
	
	public void remItem(Item item)
	{
		if(inv.contains(item)) inv.remove(item);
	}
	
}
