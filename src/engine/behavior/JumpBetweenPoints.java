package engine.behavior;

import engine.controls.Action;
import engine.controls.Jump;
import engine.controls.MoveLeft;
import engine.controls.MoveRight;
import engine.entity.GameEntity;

/**
 * Behavior implementation that causes entity to jump between specified points
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class JumpBetweenPoints implements Behavior{
	private double x1;
	private double x2;
	private Jump jump = new Jump();
	private Action actionLeft = new MoveLeft();
    private Action actionRight = new MoveRight();
    private boolean moveToPoint1 = true;
	
	public JumpBetweenPoints(double x1, double x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	@Override
	public void update(GameEntity entity) {
		jump.execute(entity);
		double xPos;
		if(moveToPoint1) {
			xPos = x1;
		}
		else {
			xPos = x2;
		}
		if(entity.getPosition()[0] > xPos) {
			actionLeft.execute(entity);
		}
		else {
			actionRight.execute(entity);
		}
				
		if(((x1 < x2) && (entity.getPosition()[0] < x1)) || ((x1 > x2) && (entity.getPosition()[0] > x1))) {
			moveToPoint1 = false;
		}
		else if(((x1 < x2) && (entity.getPosition()[0] > x2)) || ((x1 > x2) && (entity.getPosition()[0] < x2))) {
			moveToPoint1 = true;
		}
	}

}
