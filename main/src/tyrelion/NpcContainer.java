/**
 * 
 */
package tyrelion;

import org.newdawn.slick.Graphics;

/**
 * @author jahudi
 *
 */
public class NpcContainer {
	
	private Npc[][] npcs;
	
	public NpcContainer(int x, int y) {
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

}
