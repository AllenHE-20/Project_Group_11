package testformgenerator.project_group_11;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


/*
 * Extraneous class created with project template to control demo scene
 * TODO: Delete HelloController.java & hello-view.fxml
 */
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}