package testformgenerator.project_group_11;

import java.util.Vector;

public class Test {
    private Vector<Form> versions; //Store versions in an expandable vector
    private int versionMax=0;
    private int storedVersions=0;
    private String name;

    public Test(String testName, int versionCount) { //Set up test information
        name = testName;
        versionMax = versionCount;
        versions = new Vector<Form>(versionMax); //Initialize vector
    }

    public boolean addForm(Form toAdd) {
        boolean added = false; //Holds success state.

        if(storedVersions < versionMax) {
            added = true;
            versions.add(toAdd);
            storedVersions++;
        }
        return added;
    }

    public String getTestName() { //Retrieve name to search
        return name;
    }

    public int getVersionCount() {
        return storedVersions;
    }

    public Form getForm(int index) {
        Form temp = null;
        if(index < storedVersions) {
            temp = versions.get(index);
        }
        return temp;
    }
}
