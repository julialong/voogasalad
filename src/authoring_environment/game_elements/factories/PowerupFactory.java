package authoring_environment.game_elements.factories;

import engine.entity.GameEntity;
import engine.level.Level;
import engine.powerup.PowerUp;
import engine.weapon.Weapon;

import java.lang.reflect.Constructor;
import java.util.List;

class PowerupFactory {

    private static final String POWERUP_PATH = "engine.powerup.";
    private static final String WEAPON_PATH = "engine.weapon.";

    PowerUp createPowerUp(String powerup, List<String> powerupAttributes, GameEntity newEntity, Level myLevel) {
        try {
            Constructor<?> powerupConstructor = Class.forName(POWERUP_PATH + powerup).getConstructor(Weapon.class);
            powerupConstructor.setAccessible(true);
            return (PowerUp) powerupConstructor.newInstance(createWeapon(powerupAttributes.get(0), newEntity, myLevel));
        }
        catch (Exception e) {
            try {
                Constructor<?> powerupConstructor = Class.forName(POWERUP_PATH + powerup).getConstructor(double.class);
                powerupConstructor.setAccessible(true);
                return (PowerUp) powerupConstructor.newInstance(Double.parseDouble(powerupAttributes.get(0)));
            }
            catch (Exception ee) {
                try {
                    Constructor<?> powerupConstructor = Class.forName(POWERUP_PATH + powerup).getConstructor();
                    powerupConstructor.setAccessible(true);
                    return (PowerUp) powerupConstructor.newInstance();
                }
                catch (Exception eee) {
                    return null;
                }
            }
        }
    }

    private Weapon createWeapon(String weapon, GameEntity newEntity, Level myLevel) {
        try {
            Constructor<?> weaponConstructor = Class.forName(WEAPON_PATH + weapon).getConstructor(GameEntity.class, Level.class);
            weaponConstructor.setAccessible(true);
            return (Weapon) weaponConstructor.newInstance(newEntity, myLevel);
        }
        catch (Exception e) {
            return null;
        }
    }
}
