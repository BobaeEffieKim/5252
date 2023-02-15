<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 구글폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<style>
body {
	background-image: url('/52world/asset/popup_background.png');
	background-repeat: no-repeat;
	background-size : cover;
	font-family: 'Single Day' !important;
	
}
#container {
	margin: 130px 0 0 0;
	padding: 30px 60px;
}
</style>


<title>Insert title here</title>
</head>

<body>
	<div id="container">
	<p>일촌신청이 완료되었습니다.<br>
	${homeOwner.name }님이 수락을 완료하면
	일촌이 맺어집니다.
	
	</p>

	<button onclick="self.close()">확인</button>

	<!-- 일촌신청은 팝업으로 띄우니까 미니홈피로 보낼필요 없겠지?   -->
	</div>
</body>
</html> 