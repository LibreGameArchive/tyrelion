/**
 * 
 */
package tyrelion.loaders;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import tyrelion.objects.Npc;

/**
 * @author jahudi
 *
 */
public class NpcLoader {
	
	private static NpcLoader instance = null;

	Document npcXml;
	Npc[] npcs;
	
	public static NpcLoader getInstance() {
		if (instance == null) {
			instance = new NpcLoader();
		}
		return instance;
	}
	
	public NpcLoader() {
		try {
			npcXml = new SAXBuilder().build("res/xml/npcs.xml");
			makeNpcs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void makeNpcs() throws Exception{
		List<?> childs = npcXml.getRootElement().getChildren();
		npcs = new Npc[childs.size()];
		
		for (int i = 0; i < childs.size(); i++) {
			Element e = (Element) childs.get(i);
			
			int id = e.getAttribute("id").getIntValue();
			String animation = e.getAttribute("animation").getValue();
			List<?> texts = e.getChildren();
			String[] helloText = new String[texts.size()];
			
			for (int j = 0; j < texts.size(); j++) {
				Element elem = (Element) texts.get(j);
				helloText[j] = elem.getTextNormalize();
			}
			npcs[i] = new Npc(id, 0, 0, animation, helloText);
		}
	}
	
	public Npc getNpc(int id) {
		Npc npc = null;
		for (Npc e : npcs) {
			if (id == e.getUid()) {
				npc = e;
				break;
			}
		}
		return npc;
	}
	
}
