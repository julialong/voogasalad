package engine.behavior;
/**
 * Defines how an object reacts to another object, usually with regards to a PlayerCharacter.
 * Mostly for enemy AI.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public interface Behavior {
	/**
	* According to the Object given, defines the actions that should be taken wrt that object.
	*/
	public abstract void behave(Object target);
}
