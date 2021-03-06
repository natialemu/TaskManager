package com.natialemu.taskmanager.DataAccess;

public class DBConfig {
    private String username;
    private String password;
    private String databaseURL;
    private String databaseName;

    public DBConfig(){
        username = "nathnael2";
        password = "NatMysql_AWS_19";
        databaseURL = "nati-db-instances.cedako7zoc64.us-west-2.rds.amazonaws.com:3306";
        databaseName = "TaskManagerAndroid";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDbURL() {
        return getDatabaseURL() + "/"+getDatabaseName();
    }
}
