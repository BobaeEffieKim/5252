<!-- mvcProj > webapp > spa.jsp 파일 참고해서 만들기 -->

<!--  일촌평 테이블이 가져야하는 정보, id(글번호), hompeeID(홈피주인 아이디), oneline(일촌평), bffName(친구일촌명), bffId(일촌아이디) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원가입폼을 일촌평으로 바꾸기!!!!!!</h3>
	<form name="comments" action="bffComents.do" method="post">      bffComentsController 만들어야겠네
		<input type ="text" name="oneline"><input type="submit" value="저장">	
	</form>

	<hr>
	<table border="1">
		<thead>
			<tr>
			[                          ] 입력
				<th>일촌평</th><th>글쓴이가 가진 일촌명</th><th>글쓴이</th><th>삭제</th>
				<!--예시 : 친구야 오랜만이다!    (찐친이지) 신은경      [삭제]  -->           
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>

	<script>
	
	let i=0;	
	let xhtp=new XMLHttpRequest(); //비동기방식 처리
	xhtp.open('get','memberJson.do');             -> MemberJsonController 처럼 bffComentsController 만들어야함
	xhtp.send(); //send가 호출되어야 작업을 시작함
	
 	xhtp.onreadystatechange=callBackThree;
	
	//출력할 필드를 배열에 지정	
	let fields=['oneline'];                        -> 그러고보니, 일촌평 테이블이 없음! 만들어 달라고 하기
	
	function callBackThree() {
		
		if(this.readyState==4 && this.status==200) {
			let data = JSON.parse(this.responseText);
			console.log(data);
			
			let tbody=document.getElementById('list');
			
			for(let obj of data) {
				tr = makeTr(obj);
				tbody.append(tr);				
			}
	}
	} //end of callBackThree
	
	//*** 데이터 한건 tr 생성
	function makeTr(obj) {
		
		//tr td,td,td,td
		let tr = document.createElement('tr');
		
		for(let field of fields) {
			let td1=document.createElement('td');
			td1.innerText=obj[field];
			tr.append(td1);
		}
		
		//삭제버튼
		let td1=document.createElement('td');
		let btn=document.createElement('button');         -> 삭제버튼은 주인만 보이게 할 수 있는가? 그리고 버튼 말고 [ 삭제 ] 요렇게 넣으면 좋겠음
		btn.innerText='삭제';
		
		//클릭이벤트
		btn.addEventListener('click', deleteCallBack);//이벤트타입, 기능
		td1.append(btn);
		tr.append(td1);
		
		return tr;
	}
	
	function deleteCallBack(e) {
		console.log(this); //event의 call함수(이벤트를 받는 대상)
	
		let delId=this.parentElement.parentElement.firstElementChild.innerText;
		
		let delAjx=new XMLHttpRequest(); //send
		delAjx.open('post', 'removeMemberAjax.do');
		delAjx.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		delAjx.send('id='+delId);
		delAjx.onload=function() {
			let result = JSON.parse(delAjx.responseText)
			if (result.retCode =='Success')
			   	e.target.parentElement.parentElement.remove();				
		    else 
				alert('처리중 에러발생');
		}
		
		
		
		this.parentElement.parentElement.remove();
	}
	
	//form 전송이벤트가 실행이 되면 form 태그실행하지않고 ajax실행되도록
	document.forms.addFrm.onsubmit=function(e) {
		
		//기본기능 차단
		e.preventDefault();
		let url=document.forms.addFrm.getAttribute('action');
		let id= document.forms.addFrm.id.value;
		let name= document.forms.addFrm.name.value;
		let pass= document.forms.addFrm.passwd.value;
		let mail= document.forms.addFrm.mail.value;
		let param='id='+id+'&name='+name+'&passwd='+pass+'&mail='+mail;
		console.log(param);
	
		let addAjx=new XMLHttpRequest(); //send
		addAjx.open('post', url);
		addAjx.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		addAjx.send(param); //id=user1&passwd=1234&name=Hong&mail=email@com
		addAjx.onload=function() {
			console.log(addAjx.reponseText);
			let data=JSON.parse(addAjx.responseText); //json->object
			//tbody태그의 id(list) 
			document.getElementById('list').append(makeTr(data));
			
		}
		document.forms.addFrm.id.value="";
		document.forms.addFrm.name.value="";
		document.forms.addFrm.passwd.value="";
		document.forms.addFrm.mail.value="";
		document.forms.addFrm.id.focus();
	}
	
	</script>
</body>
</html>
</body>
</html> 