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
	
	public class InventoryField{
		
		/** Item stored in this inventory field */
		private FieldContent content = null;
		
		private boolean show = true;
		
		public InventoryField(FieldContent content){
			this.content = content;
			
		}
		
		public InventoryField(){
			
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
		
		
		public void toggleShow(){
			show = !show;
		}
		
		public boolean isShow(){
			return show;
		}
		
		public boolean isFull(){
			return !(content.getCount()<5);
		}
		
		public int getCount(){
			return content.getCount();
		}
		
		public void decreaseCountBy(int count){
			content.decreaseCountBy(count);
		}
		
		/** returns the item stored in this field */
		public Item getItem(){
			return content.getItem();
		}
		
		public FieldContent getContent(){
			return content;
		}
		
		public void render(Graphics g, int x, int y){
			g.drawImage(content.getItem().getImage_inv(), x, y);
			if (content.getCount()>1) g.drawString(Integer.toString(content.getCount()), x+30, y+2);
		}
	}
	
	interface FieldContent{
		
		Item item = null;
		
		public Item getItem();
		
		public int getCount();
		
		public void decreaseCountBy(int count);
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
		
		public void decreaseCountBy(int count){
			//
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
		
		public void decreaseCountBy(int count){
			this.count= this.count-count;
			if (count < 1) count = 1;
		}
		
	}
	
	private int invWidth = 4;
	private int invHeight = 6;
	
	private int flyingX = -1;
	private int flyingY = -1;
	
	private InventoryField[][] fields;
	
	public Inventory() throws SlickException{
		fields = new InventoryField[invWidth][invHeight];
		

		
		Food apple = new Food(1233, "Krasser Apfel", new Image("res/img/items/apple_world.png"),
				new Image("res/img/items/apple_inv.png"), true);
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
		for (int j = 0; j < invHeight; j++){
			for (int i = 0; i < invWidth; i++){
				if (fields[i][j]!=null){
					if (fields[i][j].getItem().getUid() == item.getUid()) 
						if (!fields[i][j].isFull()) return new Point(i, j);
				}
			}
		}
		
		return getFreeField();
	}
	
	private Point getFreeField(){
		Point field = null;
		
		for (int j = 0; j < invHeight; j++){
			for (int i = 0; i < invWidth; i++){
				if (fields[i][j] == null) return new Point(i, j);
			}
		}
		
		return field;
	}
	
	public void render(Graphics g, int x, int y){
		for (int j = 0; j < invHeight; j++){
			for (int i = 0; i < invWidth; i++){
				InventoryField field = fields[i][j];
				if (field != null && field.isShow()){
					field.render(g, x+i*57, y+j*55);
				}
			}
		}
	}
	
	public InventoryField getField(int x, int y){
		return fields[x][y];
	}
	
	public InventoryField isOverItem(int x, int y){
		int fieldX = x / 56;
		int fieldY = y / 55;
		
		if (fields[fieldX][fieldY]!=null){
			flyingX=fieldX; flyingY=fieldY;
		}
		return fields[fieldX][fieldY];
	}
	
	public void drop(FieldContent content, int x, int y){
		int fieldX = x / 56;
		int fieldY = y / 55;
		
		if (fields[fieldX][fieldY]!=null){
			if (fields[fieldX][fieldY].getItem().getUid() == content.getItem().getUid()) {
				if (!fields[fieldX][fieldY].isFull()) {
					int stack1 = fields[fieldX][fieldY].getCount();
					int stack2 = fields[flyingX][flyingY].getCount();
					if ((stack1+stack2)>5) {
						fields[fieldX][fieldY] = new InventoryField(new InventoryStack(fields[fieldX][fieldY].getItem(), 5));
						fields[flyingX][flyingY].decreaseCountBy(stack2-stack1);
					} else {
						fields[fieldX][fieldY] = new InventoryField(new InventoryStack(fields[fieldX][fieldY].getItem(), stack1+stack2));
					}
				}
			} else { 
				fields[flyingX][flyingY] = fields[fieldX][fieldY];
				fields[fieldX][fieldY] = new InventoryField(content);
			}
		} else {
			fields[fieldX][fieldY] = new InventoryField(content);
			fields[flyingX][flyingY] = null;
		}
		
	}
	
	
	
}
