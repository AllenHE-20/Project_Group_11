package testformgenerator.project_group_11;

public final class DBMgrHolder {
    private DBMgr database;
    private final static DBMgrHolder INSTANCE = new DBMgrHolder();

    private DBMgrHolder() {}

    public static DBMgrHolder getInstance(){
        return INSTANCE;
    }

    public void setDatabase(DBMgr inDB){
        this.database = inDB;
    }
    
    public DBMgr getDatabase(){
        return database;
    }
}
