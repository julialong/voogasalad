package authoring_environment.game_elements.factories;

import engine.entity.GameEntity;

/**
 *  The Object Factory is created for each AuthoredLevel, and is used to create the correct
 *  GameEntity objects, given the string name of the GameEntity, its location on the grid, and
 *  the GridCell size.
 *  The ObjectFactory is flexible because it can be replaced with another ObjectFactory that
 *  creates different kinds of GameEntities for different game genres.
 *  The Object Factory follows the Factory pattern, and reduces the use of the "new" keyword when
 *  instantiating new GameEntity objects.
 */
public interface ObjectFactory {

    /**
     * Adds constructed GameEntity to Level at the correct location in the level
     * @param ID is the String ID of the new object
     * @param x is the x position
     * @param y is the y position
     * @param cellSize is the size of the gridcell
     * @return the constructed GameEntity
     */
    GameEntity addObject(String ID, double x, double y, double cellSize);
}
