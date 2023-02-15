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
	href="${pageContext.request.contextPath}/css/galleryList.css?after" />
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
	font-size: 15px;
	line-height: 30px;
	padding-top: 30px;
}
#makeBtn {
	font-size: 12px;
	cursor: pointer;
	background-color: smokewhite;
	border-radius: 5px;
	border: 1px solid #ddd;
	color: gray;
}
#makeBtn:hover {
	color: rgb(34, 84, 52);
}
#editBtn {
	font-size: 12px;
	cursor: pointer;
	background-color: smokewhite;
	border-radius: 5px;
	border: 1px solid #ddd;
	color: gray;
}
#editBtn:hover {
	color: rgb(34, 84, 52);
}
#deleteBtn {
	font-size: 12px;
	cursor: pointer;
	background-color: smokewhite;
	border-radius: 5px;
	border: 1px solid #ddd;
	color: gray;
}
#deleteBtn:hover {
	color: rgb(34, 84, 52);
}
#upload {
	font-size: 15px;
	font-weight: bold;
	cursor: pointer;
	background-color: smokewhite;
	border-radius: 5px;
	border: 1px solid #ddd;
	color: gray;
}
#upload:hover {
	color: rgb(34, 84, 52);
}
#commentBtn {
	font-size: 12px;
	cursor: pointer;
	background-color: smokewhite;
	border-radius: 5px;
	border: 1px solid #ddd;
	color: gray;
}
#commentBtn:hover {
	color: rgb(34, 84, 52);
}
#commentDel {
	font-size: 12px;
	cursor: pointer;
	background-color: smokewhite;
	border-radius: 5px;
	border: 1px solid #ddd;
	color: gray;
}
#commentDel:hover {
	color: rgb(34, 84, 52);
}
#commSumm {
	cursor: pointer;
}
#commSumm:hover {
	color: darkblue;
}
.center {
	text-align: center;
}
.pagination {
	display: inline-block;
}
.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
	margin: 0 4px;
}
.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}
.pagination a:hover:not(.active) {background-color: #ddd;}
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
						<input id="makeBtn" type="button" value="폴더생성"
							onclick="makeGalleryFolder()"> <input id="editBtn"
							type="button" value="폴더수정" onclick="updateGalleryFolder()">
						<input id="deleteBtn" type="button" value="폴더삭제"
							onclick="deleteGalleryFolder()">
					</h3>

				</div>
			</c:when>

		</c:choose>


		<div class="galleryFolder">
			<c:forEach var="item" items="${GFlist }">
				<div class="row date">
					<!-- <div class="makeFolder"> -->
					<a id="makeF"
						href="galleryList.do?homepee_id=${Homepee.homepeeId}&fId=${item.folderName}&pageNum=1&amount=5"><span>📁
							${item.folderName }</span></a>

					<!-- </div> -->
				</div>

			</c:forEach>


		</div>


		<div class="profile" style="width: 200px">
			<br />
			<br />
			<br />
			<br />
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
							<input id="upload" type="button" value="사진올리기"
								onclick="addToGalleryList()" style="cursor: pointer;">
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
								<img alt="img" src="${item.img }" style="width: 500px;">
							</div>
						</form>

						<form id="formContent">
							<div class="content">
								<!--<label>내용 : </label>-->
								<span style="white-space: pre;">${item.content}</span>
							</div>
						</form>

						<form id="formLikes">
							<div class="likes">
								<a
									href="javascript:pushLike('${item.postId }','${member.id }');"
									id="heart"> <span> <c:if
											test="${fn:contains(item.likeList, member.id)}">
									❤️ </c:if> <c:if test="${!fn:contains(item.likeList, member.id)}">
									🤍 </c:if>
								</span></a> <span>${item.likes}개</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span style="font-weight: bold">${item.likeList} </span>
								<c:if test="${item.likeList ne null }">
									<label>님이 좋아합니다.</label>
								</c:if>
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

						<!-- 댓글입력창 -->
						<form name="addCommentBtnForm" id="formComment"
							action="galleryComment.do" method="post"
							style="text-align: center; margin: 80px 0px 20px 0px">


							<!-- <form name="formComment" id="formComment" action="galleryComment.do" method="post"> -->
							<div class="comment">
								<span> <input type="text" name="content"
									placeholder="댓글입력" style="width: 600px;"> <input
									type="hidden" name="postId" value=${item.postId} > <input
									type="hidden" name="writer" value=${member.id} >
									&nbsp;&nbsp;&nbsp; <input id="commentBtn" type="button"
									class="addCommentBtn" value="댓글등록">
								</span>

							</div>
						</form>

						<details>
							<summary id="commSumm"
								style="text-align: left; color: grey; font-size: 13px; padding-left: 50px">
								show comment </summary>
							<hr>
							<br>

							<!-- 댓글 보이는 창 -->
							<table>
								<tbody id="galleryCommentList${item.postId }">
									<c:forEach var="comment" items="${commentList }">
										<c:if test="${item.postId eq comment.postId }">
											<tr>
												<td
													style="padding-left: 20px; font-weight: bold; font-size: 12px">${comment.writer }</td>
												<td style="padding-left: 30px;">${comment.content }</td>
												<td style="padding-left: 30px; font-size: 10px">${comment.createDate }</td>

												<c:if
													test="${(member.id eq comment.writer) || (member.id eq homeOwner.id)}">
													<td style="padding-left: 20px;"><button
															id="commentDel" onclick="deleteComment(event)"
															style='cursor: pointer;' class="delete-button"
															data-value="${comment.commId }">삭제</button></td>

												</c:if>

											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</details>
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


<script>
	$('.page')
			.on(
					'click',
					'.addCommentBtn',
					function(event) {
						var addPost = $(event.target);
						var postId = addPost.closest('.comment').find(
								"input[name=postId]").val();
						var content = addPost.closest('.comment').find(
								"input[name=content]").val();
						if (content == "") {
							alert("내용이 없습니다.");
							return;
						}
						var writer = addPost.closest('.comment').find(
								"input[name=writer]").val();
						console.log(postId + " " + content + " " + writer);//
						addPost.closest('.comment').find("input[name=content]")
								.val("");
						$
								.ajax({
									type : "POST",
									url : "galleryComment.do",
									dataType : "json",
									data : {
										"writer" : writer,
										"content" : content,
										"postId" : postId
									},
									success : function(data) {
										console.log(data);
										var tbody = $('#galleryCommentList'
												+ data.postId);
										let comment = "<tr><td style='padding-left: 20px; font-weight: bold; font-size: 12px' >" + data.writer
												+ "</td>";
										comment += "<td style='padding-left: 30px;'>" + data.content
												+ "</td>";
										comment += "<td style='padding-left: 30px; font-size: 10px' >" + data.createDate
												+ "</td>";
											
										comment += "<td style='padding-left: 20px;'><button id='commentDel' onclick='deleteComment()' style='cursor: pointer;' class='delete-button' data-value='"+data.commId+"'>삭제</button></td></tr>";
										tbody.prepend(comment);
									},
									error : function(er) {
										console.log("실패원인 : " + er);
									}
								});
					});
	function deleteComment(e) {
		const delBtn = event.target;
		let delId = delBtn.getAttribute("data-value");
		console.log(delId + "=삭제할 사진첩 댓글");
		let isDel = confirm("삭제하시겠습니까?");
		if (isDel == true) {
			let delAjx = new XMLHttpRequest(); //send
			delAjx.open('post', 'galleryCommentDeleteAjax.do');
			delAjx.setRequestHeader('Content-type',
					'application/x-www-form-urlencoded');
			delAjx.send('delId=' + delId); // Cannot read properties of undefined (reading 'parentElement')
			delAjx.onload = function() {
				delBtn.parentElement.parentElement.remove();
				alert("삭제되었습니다");
			}
		}
	}
	function updateComment(e) {
	}
</script>



<!-- 가운데 화면 끝 -->