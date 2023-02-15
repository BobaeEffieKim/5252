<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/loginMain.css?after" />
<title>login/loginForm</title> 
</head>
<body>
	<div class="background">
	<div align="center" id="wrap">

		<div>
			<form id="frm" name="frm" action="memberLogin.do" method="post">
				<div id="idBox">
					<input type="text" id="id" name="id" placeholder="아이디">
				</div>
				<br />
				<div id="pwBox">
					<input type="password" id="password" name="password"
						placeholder="비밀번호">
				</div>
				<br />
				<div>
					<button id="loginBtn" type="button" onclick="formCheck()">로그인</button>
					&nbsp;&nbsp;&nbsp;
				</div>
				<br>
				<div>
					<a href="memberIdCheckForm.do">id찾기</a>
					<a href="memberPwCheckForm.do">pw찾기</a>  
					<a href="javascript:clauseAgreement()">회원가입</a>
				</div>
			</form>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		// 배경음악
		window.parent.postMessage('{"hId": " ", "src": " "}', "*");
	
		function formCheck() {
			if (frm.id.value == "") {
				alert("아이디를 입력하세요.");
				frm.id.focus();
				return false;
			}

			if (frm.password.value == "") {
				alert("패스워드를 입력하세요.");
				frm.password.focus();
				return false;
			}
			frm.submit();
		}
		
		// 회원가입 약관동의 팝업창 열기
		function clauseAgreement() {
			
			var popHeight = 700;  // 띄울 팝업창 높이   
			var popWidth = 550;// 띄울 팝업창 너비
			var winHeight = document.body.clientHeight;	  // 현재창의 높이
			var winWidth = document.body.clientWidth;	  // 현재창의 너비
			var winX = window.screenLeft;// 현재창의 x좌표
			var winY = window.screenTop;// 현재창의 y좌표

			var popX = winX + (winWidth - popWidth)/2;
			var popY = winY + (winHeight - popHeight)/2;

			window.open("${pageContext.request.contextPath }/clauseAgreementForm.do?", "약관동의",  "top="+popY+", left="+popX+",width="+popWidth+",height="+popHeight+", scrollbars=no, toolbars=no, menubar=no");
	
		
		}
	</script>
</body>
</html>
