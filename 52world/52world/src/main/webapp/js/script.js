/**
 * 
 */

function display_message() {

	if (document.getElementById('id').value != '') {
		document.getElementById("idmessage1").style.display = 'none';


	}
}



//아이디 제한
const userId = document.querySelector("#id");
userId.onchange = changeId;
var pattern1 = /[0-9]/; // 숫자
var pattern2 = /[a-zA-Z]/; // 문자
var pattern3 = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
var numtextyn = (pattern1.test(id) || pattern2.test(id));
function changeId() {
	if (!numtextyn || pattern3.test(id) || id.length < 15) {
		alert("15자리 이하 문자와 숫자로 작성해주세요");
		userId.value = ""
		userId.focus();
		return false;
	} else {
		return true;
	}
}

function checkCk() {
	let id = document.getElementById("id").value;
	fetch('.//../idcheckAjax.do?id=' + id)
		.then(function(result) {
			return result.json();
		}).then(result => {
			console.log(result);
			if (result == 1) {
				document.getElementById("idmessage1").style.display = 'block';
				document.getElementById('idmessage1').innerText = '중복된 아이디입니다.'
				document.getElementById("idmessage1").style.color = 'red';
			} else {

				document.getElementById("idmessage1").style.display = 'block';
				document.getElementById('idmessage1').innerText = '사용가능한 아이디입니다.'
				document.getElementById("idmessage1").style.color = 'green';
			}
		})
}

//이메일선택시 값 입력되게 하기
function checkEmail() {
	let domain = document.getElementById("emailList").value;
	if (domain != "") {
		document.getElementById("email_domain").value = domain;
	} else {
		document.getElementById("email_domain").focus();
		document.getElementById("email_domain").value = "";
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