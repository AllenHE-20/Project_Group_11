package testformgenerator.project_group_11;

public class Question {
    private String name; //Holds question data
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    //Public
    //Construct all parts of question simultaneously
    public Question(String question, String ansOne, String ansTwo, String ansThree, String ansFour){
        name = question;
        ansA = ansOne;
        ansB = ansTwo;
        ansC = ansThree;
        ansD = ansFour;
    }
    //Get whole question simultaneously
    public String getQuestion() {
        return name + "\n" + ansA + "\n" + ansB + "\n" + ansC + "\n" + ansD;
    }
}
