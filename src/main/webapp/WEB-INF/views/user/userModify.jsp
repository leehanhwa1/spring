<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자 수정 페이지</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css"
	rel="stylesheet">
	
</head>

<body>
	<%@ include file="/WEB-INF/views/module/header.jsp"%>
	
	<div class="container-fluid">
      <div class="row">
         <div class="col-sm-3 col-md-2 sidebar">
            <%@ include file="/WEB-INF/views/module/left.jsp"%>
         </div>
         <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">사용자 정보 조회</h1>
            <form id="frm" action="${cp }/user/userModifyForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
            
               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="userId" name="userId"
                        placeholder="사용자 아이디" readonly />
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">사진</label>
                  <div class="col-sm-10">
                  	<img id="img" src="" />
                     <input type="file" class="form-control" id="profile" name="profile"
                        placeholder="사진" />
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="userNm" name="userNm"
                        placeholder="사용자 이름" />
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">별명</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="alias" name="alias"
                        placeholder="별명" />
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">주소1</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="addr1" name="addr1"
                        placeholder="주소" readonly />
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">주소2</label>
                  <div class="col-sm-10">
                     <input type="text" class="form-control" id="addr2" name="addr2"
                        placeholder="상세주소" />
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">우편번호</label>
                  <div class="col-sm-7">
                     <input type="text" class="form-control" id="zipcode"
                        name="zipcode" placeholder="우편번호" readonly />
                  </div>
                  <div class="col-sm-3">
                     <button id="zipcodeBtn" type="button" class="btn btn-default">검색</button>
                  </div>
               </div>

               <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">Password</label>
                  <div class="col-sm-10">
                     <input type="password" class="form-control" id="pass" name="pass"
                        placeholder="*******" />
                  </div>
               </div>

               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button id="regBtn" type="button" class="btn btn-default">사용자 수정</button>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>




<!-- Bootstrap core JavaScript
    ========================================== -->
   <!-- Placed at the end of the document so the pages load faster -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <!-- 다음 주소 api -->
   <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
   
   <script>
   	function initData() {
   		<%-- $("userId").val("<%=request.getParameter("userId") %>"); --%>
        $("#userId").val("${userVo.userId}");
        $("#userNm").val("${userVo.userNm}");
        $("#alias").val("${userVo.alias}");
        $("#addr1").val("${userVo.addr1}");
        $("#addr2").val("${userVo.addr2}");
        $("#zipcode").val("${userVo.zipcode}");
        $("#pass").val("${userVo.pass}");
        $("#img").attr("src", "${cp}/user/profileImg?userId=${userVo.userId}");
   	}
   	
	  	//우편번호 검색 버튼 클릭 이벤트
	    $("#zipcodeBtn").on("click", function() {
	       new daum.Postcode({
	          oncomplete : function(data) {
	             // 새 우편번호 : data.zonecode
	             $("#zipcode").val(data.zonecode);
	
	             // 기본주소(도로주소) : data.roadAddress
	             $("#addr1").val(data.roadAddress);
	
	             // 상세주소 input focus
	             $("#addr2").focus();
	          }
	       }).open();
	    });	
   	
   	$(document).ready(function() {
   		initData();
   		
   		$("#regBtn").on("click", function() {
            $("#frm").submit(); 
    		
   		});
   	});
   	
   	
   </script>

</body>
</html>