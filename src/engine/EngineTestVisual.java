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

public class EngineTestVisual extends Application{
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
		//level.setSize(400, 400);
		Player player = new Player();
		Foes enemy = new Foes();
		Foes enemy2 = new Foes();
		Foes enemy3 = new Foes();
		//Block enemy = new Block();
		//enemy.addBehavior(new MoveForward(new Player()));
		//enemy.addInteraction(new KnockBack());
		//enemy.addInteraction(new PreventClipping());
		//enemy.addInteraction(new DamageOnStomp());
		enemy.overridePosition(30, -170);
		enemy.setSizeX(10);
		enemy.setSizeY(20);
		enemy.setMaxXVelocity(30);
		enemy.setMaxYVelocity(500);
		enemy.addInteraction(new Pushable());
		enemy3.overridePosition(-70, -170);
		enemy3.setSizeX(10);
		enemy3.setSizeY(20);
		enemy3.setMaxXVelocity(30);
		enemy3.setMaxYVelocity(500);
		//enemy3.addInteraction(new Pushable());
		enemy2.overridePosition(-85, -170);
		enemy2.setSizeX(10);
		enemy2.setSizeY(20);
		enemy2.setMaxXVelocity(30);
		enemy2.setMaxYVelocity(500);
		enemy2.addInteraction(new Pushable());
		//enemy.addBehavior(new JumpALot());
		//enemy.setJumpFactor(150);
		//enemy2.addBehavior(new MoveForward(new Player()));
		//enemy2.addInteraction(new DamageOnStomp());
		//enemy2.addInteraction(new KnockBack());
		//enemy2.addInteraction(new PreventClipping());
		enemy2.setJumpFactor(150);
		//enemy3.addInteraction(new JumpOverObstacle());
		//enemy3.addBehavior(new ChasePlayer(player));
		enemy3.addBehavior(new JumpBetweenPoints(-70, -100));
		//enemy3.addInteraction(new PreventClipping());
		//enemy3.addBehavior(new MoveForward(new Player()));
		enemy3.setJumpFactor(150);
		Block block = new Block(-400,-190);
		block.setSizeX(800);
		block.setSizeY(10);
		//enemy2.addBehavior(new MoveBetweenThresholds(block.getKinematics().getX(), block.getKinematics().getX()+block.getSizeX()));
		block.addInteraction(new PreventClipping());
		Block wall = new Block(-50, -142);
		wall.setSizeX(10);
		wall.setSizeY(48);
		player.setMovementType(new Grounded());
		//player.setScenePosition(400/2 - player.getSizeX()/2, 400/2 - player.getSizeY()/2);
		player.overridePosition(-380, -170);
		player.setSizeX(10);
		player.setSizeY(20);
		player.setSpeedFactor(1000);
		player.setMaxXVelocity(50);
		player.setMaxYVelocity(500);
		player.setFrictionConstant(200);
		player.setJumpFactor(150);
		controls = new Controls(player);
		wall.addInteraction(new PreventClipping());
		//wall.addInteraction(new AddPowerup(new SpeedBoost(5, player)));
		//wall.addInteraction(new AddPowerup(new LightWeight(5, player)));
		//wall.addInteraction(new RemoveOnInteractWithPlayer());
		Block platform = new Block();
		platform.overridePosition(0, -130);
		platform.setSizeX(100);
		platform.setSizeY(10);
		platform.addInteraction(new Platform());
		Block platform2 = new Block();
		platform2.overridePosition(100, -100);
		platform2.setSizeX(100);
		platform2.setSizeY(10);
		platform2.addInteraction(new Platform());
		level.addObject(block);
		level.addObject(enemy);
		level.addObject(wall);
		level.addObject(player);
		level.addObject(enemy2);
		level.addObject(platform);
		level.addObject(platform2);
		//level.addObject(enemy3);
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
				//geRectMap.get(ge).setX(ge.getPosition()[0]+200);
				//geRectMap.get(ge).setY(-ge.getPosition()[1]+200);
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
