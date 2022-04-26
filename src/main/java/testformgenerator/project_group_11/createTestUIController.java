package testformgenerator.project_group_11;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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


}
