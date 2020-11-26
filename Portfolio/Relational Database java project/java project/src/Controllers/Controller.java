package Controllers;

import ConsoleMenu.ConsoleMenu;

import java.sql.SQLException;
/**
 * The purpose of this class is to run the console Menu
 * @author Chris Hinson
 */
public class Controller {
	/**The purpose of this function is to run the console menu
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		//this instantiates the console menu and activates the loop for the user
		ConsoleMenu test = new ConsoleMenu();
		test.loopMenu();
	}

}
