package lavankor.questsystem;

import lavankor.GameMaster;
import lavankor.ressourcesystem.RessourceManager;

import org.newdawn.slick.Color;

public class dummy_quest {

	public static void dolch_suchen(){
		GameMaster.getInstance().print("Neues Quest: Findet den Dolch!");
		GameMaster.getInstance().getMap().getItemMap().add(RessourceManager.getInstance().getItem(3), 101, 107);
		GameMaster.getInstance().getMap().getNpcMap().get("Galawain").setDialog(RessourceManager.getInstance().getDialog(3));
	}
	
	public static void dolch_gefunden(){
		GameMaster.getInstance().print("Quest Info: Bringt Galawain seinen Dolch!");
		GameMaster.getInstance().getMap().getNpcMap().get("Galawain").setDialog(RessourceManager.getInstance().getDialog(2));
	}
	
	public static void dolch_abgegeben1(){
		GameMaster.getInstance().print("Quest erfüllt!");
		GameMaster.getInstance().getMap().getNpcMap().get("Galawain").setDialog(RessourceManager.getInstance().getDialog(4));
	}
	
	public static void dolch_abgegeben2(){
		GameMaster.getInstance().print("Quest erfüllt!");
		GameMaster.getInstance().getPlayer().getCharacter().getInventory().dropItem(3);
		GameMaster.getInstance().getMap().getNpcMap().get("Galawain").setDialog(RessourceManager.getInstance().getDialog(4));
	}
}
