package com.endavatraining.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;

public class MySQLDB {

    private static Logger log = LogManager.getLogger(MySQLDB.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/samsara_api";
    private static final String USERNAME = "root";
    private static final String PASSWORD =  "root";


    /**
     *
     * Print hero's name and type
     *
     * @param sql - query
     */
    public void printHeroNameAndType(String sql) throws SQLException {

        try (Connection dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

             Statement statement = dbConnection.createStatement();

             ResultSet statementResult = statement.executeQuery(sql)) {

            while (statementResult.next()) {

                log.info("Hero -" +
                        " name: " + statementResult.getString("name") +
                        " type: " + statementResult.getString("type"));
            }
        }
    }

    /**
     *
     * Print all data from every column
     *
     * @param sql - query
     */
    public void printAllColumns(String sql) throws SQLException {

        try (Connection dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

             Statement statement = dbConnection.createStatement();

             ResultSet statementResult = statement.executeQuery(sql)) {

            ResultSetMetaData rsmd = statementResult.getMetaData();

            int columnNumber = rsmd.getColumnCount();

            while (statementResult.next()) {
                log.info("*************************");
                for (int i = 1; i <= columnNumber; i++) {
                    String getData = statementResult.getString(i);
                    log.info(getData);
                }

            }
        }
    }
}
