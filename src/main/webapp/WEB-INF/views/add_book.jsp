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
<spring:url value="/newbook" var="formUrl"/>
<sf:form commandName="book" action="${formUrl}" method="POST">

<label>Author</label>
<sf:input path="bookauthor"/>
<sf:errors></sf:errors>
<br>
<label>Book name</label>
<sf:input path="bookname"/>
<sf:errors></sf:errors>
<br>
<label>Genre</label>
<sf:input path="genre"/>
<sf:errors></sf:errors>
<br>
<input type="submit" value="submit"/> 
</sf:form>

</body>
</html>