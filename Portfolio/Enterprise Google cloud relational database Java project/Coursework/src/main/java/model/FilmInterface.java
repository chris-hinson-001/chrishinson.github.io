package model;

import java.util.ArrayList;

//interface is used in the FilmDAO
public interface FilmInterface {

	public int addFilm(Film finfo);

	public int deleteFilm(int filmid);

	public int updateFilm(Film f);

	public ArrayList<Film> listFilm();

	public ArrayList<Film> searchFilm(String searchStr);

}
