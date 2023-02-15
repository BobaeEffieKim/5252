<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", -1);
response.setHeader("Cache-Control", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>마이페이지</title>

<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">
<style>
#wrap {
	margin: 20px auto;
	height: 90%;
	width: 650px;
	background-color: white;
	border-radius: 20px;
	font-family: 'Gowun Dodum', sans-serif;
	font-size: large;
}

#mypage {
	margin: 40px auto;
}

input {
	font-size: medium;
	font-family: 'Gowun Dodum', sans-serif;
	border-style: none;
	border-bottom: solid 1px #cacaca;
	border-collapse: collapse;
}
.passwordTitle2{
	width:350px;
	display:inline-block;
}
.passwordGroup2{
	float:left;
}

.message {
display:inline-block;
	font-size: medium;
	font-weight: bold;
	margin-left: 20px;
	
	font-family: 'Gowun Dodum', sans-serif;
	height: 20px;
	width: 200px;
}

p {
	margin: 3px;
	padding: 0;
}

lable {
	margin-right: 30px;
}

.idTitle {
	margin: 15px 0;
}

.passwordTitle {
	margin: 15px 0;
}

.namedTitle {
	margin: 15px 0;
}

.birthTitle {
	margin: 15px 0;
}

.genderTitle {
	margin: 15px 0;
}

.emailTitle {
	margin: 15px 0;
}

.phoneTitle {
	margin: 15px 0;
}

.homepeeName {
	margin: 15px 0;
}

.backColor {
	margin: 15px 0;
}

