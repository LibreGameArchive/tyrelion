package lavankor.questsystem;

import java.util.ArrayList;

public class QuestLog {
	
	ArrayList<Quest> quests;
	
	Quest activeQuest;

	/** Konstruktor der Klasse */
	public QuestLog() {
		
	quests = new ArrayList<Quest>();
	activeQuest = null;
	
	}
	
	
}
