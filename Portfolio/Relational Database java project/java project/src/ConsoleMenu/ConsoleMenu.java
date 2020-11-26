package ConsoleMenu;

import DAO.BookDao;
import DAO.Book;
import DAO.ISBNValidator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The purpose of this class is to construct the console menu, accept and validate user inputs and call functions from the BookDAO
 * @author Chris Hinson
 */
public class ConsoleMenu {
    /**The message that is displayed with the console menu*/
    private String message;
    /**an integer used to hold the users input selection in the console menu*/
    private int action;
    /**The message that is displayed if the user inputs an invalid selection*/
    private String errorMessage;
    /**an array which holds the menu items*/
    private String[] menuItem;
    /**a String which displays the current menu's title*/
    private String menuTitle;
    /**a scanner to record the user's input*/
    private Scanner sc = new Scanner(System.in);

    //constructor
    /**
     * Constructor, no arguments to construct the console menu
     */
    public ConsoleMenu() {
    }

    /** Gets the menu's Message
     * @return a String representing the menu's message
     */
    //getters and setters
    public String getMessage() {
        return message;
    }

    /** Sets the menu's Message
     * @param message a String representing the menu's message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /** Gets the user's Action
     * @return an integer representing the user's action
     */
    public int getAction() {
        return action;
    }

    /** Sets the user's Action
     * @param action an integer representing the user's action
     */
    public void setAction(int action) {
        this.action = action;
    }
    /** Gets the menu's Error Message
     * @return a String representing the menu's error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /** Sets the menu's Error Message
     * @param errorMessage a String representing the menu's error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /** Gets the menu's Items
     * @return an array of the menu's items
     */
    public String[] getMenuItem() {
        return menuItem;
    }
    /** Gets the menu's Title
     * @return a String representing the menu's title
     */
    public String getMenuTitle() {
        return menuTitle;
    }

    /** The purpose of this function is to set and display the main menu's title, menu items and message and set the error message
     */
    //this function will display message and defines the prompt and error messages
    public void displayMenu() {
    	this.menuTitle = "Book Inventory";
    	this.menuItem=(new String[]{"Retrieve all books", "Search for book", "Insert new book into database"
				, "Update existing book price details", "Delete book from database", "Exit"});
        System.out.println(getMenuTitle());
        System.out.println("-------------------------");
        this.message = "Please enter a number between (1-" + getMenuItem().length + ") to choose an action ";
        this.errorMessage = "Error, invalid entry. Please enter a number between (1-" + getMenuItem().length + ")\n";
        for (int i = 0; i < getMenuItem().length; i++) {
            System.out.println((i + 1) + " - " + this.menuItem[i]);
        }
        System.out.println("");
    }
    /** The purpose of this function is to set and display the sub menu's title, menu items and message and set the error message
     */
    //this function will redefine the menuItem, menuTitle and error message when the SubMenu is displayed
    public void displaySubMenu() {
		this.menuTitle = "Search Menu";
		this.menuItem = new String[]{"Search by Book ID", "Search by Title", "Search by Author", "Search by Price Range", "Return"};
		System.out.println(getMenuTitle());
		System.out.println("-------------------------");
        this.setMessage("Please enter a number between (1-" + menuItem.length + ") to choose an action ");
        this.setErrorMessage("Error, invalid entry. Please enter a number between (1-" + menuItem.length + ")\n");
        for (int i = 0; i < menuItem.length; i++) {
            System.out.println((i + 1) + " - " + this.menuItem[i]);
        }
        System.out.println("");
    }

