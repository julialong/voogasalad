package engine.physics;

/**
 * Kinematics object stores velocities, accelerations, and frictional constants that affect a game entity
 * @author Marcus Oerte
 *
 */
public class Kinematics {
	private double xCor;
	private double yCor;
	private double xVel;
	private double yVel;
	private double xAcc;
	private double yAcc;
	private double gravitionalConstant;
	private double frictionConstant;
	
	/**
	 * Constructs Kinematics object with 0 initial velocity/acceleration
	 */
	public Kinematics() {
		this(0,0,0,0,0,0);
	}
	
	/**
	 * Construct Kinematics object based on given parameters
	 * @param x - x position
	 * @param y - y position
	 * @param xv - x velocity
	 * @param yv - y velocity
	 * @param xa - x acceleration
	 * @param ya - y acceleration
	 */
	public Kinematics(double x, double y, double xv, double yv, double xa, double ya) {
		xCor = x;
		yCor = y;
		xVel = xv;
		yVel = yv;
		xAcc = xa;
		yAcc = ya;
		frictionConstant = 0;
		gravitionalConstant = 9.81*10;
	}
	
	/**
	 * Returns the x coordinate
	 * @return xCor - (double) x coordinate
	 */
	public double getX() {
		return xCor;
	}
	
	/**
	 * Sets the x coordinate
	 * @param xC - (double) x coordinate
	 */
	public void setX(double xC) {
		xCor = xC;
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
	 * @param yC - (double) x coordinate
	 */
	public void setY(double yC) {
		yCor = yC;
	}
	
	/**
	 * Returns the x velocity
	 * @return xVel
	 */
	public double getXVelocity() {
		return xVel;
	}
	
	/**
	 * Sets the x velocity
	 * @param xv
	 */
	public void setXVelocity(double xv) {
		xVel = xv;
	}
	
	/**
	 * Returns the y velocity
	 * @return yVel
	 */
	public double getYVelocity() {
		return yVel;
	}
	
	/**
	 * Sets the y velocity
	 * @param yv
	 */
	public void setYVelocity(double yv) {
		yVel = yv;
	}
	
	/**
	 * Returns the x acceleration
	 * @return xAcc
	 */
	public double getXAcceleration() {
		return xAcc;
	}
	
	/**
	 * Sets the x acceleration
	 * @param xa
	 */
	public void setXAcceleration(double xa) {
		xAcc = xa;
	}
	
	/**
	 * Returns the y acceleration
	 * @return yAcc
	 */
	public double getYAcceleration() {
		return yAcc;
	}
	
	/**
	 * Sets the y acceleration
	 * @param ya
	 */
	public void setYAcceleration(double ya) {
		yAcc = ya;
	}
	
	/**
	 * Returns the frictional constant
	 * @return frictionConstant
	 */
	public double getFrictionConstant() {
		return frictionConstant;
	}
	
	/**
	 * Sets the frictional constant
	 * @param fc
	 */
	public void setFrictionConstant(double fc) {
		frictionConstant = fc;
	}
	
	/**
	 * Returns the gravitational constant
	 * @return gravitionalConstant
	 */
	public double getGravitationalConstant() {
		return gravitionalConstant;
	}
	
	/**
	 * Sets the gravitational constant
	 * @param gc
	 */
	public void setGravitationalConstant(double gc) {
		gravitionalConstant = gc;
	}
}
