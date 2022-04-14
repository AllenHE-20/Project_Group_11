package testformgenerator.project_group_11;

import java.util.Vector;

public class DBMgr {
    private Vector<QuestionBank> questionSets; //Store question banks and tests in expandable vectors
    private Vector<Test> tests;
    private int sets=0; //Track the number of each present
    private int testCount=0;

    public DBMgr() { //Instantiate storage on object creation
        questionSets = new Vector<QuestionBank>();
        tests = new Vector<Test>();
    }

    public boolean addBank(QuestionBank newBank) {
        boolean temp=questionSets.add(newBank);
        if(temp) { //Only increment counter if addition is successful
            sets++;
        }
        return temp;
    }

    public QuestionBank getQuestionBank(String bankName) {
        QuestionBank temp = null;
        for(int x=0; x < sets; x++) { //Loop until question bank found or all listings exhausted
            QuestionBank examineTemp = questionSets.get(x);
            if(examineTemp.getName() == bankName) {
                temp = examineTemp; //If the current ban's name matches, assign it to temp and end the loop.
                break;
            }
        }

        return temp;

    }

    public boolean addTest(Test toAdd) {
        boolean temp = false;
        temp = tests.add(toAdd);
        if(temp) //Only increment test count if addition is successful.
            testCount++;

        return temp;
    }
}
