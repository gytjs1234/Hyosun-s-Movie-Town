package net.hyosun.movie.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import net.hyosun.movie.model.Week_rankingVO;

public interface Week_rankingService {

	public List<Week_rankingVO> getWeek_rank() throws IOException, ParseException;
}
