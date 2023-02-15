<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<div class="right-container">
	<div class="right-title" >
		
	</div>
	<div class="right-tab">
		<div class="tab"><a href="minihomepee.do?id=${homeOwner.id }">홈</a></div>
     	<div class="tab"><a href="diaryDetails.do?id=${homeOwner.id }">다이어리</a></div>
  	    <div class="tab"><a href="galleryList.do?id=${member.id }&homepee_id=${homeOwner.id}">사진첩</a></div>
    	<div class="tab"><a href="guestbook.do?id=${homeOwner.id }">방명록</a></div>
		<c:if test="${member.id eq homeOwner.id }">
        	<div class="tab"><a href="market.do?id=${homeOwner.id }">마켓</a></div>
        	<div class="tab"><a href="setting.do">설정</a></div>
        </c:if>
        <!-- 로그아웃 -->
        <div class="tab"><a href="javascript:logout();" >로그아웃</a></div>
	</div>
	
	
	
</div>
