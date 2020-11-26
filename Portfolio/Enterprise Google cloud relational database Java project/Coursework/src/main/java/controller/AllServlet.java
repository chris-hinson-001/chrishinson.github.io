package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.FilmDAO;
import model.Film;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

//This is the all servlet that returns all records in the database.
/**
 * Servlet implementation class Control
 */
@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllServlet() {
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
		ArrayList<Film> films = dao.listFilm();
		request.setAttribute("films", films);
		// check output format, then determine which is the appropriate handler and
		// return to front end
		String format = request.getParameter("format");
		String outputPage;
		if ("xml".equals(format)) {
			response.setContentType("text/xml");
			XStream xstream = new XStream();
			xstream.alias("film", Film.class);
			String xmlstring = xstream.toXML(films);
			request.setAttribute("xmlstring", xmlstring);
			outputPage = "/WEB-INF/results/films-xml.jsp";
		} else if ("json".equals(format)) {
			response.setContentType("application/json");
			Gson gson = new Gson();
			String jsonstring = gson.toJson(films);
			request.setAttribute("jsonstring", jsonstring);
			outputPage = "/WEB-INF/results/films-json.jsp";
		} else {
			response.setContentType("text/plain");
			request.setAttribute("plainstring", films.toString());
			outputPage = "/WEB-INF/results/films-string.jsp";
		}
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
