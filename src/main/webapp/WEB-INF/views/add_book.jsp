<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<spring:url value="/admin/newbook" var="formUrl"/>
<sf:form commandName="book" action="${formUrl}" method="POST">

<label>Author</label>
<sf:input path="bookAuthor"/>
<sf:errors path="bookAuthor"></sf:errors>
<br>
<label>Book name</label>
<sf:input path="bookName"/>
<sf:errors path="bookName"></sf:errors>
<br>
<sf:select path="genre">
<sf:option value="0" label="Select genre"/>
<sf:options items="${book.genre}"/>
</sf:select>
<%-- <sf:input path="genre"/> --%>
<sf:errors path="genre"></sf:errors>
<br>
<label>Number of books</label>
<sf:input path="availableBooks"/>
<sf:errors path="availableBooks"></sf:errors>

<input type="submit" value="submit"/> 
</sf:form>

</body>
</html>