package engine.powerup;

import java.util.List;

import engine.entity.Player;

/**
 * A power up that increases the speed a player can move at.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class SpeedBoost extends TimedPowerUp{

    public SpeedBoost(double time, Player player){
        setDuration(time);
        setPlayer(player);
        counter = 0;
    }

	@Override
	public void effect() {
		player.setMaxXVelocity(2*player.getMaxXVelocity());
	}

	@Override
	public void reverseEffect() {
		player.setMaxXVelocity(0.5*player.getMaxXVelocity());
	}
}