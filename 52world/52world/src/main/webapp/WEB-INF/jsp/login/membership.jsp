
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>membership registration</title>


<style>
.middle-container{
box-sizing: border-box;
	margin: 10px auto;
}
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
		<!-- 여기부터 코드 넣으면 됨 -->
		<div style="height: 200px">
		<img class="profile-img" src="${homeOwner.profile }" alt="profile image" />
		<br />
		</div>

		<div class="profile">
			<p style="height: 200px; width:200px; padding-top: 50px">${homeOwner.introduce }</p>

			<!-- c:if문으로 홈피주인인지 체크 -->
			<c:choose>
				<c:when test="${member.id eq Homepee.homepeeId }">
					<a href="#" onclick="editProfile()"><span style="color: #3e9fbe; font-size: 8px">▶ </span>EDIT</a> 
				</c:when>
				<c:when test="${member.id ne Homepee.homepeeId }">
					<a href="#" onclick="bffRequest()">
					<span style="color: #3e9fbe; font-size: 8px">▶ </span>일촌신청</a>
				</c:when>
			</c:choose>
			&nbsp
			<!-- 친구찾기 -->
			<a href="">
			<span style="color: #3e9fbe; font-size: 8px">▶ </span>친구찾기</a>
			&nbsp
			<!-- 로그아웃 -->
			<a href="">
			<span style="color: #3e9fbe; font-size: 8px">▶ </span>로그아웃</a>
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
							<c:forEach var="bff" items="${bffList }">
		  						<option value="${bff.reqId }">${bff.reqId }(${bff.reqName })</option>
							</c:forEach>
						</optgroup>
					</select>
					<input type="submit" value="이동">
				</form>
			</div>

		</div>
		<!-- 여기까지 넣으면 됨 -->
	</div>
</div>

<!-- 가운데화면 시작 -->
<div class="middle-container">

	<!-- 미니홈피 제목 -->
	<div class="middle-title">

		<div class="middle-url">https://52world.com/${homeOwner.id }</div>
	</div>
	<!-- 방명록 화면 만들어야해요-->
	<div class="middle-box">



	<h3>회원가입</h3>
	<form id="joinForm" method="post" action="memberInsert.do">
		<div id="contain">
			<div class="idGroup">
				<h3 class="idTitle">
					<label for="id">아이디</label> <input type="text" name="id" id="id"
						onfocus="this.placeholder = ''"
						onblur="this.placeholder = '15자리 이하의 영문과 숫자로 입력'" />
					<button type="button" id="idCheck" value="unChecked"
						onclick="checkCk()">중복체크</button>
				</h3>
			</div>

			<div class="message">
				<p style="display: none;" id="idmessage1"></p>
				<input type="hidden" id="idmessage2" class="idmessage2"
					tabindex="-2" value="아이디를 확인해주세요">
			</div>

			<div class="passwordGroup">
				<h3 class="passwordTitle">
					<label for="password">비밀번호</label> <input type="password"
						name="password" id="password" placeholder="영문+숫자+특수문자 조합" required />
				</h3>
			</div>

			<div class="passwordGroup">
				<h3 class="passwordTitle">
					<label for="passwordCheck">비밀번호재확인</label> <input type="password"
						name="passwordCheck" id="passwordCheck"
						placeholder="영문+숫자+특수문자 조합" required />
				</h3>
			</div>

			<div class="nameGroup">
				<h3 class="namedTitle">
					<label for="name">이름</label> <input type="text" name="name"
						id="name" required />
				</h3>
			</div>

			<div class="birthGroup">
				<h3 class="birthTitle">
					<label for="birth">생년월일</label>
					<!--        <input type="text" name="year" id="year" placeholder="년(4자)" size=5 required /> -->
					<select id="year" name="year">
						<option value="">년</option>
						<c:forEach var="i" begin="1920" end="2022">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select> <select id="month" name="month">
						<option value="">월</option>
						<c:forEach var="i" begin="1" end="12">
							<c:choose>
								<c:when test="${i lt 10 }">
									<option value="0${i}">0${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <select id="day" name="day">
						<option value="">일</option>
						<c:forEach var="i" begin="1" end="31">
							<c:choose>
								<c:when test="${i lt 10 }">
									<option value="0${i}">0${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</h3>
			</div>

			<div class="genderGroup">
				<h3 class="genderTitle">
					<label for="gender">성별</label> <input type="radio"
						name="userGender" autocomplete="off" value="M" />남성 <input
						type="radio" name="userGender" autocomplete="off" value="F"
						checked />여성
				</h3>
			</div>

			<div class="emailGroup">
				<h3 class="emailTitle">
					<label for="eamil">이메일</label> <input type="text" id="email_Id"
						name="email_Id" title="아이디" placeholder="아이디 입력" /> <span>@</span>
					<input type="text" id="email_domain" name="email_domain"
						title="이메일 주소" placeholder="이메일을 입력하세요" /> <select class="select"
						id="emailList" title="이메일 주소 선택" onchange="return checkEmail()">

						<option value="" id="textEmail" selected>직접 입력하기</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
					</select>
				</h3>
			</div>


			<div class="phoneGroup">
				<h3 class="phoneTitle">
					<label for="phone">전화번호</label> <select class="select"
						id="firstPhone" name="firstPhone" title="앞자리">
						<option value="010" selected="selected">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select> <input type="text" name="secondPhone" id="secondPhone" size="5"
						maxlength='4' onkeyup="nextBlank(4,this.id,'lastPhone');"
						oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
						required /> <input type="text" name="lastPhone" id="lastPhone"
						size="5" maxlength='4'
						oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
						required />
					<button type="button" onclick="phoneCheck()">중복체크</button>
				</h3>
			</div>
		</div>



		<div>
			<input type='button' value='버튼' id='btn1' type="hidden" />
		</div>
		<div>
			<input type='button' value='이 버튼을 클릭하세요' id='btn2'
				onclick='toggleBtn1()' />
		</div>


		<!-- 메인창으로 보내게 해야함 -->
		<input type="button" value="취소하기" onclick="" /> <input type="submit"
			value="가입하기" />
	</form>






</div>
</div>



<!-- 가운데 화면 끝 -->
</body>

</html> 