<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function fromClose(b) {
			if (!b) {
				opener.frm.password.value = "";
				opener.frm.password.focus();
				opener.frm.idCheck.value = "Checked";
				window.close();
			} else {
				opener.frm.id.value = "";
				opener.frm.id.focus();
				window.close();
			}
		}
	</script>
</head>

<body>
	<div align="center">
		<h1>${param.id }는 ${msg }</h1>
		<button type="button" onclick="fromClose(${check})"> 확인 </button>
	</div>
</body>

</html>