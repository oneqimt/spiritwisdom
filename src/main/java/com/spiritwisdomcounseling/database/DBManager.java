package com.spiritwisdomcounseling.database;

import com.spiritwisdomcounseling.model.Contact;
import com.spiritwisdomcounseling.util.SecurityUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Dennis Miller
 */
public class DBManager {

    private Connection connection = null;

    public DBManager() {

    }

    public Connection createConnection() throws IOException, ClassNotFoundException, SQLException {

        String host= SecurityUtil.getInstance().getHost();
        String username= SecurityUtil.getInstance().getUsername();
        String password= SecurityUtil.getInstance().getPassword();
        String driver= SecurityUtil.getInstance().getDriver();

        Class.forName(driver);
        System.out.println("DRIVER: " + driver);

        connection = DriverManager.getConnection(host, username, password);
        System.out.println("CONNECTION: " + connection);

        return connection;
    }

    public Contact createContact(){
        Contact contact = new Contact();



        return contact;
    }
}