    /** The purpose of this function is to read the users inputs and call the functions they select from the BookDao
     * This function uses the scanner to read the user input and is the core function of the class
     * @throws SQLException
     */
    //this function reads the users actions and connects their choice to the DAO
    public void userAction() throws SQLException {
        BookDao catalogue = new BookDao();
        ISBNValidator isbnValidator = new ISBNValidator();
        setAction(-1);
        /* this loop validates a users action input, it operate slightly differently from the number validation
        because it needs to re-display the menu if there is an incorrect input
         */
        do {
            System.out.println(this.getMessage());

            String next = sc.nextLine();
            try {
                setAction(Integer.parseInt(next));
            } catch (NumberFormatException exp) {
            }
            if ((getAction() < 0) || getAction() > getMenuItem().length) {
                System.out.println(this.getErrorMessage());
				displayMenu();
            }
        } while ((getAction() < 0) || (getAction() > getMenuItem().length));
        //switch statement will assign the users input to a function
        switch (getAction()) {
            case 1:
                System.out.println("Retrieving all books ...");
                //getting all books from the BookDao
                ArrayList<Book> allBooks = catalogue.getAllBooks();
                for (Book a : allBooks) {
                    System.out.println(a.toString());
                }
                System.out.println("");
                break;
                /* To accommodate multiple search functions we have created a sub menu, a switch nested inside a switch
                it also starts the validation loop again which is different as it has to display the submenu if there
                is an error
                 */
            case 2:
                displaySubMenu();
                do {
                    System.out.println(this.getMessage());
                    String next = sc.nextLine();
                    try {
                        setAction(Integer.parseInt(next));
                    } catch (NumberFormatException exp) {
                    }
                    if ((getAction() < 0) || getAction() > getMenuItem().length) {
                        System.out.println(this.getErrorMessage());
                        displaySubMenu();
                    }
                } while ((getAction() < 0) || (getAction() > getMenuItem().length));
                switch (getAction()) {
                    case 1:
                        //we use the number validator to ensure a user is entering a number into the book ID
                        int temp_ID = numberValidator("Book ID");
                        System.out.println("Searching for book by ID ...");
                        //getting bookByID from the the BookDAO
                        Book temp = catalogue.getBookById(temp_ID);
                        System.out.println(temp.toString());
                        System.out.println("");
                        break;
                    case 2:
                        System.out.println("Please enter Title (case sensitive)");
                        String searchTitle = (sc.nextLine());
                        System.out.println("Searching for book by Title ...");
                        ////getting bookByTitle from the the BookDAO
                        ArrayList<Book> bookByTitle = catalogue.getBookByTitle(searchTitle);
                        for (Book a : bookByTitle) {
                            System.out.println(a.toString());
                        }
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("Please enter Author (case sensitive)");
                        String searchAuthor = (sc.nextLine());
                        System.out.println("Searching for book by Author ...");
                        //getting bookByAuthor from the the BookDAO
                        ArrayList<Book> bookByAuthor = catalogue.getBookByAuthor(searchAuthor);
                        for (Book a : bookByAuthor) {
                            System.out.println(a.toString());
                        }
                        System.out.println("");
                        break;
                    case 4:
                        //we use the number validator to ensure a user is entering a number into the price ranges
                        int tempPriceLower = numberValidator("lower price range");
                        int tempPriceUpper = numberValidator("upper price range");
                        System.out.println("Searching for book by Price ...");
                        //getting bookByPrice from the the BookDAO
                        ArrayList<Book> bookByPrice = catalogue.getBookByPriceRange(tempPriceLower, tempPriceUpper);
                        for (Book a : bookByPrice) {
                            System.out.println(a.toString());
                        }
                        System.out.println("");
                        break;
                    case 5:
                        //this returns the user to the ConsoleMenu from the SubMenu
                        break;
                    default:
                        setAction(getAction() - 1);
                }
                break;
            case 3:
                //setting up a dummy book so values can be checked
                Book in = new Book(0, "", "", 0, 0, "", "", "", "", 0, "");
                int decision;
                //we use the number validator to ensure a user is entering a number into the book ID
                in.setBook_id(numberValidator("Book ID"));
                System.out.println("Please enter Title...");
                in.setTitle(sc.nextLine());
                System.out.println("Please enter Author...");
                in.setAuthor(sc.nextLine());
                //we use the number validator to ensure a user is entering a number into the year
                in.setYear(numberValidator("Year"));
                //we use the number validator to ensure a user is entering a number into the edition
                in.setEdition(numberValidator("Edition"));
                System.out.println("Please enter Publisher...");
                in.setPublisher(sc.nextLine());
                System.out.println("Please enter ISBN...");
                //we use the isbn validator to ensure a user is entering a valid isbn
                in.setIsbn(sc.nextLine());
                while (isbnValidator.validateIsbn(in.getIsbn())!= true){
                    System.out.println("Invalid ISBN, please re-enter ISBN");
                    in.setIsbn(sc.nextLine());
                }
                System.out.println("Please enter Cover...");
                in.setCover(sc.nextLine());
                System.out.println("Please enter Condition...");
                in.setCondition(sc.nextLine());
                //we use the number validator to ensure a user is entering a number into the price
                in.setPrice(numberValidator("Price"));
                System.out.println("Please enter any notes...");
                in.setNotes(sc.nextLine());
                //prints a record of the book just entered
                System.out.println(in.toString());
                //Binary validator gives the user the chance to review their entry and whether or not to enter or cancel it
                decision = binaryValidator("Insert");
                if (decision == 1) {
                    System.out.println("Inserting record...");
                    //inserting the new record with the BookDao
                    catalogue.insertRecordIntoBooksTable(in);
                    System.out.println();
                    break;
                } else {
                    System.out.println("Returning to Main menu");
                    System.out.println();
                    break;
                }
            case 4:
                //we use the number validator to ensure a user is entering a number into the book ID
                int nextId = numberValidator("Book ID");
                //we use the number validator to ensure a user is entering a number into the price
                int nextPrice = numberValidator("Price");
                //we use the id to get a record of the book from the database and display the new and old prices
                Book priceUpdated = catalogue.getBookById(nextId);
                System.out.println("\n ID: " + priceUpdated.getBook_id() +
                        "\n Title: " + priceUpdated.getTitle() +
                        "\n Author: " + priceUpdated.getAuthor() +
                        "\n Old Price: " + priceUpdated.getPrice() +
                        "\n New Price: " + nextPrice);
                //Binary validator gives the user the chance to review their entry and whether or not to enter or cancel it
                int choice = binaryValidator("Update");
                if (choice == 1) {
                    System.out.println("Updating book price...");
                    // updating the record with the new book price
                    catalogue.updateBookPrice(nextId, nextPrice);
                    System.out.println("");
                    break;
                } else {
                    System.out.println("Returning to Main menu");
                    System.out.println();
                    break;
                }
            case 5:
                int deleteID = numberValidator("Book ID");
                //we use the id to get a record of the book from the database and display it
                Book toBeDeleted = catalogue.getBookById(deleteID);
                System.out.println(toBeDeleted.toString());
                //Binary validator gives the user the chance to review their entry and whether or not to enter or cancel it
                int judgement = binaryValidator("Delete");
                if (judgement == 1) {
                    System.out.println("Deleting book...");
                    catalogue.deleteBook(deleteID);
                    System.out.println("");
                    break;
                } else {
                    System.out.println("Returning to Main menu");
                    System.out.println();
                    break;
                }
            case 6:
                System.out.println("Exiting Program...");
                System.exit(0);
                break;
            default:
                setAction(getAction() - 1);
        }
    }

