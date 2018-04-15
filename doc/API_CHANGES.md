# API CHANGES

## Game Authoring Environment
* Attribute interface/objects are not in use, to be removed
* AuthoredElement
 	* getID():String is no longer in use, to be removed
* GameCreator is not in use, to be removed
	* Instead, using AuthoredGame to store relevant information about the current state of the game
		* This is not yet an interface, but it will be refactored during the next sprint to implement the Game interface, which will have these methods:
			* rename(String):void
			* getName():String
			* setDescription(String):void
			* getDescription():String
			* setPlayable(boolean):void
			* isPlayable():boolean
			* addLevel(Level):void
			* removeLevel(Level):void
			* getLevels():List<Level>
			* setCurrentLevel(Level):void
			* getCurrentLevel():Level
			* update()
* CreatorView has several methods that have not been used at all and will need to be removed:
	* updateGrid():void
	* updateGridView(double):void
	* openAttributeEditor(AuthoredElement):void


## Game Data
* GAEtoJson methods changed:
    * void update() takes List<Level> instead of Map<Level, List<List>>. Instead of saving a whole game at once, we're saving levels only one at a time.
    * new method void updateMeta(boolean ready, String desc) that takes in whether a game is ready or not, and the game's description to save to data file.
    * Level revertChanges() returns a Level now, instead of the objects within the Level
    * new method void renameGame(String newName) renames a game folder

* JSONtoGAE methods changed:
    * List<Object> loadGame(String gameName); has been updated to List<Level> loadCompleteGame(String gameName); Instead of returning a list of game objects, loading a game will return a list of Level objects
    * List<Object> loadLevel(String gameName, String levelName); has been updated to Level loadLevel(String gameName, String levelName); Instead of returning a list of objects upon loading a level, a Level object will be returned.
    * List<Object> loadSettings(String gameName); has been updated to Map<String,String> loadSettings(String gameName); where the Map will have keys be settings values like description or ready to play and keys will be the values of these settings
    * Map<String,String> getGameNames(); has been added which returns a map with keys being the names of games that are ready to be played and values being the descriptions of those games.


## Game Player
* GameView methods changed:
    * added methods to control the game flow: 
        * public void startGame()
        * public void resumeGame()
        * public void pauseGame()
    * removed public void updateGame() after deciding the game loop implementation will take place within the class that implements GameView


## Game Engine
* The Enemy abstract class now extends GameObject 
* Several methods have been added or changed in the GameEntity interface to handle moving and interactions:
    *  setInteraction() is now addInteraction()
    *  interact() now takes two GameEntities as parameters
    *  getSpeedFactor(), getPosition(), getJumpFactor(), setSpeedFactor(), setJumpFactor(), overridePosition(), setXVelocity(), setYVelocity(), setXAcceleration(), setYAcceleration(), getMovementType(), setMaxXVelocity(), setMaxYVelocity(), setFrictionConstant(), setSizeX(), setSizeY(), getSizeX(), getSizeY(), getKinematics(), setImagePath(), getImagePath(), and update() have all been added as methods.
* GameObject.setBehavior() has been changed to GameObject.addBehavior()