package net.hyosun.movie.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import net.hyosun.movie.model.Daily_rankingVO;

public interface Daily_rankingService {

	public List<Daily_rankingVO> getDaily_rank() throws IOException, ParseException;

}
