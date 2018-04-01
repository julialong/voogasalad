PROJECT PLAN
===

### Data

#### Belanie
* Features:
    * Functionality for loading games from the database and converting the JSON data into a list of objects that can compile to one game. Creation of game, level, and sprite objects from existing data and assigning the appropriate properties to those objects. 
* Use Cases: 
    * Send existing game data to Game Player
    * Send existing data to GAE (for continued editing)
    * Convert JSON data into usable data for Game Player
    * Convert JSON data into usable data from Game Engine
    * Creation of default settings files
* Extensions:
    * Converting to an online database
    * Dynamic updating of game objects as it is being played

#### Maya
* Features:
    * Functionality for saving games being made or played to database by converting objects to JSON and saving to database.
* Use cases:
    * Starting a new game and making files/folder for it
    * Java object to database (from Game Engine or GAE)
        * Save data on interval
    * Adding to a game (new levels)
    * Creation of default level files
* Extensions:
    * Converting to online database instead of local files
    * Dynamic updating of game as it's being played

### Game Authoring Environment 

#### Julia 
* Features:
	* New level creator window
	* Game settings
* Use cases:
	* Create a blank new level of a certain size
	* Load images/sprites for use in custom elements or level backgrounds
	* Set level order within a game
	* Export a full game via Game Data as a file loadable by the Game Player
	* Determine how transitions from one level to the next will be achieved
	* Create a splash screen/ home page/ rules page for a game
* Extensions: 
	* Make the GAE work as triggered from the Game Player

#### Judi
* Features: 
	* New element creator window
* Use cases:
    * Create a custom element of a certain type with custom properties
    * Load images/sprites for use in custom elements or level backgrounds
    * Load and edit a previously saved level
    * Load and edit a previously saved game file
    * Create a custom element of a certain type with custom properties
    * Load images/sprites for use in custom elements or level backgrounds

* Extensions: 

#### Michael
* Features:
	* Creating the element manager
	* Dragging and dropping elements onto the grid
	* UI layout and design
* Use cases: 
	* Add an element to a desired position in the level
	* Add a default game element with default properties to the game environment
	* Make a quick clone of a previously created game element to add to the game environment
	*  Assign custom reactions to keyboard/mouse input
* Extensions: 
	* Make the GAE work as triggered from the Game Player

### Game Engine

#### Robert
* Features:
    * Creating objects for the game (PlayerCharacter, Enemy, etc)
    * Creating behaviors for the game (Movement, Interaction, Behavior)

* Use Cases:
    * User defines an enemy that stays still
    * User defines an enemy that can fly
    * User defines an enemy that can chase the player
    * User defines an enemy that jumps frequently
    * User defines an enemy that does not hurt the player, but does knock the player backwards on contact

* Extensions:
    * Support for extra behaviors 

#### Marcus
* Features:
    * Creating objects for the game (PlayerCharacter, Enemy, etc)
    * Creating behaviors for the game (Movement, Interaction, Behavior)

* Use Cases:
    * User custom-defined and enemy, and changes their behavior to chase the player instead of walk back and forth.
    * User defines an enemy that can fire projectiles
    * User defines a powerup that makes the player grow in size and increases its HP
    * User defines a powerup that lets the player character fire projectiles for a limited time
    * User defines an object in the game that the player character can jump off of if they press a certain combination of keys or button

* Extensions:
    * Support for extra movement types

### Game Player

#### Kelley 
* Features:
    * MenuBar
    * Buttons
        * Keeping track of scores
        * Replay/switch game
* Use Cases:
    * Users saves the current state of the game
    * User remains in the game, but opens a new player
    * User restarts the game from any point
    * User pauses all game actions
    * User is playing the game, adds a block to the game in the GAE while the game is in play
    * User has two game windows open, completes the game in one window, registering a new highscore, user checks highscores in the second window
    
* Extensions:
    * Accessing public APIs (ie: Twitter, Facebook, etc)

#### Dorian
* Features:
    * Formatting the Heads-Up Display to be flexible for different types of display formats
    * Creating the Game Chooser application and connecting to the Game Player Application
    * Developing on the Game Player Application to contain the Heads-Up Display
    * Styling UI

* Use Cases:
    * User selects a game to play   
    * Users exits the game to pick a new one
    * User reaches the end of a level, user is presented with the option to save their current progress
    * User has the launch a new game window open while user saves a new game in the GAE, new game window updates its options

* Extensions:
    * Accessing public APIs (ie: Twitter, Facebook, etc)