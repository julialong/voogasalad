package engine.powerup;

import engine.entity.Player;
import engine.weapon.Weapon;

/**
 * Changes a Player's current weapon.
 * @author Robert Gitau
 *
 */
public class SwitchWeapon implements PowerUp{
	private Weapon weapon;
	private Player player;
	
	public SwitchWeapon(Weapon weapon, Player player){
		this.weapon = weapon;
		setPlayer(player);
	}

	@Override
	public void setDuration(double time) {
		// do nothing
	}

	@Override
	public void activate() {
		player.setWeapon(weapon);
	}

	@Override
	public void deactivate() {
		// do nothing
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public boolean update() {
		return false;
	}

}
