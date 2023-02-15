<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>회원정보입력</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>

	body {
		height: 100vh;
		width: 100%;
		background-image: url("/52world/asset/최최종.png");
		background-repeat: no-repeat;
		background-size: cover;
		background-position: center;
		font-family: 'Gowun Dodum', sans-serif;
		
	}
	
	#wrap {
		margin: 60px auto;
		padding-top:50px;
		height: 90%;
		width: 650px;
		background-color : white;
		border-radius :20px; 
		
	}
	
	#joinForm{
	
	padding-top : 50px;
	
	
	}
	
	#contain {
		font-size: x-large;
		height : 95%;
		width : 630px;
	}
	
	input {
		font-size: large;
		font-family: 'Gowun Dodum', sans-serif;
	   	border-style:none;
	      border-bottom:solid 1px #cacaca;
	      border-collapse:collapse;
		
	}
	
	select {
		font-size: large;
		font-family: 'Gowun Dodum', sans-serif;
		
		
	}
	
	button {
		font-size: large;
		font-family: 'Gowun Dodum', sans-serif;
		
		
	}
	
	option {
		font-size: large;
		font-family: 'Gowun Dodum', sans-serif;
		
		
	}
	
	.message {
		font-size: medium;
		font-weight: bold;
		margin-left : 20px;
		font-family: 'Gowun Dodum', sans-serif;
	
	}
	
	.message-none {
	font-size: medium;
		font-weight: bold;
		margin-left : 20px;
		height :3px;
	}
	
	h5 {
		margin: 10px 5px 5px 5px;
		padding: 5px;
	}
	
	
	.message {
		height: 18px;
		width: 600px;
	}
	
	p {
		margin:5px;
		padding :0;
	}
	
	
	#btn_group{
	padding-top:10px;
}
	#test_btn1{
	border-radius: 5px;
	border:solid 1px #cacaca;
	background-color: rgba(0,0,0,0);
	 padding: 5px;
             
        
     }
	#test_btn2{
	border-radius: 5px;
	border:solid 1px #cacaca;
	background-color: rgba(0,0,0,0);
	padding: 5px;
	
	}
 
  #test_btn1:hover{
         color: white;
         background-color: green;
}
     #test_btn2:hover{
         color: white;
         background-color: green;
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
   integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
   crossorigin="anonymous"></script>
</head>

