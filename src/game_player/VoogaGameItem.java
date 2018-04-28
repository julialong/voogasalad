package game_player;

import engine.level.Level;
import game_player_api.GameItem;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.List;

/**
 * Each instance of VoogaGame represents a single developed game created from the
 * game authoring environment. Each VoogaGame has a Name, Description, access path, and
 * author.
 *
 * @Author Dorian Barber, Kelley Scroggs
 */
public class VoogaGameItem extends Label implements GameItem {
    private String gameName;
    private String gameDescription;
    private Stage gameApplication = new Stage();

    public VoogaGameItem(String name, String description){
        this.setWrapText(true);
        gameName = name;
        gameDescription = description;
        setFutureBounds(gameApplication);
        handleCloseRequest(gameApplication);
        super.setText(this.toString());
    }

	/**
     * Sets up the Game view application environment
     * with the specific game that this item represents
     */
    @Override
    public void setUpGame(List<Level> gameMaterials){
        PlayerView gameView;
        try{
            gameView = new PlayerView(gameMaterials, gameName, gameDescription);
        } catch(NullPointerException e){
            gameView = new PlayerView();
        }
        VController gameController = new VController(gameView);
        Scene scene = new Scene(gameView);
        scene.getStylesheets().add("./game.player.styling/styleSheet.css");
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
     * Go back to the chooser when you close out of the game
     * 
     * @param primaryStage the current stage whose close request needs to be caught
     */
    private void handleCloseRequest(Stage primaryStage) {
        primaryStage.setOnCloseRequest(event -> {
            Stage stage = new Stage();
            new VoogaChooser(stage);
        });
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
