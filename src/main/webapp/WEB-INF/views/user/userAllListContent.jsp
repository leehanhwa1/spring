<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


				<h1 class="page-header">전체 사용자 리스트(tiles)</h1>
				<!-- userList 정보를 화면에 출력하는 로직 작성 -->

				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>사용자 아이디</th>
								<th>사용자 이름</th>
								<th>별명</th>
								<th>등록일시</th>
							</tr>
						</thead>
						<tbody>
						
						<c:forEach items="${userList }" var="user" >
							<tr class="userTr" data-userid="${user.userId }">
								<td></td>	<!-- 생략 -->
								<td>${user.getUserId() }</td> <!-- userId -->
								<%-- <td>${user.getUserId() }</td><!-- userId --> --%>
								
								<td>${user.userNm }</td><!-- userNm -->
								<td></td><!-- 생략 -->
								<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy/MM/dd"/> </td> <!-- reg_dt -->
							</tr>
						</c:forEach>
							
						</tbody>
					</table>
				</div>

	<script>
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function(){
			console.log("document ready");
			
			//사용자 tr 태그 클릭시 이벤트 핸들러
			$(".userTr").on("click", function(){
				console.log("userTr click");
				//클릭한 userTr태그의 userId 값을 출력
// 				var userId = $(this).children()[1].innerText;
// 				console.log("userId : " + userId);

				var userId = $(this).data("userid");
				
				// /user
				// 1. document
				//document.location = "/user?userId=" + userId;
				
				// 2. form
				$("#userId").val(userId);
				//$("#frm").attr("action", "/userAllList");
				$("#frm").submit();
			});
		});
	</script>
	
<%-- <%

	pageContext.getRequest().equals(request);
	pageContext.getSession().equals(session);
	
	request.getContextPath();
	((HttpServletRequest)pageContext.getRequest()).getContextPath();
	
	application.getContextPath();
	
%> --%>
	

<form id="frm" action="${cp }/user" method="get">
	<input type="hidden" id="userId" name="userId"/>
</form>