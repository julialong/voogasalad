package usecases;
import api.engine.Enemy;
import api.engine.Behavior;
import api.engine.Weapon;
import api.engine.Interaction;
import api.engine.Movement;
import api.engine.Enemy;
import api.engine.NoWeapon;
import api.engine.HarmPlayer;
import api.engine.Grounded;
import api.engine.ChasePlayer;
// Use Case: User defines an enemy that can chase the player.
public class CreateEnemy {
	private Enemy myEnemy;
	public CreateEnemy(){
		myMovement = defineGroundedMovement();
		myWeapon = defineWeapon();
		myBehavior = defineChasePlayer();
		myInteraction = defineHarmPlayer();
		myEnemy = createEnemy(myMovement, myBehavior, myWeapon, 1, myInteraction);
	}
	private Weapon defineWeapon(){
		return new NoWeapon();
	}
	private Interaction defineHarmPlayer(){
		return new HarmPlayer();
	}
	private Movement defineGroundedMovement(){
		return new Grounded();
	}
	private Behavior defineChasePlayer(){
		return new ChasePlayer();
	}
	private Enemy createEnemy(Movement movement, Behavior behavior, Weapon weapon, int HP, Intearaction interaction){
		myGameCreator = new GameCreator();
		myGameCreator.createNewElement("ENEMY");
		
		// this: 
		Enemy enemy = new Enemy(movement, behavior, weapon, HP, interaction);
		// or this:
//      Enemy enemy = new Enemy();
//		enemy.setBehavior(behavior);
//		enemy.setMovementType(movement);
//		enemy.setWeapon(weapon);
//		enemy.setHealth(HP);
//		enemy.setInteraction(interaction);
		return enemy;
	}
}