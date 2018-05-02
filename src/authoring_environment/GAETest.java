package authoring_environment;

import authoring_environment.game_elements.ObjectFactory;
import engine.entity.GameEntity;
import engine.level.BasicLevel;
import engine.level.Level;

public class GAETest {

    private final static String XML = "./data/template";

    public static void main(String[] args) {
        Level level = new BasicLevel();
        ObjectFactory factory = new ObjectFactory(level);
        GameEntity e = factory.addObject("template", 0, 0, 50);
        System.out.println(e.getClass());
    }
}
