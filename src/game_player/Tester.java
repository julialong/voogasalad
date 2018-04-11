package game_player;

import engine.controls.Controls;
import engine.entity.Block;
import engine.interaction.PreventClipping;
import engine.level.BasicLevel;
import engine.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Tester extends Application {
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Level level = new BasicLevel();
    private PlayerDemo user = new PlayerDemo();

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpScene(primaryStage);
        activateLoop();
    }

    /**
     * Sets up the scene, listener, and game loop
     * @param primaryStage
     */
    private void setUpScene(Stage primaryStage){
        primaryStage.setTitle("Test");
        Scene scene = getTestScene(user);
        Controls controller = new Controls(user);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode(), controller, user));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates the scene
     */
    private Scene getTestScene(PlayerDemo user) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        user.addInteraction(new PreventClipping());
        Block block = new Block(-10, 0);
        block.setSizeX(100);
        block.setSizeY(1);
        block.addInteraction(new PreventClipping());

        //TODO change jump factor and see if the image changes too, probably not, in the step function update position of
        //TODO image to the position of the actual PlayerCharacter
        root.getChildren().add(user.getIcon());
        level.addObject(user);
        level.addObject(block);
        return scene;
    }

    private void activateLoop() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    /**
     * Technically nothing really happens in the game loop
     */
    private void step(double secondDelay){
        level.update();
        if(user.getPosition()[1] != 0){
            System.out.println("The user's current position is at: " + user.getPosition()[1]);
        }
    }

    private void handleKeyInput(KeyCode code, Controls controller, PlayerDemo user) {
        controller.activate(code);
        //System.out.println("The user's current position is at: " + user.getPosition()[1]);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        controller.deactivate(code);
                        //System.out.println("The user's current position is at: " + user.getPosition()[1]);
                    }
                },
                5000
        );
    }

    /**
     * Runs the Game Player application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
