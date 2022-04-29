package testformgenerator.project_group_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CreateQuestionController {


    //Create variables corresponding to onscreen UI objects
    @FXML
    private Button homeButton;

    @FXML
    private Button submitButton;

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

    @FXML
    private ComboBox<String> availableBanks;

    public CreateQuestionController(){
        //Empty constructor


    }


    @FXML
    public  void initialize(){ //Perform setup actions
        DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve DBMgrHolder singleton
        DBMgr database = holder.getDatabase(); //Retrieve database object

        database.resetQuestionbankIterator(); //Reset question bank iterator
        String temp = database.getNextQuestionbankName(); //Get first question bank name
        while(!Objects.equals(temp, "")){ //Loop until all question banks exhausted
            availableBanks.getItems().add(temp); //Add current bank name to dropdown box
            temp = database.getNextQuestionbankName(); //Get next question bank name
        }


        availableBanks.getSelectionModel().selectFirst(); //Pre-select first bank
    }



    public void tryCreateQuestion(ActionEvent actionEvent) {
        submitQuestion();
    }

    private void submitQuestion(){
        String[] answers = {ansAField.getText(), ansBField.getText(), ansCField.getText(), ansDField.getText()}; //Get all answers from text fields

        int correctAnswer=99; //initialize to 99 so that if not changed it is caught by createQuestionAction

        RadioButton selectedAnswerButton = (RadioButton) answerGroup.getSelectedToggle(); //Retrieve selected correct answer
        String selectedAnsValue="-"; //Initialize to invalid value
        if(selectedAnswerButton != null) //If an answer button was selected, set selectedAnswerButton to its label contents
            selectedAnsValue = selectedAnswerButton.getText();

        switch(selectedAnsValue.charAt(0)){
            case 'A': correctAnswer = 0; break;
            case 'B': correctAnswer = 1; break;
            case 'C': correctAnswer = 2; break;
            case 'D': correctAnswer = 3; break;
            default: break; //Take no action b/c correctAnswer is already initalized w/ a failing value
        }


        String result = createQuestion.createQuestionAction(questField.getText(), answers, correctAnswer); //Validate question inputs

        if (result.equals("Created Question Sucessfully")){ //If all inputs valid, save question
            //Add to question bank, change scene
            DBMgrHolder holder = DBMgrHolder.getInstance(); //Retrieve DBMgrHolder singleton
            DBMgr database = holder.getDatabase(); //Retrieve database
            QuestionBank temp = database.getQuestionBank(availableBanks.getValue()); //Get selected bank from database
            //TODO: Does this need to check for a null bank? Should it get te bank outside the if statement?
            Question questHold = new Question(questField.getText(), answers[0], answers[1], answers[2], answers[3]); //Create question object
            temp.addNewQuestion(questHold); //Store new question

            database.setPersistentMessage("Created question: " + questField.getText() + " in bank: " + availableBanks.getValue() + " successfully."); //Set confirmation screen message
            changeSceneHandler("confirmationScreen.fxml"); //Transition to confirmation screen
        }else{
            //Set invalidQuestionLabel message to result
            invalidQuestionLabel.setText(result);
        }


    }//End of submitQuestion


    public void triggerSceneChange(ActionEvent actionEvent){ //If home button pressed, transition to main menu
        if(actionEvent.getSource() == homeButton){
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
