package engine.physics;

public class PhysicsTest {
	private static double seconds = 10;

	public static void main (String[] args) {
		Kinematics k = new Kinematics(0, 0.01, 0, 20, 0, 0);
		Physics p = new Physics();

		for(int i = 0; i < seconds*60; i++) {
			k = p.applyPhysics(k, true, true);
			if(k.getY() < 0) {
				k.setY(0.01);
			}
			if(i % 15 == 0) {
				System.out.println(i);
				System.out.println("X Pos: "+ k.getX());
				System.out.println("Y Pos: "+ k.getY());
				System.out.println("X Vel: "+ k.getXVelocity());
				System.out.println("Y Vel: "+ k.getYVelocity());
				System.out.println("");
			}
		}
	}
}