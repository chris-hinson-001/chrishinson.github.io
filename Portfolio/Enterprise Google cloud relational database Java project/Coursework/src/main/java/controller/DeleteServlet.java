package controller;

import java.io.IOException;

import model.FilmDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This is the delete servlet that deletes a record in the database.
/**
 * Servlet implementation class Control
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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
		String ID = request.getParameter("film-id-d");
		// FilmDAO returns 0 or 1 depending on outcome. the outcome is translated into a
		// readable String and sent to the front end.
		result = dao.deleteFilm(Integer.parseInt(ID));
		if (result == 1) {
			Success = "<h2> Record Successfully deleted</h2>";
		} else {
			Success = "<h2> Error, record deletion has failed</h2>";
		}
		request.setAttribute("SuccessString", Success);
		String outputPage = "/WEB-INF/results/films-result.jsp";
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
