<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<style>

body {
font-family: 'Gowun Dodum', sans-serif;
}

.wrap {
	margin: 30px 0 0 0;
	padding: 10px 10px;
	
}

img { display: block; margin: 0px auto; }

.terms__check__all{
margin-top : 10px;
}

.content{
width: 500px;
height: 680px;
box-sizing: border-box;
border-radius :10px; 
border-color :  #cccccc; 

}

ul {
padding-left: 0px;
}
li{
list-style: none;

}

.terms__content{
width : 480px;
  height: 100px;
  background-color: ;
  border:solid 1px #cacaca;
  display : inline-block;
  padding : 5px;
  margin : 5px;
  overflow:auto;

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

#test_btn2:enabled:hover{
color: white;
 background-color: green;
}
 

</style>
</head>
<body>

<div class="wrap">
      <div class="logo">
        <img src="/52world/asset/52logo.png" width="330" height="100"  >
      </div>
      <div class="contents">
        <form method="POST" id="form__wrap">
          <div class="terms__check__all">
            <input type="checkbox" name="checkAll" id="checkAll" />
            <u><strong><label for="checkAll" style=""
              >52world 이용약관, 개인정보 수집 및 이용, 프로모션 정보 수신(선택)에</u><br>&nbsp;&nbsp;&nbsp;&nbsp;<u>모두 동의합니다.</label></strong></u>
          </div>
          <br>
          <ul class="terms__list">
            <li class="terms__box">
              <div class="input__check">
                 <strong><input type="checkbox" name="agreement" id="termsOfService" value="termsOfService" required />
               <label for="termsOfService" class="required">52world 이용약관 동의</label> <span style="color:blue">(필수)</span></strong>
              </div>
              <div class="terms__content">
                여러분을 환영합니다. 52world 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 52world
                서비스의 이용과 관련하여 52world 서비스를 제공하는 52world 주식회사(이하 ‘52world’)와 이를 이용하는 52world 서비스
                회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 52world 서비스 이용에 도움이 될 수 있는
                유익한 정보를 포함하고 있습니다. 52world 서비스를 이용하시거나 52world 서비스 회원으로 가입하실 경우 여러분은 본
                약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기
                바랍니다.
              </div>
            </li>
             <br>
            <li class="terms__box">
              <div class="input__check">
               <strong> <input type="checkbox" name="agreement" id="privacyPolicy" value="privacyPolicy" required />
                <label for="privacyPolicy" class="required">개인정보 수집 및 이용 동의</label> <span style="color:blue">(필수)</span></strong>
              </div>
              <div class="terms__content">
                개인정보보호법에 따라 52world에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및
                이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니
                자세히 읽은 후 동의하여 주시기 바랍니다.1. 수집하는 개인정보 이용자는 회원가입을 하지 않아도 정보 검색,
                뉴스 보기 등 대부분의 네이버 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페,
                블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 네이버는 서비스 이용을
                위해 필요한 최소한의 개인정보를 수집합니다.
              </div>
            </li>
             <br>
            <li class="terms__box">
              <div class="input__check">
               <strong> <input type="checkbox" name="agreement" id="withdrawalProhibition" value="withdrawalProhibition" required/>
                <label for="withdrawalProhibition">회원 정보의 변경 및 탈퇴</label> <span style="color:blue">(필수)</span></strong>
              </div>
              <div class="terms__content">
             회원은 '정보수정'을 통해 언제든지 본인의 개인정보를 열람/삭제/수정할 수 있습니다. 다만, 서비스 관리를 위해 필요한 아이디는 수정이 불가능합니다.
			회원은 가입신청 시 기재한 사항이 변경되었을 경우 홈페이지를 통해 수정을 하거나 전자우편 기타 방법으로 홈페이지에 그 변경사항을 알려야 합니다.
			제2항의 변경사항을 홈페이지에 알리지 않아 발생한 불이익에 대하여 홈페이지는 책임지지 않습니다. 또한 52world는 가입 시 탈퇴가 불가능함을 알려드립니다.
              </div>
            </li>
             <br>
            <li class="terms__box">
              <div class="input__check">
                <strong><input type="checkbox" name="agreement" id="allowPromotions" value="allowPromotions" />
                <label for="allowPromotions">프로모션 정보 수신 동의</label></strong>
              </div>
              <div class="terms__content">
                52world에서 제공하는 이벤트/혜택 등 다양한 정보를 휴대전화, 이메일로 받아보실 수
                있습니다. 일부 서비스(별도 회원 체계로 운영하거나 52world 가입 이후 추가 가입하여 이용하는 서비스 등)의
                경우, 개별 서비스에 대해 별도 수신 동의를 받을 수 있으며, 이때에도 수신 동의에 대해 별도로 안내하고
                동의를 받습니다.
              </div>
            </li>
          </ul>
          <br>
          <div style="text-align: center" id="btn_group">
           <input type="button" id="test_btn1" onclick="javascript:window.close()" value="취소"></input>
          <button type="submit" id="test_btn2" class="next-button" onclick="javascript:register()" disabled>확인</button>
          </div>
        </form>
      </div>
    </div>


<script>

("use strict");

const form = document.querySelector('#form__wrap'); //데이터 전송하는 from
const checkAll = document.querySelector('.terms__check__all input'); //모두 동의 체크 박스
const checkBoxes = document.querySelectorAll('.input__check input'); //모두 동의를 제외한 3개의 체크 박스
const submitButton = document.querySelector('button'); //확인 버튼


const agreements = {
  termsOfService: false, //첫번재 필수동의 체크 박스
  privacyPolicy: false, //두번째 필수동의 체크 박스
  withdrawalProhibition: false, //이벤트 수신 동의 체크박스
};


form.addEventListener('submit', (e) => e.preventDefault()); // 새로고침(submit) 되는 것 막음

checkBoxes.forEach((item) => item.addEventListener('input', toggleCheckbox));

function toggleCheckbox(e) {
  const { checked, id } = e.target;  
  agreements[id] = checked;
  this.parentNode.classList.toggle('active');
  checkAllStatus();
  toggleSubmitButton();
}


function checkAllStatus() {
  const { termsOfService, privacyPolicy, withdrawalProhibition, allowPromotions} = agreements;
  if (termsOfService && privacyPolicy && withdrawalProhibition && allowPromotions) {
    checkAll.checked = true; //활성화 버튼
    
  } else {
    checkAll.checked = false;
  }
}


//필수 동의' 체크 여부를 확인한 뒤 버튼을 활성화, 비활성화 동작
function toggleSubmitButton() {
  const { termsOfService, privacyPolicy,withdrawalProhibition } = agreements;
  if (termsOfService && privacyPolicy && withdrawalProhibition) {
    submitButton.disabled = false; //버튼 활성화
    
  } else {
    submitButton.disabled = true; //버튼 비활성화
    
  }
}


checkAll.addEventListener('click', (e) => {
  const { checked } = e.target;
  if (checked) {
    checkBoxes.forEach((item) => {
      item.checked = true;
      agreements[item.id] = true;
      item.parentNode.classList.add('active');
    });
  } else {
    checkBoxes.forEach((item) => {
      item.checked = false;
      agreements[item.id] = false;
      item.parentNode.classList.remove('active');
    });
  }
  toggleSubmitButton();
});


//회원가입 페이지 가기
function register(){
	opener.location.href="memberInsertForm.do";
	 self.close();
	
}
</script>


</body>
</html>