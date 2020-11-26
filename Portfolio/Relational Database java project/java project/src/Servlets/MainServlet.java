package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * The purpose of this class is to build a HTML page with navigation buttons to other pages and a logout form
 * @author Chris Hinson
 */
public class MainServlet extends HttpServlet {
    /** The purpose of this function is to build a HTML page with a series of buttons with navigation links to other pages and a logout form
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>  <head>" +
                "<title>Library Index</title>"+
                //this is in page styling
                "<style>\n" +
                        "\tbody{\n" +
                        "  background-color: #241111;\n" +
                        "  font-size:22px;\n" +
                        "  line-height: 32px;\n" +
                        "  color: #ffffff;\n" +
                        "  margin: 0;\n" +
                        "  padding: 0;\n" +
                        "  word-wrap:break-word !important;\n" +
                        "  font-family: Arial, Helvetica, sans-serif;\n" +
                        "}\n" +
                        "h1{\n" +
                        "  text-align: center;\n" +
                        "  margin: 20px;"+
                        "}\n" +
                        "button{\n" +
                        "  background-color: #F5C8BF;\n" +
                        "  border-color: black;\n" +
                        "    -webkit-transition-duration: 0.4s; /* Safari */\n" +
                        "  transition-duration: 0.4s;\n" +
                        "    padding: 15px 32px;\n" +
                        "  text-align: center;\n" +
                        "  text-decoration: none;\n" +
                        "  display: inline-block;\n" +
                        "  font-size: 16px;\n" +
                        "}\n" +
                        "button:hover {\n" +
                        "  background-color: #F5C8BF; \n" +
                        "  color: white;\n" +
                        "}\n" +
                        "#nav{\n" +
                        "  margin: auto;\n" +
                        "  margin-left: 200px;\n" +
                        "  width: 90%;\n" +
                        "  padding: 10px;\n" +
                        "}\n" +
                        "\n" +
                        "#nav input[type=submit] {\n" +
                        "  background-color: #A40808;\n" +
                        "  color: white;\n" +
                        "  padding: 12px 20px;\n" +
                        "  border: none;\n" +
                        "  border-radius: 4px;\n" +
                        "  cursor: pointer;\n" +
                        "}" +
                "</style>" +
                "</head>" +
                "<body>");
        //this builds the welcome message, buttons and attaches functions as well as the log out form
        out.println("<h1>Welcome to the Library</h1>");
        out.println("<div id=\"nav\">");
        out.println(" <button type=\"button\" onclick=\"ReturnAll()\">Return all Records</button> ");
        out.println(" <button type=\"button\" onclick=\"Search()\">Search for book</button> ");
        out.println(" <button type=\"button\" onclick=\"Add()\">Add new book to database</button> ");
        out.println(" <button type=\"button\" onclick=\"Update()\">Update existing book price</button> ");
        out.println(" <button type=\"button\" onclick=\"Delete()\">Delete book from database</button> ");
        out.println("<form action=\"LogoutServlet\" >");
        out.println("<input type=\"submit\" value=\"Logout\"/>");
        out.println("</form>");
        out.println("</div>");
        out.println("<script>");
        out.println("function ReturnAll(){ window.location.href = '/AllBooks'; }");
        out.println("function Search(){ window.location.href = '/Search'; }");
        out.println("function Add(){ window.location.href = '/Add'; }");
        out.println("function Update(){ window.location.href = '/Update'; }");
        out.println("function Delete(){ window.location.href = '/Delete'; }");
        out.println("</script>");
        out.println("</body></html>");
    }
}
