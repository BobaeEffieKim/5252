<%@page import="com.dev.vo.GuestBookVO"%>
<%@page import="java.util.List"%>
<%@page import="com.dev.dao.GuestBookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>guestBook</title>
<link href="${pageContext.request.contextPath}/css/guestBook.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
crossorigin="anonymous"></script>


</head>


<body>
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
	<!-- 방명록 화면 만들어야해요-->
	<div class="middle-box">
	
	
		<!-- 방명록 입력창 만드는중....ㅎㅎ  -->
	<c:if test="${!(member.id eq Homepee.homepeeId) }">
		<div id="container">
			<form action="guestBookInsert.do" method="post" name="insert" class="insert-box" id="ckBoxSecret">
				<div id="main-box">
					<div id="content1">
						<img alt="" src="${member.profile }">
					</div>
					<div id="content2">
						<textarea class="content" name="content" id="content" ></textarea>
					</div>
				</div>
				<div id="sub-box">
					<input class="sub-box-Button" type="submit" value="확인" onclick="return insertPop();"/> 
					
					<input type="checkbox" id="only" name="only" class="sub-box-Button" value='1' />
					<input type="hidden" id="only_hidden" name="only" value='0' />
					<label class="sub-box-Button" for="only" >비밀로하기</label>
				    </div>
				
			</form>
		</div>
	
		<br>
		<br>
		</c:if>
		
		
		<!-- 밑에 방명록 리스트가 나와야함  -->	
		<c:forEach var ="vo" items="${list }">
	<br><br>
	<div>
		<div class="container-list">
			<form action="" method="post" name="form">
			 <div id="nav">
            <ul>
            
              <li class="top-text" id="postId">NO.${vo.postId }</li>
              <li class="top-text" id="writer"><a href="javascript:guestminihomepee('${vo.writer}')">${vo.writer }</a></li>
              
              <li class="top-text" id="createDate">${vo.createDate }</li>
        	
        	<c:if test = "${vo.secret eq 1 }">🔒
        	</c:if>
            <c:choose>
           
              	<c:when test="${vo.writer eq member.id}">
            	<c:choose>
            	<c:when test="${vo.secret eq 0}">

              	<li><a style='cursor:pointer;' class="update-button" data-value="${vo.postId }" >수정</a></li>
              	<li><a href="javascript:deleteCall(${vo.postId },${cri.pageNum},${cri.amount })" style='cursor:pointer;' id="delete-button" data-value="${vo.postId }">삭제</a></li>
             	<li><a href="javascript:secretCall(${vo.postId })" style='cursor:pointer;' id="secret-button" data-value="${vo.postId }">비밀로하기</a></li>
            	</c:when>
            	<c:otherwise>
            	<li><a style='cursor:pointer;' class="update-button" data-value="${vo.postId }" >수정</a></li>
              	<li><a href="javascript:deleteCall(${vo.postId },${cri.pageNum},${cri.amount })" style='cursor:pointer;' id="delete-button" data-value="${vo.postId }">삭제</a></li>

             	<li><a href="javascript:publicCall(${vo.postId })" style='cursor:pointer;' id="secret-button" data-value="${vo.postId }">전체공개</a></li>
            	</c:otherwise>
            	</c:choose>
            	</c:when>
            	<c:when test="${member.id eq Homepee.homepeeId }">
            	<c:choose>
            	<c:when test="${vo.secret eq 0}">
              	<li><a href="javascript:deleteCall(${vo.postId },${cri.pageNum},${cri.amount })" style='cursor:pointer;' id="delete-button" data-value="${vo.postId }">삭제</a></li>
             	<li><a href="javascript:secretCall(${vo.postId })" style='cursor:pointer;' id="secret-button" data-value="${vo.postId }">비밀로하기</a></li>
            	</c:when>
            	<c:otherwise>
            	<li><a href="javascript:deleteCall(${vo.postId },${cri.pageNum},${cri.amount })" style='cursor:pointer;' id="delete-button" data-value="${vo.postId }">삭제</a></li>
             	<li><a href="javascript:publicCall(${vo.postId })" style='cursor:pointer;' id="secret-button" data-value="${vo.postId }">전체공개</a></li>
            	</c:otherwise>
            	</c:choose>
            	</c:when>
            	<c:otherwise></c:otherwise>
            	</c:choose>
            </ul>
          </div>
      
				<div id="main-box-list">
					<div id="content1-list">
						<img alt="" src="${vo.profile }">
					</div>
					<div id="content2-list">
					
					<!-- 비밀인경우와 아닌경우를 나눠야함 -->
					 <c:choose>

	              	<c:when test="${vo.secret==1}">
	              	<c:choose>
	              	<c:when test="${vo.writer eq member.id}"> 
	            	<textarea class="content-list" readonly>${vo.content }</textarea>
	            	</c:when>
	            	<c:when test="${member.id eq Homepee.homepeeId }">
	            	<textarea class="content-list" readonly>${vo.content }</textarea>
	            	</c:when>
	            	<c:otherwise>
	            	 <textarea class="content-list" readonly>🔒비밀이야(이글은 작성자와 홈주인만 볼 수 있습니다)</textarea>
	            	</c:otherwise>
	            	</c:choose>
	            	</c:when>
	            	<c:otherwise>
	          			 <textarea class="content-list" readonly>${vo.content }</textarea>
					 </c:otherwise> 

	
	            	</c:choose>
			
						
					</div>
				</div>
			</form>
		<div>
		
		<!-- 댓글 -->
		<c:choose>
		<c:when test="${vo.secret eq 1}">
		<c:choose>
		<c:when test="${(vo.writer eq member.id) || (member.id eq Homepee.homepeeId) }">
		<form name ="addComForm" method="post">
			<div class = "comment-box">
			<textarea class="comment-txt" style="height: 70px"></textarea>
			</div>
			
			<div class="comment-button">
			<input type="hidden" name="commId" value="${comment.commId }">
			<input type="hidden" name="postId" value="${vo.postId }">
			<input type="hidden" name="writer" value="${member.id }">
			<c:choose>
			<c:when test="${(vo.secret == 1)&&(vo.writer ne member.id)&&(member.id ne homeOwner.id) }">
				<input type=button class="addComm" value="댓글" disabled/>
			</c:when>
			<c:otherwise>
				<input type=button class="addComm" value="댓글"/>
			</c:otherwise>
			</c:choose>
			</div>
			</form>
		</c:when>
			
			<c:otherwise>
				<form name ="addComForm" method="post">
			<div class = "comment-box">
			<textarea class="comment-txt" style="height: 70px"></textarea>
			</div>
			
			<div class="comment-button">
			<input type="hidden" name="commId" value="${comment.commId }">
			<input type="hidden" name="postId" value="${vo.postId }">
			<input type="hidden" name="writer" value="${member.id }">
			<input type=button disabled='disabled' class="addComm" value="댓글"/>
			</div>
			</form>
			
			</c:otherwise>
			</c:choose>
			</c:when>
			
			<c:otherwise>
				<form name ="addComForm" method="post">
			<div class = "comment-box">
			<textarea class="comment-txt" style="height: 70px"></textarea>
			</div>	
			<div class="comment-button">
			<input type="hidden" name="commId" value="${comment.commId }">
			<input type="hidden" name="postId" value="${vo.postId }">
			<input type="hidden" name="writer" value="${member.id }">
			<input type=button class="addComm" value="댓글"/>
			</div>
			</form>
			</c:otherwise>
			</c:choose>
			</div> 
		</div>
		

		

		
			<!-- 댓글리스트 돌려야함 -->
		<c:choose>
		<c:when test="${vo.secret eq 0}">
		<details>
		<summary >
		<c:forEach var ="cnt" items="${commentCnt }">
		<c:if test="${vo.postId eq cnt.postId }"> comment(${cnt.commId })
		</c:if>
		</c:forEach>
		</summary>

		<div class = "comment-box-list">
		<c:forEach var="comment" items="${commentList }">
		
		
		<c:choose>
			<c:when test = "${vo.postId eq comment.postId}">
				<div class ='comment-list-box'>
				<form action='' method='post' name='form'>
					<div id='nav-comment'>
						<ul>
			          		<li class='top-text' id='commId'>co.${comment.commId }</li>
							<li class="top-text" id="writer"><a href="javascript:guestminihomepee('${comment.writer}')">${comment.writer }</a></li>
							<li class='top-text' id='createDate'>${comment.createDate }</li>
							<c:choose>
							<c:when test="${(comment.writer eq member.id) }">
							<li class="top-right">
								<a onclick="deleteCallBack()" style='cursor:pointer; float:right;' id="delete-comm-button" class='commBtn' data-value="${comment.commId }">삭제&nbsp&nbsp</a>
							</li>
							<li class="top-right">
								<a style='cursor:pointer; float:right;' class='update-commBtn' data-value="${comment.commId }">수정&nbsp&nbsp</a>
							</li>
				
							</c:when>
							<c:when test="${(Homepee.homepeeId eq member.id) }">
							<li class="top-right">
								<a onclick="deleteCallBack()" style='cursor:pointer; float:right;' id="delete-comm-button"class='commBtn' data-value="${comment.commId }">삭제&nbsp&nbsp</a>
							</li>
							
							</c:when>
							
							<c:otherwise>
							
							</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<div>
						<div class = 'comment-content'>
							<textarea class='comment-txt' readonly>${comment.content }</textarea>
						</div>
					</div>
				</form>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</div>

		</details>
		
		</c:when>
	
		
		<c:otherwise>
		<c:choose>
		
		<c:when test="${vo.writer eq member.id}">

		
		<details>
		<summary >
		<c:forEach var ="cnt" items="${commentCnt }">
		<c:if test="${vo.postId eq cnt.postId }"> comment(${cnt.commId })
		</c:if>
		</c:forEach>
		</summary>
		<div class = "comment-box-list">
		<c:forEach var="comment" items="${commentList }">
		
		
		<c:choose>
			<c:when test = "${vo.postId eq comment.postId}">
				<div class ='comment-list-box'>
				<form action='' method='post' name='form'>
					<div id='nav-comment'>
						<ul>
			          		<li class='top-text' id='commId'>co.${comment.commId }</li>
							<li class="top-text" id="writer"><a href="javascript:guestminihomepee('${comment.writer}')">${comment.writer }</a></li>
							<li class='top-text' id='createDate'>${comment.createDate }</li>
							<c:choose>
							<c:when test="${(comment.writer eq member.id) }">
							<li class="top-right">
								<a onclick="deleteCallBack()" style='cursor:pointer; float:right;' id="delete-comm-button" class='commBtn' data-value="${comment.commId }">삭제&nbsp&nbsp</a>
							</li>
							<li class="top-right">
								<a style='cursor:pointer; float:right;' class='update-commBtn' data-value="${comment.commId }">수정&nbsp&nbsp</a>
							</li>
				
							</c:when>
							<c:when test="${(Homepee.homepeeId eq member.id) }">
							<li class="top-right">
								<a onclick="deleteCallBack()" style='cursor:pointer; float:right;' id="delete-comm-button"class='commBtn' data-value="${comment.commId }">삭제&nbsp&nbsp</a>
							</li>
							
							</c:when>
							
							<c:otherwise>
							
							</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<div>
						<div class = 'comment-content'>
							<textarea class='comment-txt' readonly>${comment.content }</textarea>
						</div>
					</div>
				</form>
				</div>
				
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</div>
		</details>
		
		</c:when>
		
		<c:when test="${member.id eq Homepee.homepeeId}">
			
		<details>
		<summary >
		<c:forEach var ="cnt" items="${commentCnt }">
		<c:if test="${vo.postId eq cnt.postId }"> comment(${cnt.commId })
		</c:if>
		</c:forEach>
		</summary>
		<div class = "comment-box-list">
		<c:forEach var="comment" items="${commentList }">
		
		
		<c:choose>
			<c:when test = "${vo.postId eq comment.postId}">
				<div class ='comment-list-box'>
				<form action='' method='post' name='form'>
					<div id='nav-comment'>
						<ul>
			          		<li class='top-text' id='commId'>co.${comment.commId }</li>
							<li class="top-text" id="writer"><a href="javascript:guestminihomepee('${comment.writer}')">${comment.writer }</a></li>
							<li class='top-text' id='createDate'>${comment.createDate }</li>
							<c:choose>
							<c:when test="${(comment.writer eq member.id) }">
							<li class="top-right">
								<a onclick="deleteCallBack()" style='cursor:pointer; float:right;' id="delete-comm-button" class='commBtn' data-value="${comment.commId }">삭제&nbsp&nbsp</a>
							</li>
							<li class="top-right">
								<a style='cursor:pointer; float:right;' class='update-commBtn' data-value="${comment.commId }">수정&nbsp&nbsp</a>
							</li>
				
							</c:when>
							<c:when test="${(Homepee.homepeeId eq member.id) }">
							<li class="top-right">
								<a onclick="deleteCallBack()" style='cursor:pointer; float:right;' id="delete-comm-button"class='commBtn' data-value="${comment.commId }">삭제&nbsp&nbsp</a>
							</li>
							
							</c:when>
							
							<c:otherwise>
							
							</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<div>
						<div class = 'comment-content'>
							<textarea class='comment-txt' readonly>${comment.content }</textarea>
						</div>
					</div>
				</form>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			
			</c:choose>
		</c:forEach>
		
		</div>
		
		</details>
		</c:when>
	
		<c:otherwise>
		<details>
		<summary >
		<c:forEach var ="cnt" items="${commentCnt }">
		<c:if test="${vo.postId eq cnt.postId }"> comment(${cnt.commId })
		</c:if>
		</c:forEach>
		</summary>
		</details>
		</c:otherwise>
		
		</c:choose>
	
		</c:otherwise>
		</c:choose>

		</div>
	
		<br>
		<br>

		</c:forEach>
			
		
		
	

			


		<div class="center">
			<div class="pagination">
			
				<c:if test="${pageInfo.prev }">
					<a 	href="guestbook.do?pageNum=${pageInfo.startPage-1}&amount=${pageInfo.cri.amount}"><</a>
				</c:if>
				<c:forEach var="num" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
					<a href="guestbook.do?pageNum=${num}&amount=${pageInfo.cri.amount}" class="${pageInfo.cri.pageNum==num?'active':''}" }>${num}</a>
				</c:forEach>
				<c:if test="${pageInfo.next }">
					<a href="guestbook.do?pageNum=${pageInfo.endPage+1}&amount=${pageInfo.cri.amount}">></a>
				</c:if>
			</div>
		</div>
		 

	</div>
