<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckForm.jsp</title>
</head>
<body>
<div style="text-align:center">
<h3>아이디 중복체크</h3>
<form method="post" action="idCheckProc.jsp" onsubmit="retrun blankCheck(this)">
<label for="id">아이디</label>
<input type="text" name="id" id="id" size="15" autofocus>
<!-- <input type="submit" value="중복체크"> -->
<input type="button" id="button" onclick="idCheck()" >중복확인</button><br>

</form>
</div>

<script>
function blankCheck(p){
	var id=p.id.value;
	id=id.trim();
	if(id.lenght<8){
		alert("아이디는 8글자 이상 입력해주세요")
		return false;
	}
	return true;
	
	
}
</script>
</body>
</html>