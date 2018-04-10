package engine.entity;

import java.util.ArrayList;
import engine.behavior.Behavior;
import engine.interaction.Interaction;
import engine.movement.Movement;
import engine.physics.Kinematics;

public abstract class GameObject implements GameEntity{
	protected Movement movementType;
    protected Kinematics kinematics;
    protected int health;
    protected double jumpFactor;
    protected double speedFactor;
    protected double maxVelocityX;
    protected double maxVelocityY;
    protected double width;
    protected double height;
    protected ArrayList<Behavior> behaviorList = new ArrayList<>();
    protected ArrayList<Interaction> interactionList = new ArrayList<>();
    
	@Override
	public void setMovementType(Movement movement) {
		movementType = movement;
	}

	@Override
	public void setHealth(int HP) {
		health = HP;
	}
	
	@Override
	public int getHealth() {
		return health;
	}

	/**
	 * Defines how the GameObject behaves with regard to the player (ie makes the player slide across it)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public void addBehavior(Behavior behavior) {
		behaviorList.add(behavior);
		
	}

	@Override
	public double getSpeedFactor() {
		return speedFactor;
	}

	@Override
	public double[] getPosition() {
		double[] positionArray = {kinematics.getX(), kinematics.getY()};
        return positionArray;
	}

	@Override
	public double getJumpFactor() {
		return jumpFactor;
	}

	@Override
	public void overridePosition(double x, double y) {
		kinematics.setX(x);
		kinematics.setY(y);		
	}

	@Override
	public void setXVelocity(double velocity) {
		kinematics.setXVelocity(velocity);
	}

	@Override
	public void setYVelocity(double velocity) {
		kinematics.setYVelocity(velocity);		
	}

	@Override
	public void setXAcceleration(double accel) {
		kinematics.setXAcceleration(accel);		
	}

	@Override
	public void setYAcceleration(double accel) {
		kinematics.setYAcceleration(accel);
		
	}

	@Override
	public Movement getMovementType() {
        return movementType;
	}

	@Override
	public void setMaxXVelocity(double velocity) {
		maxVelocityX = velocity;		
	}

	@Override
	public void setMaxYVelocity(double velocity) {
		maxVelocityY = velocity;
		
	}
	
	@Override
	public void setFrictionConstant(double frictionConstant) {
		kinematics.setFrictionConstant(frictionConstant);
	}
	
	@Override
	public void addInteraction(Interaction i) {
		interactionList.add(i);
	}
	
	@Override
	public void interact(GameEntity source, GameEntity target) {
		for(Interaction i : interactionList) {
			i.interact(source, target);
		}
	}
	
	@Override
	public void setSizeX(double x) {
		width = x;
	}
	
	@Override
	public void setSizeY(double y) {
		height = y;
	}
	
	@Override
	public double getSizeX() {
		return width;
	}
	
	@Override
	public double getSizeY() {
		return height;
	}
	
	@Override
	public Kinematics getKinematics() {
		return kinematics;
	}

	@Override
	public void update() {
		for(Behavior behavior : behaviorList) {
			behavior.update(this);
		}
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}
}
