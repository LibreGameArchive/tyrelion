/**
 * 
 */
package tyrelion.map;

import org.newdawn.slick.Graphics;

import tyrelion.objects.Npc;

/**
 * @author jahudi
 *
 */
public class NpcMap {
	
	private Npc[][] npcs;
	
	public NpcMap(int x, int y) {
		npcs = new Npc[x][y];
	}
	
	public void addNpc(Npc npc) {
		npcs[npc.getTileX()][npc.getTileY()] = npc;
	}
	
	public void removeNpc(Npc npc) {
		npcs[npc.getTileX()][npc.getTileY()] = null;
	}
	
	public void removeNpc(int x, int y) {
		npcs[x][y] = null;
	}
	
	public Npc getNpc(int x, int y) {
		return npcs[x][y];
	}
	
	public void drawNpcs(int startX, int startY, int endX, int endY, Graphics g) {
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (getNpc(i, j) != null) {
					getNpc(i, j).render(g);
				}
			}
		}
	}
	
	public void updateNpcs(int startX, int startY, int endX, int endY) {
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (getNpc(i, j) != null) {
					getNpc(i, j).update();
				}
			}
		}
	}
	
	public void drawNpcBubbles(int startX, int startY, int endX, int endY, Graphics g) {
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (getNpc(i, j) != null) {
					getNpc(i, j).drawBubble(g);
				}
			}
		}
	}

}
