package com.endavatraining;

import com.endavatraining.util.MySQLDB;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestDB  extends MySQLDB {

    private static Logger log = LogManager.getLogger(TestDB.class.getName());

    /*
     * Test prints every hero name and type
     */
    @Test
    public void printHeroes() throws SQLException {

        String sql = "Select * from heroes";

        printHeroNameAndType(sql);

    }

    /*
     * Test prints all data from heroes table
     */
    @Test
    public void printAllData() throws SQLException {

        String sql = "Select * from heroes";

        printAllColumns(sql);

    }

}
