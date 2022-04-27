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
    private TextField testName;


    @FXML
    public void initialize(){
        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();

        database.resetQuestionbankIterator();
        String temp = database.getNextQuestionbankName();
        while(!Objects.equals(temp, "")){
            availableBanks.getItems().add(temp);
            temp = database.getNextQuestionbankName();
        }


        availableBanks.getSelectionModel().selectFirst();
        //TODO: Make this check if the box was actually populated before updating values
        setQuestionCount(); //Set question count on initial setup
    }



    public void setQuestionCount() {
        String bank = availableBanks.getValue();

        System.out.println(bank);

        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();

        QuestionBank currentBank = database.getQuestionBank(bank);

        System.out.println(currentBank);

        bankDetails.setText(bank + ", Available Questions: " + currentBank.getQuestionCount());

        questionCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, currentBank.getQuestionCount(), currentBank.getQuestionCount()));



    }

    public void createTests() {

        DBMgrHolder holder = DBMgrHolder.getInstance();
        DBMgr database = holder.getDatabase();



        TestCreationController creationController = new TestCreationController();
        Boolean randomize = randomizeOrder.isSelected();
        QuestionBank bank = database.getQuestionBank(availableBanks.getValue());
        System.out.println((availableBanks.getValue()));

        Boolean temp = creationController.createNewTest(availableBanks.getValue(), testName.getText(), testCount.getValue(), questionCount.getValue(), database);


        System.out.println(temp);
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
