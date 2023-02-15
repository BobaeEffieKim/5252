
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/loginMain.css?after" />
<title>비번찾기결과창 pwFindSuccess.jsp</title>
<style>

p{
	margin:10px;
}
input{
	height:20px;
}

#findPwRetry {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#findPwRetry:hover{
	background-color: white;
	color: darkgreen;
}
#findPwCancle {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#findPwCancle:hover{
	background-color: white;
	color: darkgreen;
}
#home {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#home:hover{
	background-color: white;
	color: darkgreen;
}
#test_btn2{
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#test_btn2:hover{
	background-color: white;
	color: darkgreen;
}
}
</style>
</head>
<body>
	<div class="background">
		<div align="center" id="wrap">

			<div id="pwCheckResult">
				<form id="findPwForm" name="findPwForm" action="${pageContext.request.contextPath }/memberPwReset.do"
					method="post">
					<div class="pw">

						<c:if test="${member eq null}">
           	검색 결과가 없습니다. 입력정보를 다시 한 번 확인해주세요.
           </c:if>
						<c:if test="${member ne null}">
							<h4>새로운 비밀번호를 설정해주세요.</h4>
							<label for="password">새로운 비밀번호</label> 
						&nbsp;&nbsp;&nbsp;
			<input type="password" size="25" name="password" id="password"
								placeholder="영문+숫자+특수문자 조합" required onchange="limitPwd()" />

							<!-- 숨어있는 패스워드 유효성 메세지 -->
							<div class="message">
								<p style="visibility: hidden" id="pwdmessage1"></p>
								<input type="hidden" id="hidden"
									class="pwdmessage2" tabindex="-2" value="패스워드를 확인해주세요" />
							</div>

							<!-- 비밀번호확인 -->
							<label for="passwordCheck">비밀번호 재확인</label> &nbsp;&nbsp;&nbsp;<input
								type="password" autocomplete="new-password" name="passwordCheck"
								id="passwordCheck" size="25" placeholder="영문+숫자+특수문자 조합"
								required onchange="CheckPwd()" />
							<!-- 숨어있는 패스워드 유효성 메세지 -->
							<div class="message">
								<p style="visibility: hidden" id="pwdcheck1"></p>
								<input type="hidden" id="hidden"
									class="pwdcheck2" tabindex="-2" value="패스워드를 확인해주세요" />
							</div>

						</c:if>
					</div>

					<br>

					<div>
						<c:if test="${member eq null}">
							
							<button id="findPwCancle" type="button" value="홈으로"
								onclick="location.href='memberLoginForm.do'">홈으로</button>
							<button id="findPwRetry" type="button" value="다시시도"
								onclick="location.href='memberPwCheckForm.do'">다시시도</button>	
						</c:if>
						<c:if test="${member ne null}">
							<button id="home" type="button" value="홈으로"
								onclick="location.href='memberLoginForm.do'">홈으로</button>
							<input id="test_btn2"
							type="submit" value="비밀번호등록" onclick="registerPw()" />
						</c:if>
					</div>
					<br>


				</form>
			</div>
		</div>

		<script type="text/javascript">
			/* 비밀번호 유효성 검사 */
			function limitPwd() {
				let pwd = document.getElementById("password").value;
				var pattern1 = /[0-9]/; // 숫자
				var pattern2 = /[a-zA-Z]/; // 문자
				var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
				if (!(pattern1.test(pwd) & pattern2.test(pwd) & pattern3
						.test(pwd))) {
					document.getElementById("pwdmessage1").style.visibility = "visible";
					document.getElementById("pwdmessage1").innerText = "영문+숫자+특수문자를 입력해주세요";
					document.getElementById("pwdmessage1").style.color = "red";
					document.getElementById("password").value = "";
					document.getElementById("password").focus();
					return false;
				} else {
					document.getElementById("pwdmessage1").style.visibility = "visible";
					document.getElementById("pwdmessage1").innerText = "사용할수 있는 비밀번호입니다";
					document.getElementById("pwdmessage1").style.color = "green";
					return true;
				}
			}
			//비밀번호 재확인
			function CheckPwd() {
				let pwd = document.getElementById("password").value;
				let pwdCk = document.getElementById("passwordCheck").value;
				if (pwd === pwdCk) {
					document.getElementById("pwdcheck1").style.visibility = "visible";
					document.getElementById("pwdcheck1").innerText = "비밀번호가 일치합니다";
					document.getElementById("pwdcheck1").style.color = "green";
					return true;
				} else {
					document.getElementById("pwdcheck1").style.visibility = "visible";
					document.getElementById("pwdcheck1").innerText = "비밀번호가 불일치합니다";
					document.getElementById("pwdcheck1").style.color = "red";
					document.getElementById("passwordCheck").value = "";
					document.getElementById("passwordCheck").focus();
					return false;
				}
			}
			
			//비밀번호등록
			function registerPw(){
				
				let cf = confirm('비밀번호를 변경하시겠습니까?')
				if(cf){
					alert("변경이 완료되었습니다");
					document.findPwForm.submit();
					
				} else {
					alert("비밀번호 변경을 취소하였습니다")
				}
				
			}
			
		</script>
	</div>
</body>

</html>