.tabColor {
	margin: 15px 0;
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
				<img class="profile-img" src="${homeOwner.profile }"
					alt="profile image" /> <br />
			</div>

			<div class="profile">
				<p style="height: 200px; width: 200px; padding-top: 50px">
					<span style="white-space: pre;">${homeOwner.introduce }</span>
				</p>

				<!-- c:if문으로 홈피주인인지 체크 -->
				<c:choose>
					<c:when test="${member.id eq Homepee.homepeeId }">
						<a href="javascript:editProfile()"><span
							style="color: #3e9fbe; font-size: 8px">▶ </span>EDIT</a>&nbsp
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
			<!-- 여기까지 넣으면 됨 -->
		</div>
	</div>

	<!-- 가운데화면 시작 -->
	<div class="middle-container">
		<!-- 미니홈피 제목 여긴 고정 -->
		<div class="middle-title">
			<div class="middle-desc">${Homepee.name }</div>
			<div class="middle-url">https://52world.com/${homeOwner.id }</div>
		</div>
		<!-- 메인페이지 가운데 흰색화면 -->
		<div class="middle-box">
			<!-- 여기부터 코드 넣으면 됨 -->

			<div align="left" id="wrap">
				<form id="mypage" method="post" action="mypage.do">
					<div id="mypage-container">


						<div class="idGroup">
							<div class="idTitle">
								<label for="id">아이디</label> &nbsp;&nbsp;&nbsp;<input type="text"
									name="id" id="id" value="${member.id }" readonly />
							</div>
						</div>


						<div class="passwordGroup">
							<div class="passwordTitle">
								<label for="password">비밀번호</label>&nbsp;&nbsp;&nbsp; <input
									type="password" size="25" name="password" id="password"
									placeholder="영문+숫자+특수문자 조합" value="${member.password }"
									readonly />
							</div>
						</div>

						<div class="passwordGroup2">
							<div class="passwordTitle2">
								<label for="passwordCheck">비밀번호확인</label> &nbsp;&nbsp;&nbsp;<input
									type="password" name="passwordCheck" id="passwordCheck"
									size="25" autocomplete="new-password"
									placeholder="영문+숫자+특수문자 조합" required onchange="CheckPwd()" />
							</div>
						</div>

						<!-- 숨어있는 패스워드 유효성 메세지 -->
						<div class="message">
							<p style="visibility: hidden" id="pwdcheck1"></p>
							<input type="hidden" id="hidden"
								class="pwdcheck2" tabindex="-2" value="패스워드를 확인해주세요" />
						</div>

						<div class="nameGroup" style="clear: both">
							<div class="namedTitle">
								<label for="name">이름</label> &nbsp;&nbsp;&nbsp;<input
									type="text" name="name" size="15" readonly id="name" required
									value="${member.name }" />
							</div>
						</div>

						<div class="birthGroup" style="clear: both">
							<div class="birthTitle">
								<label for="birth">생년월일</label>&nbsp;&nbsp;&nbsp; <select
									id="year" name="year" required>
									<option value="">년</option>
									<c:forEach var="i" begin="1920" end="2022">
										<c:choose>
											<c:when test="${i ==  fn:split(member.birth, '-' )[0]}">
												<option disabled="disabled" value="${i}" selected="selected">${i}</option>
											</c:when>
											<c:otherwise>
												<option disabled="disabled" value="${i}">${i}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <select id="month" name="month" required>
									<option value="">월</option>
									<c:forEach var="i" begin="1" end="12">
										<c:choose>
											<c:when test="${i lt 10 }">
												<c:choose>
													<c:when test="${i ==  fn:split(member.birth, '-' )[1]}">
														<option disabled="disabled" value="0${i}"
															selected="selected">0${i}</option>
													</c:when>
													<c:otherwise>
														<option disabled="disabled" value="0${i}">0${i}</option>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${i ==  fn:split(member.birth, '-' )[1]}">
														<option disabled="disabled" value="${i}"
															selected="selected">${i}</option>
													</c:when>
													<c:otherwise>
														<option disabled="disabled" value="${i}">${i}</option>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <select id="day" name="day" required>
									<option value="">일</option>
									<c:forEach var="i" begin="1" end="31">
										<c:choose>
											<c:when test="${i lt 10 }">
												<c:choose>
													<c:when
														test="${i ==  fn:substring(fn:split(member.birth, '-' )[2],0,2 )}">
														<option disabled="disabled" value="0${i}"
															selected="selected">0${i}</option>
													</c:when>
													<c:otherwise>
														<option disabled="disabled" value="0${i}">0${i}</option>
													</c:otherwise>
												</c:choose>
											</c:when>

											<c:otherwise>
												<c:choose>
													<c:when
														test="${i ==  fn:substring(fn:split(member.birth, '-' )[2],0,2 )}">
														<option disabled="disabled" value="${i}"
															selected="selected">${i}</option>
													</c:when>
													<c:otherwise>
														<option disabled="disabled" value="${i}">${i}</option>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="genderGroup" style="clear: both">
							<div class="genderTitle">
								<label for="gender">성별</label>&nbsp;&nbsp;&nbsp;
								<c:choose>
									<c:when test="${member.gender eq 'F' }">
										<input type="radio" name="userGender" autocomplete="off"
											value="M" />남성 &nbsp;&nbsp;
							 <input type="radio" name="userGender" autocomplete="off"
											value="F" checked />여성
						</c:when>
									<c:otherwise>
										<input type="radio" name="userGender" autocomplete="off"
											value="M" checked />남성 &nbsp;&nbsp;
							<input type="radio" name="userGender" autocomplete="off"
											value="F" />여성
						</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="emailGroup" style="clear: both">
							<div class="emailTitle">
								<label for="eamil">이메일</label> &nbsp;&nbsp;&nbsp; <input
									type="text" id="email_Id" name="email_Id" title="아이디"
									placeholder="아이디 입력" readonly
									value="${fn:split(member.email,'@')[0] }" /> <span>@</span> <input
									type="text" id="email_domain" name="email_domain"
									title="이메일 주소" placeholder="이메일을 입력하세요" readonly
									value="${fn:split(member.email,'@')[1] }" /> <select
									class="select" id="emailList" title="이메일 주소 선택"
									onchange="return checkEmail()">
									<option value="" id="textEmail" selected disabled>직접
										입력하기</option>
									<option value="naver.com">naver.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="nate.com">nate.com</option>
								</select>
							</div>
						</div>


						<div class="phoneGroup" style="clear: both">
							<div class="phoneTitle">
								<label for="phone">전화번호</label> &nbsp;&nbsp;&nbsp; <select
									class="select" id="firstPhone" name="firstPhone" title="앞자리">
									<option value="010" selected="selected">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
								</select> <input type="text" name="secondPhone" id="secondPhone" size="5"
									readonly maxlength='4'
									onkeyup="nextBlank(4,this.id,'lastPhone');"
									value="${fn:split(member.phone,'-')[1] }"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									required /> <input type="text" name="lastPhone" id="lastPhone"
									readonly size="5" maxlength='4'
									value="${fn:split(member.phone,'-')[2] }"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									required />
								<!-- <button type="button" onclick="phoneCheck()">중복체크</button> -->
							</div>
						</div>
					</div>
					<div class="homepeeName">
						<label>홈피제목&nbsp;&nbsp;&nbsp; <input type="text"
							name="homepeeName" id="homepeeName" maxlength="20"
							value="${Homepee.name }" required />
						</label>
					</div>
					<div class="backColor">
						<label>배경색변경&nbsp;&nbsp;&nbsp; <input type="color"
							name="backColor" id="backColor"
							value="${Homepee.backgroundColor }" />
						</label>
					</div>
					<div class="tabColor">
						<label>탭 색상변경&nbsp;&nbsp;&nbsp; <input type="color"
							name="tabColor" id="tabColor" value="${Homepee.tabColor }" />
						</label>
					</div>



					<br>
					<br>
					<!-- 메인창으로 보내게 해야함 -->
					<div align="center">
						<input type="submit" value="수정" />
					</div>
				</form>



				<!-- 여기까지 넣으면 됨 -->
			</div>
		</div>
	</div>
	<!-- 가운데 화면 끝 -->

	<script>
<!-- 비밀번호 확인하기 -->
//비밀번호 재확인
function CheckPwd() {
  let pwd = document.getElementById("password").value;
  let pwdCk = document.getElementById("passwordCheck").value;
  if (pwd === pwdCk) {
	document.getElementById("pwdcheck1").style = "float : left";
    document.getElementById("pwdcheck1").style.visibility = "visible";
    document.getElementById("pwdcheck1").innerText ="비밀번호가 일치합니다";
    document.getElementById("pwdcheck1").style.color = "green";
   
    return true;
  } else {
    document.getElementById("pwdcheck1").style.visibility = "visible";
    document.getElementById("pwdcheck1").innerText ="비밀번호가 불일치합니다";
    document.getElementById("pwdcheck1").style.color = "red";
    document.getElementById("passwordCheck").value="";
    document.getElementById("passwordCheck").focus();
    return false;
  }
}

<!-- 생년 월일 고정시키기-->
$('input[name=userGender]').click(function (event){
    event.preventDefault();
    event.stopPropagation();
});


<!--성별 고정시키기 -->
$('input[name=userGender]').click(function (event){
    event.preventDefault();
    event.stopPropagation();
});

<!--전화번호 앞자리 고정시키기 -->
$('input[name=firstPhone]').click(function (event){
    event.preventDefault();
    event.stopPropagation();
});
</script>
</body>
</html>