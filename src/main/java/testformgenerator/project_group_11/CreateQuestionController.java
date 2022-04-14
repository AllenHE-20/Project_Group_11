package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateQuestionController {



    @FXML
    private Button homeButton;

    @FXML
    private Button submitButton;

    /*
    @FXML
    private ChoiceBox<Integer> correctAnswerBox;
    */

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

    @FXML
    private Label invalidQuestionLabel;

    public CreateQuestionController(){ //Choicebox does not work yet
        //Empty constructor
        /*
        correctAnswerBox = new ChoiceBox<Integer>();
        correctAnswerBox.getItems().add(1);
        correctAnswerBox.getItems().add(2);
        correctAnswerBox.getItems().add(3);
        correctAnswerBox.getItems().add(4);
        */

    }



    public void tryCreateQuestion(ActionEvent actionEvent) {
        submitQuestion();
    }

    private void submitQuestion(){ //Check if this needs to throw an exception
        String[] answers = {ansAField.getText(), ansBField.getText(), ansCField.getText(), ansDField.getText()};


        String result = createQuestion.createQuestionAction(questField.getText(), answers, 1);

        if (result.equals("Created Question Sucessfully")){
            //Add to question bank, change scene
        }else{
            //Set invalidQuestionLabel message to result
            invalidQuestionLabel.setText(result);
        }


    }
}
