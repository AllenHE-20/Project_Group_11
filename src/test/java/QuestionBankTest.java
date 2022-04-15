import org.junit.jupiter.api.Test;
import testformgenerator.project_group_11.DBMgr;
import testformgenerator.project_group_11.QuestionBank;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBankTest {
    DBMgr testDB = new DBMgr();

    @Test
    void test_one(){
        assertEquals("Created Question Bank Successfully", QuestionBank.createQuestionBankAction("set1", testDB));
    }

    @Test
    void test_two(){
        assertEquals("Name has been used before, please use a new name.", QuestionBank.createQuestionBankAction("set1", testDB));
    }

    @Test
    void test_three(){
        assertEquals("Name does not fit the character limit, please try again.", QuestionBank.createQuestionBankAction("", testDB));
    }

    @Test
    void test_four(){
        assertEquals("Name does not fit the character limit, please try again.", QuestionBank.createQuestionBankAction("CS3354 Exam 1 Set 1 2022 Spring May-June - Narayanasami", testDB));
    }


}