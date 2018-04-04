package game_player;

import game_player_api.GameItem;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Each instance of VoogaGame represents a single developed game created from the
 * game authoring environment. Each VoogaGame has a Name, Description, access path, and
 * author.
 */
public class VoogaGame extends Node implements GameItem  {
    private String gameName;
    private String gameDescription;
    private String gameAccessPath;
    private Stage stage = new Stage();

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
        this.setOnMouseClicked(event -> {
            setUpGame();
        });
    }


    private void setUpGame(){
        VView gameView = new VView(gameAccessPath);
        VController gameController = new VController(gameView);
        Scene scene = new Scene(gameView);
        stage.setScene(scene);
        stage.setTitle(gameName);
        stage.show();
    }
}
