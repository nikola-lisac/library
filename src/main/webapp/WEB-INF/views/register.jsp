<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<spring:url value="/newuser" var="formUrl" />
	<sf:form commandName="user" action="${formUrl}" method="POST">
		<sf:input path="username" />
		<sf:errors></sf:errors>
		<sf:password path="password" />
		<sf:errors></sf:errors>
		<sf:password path="confirmpass" />
		<sf:input path="firstName" />
		<sf:errors></sf:errors>
		<sf:input path="lastName" />
		<sf:errors></sf:errors>
		<input type="submit" value="submit">
	</sf:form>
</body>
</html>