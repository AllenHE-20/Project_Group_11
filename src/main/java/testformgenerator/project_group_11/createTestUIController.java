package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Objects;

public class createTestUIController {

    //TODO: Scene cannot be reached if there are no question banks
    //Establish variables corresponding to UI elements
    @FXML
    private Button submitButton;

    @FXML
    private Button homeButton;

    @FXML
    private ComboBox<String> availableBanks;

    @FXML
    private Label bankDetails;

    @FXML
    private Label errorLabel;

    @FXML
    private Spinner<Integer> questionCount;

    @FXML
    private RadioButton randomizeOrder;

    @FXML
    private Spinner<Integer> testCount;

    @FXML
    private TextField testName;


    @FXML
    public void initialize(){
        //TODO: Update the form count spinner to have a minimum of two
        DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve singleton instance
        DBMgr database = holder.getDatabase(); //Retrieve database object

        database.resetQuestionbankIterator(); //Set question bank iterator to start
        String temp = database.getNextQuestionbankName(); //Get first question bank if present
        while(!Objects.equals(temp, "")){ //Loop until the string denoting end of list is returned
            availableBanks.getItems().add(temp); //Add bank name to dropdown box
            temp = database.getNextQuestionbankName(); //Attempt to get next question bank name
        }


        availableBanks.getSelectionModel().selectFirst(); //Automatically select first item
        //TODO: Make this check if the box was actually populated before updating values
        setQuestionCount(); //Set question count on initial setup
    }



    public void setQuestionCount() {
        String bank = availableBanks.getValue(); //Get name of selected bank

        //System.out.println(bank);

        DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve database holder singleton
        DBMgr database = holder.getDatabase(); //Retrieve database

        QuestionBank currentBank = database.getQuestionBank(bank); //Search for selected bank

        //System.out.println(currentBank);

        bankDetails.setText(bank + ", Available Questions: " + currentBank.getQuestionCount()); //Set details label with bank name & question count

        questionCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, currentBank.getQuestionCount(), currentBank.getQuestionCount())); //Set up spinner



    }

    public void createTests() {

        DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve DBMGrHolder singleton
        DBMgr database = holder.getDatabase(); //Retrieve database



        TestCreationController creationController = new TestCreationController(); //Initialize test creation controller
        Boolean randomize = randomizeOrder.isSelected(); //Save if order randomization is selected
        QuestionBank bank = database.getQuestionBank(availableBanks.getValue()); //TODO: Is this needed?
        //System.out.println((availableBanks.getValue()));

        Boolean temp = creationController.createNewTest(availableBanks.getValue(), testName.getText(), testCount.getValue(), questionCount.getValue(), database); //Attempt test creation with given information

        if(temp){
            database.setPersistentMessage("Test " + testName.getText() + " created successfully."); //If test creation succeeded, set confirmation message
            changeSceneHandler("confirmationScreen.fxml"); //Transition to confirmation screen
        }else{
            errorLabel.setText("An invalid input was detected. Please check all inputs."); //Show error message if an input is invalid
        }

    }


    public void triggerSceneChange(ActionEvent actionEvent){
        if(actionEvent.getSource() == homeButton){ //If the home button was selected, transition to main menu scene
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
