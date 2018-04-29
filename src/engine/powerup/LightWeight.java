package engine.powerup;

import engine.entity.Player;

/**
 * A powerup that makes a Player fall slower.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class LightWeight extends TimedPowerUp{
	private double gravityFactor;
	
	public LightWeight(double time, Player player){
		this(time, player, 2);
	}
	
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
