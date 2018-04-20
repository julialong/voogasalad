package game_player;

import data.gamefiles.JSONtoObject;
import engine.level.Level;
import game_player_api.GameItem;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

/**
 * Each instance of VoogaGame represents a single developed game created from the
 * game authoring environment. Each VoogaGame has a Name, Description, access path, and
 * author.
 *
 * @Author Dorian Barber
 */
public class VoogaGameItem extends Label implements GameItem {
    private String gameName;
    private String gameDescription;
    private String gameAccessPath;
    private Stage gameApplication = new Stage();

    public VoogaGameItem(String name, String description){
        this.setPrefWidth(500);
        this.setWrapText(true);
        gameName = name;
        gameDescription = description;
        setFutureBounds(gameApplication);
        super.setText(this.toString());
    }

    /**
     * Sets up the Game view application environment
     * with the specific game that this item represents
     */
    @Override
    public void setUpGame(List<Level> gameMaterials, String name){
        PlayerView gameView;
        try{
            gameView = new PlayerView(gameMaterials, name);
        } catch(NullPointerException e){
            gameView = new PlayerView();
        }
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
        return gameName + "\n" + gameDescription;
    }

    /**
     * Sets the listener to wait for the user to click a viable game to play.
     * Once the user selects a game to play the application closes.
     */
    @Override
    public String getGameName(){
        return gameName;
    }
}
