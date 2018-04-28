package engine.powerup;

import engine.entity.Player;

/**
 * A powerup that makes a Player fall slower.
 * @author Robert Gitau
 *
 */
public class LightWeight extends TimedPowerUp{
	
    public LightWeight(double time, Player player){
        setDuration(time);
        setPlayer(player);
        counter = 0;
    }

	@Override
	public void effect() {
		player.setGravitationalConstant(player.getKinematics().getGravitationalConstant()/2);
	}

	@Override
	public void reverseEffect() {
		player.setGravitationalConstant(player.getKinematics().getGravitationalConstant()*2);
	}

}
