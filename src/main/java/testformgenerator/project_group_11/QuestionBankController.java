package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();

        String result = QuestionBank.createQuestionBankAction(answer, database);

        if (result.equals("Created Question Bank Successfully")){
            //Change scene to splash page
            database.setPersistentMessage("Created question bank " + answer + " successfully.");
            changeSceneHandler("confirmationScreen.fxml");
        }else{
            //Set invalidQuestionBankLabel message to result
            invalidQuestionBankLabel.setText(result);
        }
    }

    public void triggerSceneChange(ActionEvent actionEvent){
        if(actionEvent.getSource() == homeButton){
            changeSceneHandler("mainMenu.fxml");
        }
    }


    private void changeSceneHandler(String target){
        Stage stage;
        Parent root;
        try {
            stage = (Stage) homeButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(target));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.err.println("An error " + e.getMessage() + " occurred when switching to the create question scene.");
        }
    }

}
