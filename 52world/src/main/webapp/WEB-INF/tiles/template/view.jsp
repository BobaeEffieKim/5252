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
		<img class="profile-img" src="${member.profile }" alt="profile image" />
		<br />

		<div class="profile">
			<p style="height: 200px; width:200px; padding-top: 50px">${member.introduce }</p>

			<!-- c:if문으로 홈피주인인지 체크하는 조건 필요 -->
			<a href="#" onclick="editProfile()"><span
				style="color: #3e9fbe; font-size: 8px">▶ </span>EDIT</a>
			<hr />
			<div class="profile-name">${member.name }</div>
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
					<div class="news-line">
						<div class="news-block-img">사진첩</div>
						<div class="news-name">그때 그시절 싸이월드</div>
					</div>
					<div class="news-line">
						<div class="news-block-txt">게시판</div>
						<div class="news-name">복원시키기 프로젝트</div>
					</div>
					<div class="news-line">
						<div class="news-block-txt">게시판</div>
						<div class="news-name">싸이월드 갬성</div>
					</div>
					<div class="news-line">
						<div class="news-block-img">사진첩</div>
						<div class="news-name">추억 상자 들여다보기 ♥</div>
					</div>
				</div>
				<div class="updated-news-right">
					<div class="category">
						<div class="category-line">
							<div class="diary">
								다이어리 <span
									style="color: rgb(33, 58, 197); font-family: 'NeoDunggeunmo';">0/25</span>
							</div>
							<div class="picture">
								사진첩 <span
									style="color: rgb(33, 58, 197); font-family: 'NeoDunggeunmo';">0/25</span>
							</div>
						</div>
						<hr class="dotted" />
						<div class="category-line">
							<div class="picture">
								방명록 <span
									style="color: rgb(33, 58, 197); font-family: 'NeoDunggeunmo';">0/25</span>
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
		</div>
		<!-- 여기까지 넣으면 됨 -->
	</div>
</div>
<!-- 가운데 화면 끝 -->