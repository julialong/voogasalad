package authoring_environment.attribute_editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.util.List;

public class BehaviorAttribute extends Attribute {

    public BehaviorAttribute(){

    }

    /**
     * Creates the container for the behavior selection model
     * and creates the listener which will automatically
     * create the input lines for each one selected
     */
    @Override
    public void setupInputs(Pane target, String attributeType) {
        VBox container = new VBox();
        List<String> behaviorAttributesOptions = super.loadAttributes(attributeType);
        ObservableList<String> options = FXCollections.observableArrayList(behaviorAttributesOptions);
        ListView<String> chooser = new ListView<>(options);
        container.getChildren().add(chooser);
        chooser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        chooser.setOnMouseClicked(event -> {
            List<String> selectedItems = chooser.getSelectionModel().getSelectedItems();
            for(String option : selectedItems){
                container.getChildren().add(getOptionContents(option));
            }
        });
    }


    private HBox getOptionContents(String option){
        //TODO: GET EVERY SETTABLE SPECIFICATION FOR EACH OPTION IN THE BEHAVIOR ATTRIBUTE
        //TODO: information in the resource file, returns an hbox which is just like the
        //TODO: HBox for the basic attribute
        return null;
    }
}
