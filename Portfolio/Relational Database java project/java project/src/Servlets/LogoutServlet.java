package Servlets;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The purpose of this class is to log the user out and redirect them to the RootServlet login page
 * @author Chris Hinson
 */
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
    /** the purpose of this function is redirect to the RootServlet login page
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/");
    }

    /** The purpose of this function is to call the doGet once logout is used
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}