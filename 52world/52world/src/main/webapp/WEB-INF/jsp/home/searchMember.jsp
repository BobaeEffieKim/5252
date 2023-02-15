<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 구글폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap"
	rel="stylesheet">
<style>
body {
	background-image: url('/52world/asset/findFriend.png');
	background-repeat: no-repeat;
	background-size: cover;
	font-family: 'Single Day' !important;
}
#containers {
    margin: 0 auto;
    position: relative;
    top: 30vh;
    text-align: center;
}
input.buttons {
	text-align: center;	
	padding: 5px;
	width: 20%;
	margin: 0 10px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- 미니홈피 메인에서 보이는 [친구찾기] 윈도우 창 -->
		<div id="containers">
			<p style="font-size: 20px">
				찾고자 하는 친구의 이름을 검색하고,<br> 친구의 미니홈피를 둘러보세요 :D
			</p>

			<form id="frm"
				action="${pageContext.request.contextPath }/searchMember.do"
				method="post">
				<p style="font-size: 20px">
					이름 : <input type="text" id="searchBox" name="name" autofocus>
				</p>
				
				<input type="button" class="buttons" value="조회"
					onclick="formCheck()"> 
				<input type="button" class="buttons"
					onclick="javascript:window.close()" value="창닫기"></input>

			</form>
		</div>

	</div>


	<script>
		function formCheck() {
			if (frm.searchBox.value == "") {
				alert("이름을 입력하세요.");
				frm.searchBox.focus();
				return false;
			}
			frm.submit();
		}

	</script>
</body>
</html>
