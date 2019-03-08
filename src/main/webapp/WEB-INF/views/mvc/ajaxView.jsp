<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {

		$("#jsonReqBtn").on("click", function() {
			//responseBody();
			requestBody();
		});
	});

	
	function requestBody() {
		
		var data = {userId : "brown", userNm: "브라운"};

		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
			//data : "userId=brown&userNm=브라운",
			//data : $("#frm").serialize(),
			data : JSON.stringify(data),
			dataType : "json", // server에게 희망하는 return타입을 명시(참고값)
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#jsonRecvTbody").html("<tr><td>" + data.userId + "</tr></td>");
				console.log("data : " + data);
				// data.rangerList => array

				/* var html = "";
				for (var i = 0; i < data.length; i++) {
					var ranger = data[i];
					html += "<tr><td>" + ranger + "</tr></td>";
				}
				$("#jsonRecvTbody").html("<tr><td>" + data.userId + "</tr></td>"); */
			}
		});

	}
	
	
	function responseBody() {

		$.ajax({
			url : "${cp}/ajax/responseBody",
			method : "post",
			dataType : "json", // server에게 희망하는 return타입을 명시(참고값)
			success : function(data) {
				console.log("data : " + data);
				// data.rangerList => array

				var html = "";
				for (var i = 0; i < data.length; i++) {
					var ranger = data[i];
					html += "<tr><td>" + ranger + "</tr></td>";
				}
				$("#jsonRecvTbody").html(html);
			}
		});

	}
</script>
</head>
<body>

	<form id="frm">
		<input type="text" name="userId" value="brown" />
		<input type="text" name="userNm" value="브라운" />
	</form>

	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	
	<div>
		<button id="jsonReqBtn">jsonData요청</button>
		<div id="jsonRecv">
			<table>
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				
				<tbody id="jsonRecvTbody">
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>