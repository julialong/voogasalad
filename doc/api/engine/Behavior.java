package api.engine

public interface Behavior {
	/**
	* According to the Object given, defines the actions that should be taken wrt that object.
	*/
	public void behave(Object target);
}