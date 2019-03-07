<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testLayout.jsp</title>
</head>
<body>

	
	<tiles:insertAttribute name="header" /><br>
	=======================================<br>
	<tiles:insertAttribute name="left" /><br>
	=======================================<br>
	<tiles:insertAttribute name="content" /><br>
	=======================================<br>

</body>
</html>