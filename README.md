VOOGASalad README
===

Date started: 23 Mar 18
Date finished: 29 Apr 18

Julia Long (jbl34)
* hours worked: 105

Judith Sanchez Soriano (js640)
* hours worked: 75

Michael Acker (mja39)
* hours worked: ~70

Maya Messinger (mm479)
* hours worked: 90

Belanie Nagiel (bn51)
* hours worked: 95

Dorian Barber (dpb20)
* hours worked: 80

Kelley Scroggs (jks38)
* hours worked: 65

Robert Gitau (rg167)
* hours worked: ~65
 
Marcus Oertle (mlo11)
* hours worked: ~65


### Roles
#### Game Authoring Environment
* Julia Long
    * Creating weekly meeting agendas
    * Creation of basic overall UI
        * Toolbars, general display window
    * Create a new game, launching a new window
    * Save games and individual levels
    * Load games and individual levels 
    * Basic implementation of Level interface and BasicLevel class in Engine
    * Create new levels
        * Resize levels
        * Rename levels
        * Reorder levels 
        * Change level background
        * Delete levels
    * Launch player from GAE
    * Create AuthoredGame object that contains the information about the current game being edited (name, description, levels)
    * Add functionality to add objects to the Level when they are added to the ScrollingGrid
        * Utilize reflection in the ObjectFactory to create necessary objects with attributes read in through XML
* Judith Sanchez-Soriano
    * Custom Element Creation
    * Author Choosing for different profiles
    * Groundwork for XML saving and reading
* Michael Acker
    * Level editing grid functionality
        * Adding/deleting objects to/from grid
        * Dragging and dropping elements into cells
        * Selecting multiple cells for simultaneous adding or deleting
        * Attaching objects to cursor to "paint" on cells
        * Choosing custom cell dimensions for objects
    * Element selection menu
        * Loading custom elements into menu
        * Switching between different types of elements
    * Custom element saving/loading/data management
    * Authoring Environment UI design improvements
    * XML document parsing
    * Converting elements in cells to game entities in levels
    * Work on details of engine class/interface contents used by authroing environment
    * Leap Motion hand tracking utility

#### Game Player
* Dorian Barber
    * Game chooser
        * Intergrating with game data
        * Selecting a game opens the game view
    * GameView
        * Shows the combined view of the game and menubar
    * Overview Driver
        * Allows the user to select between the game authoring environment and the game player
    * Implemented the user moving onto the next level
    * Styling of the game player
        * Chooser, Menubar, View, Save screen, Overview Driver
    * Helped write the attribute super class and all of its subclasses for the Game Authoring Environment
* Kelley Scroggs
    * MenuBar & buttons on the menubar
        * passing game to data to be saved,
        * saving/loading/clearing highscores. creating score in game
        * manipulating the game play (pause/resume)
        * changing key bindings
        * reseting the game
        * loading a replay of the game that can be played fast/slow/start/stop
    * Displaying the game screen
    * Calling backend, displaying objects


#### Game Data

* Belanie Nagiel
    * Deserialization of GameEntities
    * Rebuilding of Levels and AuthoredLevels for loading by Game Authoring Environment and Game Player
    * Interface Adapter for serialization
    * BehaviorSkipManager for deserialization of GameEntities
    * Working on the attempted Firebase connection

* Maya Messinger
    * serialization of GameEntities (shared objects from GAE and Engine) to JSON
        * FileWriter
        * LevelSerializer
    * file writing of games (folder), game settings, and levels
    * worked on attempted Firebase connection

#### Game Engine
* Marcus Oertle and Robert Gitau worked on everything in the engine together.

### Resources

#### Game Authoring Environment
* [JavaFX CSS Reference Guide](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html)
* [JavaFX SplitPane](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/SplitPane.html)
* [JavaFX GridPane](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html)
* [JavaFX BorderPane](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html)
* [JavaFX Document](https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Document.html)

