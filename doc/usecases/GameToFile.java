package usecases;

import api.data.GAEtoJSON;
import api.gae.GameCreator;
import usecases.sample_object_classes.PlatformGameCreator;

public class GameToFile {

    GameCreator myGameCreator;
    GAEtoJSON myDataManager;

    // creates the GameCreator object needed to modify the game
    void createGame() {
        // TODO: myDataManager = new **insert sample GAEtoJSON object here
        myGameCreator = new PlatformGameCreator(myDataManager);

        // add elements and other game attributes
    }

    // saves the game through the game creator
    void saveGame() {
        // myGameCreator.saveGame() will then call the update
        // method in GAEtoJSO to save the file to the database
        myGameCreator.saveGame();
    }

}