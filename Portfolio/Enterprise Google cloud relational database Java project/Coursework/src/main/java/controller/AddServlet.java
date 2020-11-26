package controller;

import java.io.IOException;

import model.FilmDAO;
import model.Film;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This is the add servlet that adds a record to the database.
/**
 * Servlet implementation class Control
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		FilmDAO dao = FilmDAO.getSingletonDAO();
		int result = -1;
		String Success;
		// check parameters
		String ID = request.getParameter("film-id-a");
		String Title = request.getParameter("film-title-a");
		String Year = request.getParameter("film-year-a");
		String Director = request.getParameter("film-director-a");
		String Stars = request.getParameter("film-stars-a");
		String Review = request.getParameter("film-review-a");
		String outputPage;
		Film newfilm = new Film(Integer.parseInt(ID), Title, Integer.parseInt(Year), Director, Stars, Review);
		// to deal with null pointer handler missing entries are replaced with spaces
		// for strings
		if (newfilm.getTitle().isEmpty()) {
			newfilm.setTitle(" ");
		}
		if (newfilm.getDirector().isEmpty()) {
			newfilm.setDirector(" ");
		}
		if (newfilm.getStars().isEmpty()) {
			newfilm.setStars(" ");
		}
		if (newfilm.getReview().isEmpty()) {
			newfilm.setReview(" ");
		}
		// FilmDAO returns 0 or 1 depending on outcome. the outcome is translated into a
		// readable String and sent to the front end.
		result = dao.addFilm(newfilm);
		if (result == 1) {
			Success = "<h2> Record Successfully added</h2>";
		} else {
			Success = "<h2> Error, record deletion has failed</h2>";
		}
		request.setAttribute("SuccessString", Success);
		outputPage = "/WEB-INF/results/films-result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
