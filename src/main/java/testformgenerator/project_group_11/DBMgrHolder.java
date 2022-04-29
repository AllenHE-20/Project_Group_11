package testformgenerator.project_group_11;

/*
 * This is a singleton class to hold the database object for the lifetime of the program's execution
 * It can only be created and accessed by itself
 */


public final class DBMgrHolder {
    private DBMgr database;
    private final static DBMgrHolder INSTANCE = new DBMgrHolder(); //Create new instance internally, with a static allocation

    private DBMgrHolder() {}

    public static DBMgrHolder getInstance(){ //Static function allows instance retrieval
        return INSTANCE;
    }

    public void setDatabase(DBMgr inDB){
        this.database = inDB;
    }
    
    public DBMgr getDatabase(){
        return database;
    }
}
