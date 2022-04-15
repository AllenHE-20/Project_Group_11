package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class QuestionBankController {

    @FXML
    private Button homeButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField bankName;

    @FXML
    private Label invalidQuestionBankLabel;

    public void tryCreateQuestionBank(ActionEvent actionEvent){
        submitQuestionBank();
    }

    private void submitQuestionBank(){ //Check if this needs to throw an exception
        String answer = bankName.getText();
        DBMgr dummy = new DBMgr(); //In final release, this should pull down the singleton DBMgr instance

        String result = QuestionBank.createQuestionBankAction(answer, dummy);

        if (result.equals("Created Question Bank Successfully")){
            //Add to question bank, change scene
        }else{
            //Set invalidQuestionBankLabel message to result
            invalidQuestionBankLabel.setText(result);
        }


    }

}
