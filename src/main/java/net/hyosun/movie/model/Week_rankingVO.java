package net.hyosun.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Week_rankingVO {
	private String rank; // 해당일자의 박스오피스 순위를 출력합니다.
	private String rankInten; // 전일대비 순위의 증감분을 출력합니다.
	private String movieNm; // 영화명(국문)을 출력합니다.
	private String openDt; // 영화의 개봉일을 출력합니다.
	private String audiCnt; // 해당일의 관객수를 출력합니다.
	private String audiAcc; // 누적관객수를 출력합니다.
	private String audiChange; // 전일 대비 관객수 증감 비율을 출력합니다.
	private String img_url; // 네이버 검색 api 에서 추출한 img url
}
