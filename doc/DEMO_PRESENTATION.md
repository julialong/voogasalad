VoogaSalad Demo
==
### Game Player
		
		Kelley
		High Level Design
		Model view controller. 2 different views (game chooser and game screen) pass user input in the form of clicks and key presses through the controller to the the model. Were using the Game engine as our model
		Design Decisions && Trade offs considered
		Where to run the game loop
		What to do first: update all objects then check bounds or check bounds then update appropriate objects
		
		Dorian
		Game Chooser Demo
		
### Game Authoring Environment
		
		Primary Architecture:
		Our data in an AuthoredGame class which contains Level objects. Each Level contains a ScrollingGrid class which contains a two-dimensional Array of GridCells, which have an ID representing the data for a certain type of GameEntity.
		Cells store only visual data and an Element ID
		The Element ID represents the data file containing all of the behaviors and other relevant information associated with that Element
		Each level has its own identification so that they can be interchanged as the main view of the GUI
		When a Level is exported/saved, all of its GridCells are converted to unique GameEntity objects via their Element IDs. 
		
		How we work with other parts:
		Saving a game sends the current Levels and other game information to GameData for writing
		We implement Engine’s Level objects and GameEntity objects to determine the possible types of entities and behaviors to accommodate.
		
		Closed to Modification/Open to Extensions:
		We created modules for most of the UI elements so it is easy to add/remove/substitute parts of the UI
		
		Any Design Tradeoffs/Decisions:
		Our organization of the Game class allows the current state of the game to be easily edited but also creates a lot of dependencies upon the Game class
		These dependencies are clear because they are used in the constructor
		Saving custom elements in xml files
		
		
		
		Michael
		Explain functionality of ScrollingGrid and GridCells
		Demonstrate functionality of adding elements to grid:
		Add elements
		Add GIFS
		Select cells
		Delete elements
		Select multiple cells
		Add multiple simultaneous elements
		
		
		Judi
		Create element button
		Edit attributes
		Upload image
		Those attributes and image are saved in the folder
		
		
		Julia
		Level creators can create levels with different backgrounds (color only, image doesn’t look great yet)
		Show how we can jump between levels using the level chooser 
		Show that we can delete levels
		Save game, show settings file
		
### Game Engine
		
		Marcus
		Proof of function:
		
		BasicEnemyWallInteractionTest()
		Explain how entities can be instantiated, modified, and put into a level
		Level instantiated
		Any number of entities can be added and their parameters changed
		Can change the movement type
		Can add interaction classes to them
		(ie. when a block comes into contact with a player, if the block is given the PreventClipping interaction then the player will not move through the block)
		Can add behaviors classes to them
		(ie. An enemy can be given multiple behaviors. For example an enemy can be given a “jump a lot” behavior and a “chase player” behavior and it will then chase the player while jumping around to do so)
		Any entity can be added to the level with an addObject() method
		Only thing that needs to happen then is to call:
		Level.update() at each time step 
		and Controls.activate(KeyCode key) when a key is pressed that is mapped to an action
		Show example output from enemy being spawned into a level, falling onto a block, then walking along the block until is crashes into another block and is stopped there.
		
		Robert
		
		General Structure
		Level class has a List of GameEntities, which can be anything from blocks to the player itself
		Each GameEntity has properties such as Movement type, Behavior, Interactions, location, etc defined as classes that are instantiated when the Player loads data for a level and stored within the GameEntity in question. 
		By calling the method Level.update(), the Level iterates through its list of Entities, which call methods in their Behaviors (for anything that isn’t the player) and Movement classes that define where the Entity’s position should be in the next time step. 
		A separate check in Level.update() determines what Entities are colliding with each other and calls on one of the Entity’s interact() method. This method iterates over a list of Interactions that check to see if the two Entities involved are valid for that collision and, if so, defines what state change each of them needs to undergo.
		A player’s position is updated similarly, but is influenced by key presses that are decoded in a Controls class into Actions that determine where the player will go.
		With other parts:
		The Game Player calls Level.update() every keyframe to update the position of Entities in the display
		They also call Controls.activate() upon key press and Controls.deactivate() upon key release to perform actions with the player on the screen such as moving and jumping.
		The Authoring Environment and Data define and create Entities and their components based on our structure so that a user can define their own parameters for any Entity.
		
		
### Game Data
		
		Primary Architecture:
		Total encapsulation of how data is stored (nothing else knows what data files are like or how we’re storing data)
		
		How we work with other parts:
		Maya: Writing. Taking in Levels, customizing objects within them to JSON dynamically, making folders and files
		Belanie: Reading. Custom serialization/Type Adapters, outputting GameObjects
		
		Closed to Modification/Open to Extensions:
		We have the basis for reading and writing and we can add code to take into account new types of data without modifying our old code
		
		Any Design Tradeoffs/Decisions:
		Why JSON? - potential compatibility with online database, it’s easy to read and understand for us, easily add extra lines, syntax for custom serialization
