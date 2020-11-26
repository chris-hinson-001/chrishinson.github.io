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

//This is the update servlet that updates a record in the database.
/**
 * Servlet implementation class Control
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		String ID = request.getParameter("film-id-u");
		String Title = request.getParameter("film-title-u");
		String Year = request.getParameter("film-year-u");
		String Director = request.getParameter("film-director-u");
		String Stars = request.getParameter("film-stars-u");
		String Review = request.getParameter("film-review-u");
		String outputPage;
		Film updatefilm = new Film(Integer.parseInt(ID), Title, Integer.parseInt(Year), Director, Stars, Review);
		// to deal with null pointer handler missing entries are replaced with spaces
		// for strings
		if (updatefilm.getTitle().isEmpty()) {
			updatefilm.setTitle(" ");
		}
		if (updatefilm.getDirector().isEmpty()) {
			updatefilm.setDirector(" ");
		}
		if (updatefilm.getStars().isEmpty()) {
			updatefilm.setStars(" ");
		}
		if (updatefilm.getReview().isEmpty()) {
			updatefilm.setReview(" ");
		}
		// FilmDAO returns 0 or 1 depending on outcome. the outcome is translated into a
		// readable String and sent to the front end.
		result = dao.updateFilm(updatefilm);
		if (result == 1) {
			Success = "<h2> Record Successfully updated</h2>";
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
