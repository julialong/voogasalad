package game_player;

import data.fileReading.GPGameFileReader;
import data.fileReading.JSONtoGP;
import data.resources.DataFileException;
import game_player_api.GameChooser;
import game_player_api.GameItem;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Application for running the Game Chooser. The purpose is to let
 * the user select which developed game they want to play.
 *
 * @Author Dorian Barber
 */
public class VoogaChooser implements GameChooser {
    private Stage myStage;
    private BorderPane myView =  new BorderPane();
    private JSONtoGP reader = new GPGameFileReader();
    private ListView<GameItem> playableGames = new ListView<>();


    public VoogaChooser(){
        myView.setMinWidth(550);
    }

    public VoogaChooser(Stage stage){
        myStage = stage;
        setUpStage();
    }

    /**
     * Sets up the stage for the
     */
    private void setUpStage(){
        myStage.setTitle("Game Chooser");
        //myStage.setMaximized(true);
        myStage.setMinWidth(600);
        Scene scene = new Scene(this.displayChoices());
        scene.getStylesheets().add("../data/styling/styleSheet.css");
        myStage.setScene(scene);
        myStage.show();
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
    	try
    	{
	        Map<String, String> names = reader.getGameNames();
	        List<GameItem> gamesToPlay = new ArrayList<>();
	        for(String gameName : names.keySet()){
	            GameItem game = new VoogaGameItem(gameName, names.get(gameName));
	            gamesToPlay.add(game);
	        }
	        playableGames.setItems(FXCollections.observableArrayList(gamesToPlay));
	        setListener(playableGames);
	        myView.setCenter(playableGames);  
    	}
    	catch(DataFileException e)
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(e.getCause().toString());
            alert.setContentText(e.getMessage());
            alert.show();
    	}
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
            try{
                GameItem game = gameChoices.getSelectionModel().getSelectedItem();
                System.out.println(game.getGameName());
                game.setUpGame(reader.loadCompleteGame(game.getGameName()), game.getGameName());
                Stage currentStage = (Stage) myView.getScene().getWindow();
                currentStage.close();
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
            catch(DataFileException e)
            {
            	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(e.getCause().toString());
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });
    }
}
