import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static testformgenerator.project_group_11.createQuestion.createQuestionAction;

class createQuestionTest {

    @Test
    void test_one(){ //All inputs valid
        String[] a = {"1", "3", "4", "5"};
        assertEquals("Created Question Sucessfully", createQuestionAction("What is 2+2?", a, 3));

    }

    @Test
    void test_two(){ //Invalid question
        String[] a = {"1", "3", "4", "5"};
        assertEquals("Invalid Question Input", createQuestionAction("What is 2+222+222222+22222222222222222222+222222222222222+222222222222222222222222+2222222+22222222222222222+2222222222+2222222222222+2222222222222+222222222222222222222222222222222222?", a, 3));
    }

    @Test
    void test_three(){ //Exceptional question
        String[] a = {"1", "3", "4", "5"};
        assertEquals("Invalid Question Input", createQuestionAction("", a, 3));
    }

    @Test
    void test_four(){ //Exceptional answer
        String[] a = {"1", "333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333", "4", "5"};
        assertEquals("Invalid Answer Choice Input", createQuestionAction("What is 2+2?", a, 3));
    }

    @Test
    void test_five(){ //Invalid answer
        String[] a = {"1", "", "4", "5"};
        assertEquals("Invalid Answer Choice Input", createQuestionAction("What is 2+2?", a, 3));
    }

    @Test
    void test_six(){ //Exceptional answer
        String[] a = {"1", "3", "4", "5", "22", "23", "0", "92", "67", "53", "678", "2453", "24", "781"};
        assertEquals("Invalid Answer Choice Amount", createQuestionAction("What is 2+2?", a, 3));
    }

    @Test
    void test_seven(){ //Exceptional answer
        String[] a = {};
        assertEquals("Invalid Answer Choice Amount", createQuestionAction("What is 2+2?", a, 3));
    }

    @Test
    void test_eight(){ //Invalid correct answer
        String[] a = {"1", "3", "4", "5"};
        assertEquals("Invalid Correct Answer Selection", createQuestionAction("What is 2+2?", a, 5));
    }
}