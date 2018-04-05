package engine.physics;

/**
 * Kinematics object stores velocities, accelerations, and frictional constants that affect a game entity
 * @author Marcus Oerte
 *
 */
public class Kinematics {
	private double xVel;
	private double yVel;
	private double xAcc;
	private double yAcc;
	private double frictionConstant = 10;
	
	/**
	 * Constructs Kinematics object with 0 initial velocity/acceleration
	 */
	public Kinematics() {
		xVel = 0;
		yVel = 0;
		xAcc = 0;
		yAcc = 0;
	}
	
	/**
	 * Construct Kinematics object based on given parameters
	 * @param xv - x velocity
	 * @param yv - y velocity
	 * @param xa - x acceleration
	 * @param ya - y acceleration
	 */
	public Kinematics(double xv, double yv, double xa, double ya) {
		xVel = xv;
		yVel = yv;
		xAcc = xa;
		yAcc = ya;
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
}
