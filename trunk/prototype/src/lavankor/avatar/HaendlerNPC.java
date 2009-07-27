package lavankor.avatar;

import lavankor.dialog.Dialog;
import lavankor.gui.Haendler;
import lavankor.map.LavankorMap;
import lavankor.GameMaster;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HaendlerNPC extends NPC {
	
	private Haendler haendler;
	
	public HaendlerNPC(String name, Animation animation, LavankorMap map,
			Dialog dialog, float posX, float posY, float ang) {
		super(name, animation, map, dialog, posX, posY, ang);
		try {
			haendler = new Haendler(GameMaster.getInstance().getPlayer(),"haendler");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void startDialog(GameContainer container, Graphics g){
		haendler.draw(g);
	}
	
	public Haendler getHaendler(){
		return haendler;
	}
	
}
