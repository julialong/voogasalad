package engine.behavior;

import engine.controls.Action;
import engine.controls.MoveLeft;
import engine.controls.MoveRight;
import engine.entity.GameEntity;
import engine.movement.Flying;
import engine.movement.LinearFlying;
import engine.physics.Kinematics;
import engine.physics.Physics;

/**
 * Behavior implementation that causes entity to reverse direction when it reaches the edge of an object it is standing on
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class MoveBetweenThresholds implements Behavior{
	private Action leftwards = new MoveLeft();
	private Action rightwards = new MoveRight();
	private boolean goingLeft = true;
	private double thresholdLeft;
	private double thresholdRight;
	
	public MoveBetweenThresholds(double thresholdLeft, double thresholdRight){
		this.thresholdLeft = thresholdLeft;
		//System.out.println("THL: " + thresholdLeft);
		this.thresholdRight = thresholdRight;
		//System.out.println("THR: " + thresholdRight);
	}
	
	@Override
	public void update(GameEntity entity) {
		// TODO Auto-generated method stub
		Physics lookaheadPhysics = new Physics();
		Kinematics lookaheadKinematics = new Kinematics(entity.getKinematics().getX(),entity.getKinematics().getY(),entity.getKinematics().getXVelocity(),entity.getKinematics().getYVelocity(),entity.getKinematics().getXAcceleration(),entity.getKinematics().getYAcceleration());
		if(!(entity.getMovementType() instanceof Flying) && !(entity.getMovementType() instanceof LinearFlying)){
			Kinematics newKinematics = lookaheadPhysics.applyPhysics(lookaheadKinematics, true, true);
			//System.out.println("X: " + newKinematics.getX());
			if((newKinematics.getX() < thresholdLeft) || (newKinematics.getX() > thresholdRight)){
				// was newKinematics.getYVelocity() < 0
				// TODO WIP
				if(goingLeft){
					//rightwards.execute(entity);
					entity.setXVelocity(entity.getMaxXVelocity());
					//System.out.println("Going Right, close to ledge going left");
					goingLeft = false;
				} else {
					//leftwards.execute(entity);
					entity.setXVelocity(-entity.getMaxXVelocity());
					//System.out.println("Going Left, close to ledge going right");
					goingLeft = true;
				}
			} //else {
			if(goingLeft){
				//leftwards.execute(entity);
				entity.setXVelocity(-entity.getMaxXVelocity());
					//System.out.println("Going Left, not close to ledge");
			} else {
				//rightwards.execute(entity);
				entity.setXVelocity(entity.getMaxXVelocity());
					//System.out.println("Going Right, not close to ledge");
			}
			//}
		}
	}

}
