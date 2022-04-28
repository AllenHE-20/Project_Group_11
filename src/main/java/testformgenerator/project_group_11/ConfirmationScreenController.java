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

    @FXML
    private Button okButton;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize(){
        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();

        messageLabel.setText(database.getPersistentMessage());
        database.setPersistentMessage("Error: This screen should be unreachable."); //Updates persistent message to show an error if this screen is reached again improperly
    }

    public void goToMenu(){
        Stage stage;
        Parent root;
        try {
            stage = (Stage) okButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.err.println("An error " + e.getMessage() + " occurred when switching to the create question scene.");
        }
    }
}
