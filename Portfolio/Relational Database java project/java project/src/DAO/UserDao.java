package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//this is the database access object
/**
 * The purpose of this class is to connect to the database and check passwords and usernames from the database
 * @author Chris Hinson
 */
public class UserDao {
    /**
     * Constructor, no arguments to construct the DAO
     */
    //constructor
    public UserDao() {
    }
    /** The purpose of this function is to connect to the books database
     * @return Connection to the database
     */
    //connection function necessary to get it in to the database
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String dbURL = "jdbc:sqlite:books.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    /** The purpose of this function is to check that a username and password are in the database
     * @param username a string representing the username we wish to check
     * @param password a string representing the password we wish to check
     * @return a boolean that will only be true if the username string entered and the one in the database match
     * @throws SQLException
     */
    public Boolean checkLogin(String username, String password) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        //sql query
        String query = "SELECT * FROM users WHERE username =? AND password =?;";
        try {
            dbConnection = getDBConnection();
            //using a prepared statement to insert the values
            ps = dbConnection.prepareStatement(query);
            System.out.println("DBQuery: " + query +" values: " + username + " and " + password);
            ps.setString(1,username);
            ps.setString(2,password);
            // execute SQL query
            result = ps.executeQuery();
            //create a new string from the result username
            String user_check=result.getString("username");
            // check that the username matches the originally entered username
            if (user_check.equals(username)){
                return true;
            }
        }finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return false;
    }
}