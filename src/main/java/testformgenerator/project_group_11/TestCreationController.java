package testformgenerator.project_group_11;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public class TestCreationController {
    private QuestionBank currentBank=null; //Retain active question bank
    private Test currentTest=null; //Retain test object

    public TestCreationController() {
        //Empty constructor
    }

    public boolean createPDF(String pdfName, String bankName, DBMgr database, int startingQuestion, int totalQuestions){
        boolean pdfCreated = false;
        currentBank = database.getQuestionBank(bankName); //get question bank
        Document document = new Document();

        for(int x = 0; x < startingQuestion ; x++) //Advance through bank to get to desired starting question
            currentBank.getNext();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfName + ".pdf"));
            document.open();

            for (int i = 0; i < totalQuestions; i++){ //loop through question bank
                document.add(new Paragraph(currentBank.getNext().getQuestion() + "\n"));

            }

            document.close();
            writer.close();
            pdfCreated = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pdfCreated;
    }

    public boolean createNewTest(String bankName, String testName, int formCount, int questionCount, DBMgr database) {
        boolean validInputs = true; //Used to prevent execution of blocks if any input is invalid.
        boolean testCreated = false; //Used to track if test was added successfully
        currentBank = database.getQuestionBank(bankName); //Get source question bank

        //Inputs are not valid if bank was not found or name length is invalid
        if(currentBank == null || bankName.length() == 0 || bankName.length() > 50) {
            validInputs = false;
        }

        if(formCount < 2) { //Minimum of 2 forms per test
            validInputs = false;
        }

        if (questionCount < 1) {validInputs = false;} //Minimum 1 question per test
        if(testName.length() < 1 || testName.length() > 50) {validInputs = false;} //Check name length

        if(validInputs) { //If no other condition is invalid, make sure the number of questions does not exceed the number available.
            if(questionCount > currentBank.getQuestionCount()) {validInputs = false;}
        }

        if (validInputs) { //If all inputs were valid, generate the test.
            currentTest = new Test(testName, formCount); //Create test object
            for(int x=0; x < formCount; x++) { //Create all n forms
                Form currentForm = new Form(questionCount); //Create form objects
                createPDF(testName + String.valueOf(x + 1), bankName, database, questionCount * x, questionCount);
                for(int y = 0; y < questionCount; y++) { //Add n questions to each form
                    currentForm.addQuestion(currentBank.getNext()); //Add next question to current form
                }
                currentTest.addForm(currentForm); //Add the form to the test object
            }
            //createPDF(testName, bankName, database,0);
            testCreated = database.addTest(currentTest); //If the test can be added to the database it was successfully created.
        }



        return testCreated; //Return result

    }
}
