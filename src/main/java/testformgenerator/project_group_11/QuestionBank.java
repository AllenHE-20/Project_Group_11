package testformgenerator.project_group_11;

import java.util.Vector;

public class QuestionBank {
    public static final int INPUT_MAX_LENGTH = 50;
    public static final int INPUT_MIN_LENGTH = 1;
    private Vector<Question> questionStore; //Use vector to allow easy storage expansion
    private String name;
    private int questionCount=0;
    private int currentItem=0; //Used to track which question to dispense.

    public QuestionBank(String newName) { //Only constructor because a name is required to search.
        name = newName;
        questionStore = new Vector<Question>();
    }

    public Question getNext() {
        Question temp = questionStore.get(currentItem); //Save current item
        currentItem = (currentItem + 1) % questionCount; //Increment counter, looping if necessary.
        return temp;
    }

    public boolean addNewQuestion(Question newQ) {
        boolean status =  questionStore.add(newQ); //Attempt to add a question to the vector.
        if(status) { //Only increment count if addition was successful.
            questionCount++;
        }
        return status;
    }

    public String getName() { //Return name for search purposes.
        return name;
    }

    public int getQuestionCount() { //Return question count for validation purposes.
        return questionCount;
    }

    //check if name length is valid
    public static boolean nameCorrectLength(String name){
        if (name.length() < INPUT_MIN_LENGTH || name.length() > INPUT_MAX_LENGTH)
            return false;
        return true;
    }

    public static String createQuestionBankAction(String name, DBMgr targetDatabase) { //Maybe this should also receive the DBMgr so that it can add the bank to it.

        if (!nameCorrectLength(name)) {
            return "Invalid Name"; //This multiple return style is bad practice
        }

        targetDatabase.addBank(new QuestionBank(name));
        return "Created Question Bank Successfully";
    }
}
