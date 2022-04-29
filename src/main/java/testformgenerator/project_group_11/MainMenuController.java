package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


/*
 * This class handles button selections and scene transitions from the main menu
 */
public class MainMenuController {

    //Establish variables corresponding to UI elements
    @FXML
    private Button createBank;

    @FXML
    private Button createQuestion;

    @FXML
    private Button createTest;

    public void changeScene(ActionEvent actionEvent){
        if(actionEvent.getSource() == createBank){ //Transition to question bank scene if selected
            changeSceneHandler("QuestionBank.fxml");
        }else if(actionEvent.getSource() == createQuestion){ //Transition to question creation scene if selected
            changeSceneHandler("createQuestion.fxml");
        }else if(actionEvent.getSource() == createTest){ //Transition to test creation scene if selected
            changeSceneHandler("createTestUI.fxml");
        }
    }

    private void changeSceneHandler(String target){
        Stage stage; //Establish stage object
        Parent root; //Establish parent object
        try {
            stage = (Stage) createBank.getScene().getWindow(); //Get the stage this was triggered from using the create bank button
            root = FXMLLoader.load(getClass().getResource(target)); //Load desired scene into root object
            Scene scene = new Scene(root); //Create new scene using root
            stage.setScene(scene); //Set current stage to new scene
            stage.show(); //Render new scene
        }catch(IOException e){
            System.err.println("An error " + e.getMessage() + " occurred when switching to the" + target + "scene.");
        }
    }

}
