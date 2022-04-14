package testformgenerator.project_group_11;

import java.util.Vector;

public class QuestionBank {
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
}
