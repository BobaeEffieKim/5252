<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	background-image: url('/52world/asset/popup_background.png');
	background-repeat: no-repeat;
	background-size: cover;
}

#container {
	margin: 80px 0 0 0;
	padding: 30px 50px; table { width : 100%;
	border-top: 1px solid #444444;
	border-collapse: collapse;
}

table {
	border-top: 1px solid #444444;
	border-collapse: collapse;
}


</style>
<title>일촌신청 페이지</title>
</head>
<body>
	<div id="container">
		<br>
		<p style="font-family: 'Single Day' !important; font-size: 20px">일촌신청이
			도착했습니다 📩</p>

		<hr>

		<c:if test="${empty list }">
			<script>
				alert("일촌 신청 내역이 없습니다!");
				window.close();
			</script>
		</c:if>

		<c:if test="${!empty list }">
			<table
				style="margin-left: auto; margin-right: auto; width: 100%; height: 15px; text-align: center; font-size:13px">
				<thead>
					<tr>
						<th>NO</th>
						<th>신청인 ID</th>
						<th>일촌신청 메세지</th>
						<th>확인하기</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${list }">
						<form name="bffform"
							action="${pageContext.request.contextPath }/bffAnswerForm.do"
							method="post">

							<input type="hidden" name="id" value="${vo.id }"> <input
								type="hidden" name="reqId" value="${vo.reqId }"> <input
								type="hidden" name="reqName" value="${vo.reqName }"> <input
								type="hidden" name="applyId" value="${vo.applyId }"> <input
								type="hidden" name="applyName" value="${vo.applyName }">
							<input type="hidden" name="msg" value="${vo.msg }">
							<input type="hidden" name="realName" value=${vo.realName }>
							<tr>
								<td>${vo.id }</td>
								<td>${vo.reqId }</td>
								<td>${vo.msg }</td>
								<td><input type='submit' value='확인'></td>
							</tr>
							</form>
					</c:forEach>
				</tbody>
			</table>
			
		</c:if>
	</div>

</body>
</html>