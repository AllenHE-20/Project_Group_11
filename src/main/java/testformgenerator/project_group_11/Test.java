package testformgenerator.project_group_11;

import java.util.Vector;

public class Test {
    private Vector<Form> versions; //Store versions in an expandable vector
    private int versionMax=0; //Track maximum number of forms
    private int storedVersions=0; //Track current number of forms
    private String name;

    public Test(String testName, int versionCount) { //Set up test information
        name = testName;
        versionMax = versionCount;
        versions = new Vector<Form>(versionMax); //Initialize vector
    }

    public boolean addForm(Form toAdd) {
        boolean added = false; //Holds success state.

        if(storedVersions < versionMax) { //Only add new form if capacity is not reached
            added = true; //Indicate that form was added
            versions.add(toAdd); //Add form to vector
            storedVersions++; //Update total versions stored
        }
        return added; //Return result
    }

    //Retrieves test name
    public String getTestName() { //Retrieve name to search
        return name;
    }

    //Retrieves current version count
    public int getVersionCount() {
        return storedVersions;
    }


    public Form getForm(int index) {
        Form temp = null; //Create holding variable
        if(index < storedVersions && index >= 0) { //Bounds-check index
            temp = versions.get(index); //Retrieve form object
        }
        return temp; //Return form object or null
    }
}
