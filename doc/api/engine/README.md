# Game Engine API

Authors:
* Marcus Oertle
* Robert Gitau

### List of game engine interfaces and their public methods:
* PlayerCharacter
	* move();
	* setMovementType();
	* setHealth();
	* setWeapon();
	* useWeapon();
	* setInteraction();
	* addPowerUp();
	* removePowerUp();
* Enemy
	* move();
	* setMovementType();
	* setHealth();
	* setWeapon();
	* useWeapon();
	* setInteraction();
	* setBehavior();
* GameObject
	* move();
	* setMovementType();
	* setHealth();
	* setInteraction();
	* setBehavior();
* PowerUp
	* setDuration();
	* setInteraction();
* Projectile
	* move();
	* setMovementType();
	* setInteraction();
* Weapon
	* setAttackMethod();
		* (ie. swing, stab, shoot, etc...)
	* attack();