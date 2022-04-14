package testformgenerator.project_group_11;

public class createQuestion {
    public static final int INPUT_MAX_LENGTH = 50;
    public static final int INPUT_MIN_LENGTH = 1;

    public static final int ANSWERCHOICE_MAX_AMOUNT = 10;
    public static final int ANSWERCHOICE_MIN_AMOUNT = 1;

    //Check Question String
    public static boolean correctQuestionInputLength(String question) {

        if (question.length() > INPUT_MIN_LENGTH && question.length() < INPUT_MAX_LENGTH) {
            return true;
        }
        else {
            return false;
        }

    }

    //Check Answer Choice String Array
    public static boolean correctAnswerInputLengths(String[] answerChoices) {

        for (int i = 0; i < answerChoices.length; i++) {
            if (!(answerChoices[i].length() > INPUT_MIN_LENGTH && answerChoices[i].length() < INPUT_MAX_LENGTH)) {
                return false;
            }
        }
        return true;
    }

    public static boolean correctAnswerChoiceAmount(String[] answerChoices) {

        if (answerChoices.length > ANSWERCHOICE_MIN_AMOUNT && answerChoices.length < ANSWERCHOICE_MAX_AMOUNT) {
            return true;
        }
        else {
            return false;
        }
    }

    //Check to make sure correct answer choice is selected and makes sense
    public static boolean correctAnswerSelected(String[] answerChoices, int correctAnswer) {

        if (correctAnswer > answerChoices.length-1 || correctAnswer < 0) {
            return false;
        }
        return true;
    }


    public static String createQuestionAction(String question, String[] answerChoices, int correctAnswer) {

        if (!correctQuestionInputLength(question)) {
            return "Invalid Question Input";
        }
        else if (!correctAnswerInputLengths(answerChoices)) {
            return "Invalid Answer Choice Input";
        }
        else if (!correctAnswerChoiceAmount(answerChoices)) {
            return "Invalid Answer Choice Amount";
        }
        else if (!correctAnswerSelected(answerChoices, correctAnswer)) {
            return "Invalid Correct Answer";
        }

        return "Created Question Sucessfully";
    }
}
