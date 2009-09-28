/**
 * 
 */
package tyrelion;

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
	
	public void removeNpc() {
		
	}

}
