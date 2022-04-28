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

    @FXML
    private ComboBox<String> availableBanks;

    public CreateQuestionController(){
        //Empty constructor


    }


    @FXML
    public  void initialize(){
        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();

        database.resetQuestionbankIterator();
        String temp = database.getNextQuestionbankName();
        while(!Objects.equals(temp, "")){
            availableBanks.getItems().add(temp);
            temp = database.getNextQuestionbankName();
        }


        availableBanks.getSelectionModel().selectFirst();
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
            DBMgrHolder holder = DBMgrHolder.getInstance();
            DBMgr database = holder.getDatabase();
            QuestionBank temp = database.getQuestionBank(availableBanks.getValue());
            //TODO: Does this need to check for a null bank? Should it get te bank outside the if statement?
            Question questHold = new Question(questField.getText(), answers[0], answers[1], answers[2], answers[3]);
            temp.addNewQuestion(questHold);

            database.setPersistentMessage(result);
            changeSceneHandler("confirmationScreen.fxml");
        }else{
            //Set invalidQuestionLabel message to result
            invalidQuestionLabel.setText(result);
        }


    }//End of submitQuestion


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
