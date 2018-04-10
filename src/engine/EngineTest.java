package engine;

import engine.behavior.*;
import engine.controls.Controls;
import engine.entity.Block;
import engine.entity.Foes;
import engine.entity.Player;
import engine.interaction.PreventClipping;
import engine.level.*;
import javafx.scene.input.KeyCode;

public class EngineTest {
	private int seconds = 5;

	public void EnemyMovementTest() {
		Player p = new Player();
		p.overridePosition(-10, 0);
		Foes foe = new Foes();
		foe.addBehavior(new MoveForward(p));
		//foe.addBehavior(new ChasePlayer(p));
		for(int i = 0; i < seconds*60; i++) {
			//if(i%4 == 0) p.overridePosition(p.getPosition()[0]+1, 0);
			foe.update();
			System.out.println(i);
			//System.out.println("Player Pos = " + p.getPosition()[0]);
			System.out.println("Enemy Pos = " + foe.getPosition()[0]);
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
				controls.deactivate(KeyCode.D);
			}
			System.out.println(i);
			System.out.println("X Pos = " + p.getPosition()[0]);
			//System.out.println("Y Pos = " + p.getPosition()[1]);
			System.out.println("");
		}
	}
	
	public void PlayerMovementTestJUMP() {
		Player p = new Player();
		p.addInteraction(new PreventClipping());
		Block block = new Block(-10,0);
		block.setSizeX(100);
		block.setSizeY(1);
		block.addInteraction(new PreventClipping());
		Level level = new BasicLevel();
		level.addObject(p);
		level.addObject(block);
		Controls controls = new Controls(p);
		for(int i = 0; i < seconds*60; i++) {
			if(i == 10) {
				controls.activate(KeyCode.SPACE);
			}
			if(i == 2*60) {
				controls.deactivate(KeyCode.SPACE);
			}
			System.out.println(i);
			//System.out.println("X Pos = " + p.getPosition()[0]);
			System.out.println("Y Pos = " + p.getPosition()[1]);
			System.out.println("");
			level.update();
		}
	}
	
	public void BasicPlayerFloorInteractionTest() {
		Level level = new BasicLevel();
		Foes enemy = new Foes();
		enemy.addBehavior(new MoveForward(new Player()));
		enemy.overridePosition(0, 2);
		enemy.setSizeX(1);
		enemy.setSizeY(1);
		Block block = new Block(-10,0);
		block.setSizeX(100);
		block.setSizeY(1);
		block.addInteraction(new PreventClipping());
		level.addObject(enemy);
		level.addObject(block);
		
		for(int i = 0; i < seconds*60; i++) {
			System.out.println(i);
			System.out.println("X Pos = " + enemy.getPosition()[0]);
			System.out.println("Y Pos = " + enemy.getPosition()[1]);
//			System.out.println("X B Pos = " + block.getPosition()[0]);
//			System.out.println("Y B Pos = " + block.getPosition()[1]);
			System.out.println("");
			level.update();
		}
	}
	
	public void BasicPlayerWallInteractionTest() {
		Level level = new BasicLevel();
		Foes enemy = new Foes();
		enemy.addBehavior(new MoveForward(new Player()));
		enemy.overridePosition(3, 1.2);
		enemy.setSizeX(1);
		enemy.setSizeY(1);
		Block block = new Block(-10,0);
		block.setSizeX(100);
		block.setSizeY(1);
		block.addInteraction(new PreventClipping());
		Block wall = new Block(-5, 5);
		wall.setSizeX(1);
		wall.setSizeY(5);
		wall.addInteraction(new PreventClipping());
		level.addObject(enemy);
		level.addObject(block);
		level.addObject(wall);
		
		for(int i = 0; i < seconds*60; i++) {
			System.out.println(i);
			System.out.println("X Pos = " + enemy.getPosition()[0]);
			System.out.println("Y Pos = " + enemy.getPosition()[1]);
//			System.out.println("X B Pos = " + block.getPosition()[0]);
//			System.out.println("Y B Pos = " + block.getPosition()[1]);
//			System.out.println("X W Pos = " + wall.getPosition()[0]);
//			System.out.println("Y W Pos = " + wall.getPosition()[1]);
			System.out.println("");
			level.update();
		}
	}

	public static void main(String args[]) {
		EngineTest engineTest = new EngineTest();
		//engineTest.EnemyMovementTest();
		//engineTest.PlayerMovementTestLR();
		engineTest.PlayerMovementTestJUMP();
		//engineTest.BasicPlayerFloorInteractionTest();
		//engineTest.BasicPlayerWallInteractionTest();
	}
}
