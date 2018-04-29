package game_player;

/**
 * Class to store a score of a games run
 * 
 * @author Kelley Scroggs
 *
 */
public class ScoreItem {
	private int myScore;
	private String myName;

	/*
	 * Creates a new score item.
	 */
	public ScoreItem(String name, int score) {
		myName = name;
		myScore = score;
	}

	/*
	 * Returns the score in a format for display.
	 */
	public String getString() {
		return myName + ": " + myScore;
	}

	/*
	 * Gets the score of a score item for sorting.
	 */
	public int getScore() {
		return myScore;
	}

	/*
	 * Copys a score item so we can pass a copy of score lists instead of the
	 * orginal list.
	 */
	public ScoreItem copy() {
		if (myName != null) {
			return new ScoreItem(myName, myScore);
		}
		return null;
	}
}