</div>





<!-- 가운데 화면 끝 -->
</body>


<script>
<!--방명록 없으면 팝업창 -->
<!-- 방명록 수정 -->
$(function () {
	$('body').on('click', '.update-button', function(event){
		var updatebtn = $(event.target);
		var postId = updatebtn.attr("data-value");
		updatebtn.parent().parent().find("#delete-button").text("");
		updatebtn.parent().parent().find("#secret-button").text("");
		
		var contentBox = updatebtn.parent().parent().parent().parent().find(".content-list");
		contentBox.removeAttr("readonly");
		var parent = updatebtn.parent();
		parent.empty();
		parent.append("<a style='cursor:pointer; float:right;' class='1234' data-value="+postId+">수정완료</a>");
	});
	
	$('body').on('click', '.1234', function(e){	
			var updateConfirm = $(e.target);
			var postId = updateConfirm.attr("data-value");
			console.log(postId);
			var cBox = updateConfirm.parent().parent().parent().parent().find(".content-list");
			var content = cBox.val();
			console.log(content);
			
			$.ajax({
				type:"POST",
				url:"guestbookUpdate.do",
				dataType:"text",
				data:{"postId" : postId,
					"content" : content},
				success:function(data){
					updateConfirm.parent().parent().find("#delete-button").text("삭제");
					updateConfirm.parent().parent().find("#secret-button").text("비밀로하기");
					var parent = updateConfirm.parent();
					parent.empty();
					parent.append("<a style='cursor:pointer; float:right;' class='update-button' data-value="+postId+">수정</a>");
					cBox.attr("readonly", true);
					alert("수정이 완료되었습니다")
					console.log("성공");
				},
				error:function(er){
					alert("수정이 실패되었습니다")
					console.log("실패원인 : "+er);
					
				}
			});
		});
	
});
	
	
	
	
	//방명록 댓글 입력창
	$('.page').on('click','.addComm', function(event){
 	var addPost = $(event.target);
 	var postId = addPost.parent().find("input[name=postId]").val();
 	var commId = addPost.parent().find("input[name=commId]").val();
	var writer = addPost.parent().find("input[name=writer]").val();
 	var content = addPost.parent().parent().find(".comment-txt").val();
 	console.log(commId);
	if(content ==""){
		alert("댓글 내용을 입력해주세요");
		return;
	}
 		
		console.log(postId+" "+content+" "+writer);
 		
	
			$.ajax({
			url : "guestBookComment.do",
			type : "post",
			dataType : "json", 
			data : {
				"commId" : commId,
				"content": content,
				"postId" : postId,
				"writer" : writer
			},
			success : function(data) {
				
				console.log("댓글입력성공");
				let comdiv = "<div class ='comment-list-box'><form action='' method='post' name='form'><div id='nav-comment'><ul>";
	            comdiv+="<li class='top-text' id='comId'>co."+data.commId+"</li>";
	            comdiv+="<li class='top-text' id='writer'><a href='javascript:guestminihomepee('${comment.writer}')'>"+data.writer+"</a></li>";
				comdiv+="<li class='top-text' id='createDate'>"+data.createDate+"</li>";
				comdiv+="<li class='top-right'><a onclick='deleteCallBack()' style='cursor:pointer; float:right;' id='delete-comm-button' class='commBtn' data-value="+data.commId+">삭제&nbsp&nbsp</a>";
				comdiv+="</li><li class='top-right'><a style='cursor:pointer; float:right;' class='update-commBtn' data-value="+data.commId+">수정&nbsp&nbsp</a></li>";
				comdiv+="</ul></div><div><div class ='comment-content'><textarea class='comment-txt' readonly>"+data.content+"</textarea></div></div></form></div>";
				addPost.parent().parent().find(".comment-txt").val("");
				addPost.parent().parent().parent().parent().parent().find(".comment-box-list").prepend(comdiv);
			}, error:function(er){
				console.log("실패원인 : "+er);
			}
					
			
			})
			
		});
	
	
	//방명록 댓글 삭제창
	function deleteCallBack(e) {
		const delBtn = event.target;
		let delId = delBtn.getAttribute("data-value");
		console.log(delId + "= 삭제할 방명록 댓글");
		let isDel = confirm('삭제하시면 복구할 수 없습니다.\n정말로 삭제하시겠습니까?');
		
		if(isDel == true){
			let delAjx = new XMLHttpRequest();
			delAjx.open('post', 'guestCommentDeleteAjax.do');
			delAjx.setRequestHeader('Content-type','application/x-www-form-urlencoded');
			delAjx.send('delId=' + delId); // Cannot read properties of undefined (reading 'parentElement')
			delAjx.onload = function() {
				delBtn.parentElement.parentElement.parentElement.parentElement.remove();
				alert("삭제되었습니다");
	}
}
}
	
	//방명록 댓글 수정
	$(function(){
		$('body').on('click','.update-commBtn', function(event){
			var updateCommBtn = $(event.target);
			var commId = updateCommBtn.attr("data-value");
			updateCommBtn.parent().parent().find('#delete-comm-button').text("");
			
			
			var commentBox = updateCommBtn.parent().parent().parent().parent().find(".comment-txt");
			commentBox.removeAttr("readonly");
			var parent = updateCommBtn.parent();
			parent.empty();                                                                               
			parent.append("<a style='cursor:pointer; float:right;' class='update-commBtn2' data-value="+commId+">수정완료&nbsp&nbsp</a>");
		});
		
		$('body').on('click','.update-commBtn2', function(e){
			var updateConfirm = $(e.target);
			var commId = updateConfirm.attr("data-value");
			console.log(commId);
			var cBox = updateConfirm.parent().parent().parent().parent().find(".comment-txt");
			var content = cBox.val();
			console.log(content);
			
			$.ajax({
				type:"post",
				url:"guestBookCommUpdate.do",
				dataType:"text",
				data:{
					"commId" : commId,
					"content" : content},
				success:function(data){
					updateConfirm.parent().parent().find("#delete-comm-button").text("삭제");
					var parent = updateConfirm.parent();
					parent.empty();
					parent.append("<a style='cursor:pointer; float:right;' class='update-commBtn' data-value="+commId+">수정&nbsp&nbsp</a>");
					cBox.attr("readonly", true);
					console.log("성공");
					alert("수정되었습니다");
				},
				error:function(er){
					console.log("실패원인 : "+er);
					alert("수정이 실패했습니다");
				}
			});
		});
	}); 
	
	
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
</html>