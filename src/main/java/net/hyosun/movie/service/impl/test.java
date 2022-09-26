package net.hyosun.movie.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.hyosun.movie.config.URL_config;
import net.hyosun.movie.model.Movie_ListVO;

@Service
public class test {

	public static void main(String[] args) throws IOException {
		String movie_list_url = URL_config.MOVIE_LIST_URL; // 호출url

//		movie_list_url = "&" + URLEncoder.encode("movieNm", "UTF-8");
//		movie_list_url += "=" + "범죄도시2";
//		movie_list_url = "&" + URLEncoder.encode("directorNm", "UTF-8");
//		movie_list_url += "=" + "";

		URL url = new URL(movie_list_url.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		String retString = "";
		String line;
		while ((line = rd.readLine()) != null) {
			retString += line;
		}
		rd.close();
		conn.disconnect();

		JSONObject json = XML.toJSONObject(retString);
		String jsonStr = json.toString(3);

		JSONObject jObject = new JSONObject(jsonStr);

		JSONObject movieListResult = jObject.getJSONObject("movieListResult");

		JSONObject movieList = movieListResult.getJSONObject("movieList");

		// 베열로 담는이유는? 데이터가 많으니까
		JSONArray movie = movieList.getJSONArray("movie");

		// json 변환할때 쓰는 도구
		Gson gson = new Gson(); // 배열로 담은 데이터를 toString 으로 호출하여 리스트에 저장
		List<Movie_ListVO> list = gson.fromJson(movie.toString(), new TypeToken<List<Movie_ListVO>>() {
		}.getType());
		
		System.out.println(list.get(0).getDirectors());
	}

}
