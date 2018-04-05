package game_player;

import game_player_api.GameChooser;
import game_player_api.GameItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

/**
 * Application for running the Game Chooser. The purpose is to let
 * the user select which developed game they want to play.
 *
 * @Author Dorian Barber
 */
public class VoogaChooser implements GameChooser {
    private BorderPane myView =  new BorderPane();
    private ListView<GameItem> playableGames;

    public VoogaChooser(List<GameItem> gamesToPlay){
        playableGames = new ListView<GameItem>();
        ObservableList<GameItem> gameItems = FXCollections.observableArrayList(gamesToPlay);
        playableGames.setItems(gameItems);
        myView.setCenter(playableGames);
        setListener(playableGames);
    }

    /**
     * Occurs once the user has selected the game to play. This method will close the
     * current application and run the GameView application
     */
    @Override
    public String sendToGame() {
        return null;
    }

    /**
     * This will represent the extensive list of developed games that the
     * user can choose. Choosing a game will prompt the sendToGame method
     */
    @Override
    public BorderPane displayChoices() {
        return myView;
    }

    /**
     * Adds the @param gameName to the list of available games to choose.
     *
     * @param gameName
     */
    @Override
    public void addChoice(String gameName) {

    }


    /**
     * Sets the listener to wait for the user to click a viable game to play.
     * Once the user selects a game to play the application closes.
     */
    private void setListener(ListView<GameItem> gameChoices) {
        gameChoices.setOnMouseClicked(event -> {
            GameItem game = gameChoices.getSelectionModel().getSelectedItem();
            game.setUpGame();
            Stage currentStage = (Stage) myView.getScene().getWindow();
            currentStage.close();
        });
    }
}
