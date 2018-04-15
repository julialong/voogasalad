package engine;

import engine.behavior.MoveForward;
import engine.controls.Controls;
import engine.entity.Block;
import engine.entity.Foes;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.interaction.KnockBack;
import engine.interaction.PreventClipping;
import engine.interaction.Pushable;
import engine.level.*;
import engine.movement.Grounded;
import engine.movement.LinearGrounded;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EngineTestVisual extends Application{
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final Level LEVEL = new BasicLevel();
	private Group root = new Group();
	private Controls controls;
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LEVEL.setSize(400, 400);
		Foes enemy = new Foes();
		//Block enemy = new Block();
		//enemy.addBehavior(new MoveForward(new Player()));
		//enemy.addInteraction(new KnockBack());
		//enemy.addInteraction(new PreventClipping());
		enemy.overridePosition(30, 20);
		enemy.setSizeX(10);
		enemy.setSizeY(20);
		enemy.setMaxXVelocity(30);
		enemy.setMaxYVelocity(500);
		enemy.addInteraction(new Pushable());
		Block block = new Block(-125,0);
		block.setSizeX(250);
		block.setSizeY(10);
		block.addInteraction(new PreventClipping());
		Block wall = new Block(-50, 48);
		wall.setSizeX(10);
		wall.setSizeY(48);
		Player player = new Player();
		player.setMovementType(new Grounded());
		player.overridePosition(50, 20);
		player.setSizeX(10);
		player.setSizeY(20);
		player.setSpeedFactor(1000);
		player.setMaxXVelocity(50);
		player.setMaxYVelocity(500);
		player.setFrictionConstant(200);
		player.setJumpFactor(75);
		controls = new Controls(player);
		wall.addInteraction(new PreventClipping());
		LEVEL.addObject(block);
		LEVEL.addObject(enemy);
		LEVEL.addObject(wall);
		LEVEL.addObject(player);
		
		


		Scene scene = new Scene(root, 400, 400, Color.THISTLE);
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		scene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
		Timeline animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

		primaryStage.setScene(scene);
		primaryStage.setTitle("EngineVisualTest");
		primaryStage.show();
	}

	private void handleKeyInput(KeyCode code) {
		controls.activate(code);
	}

	private void handleKeyRelease(KeyCode code){
		controls.deactivate(code);
	}

	private void step(double secondDelay) {
		LEVEL.update();
		//		System.out.println("done");
		//		System.out.println("");
		root.getChildren().clear();
		for(GameEntity ge : LEVEL.getObjects()){
			Rectangle entityImage = new Rectangle(ge.getPosition()[0]+200, -ge.getPosition()[1]+200, ge.getSizeX(), ge.getSizeY()); 
			entityImage.setFill(Color.GREEN);
			entityImage.setStroke(Color.BLACK);
			if(ge instanceof Player){
				entityImage.setFill(Color.BLUE);
			}
			if(ge instanceof Foes){
				entityImage.setFill(Color.RED);
			}
			root.getChildren().add(entityImage);
		}
	}
}
