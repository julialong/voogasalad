package engine.physics;

public class Kinematics {
	private double xVel;
	private double yVel;
	private double xAcc;
	private double yAcc;
	
	public Kinematics(double xv, double yv, double xa, double ya) {
		xVel = xv;
		yVel = yv;
		xAcc = xa;
		yAcc = ya;
	}
	
	public double getXVelocity() {
		return xVel;
	}
	
	public double getYVelocity() {
		return yVel;
	}
	
	public double getXAcceleration() {
		return xAcc;
	}
	
	public double getYAcceleration() {
		return yAcc;
	}
}
