package authoring_environment.game_elements.factories;

import engine.behavior.Behavior;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Creates the necessary behaviors, given the name of a behavior and a list of its attributes.
 *
 * @author Julia Long
 */
class BehaviorFactory {

    private static final String BEHAVIOR_PATH = "engine.behavior.";

    /**
     * Creates a new Behavior object with the correct attributes
     * @param behavior
     * @param behaviorAttributes
     * @return
     */
    Behavior createBehavior(String behavior, List<String> behaviorAttributes) {
        try {
            Constructor<?> behaviorConstructor = Class.forName(BEHAVIOR_PATH + behavior).getConstructor(double.class, double.class);
            behaviorConstructor.setAccessible(true);
            Behavior b = (Behavior) behaviorConstructor.newInstance(Double.parseDouble(behaviorAttributes.get(0)),
                    Double.parseDouble(behaviorAttributes.get(1)));
            System.out.println(b);
            return (Behavior) behaviorConstructor.newInstance(Double.parseDouble(behaviorAttributes.get(0)),
                    Double.parseDouble(behaviorAttributes.get(1)));
        }
        catch (Exception e) {
            try {
                Constructor<?> behaviorConstructor = Class.forName(BEHAVIOR_PATH +  behavior).getConstructor(double.class);
                behaviorConstructor.setAccessible(true);
                return (Behavior) behaviorConstructor.newInstance(Double.parseDouble(behaviorAttributes.get(0)));
            }
            catch (Exception ee) {
                try {
                    Constructor<?> behaviorConstructor = Class.forName( BEHAVIOR_PATH + behavior).getConstructor();
                    behaviorConstructor.setAccessible(true);
                    return (Behavior) behaviorConstructor.newInstance();
                }
                catch (Exception eee) {
                    return null;
                }
            }
        }
    }
}
