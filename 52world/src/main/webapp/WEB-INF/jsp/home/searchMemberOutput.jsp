<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<style>
body {
	background-image: url('/52world/asset/findFriend.png');
	background-repeat: no-repeat;
	background-size: cover;
	font-family: 'Single Day' !important;
}
#container {
    margin: 0 auto;
    position: relative;
    top: 30vh;

   
}
#table {
	text-align: center;
	width: 400px;
	margin-left: auto;
	margin-right: auto;
	overflow-y: scroll;
	text-decoration: none;
}
input.buttons {
	text-align: center;	
	padding: 5px;
	width: 20%;
	margin: 0 10px;
}
#but {
	text-align: center;	
	padding-top:5%;
}
#noResult {
    margin: 20px 0 0 0;
    padding: 60px 30px;
    top: 20vh;
    text-align: center;
    text-decoration: none;
}

</style>
<title>친구찾기 결과</title>
</head>
<body>

	<div id="container">
		<!-- 미니홈피 메인에서 보이는 [친구찾기] 검색 결과 창 -->
		<c:if test="${!empty friend }">
			<table id="table">
				<thead>
					<tr>
						<th>이름</th>
						<th>아이디</th>
						<th>생년월일</th>
						<th>미니홈피</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${friend }">
						<tr>
							<td>${vo.name }</td>
							<td>${vo.id }</td>
							<td>${vo.birth }</td>
							<td><a href="javascript:bffminihomepee('${vo.id}')"
								style="text-decoration: none; color: black;">[이동]</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="but">
				<input type="button" class="buttons"
					onclick="javascript:window.close()" value="창닫기"></input>
			</div>
		</c:if>
		<div id="noResult">
			<c:if test="${empty friend }">
				<p style="font-size: 20px">존재하지 않는 이름(친구)입니다.</p>
				<a href="${pageContext.request.contextPath }/searchMemberForm.do">뒤로가기</a>
			</c:if>
		</div>

	</div>
	<script>
		function bffminihomepee(id){
			let userid = id;
			console.log(userid);
			window.opener.location.href="minihomepee.do?id="+userid;
			window.open('','_self').close();
		}

	

	</script>

</body>