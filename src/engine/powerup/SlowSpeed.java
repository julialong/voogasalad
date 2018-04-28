package engine.powerup;

import engine.entity.Player;

/**
 * A power up that decreases the speed a player can move at.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class SlowSpeed extends TimedPowerUp{

    public SlowSpeed(double time, Player player){
        setDuration(time);
        setPlayer(player);
        counter = 0;
    }

	@Override
	public void effect() {
		player.setMaxXVelocity(0.5*player.getMaxXVelocity());
	}

	@Override
	public void reverseEffect() {
		player.setMaxXVelocity(2*player.getMaxXVelocity());
	}
}