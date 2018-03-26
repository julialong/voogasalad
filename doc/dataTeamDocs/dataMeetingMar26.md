DATA TEAM MEETING
===
### 26 Mar 18

* scratch firebase idea
* use local directories
	* folders for each game contain setup, level files, images, etc.
	* "make game" command makes new folder
	* "edit game" will search folders to find editable game
* DynamoDB
	* https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Java.03.html
	* Tables: one table per type of item
		* rows are individual items, values are x, y, other attributes

* GAE > JSON
	* add to List changedItems constantly
	* every x seconds, GAE calls Expoerter.updateNew(changedItems)
	* clear changedItems after updateNew() call

	* GAE needed methods/variables
		* List addedItems
		* List changedItems
		* List removedItems
		* addtoNew(item)
		* passItemstoData()
			* at end, calls addedItems.clear(), changedItems.clear(), removedItems.clear()

	* data class takes items, converts them to JSON

	* Write
		* update(Map<filetoEdit, editedLists>)
			* editedLists is List<List<item>>. addedItems, changedItems, removedItems
			* GAE calls this in passItemstoData()
			* implemented based on add, change, or remove
		* load()
			* GAE calls Write.load(gameName)
				* if folder gameName exists, go into it and export for editing
				* if golder gameName does not exist, make folder, set up defaults/templates, export for editing

* JSON > GAE
	* passtoGAE()
		* returns Map<files, itemsInFile>
			* reflection to create instances of level/settings classes (from filename) and objects
				* newLevel()
					* for enemy:enemies
						* aGoomba = new Goomba();
						* aGoomba.xPos = x;
						* aGoomba.yPos = y;
						* newLevel.enemies.add(aGoomba);
			* itemsInFile is List<List<Items>>, each list is Enemies, Bricks, Coins, etc. according to classes GAE wrote

* JSON > GE/GP
	* same as JSON > GAE, just with whatever objects GE/GP uses

* GE/GP > JSON
	* same as GAE > JSON, just with whatever objects GE/GP uses