<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<style>
#container {
   padding: 0;
   width: 720px;
   box-sizing: border-box;
   margin: 10px auto;
  
   /* background-color: rgb(245, 245, 245); */
   
}
.galleryFolder {
	height: 300px;
	font-size: 18px;
	line-height : 30px;
}
#makeBtn{
	font-size : 12px;
	cursor: pointer; 
	background-color: smokewhite; 
	border-radius: 5px; 
	border:1px solid #ddd;
	color : gray;
}
#makeBtn:hover{
	color:  rgb(34, 84, 52);
}
#editBtn{
	font-size : 12px;
	cursor: pointer; 
	background-color: smokewhite; 
	border-radius: 5px; 
	border:1px solid #ddd;
	color : gray;
}
#editBtn:hover{
	color:  rgb(34, 84, 52);
}
#deleteBtn{
	font-size : 12px;
	cursor: pointer; 
	background-color: smokewhite; 
	border-radius: 5px; 
	border:1px solid #ddd;
	color : gray;
}
#deleteBtn:hover{
	color:  rgb(34, 84, 52);
}

h1{
	PADDING-top: 10px;
	text-align: center;
}
#makeAlbum{
	box-sizing: border-box;
	border: 1px solid #3e9fbe;
	height: 500px;
	margin-top: 30px;
	margin-left: 30px;
	margin-right: 20px;
	padding-top: 50px;
	padding-left: 30px;
	text-align: center;
}

#update{
	font-size : 15px;
	font-weight : bold;
	cursor: pointer; 
	background-color: smokewhite; 
	border-radius: 5px; 
	border:1px solid #ddd;
	color : gray;
}
#update:hover{
	color:  rgb(34, 84, 52);
}
#backBtn{
	font-size : 15px;
	font-weight : bold;
	cursor: pointer; 
	background-color: smokewhite; 
	border-radius: 5px; 
	border:1px solid #ddd;
	color : gray;
}
#backBtn:hover{
	color:  rgb(34, 84, 52);
}
</style>

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


 	<!-- kbb) 폴더리스트 넣기 -->

 	<div class="folderBtn" style="width:300px;">
 		<h3>
 			<input id="makeBtn" type="button" value="폴더생성" onclick="makeGalleryFolder()"> 
							<input id="editBtn" type="button"
							value="폴더수정" onclick="updateGalleryFolder()"> 
							<input id="deleteBtn" type="button"
							value="폴더삭제" onclick="deleteGalleryFolder()">
 		</h3>

 	</div>

 	<div class="galleryFolder">
		<c:forEach var="item" items="${GFlist }">
		<div class="row date">
			<div class="makeFolder">
				<a id="makeF" href="galleryList.do?id=${ Homepee.homepeeId}&fId=${item.folderName}"><span>📁 ${item.folderName }</span></a>
				
			</div>
		</div>	
		
		</c:forEach><br><br>

 	</div>  
 	
 	<%-- 
 	<div class="galleryFolder">
		<c:forEach var="item" items="${GalleryFolder }">
		<div class="row date">
			<div class="makeFolder">
				<span>${GalleryFolder.folderName }</span>
			</div>
		</div>	
		</c:forEach>
 	</div>   --%>
 

		<div class="profile">
				<br /><br /><br /><br />
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


	<c:set var="user" value="${member.id}" />
	<c:if test="${empty Homepee.homepeeId}">
		<c:redirect url="${pageContext.request.contextPath }/galleryAddForm.do"></c:redirect>
	</c:if> 

		<h1>폴더생성 페이지입니다.</h1>
			<form id="makeAlbum" action="${pageContext.request.contextPath }/galleryMakeFolder.do" method="post" >

				<div id="container">
					<div class = "title">
						<h3>
							<label for="folderName">폴더 이름</label>
							<input type="text" name="fname" id="Fname" placeholder="폴더이름">
						</h3>
					</div>
					
					<div class = "secretChoose" >
						<h3>
						<label for="secretOption">공개여부</label>
						<select name="secretOption">
							<option  value="0" selected>전체공개</option>
							<option  value="1" >일촌공개</option>
							<option  value="2" >비공개</option>
						</select>
						</h3>
					</div>



				<div class="btn">
					<input id="update" type="button" value="폴더생성" onclick="makeCheck()">
					<input id="backBtn" type="button" value="뒤로가기" onclick="back()">
				</div>
			</div>	
		</form>


	<!-- 	<script type="text/javascript">
		function addToGalleryList(){
			location.href = "WEB-INF/gallery/galleryList.jsp";
		}
	</script>  -->

	<script type="text/javascript">
	function back(){
		location.href = "galleryList.do?id=${homeOwner.id }";
	}
	
	function makeCheck(){
		if(makeAlbum.fname.value==""){
			alert("폴더명을 입력해주세요.");
			makeAlbum.fname.focus();
   			return false;
		}else{
   			
   			
			let cf = confirm('폴더를 생성하시겠습니까?')
			if(cf){
				alert("폴더가 생성되었습니다.");
				makeAlbum.submit();
				
			} else {
				alert("폴더 생성을 취소하였습니다")
			}
   			
   		}
	}
	</script>	

	</div>		
</div>
<!-- 가운데 화면 끝 --> 