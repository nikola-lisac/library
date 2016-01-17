<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>dis be users page</h2>
	${user.username } ${user.password}
	<br /> User has rented books:
	<hr />
	<c:forEach items="${usersBooks}" var="book">
		<a href="<spring:url value="/returnbook?username=${user.username}&bookname=${book.bookName}"/>">${book.bookName }</a>
		${book.bookAuthor} <br />
	</c:forEach>
	<hr />
	<br> Click on the book to rent it:
	<table>
		<tr>
			<th>Book author</th>
			<th>Book name</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.bookAuthor}</td>
				<td><a
					href="<spring:url value="/rentbook?username=${user.username}&bookname=${book.bookName}"/>">${book.bookName}</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>