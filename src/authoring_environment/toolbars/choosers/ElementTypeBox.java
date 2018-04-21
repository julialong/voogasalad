package authoring_environment.toolbars.choosers;

import java.io.File;
import java.util.ArrayList;

import authoring_environment.editor_windows.EditorWindow;
import authoring_environment.toolbars.RightBar;
import javafx.scene.control.ComboBox;

/**
 * 
 * @author Michael Acker
 * Date started: 4/21/2018
 *
 */

public class ElementTypeBox extends ComboBox {
	private ArrayList<String> myTypes;
	private RightBar myRightBar;
	
	public ElementTypeBox(RightBar bar) {
		super();
		myTypes = getTypes();
		myRightBar = bar;
		this.getItems().addAll(myTypes);
		this.setPromptText("Elelemt Type");
		this.setOnAction(e -> select());
	}
	
	private ArrayList<String> getTypes() {
		ArrayList<String> types = new ArrayList<String>();
		File folder = new File("./data/authoredElementImages/");
		File[] typeNames = folder.listFiles((folder1, name) -> {
            return (true);
        });
		for (File file : typeNames) {
			types.add(file.getName());
		}
		return types;
	}
	
	private void select() {
		String choice = (String) this.getValue().toString();
		myRightBar.getElementPicker().updateTypeChoice(choice);
	}

}
