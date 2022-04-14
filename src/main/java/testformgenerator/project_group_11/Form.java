package testformgenerator.project_group_11;

import java.util.Vector;

public class Form {
    private Vector<Question> questionStore; //Store questions and state information
    private int questionMax=0;
    private int questionCount=0;

    public Form(int questionQuantity) { //Set up vector and storage capacity info
        questionCount = questionQuantity;
        questionStore = new Vector<Question>(questionCount);
    }

    public boolean addQuestion(Question newQ) {
        boolean added=false;
        if(questionCount < questionMax) { //Only add to vector if there is space.
            added = questionStore.add(newQ);
        }
        if(added) { //Only increment count if add was actuall successful.
            questionCount++;
        }
        return added;
    }
}
