package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//this is the database access object

/**
 * The purpose of this class is to connect to the database and call records from the database
 * @author Chris Hinson
 */
public class BookDao {

    /**
     * Constructor, no arguments to construct the DAO
     */
    public BookDao() {
    }

    /** The purpose of this function is to connect to the books database
     * @return Connection to the database
     */
    //connection function necessary to get it in to the database
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String dbURL = "jdbc:sqlite:books.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    /** The purpose of this function is to get all the records from the databse
     * @return ArrayList of books from the database
     * @throws SQLException
     */
    //function returns a list of all books
    public ArrayList<Book> getAllBooks() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM books;";
        ArrayList<Book> books = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int book_id = result.getInt("ID");
                String title = result.getString("Title");
                String author = result.getString("Author");
                int year = result.getInt("Year");
                int edition = result.getInt("Edition");
                String publisher = result.getString("Publisher");
                String isbn = result.getString("ISBN");
                String cover = result.getString("Cover");
                String condition = result.getString("Condition");
                int price = result.getInt("Price");
                String notes = result.getString("Notes");
                //this gets the books in the database into an ArrayList
                books.add(new Book(book_id, title, author, year, edition, publisher, isbn, cover, condition, price, notes));
            }
        } catch (Exception e) {
            System.out.println("get all books: " + e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        //returns the ArrayList
        return books;
    }

    /** Purpose of function is to return a record from the database based on ID
     * @param temp_id an integer of the book ID we wish to search for
     * @return a Book object representing the record from the database
     * @throws SQLException
     */
    //function returns a user specified book
    public Book getBookById(int temp_id) throws SQLException {

        Book temp = null;
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        //sql query
        String query = "SELECT * FROM books WHERE ID =?;";

        try {
            dbConnection = getDBConnection();
            // using a prepared statement to insert the values
            ps = dbConnection.prepareStatement(query);

            ps.setInt(1, temp_id);
            System.out.println("DBQuery: " + query + "values: " +temp_id);
            // execute SQL query

            result =ps.executeQuery();

            while (result.next()) {

                int book_id = result.getInt("ID");
                String title = result.getString("Title");
                String author = result.getString("Author");
                int year = result.getInt("Year");
                int edition = result.getInt("Edition");
                String publisher = result.getString("Publisher");
                String isbn = result.getString("ISBN");
                String cover = result.getString("Cover");
                String condition = result.getString("Condition");
                int price = result.getInt("Price");
                String notes = result.getString("Notes");
                //this gets the result from the database into a book object
                temp = new Book(book_id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);

            }
            //if no book is returned this sends back a book with no data as the result
            if (temp==null){
				temp = new Book(temp_id,"NO DATA","NO DATA",0,0,"NO DATA","0","NO DATA","NO DATA",0,"NO DATA");
				return temp;
			}
		}finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        //return the book
        return temp;
    }

    /** Purpose of function is to return records from the database based on Title
     * @param searchTitle a String of the book's Title we wish to search for
     * @return an ArrayList of Book objects representing the records from the database
     * @throws SQLException
     */
    public ArrayList<Book> getBookByTitle(String searchTitle) throws SQLException {
        ArrayList<Book> bookByTitle = new ArrayList<>();
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        //sql query
        String query = "SELECT * FROM books WHERE Title LIKE ? ORDER BY TITLE;";

        try {
            dbConnection = getDBConnection();
            //using a prepared statement to insert the values
            ps = dbConnection.prepareStatement(query);
            System.out.println("DBQuery: " + query + " values: " + searchTitle);
            ps.setString(1, "%" + searchTitle + "%");
            // execute SQL query
            result = ps.executeQuery();

            while (result.next()) {

                int book_id = result.getInt("ID");
                String title = result.getString("Title");
                String author = result.getString("Author");
                int year = result.getInt("Year");
                int edition = result.getInt("Edition");
                String publisher = result.getString("Publisher");
                String isbn = result.getString("ISBN");
                String cover = result.getString("Cover");
                String condition = result.getString("Condition");
                int price = result.getInt("Price");
                String notes = result.getString("Notes");
                //this gets the books in the database into an ArrayList
                bookByTitle.add(new Book(book_id, title, author, year, edition, publisher, isbn, cover, condition, price, notes));
            }
            //if no book is returned this sends back a book with no data as the result
            if (bookByTitle==null){
				bookByTitle.add(new Book(0,searchTitle,"NO DATA",0,0,"NO DATA","0","NO DATA","NO DATA",0,"NO DATA"));
				return bookByTitle;
			}
        } finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        //returns the ArrayList
        return bookByTitle;
    }
    /** Purpose of function is to return records from the database based on Author
     * @param searchAuthor a String of the book's Author we wish to search for
     * @return an ArrayList of Book objects representing the records from the database
     * @throws SQLException
     */
    public ArrayList<Book> getBookByAuthor(String searchAuthor) throws SQLException {

        ArrayList<Book> bookByAuthor = new ArrayList<>();
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        //sql query
        String query = "SELECT * FROM books WHERE Author LIKE ? ORDER BY Author;";

        try {
            dbConnection = getDBConnection();
            //using a prepared statement to insert the values
            ps = dbConnection.prepareStatement(query);
            System.out.println("DBQuery: " + query +" values: "+searchAuthor );
            ps.setString(1, "%" + searchAuthor + "%");
            // execute SQL query
            result = ps.executeQuery();

            while (result.next()) {


                int book_id = result.getInt("ID");
                String title = result.getString("Title");
                String author = result.getString("Author");
                int year = result.getInt("Year");
                int edition = result.getInt("Edition");
                String publisher = result.getString("Publisher");
                String isbn = result.getString("ISBN");
                String cover = result.getString("Cover");
                String condition = result.getString("Condition");
                int price = result.getInt("Price");
                String notes = result.getString("Notes");
                //this gets the books in the database into an ArrayList
                bookByAuthor.add(new Book(book_id, title, author, year, edition, publisher, isbn, cover, condition, price, notes));

            }//if no book is returned this sends back a book with no data as the result
			if (bookByAuthor==null) {
				bookByAuthor.add(new Book(0, "NO DATA", searchAuthor, 0, 0, "NO DATA", "0", "NO DATA", "NO DATA", 0, "NO DATA"));
				return bookByAuthor;
			}
        } finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();

            }
        }//returns the ArrayList
        return bookByAuthor;
    }

    /** Purpose of function is to return records from the database based on a specified price range
     * @param tempPriceLower an integer representing the lower price point
     * @param tempPriceUpper an integer representing the upper price point
     * @return an ArrayList of Book objects representing the records from the database
     * @throws SQLException
     */
    public ArrayList<Book> getBookByPriceRange(int tempPriceLower, int tempPriceUpper) throws SQLException {
        ArrayList<Book> bookByPrice = new ArrayList<>();
        Connection dbConnection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        //sql query
        String query = "SELECT * FROM books WHERE Price Between ? and ? ORDER BY Price, Title;";

        try {
            dbConnection = getDBConnection();
            ps = dbConnection.prepareStatement(query);
            //using a prepared statement to insert the values
            System.out.println("DBQuery: " + query + " values: " +tempPriceLower + " and " + tempPriceUpper);
            ps.setInt(1,tempPriceLower);
            ps.setInt(2,tempPriceUpper);
            // execute SQL query
            result = ps.executeQuery();

            while (result.next()) {


                int book_id = result.getInt("ID");
                String title = result.getString("Title");
                String author = result.getString("Author");
                int year = result.getInt("Year");
                int edition = result.getInt("Edition");
                String publisher = result.getString("Publisher");
                String isbn = result.getString("ISBN");
                String cover = result.getString("Cover");
                String condition = result.getString("Condition");
                int price = result.getInt("Price");
                String notes = result.getString("Notes");
                //this gets the books in the database into an ArrayList
                bookByPrice.add(new Book(book_id, title, author, year, edition, publisher, isbn, cover, condition, price, notes));

            }//if no book is returned this sends back a book with no data as the result
			if(bookByPrice==null) {
				bookByPrice.add(new Book(0, "NO DATA", "NO DATA", 0, 0, "NO DATA", "0", "NO DATA", "NO DATA", 0, "NO DATA"));
				return bookByPrice;
			}
        } finally {
            if (result != null) {
                result.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }//returns the ArrayList
        return bookByPrice;
    }

    /** The purpose of this function is to remove a record from the database based on a book's ID
     * @param deleteBook an integer representing the book's ID
     * @throws SQLException
     */
    //this function deletes a user specified book
    public void deleteBook(int deleteBook) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String query = "DELETE FROM books WHERE ID = ?;";
        try {
            dbConnection = getDBConnection();
            //using a prepared statement to insert the values
            ps = dbConnection.prepareStatement(query);
            System.out.println(query + " values: " +deleteBook);
            ps.setInt(1,deleteBook);
            // execute SQL query
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /** The purpose of this function is to update a book's price based on ID
     * @param nextID an integer representing the book's ID
     * @param nextPrice an integer representing the new Price
     * @throws SQLException
     */
    //function to update the books price
    public void updateBookPrice(int nextID, int nextPrice) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement ps = null;

        String query = "UPDATE books " + "SET Price= ? WHERE ID = ?;";

        try {
            dbConnection = getDBConnection();
            //using a prepared statement to insert the values
            ps = dbConnection.prepareStatement(query);
            System.out.println(query + " values: " + nextPrice + " and "+ nextID);
            ps.setInt(1,nextPrice);
            ps.setInt(2,nextID);
            // execute SQL update
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            //returns a boolean right now, this might be adopted to ask the user if they are sure before commiting a record change in final build
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    /** The purpose of this function is to add a book record to the database
     * @param in a book object representing the record we wish to add to the database
     * @throws SQLException
     */
    //function to insert record
    public void insertRecordIntoBooksTable(Book in) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        //sql string
        String update = "INSERT INTO books (ID, Title, Author, Year, Edition, Publisher, ISBN, Cover, Condition, Price, Notes) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        try {
            dbConnection = getDBConnection();
            ps = dbConnection.prepareStatement(update);
            System.out.println(update + " values: " +in.getBook_id() +", " + in.getTitle() + ", " +in.getAuthor() + ", " + in.getYear() + ", " + in.getEdition() + ", " + in.getPublisher() + ", " +
            in.getIsbn() + ", " + in.getCover() + ", " + in.getCondition() + ", " + in.getPrice() + ", " + in.getNotes());
            ps.setInt(1,in.getBook_id());
            ps.setString(2,in.getTitle());
            ps.setString(3,in.getAuthor());
            ps.setInt(4,in.getYear());
            ps.setInt(5,in.getEdition());
            ps.setString(6,in.getPublisher());
            ps.setString(7,in.getIsbn());
            ps.setString(8,in.getCover());
            ps.setString(9,in.getCondition());
            ps.setInt(10,in.getPrice());
            ps.setString(11,in.getNotes());
            // execute SQL query
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }

        }


    }

}
