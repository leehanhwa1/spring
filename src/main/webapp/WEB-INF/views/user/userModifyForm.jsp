<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<%@ include file="/WEB-INF/views/module/jsLib.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/views/module/header.jsp" %>
<div class="container-fluid">
    <div class="row">
		<%@ include file="/WEB-INF/views/module/left.jsp" %>
 	    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">사용자 수정</h1>
		    	
             <form id="frm" action="${cp }/user/userModifyForm" method="post" 
             		class="form-horizontal" role="form" enctype="multipart/form-data">
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사진</label>
						<div class="col-sm-10">
							<img src="">
							<input type="file" class="form-control" id="profile" name="profile">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userId" name="userId"
								readonly="readonly">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userNm" name="userNm"
								>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias"
								name="alias" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="zipcode"
								name="zipcode" placeholder="우편번호" readonly="readonly">
						</div>
						<div class="col-sm-3">
							<button id="zipcodeBtn" type="button" class="btn btn-default">주소 검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소1</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr1"
								name="addr1" readonly="readonly">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소2</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2"
								name="addr2" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass">
						</div>
					</div> 
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="updateBtn" type="button" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
				</form>
          
		</div>
	</div>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		
		$("img").attr("src","${cp }/user/profileImg?userId=${userVo.userId }");
		$("#userId").val("${userVo.userId}");
		$("#userNm").val("${userVo.userNm}");
		$("#alias").val("${userVo.alias}");
		$("#addr1").val("${userVo.addr1}");
		$("#addr2").val("${userVo.addr2}");
		$("#zipcode").val("${userVo.zipcode}");
// 		$("#pass").val("${userVo.pass}");
		
		//우변번호 검색 버튼 클릭 이벤트
		$("#zipcodeBtn").on("click", function(){
			new daum.Postcode({
		        oncomplete: function(data) {
		            
		            //새 우편번호 : data.zonecode
		            $("#zipcode").val(data.zonecode);
		            
		            //기본주소(도로주소) : data.roadAddress
		            $("#addr1").val(data.roadAddress);
		            
		            //상세주소 input foucs
		            $("#addr2").focus();
		            
		        }
		    }).open();
		});
		
		$("#updateBtn").on("click", function(){
			//사용자 아이디
			if($("#userId").val().trim()==""){
				alert("사용자 아이디를 입력해주세요");
				$("#userId").focus();
				return false;
			}
			//사용자 이름
			if($("#userNm").val().trim()==""){
				alert("사용자 이름을 입력해주세요");
				$("#userNm").focus();
				return false;
			}
			//별명
			if($("#alias").val().trim()==""){
				alert("사용자 별명을 입력해주세요");
				$("#alias").focus();
				return false;
			}
			//주소1 /우편번호
			if($("#addr1").val().trim()==""){
				alert("사용자 주소를 입력해주세요");
				$("#zipcodeBtn").trigger("click");
				return false;
			}
			//주소2
			if($("#addr2").val().trim()==""){
				alert("사용자 상세주소를 입력해주세요");
				$("#addr2").focus();
				return false;
			}
			
			//정상적으로 validation이 완료 --> form 전송
			$("#frm").submit();
			
		});
		
	});
</script>
</body>
</html>

<%-- localhost/user/userList.jsp --%>