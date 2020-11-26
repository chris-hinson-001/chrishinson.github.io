package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**The purpose of this class is to build a HTML page with a form that collects ID number of a book and a new price and post them
 * @author Chris Hinson
 */
public class Update extends HttpServlet {

    /**The purpose of this function is to build a HTML page with a form that collects the ID number of a book and a new price and post them
     * the function will also build navigation buttons onto the page to redirect to the user to other pages
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println( "<!DOCTYPE html>"+
                        "<html>"+
                        "<head>"+
                        "<title>Library Update Books Input</title>"+
                        "<style>\n" +
                        //builds in page styling
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
                        "  color: black;\n" +
                        "  padding: 12px 20px;\n" +
                        "  border: none;\n" +
                        "  border-radius: 4px;\n" +
                        "  cursor: pointer;\n" +
                        "}\n" +
                        "input[type=text], input[type=number], select{\n" +
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
                        "}\n" +
                        "</style>" +
                        "</head>"+
                        // builds page heading and form
                        "<body>"+
                        "<h1>Update a Record's Price</h1>" +
                        "    <div class=\"container\">\n" +
                        "      <form name=\"Update\" action=\"UpdateResult\" method=\"post\">\n" +
                        "        <div class=\"row\">\n" +
                        "          <div class=\"col-25\">\n" +
                        "            <label for=\"ID\">Book ID</label>\n" +
                        "          </div>\n" +
                        "          <div class=\"col-75\">\n" +
                        "            <input type=\"number\" id=\"ID\" name=\"ID\" required placeholder=\"Book ID..\">\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "        <div class=\"row\">\n" +
                        "          <div class=\"col-25\">\n" +
                        "            <label for=\"Price\">New Price</label>\n" +
                        "          </div>\n" +
                        "          <div class=\"col-75\">\n" +
                        "            <input type=\"number\" id=\"Price\" name=\"Price\" required placeholder=\"New Price..\">\n" +
                        "          </div>\n" +
                        "        </div>" +
                        "        <div class=\"row\">\n" +
                        "          <input type=\"submit\" value=\"Update\">\n" +
                        "        </div>" +
                        "      </form>" +
                        "    </div>");
        //builds navigation buttons
        out.println("<div id=\"nav\">");
        out.println(" <button type=\"button\" onclick=\"ToMainMenu()\">Return to Main Menu</button> ");
        out.println("</div>");
        out.println("<script>");
        out.println("function ToMainMenu(){ window.location.href = '/Main'; }");
        out.println("</script>");
        out.println("</body>" + "</html>");
    }
}
