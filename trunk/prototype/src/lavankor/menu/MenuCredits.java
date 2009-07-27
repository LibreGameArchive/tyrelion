package lavankor.menu;



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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuCredits extends BasicGameState {
	
	/** Die ID dieses Game States. */
	public static final int ID = 4;
	
	/** Der GameContainer, in dem sich dieser State befindet. */
	private GameContainer gameContainer;
	
	private AngelCodeFont font;
	
	private Display display;
	
	public Image hintergrund;
	public Image btn1_pic;
	public Image btn1_over;
	public Image btn1_down;
	
	public MenuCredits() {
			super();
	}

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		gameContainer = container;
		
		display = new Display(gameContainer);
		
		font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		
        hintergrund = new Image("res/images/menu/menu_bg.png");
        
        btn1_pic = new Image("res/images/menu/menu_button_zurueck.jpg");
        btn1_over = new Image("res/images/menu/menu_button_zurueck1.jpg");
        btn1_down = new Image("res/images/menu/menu_button_zurueck2.jpg");
		
		Container content = new Container();
        content.setSize(300, 400);
        content.setLocation(140, 300);
        
        // Buttons erstellen und positionieren
        Button btn1 = new Button(btn1_pic);
        btn1.setLocation(0,310);
        btn1.setPadding(0);
        btn1.setRolloverImage(btn1_over);
        btn1.setDownImage(btn1_down);
        btn1.pack();
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RessourceManager.getInstance().getMusicManager().playMenu();
				game.enterState(1);
			}
        }); 
        content.add(btn1);

        display.add(content);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(hintergrund, 0, 0);

		try {
			AngelCodeFont font32 = RessourceManager.getInstance().getFont(RessourceStrings.FONT_32_BLACK);
	    	
	    	font32.drawString(190, 300, "Diceware Team", Color.gray);
			font.drawString(100, 340, "Sebastian Daennart - Developer Producer", Color.black);
			font.drawString(130, 370, "Thomas Irmscher - Lead Engineer", Color.black);
			font.drawString(160, 400, "Gerrit Kahn - Lead Designer", Color.black);
			font32.drawString(160, 450, "Regeln und Welt von", Color.gray);
			font.drawString(230, 490, "Oliver Riske", Color.black);
			font32.drawString(225, 530, "Musik von", Color.gray);
			font.drawString(235, 570, "Erdenstern", Color.black);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		display.render(container, g);
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		display.update(container, delta);
		
	}

	
}
