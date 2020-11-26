package Servlets;

import DAO.BookDao;
import DAO.Book;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
/**The purpose of this class is to conduct a search on the database and build a HTML page with a table which displays the results
 * @author Chris Hinson
 */
public class SearchResult extends HttpServlet {

    /** The purpose of this function is to conduct a search on the database with the details from the search form
     * the function build a HTML page with a table to display the results
     * the function will also build navigation buttons onto the page to redirect to the user to other pages
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Book> searchResults = new ArrayList<Book>();
        //build dummy book for error testing
        Book searchById = new Book(0, "", "", 0, 0, "", "", "", "", 0, "");
        ;
        BookDao _dao= new BookDao();
        PrintWriter out = response.getWriter();
        // catch the posted details
        String id = request.getParameter("ID");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String lowerPrice = request.getParameter("lowerPrice");
        String upperPrice = request.getParameter("upperPrice");

        out.println("<html>  <head>" +
                "<title>Library Search Books</title>"+
                "<style>\n" +
                //build in page styling
                "\tbody{\n" +
                "  background-color: #241111;" +
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
                "table {\n" +
                "  border: 2px solid #A40808;\n" +
                "  background-color: #EEE7DB;\n" +
                "  width: 70%;\n" +
                "  margin-left:15%; \n" +
                "  margin-right:15%;\n" +
                "  text-align: center;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "table td, table th {\n" +
                "  border: 1px solid #AAAAAA;\n" +
                "  color: black;\n" +
                "  padding: 3px 2px;\n" +
                "}\n" +
                "table tbody td {\n" +
                "  font-size: 17px;\n" +
                "   color: #333333;\n" +
                "}\n" +
                "table tr:nth-child(even) {\n" +
                "  background: #F5C8BF;\n" +
                "}\n" +
                "table thead {\n" +
                "  background: #A40808;\n" +
                "}\n" +
                "table thead th {\n" +
                "  font-size: 19px;\n" +
                "  font-weight: bold;\n" +
                "  color: black;\n" +
                "  text-align: center;\n" +
                "  border-left: 2px solid #A40808;\n" +
                "}\n" +
                "table thead th:first-child {\n" +
                "  border-left: none;\n" +
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
                //build Heading message and table headings
                "<body>" +
                "<h1>Displaying Search Results</h1>" +
                "<table><tr><th>ID</th><th>Title</th><th>Author</th><th>Year</th><th>Edition</th><th>Publisher</th><th>ISBN</th><th>Cover</th><th>Conditions</th><th>Price</th><th>Notes</th>" + "</tr>");
        //this will run search by ID if a book id form was used
        if (id != null) {
            int searchID = Integer.parseInt(id);
            try {
                //get search results from the bookDAO
                searchById = _dao.getBookById(searchID);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            out.println(
                    //add the results to the table
                    "<tr><td>" + searchById.getBook_id()
                            + "</td><td>" + searchById.getTitle()
                            + "</td><td>" + searchById.getAuthor()
                            + "</td><td>" + searchById.getYear()
                            + "</td><td>" + searchById.getEdition()
                            + "</td><td>" + searchById.getPublisher()
                            + "</td><td>" + searchById.getIsbn()
                            + "</td><td>" + searchById.getCover()
                            + "</td><td>" + searchById.getCondition()
                            + "</td><td>" + searchById.getPrice()
                            + "</td><td>" + searchById.getNotes()
                            + "</td></tr>"
            );
        }
        //this will run search by Title if a Title form was used
        if (title != null) {
            try {
                //get search results from the bookDAO
                searchResults = _dao.getBookByTitle(title);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //this needs to be an ArrayList loop as more than one result could be pulled
            for (Book b : searchResults) {
                out.println(
                        //add the results to the table
                        "<tr><td>" + b.getBook_id()
                                + "</td><td>" + b.getTitle()
                                + "</td><td>" + b.getAuthor()
                                + "</td><td>" + b.getYear()
                                + "</td><td>" + b.getEdition()
                                + "</td><td>" + b.getPublisher()
                                + "</td><td>" + b.getIsbn()
                                + "</td><td>" + b.getCover()
                                + "</td><td>" + b.getCondition()
                                + "</td><td>" + b.getPrice()
                                + "</td><td>" + b.getNotes()
                                + "</td></tr>"
                );
            }
        }
        //this will run search by Author if a Author form was used
        if (author != null) {
            try {
                //get search results from the bookDAO
                searchResults = _dao.getBookByAuthor(author);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //this needs to be an ArrayList loop as more than one result could be pulled
            for (Book b : searchResults) {
                out.println(
                        //add the results to the table
                        "<tr><td>" + b.getBook_id()
                                + "</td><td>" + b.getTitle()
                                + "</td><td>" + b.getAuthor()
                                + "</td><td>" + b.getYear()
                                + "</td><td>" + b.getEdition()
                                + "</td><td>" + b.getPublisher()
                                + "</td><td>" + b.getIsbn()
                                + "</td><td>" + b.getCover()
                                + "</td><td>" + b.getCondition()
                                + "</td><td>" + b.getPrice()
                                + "</td><td>" + b.getNotes()
                                + "</td></tr>"
                );
            }
        }
        //this will run search by Price if a price form was used
        if (lowerPrice != null && upperPrice != null) {
            int searchLow = Integer.parseInt(lowerPrice);
            int searchUp = Integer.parseInt(upperPrice);
            try {
                //get search results from the bookDAO
                searchResults = _dao.getBookByPriceRange(searchLow, searchUp);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //this needs to be an ArrayList loop as more than one result could be pulled
            for (Book b : searchResults) {
                out.println(
                        //add the results to the table
                        "<tr><td>" + b.getBook_id()
                                + "</td><td>" + b.getTitle()
                                + "</td><td>" + b.getAuthor()
                                + "</td><td>" + b.getYear()
                                + "</td><td>" + b.getEdition()
                                + "</td><td>" + b.getPublisher()
                                + "</td><td>" + b.getIsbn()
                                + "</td><td>" + b.getCover()
                                + "</td><td>" + b.getCondition()
                                + "</td><td>" + b.getPrice()
                                + "</td><td>" + b.getNotes()
                                + "</td></tr>"
                );
            }
        }
        out.println("</table>");
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
