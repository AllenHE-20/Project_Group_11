package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button createBank;


    public void changeToBank(ActionEvent actionEvent) throws IOException {createBankSceneChange();}

    private void createBankSceneChange() throws IOException {
        //
        Stage stage;
        Parent root;
        try {
            stage = (Stage) createBank.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("QuestionBank.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.err.println("An error " + e.getMessage() + " occurred when switching to the create question scene.");
        }
    }
}
