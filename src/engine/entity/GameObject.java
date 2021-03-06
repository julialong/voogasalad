package engine.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.behavior.Behavior;
import engine.interaction.Interaction;
import engine.movement.Movement;
import engine.physics.Kinematics;

public abstract class GameObject implements GameEntity{
	protected int id;
	protected Movement movementType;
	protected Kinematics kinematics;
	protected int health = 1;
	protected double jumpFactor;
	protected double speedFactor;
	protected double maxVelocityX;
	protected double maxVelocityY;
	protected double width;
	protected double height;
	protected double sceneX;
	protected double sceneY;
	protected String myImagePath;
	protected String myElementID;
	protected ArrayList<Behavior> behaviorList = new ArrayList<>();
	protected ArrayList<Interaction> interactionList = new ArrayList<>();
	protected Map<GameEntity, String> interactionsMap = new HashMap<>();
	protected boolean destructible = false;

	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}

	public void setMovementType(Movement movement) {
		movementType = movement;
	}

	public void setHealth(int HP) {
		health = HP;
	}

	public int getHealth() {
		return health;
	}

	public void addBehavior(Behavior behavior) {
		behaviorList.add(behavior);

	}

	public double getSpeedFactor() {
		return speedFactor;
	}

	public double[] getPosition() {
		double[] positionArray = {kinematics.getX(), kinematics.getY()};
		return positionArray;
	}

	public double getJumpFactor() {
		return jumpFactor;
	}

	public void setSpeedFactor(double speedFactor){
		this.speedFactor = speedFactor;
	}

	public void setJumpFactor(double jumpFactor){
		this.jumpFactor = jumpFactor;
	}

	public void overridePosition(double x, double y) {
		kinematics.setX(x);
		kinematics.setY(y);		
	}

	public void setXVelocity(double velocity) {
		kinematics.setXVelocity(velocity);
	}

	public void setYVelocity(double velocity) {
		kinematics.setYVelocity(velocity);		
	}

	public void setXAcceleration(double accel) {
		kinematics.setXAcceleration(accel);		
	}

	public void setYAcceleration(double accel) {
		kinematics.setYAcceleration(accel);

	}

	public Movement getMovementType() {
		return movementType;
	}

	public void setMaxXVelocity(double velocity) {
		maxVelocityX = velocity;		
	}

	public void setMaxYVelocity(double velocity) {
		maxVelocityY = velocity;

	}
	
	public double getMaxXVelocity() {
		return maxVelocityX;
	}

	public double getMaxYVelocity() {
		return maxVelocityY;
	}

	public void setFrictionConstant(double frictionConstant) {
		kinematics.setFrictionConstant(frictionConstant);
	}
	
	public void setGravitationalConstant(double gravitationalConstant) {
		kinematics.setGravitationalConstant(gravitationalConstant);
	}

	public void addInteraction(Interaction i) {
		interactionList.add(i);
	}

	public void interact(GameEntity source, GameEntity target, String direction) {
		interactionsMap.put(target, direction);
		for(Interaction i : interactionList) {
			if(i != null)
			{
				i.interact(source, target);
			}
		}
	}
	
	public Map<GameEntity, String> getInteractionMap() {
		return interactionsMap;
	}
	
	public void clearInteractionMap() {
		interactionsMap.clear();
	}
	
	public List<Interaction> getInteractions() {
		return interactionList;
	}

	public void setSizeX(double x) {
		width = x;
	}

	public void setSizeY(double y) {
		height = y;
	}

	public double getSizeX() {
		return width;
	}

	public double getSizeY() {
		return height;
	}
	
	public void setX(double x) {
		kinematics.setX(x);
	}
	
	public void setY(double y) {
		kinematics.setY(y);
	}

	public Kinematics getKinematics() {
		return kinematics;
	}

	public void setKinematics(Kinematics kinematics) {
		this.kinematics = kinematics;
	}
	
	public void setScenePosition(double x, double y) {
		sceneX = x;
		sceneY = y;
	}
	
	public double[] getScenePosition() {
		double[] positionArray = {sceneX, sceneY};
		return positionArray;
	}
	
	public boolean getDestructible() {
		return destructible;
	}
	
	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}

	public void setImagePath(String path) {
		myImagePath = path;
		//myImagePath.setFitWidth(width);
		//myImagePath.setFitHeight(height);
	}
	
	public String getImagePath() {
		return myImagePath;
	}

	public void setElementID(String ID) {
		myElementID = ID;
	}
	
	public String getElementID() {
		return myElementID;
	}
	
	public List<Behavior> getBehaviorList() {
		return behaviorList;
	}

	public void update() {
		interactionsMap.clear();
		for(Behavior behavior : behaviorList) {
			behavior.update(this);
		}
		kinematics = movementType.update(kinematics, maxVelocityX, maxVelocityY);
	}
}
