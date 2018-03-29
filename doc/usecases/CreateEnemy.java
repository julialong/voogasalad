package usecases;
import api.data.GAEtoJSON;
import api.gae.GameCreator;
import usecases.sample_object_classes.PlatformGameCreator;

public class CreateEnemy {
	
	GameCreator myGameCreator;
	GAEtoJSON myDataManager;

	    // creates the GameCreator object needed to modify the game
	    void createGame() {
	        // TODO: myDataManager = new **insert sample GAEtoJSON object here
	        myGameCreator = new PlatformGameCreator(myDataManager);

	    }
	    
	    void createEnemy() {
	    		myGameCreator.createNewGameElement();
	    		Enemy villian = new Enemy();
	    		villian.uploadImage();
	    		villian.updateAttributes();
	    
	    }
	    
}