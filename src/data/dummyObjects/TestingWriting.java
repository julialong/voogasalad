package data.dummyObjects;

import java.io.FileWriter;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.gamefiles.GameFileWriter;
import engine.behavior.MoveForward;
import engine.controls.Controls;
import engine.entity.*;
import engine.interaction.DamageOnStomp;
import engine.interaction.KnockBack;
import engine.interaction.PreventClipping;
import engine.interaction.Pushable;
import engine.level.Level;
import engine.movement.Grounded;
import engine.level.BasicLevel;

public class TestingWriting {

	public static void main(String[] args) {
		try {
		GameFileWriter myWriter = new GameFileWriter("Belanie", "ErrorWithEnemies");

		FileWriter fw;

		myWriter.saveIndivLevel(makeDummyObjects());
		myWriter.saveData(makeDummyObjects());
		} catch (Exception e) {
			System.out.println("not my job");
			e.printStackTrace();
		}
	}

	private static AuthoredLevel makeDummyObjects() {
		// Set up level
		Level one = new BasicLevel();
		AuthoredLevel oneA = new AuthoredLevel(one, new ScrollingGrid());
		one.setSize(400, 400);

		// Set up player
		Player player = new Player();
		player.setMovementType(new Grounded());
		player.overridePosition(-250, -170);
		player.setSizeX(10);
		player.setSizeY(30);
		player.setSpeedFactor(1000);
		player.setMaxXVelocity(50);
		player.setMaxYVelocity(500);
		player.setFrictionConstant(200);
		player.setJumpFactor(150);
		player.setImagePath("./game.player.styling/mario_running.gif");
		System.out.println(player.getImagePath());
		one.addObject(player);

		// big block that player stands on
		Block block = new Block(-400, -190);
		block.setSizeX(800);
		block.setSizeY(10);
		block.addInteraction(new PreventClipping());
		block.setHealth(1);
		one.addObject(block);

		// big block that player has to jump over, player needs to push smaller blocks
		// closer to jump from
		Block wall = new Block(-50, -142);
		wall.setSizeX(10);
		wall.setSizeY(48);
		wall.setHealth(1);
		wall.addInteraction(new PreventClipping());
		one.addObject(wall);

		// small block that can be pushed on the right side of the big block
		Enemy enemy = new Enemy();
		enemy.overridePosition(30, -170);
		enemy.setSizeX(10);
		enemy.setSizeY(30);
		enemy.setMaxXVelocity(30);
		enemy.setMaxYVelocity(500);
		enemy.addInteraction(new Pushable());
		enemy.setHealth(1);
		one.addObject(enemy);

		// small block that can be pushed on the left side of the big block
		Enemy enemy3 = new Enemy();
		enemy3.overridePosition(-70, -170);
		enemy3.setSizeX(10);
		enemy3.setSizeY(20);
		enemy3.setMaxXVelocity(30);
		enemy3.setMaxYVelocity(500);
		enemy3.addInteraction(new Pushable());
		enemy3.setHealth(1);
		one.addObject(enemy3);

		// enemy that player kills by jumping on. should chase the player
		Enemy enemy2 = new Enemy();
		enemy2.overridePosition(-85, -170);
		enemy2.setSizeX(10);
		enemy2.setSizeY(20);
		enemy2.setMaxXVelocity(30);
		enemy2.setMaxYVelocity(500);
		// line below causes data error
		enemy2.addBehavior(new MoveForward(new Player()));
		enemy2.addInteraction(new DamageOnStomp());
		enemy2.addInteraction(new KnockBack());
		enemy2.setHealth(1);
		one.addObject(enemy2);

		return oneA;
	}

}
