package authoring_environment.game_elements;

import authoring_environment.grid.ScrollingGrid;
import engine.level.Level;

/**
 * The AuthoredLevel class is a mediator between the Level and ScrollingGrid
 * class
 */
public class AuthoredLevel {

    private Level myLevel;
    private ScrollingGrid myScrollingGrid;

    public AuthoredLevel(Level level, ScrollingGrid scrollingGrid) {
        myLevel = level;
        myScrollingGrid = scrollingGrid;
    }

}
