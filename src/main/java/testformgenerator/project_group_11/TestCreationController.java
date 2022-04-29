package testformgenerator.project_group_11;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class TestCreationController {
    private QuestionBank currentBank=null;
    private Test currentTest=null;

    public TestCreationController() {
        //Empty constructor
    }

    public boolean createPDF(String pdfName, String bankName, DBMgr database){
        boolean pdfCreated = false;
        currentBank = database.getQuestionBank(bankName); //get question bank
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfName + ".pdf"));
            List orderedList = new List(List.ORDERED);
            for (int i = 0; i < currentBank.getQuestionCount(); i++){ //loop through question bank
                orderedList.add((Element) currentBank.getNext());
                orderedList.add("\n");
                document.add(orderedList);

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
