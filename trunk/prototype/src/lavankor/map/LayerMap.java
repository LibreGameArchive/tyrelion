package lavankor.map;

public abstract class LayerMap<Type> {
	
	protected int lengthX;
	protected int lengthY;
	
	protected Type[][] matrix;
	
	public void add(Type object, int x, int y) {
		matrix[x][y] = object;
	}
	
	public Type get(int x, int y) {
		return matrix[x][y];
	}
	
	public void remove(int x, int y) {
		matrix[x][y] = null;
	}
	
	
	
	

}
