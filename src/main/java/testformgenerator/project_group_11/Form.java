package testformgenerator.project_group_11;

import java.util.Vector;

public class Form {
    private Vector<Question> questionStore; //Store questions and state information
    private int questionMax=0; //Store max form size in terms of questions
    private int questionCount=0; //Store current number of stored question

    public Form(int questionQuantity) { //Set up vector and storage capacity info
        questionCount = questionQuantity;
        questionStore = new Vector<>(questionCount);
    }

    public boolean addQuestion(Question newQ) {
        boolean added=false;
        if(questionCount < questionMax) { //Only add to vector if there is space.
            added = questionStore.add(newQ); //Set added status based on storage operation result
        }
        if(added) { //Only increment count if add was actually successful.
            questionCount++;
        }
        return added;
    }

    //iterator to get questions
    public String getQuestions(Vector<Question> questionStore){
        this.questionStore = questionStore;
        String output = "";
        for (Question q : questionStore){
            output = q.getQuestion() + "\n";
        }
        return output;
    }
}
