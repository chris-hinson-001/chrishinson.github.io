package Servlets;

import DAO.PasswordHasher;
import DAO.UserDao;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/**
 * The purpose of this class is to validate the user's username and password and redirect them as appropriate
 * @author Chris Hinson
 */
public class LoginServlet extends HttpServlet {

    /** The purpose of this function is to validate the username and password that was input on the RootServlet
     * if the login matches then they are redirected to the MainServlet, otherwise user's are redirected to the RootServlet
     * with an error message
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        //this will take the password the user has entered and hash it with md5
        PasswordHasher ph= new PasswordHasher();
        String hashedpassword = ph.md5(password);
        //check the password and username combination with the UserDAO
        UserDao _dao= new UserDao();
        try {
            Boolean access = _dao.checkLogin(uname, hashedpassword);
        if (access == true) {
            //redirect user to MainServlet if they log in successfully
            response.sendRedirect("/Main");
        }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        //redirect to the RootServlet if they fail to log in successfully with an error attached to the HTML line
        response.sendRedirect("/?error=1");
    }
}