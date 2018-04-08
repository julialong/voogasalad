package engine;

import engine.behavior.*;
import engine.controls.Controls;
import engine.entity.Foes;
import engine.entity.Player;
import javafx.scene.input.KeyCode;

public class EngineTest {
	private int seconds = 3;

	public void EnemyMovementTest() {
		Player p = new Player();
		p.overridePosition(-10, 0);
		Foes foe = new Foes(p);
		foe.addBehavior(new MoveForward(p));
		//foe.addBehavior(new ChasePlayer(p));
		for(int i = 0; i < seconds*60; i++) {
			foe.update();
			System.out.println(i);
			System.out.println("X Pos = " + foe.getPosition()[0]);
			//System.out.println("Y Pos = " + foe.getPosition()[1]);
			System.out.println("");
		}
	}

	public void PlayerMovementTestLR() {
		Player p = new Player();
		p.setFrictionConstant(20);
		Controls controls = new Controls(p);
		for(int i = 0; i < seconds*60; i++) {
			p.update();
			if(i == 0) {
				controls.activate(KeyCode.D);
			}
			if(i == 2*60) {
				controls.deactivate();
			}
			System.out.println(i);
			System.out.println("X Pos = " + p.getPosition()[0]);
			//System.out.println("Y Pos = " + p.getPosition()[1]);
			System.out.println("");
		}
	}

	public static void main(String args[]) {
		EngineTest engineTest = new EngineTest();
		//engineTest.EnemyMovementTest();
		engineTest.PlayerMovementTestLR();
	}
}
