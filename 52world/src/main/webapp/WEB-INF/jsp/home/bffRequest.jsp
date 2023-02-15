<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	background-image: url('/52world/asset/popup_background.png');
	background-repeat: no-repeat;
	background-size : cover;
}
#container {
	margin: 90px 0 0 0;
	padding: 20px 40px;
	line-height:170%; 
}
input.buttons {
	text-align: center;	
	padding: 5px;
	width: 20%;
	margin: 0 10px;
}
.but {
	padding-top:20px;
	text-align: center;	
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="container">
	<form action = "${pageContext.request.contextPath }/bffRequest.do" method="post">

	<!-- ${member.id } : 로그인 정보의 아이디, ${Hompee.hompeeId } : 미니홈피 주인의 아이디 -->
	
	보내는 이 : ${member.name }<br>
	${homeOwner.name }님께 일촌을 신청합니다.<br>
	<hr>
	${member.name }님을 ${homeOwner.name }님의 <input type="text" name="reqName" autofocus style="width:110px;">로<br>
	${homeOwner.name }님을 ${member.name }님의 <input type="text" name="applyName" style="width:110px;">로<br>
	일촌을 맺고싶어요<br>

    	<span style= 'color:grey; font-size:13px;'>예) 상대방을 나의 멋진 후배로 <br>
		나를 상대방의 예쁜 후배로 <br></span>
	<br>

	<textarea name="msg" cols="40" rows="3">일촌 신청해요~</textarea><br>


	상대방이 동의하면 일촌이 맺어집니다.<br>
	<div class='but'>
	<input type="submit" class="buttons" value = "신청">
	<input type="button" class="buttons" onclick="javascript:window.close()" value="취소"></input>
	<input type="hidden" value="${member.id }" name="reqId"> 
 	<input type="hidden" value="${Homepee.homepeeId }" name="applyId"> 
	</div>
	</form>
</div>

</body>
</html> 