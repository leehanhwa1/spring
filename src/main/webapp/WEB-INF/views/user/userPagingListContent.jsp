<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
			<h1 class="page-header">전체 사용자 리스트Tiles</h1>
		    	<!-- userList 정보를 화면에 출력하는 로직 작성 -->
		    	<div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>No</th>
                  <th>사용자 아이디</th>
                  <th>사용자 이름</th>
                  <th>별명</th>
                  <th>등록일시</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="i" begin="0" end="${userList.size()-1 }">
              		<tr class="userTr" data-userid="${userList.get(i).getUserId()}">
              			<td>${i+1 }</td>
              			<td>${userList.get(i).getUserId()}</td>
              			<td>${userList.get(i).getUserNm()}</td>
              			<td>-</td>
              			<td>${userList.get(i).getReg_dt_fmt()}</td>
              		</tr>
              </c:forEach>
              </tbody>
            </table>
            
            <form action="${cp }/user/userForm" method="get">
            	<button type="submit" class="btn btn-default">사용자 등록</button>
            </form>
            
           
            <c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }"/>
            
            <nav style="text-align: center;">
			  <ul class="pagination">
			  	<c:choose>
			  		<c:when test="${page == 1 }">
			  			<li class="disabled">
					    	<a aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					    	</a>
				    	</li>
			  		</c:when>
			  		<c:otherwise>
			  			<li>
					    	<a href="${cp }/user/userPagingList" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					    	</a>
				    	</li>
			  		</c:otherwise>
			  	</c:choose>
			  	<c:forEach var="i" begin="1" end="${lastPage }">
			  		<c:set var="active" value=""/>
			  		<c:if test="${i == page }">
			  			<c:set var="active" value="active"/>
			  		</c:if>
			  		
			  		<li class="${active }">
			  			<a href="${cp }/user/userPagingList?page=${i }">${i }</a>
			  		</li>
			  	</c:forEach>
			  	<c:choose>
			  		<c:when test="${page == lastPage }">
			  			<li class="disabled">
					    	<a aria-label="Next">
					        	<span aria-hidden="true">&raquo;</span>
					    	</a>
				    	</li>
			  		</c:when>
			  		<c:otherwise>
			  			<li>
					    	<a href="${cp }/user/userPagingList?page=${lastPage }" aria-label="Next">
					        	<span aria-hidden="true">&raquo;</span>
					    	</a>
				    	</li>
			  		</c:otherwise>
			  	</c:choose>
			  </ul>
			</nav>
            
          </div>
<script>
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function(){
			
			//msg 속성이 존재하면 alert, 존재하지 않으면 넘어가기
			<c:if test="${msg != null}">
				alert("${msg}");
				<%session.removeAttribute("msg");%>
			</c:if>
			
			//사용자 tr태그 클릭시 이벤트 핸들러
			$(".userTr").on("click", function(){
				console.log("userTr click");
			  //클릭한 userTr태그의 userId 값을 출력
 			  //console.log($(this).children()[1].innerText);
				
			 	var userId = $(this).data("userid");
				
				// /user
				// 1. docuement
				//document.location = "/user?userId=" + userId;
				
				// 2. form
				$("#userId").val(userId);
 				//$("#frm").attr("action", "/userAllList"); // attr("수정할 옵션", "수정할 값")
				$("#frm").submit();
				
			});
		});
</script>

<form id="frm" action="${cp }/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>