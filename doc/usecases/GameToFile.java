package usecases;

import api.gae.GameCreator;

import java.io.IOException;

public class GameToFile {

    GameCreator myGameCreator;

    // creates the GameCreator object needed to modify the game
    void createGame(GameCreator gameType) {
        myGameCreator = gameType;
        // add elements and other game attributes
    }

    // saves the game through the game creator
    void saveGame(String fileToSave) {
        try {
            myGameCreator.saveGame(fileToSave);
        }
        catch (IOException e) {
            // TODO: Handle exception
        }
    }

}