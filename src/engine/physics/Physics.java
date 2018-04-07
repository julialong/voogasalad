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
	 * @param gravApplies - boolean determining whether to apply gravity
	 * @return new Kinematics()
	 */
	public Kinematics applyPhysics(Kinematics k, boolean gravApplies){
		double xAcc = k.getXAcceleration();
        if(k.getXVelocity() != 0){
            xAcc -= k.getFrictionConstant();
        }
		double yAcc = k.getYAcceleration();
		if(gravApplies){
			yAcc += -gravitationalConstant;
		}
		double xFinalVelocity = k.getXVelocity() + xAcc * TIME_STEP;
		double yFinalVelocity = k.getYVelocity() + yAcc * TIME_STEP;
		double xFinalPos = k.getX() + k.getXVelocity() * TIME_STEP + 0.5 * xAcc * TIME_STEP * TIME_STEP;
		double yFinalPos = k.getY() + k.getYVelocity() * TIME_STEP + 0.5 * yAcc * TIME_STEP * TIME_STEP;
		if(k.getXAcceleration() == 0){
			if(((k.getXVelocity() > 0) && (xFinalVelocity < 0)) || ((k.getXVelocity() < 0) && (xFinalVelocity > 0))) {
				xFinalVelocity = 0;
				xFinalPos = k.getX();
			}
		}
        return new Kinematics(xFinalPos, yFinalPos, xFinalVelocity, yFinalVelocity, k.getXAcceleration(), k.getYAcceleration());
	}
}