<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="i" begin="0" end="${userList.size()-1 }">
	<tr class="userTr" data-userid="${userList.get(i).getUserId()}">
		<td>${i+1 }</td>
		<td>${userList.get(i).getUserId()}</td>
		<td>${userList.get(i).getUserNm()}</td>
		<td>-</td>
		<td>${userList.get(i).getReg_dt_fmt()}</td>
	</tr>
</c:forEach>
========================asdlfkjasldkfjalsdkjflasjdflasjdlfjasdlkfj=======================
<c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }"/>
 
<c:choose>
	<c:when test="${page == 1 }">
		<li class="disabled"><a aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="javascript:getUserPageListHtml(1);" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
		</a></li>
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="1" end="${lastPage }">
	<c:set var="active" value="" />
	<c:if test="${i == page }">
		<c:set var="active" value="active" />
	</c:if>

	<li class="${active }"><a href="javascript:getUserPageList(${i });">${i }</a></li>
</c:forEach>
<c:choose>
	<c:when test="${page == lastPage }">
		<li class="disabled"><a aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
		</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="javascript:getUserPageListHtml(${lastPage });"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</c:otherwise>
</c:choose>