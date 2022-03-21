package com.utils;

import org.testng.Assert;

import java.sql.*;
import java.util.Properties;


public class DBUtil {

    private static Connection connObj = null;
    String url, username, password;
    public ConfigReader configReader;
    public Properties prop;
    CommonUtils commonUtils = new CommonUtils();

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

    public String getQuery(String fileName, String queryKey, String... queryParameters) {

        String matchText = "<arguments>";
        String[] arrQueryString = null;
        try {

            //1. read JSON to fetch meta query string
            String queryVal = commonUtils.readJSON(fileName, queryKey);

            //2. construct query
            if(queryVal.contains(matchText)) {

                arrQueryString = queryVal.split(matchText);

                for(int cnt = 0; cnt < queryParameters.length; cnt++) {
                    arrQueryString[cnt] = arrQueryString[cnt] + queryParameters[cnt];
                }

                queryVal = String.join("", arrQueryString);
            }

            return queryVal;

        } catch(Exception e) {
            Assert.fail("Exception in getQuery method: " + e.getMessage());
            return null;
        }

    }

}
