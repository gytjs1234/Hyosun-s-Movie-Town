package net.hyosun.movie.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.hyosun.movie.config.URL_config;
import net.hyosun.movie.model.Daily_rankingVO;
import net.hyosun.movie.service.Daily_rankingService;
import net.hyosun.movie.service.NaverService;

@Service
public class Daily_rankingServiceImpl implements Daily_rankingService {

	@Autowired
	private NaverService naver;

	@Override
	public List<Daily_rankingVO> getDaily_rank() throws IOException, ParseException {
		String daily_rank_url = URL_config.DAILY_URL; // 호출url

		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");

		Date today = new Date(); // 오늘 날짜
		String st_today = dateForm.format(today); // 오늘 날짜 String형 변환
		Date setDate = dateForm.parse(st_today); // 기준 날짜 설정

		Calendar cal = new GregorianCalendar(Locale.KOREA); // 한국 날짜 기준으로 설정
		cal.setTime(setDate); // 기준 날짜 설정
		cal.add(Calendar.DATE, -1); // 하루 전으로 설정
		String date = dateForm.format(cal.getTime()); // 하루 전 날짜 String 타입 지정

		daily_rank_url += "=" + date;

		URL url = new URL(daily_rank_url.toString());
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

		JSONObject boxOfficeResult = jObject.getJSONObject("boxOfficeResult");

		JSONObject dailyBoxOfficeList = boxOfficeResult.getJSONObject("dailyBoxOfficeList");

		// 베열로 담는이유는? 데이터가 많으니까
		JSONArray dailyBoxOffice = dailyBoxOfficeList.getJSONArray("dailyBoxOffice");

		// json 변환할때 쓰는 도구
		Gson gson = new Gson(); // 배열로 담은 데이터를 toString 으로 호출하여 리스트에 저장
		List<Daily_rankingVO> list = gson.fromJson(dailyBoxOffice.toString(), new TypeToken<List<Daily_rankingVO>>() {
		}.getType());

		for (int i = 0; i < list.size(); i++) {
			naver.getNaver(naver.queryString(list.get(i).getMovieNm()));
		}

		return list;
	}

}
