package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.nio.charset.StandardCharsets;

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

    @FXML
    private ToggleGroup answerGroup;

    public CreateQuestionController(){
        //Empty constructor


    }





    public void tryCreateQuestion(ActionEvent actionEvent) {
        submitQuestion();
    }

    private void submitQuestion(){ //Check if this needs to throw an exception
        String[] answers = {ansAField.getText(), ansBField.getText(), ansCField.getText(), ansDField.getText()};

        int correctAnswer=99; //initialize to 99 so that if not changed it is caught by createQuestionAction

        RadioButton selectedAnswerButton = (RadioButton) answerGroup.getSelectedToggle();
        String selectedAnsValue="-";
        if(selectedAnswerButton != null)
            selectedAnsValue = selectedAnswerButton.getText();

        switch(selectedAnsValue.charAt(0)){
            case 'A': correctAnswer = 0; break;
            case 'B': correctAnswer = 1; break;
            case 'C': correctAnswer = 2; break;
            case 'D': correctAnswer = 3; break;
            default: break; //Take no action b/c correctAnswer is already initalized w/ a failing value
        }


        String result = createQuestion.createQuestionAction(questField.getText(), answers, correctAnswer);

        if (result.equals("Created Question Sucessfully")){
            //Add to question bank, change scene
            invalidQuestionLabel.setText(result); //In final version, this section will retrieve DBMgr singleton, get the question bank, and add the question
        }else{
            //Set invalidQuestionLabel message to result
            invalidQuestionLabel.setText(result);
        }


    }
}
