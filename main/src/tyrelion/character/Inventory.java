/**
 * 
 */
package tyrelion.character;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import tyrelion.itemsystem.Food;
import tyrelion.itemsystem.Item;

/**
 * @author Basti
 *
 */
public class Inventory {
	
	class InventoryField{
		
		/** Item stored in this inventory field */
		private FieldContent content;
		
		public InventoryField(FieldContent content){
			this.content = content;
			
		}
		
		/** puts an item into this field */
		public FieldContent put(Item item){
			FieldContent old_field = this.content;
			
			if (item.getUid() == this.content.getItem().getUid()) {
				if (content.getItem().isStackable()){ 
					this.content = new InventoryStack(content.getItem(), content.getCount()+1);
					return null;
				} else {this.content = new InventoryItem(item); return old_field;}
			} else {
				this.content = new InventoryItem(item); return old_field;
			}
		}
		
		/** puts intern content into this field */
		public FieldContent put(FieldContent item){
			FieldContent old_field = this.content;
			
			if (item.getItem().getUid() == this.content.getItem().getUid()) {
				if (content.getItem().isStackable()){ 
					this.content = new InventoryStack(content.getItem(), content.getCount()+1);
					return null;
				} else {this.content = item; return old_field;}
			} else {
				this.content = item; return old_field;
			}
		}
		
		/** returns the item stored in this field */
		public Item getItem(){
			return content.getItem();
		}
		
	}
	
	interface FieldContent{
		
		Item item = null;
		
		public Item getItem();
		
		public int getCount();
		
		
	}
	
	private class InventoryItem implements FieldContent{
		
		private Item item;
		
		public InventoryItem(Item item){
			this.item = item;
		}
		
		public Item getItem(){
			return item;
		}
		
		public int getCount(){
			return 1;
		}
		
	}
	
	private class InventoryStack implements FieldContent{
		
		private Item item;
		private int count;
		
		public InventoryStack(Item item, int count){
			this.item = item;
			this.count = count;
		}
		
		public Item getItem(){
			return item;
		}
		
		public int getCount(){
			return count;
		}
		
	}
	
	private int invWidth = 4;
	private int invHeight = 6;
	
	private InventoryField[][] fields;
	
	public Inventory() throws SlickException{
		fields = new InventoryField[invWidth][invHeight];
		

		
		Food apple = new Food(1233, "Krasser Apfel", new Image("res/img/items/apple_world.png"),
				new Image("res/img/items/apple_inv.png"), true);
		
		fields[0][0] = new InventoryField(new InventoryItem(apple));
		fields[1][1] = new InventoryField(new InventoryItem(apple));
		fields[1][2] = new InventoryField(new InventoryItem(apple));
	}
	
	public boolean addItem(Item item){
		
		Point freeField; 
		
		if (item.isStackable()) {freeField = getPossibleField(item);} else freeField = getFreeField();
		
		
		if (freeField == null) {
			return false;
		} else {
			if (fields[freeField.x][freeField.y] != null){
				fields[freeField.x][freeField.y].put(item);
			} else {
				fields[freeField.x][freeField.y] = new InventoryField(new InventoryItem(item));
			}
			return true;
		}
		
	}
	
	private Point getPossibleField(Item item){
		for (int i = 0; i < invWidth; i++){
			for (int j = 0; j < invWidth; j++){
				if (fields[i][j]!=null){
					if (fields[i][j].getItem() == item) return new Point(i, j);
				}
			}
		}
		
		return getFreeField();
	}
	
	private Point getFreeField(){
		Point field = null;
		
		for (int i = 0; i < invWidth; i++){
			for (int j = 0; j < invWidth; j++){
				if (fields[i][j] == null) return new Point(i, j);
			}
		}
		
		return field;
	}
	
	public void render(Graphics g, int x, int y){
		
		
		
		for (int i = 0; i < invWidth; i++){
			for (int j = 0; j < invWidth; j++){
				InventoryField field = fields[i][j];
				if (field != null){
					g.drawImage(field.getItem().getImage_inv(), x+i*50, y+j*50);
				}
			}
		}
	}
	
	
	
	
	
}