<body>
	<div align="center" id="wrap">	
	
	<img src="/52world/asset/52logo.png" width="320" height="90">
	<div>
	</div>
		<form id="joinForm" method="post" action="memberInsert.do" onsubmit="return register()">
			<div align="center" id="contain">
				<h5 class="idTitle">
					<!-- 아이디 -->
					<label for="id">아이디</label> &nbsp;&nbsp;&nbsp;<input type="text" name="id" id="id"
						required placeholder="4~12자리 이하의 영문과 숫자" size="25" onchange="checkCk()" />
				</h5>

				<!-- 숨어있는 아이디 중복체크 메세지 -->
				<div class="message">
					<p style="visibility: hidden" id="idmessage1"></p>
					&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden" class="idmessage2" tabindex="-2"
						value="아이디를 확인해주세요" />
				</div>

				<!-- 비밀번호 -->
				<div class="passwordGroup">
					<h5 class="passwordTitle">
						<label for="password">비밀번호</label> 
						&nbsp;&nbsp;&nbsp;<input type="password" size="25"
							name="password" id="password" placeholder="영문+숫자+특수문자 조합"
							required onchange="limitPwd()" />
					</h5>
				</div>
				<!-- 숨어있는 패스워드 유효성 메세지 -->
				<div class="message">
					<p style="visibility: hidden" id="pwdmessage1"></p>
					&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden" class="pwdmessage2" tabindex="-2"
						value="패스워드를 확인해주세요" />
				</div>

				<div class="passwordGroup">
					<h5 class="passwordTitle">
						<label for="passwordCheck">비밀번호 재확인</label> &nbsp;&nbsp;&nbsp;<input type="password"
							autocomplete="new-password" name="passwordCheck" id="passwordCheck" size="25"
							placeholder="영문+숫자+특수문자 조합" required onchange="CheckPwd()" />
					</h5>
				</div>

				<!-- 숨어있는 패스워드 유효성 메세지 -->
				<div class="message">
					<p style="visibility: hidden" id="pwdcheck1"></p>
					&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden" class="pwdcheck2" tabindex="-2"
						value="패스워드를 확인해주세요" />
				</div>

				<div class="nameGroup">
					<h5 class="namedTitle">
						<label for="name">이름</label> &nbsp;&nbsp;&nbsp;<input type="text" name="name"
						 size="15"	required id="name" required onchange="CheckName()" />
					</h5>
				</div>

				<!-- 숨어있는 이름 유효성 메세지 -->
				<div class="message">
					<p style="visibility: hidden" id="nameheck1"></p>
					&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden" class="nameheck2" tabindex="-2"
						value="이름을 확인해주세요" />
				</div>

				<div class="birthGroup">
					<h5 class="birthTitle">
						<label for="birth">생년월일</label>
						<!--        <input type="text" name="year" id="year" placeholder="년(4자)" size=5 required /> -->
						&nbsp;&nbsp;&nbsp;<select id="year" name="year" required>
							<option value="">년</option>
							<c:forEach var="i" begin="1920" end="2022" >
								<option value="${i}">${i} </option>
							</c:forEach>
						</select>&nbsp;&nbsp;<select id="month" name="month" required >
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
						</select>&nbsp;&nbsp;<select id="day" name="day" required >
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
					</h5>
				</div>
				<div class="message-none">
					<p style="visibility: hidden"></p>
				</div>

				<div class="genderGroup">
					<h5 class="genderTitle">
						<label for="gender">성별</label> &nbsp;&nbsp;&nbsp;<input type="radio"
							name="userGender" autocomplete="off" value="M" />남성
						&nbsp;&nbsp;&nbsp;<input type="radio" name="userGender" autocomplete="off" value="F"
							checked />여성
					</h5>
				</div>
				<div class="message-none">
					<p style="visibility: hidden"></p>
				</div>

				<div class="emailGroup">
					<h5 class="emailTitle">
						<label for="eamil">이메일</label> &nbsp;&nbsp;&nbsp;
						<input type="text" id="email_id" name="email_id" title="아이디" required placeholder="아이디 입력" size="12" value="" onchange="checkEmail()"/> 
						<span> @ </span>
						<input type="text" id="email_domain" name="email_domain" title="이메일 주소" required placeholder="직접 입력하기" size="15" onchange="checkEmailDomain()"/>
						<select class="select" id="emailList" title="이메일 주소 선택" onchange="selectEmail()" >						
							<option value="" id="textEmail" selected>직접 입력하기</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="nate.com">nate.com</option>
						</select>
					</h5>
				</div>

				<!-- 숨어있는 이메일 유효성 메세지 -->
				<div class="message">
					<p style="visibility: hidden" id="emailcheck1"></p>
					<input type="hidden" id="hidden" class="emailcheck2" tabindex="-2"
						value="이메일을 확인해주세요" />
				</div>

				<div class="phoneGroup">
					<h5 class="phoneTitle">
						<label for="phone">전화번호</label> &nbsp;&nbsp;&nbsp;<select class="select"
							id="firstPhone" name="firstPhone" title="앞자리">
							<option value="010" selected="selected">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
						</select>&nbsp;-&nbsp;<input type="text" name="secondPhone" id="secondPhone" size="5" required 
							maxlength="4" onkeyup="nextBlank(4,this.id,'lastPhone');"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
							required />&nbsp;-&nbsp;<input type="text" name="lastPhone" required 
							id="lastPhone" size="5" maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
							required onchange="checkPhone()" />
					</h5>

					<!-- 폰번호 유효성검사 -->
					<div class="message">
						<p style="visibility: hidden" id="phonemessage1"></p>
						<input type="hidden" id="hidden" class="phonemessage2"
							tabindex="-2" value="폰번호를 확인해주세요" />
					</div>

		
					<br>
					<!-- 메인창으로 보내게 해야함 -->
					<div style="text-align: center" id="btn_group">				
						<input id="test_btn1" type="button" value="취소하기" onclick="location.href='memberLoginForm.do'" /> 
						<input id="test_btn2" type="submit" value="가입하기" />
					</div>
				</div>
			</div>
		</form>
	</div>

	<script>
 
	
	
	
      //아이디 중복 체크 + 유효성 검사
      function checkCk() {
        let id = document.getElementById("id").value;
        fetch(".//../idcheckAjax.do?id=" + id)
          .then(function (result) {
            return result.json();
          })
          .then((result) => {
            console.log(result);
            /* 아이디 유효성 검사 */
            var pattern1 = /[0-9]/; // 숫자
            var pattern2 = /[a-zA-Z]/; // 문자
            var pattern3 = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gim;  // 특수문자
            var numtextyn = pattern1.test(id) & pattern2.test(id);
            if (result == 1) {
              document.getElementById("idmessage1").style.visibility ="visible";
              document.getElementById("idmessage1").innerText ="중복된 아이디입니다";
              document.getElementById("idmessage1").style.color = "red";
              document.getElementById("id").value="";
              document.getElementById("id").focus();
              return false;
              
            } else if (result == 0) {
              if (pattern3.test(id) || id.length < 4 || id.length > 12) {
                document.getElementById("idmessage1").style.visibility ="visible";
                document.getElementById("idmessage1").innerText ="4~12자리 이하 영문+숫자로 입력해주세요";
                document.getElementById("idmessage1").style.color = "blue";
                document.getElementById("id").value="";
                document.getElementById("id").focus();
                return false;
              } else if (numtextyn) {
                document.getElementById("idmessage1").style.visibility ="visible";
                document.getElementById("idmessage1").innerText ="사용가능한 아이디입니다";
                document.getElementById("idmessage1").style.color = "green";
                return true;
              }
            }
          
          });
        
          }
      
 
      
      /* 비밀번호 유효성 검사 */
      function limitPwd() {
        let pwd = document.getElementById("password").value;
        var pattern1 = /[0-9]/; // 숫자
        var pattern2 = /[a-zA-Z]/; // 문자
        var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
        if (!(pattern1.test(pwd) & pattern2.test(pwd) & pattern3.test(pwd))) {
          document.getElementById("pwdmessage1").style.visibility = "visible";
          document.getElementById("pwdmessage1").innerText ="영문+숫자+특수문자를 입력해주세요";
          document.getElementById("pwdmessage1").style.color = "red";
          document.getElementById("password").value="";
          document.getElementById("password").focus();
          return false;
        } else {
          document.getElementById("pwdmessage1").style.visibility = "visible";
          document.getElementById("pwdmessage1").innerText ="사용할수 있는 비밀번호입니다";
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
   
          }else { 
       	  	document.getElementById("nameheck1").style.visibility = "visible";
          	document.getElementById("nameheck1").innerText ="사용 가능한 이름입니다";
           document.getElementById("nameheck1").style.color = "green";	
           return true;
          }
      }
      
      
      
      //이메일선택시 값 입력되게 하기
      function selectEmail() {
 
        let domainlist = document.getElementById("emailList").value;
        if (domainlist != "") {
          document.getElementById("email_domain").value = domainlist;
        } else {
          document.getElementById("email_domain").focus();
          document.getElementById("email_domain").value=""; 
        }

        let domain = document.getElementById("email_domain").value;
   

        var regEmail =/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
     	 if (!regEmail.test(domain)) {
          document.getElementById("emailcheck1").style.visibility = "visible";
          document.getElementById("emailcheck1").innerText ="이메일 형식이 올바르지 않습니다";
          document.getElementById("emailcheck1").style.color = "red";
          document.getElementById("email_domain").value="";    
          document.getElementById("email_domain").focus();
          return false;
        } else {
          document.getElementById("emailcheck1").style.visibility = "visible";
          document.getElementById("emailcheck1").innerText ="사용가능한 이메일 입니다";
          document.getElementById("emailcheck1").style.color = "green";
          return true;
        }
         
        
      }
      
     
      
      //이메일 아이디 정규식 검사
      function checkEmail() {
        let id = document.getElementById("email_id").value;
        
        var regEmail = /^[A-Za-z0-9]+$/;
      
        if (!regEmail.test(id)) {
          document.getElementById("emailcheck1").style.visibility = "visible";
          document.getElementById("emailcheck1").innerText ="아이디 형식이 올바르지 않습니다";
          document.getElementById("emailcheck1").style.color = "red";
          document.getElementById("email_id").value=""; 
          document.getElementById("email_id").focus();
          return false;
        } else {
          document.getElementById("emailcheck1").style.visibility = "visible";
          document.getElementById("emailcheck1").innerText ="사용가능한 이메일 아이디입니다";
          document.getElementById("emailcheck1").style.color = "green";
          return true;
        }
      } 
      
    //이메일 도메인 정규식 검사
      function checkEmailDomain(){
    	  let domain = document.getElementById("email_domain").value;
    	   

          var regEmail =/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       	 if (!regEmail.test(domain)) {
            document.getElementById("emailcheck1").style.visibility = "visible";
            document.getElementById("emailcheck1").innerText ="이메일 형식이 올바르지 않습니다";
            document.getElementById("emailcheck1").style.color = "red";
            document.getElementById("email_domain").value="";    
            document.getElementById("email_domain").focus();
            return false;
          } else {
            document.getElementById("emailcheck1").style.visibility = "visible";
            document.getElementById("emailcheck1").innerText ="사용가능한 이메일 입니다";
            document.getElementById("emailcheck1").style.color = "green";
            return true;
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
      
      //전화번호 중복 체크 + 유효성 검사
      function checkPhone() {
        let first = document.getElementById("firstPhone").value;
        let second = document.getElementById("secondPhone").value;
        let last = document.getElementById("lastPhone").value;
        let phone = first + "-" + second + "-" + last;
        console.log(phone);
        fetch(".//../phoneCheckAjax.do?phone=" + phone)
          .then(function (result) {
            return result.json();
          })
          .then((result) => {
            console.log(result);
            /* 전화번호 유효성 검사 */
            var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
            if (!regExp.test(phone)) {
              document.getElementById("phonemessage1").style.visibility ="visible";
              document.getElementById("phonemessage1").innerText ="전화번호를 확인해주세요";
           	 document.getElementById("phonemessage1").style.color = "blue";
             document.getElementById("secondPhone").value="";
             document.getElementById("lastPhone").value="";
             document.getElementById("secondPhone").focus();
           	 return false;
             
            } else if (result == 1) {
              document.getElementById("phonemessage1").style.visibility ="visible";
              document.getElementById("phonemessage1").innerText ="이미 가입된 번호입니다";
              document.getElementById("phonemessage1").style.color = "red";
              document.getElementById("secondPhone").value="";
              document.getElementById("lastPhone").value="";
              document.getElementById("secondPhone").focus();
              return false;
            } else {
            	document.getElementById("phonemessage1").style.visibility ="visible";
                document.getElementById("phonemessage1").innerText ="가입 가능한 번호입니다";
                document.getElementById("phonemessage1").style.color = "green";
   
            	return true;
            }
  
          });
        
      }
      
      function register() {
  		
    	  alert('52world에 오신것을 환영합니다');
  		
      }
    </script>
</body>
</html>