DESIGN
===

## High-level goals
* Provide an MVC design with well-encapsulated separate parts (engine as model, player as view, data as controller, and GAE as a model and view).
* Have game objects shared by both game engine and game authoring environment that can serve the purposes of both, but encapsulate the different types of uses
* Design a game player environment that is flexible enough to support a different type of game engine with minimum adjustments to the existing code

## How to add new features
* Make a new: weapon, powerup, behavior, interaction
    * To create a new interfaced object, a new class needs to be created in the respective package that implements the correct interface. Because the reflection in the Game Authoring Environment accounts for a couple different constructors for different object types, the constructor could potentially follow any of these types. If the object needs a different constructor, the reflection in ObjectFactory must be altered to account.
* Track an extra variable with HUD
    * Within the setUpHud() method for the VoogaGameView a new hud component can be added to the game view. Additionally, that component must be updated (if desired) within the game loop. The ID of the component must be kept track of in order for the HUD to update properly.
* Save additional data for a Game
    * Need to make a getter method in the Level that returns the piece of data
    * In LevelSerializer, in the serialize() method, call writeKeyValue(fw, _data title_, level.get____().toString()) before writeGrid()
    * In the LevelBuiler, the user would have to add calls to getters and setters in the addMetaData() method in order to rebuild the level with the new information.
* New Button to menubar that controls the game: 
    * First add a new method to the GameViewIterface that accomplishes the desired affect. You will have to write the method in the VoogaGameView class. Next create a new VButton and add a call to the new method in the VButton's setOnAction method. Next use the MenuBar interface's addButton method to add the new VButton.

## Design choices and trade-offs
* Using JSON as data format instead of XML
    * We decided to use JSON instead of XML because Maya and Belanie were more familiar with JSON after having worked with it extensively in CS290. Also, we were hoping to integrate with an online database and we thought it would be easier to do so with JSON, although that did not prove to be the case. 
* Making AuthoredLevel class
    * Because the authoring environment used a Scrolling Grid for dragging and dropping items into a level, but the scrolling grid was not compatible with anything except authoring environment. In order to encapsulate it as much as possible and not have the Level class have to have methods to interact with it, GAE made essentially a wrapper that contains a Level and Scrolling grid.
* Folder hierarchy
    * We decided to use a folder hierarchy to make sure that all of the necessary files for one game could be kept in the same place. Also, Game Authoring Environment wanted to be able to keep track of authors for custom object associated to that author which was the purpose of the author folder that games are stored under.
* 

## Assumptions/decisions
##### Game Authoring Environment
* We decided to manage the visualization of the level on the editor side as a GridPane. This allowed us to more easily utilize the structure of "blocks" and to create a traditional platform game.

##### Data
* We decided to use JSON files in order to store data as the type of file required was not specified. We chose this because we were more familiar with JSON files due to taking Web Development at the same time as this course. We also hoped to integrate to an online database at some point and thought this would work better with JSON. 

##### Game Player
* Custom preferences a user sets while playing the game persist while they have the application open, but are reset if the user presses the reset button (which also resets the current game play back to the first level of the current game being played)

#### Engine
* Certain interactions/behaviors cannot be mixed, but this is handled by the GAE. Mixing them will not crash anything, but will cause unexpected behavior.
* A summary of these can be read [here](https://docs.google.com/document/d/1ySIgAso5LrclbW8yRnkndx0nSmYM96dGtUxtc-lu2R4/edit).