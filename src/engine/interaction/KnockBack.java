package engine.interaction;

import engine.entity.GameEntity;
import engine.physics.DetectCollision;
import engine.physics.Kinematics;

/**
 * The source causes the target to get knocked away from the source
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class KnockBack implements Interaction{
	private double knockbackFactor;
	
	public KnockBack(){
		this(30);
	}
	
	public KnockBack(double knockbackFactor){
		this.knockbackFactor = knockbackFactor;
	}
	
	@Override
	public void interact(GameEntity source, GameEntity target) {
		String collisionType = new DetectCollision().detect(source, target);
		
		if(!(collisionType.equals("top") || collisionType.equals("bottom"))) {
			Kinematics kTarget = target.getKinematics();
			Kinematics kSource = source.getKinematics();
			if(kTarget.getXVelocity() != 0){
				target.setXVelocity(-knockbackFactor*kTarget.getXVelocity());
			}
			else{
				target.setXVelocity(knockbackFactor*kSource.getXVelocity());
			}
		}
	}

}
