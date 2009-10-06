/**
 * 
 */
package tyrelion.gui;

/**
 * @author daennart
 *
 */
public class Message {
	
	public static int SYSTEM = 1;
	public static int QUEST = 2;
	public static int FIGHT = 3;
	public static int ITEM = 4;
	public static int EXPERIENCE = 5;
	public static int MISC = 6;
	
	private String text;
	
	private int category;
	
	public Message(String text, int category){
		
		this.text = text;
		this.category = category;
		
	}
	
	public String getText(){
		return text;
	}
	
	public int getCategory(){
		return category;
	}

}
