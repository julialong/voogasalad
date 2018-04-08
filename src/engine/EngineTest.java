package engine;

import engine.behavior.ChasePlayer;
import engine.entity.Foes;
import engine.entity.Player;

public class EngineTest {
	private static int seconds = 5;
	
	public static void EnemyMovementTest() {
		Player p = new Player();
		p.overridePosition(-10, 0);
		Foes foe = new Foes(p);
		foe.setBehavior(new ChasePlayer(p));
		for(int i = 0; i < seconds*60; i++) {
			foe.update();
			System.out.println(i);
			System.out.println("X Pos = " + foe.getPosition()[0]);
			//System.out.println("Y Pos = " + foe.getPosition()[1]);
			System.out.println("");
		}
	}
	
	public static void main(String args[]) {
		EnemyMovementTest();
	}
}
