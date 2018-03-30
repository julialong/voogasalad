package usecases.sample_object_classes;

import api.data.GAEtoJSON;
import api.gae.GameCreator;

import java.io.File;
import java.io.IOException;

public class PlatformGameCreator implements GameCreator{

    private GAEtoJSON myDataManager;


    public PlatformGameCreator(GAEtoJSON dataManager) {
        myDataManager = dataManager;
    }

    @Override
    public void saveGame() {
        // TODO: change to real map
        myDataManager.update(null);
    }

    @Override
    public void loadGame(File game) throws IOException {

    }

    @Override
    public void loadLevel(File level) throws IOException {

    }
}