    /** The purpose of this function is to loop the menu
     * It is the function required to get the console menu started in the controller
     * @throws SQLException
     */
    //this loops the menu, necessary to activate in the controller to get the interface working in the console
    public void loopMenu() throws SQLException {
        while (true) {

            this.displayMenu();
            this.userAction();
        }
    }

    /** The purpose of this function is to validate a users input as a number
     * this function will loop until an acceptable input has been used
     * @param catagory the string designating which catagory the user is required to input
     * @return an integer representing the users input
     */
    public int numberValidator(String catagory) {
        this.setErrorMessage("Error, invalid entry. Please enter a number...");
        int tempNum = 0;
        do {
            System.out.println("Please enter " + catagory + "...");
            //this takes the whole line as a string so we can avoid errors
            String next = sc.nextLine();

            try {
                //parses the string into an integer
                tempNum = (Integer.parseInt(next));
            } catch (NumberFormatException exp) {
            }
            if (tempNum <= 0) {
                //if not an integer prints the error message
                System.out.println(errorMessage);
            }
        } while (tempNum <= 0);
        //return the integer we parsed
        return tempNum;
    }
    /** The purpose of this function is confirm if a user wishes to complete an action
     * this function asks for a 1-0 input and will loop until this condition has been met
     * @param type the string designating which catagory the user is required to input
     * @return an integer representing the users input
     */
    public int binaryValidator(String type) {
        this.setErrorMessage("Error, invalid entry. Please enter 1 or 0");
        int yesOrNo = -1;
        do {
            System.out.println(type + " this record? Yes = 1 / No = 0 ");
            String next = sc.nextLine();
            //this takes the whole line as a string so we can avoid errors
            try {
                //parses the string into an integer
                yesOrNo = (Integer.parseInt(next));
            } catch (NumberFormatException exp) {
            }
            //if not 0 or 1 prints the error message
            if ((yesOrNo < 0) || (yesOrNo > 1)) {
                System.out.println(errorMessage);
            }
        } while ((yesOrNo < 0) || (yesOrNo > 1));
        //returns the 0 or 1
        return yesOrNo;
    }

}


