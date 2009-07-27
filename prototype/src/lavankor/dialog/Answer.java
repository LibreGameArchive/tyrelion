package lavankor.dialog;

import lavankor.GameMaster;
import lavankor.avatar.Player;
import lavankor.character.Astralenergie;
import lavankor.character.Attribut;
import lavankor.character.Fertigkeit;
import lavankor.character.Lebenskraft;
import lavankor.dicesystem.Dicerolls;
import lavankor.questsystem.dummy_quest;
import lavankor.ressourcesystem.RessourceManager;
import lavankor.ressourcesystem.RessourceStrings;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;


/** Diese Klasse repräsentiert eine Antwort in einem Dialog.
 * Sie weiß welche Fähigkeit sie benötigt und welche Auswirkung sie hat.
 * @author diceware
 * @version 0.1a
 */
public class Answer {
	
	
	/** Antwort in Textform. */
	private String text;
	
	/** Folgedialog als ID*/
	private int followUp = 0;
	
	/** ID des Items das zugefügt wird */
	private int itemIn = 0;
	
	/** ID des Items das entfernt wird */
	private int itemOut = 0;
	
	/** Attribut ändern - Attribut Name */
	private String changeAtt_name = null;
	/** Attribut ändern - Änderungswert */
	private int changeAtt_amount = 0;
	
	/** Fertigkeit ändern - Fertigkeit Name */
	private String changeFert_name = null;
	/** Fertigkeit ändern - Änderungswert */
	private int changeFert_amount = 0;
	
	/** LE ändern - LE Name */
	private int changeLE_amount = 0;
	
	/** AE ändern - AE Name */
	private int changeAE_amount = 0;
	
	
	/** Fertigkeit die bestanden werden muss um die Antwort zu geben. */
	private String challenge;
	
	/** Indikator für geschaffte Probe. */
	private boolean challenge_passed;
	
	private String quest_info;
	
	
	
	/**
	 * @param text
	 * @param followUp
	 * @param itemIn
	 * @param itemOut
	 * @param changeAtt_name
	 * @param changeAtt_amount
	 * @param changeFert_name
	 * @param changeFert_amount
	 * @param changeLE_amount
	 * @param changeAE_amount
	 * @param challenge
	 */
	public Answer(String text, int followUp, int itemIn, int itemOut,
			String changeAtt_name, int changeAtt_amount,
			String changeFert_name, int changeFert_amount, int changeLE_amount,
			int changeAE_amount, String challenge, String quest_info) {
		this.text = text;
		this.followUp = followUp;
		this.itemIn = itemIn;
		this.itemOut = itemOut;
		this.changeAtt_name = changeAtt_name;
		this.changeAtt_amount = changeAtt_amount;
		this.changeFert_name = changeFert_name;
		this.changeFert_amount = changeFert_amount;
		this.changeLE_amount = changeLE_amount;
		this.changeAE_amount = changeAE_amount;
		this.challenge = challenge;
		this.quest_info = quest_info;
	}

	public void draw(Graphics g, Float x, Float y){
		
		Font font = RessourceManager.getInstance().getFont(RessourceStrings.FONT_24_BLACK);
		
		String answer = text;
		
		if (challenge!=null) { answer += "  ( " + " )"; }
		
		font.drawString(x, y, answer);
		
	}
	
	public void press() {
		
		GameMaster gamemaster = GameMaster.getInstance();
		
		Player player = gamemaster.getPlayer();
		
		challenge_passed = true;
		
		if (!quest_info.equals(" ")){
			if (quest_info.equals("dolch_suchen")){
				dummy_quest.dolch_suchen();
			}
			if (quest_info.equals("dolch_abgegeben_1")){
				dummy_quest.dolch_abgegeben1();
			}
			if (quest_info.equals("dolch_abgegeben_2")){
				dummy_quest.dolch_abgegeben2();
			}
		}
	
		if (challenge!=null) {
			int roll = Dicerolls.roll(player.getCharacter(), challenge, null, 10);
			int value = 0; 
			if (player.getCharacter().getAttribute().getAttribute().containsKey(challenge)) {
				value = player.getCharacter().getAttribute().getWert(challenge);
			} else {
				if (player.getCharacter().getAbgAttribute().getAbgAttribute().containsKey(challenge)){
					value = player.getCharacter().getAbgAttribute().getWert(challenge);
				} else {
					if (player.getCharacter().getFertigkeiten().getFertigkeiten().containsKey(challenge)){
						value = player.getCharacter().getFertigkeiten().getWert(challenge);
					}
				}
			}
			
			if (roll>=0){
				gamemaster.print("Probe auf " + challenge + " ( mit Wert " + value + " ) bestanden.");
				gamemaster.print("( Probenergebnis: "+ roll + (roll==1?" Punkt":" Punkte") + " uebrig )");
				challenge_passed = true;
			} else { 
				gamemaster.print("Probe auf " + challenge + " ( mit Wert " + value + " ) verhauen. ");
				gamemaster.print("( Probenergebnis: "+ -roll + (roll==-1?" Punkt":" Punkte") + " malus )");
				challenge_passed = false; }
		}
		
		if (challenge_passed){
		if (followUp!=0) {
			gamemaster.setActiveDialog(RessourceManager.getInstance().getDialog(followUp));
			} else {
			gamemaster.getActiveDialog().setShowDialog(false);
		}
		
		if (itemIn!=0) {
			gamemaster.getPlayer().getCharacter().getInventory().addItem(RessourceManager.getInstance().getItem(itemIn).copy().getItem_Impl());
			gamemaster.print(gamemaster.getPlayer().getCharacter().getName() + " hat folgenden Gegenstand erhalten: "
						+ RessourceManager.getInstance().getItem(itemIn).getName());
		}
		
		if (itemOut!=0) {
			gamemaster.getPlayer().getCharacter().getInventory().dropItem(RessourceManager.getInstance().getItem(itemOut).getName());
			gamemaster.print(gamemaster.getPlayer().getCharacter().getName() + " hat folgenden Gegenstand verloren: "
					+ RessourceManager.getInstance().getItem(itemOut).getName());
		}
		
		Attribut att = null;
		if (changeAtt_name!=null) {
			att = GameMaster.getInstance().getPlayer().getCharacter().getAttribute().get(changeAtt_name);
		}
		if (changeAtt_amount!=0) {
			att.setWert(changeAtt_amount);
		}
		
		Fertigkeit fert = null;
		if (changeFert_name!=null) {
			fert = GameMaster.getInstance().getPlayer().getCharacter().getFertigkeiten().get(changeFert_name);
		}
		if (changeFert_amount!=0) {
			fert.setWert(changeFert_amount);
		}
		
		if (changeLE_amount!=0) {
			Lebenskraft le = GameMaster.getInstance().getPlayer().getCharacter().getLe();
			if (changeLE_amount<0) { le.down(changeLE_amount); } else { le.up(changeLE_amount); }
		}
		
		if (changeAE_amount!=0) {
			Astralenergie ae = GameMaster.getInstance().getPlayer().getCharacter().getAe();
			if (changeAE_amount<0) { ae.down(changeAE_amount); } else { ae.up(changeAE_amount); }
		}
		
		}
		
		
		
		
	}
	
	public String toString(){
		
		String answer = text;
		
		if (challenge!=null) { answer += "[ " + challenge + " ]"; }
		
		return answer;
	}

	public String getText() {
		return text;
	}

	


	public void setText(String text) {
		this.text = text;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	
	
}
