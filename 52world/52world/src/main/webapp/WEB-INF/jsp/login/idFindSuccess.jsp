
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/loginMain.css?after" />
<title>아이디찾기결과창 idFindSuccess.jsp</title> 
<style>
#findIdRetry {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#findIdRetry:hover{
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
#home {
	border-radius: 30px;
	border: 1px solid rgb(34, 84, 52);
	background-color: darkgreen;
	color: white;
	font-weight: bold;
	font-size: 15px;
	width: 150px;
	height: 30px;
}
#home:hover{
	background-color: white;
	color: darkgreen;
}
</style>
</head>
<body>
   <div class="background">
   <div align="center" id="wrap">

      <div id="idCheckResult">
         <form id="findForm" name="findForm" action="memberIdCheck.do" method="post">
           <div class="name" >
           <h3>
           <c:if test="${member.id eq null}" >
           	검색 결과가 없습니다.
           </c:if>
           
           <c:if test="${member.id ne null}">
           	<label>아이디는 </label><span style="font-weight:bold;">"${member.id}"</span><label>입니다. </label>
           
           </c:if>
           </h3>
           </div>
            <br><br>
            <div>
			<c:if test="${member.id eq null}">	            
               <button id="findIdRetry" type="button" value="다시시도" onclick="location.href='memberIdCheckForm.do'">다시시도</button>
               <button id="findIdCancle" type="button" value="홈으로" onclick="location.href='memberLoginForm.do'">홈으로</button>
            </c:if>   
            <c:if test="${member.id ne null}">
            	<button id="home" type="button" value="홈으로" onclick="location.href='memberLoginForm.do'">홈으로</button>
            </c:if>   
            </div>
            
         
         </form>
      </div>
   </div>
</div>
   <script type="text/javascript">
   
  
   </script>
</body>

</html>
