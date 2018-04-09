package game_player;

import data.gamefiles.JSONtoObject;
import game_player_api.GameItem;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Each instance of VoogaGame represents a single developed game created from the
 * game authoring environment. Each VoogaGame has a Name, Description, access path, and
 * author.
 *
 * @Author Dorian Barber
 */
public class VoogaGame extends Label implements GameItem {
    private String gameName;
    private String gameDescription;
    private String gameAccessPath;
    private Stage gameApplication = new Stage();

    public VoogaGame(String game){
        this.setPrefWidth(500);
        this.setWrapText(true);
        gameName = game;
        setFutureBounds(gameApplication);
        super.setText(this.toString());
    }

    /**
     * This method may be automatically defined for all
     * GameItem objects, but the difference is dependent on
     * how the GameItem was created
     */
    @Override
    public void actionOnClick() {
    }

    /**
     * Sets up the Game view application environment
     * with the specific game that this item represents
     */
    @Override
    public void setUpGame(){
        VView gameView = new VView(gameAccessPath);
        VController gameController = new VController(gameView);
        Scene scene = new Scene(gameView);
        scene.getStylesheets().add("styleSheet.css");
        gameApplication.setScene(scene);
        gameApplication.setTitle(gameName);
        gameApplication.show();
    }

    /**
     * Makes the game application that the user selected take up the entire window of the computer.
     * @param primaryStage
     */
    private void setFutureBounds(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setMinWidth(primaryScreenBounds.getWidth());
        primaryStage.setMaxWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setMinHeight(primaryScreenBounds.getHeight());
        primaryStage.setMaxHeight(primaryScreenBounds.getHeight());
    }


    /**
     * Alters toString method to return a properly formatted
     * representation of the game that can be chosen
     */
    @Override
    public String toString(){
        return gameName;
    }
}
