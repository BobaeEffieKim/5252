<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>52WORLD</title>

</head>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="css/main.css?after" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
   integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
   crossorigin="anonymous"></script>
</head>
<body>
	<div class="bookcover">
		<div class="bookdot">
			<div class="page">

				<!-- 왼쪽 + 가운데 화면 시작 -->
				<tiles:insertAttribute name="view"></tiles:insertAttribute>
				<!-- 화면 끝 -->

				<!-- 오른쪽 탭 화면 시작 -->
				<tiles:insertAttribute name="tab"></tiles:insertAttribute>
				<!-- 오른쪽 탭화면 끝 -->

				<input type="hidden" id="backColor"
					value="${Homepee.backgroundColor }" /> <input type="hidden"
					id="tabColor" value="${Homepee.tabColor }" />
			</div>
		</div>
	</div>
	<script>
	//배경음악
	window.parent.postMessage('{"hId": "${Homepee.homepeeId}", "src": "${Homepee.bgm}"}', "*");
	
		// 배경 탭 색변경
		let backColor = document.querySelector("#backColor").value;
		let tabColor = document.querySelector("#tabColor").value;
		const cover = document.querySelector(".bookcover");
		const tab = document.querySelectorAll(".tab");
		cover.style.backgroundColor = backColor;
		for(let i=0; i<tab.length; i++){
			let item = tab.item(i);
			item.style.backgroundColor = tabColor;
		}
	
		// 프로필수정 팝업창 열기
		function editProfile() {
			let src = document.querySelector(".profile-img").src;
			window.open(
					"${pageContext.request.contextPath }/editProfile.do?src="
							+ src, "프로필 수정", "width= 450px, height= 650px");
		}
		
		// 일촌신청 팝업창 열기
		function bffRequest() {
			window
					.open(
							"${pageContext.request.contextPath }/bffRequsetForm.do",
					
									"일촌신청", "width= 450px, height= 570px");
		}
		// SEK 친구찾기 팝업창 열기
		
		
		function searchMemberForm() {
			window.open(
					"${pageContext.request.contextPath }/searchMemberForm.do",
					"친구찾기", "width= 450px, height= 350px");
		}

		// 일촌수락 팝업창 열기
		function bffRequestList() {
			window.open(
					"${pageContext.request.contextPath }/bffRequestList.do",
					"일촌수락", "width= 450px, height= 570px");
			
		}
		// 로그아웃 확인창
		function logout() {
			let result = confirm('정말 로그아웃 하시겠습니까?');
			
			if (result == true) {
				window.location.href = 'memberLogout.do';
			}else{
				window.location.href = 'minihomepee.do';
			}
		}

		function addToGList() {
			location.href = "galleryMakeFolder.do";
		}

		function backToFolderList() {
			location.href = "galleryList.do";
		}

		function preview(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.querySelector("#preview-img").src = e.target.result;
					document.querySelector('#preview-img').width = 200;
					document.querySelector('#preview-img').height = 200;
				};
				reader.readAsDataURL(input.files[0]);
			} else {
				document.getElementById('preview').src = "";
			}
		}
	
		
	// sek 다이어리 삭제하기
		
		function deleteDiary(id) {
		
		let postId = id;
		console.log(postId);
		
		let c = confirm('삭제하시면 복구할 수 없습니다.\n정말로 삭제하시겠습니까?')
		if(c){
			alert("삭제가 완료되었습니다");
			location.href="diaryDelete.do?postId="+postId;
			
		} else {
			alert("삭제를 취소하였습니다")
		}
		
	}

		// sek 다이어리 등록하기
		
		function writeDiary() {
			
			if (write.title.value == "") {
				alert("제목을 입력하세요.");
				write.title.focus();
				return false;
			}

			if (write.content.value == "") {
				alert("내용을 입력하세요.");
				write.title.focus();
				return false;
			}
			
			
			alert("게시물 등록이 완료되었습니다");
			document.write.submit();
		}

		
	
		//방명록 비밀로 하기
		function secretCall(id){
			let postId = id;
			console.log(postId);
			
			let s = confirm('비밀로 하면 작성자와 홈주인 만 볼수 있습니다.\n비밀로 하시겠습니까?')
			if(s){
				alert('방명록이 비밀로 변경 되었습니다.');
				location.href="guestBookSecret.do?postId="+postId+"&secret=1";
			} else {
				alert("취소하였습니다")
			}
		}
		
		//방명록 삭제창
		function deleteCall(id,num,amo) {
			let postId = id;
			let pageNum = num;
			let amount = amo;
			console.log(pageNum,amount);
			console.log(postId);
			
			let c = confirm('삭제하시면 복구할 수 없습니다.\n정말로 삭제하시겠습니까?')
			if(c){
				alert("삭제가 완료되었습니다");
				location.href="gusetBookDelete.do?postId="+postId+"&pageNum="+pageNum+"&amount="+amount;
				
			} else {
				alert("삭제를 취소하였습니다")
			}
			
		}
		
		
		//방명록 전체 공개
		function publicCall(id){
		let postId = id;
		console.log(postId);
		
		let s = confirm('전체공개로 하면 모든 방문자가 볼 수 있습니다.\n전체공개로 하시겠습니까?')
		if(s){
			alert('방명록이 전체공개로 변경 되었습니다.');
			location.href="guestBookSecret.do?postId="+postId+"&secret=0";
		} else {
			alert("취소하였습니다")
		}
	}

		//방명록 내용 미입력시 팝업
		function insertPop(){
			let content = document.getElementById("content").value;
			if(!content){
				 alert('내용을 입력하세요');
				 return false;
			}
		}

		
		/* 사진관련 함수들 */
	function addToGalleryList() {
		location.href = "galleryAddForm.do";
	}
	function editPhoto() {
		location.href = "galleryUpdateForm.do?postId=${item.postId}&title=${item.title}&content=${item.content}&folderName=${item.folder}" ;
	}

 	function deletePhoto(id) {

		
	 		let postId = id;
			console.log(postId);
			
			let cf = confirm('이 사진을 정말로 삭제하시겠습니까?')
			if(cf){
				alert("삭제가 완료되었습니다");
				location.href="galleryDelete.do?postId="+postId;
				
			} else {
				alert("삭제를 취소하였습니다")
			}
		}
	
 	
 	/* 폴더관련 함수들 */
 	function makeGalleryFolder(){
		location.href = "galleryMakeFolderForm.do";
		
	}	
	
	function editToGList(){
		
		
		
		lacation.href = "${pageContext.request.contextPath }/galleryUpdateFolder.do?folderName=&secret=${item.secret}";
	}
	 
	function updateGalleryFolder(){
		location.href = "${pageContext.request.contextPath }/galleryUpdateFolderForm.do?postId=${item.postId}&title=${item.title}&content=${item.content}&folderName=${item.folder}";
	}
	
	function deleteGalleryFolder(){
		location.href = "galleryDeleteFolderForm.do";
	}
	
	/* 좋아요 */
	function pushLike(id, like){
		console.log(id);
		console.log(like);
		let postId = id;
		let likeMember = like;
		location.href = "galleryLike.do?postId="+postId+"&likeMember=likeMember&fId=${param.fId}&pageNum=${param.pageNum}&amount=${param.amount}";
		

	}	 	
	
	
	
			
		
	</script>

</body>
</html>