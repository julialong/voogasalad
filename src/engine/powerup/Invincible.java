package engine.powerup;

import engine.entity.Player;

/**
 * A powerup that stops the player from getting hurt
 * Specifically, this will not stop the player from dying by pit.
 * @author Robert Gitau
 *
 */
public class Invincible extends TimedPowerUp {
	
	public Invincible(){
		
	}
	
	public Invincible(double time, Player player){
        setDuration(time);
        setPlayer(player);
        counter = 0;
    }
	
	@Override
	public void effect() {
		player.setDestructible(false);
	}

	@Override
	public void reverseEffect() {
		player.setDestructible(true);
	}

}
