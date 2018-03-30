package usecases;

/*
 * Adds elements to a level.
 */
public class AddElement {
	public AddElement(Level level, GameObject object) {
		level.addObject(object);
	}
	
	public AddElement(Level level, List<GameObject> objects){
		level.setObjects(objects);
	}
}