package engine.behavior;

import java.util.Random;

import engine.entity.Enemy;
import engine.entity.GameEntity;

public class UseWeapon implements Behavior{
	private double percentChance;
	private Random random = new Random();
	private int counter = 0;

	public UseWeapon(double percentChance){
		this.percentChance = percentChance;
	}
	
	@Override
	public void update(GameEntity entity) {
		if(!(entity instanceof Enemy)) return;
		counter++;
		if(counter > 30){
			double value = random.nextDouble();
			if(value <= percentChance){
				((Enemy) entity).useWeapon();
			}
			counter = 0;
		}
	}

}
