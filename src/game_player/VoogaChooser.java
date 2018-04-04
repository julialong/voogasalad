package game_player;

import game_player_api.GameChooser;
import game_player_api.GameItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import javax.swing.border.Border;
import java.util.List;

/**
 * Application for running the Game Chooser. The purpose is to let
 * the user select which developed game they want to play.
 *
 * @Author Dorian Barber
 */
public class VoogaChooser implements GameChooser {
    private BorderPane myView;
    private ListView<GameItem> playableGames;

    public VoogaChooser(List<GameItem> gamesToPlay){
        playableGames = new ListView<GameItem>();
        ObservableList<GameItem> gameItems = FXCollections.observableArrayList(gamesToPlay);
        playableGames.setItems(gameItems);
        myView.setCenter(playableGames);
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
    public Node displayChoices() {
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
}
