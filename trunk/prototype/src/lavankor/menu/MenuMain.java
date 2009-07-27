package lavankor.menu;



import java.io.IOException;

import lavankor.GameMaster;
import lavankor.ressourcesystem.RessourceManager;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuMain extends BasicGameState {
	
	/** Die ID dieses Game States. */
	public static final int ID = 1;
	
	public GameMaster gameMaster;
	
	/** Der GameContainer, in dem sich dieser State befindet. */
	private GameContainer gameContainer;
	
	/** Die Ressource, die als nächstes geladen werden soll. */
	private DeferredResource nextResource;
	/** True, wenn alle Ressourcen geladen und das Rendering gestartet wurde. */
	private boolean started;
	
	private Display display;
	
	private Image loading;
	private Image hintergrund;
	private Image btn1_pic;
	private Image btn1_over;
	private Image btn1_down;
	private Image btn2_pic;
	private Image btn2_over;
	private Image btn2_down;
	private Image btn3_pic;
	private Image btn3_over;
	private Image btn3_down;
	private Image btn4_pic;
	private Image btn4_over;
	private Image btn4_down;
	private Image btn5_pic;
	private Image btn5_over;
	private Image btn5_down;
	
	public MenuMain() {
			super();
	}

	public int getID() {
		return ID;
	}

	public void init(GameContainer container,final StateBasedGame game) throws SlickException {
		gameContainer = container;
        gameContainer.setMouseCursor("res/images/cursor.gif", 1, 1);
	
		//mainGame.setSoundEffects(new SoundEffects());
		//mainGame.setAnimations(new Animations());
		
		display = new Display(container);
		
		// Deffered Loading aktivieren
		LoadingList.setDeferredLoading(true);
		gameMaster = GameMaster.getInstance();
		
		loading = new Image("res/images/loading.jpg");
        hintergrund = new Image("res/images/menu/menu_bg.png");
        
        btn1_pic = new Image("res/images/menu/menu_button_neues.jpg");
        btn1_over = new Image("res/images/menu/menu_button_neues1.jpg");
        btn1_down = new Image("res/images/menu/menu_button_neues2.jpg");
        btn2_pic = new Image("res/images/menu/menu_button_laden.jpg");
        btn2_over = new Image("res/images/menu/menu_button_laden1.jpg");
        btn2_down = new Image("res/images/menu/menu_button_laden2.jpg");
        btn3_pic = new Image("res/images/menu/menu_button_opt.jpg");
        btn3_over = new Image("res/images/menu/menu_button_opt1.jpg");
        btn3_down = new Image("res/images/menu/menu_button_opt2.jpg");
        btn4_pic = new Image("res/images/menu/menu_button_cred.jpg");
        btn4_over = new Image("res/images/menu/menu_button_cred1.jpg");
        btn4_down = new Image("res/images/menu/menu_button_cred2.jpg");
        btn5_pic = new Image("res/images/menu/menu_button_end.jpg");
        btn5_over = new Image("res/images/menu/menu_button_end1.jpg");
        btn5_down = new Image("res/images/menu/menu_button_end2.jpg");
        
		Container content = new Container();
        content.setSize(300, 400);
        content.setLocation(140, 300);
		
        // Buttons erstellen und positionieren
        Button btn1 = new Button(btn1_pic);
        btn1.setLocation(0, 30);
        btn1.setPadding(0);
        btn1.setRolloverImage(btn1_over);
        btn1.setDownImage(btn1_down);
        btn1.pack();
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RessourceManager.getInstance().getMusicManager().playNavNormal();
				game.enterState(5);
			}
        }); 
        content.add(btn1);
        
        Button btn2 = new Button(btn2_pic);
        btn2.setLocation(0, 100);
        btn2.setPadding(0);
        btn2.setRolloverImage(btn2_over);
        btn2.setDownImage(btn2_down);
        btn2.pack();
        content.add(btn2);
        
        Button btn3 = new Button(btn3_pic);
        btn3.setLocation(0, 170);
        btn3.setPadding(0);
        btn3.setRolloverImage(btn3_over);
        btn3.setDownImage(btn3_down);
        btn3.pack();
        btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.enterState(2);
			}
        }); 
        content.add(btn3);
        
        Button btn4 = new Button(btn4_pic);
        btn4.setLocation(0,240);
        btn4.setPadding(0);
        btn4.setRolloverImage(btn4_over);
        btn4.setDownImage(btn4_down);
        btn4.pack();
        btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        RessourceManager.getInstance().getMusicManager().playCredits();
				game.enterState(4);
			}
        }); 
        content.add(btn4);
        
        Button btn5 = new Button(btn5_pic);
        btn5.setLocation(0, 310);
        btn5.setPadding(0);
        btn5.setRolloverImage(btn5_over);
        btn5.setDownImage(btn5_down);
        btn5.pack();
        btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameContainer.exit();
			}
        }); 
        content.add(btn5);

        display.add(content);  
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (nextResource != null) {
			// Ladebildschirm anzeigen
			g.drawImage(loading, 0, 0);
			g.setColor(new Color(70, 20, 0));
			// Anzeigen, welche Ressource momentan geladen wird
			g.drawString("Loading: "+nextResource.getDescription(), 255, 683);
			
			// Die Dimensionen des Ladebalkens berechnen
			int total = LoadingList.get().getTotalResources();
			int segment = 537/total;
			int loaded = segment * (LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources());
			
			// Den Ladebalken anzeigen
			g.fillRoundRect(244, 704, loaded, 14, 10);
			g.drawRoundRect(244, 704, 537, 14, 10);
		}

		// Wenn alles geladen wurde, den eigentlichen State starten
		if (started) {
			g.clear();
			g.drawImage(hintergrund, 0, 0);
			display.render(container, g);
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// Wenn noch Ressourcen zum Laden vorhanden sind, diese Laden
		if (nextResource != null) {
			try {
				nextResource.load();
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			nextResource = null;
		}
		
		// nextRessource auf die nächste Ressource setzen falls noch welche vorhanden
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			// Es sind keine Ressourcen zum Laden mehr vorhanden
			if (!started) {
				// Eigentlichen Container starten
				started = true;
				RessourceManager.getInstance().getMusicManager().playMenu();
			}
		}
		display.update(container, delta);
	}

}
