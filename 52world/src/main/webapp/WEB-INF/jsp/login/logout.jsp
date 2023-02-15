<%@page import="com.dev.common.HttpUtil"%>
<%@page import="com.dev.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script type="text/javascript">
	
		let result= confirm('정말 로그아웃 하시겠습니까?');
		if(result == true){
			<%
	
			session.invalidate();
			response.sendRedirect("WEB-INF/login/loginForm.jsp");
			
			%>
		} else{
			location.href = "minihomepee.do";
		}
	</script>

</body>
</html> 