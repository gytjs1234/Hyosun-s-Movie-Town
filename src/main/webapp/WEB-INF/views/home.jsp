<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<style>
body {
	background-color: black;
	color: white;
}

div.title {
	height: 100px;
	width: 500px;
	display: table;
}

h1.title {
height: 100px;
text-align: center;
line-height: 100px;
}
</style>
</head>
<body>
	<div class="title">
		<h1 class="title">
			<img src="${rootPath}/static/img/icon.png">
			Movie Town
			<img src="${rootPath}/static/img/icon.png">
		</h1>
	</div>

	<h2>일간순위 ${DAILY_DATE}</h2>
	<c:forEach items="${DAILY}" var="D_Rank">
		<div>
			<h3>
				순위 : ${D_Rank.rank}위 <small>(${D_Rank.rankInten})</small>
			</h3>
			<h4>제목 : ${D_Rank.movieNm}</h4>
			<h4>개봉일 : ${D_Rank.openDt}</h4>
			<h4>
				관객수 : ${D_Rank.audiCnt}명 <small>(${D_Rank.audiChange}%)</small>
			</h4>
			<h4>누적 관객수 : ${D_Rank.audiAcc}명</h4>
		</div>
	</c:forEach>


	<h2>주간순위 ${WEEK_DATE}</h2>
	<c:forEach items="${WEEK}" var="W_Rank">
		<div>
			<h3>
				순위 : ${W_Rank.rank}위 <small>(${W_Rank.rankInten})</small>
			</h3>
			<h4>제목 : ${W_Rank.movieNm}</h4>
			<h4>개봉일 : ${W_Rank.openDt}</h4>
			<h4>
				관객수 : ${W_Rank.audiCnt}명 <small>(${W_Rank.audiChange}%)</small>
			</h4>
			<h4>누적 관객수 : ${W_Rank.audiAcc}명</h4>
		</div>
	</c:forEach>
</body>
</html>