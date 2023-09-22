<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Member List Page</h1>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>PW</td>
			<td>REGDATE</td>
			<td>LASTLOGIN</td>
		</tr>
		<c:forEach items="${list}" var="mvo">
			<tr>
				<td><a href="mem/detail?id=${mvo.id}">${mvo.id}</a></td>
				<td>${mvo.pw}</td>
				<td>${mvo.regdate}</td>
				<td>${mvo.last_login}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/index.jsp"><button>index</button></a>
</body>
</html>