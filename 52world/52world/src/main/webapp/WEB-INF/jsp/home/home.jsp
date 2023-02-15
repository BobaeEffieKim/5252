<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", -1);
response.setHeader("Cache-Control", "no-cache");
%>
<!-- 왼쪽 화면 시작 -->
<div class="left-container">
	<!-- 방문자수 이건 고정 -->
	<div class="left-visits">
		<p>
			<fmt:parseNumber var="today" integerOnly="true"
				value="${Homepee.visitToday/1000000 }" />
			TODAY <span style="color: red">${today }</span> | TOTAL
			${Homepee.visitTotal }
		</p>
	</div>

	<!-- 왼쪽 흰색화면 -->
	<div class="left-box">
		<!-- 여기부터 코드 넣으면 됨 -->
		<div style="height: 200px">
			<img class="profile-img" src="${homeOwner.profile }"
				alt="profile image" /> <br />
		</div>

		<div class="profile">
			<p style="height: 200px; width: 200px; padding-top: 50px"><span style="white-space:pre;">${homeOwner.introduce }</span></p>

			<!-- c:if문으로 홈피주인인지 체크 -->
			<c:choose>
				<c:when test="${member.id eq Homepee.homepeeId }">
					<a href="javascript:editProfile()"><span
						style="color: #3e9fbe; font-size: 8px">▶ </span>EDIT</a>&nbsp
					<!-- 일촌수락 -->
					<a href="javascript:bffRequestList()"> <span
						style="color: #3e9fbe; font-size: 8px">▶ </span>일촌수락
					</a>


				</c:when>
				<c:when test="${member.id ne Homepee.homepeeId }">

					<c:if test="${empty isFriend }">
						<a href="javascript:bffRequest()"> <span
							style="color: #3e9fbe; font-size: 8px">▶ </span>일촌신청
						</a>&nbsp
					</c:if>
				</c:when>
			</c:choose>

			<!-- 친구찾기 -->
			<a href="#" onclick="searchMemberForm()"> <span
				style="color: #3e9fbe; font-size: 8px">▶ </span>친구찾기
			</a>&nbsp

			<hr />



			<div class="profile-name">${homeOwner.name }</div>
			<br />
			<!-- 일촌목록 -->
			<a><span style="color: #3e9fbe; font-size: 8px">▶ </span>파도타기</a>
			<hr>
			<div class="friendsList">
				<form action="minihomepee.do" method="post">
					<select name="id" style="width: 150px">
						<optgroup label="일촌목록">
							<option value="${member.id }">내 홈피로</option>
							<c:forEach var="bff" items="${bffList }">
								<option value="${bff.reqId }">${bff.reqId }(${bff.applyName })</option>
							</c:forEach>
						</optgroup>
					</select> <input type="submit" value="이동">
				</form>
			</div>



		</div>
		<!-- 여기까지 넣으면 됨 -->
	</div>
</div>

