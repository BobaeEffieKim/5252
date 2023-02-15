<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/galleryList.css" />
<title>guestBook</title>
<style>
#container {
	padding: 0;
	width: 720px;
	box-sizing: border-box;
	margin: 10px auto;
	background-color: rgb(245, 245, 245);
}

.galleryFolder {
	height: 300px;
	font-size: 18px;
	line-height: 30px;
}
</style>
</head>


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
		<%-- <div style="height: 200px">
		<img class="profile-img" src="${homeOwner.profile }" alt="profile image" />
		<br />
		</div>
		<div class="profile">
			<p style="height: 200px; padding-top: 50px">${homeOwner.introduce }</p>
 --%>


		<!-- kbb) 폴더리스트 넣기 -->



		<c:choose>
			<c:when test="${member.id eq homeOwner.id }">
				<div class="folderBtn" style="width: 300px;">
					<h3>
						<input type="button" value="폴더생성" onclick="makeGalleryFolder()"
							style="cursor: pointer;"> <input type="button"
							value="폴더수정" onclick="updateGalleryFolder()"
							style="cursor: pointer;"> <input type="button"
							value="폴더삭제" onclick="deleteGalleryFolder()"
							style="cursor: pointer;">
					</h3>

				</div>
			</c:when>

		</c:choose>

		<div class="galleryFolder">
			<c:forEach var="item" items="${GFlist }">
				<div class="row date">
					<div class="makeFolder">
						<a id="makeF"
							href="galleryList.do?homepee_id=${Homepee.homepeeId}&fId=${item.folderName}&pageNum=1&amount=5"><span>📁
								${item.folderName }</span></a>

					</div>
				</div>

			</c:forEach>
			<br> <br>

		</div>


		<div class="profile">
			<!-- c:if문으로 홈피주인인지 체크 -->
			<c:choose>
				<c:when test="${member.id eq Homepee.homepeeId }">
					<a href="#" onclick="editProfile()"><span
						style="color: #3e9fbe; font-size: 8px">▶ </span>EDIT</a>
				</c:when>
				<c:when test="${member.id ne Homepee.homepeeId }">
					<a href="#" onclick="bffRequest()"> <span
						style="color: #3e9fbe; font-size: 8px">▶ </span>일촌신청
					</a>
				</c:when>
			</c:choose>
			&nbsp
			<!-- 친구찾기 -->
			<a href=""> <span style="color: #3e9fbe; font-size: 8px">▶
			</span>친구찾기
			</a> &nbsp
			<!-- 로그아웃 -->
			<a href=""> <span style="color: #3e9fbe; font-size: 8px">▶
			</span>로그아웃
			</a>
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
							<c:forEach var="bff" items="${bffList }">
								<option value="${bff.reqId }">${bff.reqId }(${bff.reqName })</option>
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
	<!-- 미니홈피 제목 -->
	<div class="middle-title">
		<div class="middle-desc">${Homepee.name }</div>
		<div class="middle-url">https://52world.com/${homeOwner.id }</div>
	</div>


	<!-- 사진첩 화면 만들어야해요-->
	<div class="middle-box">


		<div class="container">
			<!-- <h1>사진첩입니다</h1> -->

			<div class="btn">

				<c:choose>
					<c:when test="${member.id eq Homepee.homepeeId }">
						<h3>
							<input type="button" value="사진올리기" onclick="addToGalleryList()"
								style="cursor: pointer;">
						</h3>
					</c:when>
				</c:choose>



			</div>

			<div class="realContainer">
				<c:forEach var="item" items="${list }">
					<br>
					<div class="row data">


						<form id="formNum">
							<div class="num">



								<span style="text-align: left"></span><label>NO. </label><span>${item.postId}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="title"
									style="font-weight: bold; font-size: 17px;">${item.title}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

								<c:choose>
									<c:when test="${member.id eq homeOwner.id }">

										<a
											href="${pageContext.request.contextPath }/galleryUpdateForm.do?postId=${item.postId}&title=${item.title}&content=${item.content}&folderName=${item.folder}"
											style='cursor: pointer;' id="update_button">사진수정</a>&nbsp;&nbsp;&nbsp;
										<a href="javascript:deletePhoto(${item.postId })"
											style="cursor: pointer;" id="delete_button"
											data-value="${item.postId }">사진삭제</a>

									</c:when>

								</c:choose>



							</div>
						</form>

						<form id="formWrite">
							<div class="info">
								<label>작성자 : </label><span>${Homepee.homepeeId}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp; <label>작성일자 : </label><span>${item.createDate}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp; <label>📂</label><span>${item.folder}</span>
							</div>
						</form>


						<form id="formPhoto">
							<div class="photo">
								<img alt="img" src="${item.img }">
							</div>
						</form>

						<form id="formContent">
							<div class="content">
								<!--<label>내용 : </label>-->
								<span>${item.content}</span>
							</div>
						</form>

						<form id="formLikes">
									<div class="likes">
								<a
									href="javascript:pushLike('${item.postId }','${member.id }');"
									id="heart">
									<span> <c:if test = "${fn:contains(item.likeList, member.id)}">
									❤️ </c:if>
									<c:if test = "${!fn:contains(item.likeList, member.id)}">
									🤍 </c:if>
									</span></a> <span>${item.likes}개</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 	<span style="font-weight: bold">${item.likeList} </span>
									<label>님이 좋아합니다.</label>
							</div>
							 


						<%-- 	<div class="likes">
								<a
									href="javascript:pushLike('${item.postId }','${member.id }');"
									id="heart"> <c:choose>
										<c:when>
											<c:choose>
												<span> <c:when
														test="${fn:contains(item.likeList, member.id)}">
									❤️ </c:when>
											</c:choose>
										</c:when>
										<c:if test="${!fn:contains(item.likeList, member.id)}">
									🤍 </c:if>
										</span></a>
								</c:choose>
							</div> --%>


							<%-- 	<div>
								<c:if test = "${!item.likes == null }">
								<span style="font-weight: bold">${item.likeList} </span>
								<label>님이 좋아합니다.</label>
								</c:if>
								<c:if test="${item.likes != null }">
								<span style="font-weight: bold">${item.likeList} </span>
								<label>좋아요!</label>
								</c:if> 
							</div>	 --%>
						</form>

						<form id="formComment">
							<div class="comment">
								<label>댓글 </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><input
									type="text" placeholder="댓글입력" style="width: 550px;"></span>
								&nbsp;&nbsp;&nbsp; <span><input type="button"
									value="댓글등록"></span>
							</div>
						</form>

					</div>
					<br>
					<br>
					<br>

				</c:forEach>
			</div>

			<div class="center">
				<div class="pagination">
					<c:if test="${pageInfo.prev }">
						<a
							href="galleryList.do?pageNum=${pageInfo.startPage-1}&amount=${pageInfo.cri.amount}&homepee_id=${homepee.homepeeId}&fId=${pageParam.folder}"><</a>
					</c:if>
					<c:forEach var="num" begin="${pageInfo.startPage}"
						end="${pageInfo.endPage}">
						<a
							href="galleryList.do?pageNum=${num}&amount=${pageInfo.cri.amount}&homepee_id=${homepee.homepeeId}&fId=${pageParam.folder}">${num}</a>
					</c:forEach>
					<c:if test="${pageInfo.next }">
						<a
							href="galleryList.do?pageNum=${pageInfo.endPage+1}&amount=${pageInfo.cri.amount}&homepee_id=${homepee.homepeeId}&fId=${pageParam.folder}">></a>
					</c:if>
				</div>
			</div>


		</div>

	</div>
</div>
<!-- 가운데 화면 끝 -->