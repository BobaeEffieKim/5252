<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>52WORLD</title>
<style>
.MAIN {
	width: 100vw;
	height: 100vh;
	border: none;
}
</style>
</head>
<body style="overflow: hidden">

	<audio id="audio" src="" loop="loop" hidden="true"></audio>
	<input type="hidden" name="hId"  />
	<iframe src="memberLoginForm.do" class="MAIN" ></iframe>


</body>
<script type="text/javascript" charset="UTF-8">
	window.addEventListener("message", receive, false);
	
	function receive(event){
		let hId = document.querySelector("input[name=hId]");
		let audio = document.querySelector("#audio");
		var data = JSON.parse(event.data);
		var audioSrc = decodeURIComponent(audio.src).substr(21);
		console.log("부모창: "+hId.value+" 프레임창: "+data.hId);
		console.log("부모창: "+audioSrc+" 프레임창: "+data.src);
		console.log((hId.value != data.hId));
		console.log((audioSrc != data.src));
		
		if(hId.value != data.hId){
			hId.value=data.hId;
			audio.src=data.src;
			var promise = audio.play();
			if (promise !== undefined) {
			  promise.then(_ => {
			    // Autoplay started!
			  }).catch(error => {
			    // Autoplay was prevented.
			    // Show a "Play" button so that user can start playback.
			  });
			}
		}else if(audioSrc != data.src){
			audio.src=data.src;
			var promise = audio.play();
			if (promise !== undefined) {
			  promise.then(_ => {
			    // Autoplay started!
			  }).catch(error => {
			    // Autoplay was prevented.
			    // Show a "Play" button so that user can start playback.
			  });
			}
		} 
	}
</script>
</html>