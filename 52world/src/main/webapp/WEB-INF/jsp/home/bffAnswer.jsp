<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	background-image: url('/52world/asset/popup_background.png');
	background-repeat: no-repeat;
	background-size: cover;
}

#container {
	margin: 80px 0 0 0;
	padding: 30px 50px;
}

.test_obj {
	resize: none;
	width: 330px;
}
#textbox {
	padding-top: 30px 0;
}
.buttooooon {
	text-align : center;
}
input.buttons {
	text-align: center;	
	padding: 5px;
	width: 30%;
	margin: 0 10px;
}

</style>
<title>Insert title here</title>
</head>
<body>

	<div id="container">


		보낸 이 : ${realName } <br> <br> ${realName }님께서 ${member.name }님과
		<br> 일촌 맺기를 희망합니다. <br>

		<hr>

		아래 일촌명으로 신청하셨습니다. <br> <br> ${realName }(${reqName }) -
		${member.name }(${applyName }) <br> <br>

		<div id="textbox">
			<textarea class="test_obj" rows="3" cols="30">${msg }</textarea>
		</div>
		<hr>
		일촌을 맺으시겠습니까? <br> <br>
		<form name="answerForm" action="bffAnswerOutput.do" method="post">

			<input type="hidden" value="${id }" name="id"> 
			<input
				type="hidden" value="Y" name="answer"> 
				<input type="hidden"
				value=" ${reqId }" name="reqId"> 
				<input type="hidden"
				value=" ${applyId }" name="applyId"> 
				<input type="hidden"
				value=" ${reqName }" name="reqName"> 
				<input type="hidden"
				value=" ${applyName }" name="applyName"> 
				<input type="hidden" value=" ${msg }" name="msg"> 
				<div class="buttooooon">
				<input type="button" class="buttons" onclick="yesBff()" value="수락"> 
				<input type="button" class="buttons" onclick="noBff()" value="거절">

				</div>
		</form>
	</div>
	<script>
		function yesBff() {
		
			let id = document.querySelector('form>input[name=id]').value;
			console.log(id)
			fetch('bffAnswerOutput.do',{
				method: 'post',
				headers: {'Content-type': 'application/x-www-form-urlencoded'},
				body: 'id='+id+'&answer=Y'
			})
			.then(result => result.text())
			.then(result => {
				if(result=='Y'){
					alert("${reqId}님과 일촌이 되었습니다 ٩(◕ᗜ◕)و");
					opener.parent.location.reload();
					window.location.assign('bffRequestList.do')
					
				}else{
					console.log('err', result)
				}
			})
			.catch(err => console.log(err))
		
		}
		
		function noBff() {
			let id = document.querySelector('form>input[name=id]').value;
			console.log(id);
			fetch('bffAnswerOutput.do',{
				method: 'post',
				headers: {'Content-type': 'application/x-www-form-urlencoded'},
				body: 'id='+id+'&answer=N'
			})
			.then(result => result.text())
			.then(result => {
				if(result=='N'){
					alert("일촌신청을 거절하셨습니다 (ᴗ_ᴗ̩̩)");
					opener.parent.location.reload();
					window.location.assign('bffRequestList.do')
					
				}else{
					console.log('err', result)
				}
			})
			.catch(err => console.log(err))

		}
	
		
	</script>

</body>
</html>