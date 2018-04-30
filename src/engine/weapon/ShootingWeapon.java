package engine.weapon;

import java.util.ArrayList;
import java.util.List;

import engine.behavior.Behavior;
import engine.behavior.MoveForward;
import engine.entity.Block;
import engine.entity.GameEntity;
import engine.interaction.Projectile;
import engine.level.Level;
import engine.movement.Flying;
import engine.movement.Movement;

/**
 * A Weapon that fires blocks as projectiles
 * 
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class ShootingWeapon extends WeaponBase {
	private static final double DEFAULT_WEAPON_ANGLE = 0;
	// private Block projectile;
	private Movement projectileMovement;
	private List<Behavior> projectileBehavior;
	private int projectileDamage = 1;
	private double projectileWidth = 5;
	private double projectileHeight = 5;
	private double projectileSpeed = 100;
	private int firingRate = 15;
	private int counter = 0;
	private double jumpFactor = 150;
	private double terminalVelocity = 500;

	/**
	 * Constructs a default ShootingWeapon with a default 5px X 5px projectile
	 * that deals 1 damage and moves in a straight line
	 * 
	 * @param entity
	 *            the owner of the weapon
	 * @param level
	 *            the level the weapon is in
	 */
	public ShootingWeapon(GameEntity entity, Level level) {
		super(entity, level);
		hitBox.setSizeX(xHolderSize * 2);
		hitBox.setSizeY(yHolderSize - 2 * offset);
		width = xHolderSize * 2;
		height = yHolderSize / 4;
		weaponAngle = DEFAULT_WEAPON_ANGLE;
		rightXOffset = xHolderSize / 10;
		leftXOffset = width - 9 * (xHolderSize / 10);
		yOffset = yHolderSize / 3;
		// projectile = new Block();
		projectileMovement = new Flying();
		projectileBehavior = new ArrayList<Behavior>();
		projectileBehavior.add(new MoveForward());
	}

	/**
	 * Constructor that allows the setting of the projectile's damage, speed,
	 * and firing rate
	 * 
	 * @param entity
	 *            the owner of the weapon
	 * @param level
	 *            the level the weapon is in
	 * @param damage
	 *            the damage the weapon can deal
	 * @param speed
	 *            the speed the projectiles move at
	 * @param firingRate
	 *            the number of frames between projectiles being fired (minimum
	 *            15)
	 */
	public ShootingWeapon(GameEntity entity, Level level, int damage, double speed, int firingRate) {
		this(entity, level);
		projectileDamage = damage;
		projectileSpeed = speed;
		this.firingRate = (firingRate < 15) ? 15 : firingRate;
	}

	/**
	 * Constructor that sets the projectile to be fired based on the Block given
	 * in the constructor, with default damage and firing rate
	 * 
	 * @param entity
	 *            the owner of the weapon
	 * @param level
	 *            the level the weapon is in
	 * @param block
	 *            the block with all the physical parameters the projectile will
	 *            have
	 */
	public ShootingWeapon(GameEntity entity, Level level, Block block) {
		this(entity, level);
		projectileMovement = block.getMovementType();
		projectileBehavior = block.getBehaviorList();
		projectileWidth = block.getSizeX();
		projectileHeight = block.getSizeY();
		projectileSpeed = block.getMaxXVelocity();
		jumpFactor = block.getJumpFactor();
		terminalVelocity = block.getMaxYVelocity();
	}

	@Override
	public void attack() {
		if (counter == 0) {
			Block projectile = new Block();
			projectile.setSizeX(projectileWidth);
			projectile.setSizeY(projectileHeight);
			projectile.setMaxYVelocity(terminalVelocity);
			projectile.setJumpFactor(jumpFactor);
			if (direction.equals("right")) {
				projectile.overridePosition(kinematics.getX() + width, kinematics.getY());
				projectile.setXVelocity(projectileSpeed);
			} else {
				projectile.overridePosition(kinematics.getX() - projectileWidth, kinematics.getY());
				projectile.setXVelocity(-projectileSpeed);
			}
			projectile.setMaxXVelocity(projectileSpeed);
			projectile.setMovementType(projectileMovement);
			for (Behavior b : projectileBehavior) {
				projectile.addBehavior(b);
			}
			projectile.addInteraction(new Projectile(weaponHolder, projectileDamage));
			level.addObject(projectile);
			counter = firingRate;
		}
	}

	@Override
	public void update() {
		updateDirectionality();
		if (counter != 0)
			counter--;
	}

	/**
	 * Sets the projectile's parameters to that of the given block. This will
	 * set the projectile's Movement, Behavior, size, speed, jump factor, and
	 * terminal velocity
	 * 
	 * @param block
	 *            the block to match the projectile spawned
	 */
	public void setProjectile(Block block) {
		projectileMovement = block.getMovementType();
		projectileBehavior = block.getBehaviorList();
		projectileWidth = block.getSizeX();
		projectileHeight = block.getSizeY();
		projectileSpeed = block.getMaxXVelocity();
		jumpFactor = block.getJumpFactor();
		terminalVelocity = block.getMaxYVelocity();
	}

	/**
	 * Sets the Movement of the projectile
	 * 
	 * @param movement
	 *            the Movement desired
	 */
	public void setProjectileMovement(Movement movement) {
		projectileMovement = movement;
	}

	/**
	 * Sets the behaviors of the projetile
	 * 
	 * @param behaviors
	 *            a List containing the Behaviors desired
	 */
	public void setProjectileBehaviors(List<Behavior> behaviors) {
		projectileBehavior = behaviors;
	}

	/**
	 * Sets the damage dealt by the projectile
	 * 
	 * @param damage
	 *            an int representing how much damage a projectile can do
	 */
	public void setDamage(int damage) {
		projectileDamage = damage;
	}

	/**
	 * Sets the size of the projectile
	 * 
	 * @param xSize
	 *            the width of the projectile
	 * @param ySize
	 *            the height of the projectile
	 */
	public void setSize(double xSize, double ySize) {
		projectileWidth = xSize;
		projectileHeight = ySize;
	}

	/**
	 * Sets the speed of the projectile
	 * 
	 * @param speed
	 *            the desired max and starting speed of the projectile, in the x
	 *            direction
	 */
	public void setSpeed(double speed) {
		projectileSpeed = speed;
	}

	/**
	 * Sets how many frames the weapon will wait before it can be fired again
	 * (sets to 15 if given a number less than that)
	 * 
	 * @param firingRate
	 *            the minimum number of frames between shots
	 */
	public void setFiringRate(int firingRate) {
		this.firingRate = (firingRate < 15) ? 15 : firingRate;
	}

	/**
	 * Sets the jump factor of the bullet for those that can jump to influence
	 * its jump height
	 * 
	 * @param jumpFactor
	 *            the desired jump factor, as a double
	 */
	public void setBulletJumpFactor(double jumpFactor) {
		this.jumpFactor = jumpFactor;
	}

	/**
	 * Sets the fastest speed a projectile can have in the y direction
	 * 
	 * @param maxYVel
	 *            a double for the fastest speed the projectile can move in the
	 *            y direction
	 */
	public void setBulletTerminalVelocity(double maxYVel) {
		terminalVelocity = maxYVel;
	}
}
