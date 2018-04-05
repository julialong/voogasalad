package game_player;

import game_player_api.GameItem;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

/**
 * Each instance of VoogaGame represents a single developed game created from the
 * game authoring environment. Each VoogaGame has a Name, Description, access path, and
 * author.
 *
 * @Author Dorian Barber
 */
public class VoogaGame implements GameItem  {
    private String gameName;
    private String gameDescription;
    private String gameAccessPath;
    private Stage gameApplication = new Stage();

    public VoogaGame(String dataPath){
        //Some how source to the proper section where the name, description, and access paths are available.
        //for now all relevant fields will be set to the dataPath parameter
        gameName = dataPath;
        gameDescription = dataPath;
        gameAccessPath = dataPath;
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
        gameApplication.setScene(scene);
        gameApplication.setTitle(gameName);
        gameApplication.show();
    }

    /**
     * Alters toString method to return a properly formatted
     * representation of the game that can be chosen
     */
    @Override
    public String toString(){
        return gameName + "\n" +
                gameDescription;
    }
}
