package com.spiritwisdomcounseling.database;

import com.mysql.jdbc.Driver;
import com.spiritwisdomcounseling.model.Contact;
import com.spiritwisdomcounseling.util.SecurityUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        System.out.println("DENNIS and DRIVER is: " + driver);

        //NOTE - import com.mysql.jdbc.Driver; (don't forget to import this!!!- will get class not found exception)
        Driver dummy = new Driver();


        Class.forName(driver);


        connection = DriverManager.getConnection(host, username, password);
        System.out.println("CONNECTION: " + connection);

        return connection;
    }

    public String addContact(Contact contact)throws IOException, ClassNotFoundException, SQLException{

        Connection conn = createConnection();
        String returnStr="";

        // query
        String query = "INSERT INTO contacts ("
                + " contact_id,"
                + " first_name,"
                + " last_name,"
                + " email,"
                + " phone,"
                + " comments,"
                + " referral ) VALUES ("
                + "0, ?, ?, ?, ?, ?, ?)";

        try{
            // create java statement
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, contact.getFirstName());
            st.setString(2, contact.getLastName());
            st.setString(3, contact.getEmail());
            st.setString(4, contact.getPhone());
            st.setString(5, contact.getComments());
            st.setString(6, contact.getReferral());

            int i = st.executeUpdate();
            if (i>0){
                returnStr = "success";
            }else{
                returnStr = "fail";
            }
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return returnStr;
    }
}
