/**
 * 
 */
package tyrelion.character;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import tyrelion.TyrelionContainer;
import tyrelion.itemsystem.Item;
import tyrelion.objects.Player;
import tyrelion.objects.WorldItem;
import tyrelion.sfx.SoundManager;

/**
 * @author Basti
 *
 */
public class Inventory {
	
	public class InventoryField{
		
		/** Item stored in this inventory field */
		private FieldContent content = null;
		
		private boolean show = true;
		
		private InventoryField splittedDummy = null;
		
		public InventoryField(FieldContent content){
			this.content = content;
		}
		
		public InventoryField(Item item){
			this.content = new InventoryItem(item);
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
		
		
		public void toggleShow(boolean split){
			if (show){
				if (split && this.getCount()>1) {
					splittedDummy = new InventoryField(new InventoryStack(this.getItem(), this.getCount()-1));
				} else { 
					splittedDummy = null; 
					show = false;
				}
			} else {
				splittedDummy = null;
				show = true;
			}
		}
		
		public void showIt(){
			show = true;
		}
		
		public InventoryField getSplittedDummy(){
			return splittedDummy;
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
		
		public void increaseCountBy(int count){
			if (content instanceof InventoryItem){
				content = new InventoryStack(content.getItem(), 2);
			} else {
				content.increaseCountBy(count);
			}
			
			
		}
		
		/** returns the item stored in this field */
		public Item getItem(){
			return content.getItem();
		}
		
		public FieldContent getContent(){
			return content;
		}
		
		public void render(Graphics g, int x, int y){
			FieldContent renderedContent;
			if (splittedDummy == null) { renderedContent = this.getContent(); } else { renderedContent = splittedDummy.getContent(); }
			g.drawImage(renderedContent.getItem().getImage_inv(), x, y);
			if (renderedContent.getCount()>1) g.drawString(Integer.toString(renderedContent.getCount()), x+30, y+2);
		}
	}
	
	interface FieldContent{
		
		Item item = null;
		
		public Item getItem();
		
		public int getCount();
		
		public void decreaseCountBy(int count);
		public void increaseCountBy(int count);
	}
	
	class InventoryItem implements FieldContent{
		
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
		
		public void increaseCountBy(int count){
			//
		}
		
	}
	
	class InventoryStack implements FieldContent{
		
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
		
		public void increaseCountBy(int count){
			if ((this.count+count) < 6)
				this.count= this.count+count;
		}
		
	}
	
	private int invWidth = 4;
	private int invHeight = 6;
	
	/** the old place of the item, flying with the cursor at the moment */
	private int flyingX = -1;
	
	/** the old place of the item, flying with the cursor at the moment */
	private int flyingY = -1;
	
	private InventoryField[][] fields;
	
	public Inventory() throws SlickException{
		fields = new InventoryField[invWidth][invHeight];
	}
	
	/** adds the item to the the inventory. */
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
	
	/** tries to find a stack, which is not full yet. otherwise returns the next free field. */
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
	
	/** returns the first empty field in the inventory (left to right, top to bottom) */
	private Point getFreeField(){
		Point field = null;
		
		for (int j = 0; j < invHeight; j++){
			for (int i = 0; i < invWidth; i++){
				if (fields[i][j] == null) return new Point(i, j);
			}
		}
		
		return field;
	}
	
	/** renders contained items to the screen */
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
		//calculate inventory field
		int fieldX = x / 56;
		int fieldY = y / 55;
		
		//save source koordinates
		if (fields[fieldX][fieldY]!=null){
			flyingX=fieldX; flyingY=fieldY;
		}
		
		return fields[fieldX][fieldY];
	}
	
	public void drop(FieldContent content, int fieldX, int fieldY, boolean splitted){
		if ((fieldX < 0) || (fieldY < 0)){
			int dropCount = content.getCount();
			
			if (splitted){
				if (fields[flyingX][flyingY].getCount()>1){
					fields[flyingX][flyingY].decreaseCountBy(1);
				} else {
					fields[flyingX][flyingY] = null;
				}
			} else{
				fields[flyingX][flyingY] = null;
			}
			
			for (int i = dropCount; i>0; i=i-1){
				WorldItem droppedItem = new WorldItem(Player.getInstance().getTileX(), Player.getInstance().getTileY(), content.getItem());
				TyrelionContainer.getInstance().getMap().getItems().addItem(droppedItem);
			}
				
			
			
			SoundManager.getInstance().play("ambience", "thunder");
			
		} else {
			
			//not the same field 
			if (fieldX!=flyingX || fieldY!=flyingY){
				if (splitted){
					
					if (fields[fieldX][fieldY]!=null){
						//same item? (if not same, but not stackable it snaps back)
						if (fields[fieldX][fieldY].getItem().getUid() == content.getItem().getUid()) {
							//full stack?
							if (!fields[fieldX][fieldY].isFull()) {
									fields[fieldX][fieldY] = new InventoryField(new InventoryStack(fields[fieldX][fieldY].getItem(), fields[fieldX][fieldY].getCount()+1));
									if (fields[flyingX][flyingY].getCount()>1){
										fields[flyingX][flyingY].decreaseCountBy(1);
									} else {
										fields[flyingX][flyingY] = null;
									}
							}
						}
					} else {
						//just move item
						fields[fieldX][fieldY] = new InventoryField(content);
						if (fields[flyingX][flyingY].getCount()>1){
							fields[flyingX][flyingY].decreaseCountBy(1);
						} else {
							fields[flyingX][flyingY] = null;
						}
					}
					
				} else {
				
					//new field not null
					if (fields[fieldX][fieldY]!=null){
						//same item? (if not same, but not stackable it snaps back)
						if (fields[fieldX][fieldY].getItem().getUid() == content.getItem().getUid()) {
							//full stack?
							if (!fields[fieldX][fieldY].isFull()) {
								//count of the source stack
								int stack1 = fields[fieldX][fieldY].getCount();
								//count of the new stack
								int stack2 = fields[flyingX][flyingY].getCount();
								//fillup the new stack and decrease the old one
								if ((stack1+stack2)>5) {
									fields[fieldX][fieldY] = new InventoryField(new InventoryStack(fields[fieldX][fieldY].getItem(), 5));
									fields[flyingX][flyingY]= new InventoryField(new InventoryStack(fields[fieldX][fieldY].getItem(), stack1+stack2-5));
								} else {
									fields[fieldX][fieldY] = new InventoryField(new InventoryStack(fields[fieldX][fieldY].getItem(), stack1+stack2));
									fields[flyingX][flyingY] = null;
								}
							}
						} else { 
							//flip old and new item
							fields[flyingX][flyingY] = fields[fieldX][fieldY];
							fields[fieldX][fieldY] = new InventoryField(content);
						}
					} else {
						//just move item
						fields[fieldX][fieldY] = new InventoryField(content);
						fields[flyingX][flyingY] = null;
					}
				}	
			}
			
		}
	
	}
	
}
