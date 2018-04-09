package authoring_environment;


/**
 * Authored Element Creator allows for the user to create a new AuthoredElement.  
 * @author Judi Sanchez
 * Date started: April 2 2018
 *
 */
public class AuthoredElementCreator {
	
	
	/**
	 * This constructor is called when the AddElementButton is pushed on the right-side toolbar.
	 * It creates a new AttributeEditor for a specific gameEntity
	 */
	public AuthoredElementCreator() {
		GameEntity element = new GameEntity();
		Double elementID = element.getID();
		AttributeEditor editor= new AttributeEditor(element, elementID);
			
	}


}