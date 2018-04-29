package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import engine.behavior.ChasePlayer;
import engine.behavior.JumpALot;
import engine.behavior.JumpBetweenPoints;
import engine.behavior.MoveBetweenThresholds;
import engine.behavior.MoveForward;
import engine.controls.Controls;
import engine.entity.Block;
import engine.entity.Foes;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.interaction.AddPowerup;
import engine.interaction.DamageOnStomp;
import engine.interaction.JumpOverObstacle;
import engine.interaction.KnockBack;
import engine.interaction.Platform;
import engine.interaction.PreventClipping;
import engine.interaction.Pushable;
import engine.interaction.RemoveOnInteractWithPlayer;
import engine.level.*;
import engine.movement.Grounded;
import engine.movement.LinearGrounded;
import engine.powerup.LightWeight;
import engine.powerup.SpeedBoost;
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

public class EngineDemo1 extends Application{
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Level level;
	private Group root = new Group();
	private Map<GameEntity, Rectangle> geRectMap = new HashMap<>();
	private Controls controls;
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		level = new BasicLevel(800, 400, 400, 400, 0);
		setupLevel();

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

	private void setupLevel() {
		// Bottom platform
		Block block = new Block(-400,-160);
		block.setSizeX(800);
		block.setSizeY(40);
		block.addInteraction(new PreventClipping());		
		level.addObject(block);
		
		// Player
		Player player = new Player();
		player.overridePosition(-380, -110);
		player.setSizeX(20);
		player.setSizeY(20);
		player.setSpeedFactor(1000);
		player.setMaxXVelocity(50);
		player.setMaxYVelocity(500);
		player.setFrictionConstant(200);
		player.setJumpFactor(300);
		controls = new Controls(player);
		level.addObject(player);
		
		// Block 1
		Block block1 = new Block(-220,-60);
		block1.setSizeX(100);
		block1.setSizeY(20);
		block1.addInteraction(new PreventClipping());		
		level.addObject(block1);
		
		// Block 2
		Block block2 = new Block(-180,50);
		block2.setSizeX(20);
		block2.setSizeY(20);
		block2.addInteraction(new PreventClipping());		
		level.addObject(block2);
		
		// Block 3
		Block block3 = new Block(-300,-60);
		block3.setSizeX(20);
		block3.setSizeY(20);
		block3.addInteraction(new PreventClipping());		
		level.addObject(block3);
		
		// Enemy 1
		
		
		for(GameEntity ge : level.getObjects()){
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
			geRectMap.put(ge, entityImage);
		};
	}

	private void handleKeyInput(KeyCode code) {
		controls.activate(code);
	}

	private void handleKeyRelease(KeyCode code){
		controls.deactivate(code);
	}

	private void step(double secondDelay) {
		level.update();
		ArrayList<GameEntity> toRemove = new ArrayList<>();
		for(GameEntity ge : geRectMap.keySet()){
			if(level.getObjects().contains(ge)) {
				geRectMap.get(ge).setX(ge.getScenePosition()[0]);
				geRectMap.get(ge).setY(ge.getScenePosition()[1]);

			}
			else {
				toRemove.add(ge);
				root.getChildren().remove(geRectMap.get(ge));
			}
		}
		for(GameEntity ge : toRemove) {
			geRectMap.remove(ge);
		}
	}
}
