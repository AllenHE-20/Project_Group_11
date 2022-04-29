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

    //These variables establish where the elements in the UI are mapped to to enable interaction
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
    } //Triggered when a question bank is submitted

    private void submitQuestionBank(){ //Check if this needs to throw an exception
        String answer = bankName.getText(); //Retrieve name input from field
        DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve DBHolder singleton instance
        DBMgr database = holder.getDatabase(); //Retrieve database

        String result = QuestionBank.createQuestionBankAction(answer, database); //Attempt to create question bank

        if (result.equals("Created Question Bank Successfully")){ //Check if creation succeeded
            database.setPersistentMessage("Created question bank " + answer + " successfully."); //Set message for confirmation screen
            changeSceneHandler("confirmationScreen.fxml"); //Transition to confirmation screen
        }else{
            //Set invalidQuestionBankLabel message to result
            invalidQuestionBankLabel.setText(result); //Set error message to tell user what's wrong
        }
    }

    public void triggerSceneChange(ActionEvent actionEvent){
        if(actionEvent.getSource() == homeButton){ //When user selects home button, send them to main menu
            changeSceneHandler("mainMenu.fxml");
        }
    }


    private void changeSceneHandler(String target){
        Stage stage; //Establish stage object
        Parent root; //Establish parent object
        try {
            stage = (Stage) homeButton.getScene().getWindow(); //Get the stage this was triggered from using the home button
            root = FXMLLoader.load(getClass().getResource(target)); //Load desired scene into root object
            Scene scene = new Scene(root); //Create new scene using root
            stage.setScene(scene); //Set current stage to new scene
            stage.show(); //Render new scene
        }catch(IOException e){
            System.err.println("An error " + e.getMessage() + " occurred when switching to the" + target + "scene.");
        }
    }

}
