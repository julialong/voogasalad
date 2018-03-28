package usecases.sample_object_classes;

import api.gae.GameCreator;

import java.io.File;
import java.io.IOException;

public class PlatformGameCreator implements GameCreator{


    @Override
    public void saveGame(String filePath) throws IOException {

    }

    @Override
    public void loadGame(File game) throws IOException {

    }

    @Override
    public void loadLevel(File level) throws IOException {

    }
}
