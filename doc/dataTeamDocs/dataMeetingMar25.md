DATA TEAM MEETING
===
### 25 Mar 18

ReadInAuthor class
* gets data from a created game, saves to .xml
	* id of items to access individuals - starts with sequence to identify item type
* method that real-time (during game running) inserts items
	* adds to XMl file, tells creation to re-read file?
* objects and location - Map<Item, Point>
* createXML()

ReadInGame class
* save state of game as is (save progress)

Creation class
* reads data from XML and creates a game - passes to Game Player
* readXML()

Exporting data file in middle of playing
* is .xml file edited during gameplay?
	* ex. if enemy killed, is that enemy in the file removed?
* authoring environment change edits .xml
* game engine changes don't change

Needed files:
* folder of images of possible sprites/items in game
* template XML file with subsets already made
* general setup XML file

Need to talk to Game Engine and Game Player groups to discuss integration