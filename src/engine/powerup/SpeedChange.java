package engine.powerup;

import engine.entity.Player;

/**
 * A power up that increases the speed a player can move at.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class SpeedChange extends TimedPowerUp{
	private double speedFactor;
	
	public SpeedChange(double time, Player player){
		this(time, player, 2);
	}
	
    public SpeedChange(double time, Player player, double scaleFactor){
        setDuration(time);
        setPlayer(player);
        counter = 0;
        speedFactor = scaleFactor;
    }

	@Override
	public void effect() {
		player.setMaxXVelocity(speedFactor*player.getMaxXVelocity());
	}

	@Override
	public void reverseEffect() {
		player.setMaxXVelocity(player.getMaxXVelocity()/speedFactor);
	}
}