#### Data
* [Setting Up GSON](https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52)
* [Creating Folders](https://stackoverflow.com/questions/4801971/how-to-create-empty-folder-in-java)
* [File Existence](https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java)
* [GSON Example](http://www.javacreed.com/simple-gson-example/)
* [GSON Circular References](https://futurestud.io/tutorials/gson-advanced-mapping-of-circular-references)
* [Writing JSON](https://stackoverflow.com/questions/31728446/write-a-json-file-in-java)
* [Updating Files](https://stackoverflow.com/questions/19681163/update-an-existing-file-in-java)
* [Escape Quotes](https://stackoverflow.com/questions/3559063/how-to-enter-quotes-in-a-java-string)
* [GSON JsonReader](https://google.github.io/gson/apidocs/com/google/gson/stream/JsonReader.html)
* [GSON Json Parsing](https://stackoverflow.com/questions/5490789/json-parsing-using-gson-for-java)
* [GSON JsonParser](https://static.javadoc.io/com.google.code.gson/gson/2.6.2/com/google/gson/JsonParser.html)
* [Counting Files in Directory](https://www.javabrahman.com/quick-tips/how-to-count-number-of-files-in-a-directory-in-java-file-list-vs-nios-directorystream/)
* [Last Index Of](https://www.geeksforgeeks.org/java-lang-string-lastindexof-method/)
* [Iterating Over Files in a Directory](https://stackoverflow.com/questions/4917326/how-to-iterate-over-the-files-of-a-certain-directory-in-java)
* [Renaming a Directory](http://javabeginnerstutorial.com/code-base/java-rename-directory-example/)
* [Serializing Classes with Interfaces](https://stackoverflow.com/questions/4795349/how-to-serialize-a-class-with-an-interface/9550086#9550086)
* [String to Boolean](https://stackoverflow.com/questions/1538755/how-to-convert-string-object-to-boolean-object)
* [Javafx Color](https://docs.oracle.com/javafx/2/api/javafx/scene/paint/Color.html)
* [Firebase Setup](https://firebase.google.com/docs/database/admin/start#admin-sdk-setup)
* [Firebase](https://groups.google.com/forum/#!topic/firebase-talk/LEFeJYcR92Q)
* [Firebase Admin SDK](https://firebase.google.com/docs/admin/setup)
* [Google Api Client](https://developers.google.com/api-client-library/java/google-api-java-client/download)
* [Debugging Firebase](https://stackoverflow.com/questions/7421612/slf4j-failed-to-load-class-org-slf4j-impl-staticloggerbinder)
* [Save Data in Firebase](https://firebase.google.com/docs/database/admin/save-data)
* [Firebase Service Account Key](https://stackoverflow.com/questions/44626919/where-is-or-what-is-the-serviceaccountkey-json-is-the-node-js-sample-of-firebase/49039675#49039675)
* [Firebase DatabaseReference](https://firebase.google.com/docs/reference/admin/java/reference/com/google/firebase/database/DatabaseReference)
* [Saving Data in Firebase](https://www.javaquery.com/2016/09/how-to-save-data-in-firebase.html)
* [Firebase DatabaseReference](https://firebase.google.com/docs/reference/js/firebase.database.Reference)
* [Json Primitive](https://google.github.io/gson/apidocs/com/google/gson/JsonPrimitive.html)
* Human Resources: UTA Lasia Lo and his friend who knows about Firebase

#### Game Player
* [Managing multiple scenes](https://community.oracle.com/thread/2335204?start=0)
* [Styling](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html)
* [Resource Bundle](https://docs.oracle.com/javase/7/docs/api/java/util/ResourceBundle.html)
* [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)
* [Stage](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)
* [Pane](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html)
* [Alert](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html)
* [ListView]
* [Screen bounds]

#### Engine
* [Collision Detection](https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision)

### The Project
#### Starting the project
To start the project, the OverviewDriver must be run. This class allows the user to choose between launching the Game Authoring Environment and the Game Player. When one of those two options are chosen, the appropriate window is launched.

#### Testing the project
##### Game Authoring Environment
* We initially created a testing file to ensure that adding objects to levels was working correctly. 
* After this point, most of the testing was done manually, using print statements throughout the code to ensure that the correct objects were being created at the correct points with the correct attributes. Authoring Environment functionality was also tested by creating and writing games, and then reading them into the Player for testing.
##### Data
* "TestingGSON.java" file to test de-serialization of JSON files to game Java Objects
* "TestingWriting.java" file to test serialization of game Java Objects to JSON files for settings and levels, and for testing correct folder heirarchies for storing game data
* Data should handle exceptions for missing files, bad filepaths, and errors in serialization by throwing an error that will display in an Alert on the front end
##### Game Player
* we used the TestWriting.java class written by the Data team to save objects we wanted to create direclty into JSON files. Throughout the course of the project we created many test games, as sophistication of the engine increased. These files can be run by the game player like any game created in the GAE. Some of the remaing testing games are:
    *  Demo
    *  TestImage
    *  TestGoal

##### Engine
* Initial testing:
    * Used "EngineTest.java" to test math of the physics engine and then also to test entity movement and basic collisions.
* Visual testing
    * "EngineTestVisual.java" and "EngineDemo1.java" were used to test all other features and see a visual output. They essentially were our own JavaFX program that used the engine to create entitites, give them interactions, behaviors, weapons, etc, and see the result visually using Rectange objects. This allowed us to fine tune many aspects of the engine.

#### Necessary files
* /data/authoredElementData/ folder must exist with .xml contents
* /data/authoredElementImages/ folder with sub-folders Block/, Flag/, Foes/, and Player/ with image file contents
* /data/gameData/ folder must exist
* /data/levelData/ folder must exist
* /data/styling/ folder must exist with CSS and image files
* /src/data/resources/behaviorsToSkip.properties
* /src/data/resources/gameObjects.properties
* /src/data/resources/jsonCharacters.properties
* /src/engine/controls/resources/Controls.properties
* /src/game_player/resources/chooser.properties
* /src/game_player/resources/overview.properties
* /src/game_player/resources/saves_screen.properties
* /src/game_player/resources/scores.properties
* /src/resources/Attributes.properties

#### Assuptions and decisions to handle vague specs

##### Game Authoring Environment
* We decided to manage the visualization of the level on the editor side as a GridPane. This allowed us to more easily utilize the structure of "blocks" and to create a traditional platform game.

##### Data
* We decided to use JSON files in order to store data as the type of file required was not specified. We chose this because we were more familiar with JSON files due to taking Web Development at the same time as this course. We also hoped to integrate to an online database at some point and thought this would work better with JSON. 

##### Game Player
* Custom preferences a user sets while playing the game persist while they have the application open, but are reset if the user presses the reset button (which also resets the current game play back to the first level of the current game being played)

#### Dependencies
* libraries (GSON, firebase)
    * api-common-1.5.0.jar
    * firebase-admin-5.10.0-javadoc.jar
    * firebase-admin-5.10.0-javadoc.jar
    * firebase-admin-5.10.0-sources.jar
    * firebase-admin-5.10.0-sources.jar
    * firebase-admin-5.10.0.jar
    * firebase-admin-5.10.0.jar
    * firebase-server-sdk-3.0.0-javadoc.jar
    * firebase-server-sdk-3.0.0-sources.jar
    * firebase-server-sdk-3.0.0.jar
    * google-api-client-1.23.0-javadoc.jar
    * google-api-client-1.23.0-sources.jar
    * google-api-client-1.23.0.jar
    * google-api-client-1.23.0.jar
    * google-api-services-drive-v3-rev110-1.23.0-javadoc.jar
    * google-api-services-drive-v3-rev110-1.23.0-sources.jar
    * google-api-services-drive-v3-rev110-1.23.0.jar
    * google-api-services-oauth2-v2-rev134-1.23.0.jar
    * google-auth-library-credentials-0.9.1.jar
    * google-auth-library-credentials-0.9.1.jar
    * google-auth-library-oauth2-http-0.1.0-javadoc.jar
    * google-auth-library-oauth2-http-0.1.0-sources.jar
    * google-auth-library-oauth2-http-0.1.0.jar
    * google-auth-library-oauth2-http-0.9.1-javadoc.jar
    * google-auth-library-oauth2-http-0.9.1-sources.jar
    * google-auth-library-oauth2-http-0.9.1.jar
    * google-oauth-client-jetty-1.23.0-javadoc.jar
    * google-oauth-client-jetty-1.23.0-sources.jar
    * google-oauth-client-jetty-1.23.0.jar
    * google-oauth-java-client/libs-sources/commons-logging-1.1.1-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-android-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-appengine-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-gson-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-jackson-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-jackson2-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-jdo-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-protobuf-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-http-client-xml-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-oauth-client-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-oauth-client-appengine-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-oauth-client-java6-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-oauth-client-jetty-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/google-oauth-client-servlet-1.23.0-sources.jar
    * google-oauth-java-client/libs-sources/gson-2.1-sources.jar
    * google-oauth-java-client/libs-sources/httpclient-4.0.1-sources.jar
    * google-oauth-java-client/libs-sources/httpcore-4.0.1-sources.jar
    * google-oauth-java-client/libs-sources/jackson-core-2.1.3-sources.jar
    * google-oauth-java-client/libs-sources/jackson-core-asl-1.9.11-sources.jar
    * google-oauth-java-client/libs-sources/jdo2-api-2.3-eb-sources.jar
    * google-oauth-java-client/libs-sources/jetty-6.1.26-sources.jar
    * google-oauth-java-client/libs-sources/jetty-util-6.1.26-sources.jar
    * google-oauth-java-client/libs-sources/protobuf-java-2.6.1-sources.jar
    * google-oauth-java-client/libs-sources/transaction-api-1.1-sources.jar
    * google-oauth-java-client/libs-sources/xpp3-1.1.4c-sources.jar
    * google-oauth-java-client/libs/commons-logging-1.1.1.jar
    * google-oauth-java-client/libs/google-http-client-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-android-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-appengine-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-gson-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-jackson-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-jackson2-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-jdo-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-protobuf-1.23.0.jar
    * google-oauth-java-client/libs/google-http-client-xml-1.23.0.jar
    * google-oauth-java-client/libs/google-oauth-client-1.23.0.jar
    * google-oauth-java-client/libs/google-oauth-client-appengine-1.23.0.jar
    * google-oauth-java-client/libs/google-oauth-client-java6-1.23.0.jar
    * google-oauth-java-client/libs/google-oauth-client-jetty-1.23.0.jar
    * google-oauth-java-client/libs/google-oauth-client-servlet-1.23.0.jar
    * google-oauth-java-client/libs/gson-2.1.jar
    * google-oauth-java-client/libs/httpclient-4.0.1.jar
    * google-oauth-java-client/libs/httpcore-4.0.1.jar
    * google-oauth-java-client/libs/jackson-core-2.1.3.jar
    * google-oauth-java-client/libs/jackson-core-asl-1.9.11.jar
    * google-oauth-java-client/libs/jdo2-api-2.3-eb.jar
    * google-oauth-java-client/libs/jetty-6.1.26.jar
    * google-oauth-java-client/libs/jetty-util-6.1.26.jar
    * google-oauth-java-client/libs/jsr305-1.3.9.jar
    * google-oauth-java-client/libs/protobuf-java-2.6.1.jar
    * google-oauth-java-client/libs/transaction-api-1.1.jar
    * google-oauth-java-client/libs/xpp3-1.1.4c.jar
    * google-services-3.0.0.jar
    * google-services-3.1.0.jar
    * gson-2.6.2.jar
    * gson-2.6.2.jar
    * gson-2.8.2-javadoc.jar
    * gson-2.8.2-sources.jar
    * gson-2.8.2.jar
    * guava-12.0.jar
    * guava-24.1-jre.jar
    * jettison-1.2.jar
    * json-20140107.jar
    * netty-all-4.0.4.Final.jar
    * netty-all-4.1.24.Final.jar
    * slf4j-api-1.7.25.jar
    * slf4j-simple-1.6.1.jar

* The GAE.css file is required for much of the styling within the authoring environment.

* Engine
    * Certain interactions/behaviors cannot be mixed, but this is handled by the GAE. Mixing them will not crash anything, but will cause unexpected behavior.
    * A summary of these can be read [here](https://docs.google.com/document/d/1ySIgAso5LrclbW8yRnkndx0nSmYM96dGtUxtc-lu2R4/edit).

#### Known bugs, crashes, problems

##### Game Authoring Environment
* The "launch player" button does not currently work.
* The original scrollingGrid attached to the game editor when it is launched is not associated with a level. So, nothing gets saved to that scrolling grid.

* Engine
    * A pushable object being pushed by a pushable object being pushed by an entity (such as the player) only works while moving to the right.
* Game Player
    * If the user click the very thin white bar on the bottom of the VoogaChooser application a nullpointer exception is thrown
    * If the user closes the VoogaChooser application a null pointer exception is thrown
    * Opening the game authoring environment from the player opens a new game not the current game being played
    * If the player character falls of the of the bottom of the screen and the game is not ended, it will causes the user to lose function of the menubar.
#### Extra Features
* A user can replay their progress at any point in a level and at the end of the level. The replay opens in a new screen and closes when it is done. The replay can be started/stopped at any time and toggled between 2x and 0.5 speed as well.

#### Impressions

##### Julia
I entered this project feeling fairly optimistic and excited to make a piece of software this large and complex. Over the course of the project, I felt increasingly disillusioned. Managing a project at this scale with so many highly dependent parts was incredibly difficult, mostly because managing subgroups of people were incredibly difficult. Our team struggled with managing mutually respectful deadlines that allowed other subteams to finish their parts of the project completely and on time. This proved to be an issue at the end of the project, where the authoring environment and data had to make major changes close to the deadline. I do feel proud of what we have accomplished, and I'm glad that I was able to experience this project.

##### Michael
I began this project feeling strongly that the quality and efficacy of our communication would essentially make or break our project, and feeling daunted by the scale and difficulty it presented. As we strove further into the design of the project, I was pleasantly impressed by how confident I felt about our design - we've clearly come very far as capable programmers over the semester. That being said, that confidence may have given way to a lack of dilegence for the importance of communication between parts; when we came together for our midpoint demo, we realized that our standard of communication fell short of what was necessary, and our implementation failed. I've come out of this project with an increased level of respect for two things: our individual potential of programmers, and the relative unimportance of an individual programmer's potential compared to the paramount cruciality of strong, respectful, frequent team communication.

##### Belanie
This project is very challenging because not only do we have to create a complex program, but we also have to coordinate a group of people with different ideas and different schedules. Overall, I feel like we were able to make some really cool features and I learned a lot about working in a team. Being on the data side was really frustrating because we relied so much on other people and had to wait until other people had finished features before being able to check and handle all the errors. I had fun finding bugs and trying to figure them out.

##### Maya
I was really impressed with what everyone did. At the mid-point demo we were in a rough spot, but getting from a game made in the Authoring Environment to being played correctly was really exciting. I think the project is a good exercise in teamwork on a much larger scale than any of us have experienced, which was an experience. As a member of the data team, I did get frustrated at having to rely on others before I could start work. Without knowing what objects we would be serializing and deserializing, we went from either having not much to do to having a lot to do because other parts of the team relied on us. But the project was an experience for sure.

##### Marcus
I'm really glad that I was on backend for this project it was quite the learning experience and a really pleasing experience to see the features come together and mesh in a really cool way. I'm really proud of the overall structure of the engine's code, although it certainly still has room for improvement. It was challenge to keep adding new features and retain an organized and non-repetitive code structure. The project as a whole has been a good learning experience about how to work with such a large group on a coding project, which will be invaluable in the future.

##### Robert
This was an interesting project to do, especially from the engine side of things, with all the raw logic that went into it. Even though there was a lot of coding to do, I did enjoy doing it. The hardest parts of the project comes out of both integration and from managing a team as large as these teams, and while both of these can be frustrating, I think it did work well as a lesson regarding how to manage these challenges in a more formal coding environment, while also framing it in a challenge that is both interesting and has a very clear and honestly pretty cool visual result.

##### Kelley
It was fun to get to know everyone on the team over the course of the end of the semester. It was a very rewarding experience to be a part of such a great team that was able to create a really cool piece of software.  Our group could have used some more time working together during class time, to make sure we were all on the same page.

#### Dorian
I really enjoyed this project because I thought it really stretched our individual capability of thinking about design. It was really nice to work on a project with a team for a longer period of time. I think more discussions and regular meetings made working with my teammates more enjoyable. Our group came together as a random jumble of small groups of people that knew each other for some reason, which was interesting. I really thought the midpoint demo was a massive wakeup call, and I want to say our team reacted well to it. I really enjoyed working side-by-side with some of these teammates, so overall I'm pretty satisfied. I wish the lectures towards to end of the class were more focused on other design patterns than random useful computer science knowledge.

#### Judith
I thought that this project did a wonderful job of teaching me how the real world works in terms of working with people. I had the opportunity to learn from my peers and to become a better coder and planner because of it. I was also able to learn how to deal with situations when things did not go according to plan. I learned how to cooperate with others to reach a mutually understood goal. The point was not to argue or to get our own way; the point was to meet our goal. 
