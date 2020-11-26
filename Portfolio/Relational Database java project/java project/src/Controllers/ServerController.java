package Controllers;

import  Servlets.ServerStartup;
import java.io.IOException;
import java.sql.SQLException;
/**
 * The purpose of this class is to run the server to host the website
 * @author Chris Hinson
 */
public class ServerController {
    /**The purpose of this function is to turn on the server
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //this instantiates the serverStartup and activates server for the user
        ServerStartup a = new ServerStartup();
        a.Construct();


    }
}