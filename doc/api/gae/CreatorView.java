/**
 * 
 * @author Judi Sanchez
 *
 */
public interface CreatorView {

	/**
	 * Updates the current GUI representation of the AuthoredElements locations
	 * on a particular level's grid
	 */
	public void updateGrid();
	
	
	/**
	 * Updates the portion of thr grid that is currently showm on 
	 * the program's screen. This will be accomplished with a
	 * scrollbar that has a value which will denote the leftmost
	 * x coordindate                                                                                                       	                                        
	 * @param location is the value of the leftmost coordinate on the
	 * viewable grid
	 */
	public void updateGridView(double location);
	
	/**
	 * Opens an editor to change the atributes of a specific AuthoredElement
	 * @param elem is the AuthoredElement for which an editor window will be opened
	 */
	public void openAttributeEditor(AuthoredElement elem);
	
	/**
	 * Opens a new window to allow for multi-window editing 
	 * of the same level or different levels of the game
	 */
	public void openNewWindow();
	
	
	
}
