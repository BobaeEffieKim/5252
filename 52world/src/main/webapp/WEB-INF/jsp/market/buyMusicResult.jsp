<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>구매목록</h3>
	<p>
		곡 수 : ${fn:length(mList) }개&nbsp&nbsp&nbsp&nbsp총가격 :
		🥒${fn:length(mList)*3 }개 &nbsp&nbsp&nbsp&nbsp
		<button id="buyMusic" onclick="buy()">구매하기</button>
	</p>
	<table>
		<c:forEach var="vo" items="${mList }">
		<form name="form"><input type="hidden" name="music" value="${vo.id }" /></form>
			<tr>
				<td rowspan='2'><div class='list-icon'>🎵</div></td>
				<td><div class='list-row'>&nbsp&nbsp${vo.title }</div></td>
				<td rowspan='2'><div class='list-icon'>🥒x3</div></td>

			</tr>
			<tr>
				<td><div class='list-row'>&nbsp&nbsp${vo.singer }</div></td>
			</tr>
		</c:forEach>
		
	</table>
	<script>
	function buy(){
		const id = 'input[name="music"]';
		const selected = document.querySelectorAll(id);
		let result = '';
		selected.forEach((el)=>{
			result += el.value+'_';
		});
		
		location.href="buyMusicResult.do?id="+result;
		
	}
	
	</script>
</body>
</html>