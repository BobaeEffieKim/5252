<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	$(function () {
		$('#id').change(function () {
			$('#idCheck').val('unChecked');
		});
		//idCheck 버튼을 클릭했을 때 
		$("#idCheck").click(function () {
			var id = $("#id").val();
			
			if(id === '') {
				alert('아이디를 입력하세요.') ;
			} else {
				$.ajax({
					type: 'POST',
					data: {
						id: id
					},
					url: "ajaxMemberIdCheck.do", //별도 서블릿으로 만들었다. *.do에서 제외(컨트롤러 안탐)
					success: function (data) {
						if (data > 0) {
							alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
							$("#id").val("");
							$("#id").focus();
						} else {
							alert("사용가능한 아이디입니다.");
							$("#idCheck").val("checked");
							$("#password").focus();
						}
					},
					error: function (error) {
						alert("error : " + error);
					}
				});
				
			}
		});
	});
</script>

<script type="text/javascript">
	function formCheck() {
		if (frm.id.value == "") {
			alert("아이디를 입력하세요.");
			frm.id.focus();
			return false;
		}
		if (frm.idCheck.value == "unChecked") {
			alert("아이디 중복체크를 하세요.");
			return false;
		}
		if (frm.password.value == "") {
			alert("패스워드를 입력하세요.");
			frm.password.focus();
			return false;
		}
		frm.submit();
	}
	function idCheckDo() {
		var id = frm.id.value;
		window.open("/DbTest/idCheck.do?id=" + id, "childForm", "width=570, height=350, resizable = no, scrollbars = no");
	}
</script>
<div align="center">
	<div>
		<h1>회원가입</h1>
	</div>
	<div>
		<form id="frm" name="frm" action="memberJoin.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<td width="300"><input type="text" id="id" name="id">&nbsp;
							<button type="button" id="idCheck" name="idCheck" value="unChecked">중복체크</button>
						</td>
					</tr>
					<tr>
						<th width="150">패스워드</th>
						<td width="300"><input type="password" id="password" name="password"></td>
					</tr>
					<tr>
						<th width="150">이름</th>
						<td width="300"><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<th width="150">주소</th>
						<td width="300"><input type="text" id="address" name="address"></td>
					</tr>
				</table>
			</div><br />
			<div>
				<button type="button" onclick="formCheck()">회원가입</button>&nbsp;&nbsp;&nbsp;
				<button type="reset">취 소</button>&nbsp;&nbsp;&nbsp;
				<button type="button" onclick="location.href='memberLoginForm.do'">홈</button>
			</div>
		</form>
	</div>
</div> 