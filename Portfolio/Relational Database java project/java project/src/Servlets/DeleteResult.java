package Servlets;

import DAO.BookDao;
import DAO.Book;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**The purpose of this class is to build a HTML page with a table that shows the deleted book and to update the database by deleting the record
 * @author Chris Hinson
 */
public class DeleteResult extends HttpServlet {


    /** The purpose of this function is to build a page with a table that displays the book just deleted
     * the function will also update the books database by deleting the record
     * the function will also build navigation buttons onto the page to redirect to the user to other pages
     * @param request extends the ServletRequest interface to provide request information for HTTP servlets
     * @param response extends the ServletResponse interface to provide HTTP-specific functionality in sending a response.
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //build a dummy book for error testing
        Book deletedBook = new Book(0, "", "", 0, 0, "", "", "", "", 0, "");
        ;
        PrintWriter out = response.getWriter();
        BookDao _dao= new BookDao();
        //get the parameters from the form
        String id = request.getParameter("ID");
        out.println("<html>  <head>  "+
                "<title>Library Delete Books</title>"+
                "<style>\n" +
                //builds in page styling
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
                //builds the heading message and table headings
                "<body>" +
                "<h1>Displaying Deleted Record</h1>" +
                "<table><tr><th>ID</th><th>Title</th><th>Author</th><th>Year</th><th>Edition</th><th>Publisher</th><th>ISBN</th><th>Cover</th><th>Conditions</th><th>Price</th><th>Notes</th>" + "</tr>");
        if (id != null) {
            int deleteID = Integer.parseInt(id);
            try {
                //getting the details of the book we just deleted using BookDAO
                deletedBook = _dao.getBookById(deleteID);
                //deleting book from database using BookDAO
                _dao.deleteBook(deleteID);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //adding details of deleted book to the table
            out.println(
                    "<tr><td>" + deletedBook.getBook_id()
                            + "</td><td>" + deletedBook.getTitle()
                            + "</td><td>" + deletedBook.getAuthor()
                            + "</td><td>" + deletedBook.getYear()
                            + "</td><td>" + deletedBook.getEdition()
                            + "</td><td>" + deletedBook.getPublisher()
                            + "</td><td>" + deletedBook.getIsbn()
                            + "</td><td>" + deletedBook.getCover()
                            + "</td><td>" + deletedBook.getCondition()
                            + "</td><td>" + deletedBook.getPrice()
                            + "</td><td>" + deletedBook.getNotes()
                            + "</td></tr>"
            );
        }
        out.println("</table>");
        //building navigation buttons
        out.println("<div id=\"nav\">");
        out.println(" <button type=\"button\" onclick=\"ToMainMenu()\">Return to Main Menu</button> ");
        out.println(" <button type=\"button\" onclick=\"ReturnAll()\">Return all Records</button> ");
        out.println("</div>");
        out.println("<script>");
        out.println("function ToMainMenu(){ window.location.href = '/Main'; }");
        out.println("function ReturnAll(){ window.location.href = '/AllBooks'; }");
        out.println("</script>");
        out.println("</body>" + "</html>");
    }
}