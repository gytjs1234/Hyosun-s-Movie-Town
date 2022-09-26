package net.hyosun.movie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.hyosun.movie.service.Daily_rankingService;
import net.hyosun.movie.service.NaverService;
import net.hyosun.movie.service.Week_rankingService;

@Controller
public class HomeController {

	@Autowired
	private Daily_rankingService daily;

	@Autowired
	private Week_rankingService week;
	
	@Autowired
	private NaverService naver;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws IOException, ParseException {
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");

		Date today = new Date(); // 오늘 날짜
		String st_today = dateForm.format(today); // 오늘 날짜 String형 변환
		Date setDate = dateForm.parse(st_today); // 기준 날짜 설정

		
		Calendar daily_cal = new GregorianCalendar(Locale.KOREA); // 한국 날짜 기준으로 설정
		daily_cal.setTime(setDate); // 기준 날짜 설정
		daily_cal.add(Calendar.DATE, -1); // 하루 전으로 설정
		String daily_date = dateForm.format(daily_cal.getTime()); // 하루 전 날짜 String 타입 지정

		
		Calendar week_cal = new GregorianCalendar(Locale.KOREA);
		week_cal.setTime(setDate);
		week_cal.add(Calendar.DATE, -7); // 일주일 전으로 설정
		String week_date = dateForm.format(week_cal.getTime());

		model.addAttribute("DAILY", daily.getDaily_rank());
		model.addAttribute("DAILY_DATE", daily_date);

		model.addAttribute("WEEK", week.getWeek_rank());
		model.addAttribute("WEEK_DATE", week_date);

		return "home";
	}
}
