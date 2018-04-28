package game_player;

import java.util.List;
import java.util.Map;

import engine.entity.GameEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ReplayScreen {
	private final int MIN_WINDOW_WIDTH = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Stage myStage = new Stage();
	private Pane myView = new Pane();
	private List<List<ImageView>> myReplayList;
	private boolean myReplayStatus = true;
	private int myFrameIndex = 0;
	
	public ReplayScreen(List<List<ImageView>> list) {
		myReplayList = list;
		setUpStage();
		startReplay();
	
	}
	/**
     * Sets up the stage for the
     */
    private void setUpStage(){
        myStage.setTitle("Replay of Game");
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		myStage.setX(primaryScreenBounds.getMinX());
		myStage.setY(primaryScreenBounds.getMinY());
		myStage.setWidth(primaryScreenBounds.getWidth());
		myStage.setHeight(primaryScreenBounds.getHeight());
		
        
        Scene scene = new Scene(myView);
        myStage.setScene(scene);
        myStage.show();
    }
	
	private void displayObjects(int frameIndex) {
		if(frameIndex < myReplayList.size()) {
			List<ImageView> snapShot = myReplayList.get(frameIndex);
//			System.out.println(frameIndex);
			myView.getChildren().clear();
			myView.getChildren().addAll(snapShot);
//			for(ImageView iv : snapShot) {
//				myView.getChildren().add(iv);
//				String tab = "\t";
//				System.out.println(tab + "image one");
//				System.out.println(tab +"X: "+iv.getX());
//				System.out.println(tab +"Y: "+iv.getY());
//			}
		}
	}
	
	/**
	 * initializes the game loop.
	 */
	public void startReplay() {
		myReplayStatus = true;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * The game loop
	 * 
	 * @param elapsedTime
	 */
	private void step(double elapsedTime) {
		if (myReplayStatus) {
			displayObjects(myFrameIndex);
			myFrameIndex++;
		}
	}

}	