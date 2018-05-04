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
import engine.behavior.UseWeapon;
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
import javafx.scene.Node;
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
		//block.setImagePath("bottomPlatform.png");
		level.addObject(block);

		// Player
		player = new Player();
		player.overridePosition(-380, -110);
		player.setSizeX(15);
		player.setSizeY(30);
		player.setSpeedFactor(1000);
		player.setMaxXVelocity(50);
		//player.setMaxXVelocity(200);
		player.setMaxYVelocity(500);
		player.setFrictionConstant(200);
		player.setJumpFactor(200);
		controls = new Controls(player);
		playerWeapon = new ShootingWeapon(player, level);
		playerWeapon.setImagePath("gun.png");
		player.setWeapon(playerWeapon);
		player.setImagePath("luigi.png");
		level.addObject(player);

		// Block 1
		Block block1 = new Block(-220,-110);
		block1.setSizeX(100);
		block1.setSizeY(10);
		block1.addInteraction(new PreventClipping());		
		level.addObject(block1);

		// Block 3 - power up block
		Block block3 = new Block(-180,-40);
		block3.setSizeX(20);
		block3.setSizeY(20);
		block3.addInteraction(new RemoveOnInteractWithPlayer());
		Block bullet = new Block();
		bullet.setImagePath("bullet.png");
		bullet.addBehavior(new MoveForward());
		bullet.addBehavior(new JumpALot());
		bullet.setMovementType(new Grounded());
		bullet.setSizeX(5);
		bullet.setSizeY(5);
		bullet.setMaxXVelocity(100);
		bullet.setMaxYVelocity(500);
		bullet.setJumpFactor(150);
		block3.addInteraction(new AddPowerup(new SwitchWeapon(new ShootingWeapon(player,level,bullet), player)));
		block3.setImagePath("powerup.png");
		level.addObject(block3);

		// Block 4 - power up block stabbing weapon
		Block block4 = new Block(-300,-80);
		block4.setSizeX(20);
		block4.setSizeY(20);
		block4.addInteraction(new RemoveOnInteractWithPlayer());
		block4.addInteraction(new AddPowerup(new SwitchWeapon(new StabbingWeapon(player,level), player)));
		block4.setImagePath("powerup.png");
		level.addObject(block4);

		// Block 5 - wall
		Block block5 = new Block(0,-90);
		block5.setSizeX(10);
		block5.setSizeY(70);
		block5.addInteraction(new PreventClipping());		
		level.addObject(block5);

		// Block 6 - pushable
		Block block6 = new Block(-60,-140);
		//block6.setMovementType(new Grounded());
		block6.setSizeX(20);
		block6.setSizeY(20);
		block6.addInteraction(new Pushable());	
		block6.setImagePath("pushableblock.png");
		level.addObject(block6);

		// Block 7 - wall
		Block block7 = new Block(200,-90);
		block7.setSizeX(10);
		block7.setSizeY(70);
		block7.addInteraction(new PreventClipping());		
		level.addObject(block7);

		// Block 8 - power up block jump height
		Block block8 = new Block(100,-80);
		block8.setSizeX(20);
		block8.setSizeY(20);
		block8.addInteraction(new RemoveOnInteractWithPlayer());
		block8.addInteraction(new AddPowerup(new LightWeight(5, player)));
		block8.setImagePath("powerup.png");
		level.addObject(block8);

		// Enemy 1
		Enemy enemy1 = new Enemy(-180, -100);
		enemy1.setSizeX(16);
		enemy1.setSizeY(27);
		enemy1.setMaxXVelocity(30);
		enemy1.setMaxYVelocity(500);
		enemy1.addBehavior(new MoveForward());
		enemy1.addInteraction(new PreventClipping());
		enemy1.addInteraction(new DamageOnStomp());
		enemy1.setImagePath("turtle.png");
		level.addObject(enemy1);

		// Enemy 2
		Enemy enemy2 = new Enemy(-140, -100);
		enemy2.setSizeX(16);
		enemy2.setSizeY(27);
		enemy2.setMaxXVelocity(30);
		enemy2.setMaxYVelocity(500);
		enemy2.addBehavior(new JumpALot());
		enemy2.addInteraction(new PreventClipping());
		enemy2.addInteraction(new DamageOnStomp());
		enemy2.setImagePath("turtle.png");
		level.addObject(enemy2);

		// Enemy 4
		Enemy enemy4 = new Enemy(150, -100);
		enemy4.setSizeX(16);
		enemy4.setSizeY(27);
		enemy4.setMaxXVelocity(30);
		enemy4.setMaxYVelocity(500);
		enemy4.addBehavior(new MoveBetweenThresholds(10, 180));
		//enemy4.addInteraction(new PreventClipping());
		enemy4.addInteraction(new DamageOnStomp());
		enemy4.setImagePath("turtle.png");
		level.addObject(enemy4);

		// Enemy 3 bowser
		Enemy enemy3 = new Enemy(350, -90);
		enemy3.setSizeX(50);
		enemy3.setSizeY(50);
		enemy3.setMaxXVelocity(30);
		enemy3.setMaxYVelocity(500);
		enemy3.addBehavior(new JumpALot());
		enemy3.addInteraction(new PreventClipping());
		enemy3.addInteraction(new DamageOnStomp());
		enemy3.setXVelocity(-1);
		enemy3.setFrictionConstant(1000);
		enemy3.setHealth(3);
		Block bullet2 = new Block();
		bullet2.setImagePath("bullet.png");
		bullet2.addBehavior(new MoveForward());
		bullet2.addBehavior(new JumpALot());
		bullet2.setMovementType(new Grounded());
		bullet2.setSizeX(5);
		bullet2.setSizeY(5);
		bullet2.setMaxXVelocity(100);
		bullet2.setMaxYVelocity(500);
		bullet2.setJumpFactor(150);
		enemy3.addBehavior(new UseWeapon(0.5));
		ShootingWeapon bowserWeapon = new ShootingWeapon(enemy3,level,bullet2);
		bowserWeapon.setImagePath("launcher.png");
		enemy3.setWeapon(bowserWeapon);
		enemy3.setImagePath("bowser.png");
		level.addObject(enemy3);

		for(GameEntity ge : level.getObjects()){
			if(ge.getImagePath() == null) {
				Rectangle entityImage = new Rectangle(ge.getPosition()[0]+200, -ge.getPosition()[1]+200, ge.getSizeX(), ge.getSizeY()); 
				entityImage.setFill(Color.GREEN);
				entityImage.setStroke(Color.BLACK);
				if(ge instanceof Player){
					entityImage.setFill(Color.BLUE);
				}
				if(ge instanceof Enemy){
					entityImage.setFill(Color.RED);
				}
				root.getChildren().add(entityImage);
				geRectMap.put(ge, entityImage);
			}
			else {
				Image image = new Image(ge.getImagePath());
				ImageView actualImage = new ImageView(image);
				actualImage.setX(ge.getPosition()[0]+200);
				actualImage.setY(-ge.getPosition()[1]+200);
				root.getChildren().add(actualImage);
				geImageMap.put(ge, actualImage);
			}
		};
	}

	private void handleKeyInput(KeyCode code) {
		if(code.equals(KeyCode.A)) {
			if(player.getWeapon().equals(playerWeapon) && geImageMap.containsKey(player.getWeapon()) && player.getWeapon() instanceof ShootingWeapon) {
				geImageMap.get(player.getWeapon()).setImage(new Image("gunflipped.png"));
			}
			else if(geImageMap.containsKey(player.getWeapon()) && player.getWeapon() instanceof ShootingWeapon) {
				geImageMap.get(player.getWeapon()).setImage(new Image("junkratflipped.png"));
			}
			else if(geImageMap.containsKey(player.getWeapon()) && player.getWeapon() instanceof StabbingWeapon) {
				geImageMap.get(player.getWeapon()).setImage(new Image("swordflipped.png"));
			}
			geImageMap.get(player).setImage(new Image("luigiflipped.png"));
		}
		else if(code.equals(KeyCode.D)) {
			if(player.getWeapon().equals(playerWeapon) && geImageMap.containsKey(player.getWeapon()) && player.getWeapon() instanceof ShootingWeapon) {
				geImageMap.get(player.getWeapon()).setImage(new Image("gun.png"));
			}
			else if(geImageMap.containsKey(player.getWeapon()) && player.getWeapon() instanceof ShootingWeapon) {
				geImageMap.get(player.getWeapon()).setImage(new Image("junkrat.png"));
			}
			else if(geImageMap.containsKey(player.getWeapon()) && player.getWeapon() instanceof StabbingWeapon) {
				geImageMap.get(player.getWeapon()).setImage(new Image("sword.png"));
			}
			geImageMap.get(player).setImage(new Image("luigi.png"));
		}
		controls.activate(code);
	}

	private void handleKeyRelease(KeyCode code){
		controls.deactivate(code);
	}

	private void step(double secondDelay) {
		Node actualImage = null;
		boolean addEntity = false;
		for(GameEntity ge : level.getObjects()){
			if(!geRectMap.keySet().contains(ge) && !geImageMap.keySet().contains(ge)){
				if(ge instanceof ShootingWeapon) {
					ge.setImagePath("gun.png");
					Image image = new Image(ge.getImagePath());
					actualImage = new ImageView(image);
					((ImageView) actualImage).setX(ge.getPosition()[0]+200);
					((ImageView) actualImage).setY(-ge.getPosition()[1]+200);
					geImageMap.put(ge, ((ImageView) actualImage));
					addEntity = true;
				}
				else if(ge instanceof StabbingWeapon) {
					ge.setImagePath("sword.png");
					Image image = new Image(ge.getImagePath());
					actualImage = new ImageView(image);
					((ImageView) actualImage).setX(ge.getPosition()[0]+200);
					((ImageView) actualImage).setY(-ge.getPosition()[1]+200);
					geImageMap.put(ge, ((ImageView) actualImage));
					addEntity = true;
				}
				else {
					actualImage = new Rectangle(ge.getPosition()[0]+200, -ge.getPosition()[1]+200, ge.getSizeX(), ge.getSizeY()); 
					((Rectangle) actualImage).setFill(Color.BLACK);
					geRectMap.put(ge, ((Rectangle) actualImage));
					addEntity = true;
				}
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
				if(ge instanceof Enemy) {
					if (ge.getKinematics().getXVelocity() > 0) {
						geImageMap.get(ge).setImage(new Image("turtleflipped.png"));
					}
					else {
						if(!ge.getImagePath().equals("bowser.png"))
						geImageMap.get(ge).setImage(new Image("turtle.png"));
					}
				}
			}
			else {
				toRemove.add(ge);
				root.getChildren().remove(geImageMap.get(ge));
			}
		}
		for(GameEntity ge : geRectMap.keySet()){
			if(level.getObjects().contains(ge)) {
				geRectMap.get(ge).setX(ge.getScenePosition()[0]);
				geRectMap.get(ge).setY(ge.getScenePosition()[1]);
				if(ge instanceof Weapon){
					geRectMap.get(ge).setRotate(((Weapon) ge).getAngle());
				}
			}
			else {
				toRemove.add(ge);
				root.getChildren().remove(geRectMap.get(ge));
			}
		}
		for(GameEntity ge : toRemove) {
			geImageMap.remove(ge);
			geRectMap.remove(ge);
		}
	}
}