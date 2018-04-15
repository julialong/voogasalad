#USE CASES EXTRA

## Game Authoring Environment
* enable shareable editing 
* allow the attributes of each element to be shown when the mouse hovers over the image
* allow the user to add their custom elements to specific virtual folders in the GUI for organizing their work
* give the user error messages for duplicate element IDs or otherwise invalid IDs
* determine which attributes are mutually exclusive and give the user error messages when they try to do this, or remove it from the authoring environment capability 
* allow players to edit game elements that they have already created
* allow users to reorganize the flow of levels
* allow users to set an "end location" for each level that will move the user to the next level
* allow users to create "sublevels", i.e. levels within levels
* allow usets to save and load levels for editing
* allow users to edit level names


## Game Data
* save a game for individual players, keeping track of their progess
* throw proper errors for file saving/loading
* if possible, integrate with an online database
* create custom keybindings/settings for users
* serialize new types of game objects and new level variables
* support editing game in the middle of play and still read data file
* be able to re-load an unfinished game and update its data
* pass more game metadata to game player (more info on game when selecting which game to play)
* create custom serialization for objects incompatible with usual GSON serialization/deserialization
* allow for "cheats" that a user knowledgeable about how data is stored to manipulate data saving (call API methods? change object properties?)

## Game Player
* User selects a game to play by clicking on the game's description the list of possible games close, and at the same time the game they selected is loaded in a paused state

* Users saves the current state of the game after they reach the end of a level
* Users exits the game play screen, the screen to choose another game is automatically launched
* User is playing one game, but opens a new window to select another game to play
* In the middle of gameplay, User restart the game and are taken back to the games original state

* User pauses the game, if they have a game loaded in another window, that game does not pause
* User is playing the game, adds a block to the game in the GAE while the game is in play
* User has two game windows open, completes the game in one window, registering a new highscore, user checks highscores in the second window
* User reaches the end of a level, user is presented with the option to save their current progress
* User has the launch a new game window open while user saves a new game in the GAE, new game window updates its optionsUser selects a game to play

## Game Engine
* The User defines an enemy that comes equipped with a weapon that it periodially swings in front of itself
* The user defines a power up that increases the player's HP and allows the player to press the "Attack" key to shoot a projectile forwards.
* The user defines a power up that gives the player a weapon that allows the player to damage enemies close in front of it when the "Attack" key is pressed.
* The user defines a power up that increases the number of lives remaining by one.
* The user defines a GameObject that moves the player to the next level when the player collides with it.
* The player falls below a certain threshold on the screen, loses a life, and is placed at the start of the current level.
* The player collides with an object that contains the Checkpoint interaction as one of its Interactions. After dying, the player is placed at the checkpoint's location rather than at the start of the level.
* The player collides with an Entity that sends the player to a level that is not the next one (ie from level 1 to level 78).
* The user defines an enemy that does not hurt the player, but will knock them backwards on contact,
* The player finds a weapon that shoots projectiles that are very fast and unaffected by gravity. The player presses the "Attack" key to fire the weapon in the direction they are facing.