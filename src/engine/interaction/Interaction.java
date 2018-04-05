package engine.interaction;
/**
 * Defines what happens when two objects come into contact.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public interface Interaction {
	/**
	* Given the pair of objects given, defines the rules for how the two interact with each other when they collide.
	*/
	public void interact(Object o1, Object o2);
}
