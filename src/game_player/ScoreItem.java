package game_player;

public class ScoreItem {
	private int myScore;
	private String myName;
	
	public ScoreItem(String name, int score) {
		myName = name;
		myScore = score;
	}
	
	public String getString() {
		return myName + ": " + myScore; 
	}
	
	public int getScore() {
		return myScore;
	}
	
	public ScoreItem copy() {
		if(myName != null) {
			return new ScoreItem(myName, myScore);
		}
		return null;
	}
}
