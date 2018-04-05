package engine.physics;

/**
 * Stores x and y coordinates for use with the physics engine
 * @author Marcus Oertle
 *
 */
public class Coordinates {
	private double xCor;
	private double yCor;
	
	public Coordinates(double x, double y) {
		xCor = x;
		yCor = y;
	}
	
	/**
	 * Returns the x coordinate
	 * @return xCor - (double) x coordinate
	 */
	public double getX() {
		return xCor;
	}
	
	/**
	 * Returns the y coordinate
	 * @return yCor - (double) y coordinate
	 */
	public double getY() {
		return yCor;
	}
}
