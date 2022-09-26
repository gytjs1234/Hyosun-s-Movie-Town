package net.hyosun.movie.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.hyosun.movie.config.NAVER_config;
import net.hyosun.movie.model.NaverParent;
import net.hyosun.movie.model.NaverVO;
import net.hyosun.movie.service.NaverService;

public class NaverServiceImpl implements NaverService {

	@Override
	public String queryString(String search) throws UnsupportedEncodingException {
		String queryString = NAVER_config.NAVER_API;
		String encodeSearch = null;

		encodeSearch = "?" + URLEncoder.encode("query", "UTF-8");
		encodeSearch += "=" + search;

		queryString += encodeSearch;

		return queryString;
	}

	@Override
	public List<Object> getNaver(String queryString) {
		URI restURI = null;

		try {
			restURI = new URI(queryString);
		} catch (URISyntaxException e) {
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(NAVER_config.HEADER.ID, NAVER_config.NAVER_CLIENT_ID);
		headers.set(NAVER_config.HEADER.SEC, NAVER_config.NAVER_CLIENT_SEC);

		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);

		RestTemplate restTemp = new RestTemplate();

		ResponseEntity<NaverParent<NaverVO>> resData = null;

		resData = restTemp.exchange(restURI, HttpMethod.GET, entity,
				new ParameterizedTypeReference<NaverParent<NaverVO>>() {
				});

		return resData.getBody().items;
	}

	
	
	public String getJson(String queryString) {
		URL url = null;

		HttpsURLConnection httpCon = null;

		try {
			url = new URL(queryString);
			httpCon = (HttpsURLConnection) url.openConnection();

			httpCon.setRequestMethod("GET");

			httpCon.setRequestProperty(NAVER_config.HEADER.ID, NAVER_config.NAVER_CLIENT_ID);
			httpCon.setRequestProperty(NAVER_config.HEADER.SEC, NAVER_config.NAVER_CLIENT_SEC);

			int resCode = httpCon.getResponseCode();

			InputStreamReader is = null;
			BufferedReader buffer = null;

			if (resCode == 200) {
				is = new InputStreamReader(httpCon.getInputStream());
			} else {
				is = new InputStreamReader(httpCon.getErrorStream());
			}

			buffer = new BufferedReader(is);

			String retString = "";

			while (true) {
				String line = buffer.readLine();

				if (line == null)
					break;

				retString += line;
			}

			return retString;

		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}
