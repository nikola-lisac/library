<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<p>This is login</p>

	<spring:url value="/login" var="formUrl" />
	<sf:form action="${formUrl}" method="POST">
		<label for="username">Username:</label>
		<input type="text" name="username" />
		<label for="password">Password</label>
		<input type="password" name="password" />
		<label><input type="checkbox">Remember me</label>
		<input name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Submit" />
	</sf:form>
</body>
</html>