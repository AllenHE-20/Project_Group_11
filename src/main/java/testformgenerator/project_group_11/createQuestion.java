package testformgenerator.project_group_11;

public class createQuestion {
    public static final int INPUT_MAX_LENGTH = 50;
    public static final int INPUT_MIN_LENGTH = 1;

    public static final int ANSWERCHOICE_MAX_AMOUNT = 10;
    public static final int ANSWERCHOICE_MIN_AMOUNT = 1;

    //Check Question String length
    public static boolean correctQuestionInputLength(String question) {

        if (question.length() > INPUT_MIN_LENGTH && question.length() < INPUT_MAX_LENGTH) {
            return true;
        }
        else {
            return false;
        }

    }

    //Check Answer Choice String Array for lengths
    public static boolean correctAnswerInputLengths(String[] answerChoices) {

        for (int i = 0; i < answerChoices.length; i++) {
            if (!(answerChoices[i].length() >= INPUT_MIN_LENGTH && answerChoices[i].length() <= INPUT_MAX_LENGTH)) {
                return false;
            }
        }
        return true;
    }

    //Check if there is the correct number of answer choices
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

        //TODO: FIX THIS. Using an elif here is very bad, if an early condition is correct later ones are missed
        if (!correctQuestionInputLength(question)) { //Validate question length
            return "Invalid Question Input"; //Return error
        }
        else if (!correctAnswerInputLengths(answerChoices)) { //Validate answer lengths
            return "Invalid Answer Choice Input"; //Return error
        }
        else if (!correctAnswerChoiceAmount(answerChoices)) { //Validate number of answers
            return "Invalid Answer Choice Amount"; //Return error
        }
        else if (!correctAnswerSelected(answerChoices, correctAnswer)) { //Validate correct answer selection
            return "Invalid Correct Answer Selection";
        }

        return "Created Question Sucessfully"; //Return if all inputs are valid
    }
}
