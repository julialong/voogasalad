package engine.physics;

/**
 * Performs gravitational and pseudo-frictional physics calculations
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class Physics {
    private static final double FRAME_RATE = 60;
	private static final double TIME_STEP = 1/FRAME_RATE;
    private double gravitationalConstant;
	
    /**
     * Constructor without inputs, sets gravity to default
     */
	public Physics() {
		gravitationalConstant = 9.81;
	}
	
	/**
	 * Constructor with input to set gravitational constant
	 * @param gravConstant - the gravitional constant (default 9.81)
	 */
	public Physics(double gravConstant) {
		gravitationalConstant = gravConstant;
	}
	
	/**
	 * Apply physics to a Kinematics object
	 * @param k - a Kinematics object
	 * @return new Kinematics()
	 */
    public Kinematics applyPhysics(Kinematics k){
        double yFinalVelocity = applyGravity(k.getYVelocity());
        double xFinalVelocity = applyFriction(k);
        return new Kinematics(xFinalVelocity, yFinalVelocity, k.getXAcceleration(), k.getYAcceleration());
    }
    
    /**
     * Applies gravity to the Kinematics object
     * @param yVelocity
     * @return (double) updated y velocity
     */
	private double applyGravity(double yVelocity){
        return yVelocity - gravitationalConstant * TIME_STEP;
    }
    
	/**
	 * Applies pseudo-friction calculations to the Kinematics object
	 * @param k
	 * @return (double) updated x velocity
	 */
    private double applyFriction(Kinematics k) {
    	double nextXVel = k.getXVelocity() + k.getXAcceleration()*TIME_STEP;
    	double xFinalVelocity = nextXVel;
        if(nextXVel > 0) {
        	xFinalVelocity = nextXVel - k.getFrictionConstant()*TIME_STEP;
        }
        else if (nextXVel < 0) {
        	xFinalVelocity = nextXVel + k.getFrictionConstant()*TIME_STEP;
        }
        if(((nextXVel > 0) && (xFinalVelocity < 0)) || ((nextXVel < 0) && (xFinalVelocity > 0))) {
        	return 0;
        }
        return xFinalVelocity;
    }
}