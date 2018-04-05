package engine.physics;

public class Physics {
	double gravitationalConstant;
	
	public Physics() {
		gravitationalConstant = 9.81;
	}
	
	public Physics(double gravConstant) {
		gravitationalConstant = gravConstant;
	}
	
	public Coordinates moveX(Coordinates coords, double velocity, double frictionalConstant) {
		double x = coords.getX();
		
		return new Coordinates(xFinal, yFinal);
	}
	
	public Coordinates moveX(Coordinates coords, double velocity, double frictionalConstant) {
		double y = coords.getY();
		
		return new Coordinates(xFinal, yFinal);
	}
}
