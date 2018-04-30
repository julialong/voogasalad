package authoring_environment.attribute_editor;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class BehaviorAttribute extends Attribute {

    public BehaviorAttribute(){

    }

    @Override
    public void setupInputs(Pane target, String attributeType) {
        List<String> basicAttributesOptions = super.loadAttributes(attributeType);
        VBox container = new VBox();
    }
}
