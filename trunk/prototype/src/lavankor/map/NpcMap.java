package lavankor.map;

import lavankor.avatar.NPC;

public class NpcMap extends LayerMap<NPC>{
	
	
	
	public NpcMap(int x, int y) {
		lengthX = x;
		lengthY = y;
		matrix = new NPC[x][y];
	}
	
	public NPC get(String name) {
		NPC npc = null;
		for (int i = 0; i < lengthX; i++) {
			for (int j = 0; j < lengthY; j++) {
				if (matrix[i][j] != null) {
					if (matrix[i][j].getName().equalsIgnoreCase(name)) {
						npc = matrix[i][j];
						break;
					}
				}
			}
		}
		return npc;
	}
	
	public boolean moving(int x, int y) {
		return matrix[x][y].getNpcMover().isMoving();
	}
	
	public int getLenghtX() {
		return lengthX;
	}
	
	public int getLenghtY() {
		return lengthY;
	}
	
	public void hideNames(){
		for (int i = 0; i < lengthX; i++) {
			for (int j = 0; j < lengthY; j++) {
				if (matrix[i][j] != null) {
					matrix[i][j].hideName();
				}
			}
		}
	}
	
}
