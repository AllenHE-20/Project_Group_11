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

    @FXML
    private Button submitButton;

    @FXML
    private Button homeButton;

    @FXML
    private ComboBox<String> availableBanks;

    @FXML
    private Label bankDetails;

    @FXML
    private Spinner<Integer> questionCount;

    @FXML
    private RadioButton randomizeOrder;

    @FXML
    private Spinner<Integer> testCount;


    @FXML
    public void initialize(){
        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();

        database.resetQuestionbankIterator();
        String temp= database.getNextQuestionbankName();
        while(!Objects.equals(temp, "")){
            availableBanks.getItems().add(temp);
            temp = database.getNextQuestionbankName();
        }


        availableBanks.getSelectionModel().selectFirst();
    }

    // find way to initialize this on scene open
    public void initializeBanks() {

        // filler database until persistence is achieved
        DBMgr database = new DBMgr();
        QuestionBank qb = new QuestionBank("Bank 1");
        Question q = new Question("What is today's date?", "July 24th, 2022", "August 16th, 2022", "January 24th, 2022", "April 21st, 2022");
        qb.addNewQuestion(q);
        database.addBank(qb);


        availableBanks.getItems().add(qb.getName());
        availableBanks.getSelectionModel().selectFirst();

    }

    public void setQuestionCount() {
        String bank = availableBanks.getValue();

        System.out.println(bank);

        // filler database until persistence is achieved
        DBMgr database = new DBMgr();
        QuestionBank qb = new QuestionBank("Bank 1");
        Question q = new Question("What is today's date?", "July 24th, 2022", "August 16th, 2022", "January 24th, 2022", "April 21st, 2022");
        Question q1 = new Question("What is today's date?", "July 24th, 2022", "August 16th, 2022", "January 24th, 2022", "April 21st, 2022");
        qb.addNewQuestion(q);
        qb.addNewQuestion(q1);

        database.addBank(qb);

        QuestionBank currentBank = database.getQuestionBank(bank);

        System.out.println(currentBank);

        bankDetails.setText(bank + ", Available Questions: " + currentBank.getQuestionCount());

        questionCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, currentBank.getQuestionCount(), currentBank.getQuestionCount()));



    }

    public void createTests() {

        // filler database until persistence is achieved
        DBMgr database = new DBMgr();
        QuestionBank qb = new QuestionBank("Bank 1");
        database.addBank(qb);

        Boolean randomize = randomizeOrder.isSelected();
        QuestionBank bank = database.getQuestionBank(availableBanks.getValue());

        System.out.println(randomize);
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
