package usecases.sample_object_classes;

import api.gae.AuthoredElement;

public class SampleBlock implements AuthoredElement {
	private int myGridX;
	private int myGridY;
	private String myID;
	//Has a bunch of defined properties as well, but only x and y positions on grid are relevant to individual construction
	
	public SampleBlock(int gridX, int gridY, String ID) {
		myGridX = gridX;
		myGridY = gridY;
		myID = ID;
	}

}
