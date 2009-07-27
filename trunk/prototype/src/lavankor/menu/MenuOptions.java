package lavankor.menu;

import lavankor.Main;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuOptions extends BasicGameState {
	
	/** Die ID dieses Game States. */
	public static final int ID = 2;
	
	/** Der GameContainer, in dem sich dieser State befindet. */
	private GameContainer gameContainer;
	
	/** Das StateBasedGame bzw. genauer Main, in dem sich dieser State befindet. */
	private Main mainGame;
	
	private Display display;
	
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
	private Image btn6_pic;
	private Image btn6_over;
	private Image btn6_down;
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	
	public MenuOptions() {
			super();
	}

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		gameContainer = container;
		mainGame = (Main) game;
		display = new Display(gameContainer);
		
        hintergrund = new Image("res/images/menu/menu_bg.png");
        
        if (RessourceManager.getInstance().getMusicManager().getActive() == null) {       
        	btn1_pic = new Image("res/images/menu/menu_button_klein_musik_an2.jpg");
        	btn1_over = new Image("res/images/menu/menu_button_klein_musik_an2.jpg");
        	btn2_pic = new Image("res/images/menu/menu_button_klein_musik_aus.jpg");
        	btn2_over = new Image("res/images/menu/menu_button_klein_musik_aus1.jpg");	
        } else {
        	btn1_pic = new Image("res/images/menu/menu_button_klein_musik_an2.jpg");
            btn1_over = new Image("res/images/menu/menu_button_klein_musik_an2.jpg");
            btn2_pic = new Image("res/images/menu/menu_button_klein_musik_aus.jpg");
            btn2_over = new Image("res/images/menu/menu_button_klein_musik_aus1.jpg");
        }
        
        btn1_down = new Image("res/images/menu/menu_button_klein_musik_an2.jpg");
        btn2_down = new Image("res/images/menu/menu_button_klein_musik_aus2.jpg");
        btn3_pic = new Image("res/images/menu/menu_button_steuerung.jpg");
        btn3_over = new Image("res/images/menu/menu_button_steuerung1.jpg");
        btn3_down = new Image("res/images/menu/menu_button_steuerung2.jpg");
        btn4_pic = new Image("res/images/menu/menu_button_zurueck.jpg");
        btn4_over = new Image("res/images/menu/menu_button_zurueck1.jpg");
        btn4_down = new Image("res/images/menu/menu_button_zurueck2.jpg");
        btn5_pic = new Image("res/images/menu/menu_button_plus.jpg");
        btn5_over = new Image("res/images/menu/menu_button_plus1.jpg");
        btn5_down = new Image("res/images/menu/menu_button_plus2.jpg");
        btn6_pic = new Image("res/images/menu/menu_button_minus.jpg");
        btn6_over = new Image("res/images/menu/menu_button_minus1.jpg");
        btn6_down = new Image("res/images/menu/menu_button_minus2.jpg");
		
		Container content = new Container();
        content.setSize(300, 400);
        content.setLocation(140, 300);
  
        // Buttons erstellen und positionieren
        btn1 = new Button(btn1_pic);  
        btn1.setLocation(0, 30);
        btn1.setPadding(0);
        btn1.setRolloverImage(btn1_over);
        btn1.setDownImage(btn1_down);
        btn1.pack();
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RessourceManager.getInstance().getMusicManager().getActive().loop();
				 musikAn();
			}
        });
        content.add(btn1);
        
        btn2 = new Button(btn2_pic);
        btn2.setLocation(150, 30);
        btn2.setPadding(0);
        btn2.setRolloverImage(btn2_over);
        btn2.setDownImage(btn2_down);
        btn2.pack();
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RessourceManager.getInstance().getMusicManager().getActive().stop();
				musikAus();
			}
        }); 
        content.add(btn2);
        
        btn3 = new Button(btn3_pic);
        btn3.setLocation(0, 170);
        btn3.setPadding(0);
        btn3.setRolloverImage(btn3_over);
        btn3.setDownImage(btn3_down);
        btn3.pack();
        btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.enterState(3);
			}
        });
        content.add(btn3);
        
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
        
        btn5 = new Button(btn5_pic);
        btn5.setLocation(220,70);
        btn5.setPadding(0);
        btn5.setRolloverImage(btn5_over);
        btn5.setDownImage(btn5_down);
        btn5.pack();
        btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RessourceManager.getInstance().getMusicManager().getActive().setVolume(new Float(RessourceManager.getInstance().getMusicManager().getActive().getVolume()+0.1));
			}
        }); 
        content.add(btn5);
        
        btn6 = new Button(btn6_pic);
        btn6.setLocation(200,70);
        btn6.setPadding(0);
        btn6.setRolloverImage(btn6_over);
        btn6.setDownImage(btn6_down);
        btn6.pack();
        btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (RessourceManager.getInstance().getMusicManager().getActive().getVolume() >= 0)
					RessourceManager.getInstance().getMusicManager().getActive().setVolume(new Float(RessourceManager.getInstance().getMusicManager().getActive().getVolume()-0.1));
			}
        }); 
        content.add(btn6);

        display.add(content);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(hintergrund, 0, 0);

		g.setFont(RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK));
		g.setColor(Color.red);
		g.drawString("Musiklautstaerke:",170, 360);

		display.render(container, g);
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		display.update(container, delta);
		
	}

	public void musikAn() {
		try { 
			btn1.setImage(new Image("res/images/menu/menu_button_klein_musik_an2.jpg"));
	        btn1.setRolloverImage(new Image("res/images/menu/menu_button_klein_musik_an2.jpg"));
	        btn2.setImage(new Image("res/images/menu/menu_button_klein_musik_aus.jpg"));
	        btn2.setRolloverImage(new Image("res/images/menu/menu_button_klein_musik_aus1.jpg"));
        } catch (SlickException e) { 
            e.printStackTrace(); 
        }
	}
	
	public void musikAus() {
		try { 
			btn1.setImage(new Image("res/images/menu/menu_button_klein_musik_an.jpg"));
	        btn1.setRolloverImage(new Image("res/images/menu/menu_button_klein_musik_an1.jpg"));
	        btn2.setImage(new Image("res/images/menu/menu_button_klein_musik_aus2.jpg"));
	        btn2.setRolloverImage(new Image("res/images/menu/menu_button_klein_musik_aus2.jpg"));
        } catch (SlickException e) { 
            e.printStackTrace(); 
        } 
	}
	
	public void keyPressed(int key, char c) {
		if (key==Input.KEY_ESCAPE) { 
			mainGame.enterState(2); 
		}
	}
}
