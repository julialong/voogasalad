package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.behavior.Behavior;
import engine.behavior.ChasePlayer;
import engine.behavior.JumpALot;
import engine.behavior.JumpBetweenPoints;
import engine.behavior.MoveBetweenThresholds;
import engine.behavior.MoveForward;
import engine.controls.Controls;
import engine.entity.Block;
import engine.entity.Enemy;
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
import engine.powerup.SwitchWeapon;
import engine.weapon.AOEWeapon;
import engine.weapon.ShootingWeapon;
import engine.weapon.StabbingWeapon;
import engine.weapon.SwingingWeapon;
import engine.weapon.Weapon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EngineDemo_mlo11 extends Application{
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Level level;
	private Group root = new Group();
	private Map<GameEntity, Rectangle> geRectMap = new HashMap<>();
	private Map<GameEntity, ImageView> geImageMap = new HashMap<>();
	private ShootingWeapon playerWeapon;
	private Controls controls;
	private Player player;
	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		level = new BasicLevel(800, 400, 400, 400);
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
		block.setImagePath("bottomPlatform.png");
		level.addObject(block);
		
		// Player
		player = new Player();
		player.overridePosition(-380, -110);
		player.setSizeX(15);
		player.setSizeY(30);
		player.setSpeedFactor(1000);
		player.setMaxXVelocity(50);
		player.setMaxYVelocity(500);
		player.setFrictionConstant(200);
		player.setJumpFactor(300);
		controls = new Controls(player);
		playerWeapon = new ShootingWeapon(player, level);
		playerWeapon.setImagePath("gun.png");
		player.setWeapon(playerWeapon);
		player.setImagePath("luigi.png");
		level.addObject(player);
		
		// Block 1
		Block block1 = new Block(-220,-60);
		block1.setSizeX(100);
		block1.setSizeY(20);
		block1.addInteraction(new PreventClipping());		
		//level.addObject(block1);
		
		// Block 2
		Block block2 = new Block(-180,50);
		block2.setSizeX(20);
		block2.setSizeY(20);
		block2.addInteraction(new PreventClipping());		
		//level.addObject(block2);
		
		// Block 3 - power up block
		Block block3 = new Block(-300,-60);
		block3.setSizeX(20);
		block3.setSizeY(20);
		block3.addInteraction(new RemoveOnInteractWithPlayer());
//		Block bullet = new Block();
//		bullet.setImagePath("bullet.png");
//		bullet.addBehavior(new MoveForward());
//		bullet.addBehavior(new JumpALot());
//		bullet.setMovementType(new Grounded());
//		bullet.setSizeX(5);
//		bullet.setSizeY(5);
//		bullet.setMaxXVelocity(100);
//		bullet.setMaxYVelocity(500);
//		bullet.setJumpFactor(150);
		//block3.addInteraction(new AddPowerup(new SwitchWeapon(new ShootingWeapon(player,level,bullet), player)));
		//level.addObject(block3);
		
		// Enemy 1
		Enemy enemy1 = new Enemy(-180, -110);
		enemy1.setSizeX(16);
		enemy1.setSizeY(27);
		enemy1.setMaxXVelocity(30);
		enemy1.setMaxYVelocity(500);
		enemy1.addBehavior(new MoveForward());
		enemy1.addInteraction(new PreventClipping());
		enemy1.addInteraction(new DamageOnStomp());
		enemy1.setImagePath("turtle.png");
		level.addObject(enemy1);
		
		for(GameEntity ge : level.getObjects()){
			System.out.println(ge.getClass().getSimpleName());
			Image image = new Image(ge.getImagePath());
			ImageView actualImage = new ImageView(image);
			actualImage.setX(ge.getPosition()[0]+200);
			actualImage.setY(-ge.getPosition()[1]+200);
			root.getChildren().add(actualImage);
			geImageMap.put(ge, actualImage);
			//System.out.println(ge.getClass().getSimpleName());
		};
	}

	private void handleKeyInput(KeyCode code) {
		if(code.equals(KeyCode.A)) {
			geImageMap.get(playerWeapon).setImage(new Image("gunflipped.png"));
			geImageMap.get(player).setImage(new Image("luigiflipped.png"));
		}
		else if(code.equals(KeyCode.D)) {
			geImageMap.get(playerWeapon).setImage(new Image("gun.png"));
			geImageMap.get(player).setImage(new Image("luigi.png"));
		}
		controls.activate(code);
	}

	private void handleKeyRelease(KeyCode code){
		controls.deactivate(code);
	}

	private void step(double secondDelay) {
		ImageView actualImage = null;
		boolean addEntity = false;
		for(GameEntity ge : level.getObjects()){
			if(!geImageMap.keySet().contains(ge)){
				ge.setImagePath("bullet.png");
				Image image = new Image(ge.getImagePath());
				actualImage = new ImageView(image);
				actualImage.setX(ge.getPosition()[0]+200);
				actualImage.setY(-ge.getPosition()[1]+200);
				geImageMap.put(ge, actualImage);
				addEntity = true;
			}
		}
		level.update();
		if(addEntity){
			root.getChildren().add(actualImage);
			addEntity = false;
		}
		ArrayList<GameEntity> toRemove = new ArrayList<>();
		for(GameEntity ge : geImageMap.keySet()){
			if(level.getObjects().contains(ge)) {
				geImageMap.get(ge).setX(ge.getScenePosition()[0]);
				geImageMap.get(ge).setY(ge.getScenePosition()[1]);
				if(ge instanceof Weapon){
					geImageMap.get(ge).setRotate(((Weapon) ge).getAngle());
				}
			}
			else {
				toRemove.add(ge);
				root.getChildren().remove(geImageMap.get(ge));
			}
		}
		for(GameEntity ge : toRemove) {
			geImageMap.remove(ge);
		}
	}
}