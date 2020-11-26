package Servlets;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
/**
 * The purpose of this class is to set the servlet handlers and start the server
 * @author Chris Hinson
 */
public class ServerStartup {

    /** Constructor for the Server startup
     */
    public ServerStartup() {}

    /** The purpose of this function is to set the server, the servlet handlers and start the server
     * @throws Exception
     */
    public void Construct()throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        //this is a list of all the servlets and their pages, tho some pages would not be displayed in normal usage
        handler.addServletWithMapping(RootServlet.class, "/");
        handler.addServletWithMapping(LoginServlet.class, "/Login");
        handler.addServletWithMapping(MainServlet.class, "/Main");
        handler.addServletWithMapping(AllBooks.class, "/AllBooks");
        handler.addServletWithMapping(Search.class, "/Search");
        handler.addServletWithMapping(Update.class,"/Update");
        handler.addServletWithMapping(Delete.class, "/Delete");
        handler.addServletWithMapping(Add.class, "/Add");
        handler.addServletWithMapping(SearchResult.class, "/SearchResult");
        handler.addServletWithMapping(DeleteResult.class, "/DeleteResult");
        handler.addServletWithMapping(UpdateResult.class, "/UpdateResult");
        handler.addServletWithMapping(AddResult.class, "/AddResult");
        server.start();
        server.join();
    }


}