<!-- 가운데화면 시작 -->
<div class="middle-container">
	<!-- 미니홈피 제목 여긴 고정 -->
	<div class="middle-title">
		<div class="middle-desc">${Homepee.name }</div>
		<div class="middle-url">https://52world.com/${homeOwner.id }</div>
	</div>
	<!-- 메인페이지 가운데 흰색화면 -->
	<div class="middle-box">
		<!-- 여기부터 코드 넣으면 됨 -->
		<div class="updated-news">
			<div class="top-msg">
				Updated news
				<hr />
			</div>
			<div class="updated-news-wrap">
				<div class="updated-news-left">
					<c:forEach var="vo" items="${newList }">
						<c:choose>
							<c:when test="${vo.category eq 'gallery' }">
								<div class="news-line"
									OnClick="location.href ='galleryList.do?id=${homeOwner.id }'"
									style="cursor: pointer;">
									<div class="news-block-img">사 진 첩</div>
									<div class="news-name">${vo.title }</div>
								</div>
							</c:when>
							<c:when test="${vo.category eq 'diary' }">
								<div class="news-line"
									OnClick="location.href ='diaryDetails.do'"
									style="cursor: pointer;">
									<div class="news-block-txt">다이어리</div>
									<div class="news-name">${vo.title }</div>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
				<div class="updated-news-right">
					<div class="category">
						<div class="category-line">
							<div class="diary" OnClick="location.href ='diaryDetails.do'"
								style="cursor: pointer;">
								다이어리 <span
									style="color: rgb(33, 58, 197); font-family: 'NeoDunggeunmo'">${postNumber[0] }/${postNumber[1] }</span>
							</div>
							<div class="picture"
								OnClick="location.href ='galleryList.do?id=${homeOwner.id }'"
								style="cursor: pointer;">
								사진첩 <span
									style="color: rgb(33, 58, 197); font-family: 'NeoDunggeunmo'">${postNumber[2] }/${postNumber[3] }</span>
							</div>
						</div>
						<hr class="dotted" />
						<div class="category-line">
							<div class="picture" OnClick="location.href ='guestbook.do'"
								style="cursor: pointer;">
								방명록 <span
									style="color: rgb(33, 58, 197); font-family: 'NeoDunggeunmo'">${postNumber[4] }/${postNumber[5] }</span>
							</div>
						</div>
						<hr class="dotted" />
					</div>
				</div>
			</div>
		</div>
		<div class="miniroom">
			<div class="top-msg">
				Miniroom
				<hr />
				<img src="${Homepee.miniroom }" alt="miniroom"
					style="width: 600px; height: 300px; padding: 0px 80px" />
			</div>
		</div>
		<div class="friends-say">
			What's friends say
			<hr />
			<c:if test="${!empty isFriend}">
				<form
					action="${pageContext.request.contextPath }/bffCommentInsert.do"
					method="post">

					<br> &nbsp&nbsp 
					<input type="text" placeholder="일촌과 나누고 싶은 이야기를 남겨보세요♥" name="content"
						style="text-align: center; width: 670px; height: 30px; letter-spacing: 2px;"></input>
					<input type="submit" value="등록" style="width: 70px; height: 34px"></input><br>
					<br> 
					<input type="hidden" name="homepee_id" value="${Homepee.homepeeId }"> 
					<input type="hidden" name="writer" value="${member.name }"> 
					<%-- <input type="hidden" name="writerId" value="${member.id }">  --%>
					<input type="hidden" name="bffName" value="${isFriend }" />
					
					
					

				</form>
			</c:if>

			<table style="color: black;" width="750px">
				<thead>

				</thead>
				<tbody>
					<c:forEach var="comm" items="${commentList}">
						<tr>
							<td>&nbsp ${comm.content} &nbsp &nbsp &nbsp (${comm.bffName })
				<%-- 	 <span style="color: #3e9fbe;"><a href="javascript:guestminihomepee('${comm.writerId}')">${comm.writer }</a></span> --%>
							<span style="color: #3e9fbe;">${comm.writer }</span>
							 
							 <span style="color: grey"> ${comm.createDate }</span>
								
							
							</td>

							<c:choose>
								<c:when test="${member.id eq Homepee.homepeeId }">
									<td><a onclick="deleteCallBack()" style='cursor: pointer;'
										class="delete-button" data-value="${comm.commentId }">삭제</a></td>
								</c:when>
								<c:when test="${member.name eq comm.writer }">
									<td><a onclick="deleteCallBack()" style='cursor: pointer;'
										class="delete-button" data-value="${comm.commentId }">삭제</a></td>
								</c:when>
								
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>
		<!-- 여기까지 넣으면 됨 -->
	</div>
</div>
<script>
	


	function deleteCallBack(e) {
		const delBtn = event.target;
		let delId = delBtn.getAttribute("data-value");
		console.log(delId + "=삭제할 일촌평");
		let isDel = confirm("삭제하시겠습니까?");
		if (isDel == true) {
			let delAjx = new XMLHttpRequest(); //send
			delAjx.open('post', 'bffCommentDeleteAjax.do');
			delAjx.setRequestHeader('Content-type',
					'application/x-www-form-urlencoded');
			delAjx.send('delId=' + delId); // Cannot read properties of undefined (reading 'parentElement')
			delAjx.onload = function() {

				delBtn.parentElement.parentElement.remove();
				alert("삭제되었습니다");

			}
		}

	}
	
	//이름 눌러서 그사람 미니홈피 가기
	function guestminihomepee(userId){
		let id = userId;
		console.log(id);
		
		let isBoss = confirm(id+"님의 홈을 방문하시겠습니까?");
		
		   if (isBoss) {
			      alert(id+'님의 홈으로 이동~! 방문을 환영합니다');
			      location.href="minihomepee.do?id="+id;
			}
			   else {
			    
			}
	}
</script>

<!-- 가운데 화면 끝 -->

