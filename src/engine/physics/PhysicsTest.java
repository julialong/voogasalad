package engine.physics;

public class PhysicsTest {
    private static double seconds = 10;

	public static void main (String[] args) {
		Kinematics k = new Kinematics(0,0,10,0);
        Physics p = new Physics();
        
        for(int i = 0; i < seconds*60; i++) {
        	 k = p.applyPhysics(k);
        	 System.out.println(i);
             System.out.println("X Vel: "+k.getXVelocity());
             System.out.println("Y Vel: "+k.getYVelocity());
             System.out.println("");
        }
	}
}