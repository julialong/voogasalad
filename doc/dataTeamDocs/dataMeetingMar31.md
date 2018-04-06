DATA TEAM MEETING
===
### 31 Mar 18

* DB file arrangement
* Serialization
* Design pattern arrangement

Folder setup
* Game
	* default settings
	* all levels
	* user
		* preferences
		* progress on level (if exists)

New Game
* instance of Load Game

Load Game

FilePath object
	* public FilePath(String game, String player);

	* public FilePath(String game, String level, String player);

Game objects implement 2 interfaces, toJson and fromJson


Does editing a game in GAE affect levels that users have progress in?
	* position reset to start but settings and high scores, etc. saved

By team meeting tomorrow
	* know how to write to (Maya) and read from (Belanie) JSON