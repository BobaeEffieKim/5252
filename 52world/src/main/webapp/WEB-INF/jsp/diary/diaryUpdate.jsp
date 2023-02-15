<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.dev.common.calendar"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>guestBook</title>
<style>
.mybox {
	box-sizing: border-box;
}
.calendarinner{
	
	background-color: rgb(52, 156, 177);
	box-sizing: border-box;
	
	padding: 5px 5px;
	border-radius:5px;
 
	
}
table {
	margin: 8px auto;
	background-color: white;
	height: 150px;
	border-collapse:separate; 
	border-radius:5px;
	padding:10px;
	
}
#calTr {
	background-color: rgb(137, 137, 137);
}
==============================
#insertButton {
	display:inline-block;
	text-align : right;
	margin:20px 50px 25px 20px;
}
.diary_post_box {
	width: 700px;
	margin: 0 auto;
	overflow: hidden;
}
h3 {
    display: block;
    font-size: 1.17em;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 50px;
    margin-inline-end: 0px;
    font-weight: bold;
    float:left;
}
.diary_post {
	margin: 0px;
	border: 1px solid rgb(139, 183, 197);
	border-radius: 2px;
	margin: 0px 0px 30px 0px;
	padding-bottom: 80px;
}
.diary_header {
	color: rgb(62, 159, 190);
	font-weight: bolder;
	border-radius: 2px;
	margin: 20px;
	padding: 20px 0px 10px 20px;
	font-size: 18px;
	font-family: 'Do Hyeon', sans-serif;
}
.diary_title {
	margin: 20px;
	text-align: center;
	font-weight: bolder;
	color: rgb(80, 80, 80);
}
.diary_contentbox {
	margin: 20px;
	text-align: center;
	color: rgb(104, 104, 104);
}
#pagination {
	text-align: center;
}
</style>

<style type="text/css">
/* 기본스타일  */	
	tr{height: 0px;}
	td{width: 0px; text-align: center; font-size: 10pt; font-family: 'Dongle', sans-serif;}
	
/* 타이틀 스타일 */
	th#title {font-size: 10pt; font-weight: bold; color: #000000; font-family: 'Dongle', sans-serif; }
/* 요일 스타일 */
	td.sunday{ text-align: center; font-weight: bold; color: rgb(255, 153, 0); font-family: 'Dongle', sans-serif; }
	td.saturday{ text-align: center; font-weight: bold; color: grey; font-family: 'Dongle', sans-serif; }
	td.etcday{ text-align: center; font-weight: bold; color: grey; font-family: 'Dongle', sans-serif; }
/* 날짜 스타일 */
	td.sun{ text-align: center; font-size: 10pt; color: rgb(255, 153, 0); font-family: 'Dongle', sans-serif; vertical-align: top;}
	td.sat{ text-align: center; font-size: 10pt; color: grey; font-family: 'Dongle', sans-serif; vertical-align: top;}
	td.etc{ text-align: center; font-size: 10pt; color: grey; font-family: 'Dongle', sans-serif; vertical-align: top;}
	
	td.redbefore{ text-align: center; font-size: 0pt; color: rgb(255, 255, 255); font-family: 'Dongle', sans-serif; vertical-align: top;}
	td.before{ text-align: center; font-size: 0pt; color: rgb(255, 255, 255); font-family: 'Dongle', sans-serif; vertical-align: top;}
	
</style>
</head>
<body>
<!-- 왼쪽 화면 시작 -->
<div class="left-container">
	<!-- 방문자수 이건 고정 -->
	<div class="left-visits">
		<p>
			<fmt:parseNumber var="today" integerOnly="true"
				value="${Homepee.visitToday/1000000 }" />
			TODAY <span style="color: red">${today }</span> | TOTAL
			${Homepee.visitTotal }
		</p>
	</div>

	<!-- 왼쪽 흰색화면 -->
	<div class="left-box">
	<br>
	<span style="color : rgb(13, 141, 167)">Calendar</span>
	<hr style="background-color: rgb(13, 141, 167); height: 3px;">
		<!-- 여기부터 코드 넣으면 됨★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ -->
		<%
// 컴퓨터 시스템의 년, 월 받아오기
	Date date = new Date();
	int year = date.getYear() +1900;
	int month = date.getMonth() +1;
	//	오류사항 걸러주기	
	try{
		year = Integer.parseInt(request.getParameter("year"));
		month = Integer.parseInt(request.getParameter("month"));
		
		if(month>=13){
			year++;
			month =1;
		}else if(month <=0){
			year--;
			month =12;
		}
	}catch(Exception e){
		
	}
%>
<!-- 달력 만들기 -->
<div class="calendarBox" style="width:200px">
	<div class="calendarinner">
<table class="calTable">
	<div id="calTr">
	<tr>
<!-- 이전달 버튼 만들기 -->
		<th>
		<%-- <a href ="?year=<%=year%>&month=<%month-1%>">이전 달</a> --%>
		<input type="button" value="<" style="width:20px;height:20px;border:0.5px;border-style: none;border-radius: 2px;" onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
		</th>

<!-- 제목 만들기 -->
		<th id = "title" colspan = "5">
		<%=year%>년  <%=month%>월
		</th>

<!-- 다음달 버튼 만들기 -->
		<th>
		<%-- <a href ="?year=<%=year%>&month=<%month+1%>">다음 달</a> --%>
		<input type="button" value=">" style="width:20px;height:20px;border:0.5px;border-style: none;border-radius: 2px; margin: auto;" onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">

		</th>
	</tr>	
	</div>
