package com.gmail.acharne1985.connectorDb;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectorDb {

    private static Logger log = Logger.getLogger(ConnectorDb.class.getName());

    private static ConnectorDb innstance;
    private Connection con;

    private ConnectorDb() {
        createConnection();
    }

    private void createConnection() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("student-run/src/main/resources/config.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            log.error("Config file is not found", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(property.getProperty("db.driver"));
            con = DriverManager.getConnection(property.getProperty("db.host"), property.getProperty("db.login"),
                    property.getProperty("db.password"));

            log.info("Connection complete");
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Connection error", e);
        }
    }

    public Connection getConnection() {
        if (con == null) {
            createConnection();
        }
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();

                log.info("Connection CLOSE");
            }
        } catch (SQLException e) {
            log.error("Connection is not closed", e);
        }
    }

    public static ConnectorDb getInstance() {
        if (innstance == null) {
            innstance = new ConnectorDb();
        }
        return innstance;
    }
}
