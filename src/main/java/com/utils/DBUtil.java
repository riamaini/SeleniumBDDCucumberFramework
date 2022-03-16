package com.utils;

import java.sql.*;
import java.util.Properties;


public class DBUtil {

    private static Connection connObj = null;
    String url, username, password;
    public ConfigReader configReader;
    public Properties prop;

    public DBUtil(){
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    public Connection getDBConnection() {

        try {

            if (connObj == null) {

                System.out.println("Establishing connection");
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                url = prop.getProperty("DBUrl");
                username = prop.getProperty("DBUsername");
                password = prop.getProperty("DBPassword");

                connObj = DriverManager.getConnection(url,username,password);

                if (connObj != null) {
                    DatabaseMetaData metaObj = (DatabaseMetaData) connObj.getMetaData();
                    System.out.println("Driver Name = " + metaObj.getDriverName() + ", Driver Version = "
                            + metaObj.getDriverVersion() + ", Product Name = " + metaObj.getDatabaseProductName()
                            + ", Product Version = " + metaObj.getDatabaseProductVersion());
                }

                System.out.println("Connection established");
                return connObj;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connObj;

    }

    public ResultSet executeQuery(String query) {

        Statement statement = null;

        try {

            statement = getDBConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet;

        } catch (Exception e) {
            return null;
        }


    }

}
