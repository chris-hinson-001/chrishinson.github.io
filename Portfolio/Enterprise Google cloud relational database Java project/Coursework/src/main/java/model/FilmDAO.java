package model;

import java.util.ArrayList;
import java.sql.*;

public class FilmDAO implements FilmInterface {
//this class is the Database access object
	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;
	// The user password and url are all used to connect to the database on google
	// cloud
	String user = "root";
	String password = "bob";
	String url = "jdbc:mysql://34.77.12.33:3306/filmDB?useSSL=false";
//The DAO is set up as a singleton
	private static FilmDAO singletonDAO;

	private FilmDAO() {
	}

	public static synchronized FilmDAO getSingletonDAO() {
		if (singletonDAO == null) {
			singletonDAO = new FilmDAO();
		}
		return singletonDAO;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//this private function is used locally to iterate through results, it is private as it is only used internally
	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

//function used to list all films in the database.
	public ArrayList<Film> listFilm() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			// limit is set to 50 as the database is large
			String selectSQL = "select * from films limit 50";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilms;
	}

	// function to search for films using the films title
	public ArrayList<Film> searchFilm(String searchStr) {
		openConnection();
		ArrayList<Film> films = new ArrayList<Film>();
		try {
			String selectSQL = "select * from films where title like '%" + searchStr + "%';";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				films.add(oneFilm);
			}
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return films;
	}

	// function to add new films to the database
	public int addFilm(Film f) {
		openConnection();
		int result = -1;
		try {
			String insertSQL = "INSERT INTO films (ID, Title, year, director, stars, review) values (" + f.getId()
					+ ",'" + f.getTitle() + "'," + f.getYear() + ",'" + f.getDirector() + "','" + f.getStars() + "','"
					+ f.getReview() + "');";
			result = stmt.executeUpdate(insertSQL);
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return result;
	}

	// function to delete a film from the database utilising ID
	public int deleteFilm(int id) {
		openConnection();
		int result = -1;
		try {
			String insertSQL = "Delete from films where id=?" + id;
			result = stmt.executeUpdate(insertSQL);
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return result;
	}

	// Function to update an existing film record utilising id
	public int updateFilm(Film f) {
		openConnection();
		int result = -1;
		try {
			String insertSQL = "Update films set Title ='" + f.getTitle() + "', year =" + f.getYear() + ", director='"
					+ f.getDirector() + "', stars ='" + f.getStars() + "', review = '" + f.getReview() + "'where id="
					+ f.getId();
			result = stmt.executeUpdate(insertSQL);
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return result;
	}
}