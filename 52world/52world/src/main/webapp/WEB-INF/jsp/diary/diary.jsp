<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<p style="height: 200px; padding-top: 50px">${homeOwner.introduce }</p>

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
			<a href="" onclick="logout()"> <span
				style="color: #3e9fbe; font-size: 8px">▶ </span>로그아웃
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
	<!-- 다이어리 화면 만들어야해요-->
	<div class="middle-box">
		<h1>다이어리 첫 화면입니다.</h1>
		<table border: none >
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.postId }</td>
						<td>${vo.title }</td>
						<td>${vo.createDate }</td>
						<td>${vo.views }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="${pageContext.request.contextPath }/writeDiaryForm.do"><input type="button" value="새 글 등록"></a>

		<div class="center">
			<div class="pagination">
				<c:if test="${pageInfo.prev }">
					<a
						href="diaryListPaging.do?pageNum=${pageInfo.startPage-1}&amount=${pageInfo.cri.amount}"><</a>
				</c:if>
				<c:forEach var="num" begin="${pageInfo.startPage}"
					end="${pageInfo.endPage}">
					<a href="diaryListPaging.do?pageNum=${num}&amount=${pageInfo.cri.amount}">${num}</a>
				</c:forEach>
				<c:if test="${pageInfo.next }">
					<a href="diaryListPaging.do?pageNum=${pageInfo.endPage+1}&amount=${pageInfo.cri.amount}">></a>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- 가운데 화면 끝 -->