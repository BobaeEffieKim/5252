<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<!--jQuery 불러오기-->

<style>
.list-row {
	font-size: 14px;
	padding: 0px;
	padding-left: 10px;
}

.list-icon {
	font-size: 18px;
}

.musicList  table {
	width: 100%;
	border-collapse: collapse;
}

.musicList  table tr:nth-child(even) {
	border-bottom: 0.8px solid lightgray;
}

.musicShop {
	
}
</style>
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
		<!-- 마켓 화면 만들어야해요-->
		<div class="middle-box">
			<div class="market" style="width: 780px; margin: 10px">
				<div class="music">
					<div class="top-msg">
						배경음악 구매
						<hr />
					</div>
					<div class="musicShop" style="margin: 0px 80px;">
						<form id="musicForm" method="post" style="display:inline-block;">
							<input name="search" type="text" id="search" style="width: 300px; margin-left:100px; display:inline-block;">
							<button type="button" id="searchbtn">검색</button>
						</form>
						<div style="text-align:right; float:right; ">
							보유오이 🥒x${member.dotori}
						</div>
						<div class="musicList"
							style="overflow: scroll; width: 600px; height: 200px;">
							<table id="musicTable" >
							</table>
						</div>
					</div>
					<div style="text-align:right;">
						<button id="buyMusic" onclick="buyMusic()">구매</button>
					</div>
				</div>
				<div class="miniroomShop">
					<div class="top-msg">
						미니룸 구매
						<hr />
					</div>
						<form action="buyMiniroom.do">
						<div class="miniroomList" style="overflow-x: scroll; white-space: nowrap;">
							<c:forEach var="rm" items="${remainMiniroom }">
								<div class="rm" style="display:inline-block; margin:0px 5px;">
									<label >
									<input type="radio" id="rmSelect" value="${rm.id*100+rm.price }" name="rmSelect"/>
									${rm.name }&nbsp🥒x${rm.price }<br />
									<img id="${rm.id }" src="${rm.src }" alt="miniroom" style="width:400px; height:200px" />
									</label>
								</div>
							</c:forEach>
						</div>
						<div style="text-align:right">
							<button id="buyMiniroom" type="submit">구매</button>
						</div>
					</form>
				</div>
				<div class="setting" style="clear:both">
					<div class="top-msg">
						미니홈피 설정
						<hr />
					</div>
					<div class="setMusic">
						<form action="setMusic.do" method="post">
						배경음악 설정&nbsp&nbsp<select name="src" style="width: 200px">
							<optgroup label="배경음악">
							<c:forEach var="mm" items="${myMusic }">
								<option value="${mm.src }">${mm.title } - ${mm.singer }</option>
							</c:forEach>
							</optgroup>
						</select> <input type="submit" value="설정">
						</form>
					</div>
					<div class="setMiniroom">
						<form action="setMiniroom.do" method="post">
						미니룸 설정&nbsp&nbsp&nbsp&nbsp&nbsp<select name="src" style="width: 200px">
							<optgroup label="미니룸">
							<c:forEach var="mm" items="${myMiniroom }">
								<option value="${mm.src }">${mm.name }</option>
							</c:forEach>
							</optgroup>
						</select> <input type="submit" value="설정">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	$(function () {
//		$(".input").on("keyup",function(key){
//			if(key.keyCode==13) {
//				            alert("엔터키 이벤트");         
//		     }     
//	});
		
		$.ajax({
			type:"POST",
			url:"musicSearch.do",
			dataType:"json",
			data:{"searchVal" :  ' '},
			success:function(req){
				for(let i =0; i<req.length; i++){
					let table = "<tr>";
					table+="<td rowspan='2'><div class='list-icon'><label for="+req[i].id+">🎵</label></div></td>";
					table+="<td><div class='list-row'><label for="+req[i].id+">&nbsp&nbsp&nbsp"+req[i].title+"</label></div></td>";
					table+="<td rowspan='2'><div class='list-icon'><label for="+req[i].id+">🥒x3</label></div></td>";
					table+="<td rowspan='2'><div class='list-icon'><input id="+req[i].id+" name='music' value="+req[i].id+" type='checkbox' onclick='checkMusic()'' /></div></td>";
					table+="</tr><tr>";
					table+="<td><div class='list-row'><label for="+req[i].singer+">&nbsp&nbsp&nbsp"+req[i].singer+"</label></div></td></tr>";
					$('#musicTable').append(table);			
				}
			},
			error:function(er){
				console.log("실패원인 : "+er);
			}
		});
		
	$('#searchbtn').click(function(){
		$("#musicTable *").remove(); 
		var searchVal = $('#search').val();
		
		$.ajax({
			type:"POST",
			url:"musicSearch.do",
			dataType:"json",
			data:{"searchVal" : searchVal},
			success:function(req){
				for(let i =0; i<req.length; i++){
					let table = "<tr>";
					table+="<td rowspan='2'><div class='list-icon'><label for="+req[i].id+">🎵</label></div></td>";
					table+="<td><div class='list-row'><label for="+req[i].id+">&nbsp&nbsp&nbsp"+req[i].title+"</label></div></td>";
					table+="<td rowspan='2'><div class='list-icon'><label for="+req[i].id+">🥒x3</label></div></td>";
					table+="<td rowspan='2'><div class='list-icon'><input id="+req[i].id+" name='music' value="+req[i].id+" type='checkbox' onclick='checkMusic()'' /></div></td>";
					table+="</tr><tr>";
					table+="<td><div class='list-row'><label for="+req[i].singer+">&nbsp&nbsp&nbsp"+req[i].singer+"</label></div></td></tr>";
					$('#musicTable').append(table);			
				}
			},
			error:function(er){
				console.log("실패원인 : "+er);
			}
		});
	});
});

function checkMusic(){
const id = 'input[name="music"]:checked';
const selected = document.querySelectorAll(id);
let result = '';
selected.forEach((el)=>{
	result += el.value+'_';
});

document.querySelector("#buyMusic").value=result;
}
function buyMusic(){
let id = document.querySelector("#buyMusic").value;
if(id==""){
	alert("선택한 상품이 없습니다");
	return;
}
window.open("buyMusic.do?id="
+ id, "배경음악 구매", "width= 450px, height= 650px");
}
$(".miniroomList").children().first().children().first().children().first().attr('checked', true);



</script>


</body>
</html>