<!-- 요일 표시칸 만들어주기(단, 토,일요일은 색을 다르게 하기위해 구분해주기) -->
	<tr>
		<td class = "sunday">S</td>
		<td class = "etcday">M</td>
		<td class = "etcday">T</td>
		<td class = "etcday">W</td>
		<td class = "etcday">T</td>
		<td class = "etcday">F</td>
		<td class = "saturday">S</td>
	</tr>

<!-- 날짜 집어 넣기 -->
	<tr>
	<%
//	1일의 요일을 계산한다(자주 쓰이기 때문에 변수로 선언해두기)
		int first = calendar.weekDay(year, month, 1);
	
//	1일이 출력될 위치 전에 전달의 마지막 날짜들을 넣어주기위해 전 달날짜의 시작일을 계산한다.
		int start = 0 ;
		start = month ==1? calendar.lastDay(year-1, 12)- first : calendar.lastDay(year, month-1)- first;
//	1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하여 전달의날짜를 출력한다.
		for(int i= 1; i<= first; i++){
			if(i==1){
/* 일요일(빨간색)과 다른날들의 색을 구별주기  */
				out.println("<td class = 'redbefore'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
			}else{
				out.println("<td class = 'before'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
			}
		}
/* 1일부터 달력을 출력한 달의 마지막 날짜까지 반복하며 날짜를 출력 */
		for(int i = 1; i <= calendar.lastDay(year, month); i++){
			/* 요일별로 색깔 다르게 해주기위해 td에 class 태그걸어주기 */
			switch(calendar.weekDay(year, month, i)){
				case 0 :
					out.println("<td class ='sun'>" +i +"</td>");
					break;
				case 6 :
					out.println("<td class ='sat'>" +i +"</td>");
					break;
				default :
					out.println("<td class ='etc'>" +i +"</td>");
					break;
			}
			
/* 출력한 날짜(i)가 토요일이고 그달의 마지막 날짜이면 줄을 바꿔주기 */
			if(calendar.weekDay(year, month, i) == 6 && i != calendar.lastDay(year, month)){
				out.println("</tr><tr>");			
			}
		}
		if(calendar.weekDay(year, month, calendar.lastDay(year, month)) !=6){
			for(int i = calendar.weekDay(year, month, calendar.lastDay(year, month))+1; i < 7; i++){
				out.println("<td></td>");	
			}
		}
	%>
	</tr>
</table>
</div>
</div>
<div class="profile">
			<br /><br /><br /><br /><br /><br /><br /><br />
			<!-- c:if문으로 홈피주인인지 체크 -->
			<c:choose>
				<c:when test="${member.id eq Homepee.homepeeId }">
					
					<!-- 일촌수락 -->
					<a href="javascript:bffRequestList()"> <span
						style="color: #3e9fbe; font-size: 8px">▶ </span>일촌수락
					</a>


				</c:when>
				<c:when test="${member.id ne Homepee.homepeeId }">

					<c:if test="${empty isFriend }">
						<a href="javascript:bffRequest()"> <span
							style="color: #3e9fbe; font-size: 8px">▶ </span>일촌신청
						</a>&nbsp
					</c:if>
				</c:when>
			</c:choose>

			<!-- 친구찾기 -->
			<a href="#" onclick="searchMemberForm()"> <span
				style="color: #3e9fbe; font-size: 8px">▶ </span>친구찾기
			</a>&nbsp

			<hr />



			<div class="profile-name">${homeOwner.name }</div>
			<br />
			<!-- 일촌목록 -->
			<a><span style="color: #3e9fbe; font-size: 8px">▶ </span>파도타기</a>
			<hr>
			<div class="friendsList">
				<form action="minihomepee.do" method="post">
					<select name="id" style="width: 150px">
						<optgroup label="일촌목록">
							<option value="${member.id }">내 홈피로</option>
							<c:forEach var="bff" items="${bffList }">
								<option value="${bff.reqId }">${bff.reqId }(${bff.reqName })</option>
							</c:forEach>
						</optgroup>
					</select> <input type="submit" value="이동">
				</form>
			</div>



		</div>




		<!-- 여기까지 넣으면 됨 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★-->
	</div>
</div>

<!-- 가운데화면 시작 -->
<div class="middle-container">


	<!-- 미니홈피 제목 -->
	<div class="middle-title">
		<div class="middle-desc">${Homepee.name }</div>
		<div class="middle-url">https://52world.com/${homeOwner.id }</div>
	</div>
	<!-- 다이어리 화면 만들어야해요-->
	<div class="middle-box">
		<div id="mybox">
			<div id ="diaryMenu"><h3>다이어리</h3></div>
			<div id ="insertButton" style="float:right; margin:20px 50px 25px 20px;">

			<a href="${pageContext.request.contextPath }/writeDiaryForm.do">
				<input type="button" value="새 글 등록"></a> </div>
			
			<div class="diary_post_box">

					<div class="diary_post">

						<div class="diary_header"></div>
						<div class="diary_contentbox">

						<form action="${pageContext.request.contextPath }/diaryUpdate.do"
							method="post" class="insert-box">

							<p class="left_tab" style="text-align:left; margin-left:20px">제목 ▽<span style="color:red">(수정할 제목을 입력하세요)</span></p>
							<input class="right_title" type="text" name="title"
								style="width: 610px; height: 30px" value="${title }"><br>
							<br>

							<p class="left_tab" style="text-align:left; margin-left:20px">내용 ▽<span style="color:red">(수정할 내용을 입력하세요)</span></p>
							<textarea class="right_content" name="content" cols="30"
								rows="10" style="width: 610px;">${content}</textarea>

							<input type="hidden" name="postId" value="${postId }">

							<input type="submit" value="수정"
								style="padding: 10px; float: right; margin: 20px; width: 100px">

						</form>

					</div>
					</div>

			</div>


		</div>
	</div>
</div>
</body>
<!-- 가운데 화면 끝 -->
</html> 