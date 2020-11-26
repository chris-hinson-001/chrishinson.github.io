package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import model.FilmDAO;
import model.Film;

//This class handles all the restful commands in one controller class
@Path("/filmsDB")
public class RestfulFilmController {

	// this function handles get all films with the plain text format
	@GET
	@Path("/text")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmsText() throws SQLException {
		FilmDAO dao = FilmDAO.getSingletonDAO();
		ArrayList<Film> Films = new ArrayList<Film>();
		Films.addAll(dao.listFilm());
		return Films.toString();
	}

	// this function handles get all films with the xml format
	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String getFilmsXML() throws SQLException {
		FilmDAO dao = FilmDAO.getSingletonDAO();
		ArrayList<Film> Films = new ArrayList<Film>();
		Films.addAll(dao.listFilm());
		XStream xstream = new XStream();
		xstream.alias("film", Film.class);
		String xmlstring = xstream.toXML(Films);
		return xmlstring;

	}

	// this function handles get all films with the json format
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFilmsJSON() throws SQLException {
		FilmDAO dao = FilmDAO.getSingletonDAO();
		ArrayList<Film> Films = new ArrayList<Film>();
		Films.addAll(dao.listFilm());
		Gson gson = new Gson();
		String jsonstring = gson.toJson(Films);
		return jsonstring;
	}

	// this function handles add films
	@POST
	@Path("add")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addFilm(@FormParam("film-id-a") String id, @FormParam("film-title-a") String title,
			@FormParam("film-year-a") String year, @FormParam("film-director-a") String director,
			@FormParam("film-stars-a") String stars, @FormParam("film-review-a") String review)
			throws IOException, SQLException {
		int result = -1;
		String Success;
		Film film = new Film(Integer.parseInt(id), title, Integer.parseInt(year), "", "", "");
		// if null is invoked to replace blank entries to avoid null pointer errors
		ifNull(director, stars, review, film);
		FilmDAO dao = FilmDAO.getSingletonDAO();
		// FilmDAO returns 0 or 1 depending on outcome. the outcome is translated into a
		// readable String and sent to the front end.
		result = dao.addFilm(film);
		if (result == 1) {
			Success = "<h2> Record Successfully Added</h2>";
		} else {
			Success = "<h2> Error, record deletion has failed</h2>";
		}
		return Success;
	}

	// this function handles update films
	@PUT
	@Path("update")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateFilm(@FormParam("film-id-u") String id, @FormParam("film-title-u") String title,
			@FormParam("film-year-u") String year, @FormParam("film-director-u") String director,
			@FormParam("film-stars-u") String stars, @FormParam("film-review-u") String review)
			throws IOException, SQLException {
		int result = -1;
		String Success;
		Film updateFilm = new Film(Integer.parseInt(id), title, Integer.parseInt(year), "", "", "");
		// if null is invoked to replace blank entries to avoid null pointer errors
		ifNull(director, stars, review, updateFilm);
		FilmDAO dao = FilmDAO.getSingletonDAO();
		// FilmDAO returns 0 or 1 depending on outcome. the outcome is translated into a
		// readable String and sent to the front end.
		result = dao.updateFilm(updateFilm);
		if (result == 1) {
			Success = "<h2> Record Successfully Updated</h2>";
		} else {
			Success = "<h2> Error, record update has failed</h2>";
		}
		return Success;
	}

	// this function handles delete films
	@DELETE
	@Path("delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteFilmText(@FormParam("film-id-d") String id, @Context HttpServletResponse servletResponse)
			throws IOException, SQLException {
		int result = -1;
		String Success;
		// check a null entry has not been sent
		if (id == null)
			throw new RuntimeException("Delete: film with " + id + " not found");
		else {
			FilmDAO dao = FilmDAO.getSingletonDAO();
			result = dao.deleteFilm(Integer.parseInt(id));

		}
		// FilmDAO returns 0 or 1 depending on outcome. the outcome is translated into a
		// readable String and sent to the front end.
		if (result == 1) {
			Success = "<h2> Record Successfully deleted</h2>";
		} else {
			Success = "<h2> Error, record deletion has failed</h2>";
		}
		return Success;
	}

	// this function handles search films using titles with the plain text format
	@GET
	@Path("search/text")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchFilmText(@QueryParam("film-title-s") String title) throws IOException, SQLException {
		FilmDAO dao = FilmDAO.getSingletonDAO();
		ArrayList<Film> Films = new ArrayList<Film>();
		// check a null entry has not been sent
		if (title == null)
			throw new RuntimeException("Search: Film with " + title + " not found");
		Films.addAll(dao.searchFilm(title));
		String ret = Films.toString();
		return ret.subSequence(0, ret.length() - 1).toString();
	}

	// this function handles search films using titles with the xml format
	@GET
	@Path("search/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String searchFilmXML(@QueryParam("film-title-s") String title) throws IOException, SQLException {
		FilmDAO dao = FilmDAO.getSingletonDAO();
		ArrayList<Film> Films = new ArrayList<Film>();
		// check a null entry has not been sent
		if (title == null)
			throw new RuntimeException("Search: Film with " + title + " not found");
		Films.addAll(dao.searchFilm(title));
		XStream xstream = new XStream();
		xstream.alias("film", Film.class);
		String xmlstring = xstream.toXML(Films);
		return xmlstring;

	}

	// this function handles search films using titles with the jsonformat
	@GET
	@Path("search/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchFilmJSON(@QueryParam("film-title-s") String title) throws IOException, SQLException {
		FilmDAO dao = FilmDAO.getSingletonDAO();
		ArrayList<Film> Films = new ArrayList<Film>();
		// check a null entry has not been sent
		if (title == null)
			throw new RuntimeException("Search: Film with " + title + " not found");
		Films.addAll(dao.searchFilm(title));
		Gson gson = new Gson();
		String jsonstring = gson.toJson(Films);
		return jsonstring;

	}

	// function to check if strings are null, if not null update the film object
	// with the value
	private void ifNull(String director, String stars, String review, Film updateFilm) {
		if (director != null) {

			updateFilm.setDirector(director);
		}
		if (stars != null) {

			updateFilm.setStars(stars);
		}
		if (review != null) {

			updateFilm.setReview(review);
		}
	}
}
