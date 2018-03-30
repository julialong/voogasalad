package api.engine

public interface Movement {
	/**
	* Defines the logic for where something will move, given its heading, the distance to be moved, and its velocity.
	*/
	public void move(double distance, double heading, double velocity);
}