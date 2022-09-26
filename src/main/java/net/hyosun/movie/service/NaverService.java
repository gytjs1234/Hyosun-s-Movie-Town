package net.hyosun.movie.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface NaverService {
	//naver api 호출 주소
	public String queryString(String search) throws UnsupportedEncodingException;

	//api 호출
	public List<Object> getNaver(String queryString);

}
