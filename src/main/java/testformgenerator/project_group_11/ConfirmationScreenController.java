package testformgenerator.project_group_11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmationScreenController {

    //Establish variables corresponding to onscreen items
    @FXML
    private Button okButton;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize(){ //Set up scene
        DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve DBMgrHolder singleton
        DBMgr database = holder.getDatabase(); //Retrieve database

        messageLabel.setText(database.getPersistentMessage()); //Set message from value stored in database
        database.setPersistentMessage("Error: This screen should be unreachable."); //Updates persistent message to show an error if this screen is reached again improperly
    }



    public void goToMenu(){
        Stage stage; //Establish stage object
        Parent root; //Establish parent object
        try {
            stage = (Stage) okButton.getScene().getWindow(); //Get the stage this was triggered from using the home button
            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml")); //Load main menu scene into root object
            Scene scene = new Scene(root); //Create new scene using root
            stage.setScene(scene); //Set current stage to new scene
            stage.show(); //Render new scene
        }catch(IOException e){
            System.err.println("An error " + e.getMessage() + " occurred when switching to the main menu scene.");
        }
    }
}
