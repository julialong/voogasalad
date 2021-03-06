cmm134 
ez20
sr307
atn13
bn51
mm479

### Part 1
1.  What about your API/design is intended to be flexible?
    * The data should be able to handle any kind of game object and orientation without extension
    * The data writing should by dynamically based on objects being written. There should little to no hard-coding of data files
    * API is general enough that data file type (XML vs JSON) does not matter to other classes

2.  How is your API/design encapsulating your implementation decisions?
    * There are only two front facing methods read and write and the data is not changeable anywhere else.  
    * Front-end and other teams that deal with game objects have no information or access to how data is stored, encapuslating data storage language and data file writing

3.  How is your part linked to other parts of the project?
    * The methods read and write/ bridges the gap between authoring and engine/ defines how data should be exchanged for all classes
    * Classes that deal with game objects (authoring environment and game engine) can call data class methods to save the objects (serialize).

4.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * Data that will be lost. Exceptions will be thrown for cases of invalid files, both for invalid file names and files that don't exist. There is no real need to handle the errors, as game data doesn't interact with the user and (in theory) shouldn't error in game progress.
5.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * using Xstream or a custom writer reader creates a more organized method 

### Part 2
1.  Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
    * The most broad way of implementing game data is serializing a game object, deserializing a game object, and checking for errors. Although these use cases seem broad, game data should be flexible so a general serializer can be used for all related objects, between game authoring and game engine. The use cases are reasonable considering the general responsiblities of game data serializer/deserializers

2.  Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
    * These tasks have been simplified thanks to utilities like XStream and GSON, but we do have to consider the extent that these libraries can do, such as serialize complex variables like ImageViews and other JavaFX objects. We may need to take into account ways of storing these data structures that can't be serialized. However, once we have a solid game data serializer and deserializer, it should be easy to maintain and extend to keep up with new features that are added to game authoring and game engine.
  
3.  What feature/design problem are you most excited to work on?
    * It will be exciting to be able to serialize and deserialize any given object in a flexible and open way that allows for an efficient distribution of data.
    * As authoring environment and game engine (making and playing games) work with the same individual objects, it will be cool to make sure that serialization and deserialization will recreate objects that can be used by all classes that use data objects
    
4.  What feature/design problem are you most worried about working on?
    * We are most worried about converting data from the game engine to the game authoring environment, and vice versa. Given that our game engine and game authoring environment utilize different types of objects, we will be responsible for creating a uniform data system. 
  
5.  What major feature do you plan to implement this weekend?
    * We plan on serialization and deserialization working on same objects/data files to make sure that the two processes are compatible and produce the same objects/data going each way