package authoring_environment;

import authoring_environment.editor_windows.LevelCreator;
import authoring_environment.toolbars.choosers.LevelChooser;
import engine.level.BasicLevel;
import engine.level.Level;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTester {

    @Test
    public void addLevelTest() {
        AuthoredGame game = new AuthoredGame();
        Level levelone = new BasicLevel();
        levelone.setName("1");
        game.addLevel(levelone);
        Level leveltwo = new BasicLevel();
        leveltwo.setName("2");
        game.addLevel(leveltwo);
        assertEquals(2, game.getLevels().size());
    }

    public void addLevelChooserTest() {
        AuthoredGame game = new AuthoredGame();
        LevelChooser lc = new LevelChooser(game);
        Level levelone = new BasicLevel();
        levelone.setName("1");
        game.addLevel(levelone);
        Level leveltwo = new BasicLevel();
        leveltwo.setName("2");
        game.addLevel(leveltwo);
    }

}
