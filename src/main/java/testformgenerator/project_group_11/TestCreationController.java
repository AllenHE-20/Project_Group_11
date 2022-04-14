package testformgenerator.project_group_11;

public class TestCreationController {
    private QuestionBank currentBank=null;
    private Test currentTest=null;

    public TestCreationController() {
        //Empty constructor
    }

    public boolean createNewTest(String bankName, String testName, int formCount, int questionCount, DBMgr database) {
        boolean validInputs = true; //Used to prevent execution of blocks if any input is invalid.
        boolean testCreated = false;
        currentBank = database.getQuestionBank(bankName);
        if(currentBank == null || bankName.length() == 0 || bankName.length() > 50) {
            validInputs = false;
        }
        if(formCount < 2) { //Test given inputs for validity
            validInputs = false;
        }
        if (questionCount < 1) {validInputs = false;}
        if(testName.length() < 1 || testName.length() > 50) {validInputs = false;}

        if(validInputs) { //If no other condition is invalid, make sure the number of questions does not exceed the number available.
            if(questionCount > currentBank.getQuestionCount()) {validInputs = false;}
        }

        if (validInputs) {
            currentTest = new Test(testName, formCount); //Create test object
            for(int x=0; x < formCount; x++) { //Create all n forms
                Form currentForm = new Form(questionCount);
                for(int y = 0; y < questionCount; y++) { //Add n questions to each form
                    currentForm.addQuestion(currentBank.getNext());
                }
                currentTest.addForm(currentForm); //Add the form to the test object
            }
            testCreated = database.addTest(currentTest); //If the test can be added to the database it was successfully created.
        }



        return testCreated;

    }
}
