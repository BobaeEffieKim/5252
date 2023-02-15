<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<style>
@font-face {
  font-family: "KyoboHand";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
#editProfile{
	width: 86%;
	height:60%;
	margin : 0 auto;
	text-align:center;
}
#introduce{
	resize:none;
}

h3{
	text-align: left; 
	font-family: "KyoboHand";
}
.btn{
	width:90%;
	text-align: right;
}
</style>
</head>
<body>
	<div id="editProfile">
		<form name="profileForm" action="profile.do" method="post"
			enctype="multipart/form-data">
			<h3>사진 변경</h3>
			<img id="profile-img" /> 
			<div class="btn">
			<input type="file" name="img" id="img" accept="image/*" onchange="preview(this)" />
			</div>
			<br />
			<h3>자기 소개</h3>
			<textarea name="introduce" id="introduce" cols="40" rows="10"></textarea>
			<br /> 
			<div class="btn">
			<input type="submit" value="저장" onclick="profileFormSubmit()" />
			<button onclick="cancle()">취소</button>
			</div>
		</form>
	</div>
	<script>
		let search = window.location.search
		let params = new URLSearchParams(search);
		let getSrc = params.get('src');
		console.log(getSrc);
		let index = getSrc.indexOf("/52");
		getSrc = getSrc.substring(index);

		document.querySelector("#profile-img").src = getSrc;
		document.querySelector('#profile-img').width = 250;
		document.querySelector('#profile-img').height = 250;
		function preview(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.querySelector("#profile-img").src = e.target.result;
					document.querySelector('#profile-img').width = 250;
					document.querySelector('#profile-img').height = 250;
				};
				reader.readAsDataURL(input.files[0]);
			} else {
				document.getElementById('preview').src = "";
			}

		}
		function cancle() {
			window.close();
		}

		function profileFormSubmit() {
			
			
			window.opener.name = "parentPage"; // 부모창의 이름 설정
			document.profileForm.target = "parentPage"; // 타켓을 부모창으로 설정
			document.profileForm.submit();
			window.open('','_self').close();
		}
	</script>
</body>
</html>