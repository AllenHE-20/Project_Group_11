package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateQuestionController {
    public CreateQuestionController(){
        //Empty constructor
    }

    @FXML
    private Button homeButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField questField;

    @FXML
    private TextField ansAField;

    @FXML
    private TextField ansBField;

    @FXML
    private TextField ansCField;

    @FXML
    private TextField ansDField;




    public void tryCreateQuestion(ActionEvent actionEvent) {
        submitQuestion();
    }

    private void submitQuestion(){ //Check if this needs to throw an exception
        //

    }
}
