package engine.interaction;

import engine.entity.GameEntity;
import engine.entity.Player;
import engine.physics.DetectCollision;
import engine.powerup.PowerUp;

/**
 * Adds a powerup to the player on an interaction
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class AddPowerup implements Interaction{
    private PowerUp powerup;
    
    public AddPowerup(PowerUp powerup){
        this.powerup = powerup;
    }
	
	public void interact(GameEntity source, GameEntity target) {        
        if(!(target instanceof Player)) return;
		String collisionType = new DetectCollision().detect(source, target);
        if(!collisionType.equals("none")){
            ((Player) target).addPowerUp(powerup);
            powerup.setPlayer((Player) target);
            powerup.activate();
        }
	}
    
    public void setPowerUp(PowerUp powerup){
        this.powerup = powerup;
    }

}