package DAO;
/** The purpose of this class is to represent a book record
 * @author Chris Hinson
 */
//this creates the book class with its getters and setters
public class Book {
	//list of private variables
	/**The Books ID in the system*/
	private int book_id;
	/**The Books title */
	private String title;
/**The Books Author */
private String author;
/**The year the book was released*/
private int year;
/**The books edition*/
private int edition;
/**The books publisher*/
private String publisher;
/**The books ISBN*/
private String isbn;
/**The books cover*/
private String cover;
/**The books condition*/
private String condition;
/**The books price*/
private int price;
/**Any notes about the book*/
private String notes;
	//constructor

	/**	Constructor for a book record
	 * @param book_id The Books ID in the system
	 * @param title The Books title
	 * @param author The Books Author
	 * @param year The year the book was released
	 * @param edition The books edition
	 * @param publisher The books publisher
	 * @param isbn The books ISBN
	 * @param cover The books cover
	 * @param condition The books condition
	 * @param price The books price
	 * @param notes Any notes about the book
	 */
	public Book(int book_id, String title, String author, int year, int edition, String publisher, String isbn,
            String cover, String condition, int price, String notes) {
	super();
	this.book_id = book_id;
	this.title = title;
	this.author = author;
	this.year = year;
	this.edition = edition;
	this.publisher = publisher;
	this.isbn = isbn;
	this.cover = cover;
	this.condition = condition;
	this.price = price;
	this.notes = notes;
}
//getters & setters
	/** Gets the book's ID
	 * @return an integer representing the book's ID
	 */
	public int getBook_id() {
	return book_id;
}

	/** Sets the book's ID
	 * @param book_id an integer containing the book's ID
	 */
	public void setBook_id(int book_id) {
	this.book_id = book_id;
}

	/** Gets the book's Title
	 * @return a String representing the book's Title
	 */
	public String getTitle() {
	return title;
}

	/** Sets the book Title
	 * @param title a String containing the book's Title
	 */
	public void setTitle(String title) {
	this.title = title;
}

	/** Gets the book's Author
	 * @return  a String representing the book's Author
	 */
	public String getAuthor() {
	return author;
}

	/** Sets the book Author
	 * @param author a String containing the book's Author
	 */
	public void setAuthor(String author) {
	this.author = author;
}
	/** Gets the book's year
	 * @return an integer representing the book's year
	 */
public int getYear() {
	return year;
}
	/** Sets the book's year
	 * @param year an integer containing the book's year
	 */
public void setYear(int year) {
	this.year = year;
}
	/** Gets the book's edition
	 * @return an integer representing the book's edition
	 */
public int getEdition() {
	return edition;
}
	/** Sets the book's edition
	 * @param edition an integer containing the book's edition
	 */
public void setEdition(int edition) {
	this.edition = edition;
}
	/** Gets the book's Publisher
	 * @return  a String representing the book's Publisher
	 */
public String getPublisher() {
	return publisher;
}
	/** Sets the book Publisher
	 * @param publisher a String containing the book's Publisher
	 */
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
	/** Gets the book's ISBN
	 * @return a String representing the book's ISBN
	 */
public String getIsbn() {
	return isbn;
}
	/** Sets the book ISBN
	 * @param isbn a String containing the book's ISBN
	 */
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
	/** Gets the book's Cover
	 * @return a String representing the book's Cover
	 */
public String getCover() {
	return cover;
}
	/** Sets the book Cover
	 * @param cover a String containing the book's Cover
	 */
public void setCover(String cover) {
	this.cover = cover;
}
	/** Gets the book's Condition
	 * @return a String representing the book's Condition
	 */
public String getCondition() {
	return condition;
}
	/** Sets the book Condition
	 * @param condition a String containing the book's Condition
	 */
public void setCondition(String condition) {
	this.condition = condition;
}
	/** Gets the book's Price
	 * @return an integer representing the book's Price
	 */
public int getPrice() {
	return price;
}
	/** Sets the book's Price
	 * @param price an integer containing the book's Price
	 */
public void setPrice(int price) {
	this.price = price;
}
	/** Gets the book's Notes
	 * @return a String representing the book's Notes
	 */
public String getNotes() {
	return notes;
}
	/** Sets the book Notes
	 * @param notes a String containing the book's Notes
	 */
public void setNotes(String notes) {
	this.notes = notes;
}

	/** Method to create String that will display a complete record of the book
	 * @return String representing record of the book
	 */
	public String toString() {
	return "--------------------" +
			"\n ID: " + this.book_id +
			"\n Title: " + this.title +
			"\n Author: " + this.author +
			"\n Year: " + this.year +
			"\n Edition: " + this.edition +
			"\n Publisher: " + this.publisher +
			"\n ISBN: " + this.isbn +
			"\n Cover: " + this.cover +
			"\n Condition: " + this.condition +
			"\n Price: " + this.price +
			"\n Notes: " + this.notes +
			"\n--------------------";
}

}
