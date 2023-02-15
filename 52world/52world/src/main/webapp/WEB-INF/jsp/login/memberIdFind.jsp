
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/loginMain.css?after" />
<title>아이디찾기 memberIdFind.jsp</title>
<style>
p{
	margin:4px;
}
h4{
	margin:10px;
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
#findIdBtn:hover{
	background-color: white;
	color: darkgreen;
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
#findIdCancle:hover{
	background-color: white;
	color: darkgreen;
}
</style>
</head>
<body>
	<div class="background">
		<div align="center" id="wrap">

			<!-- id찾기 입력창 -->
				<form id="findForm" name="findForm" action="memberIdCheck.do"
					method="post">
					<div align="center" id="contain">

						<div class="nameGroup">
							<h4 class="namedTitle">
								<label for="name">이름</label> &nbsp;&nbsp;&nbsp;<input
									type="text" name="name" size="15" required id="name" required
									onchange="CheckName()" />
							</h4>
						</div>
						<!-- 숨어있는 이름 유효성 메세지 -->
						<div class="message">
							<p style="visibility: hidden" id="nameheck1"></p>
							<input type="hidden" id="hidden"
								class="nameheck2" tabindex="-2" value="이름을 확인해주세요" />
						</div>


						<div class="birthGroup">
							<h4 class="birthTitle">
								<label for="birth">생년월일</label>
								&nbsp;&nbsp;&nbsp; <select id="year" name="year" required>
									<option value="">년</option>
									<c:forEach var="i" begin="1920" end="2022">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>&nbsp;&nbsp; <select id="month" name="month" required>
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
								</select>&nbsp;&nbsp; <select id="day" name="day" required>
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
							</h4>
						</div>



						<div class="phoneGroup">
							<h4 class="phoneTitle">
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
							</h4>


							<!-- id찾기검사 -->
							<div class="message">
								<p style="visibility: hidden" id="idResult1"></p>
								<input type="hidden" id="hidden" class="idResult2" tabindex="-2"
									value="폰번호를 확인해주세요" />
							</div>
						</div>

						<br>
						<!-- 아이디찾기 결과창 -->

							<div class="btns">
								<button id="findIdBtn" type="submit" value="아이디찾기">아이디찾기</button>
								<button id="findIdCancle" type="button" value="뒤로가기"
									onclick="location.href='memberLoginForm.do'">뒤로가기</button>
							</div>

					</div>
				</form>
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
   
  //가입완료창
  function find(){
	  alert('아이디 찾기를 진행하시겠습니까?');
  }
 
  
  function searchId(){
	  let id =
	  
				<c:if test="${member.id eq null}">
						"검색 결과가 없습니다.";
				</c:if>
				<c:if test="${member.id ne null}">
					<label>아이디는 </label>
					<span>"${member.id}"입니다.</span>
				</c:if>
	  
  }
  
  function lookUpId(){
	  let name = document.getElementByName("name").value;
	  let birth = document.getElementByBirth("birth").value;
	  let phone = document.getElementByPhone("phone").value;
	  fetch(".//../idFindAjax.do?name=" + name + "&birth="+birth+"&phone="+phone)
      .then(function (result) {
        return result.json();
      })
      .then((result) => {
        console.log(result);
      })
  }
  
  
   </script>
</body>
</html>
