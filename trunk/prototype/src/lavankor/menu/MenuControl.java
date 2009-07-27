package lavankor.menu;

import lavankor.Main;
import lavankor.control.Control;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuControl extends BasicGameState {
	
	/** Die ID dieses Game States. */
	public static final int ID = 3;
	
	/** Der GameContainer, in dem sich dieser State befindet. */
	private GameContainer gameContainer;
	
	/** Das StateBasedGame bzw. genauer Main, in dem sich dieser State befindet. (+Tastaturbelegung)*/
	private Main mainGame;
	private Control keySet;
	
	private AngelCodeFont font;
	
	private Display display;
	
	private Image hintergrund;
	private Image btn4_pic;
	private Image btn4_over;
	private Image btn4_down;
	
	private String editable = null;
	
	public MenuControl() {
			super();
	}

	public int getID() {
		return ID;
	}
	
	public void keyPressed(int key, char c) {
		if (editable != null) {
			keySet.setKey(editable, key);
			editable = null;
			try { 
				init(gameContainer, mainGame);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		gameContainer = container;
		mainGame = (Main) game;
		keySet = Control.getInstance();
		
		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		
		display = new Display(gameContainer);
		
        hintergrund = new Image("res/images/menu/menu_bg.png");
        
        btn4_pic = new Image("res/images/menu/menu_button_zurueck.jpg");
        btn4_over = new Image("res/images/menu/menu_button_zurueck1.jpg");
        btn4_down = new Image("res/images/menu/menu_button_zurueck2.jpg");
		
		Container content = new Container();
        content.setSize(300, 400);
        content.setLocation(140, 300);

        Button btn3 = new Button(Input.getKeyName(keySet.getKey(Control.FORWARD)));
        btn3.setLocation(250, 60);
        btn3.pack();
        btn3.setWidth(50);
        btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.FORWARD;
			}
        }); 
        content.add(btn3);
        
        Button btn4 = new Button(Input.getKeyName(keySet.getKey(Control.BACK)));
        btn4.setLocation(250, 90);
        btn4.pack();
        btn4.setWidth(50);
        btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.BACK;
			}
        }); 
        content.add(btn4);
        
        Button btn5 = new Button(Input.getKeyName(keySet.getKey(Control.RIGHT)));
        btn5.setLocation(250, 120);
        btn5.pack();
        btn5.setWidth(50);
        btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.RIGHT;
			}
        }); 
        content.add(btn5);
        
        Button btn6 = new Button(Input.getKeyName(keySet.getKey(Control.LEFT)));
        btn6.setLocation(250, 150);
        btn6.pack();
        btn6.setWidth(50);
        btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.LEFT;
			}
        }); 
        content.add(btn6);
        
        Button btn7 = new Button(Input.getKeyName(keySet.getKey(Control.INVENTORY)));
        btn7.setLocation(250, 180);
        btn7.pack();
        btn7.setWidth(50);
        btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.INVENTORY;
			}
        }); 
        content.add(btn7);
        
        Button btn8 = new Button(Input.getKeyName(keySet.getKey(Control.EQUIPMENT)));
        btn8.setLocation(250, 210);
        btn8.pack();
        btn8.setWidth(50);
        btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.EQUIPMENT;
			}
        }); 
        content.add(btn8);
        
        Button btn9 = new Button(Input.getKeyName(keySet.getKey(Control.CHARINFO)));
        btn9.setLocation(250, 240);
        btn9.pack();
        btn9.setWidth(50);
        btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.CHARINFO;
			}
        }); 
        content.add(btn9);
        
        Button btn10 = new Button(Input.getKeyName(keySet.getKey(Control.QUESTLOG)));
        btn10.setLocation(250, 270);
        btn10.pack();
        btn10.setWidth(50);
        btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editable=Control.QUESTLOG;
			}
        }); 
        content.add(btn10);
        
        btn4 = new Button(btn4_pic);
        btn4.setLocation(0,310);
        btn4.setPadding(0);
        btn4.setRolloverImage(btn4_over);
        btn4.setDownImage(btn4_down);
        btn4.pack();
        btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.enterState(1);
			}
        }); 
        content.add(btn4);

        display.add(content);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(hintergrund, 0, 0);
		
		try {
	    	font.drawString(150, 355, Control.FORWARD, Color.black);
			font.drawString(150, 385, Control.BACK, Color.black);
			font.drawString(150, 415, Control.RIGHT, Color.black);
			font.drawString(150, 445, Control.LEFT, Color.black);
			font.drawString(150, 475, Control.INVENTORY, Color.black);
			font.drawString(150, 505, Control.EQUIPMENT, Color.black);
			font.drawString(150, 535, Control.CHARINFO, Color.black);
			font.drawString(150, 565, Control.QUESTLOG, Color.black);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		display.render(container, g);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		display.update(container, delta);
		
	}
}
