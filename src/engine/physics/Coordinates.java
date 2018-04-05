package engine.physics;

/**
 * Stores x and y coordinates for use with the physics engine
 * @author Marcus Oertle
 *
 */
public class Coordinates {
	private double xCor;
	private double yCor;
	
	/**
	 * Construct Coordinates class without parameters, default to 0 for coordinate values
	 */
	public Coordinates() {
		xCor = 0;
		yCor = 0;
	}
	
	/**
	 * Construct Coordinates class with given parameters
	 * @param x
	 * @param y
	 */
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
	
	/**
	 * Sets the x coordinate
	 * @param xC - (double) x coordinate
	 */
	public void setX(double xC) {
		xCor = xC;
	}
	
	/**
	 * Sets the x coordinate
	 * @param yC - (double) x coordinate
	 */
	public void setY(double yC) {
		yCor = yC;
	}
}
