import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testformgenerator.project_group_11.DBMgr;
import testformgenerator.project_group_11.Question;
import testformgenerator.project_group_11.QuestionBank;
import testformgenerator.project_group_11.TestCreationController;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTest_tests {
    static DBMgr testDB = null;
    static TestCreationController demoCtrl = null;

    @BeforeEach
    void setUp() throws Exception {
        testDB = new DBMgr();
        QuestionBank setupBank = new QuestionBank("demoBank");
        Question qOne = new Question("Question 1?", "Answer One", "Answer Two", "Answer Three", "Answer Four");
        Question qTwo = new Question("Question 2?", "Answer 5", "Answer 6", "Answer 7", "Answer 8");
        Question qThree = new Question("Question 3?", "Answer 9", "Answer 10", "Answer 11", "Answer 12");
        Question qFour = new Question("Question 4?", "Answer 13", "Answer 14", "Answer 15", "Answer 16");
        setupBank.addNewQuestion(qOne);
        setupBank.addNewQuestion(qTwo);
        setupBank.addNewQuestion(qThree);
        setupBank.addNewQuestion(qFour);
        testDB.addBank(setupBank);
        demoCtrl = new TestCreationController();
    }

    @AfterEach
    void tearDown() throws Exception {
        testDB = null;
        demoCtrl = null;
    }

    @Test
    void test_one() {
        //All inputs valid
        boolean result = demoCtrl.createNewTest("demoBank", "Example Test", 2, 2, testDB);
        assertTrue(result);
    }

    @Test
    void test_two() {
        //Invalid question count
        boolean result = demoCtrl.createNewTest("demoBank", "Example Test", 2, 0, testDB);
        assertFalse(result);
    }

    @Test
    void test_three() {
        //Invalid form count
        boolean result = demoCtrl.createNewTest("demoBank", "Example Test", 0, 2, testDB);
        assertFalse(result);
    }

    @Test
    void test_four() {
        //Invalid test name
        boolean result = demoCtrl.createNewTest("demoBank", "", 2, 2, testDB);
        assertFalse(result);
    }

    @Test
    void test_five() {
        //Invalid question bank name
        boolean result = demoCtrl.createNewTest("notHere", "Example Test", 2, 2, testDB);
        assertFalse(result);
    }

    @Test
    void test_six() {
        //Exceptional question count
        boolean result = demoCtrl.createNewTest("demoBank", "Example Test", 2, -77, testDB);
        assertFalse(result);
    }

    @Test
    void test_seven() {
        //Exceptional form count
        boolean result = demoCtrl.createNewTest("demoBank", "Example Test", -77, 2, testDB);
        assertFalse(result);
    }

    @Test
    void test_eight() {
        //Exceptional question bank name
        boolean result = demoCtrl.createNewTest("", "Example Test", 2, 2, testDB);
        assertFalse(result);
    }

	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
}
