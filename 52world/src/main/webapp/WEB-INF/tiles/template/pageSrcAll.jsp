<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>2022 MINI HOMEPAGE</title>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="52world/css/main.css" />
</head>
<body>
	<div class="bookcover">
		<div class="bookdot">
			<div class="page">
				<!-- 왼쪽 화면 시작 -->
				<div class="left-container">
					<!-- 방문자수 -->
					<div class="left-visits">
						<p>
							TODAY <span style="color: red">25</span> | TOTAL 191207
						</p>
					</div>

					<!-- 프로필 여기가 왼쪽에 흰색박스-->
					<div class="left-box">
						<img class="profile-img"
							src="/52world/52world/asset/default_profile.jpg"
							alt="profile image" /> <br />

						<div class="profile">
							<p>
								너밖에 없어서<br /> 널 사랑한게 아니라<br /> 널 사랑하다보니 너밖에 없더라.<br /> <br />
								귀여니 - 늑대의 유혹
							</p>
							<a href="#" onclick="editProfile()"><span
								style="color: #3e9fbe; font-size: 8px">▶ </span>EDIT</a>
							<hr />
							<div class="profile-name">요닝 (♀)</div>
						</div>
					</div>


				</div>
				<!-- 왼쪽 화면 끝 -->

				<!-- 가운데화면 시작 -->
				<div class="middle-container">
					<!-- 미니홈피 제목 -->
					<div class="middle-title">
						<div class="middle-desc">웃ㅈr, 네 ㅁiЧ홈㉬ㅣ 제목ぇㅓ럼.</div>
						<div class="middle-url">https://52world.com/${homeOwner.id }</div>
					</div>
					<!-- 메인페이지 가운데 흰색박스 -->
					<div class="middle-box">
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
							</div>
						</div>
					</div>
				</div>
				<!-- 가운데 화면 끝 -->

				<!-- 오른쪽 탭 화면 시작 -->
				<div class="right-container">
					<div class="right-title"></div>
					<div class="right-tab">
						<div class="tab">
							<a href="#">홈</a>
						</div>
						<div class="tab">
							<a href="#">다이어리</a>
						</div>
						<div class="tab">
							<a href="#">사진첩</a>
						</div>
						<div class="tab">
							<a href="#">방명록</a>
						</div>
						<div class="tab">
							<a href="#">마켓</a>
						</div>
					</div>
				</div>
				<!-- 오른쪽 탭화면 끝 -->
			</div>
		</div>
	</div>
	<script>
		function editProfile() {
			let src = document.querySelector(".profile-img").src;
			window.open("52world/editProfile.html?src=" + src, "프로필 수정",
					"width= 450px, height= 650px");
		}
	</script>

</body>
</html>