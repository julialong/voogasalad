package engine.powerup;

import engine.entity.Player;

/**
 * A powerup that makes a Player fall slower.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class LightWeight extends TimedPowerUp{
	private double gravityFactor;
	
	/**
	 * Constructor using default factor for power up (2)
	 * @param time
	 * @param player
	 */
	public LightWeight(double time, Player player){
		this(time, player, 2);
	}
	
	/**
	 * Constructor for customizing the effect of the power up
	 * @param time
	 * @param player
	 * @param scaleFactor
	 */
    public LightWeight(double time, Player player, double scaleFactor){
        setDuration(time);
        setPlayer(player);
        counter = 0;
        gravityFactor = scaleFactor;
    }

	@Override
	public void effect() {
		player.setGravitationalConstant(player.getKinematics().getGravitationalConstant()/gravityFactor);
	}

	@Override
	public void reverseEffect() {
		player.setGravitationalConstant(player.getKinematics().getGravitationalConstant()*gravityFactor);
	}

}
