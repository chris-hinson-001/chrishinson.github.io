package Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**The purpose of this class is to build a HTML page with several buttons and hidden forms which the buttons reveal and post the forms
 * @author Chris Hinson
 */
public class Search extends HttpServlet {

    /**The purpose of this function is to build a HTML page several hidden forms and buttons that reveal the forms
     * the page will post the form that the user submits
     * the function will also build navigation buttons onto the page to redirect to the user to other pages
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>"+
                "<html>"+
                "<head>"+
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
                "<title>Library Search Books Input</title>"+
                "<link href=\"Style.css\" rel=\"stylesheet\" type=\"text/css\">" +
                "<style>"+
                //this hides the forms for the different search inputs
                "#idDIV {"+
                "	display: none;"+
                "}"+
                "#titleDIV{"+
                "	display: none;"+
                "}"+
                "#authorDIV{"+
                "	display: none;"+
                "}"+
                "#priceDIV{"+
                "	display: none;"+
                "}"+
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
                "  padding: 15px 32px;\n" +
                "  margin: 5px;" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "}\n" +
                "button:hover {\n" +
                "  background-color: #F5C8BF; \n" +
                "  color: white;\n" +
                "}" +
                "#nav{\n" +
                "  margin: auto;\n" +
                "  margin-left: 200px;\n" +
                "  width: 90%;\n" +
                "  padding: 10px;\n" +
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
                "}\n"+
                "</style>"+
                "</head>"+
                //builds the buttons that reveal the forms and also builds navigation button
                "<body>"+
                "<h1>Search for Record</h1>" +
                "<div id=\"nav\">"+
                "<button onclick=\"ID()\">Search by ID</button>"+
                "<button onclick=\"Title()\">Search by Title</button>"+
                "<button onclick=\"Author()\">Search by Author</button>"+
                "<button onclick=\"Price()\">Search by Price</button>"+
                "<button onclick=\"ToMainMenu()\">Return to Main Menu</button>"+
                //builds the 4 seperate forms
                "</div>"+
                ""+
                "<div id=\"idDIV\">"+
                "    <div class=\"container\">\n" +
                "      <form name=\"SearchByID\" action=\"SearchResult\" method=\"post\">\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-25\">\n" +
                "            <label for=\"ID\">Book ID</label>\n" +
                "          </div>\n" +
                "          <div class=\"col-75\">\n" +
                "            <input type=\"number\" id=\"ID\" name=\"ID\" required placeholder=\"Book ID..\">\n" +
                "          </div>\n" +
                "        </div>"+
                "        <div class=\"row\">\n" +
                "          <input type=\"submit\" value=\"Search\">\n" +
                "        </div>" +
                "      </form>" +
                "    </div>"+
                "</div>"+
                ""+
                "<div id=\"titleDIV\">"+
                "    <div class=\"container\">\n" +
                "      <form name=\"SearchByTitle\" action=\"SearchResult\" method=\"post\">\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-25\">\n" +
                "            <label for=\"title\">Title</label>\n" +
                "          </div>\n" +
                "          <div class=\"col-75\">\n" +
                "            <input type=\"text\" id=\"title\" name=\"title\" required placeholder=\"Book Title..\">\n" +
                "          </div>\n" +
                "        </div>"+
                "        <div class=\"row\">\n" +
                "          <input type=\"submit\" value=\"Search\">\n" +
                "        </div>" +
                "      </form>" +
                "    </div>"+
                "</div>"+
                ""+
                "<div id=\"authorDIV\">"+
                "    <div class=\"container\">\n" +
                "      <form name=\"SearchByAuthor\" action=\"SearchResult\" method=\"post\">\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-25\">\n" +
                "            <label for=\"author\">Author</label>\n" +
                "          </div>\n" +
                "          <div class=\"col-75\">\n" +
                "            <input type=\"text\" id=\"author\" name=\"author\" required placeholder=\"Book Author..\">\n" +
                "          </div>\n" +
                "        </div>"+
                "        <div class=\"row\">\n" +
                "          <input type=\"submit\" value=\"Search\">\n" +
                "        </div>" +
                "      </form>" +
                "    </div>"+
                "</div>"+
                ""+
                "<div id=\"priceDIV\">"+
                "    <div class=\"container\">\n" +
                "      <form name=\"SearchByPrice\" action=\"SearchResult\" method=\"post\">\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-25\">\n" +
                "            <label for=\"lowerPrice\">Lower Price Limit</label>\n" +
                "          </div>\n" +
                "          <div class=\"col-75\">\n" +
                "            <input type=\"number\" id=\"lowerPrice\" name=\"lowerPrice\" required placeholder=\"Lower Price Limit..\">\n" +
                "          </div>\n" +
                "        </div>\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-25\">\n" +
                "            <label for=\"upperPrice\">Upper Price Limit</label>\n" +
                "          </div>\n" +
                "          <div class=\"col-75\">\n" +
                "            <input type=\"number\" id=\"upperPrice\" name=\"upperPrice\" required placeholder=\"Upper Price Limit..\">\n" +
                "          </div>\n" +
                "        </div>"+
                "        <div class=\"row\">\n" +
                "          <input type=\"submit\" value=\"Search\">\n" +
                "        </div>" +
                "      </form>" +
                "    </div>"+
                "</div>"+
                ""+
                //applying functions to the buttons to reveal and hide the forms
                "<script>"+
                "function ToMainMenu(){ window.location.href = '/Main'; }"+
                "function ID() {"+
                "  var x = document.getElementById(\"idDIV\");"+
                "  if (x.style.display === \"block\") {"+
                "    x.style.display = \"none\";"+
                "  } else {"+
                "    x.style.display = \"block\";"+
                "  }"+
                "}"+
                "function Title() {"+
                "  var x = document.getElementById(\"titleDIV\");"+
                "  if (x.style.display === \"block\") {"+
                "    x.style.display = \"none\";"+
                "  } else {"+
                "    x.style.display = \"block\";"+
                "  }"+
                "}"+
                "function Author() {"+
                "  var x = document.getElementById(\"authorDIV\");"+
                "  if (x.style.display === \"block\") {"+
                "    x.style.display = \"none\";"+
                "  } else {"+
                "    x.style.display = \"block\";"+
                "  }"+
                "}"+
                "function Price() {"+
                "  var x = document.getElementById(\"priceDIV\");"+
                "  if (x.style.display === \"block\") {"+
                "    x.style.display = \"none\";"+
                "  } else {"+
                "    x.style.display = \"block\";"+
                "  }"+
                "}"+
                "</script>"+
                ""+
                "</body>"+
                "</html>");

    }
}
