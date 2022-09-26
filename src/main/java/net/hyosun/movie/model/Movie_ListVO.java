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
public class Movie_ListVO {
	private String movieNm; // 영화명(국문)을 출력합니다.
	private String peopleNm; // 영화감독명을 출력합니다.
	private String directors;
}
