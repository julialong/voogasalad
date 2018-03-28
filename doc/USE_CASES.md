USE CASES
===
## Game Authoring Environment
* Create a blank new level of a certain size
* Load and edit a previously saved level
* Add an element to a desired position in the level
* Create a custom element of a certain type with custom properties
* Load images/sprites for use in custom elements or level backgrounds
* Assign custom reactions to keyboard/mouse input
* Set level order within a game
* Export a full game via Game Data as a file loadable by the Game Player
* Load and edit a previously saved game file 
* Add a default game element with default properties to the game environment
* Make a quick clone of a previously created game element to add to the game environment
* Set overall game parameters such as animation speed
* Determine how transitions from one level to the next will be achieved
* Create a splash screen/ home page/ rules page for a game

## Data
* Start new game: create new folder with blank/template files for settings
* add level files to a game folder
* send existing game data to Game Player
* send existing data to GAE (for continued editing)
* save data on interval from GAE
* convert GAE data into JSON data for saving
* convert JSON data into usable data for Game Player
* convert JSON data into usable data from Game Engine
* update settings/data as changes occur in GAE
* provide templates/default settings for objects in game if not specified

## Game Player
* User selects a game to play   
* Users saves the current state of the game
* Users exits the game to pick a new one
* User remains in the game, but opens a new player
* User restarts the game from any point
* User pauses all game actions
* User is playing the game, adds a block to the game in the GAE while the game is in play
* User has two game windows open, completes the game in one window, registering a new highscore, user checks highscores in the second window
* User reaches the end of a level, user is presented with the option to save their current progress
* User has the launch a new game window open while user saves a new game in the GAE, new game window updates its options

## Game Engine
* User defines an enemy that stays still
* User defines an enemy that can fly
* User defines an enemy that can chase the player
* User defines an enemy that jumps frequently
* User defines an enemy that does not hurt the player, but does knock the player backwards on contact
* User custom-defined and enemy, and changes their behavior to chase the player instead of walk back and forth.
* User defines an enemy that can fire projectiles
* User defines a powerup that makes the player grow in size and increases its HP
* User defines a powerup that lets the player character fire projectiles for a limited time
* User defines an object in the game that the player character can jump off of if they press a certain combination of keys or button