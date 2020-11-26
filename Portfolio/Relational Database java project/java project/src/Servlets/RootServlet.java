package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The purpose of this class is to build a HTML page with a form that requests the user's username and password and post them on
 * @author Chris Hinson
 */
public class RootServlet extends HttpServlet {
    /** The purpose of this function is to build a HTML page with a form that will collect the users username and password and post them
     * if an incorrect entry is made an error message will display at the top of the screen
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        String error = request.getParameter("error");
        PrintWriter out = response.getWriter();
        out.println("<html><head>" +
        "<title>Library Index</title>"+
                "<style>\n" +
                //this is all in page styling
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
                        "input[type=text], input[type=password], select{\n" +
                        "  width: 50%;\n" +
                        "  padding: 12px;\n" +
                        "  border: 1px solid #ccc;\n" +
                        "  border-radius: 4px;\n" +
                        "  box-sizing: border-box;\n" +
                        "  resize: vertical;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "label {\n" +
                        "  padding: 12px 12px 12px 0;\n" +
                        "  display: inline-block;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "input[type=submit] {\n" +
                        "  background-color: #F5C8BF;\n" +
                        "  color: black;\n" +
                        "  padding: 12px 20px;\n" +
                        "  border: none;\n" +
                        "  border-radius: 4px;\n" +
                        "  cursor: pointer;\n" +
                        "  float: right;\n" +
                        "}\n" +
                        "/* Style the container */\n" +
                        ".container {\n" +
                        "  border-radius: 5px;\n" +
                        "  background-color: #A40808;\n" +
                        "  padding: 20px;\n" +
                        "  width:80%;\n" +
                        "  margin:auto;\n" +
                        "\n" +
                        "}\n" +
                        "\n" +
                        "/* Floating column for labels: 25% width */\n" +
                        ".col-25 {\n" +
                        "  float: left;\n" +
                        "  width: 25%;\n" +
                        "  margin-top: 6px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Floating column for inputs: 75% width */\n" +
                        ".col-75 {\n" +
                        "  float: left;\n" +
                        "  width: 75%;\n" +
                        "  margin-top: 6px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Clear floats after the columns */\n" +
                        ".row:after {\n" +
                        "  content: \"\";\n" +
                        "  display: table;\n" +
                        "  clear: both;\n" +
                        "}");
         out.println("</style>");
         out.println("</head>");
         out.println("<body>");
         // this will display an error message at the top of the page if there was an error when logging in
        if(error != null && error.equals("1")){
            out.println("<h1 style=\"color:red;\">Login error:Re-enter name and password </h1>");
        }
        //this will display the welcome message and the log in form
        out.println("<h1>Welcome to the Library!</h1>");
        out.println("    <div class=\"container\">\n" +
                "      <form name=\"login\" id=\"login\" action=\"Login\" method=\"post\">\n" +
                        "        <div class=\"row\">\n" +
                        "          <div class=\"col-25\">\n" +
                        "            <label for=\"username\">Username</label>\n" +
                        "          </div>\n" +
                        "          <div class=\"col-75\">\n" +
                        "            <input type=\"text\" id=\"username\" name=\"username\" placeholder=\"Your username..\">\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "        <div class=\"row\">\n" +
                        "          <div class=\"col-25\">\n" +
                        "            <label for=\"password\">Password</label>\n" +
                        "          </div>\n" +
                        "          <div class=\"col-75\">\n" +
                        "            <input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Your password..\">\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "        <div class=\"row\">\n" +
                        "          <input type=\"submit\" value=\"Log in\">\n" +
                        "        </div>" +
                        "      </form>" +
                        " </div>");
        out.println("</body></html>");
    }
}
