package authoring_environment.game_elements.factories;

import authoring_environment.DocumentGetter;
import engine.entity.GameEntity;
import engine.interaction.Interaction;
import engine.level.Level;
import engine.powerup.PowerUp;
import org.w3c.dom.Document;

import java.lang.reflect.Constructor;
import java.util.List;

class InteractionFactory implements DocumentGetter {


    private static final String INTERACTION_PATH = "engine.interaction.";

    Interaction createInteraction(String interaction, List<String> interactionAttributes, Document document, GameEntity newEntity, Level level) {
        PowerupFactory powerupFactory = new PowerupFactory();
        try {
            Constructor<?> interactionConstructor = Class.forName(INTERACTION_PATH + interaction).getConstructor(PowerUp.class);
            interactionConstructor.setAccessible(true);
            return (Interaction) interactionConstructor.newInstance(powerupFactory.createPowerUp(interactionAttributes.get(0),
                    getPowerupAttributes(document, interactionAttributes.get(0)), newEntity, level));
        }
        catch (Exception e) {
            try {
                Constructor<?> interactionConstructor = Class.forName(INTERACTION_PATH + interaction).getConstructor();
                interactionConstructor.setAccessible(true);
                return (Interaction) interactionConstructor.newInstance();
            }
            catch (Exception ee) {
                return null;
            }
        }
    }
}
