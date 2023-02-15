
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/loginMain.css?after" />
<title>비번찾기 memberPwFind.jsp</title>
<style>

.idTitle{
	margin-bottom: 30px;
}
p{
	margin:5px;
}
h5{
	margin: 5px auto;
}
.message{
	height:20px;
}
#contain input{
	height:20px;
}
select{
	font-size: 16px;
	
	color: rgb(135, 134, 134);
	border-radius: 3px;
	padding: 3px;
}

#buttonForm {
	text-align: center;
}

#findIdBtn {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}

#findIdCancle {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#findPwBtn {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#findPwBtn:hover{
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
</style>
</head>
<body>
	<div class="background">
		<div align="center" id="wrap">

			<div id="square">
				<form id="findForm" name="findForm" action="memberPwCheck.do"
					method="post">
					<div align="center" id="contain">

						<div class="nameGroup">
							<h5 class="namedTitle">
								<label for="name">이름</label> &nbsp;&nbsp;&nbsp;<input
									type="text" name="name" size="15" required id="name" required
									onchange="CheckName()" >
							</h5>
						</div>

						<!-- 숨어있는 이름 유효성 메세지 -->
						<div class="message">
							<p style="visibility: hidden" id="nameheck1"></p>
							<input type="hidden" id="hidden"
								class="nameheck2" tabindex="-2" value="이름을 확인해주세요" />
						</div>


						<div class="idGroup">
							<h5 class="idTitle">
								<!-- 아이디 -->
								<label for="id">아이디</label> &nbsp;&nbsp;&nbsp;<input type="text"
									name="id" id="id" required placeholder="4~12자리 이하의 영문과 숫자"
									size="25"  >
							</h5>
						</div>

						<div class="phoneGroup">
							<h5 class="phoneTitle">
								<label for="phone">전화번호</label> &nbsp;&nbsp;&nbsp;<select
									class="select" id="firstPhone" name="firstPhone" title="앞자리">
									<option value="010" selected="selected">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
								</select>&nbsp;-&nbsp;<input type="text" name="secondPhone"
									id="secondPhone" size="5" required maxlength="4"
									onkeyup="nextBlank(4,this.id,'lastPhone');"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									required />&nbsp;-&nbsp;<input type="text" name="lastPhone"
									required id="lastPhone" size="5" maxlength="4"
									oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
									required  >
							</h5>

							
						</div>
						<br>
					
						
							<button id="findPwBtn" type="submit">비밀번호 찾기</button>
							<button id="findPwCancle" type="button" value="뒤로가기"
										onclick="location.href='memberLoginForm.do'">뒤로가기</button>
							&nbsp;&nbsp;&nbsp;
						
				
						<br>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
   
   //이름 유효성 검사
   function CheckName() {
     let name = document.getElementById("name").value;
     var pattern1 = /[0-9]/; // 숫자
     var pattern2 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
		var pattern3 = /^[가-힝a-zA-Z]{2,}$/;
     
     let notname = (pattern1.test(name) || pattern2.test(name));
	
     if (!pattern3.test(name) || notname){
     	  document.getElementById("nameheck1").style.visibility = "visible";
           document.getElementById("nameheck1").innerText ="이름을 확인해주세요";
           document.getElementById("nameheck1").style.color = "red";
           document.getElementById("name").value="";
           document.getElementById("name").focus();
				return false; 

       }
    
   }
   
   
 //핸드폰 번호 자동 커서 이동
   function nextBlank(N, Obj, nextID) {
     if (document.getElementById(Obj).value.length == N) {
       document.getElementById(nextID).focus();
     }
   }
   
   //핸드폰 번호 숫자만 입력되게
   function checkNumber() {
     if (event.keyCode < 48 || event.keyCode > 57) {
       event.returnVlue = false;
     }
   }
   
   
 
   </script>
</body>
</html>
