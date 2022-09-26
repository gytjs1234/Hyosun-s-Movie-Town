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
public class NaverVO {
	private String title; // 영화 제목. 제목에서 검색어와 일치하는 부분은 <b> 태그로 감싸져 있습니다.
	private String image; // 섬네일 이미지의 URL
	private String director; // 감독
}
