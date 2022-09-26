package net.hyosun.movie.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import net.hyosun.movie.model.Movie_ListVO;

public interface Movie_ListService {

	public List<Movie_ListVO> getMovie_List(String movieNm, String directorNm) throws IOException, ParseException;

}
