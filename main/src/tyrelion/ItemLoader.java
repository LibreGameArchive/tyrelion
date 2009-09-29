/**
 * 
 */
package tyrelion;

import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.newdawn.slick.Image;

import tyrelion.itemsystem.Food;
import tyrelion.itemsystem.Item;
import tyrelion.itemsystem.Weapon;

/**
 * @author jahudi
 *
 */
public class ItemLoader {
	
	Document itemsXml;
	Item[] items;
	
	public ItemLoader(String filename) {
		try {
			itemsXml = new SAXBuilder().build(filename);
			makeItems();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Item[] makeItems() throws Exception {
		List<?> childs = itemsXml.getRootElement().getChildren();
		items = new Item[childs.size()];
		
		for (int i = 0; i < childs.size(); i++) {
			Element e = (Element) childs.get(i);
			Item item = null;
			
			int id = e.getAttribute("id").getIntValue();
			String name = e.getAttribute("name").getValue();
			String type = e.getAttribute("class").getValue();
			String imageWorld = e.getChild("imageWorld").getTextNormalize();
			String imageInv = e.getChild("imageInv").getTextNormalize();
			String stackableStr = e.getChild("stackable").getTextNormalize();
			
			boolean stackable;
			
			if ("true".equals(stackableStr)) {
				stackable = true;
			} else {
				stackable = false;
			}
			
			if ("food".equals(type)) {
				item = new Food(id, name, new Image(imageWorld), new Image(imageInv), stackable);
			} else if ("weapon".equals(type)) {
				item = new Weapon(id, name, new Image(imageWorld), new Image(imageInv), stackable);
			}
			items[i] = item;
		}
		return items;
	}
	
	public Item getItem(int id) {
		Item item = null;
		for (Item e : items) {
			if (id == e.getUid()) {
				item = e;
				break;
			}
		}
		return item;
	}

}
