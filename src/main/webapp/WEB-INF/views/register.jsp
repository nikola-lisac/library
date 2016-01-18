<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/style.css"/>" type="text/css">
<title>Register</title>
</head>
<body>
	<spring:url value="/register" var="formUrl" />
	<sf:form commandName="user" action="${formUrl}" method="POST">
		<label>Username</label>
		<sf:input path="username" />
		<div class="errors">
			<sf:errors path="username"></sf:errors>
		</div>
		<br>
		<label>Password</label>
		<sf:password path="password" />
		<div class="errors">
			<sf:errors path="password"></sf:errors>
		</div>
		<br>
		<label>Confirm password</label>
		<sf:password path="confirmPassword" />
		<div class="errors">
			<sf:errors path="confirmPassword"></sf:errors>
		</div>
		<br>
		<label>First name</label>
		<sf:input path="firstName" />
		<div class="errors">
			<sf:errors path="firstName"></sf:errors>
		</div>
		<br>
		<label>Last name</label>
		<sf:input path="lastName" />
		<div class="errors">
			<sf:errors path="lastName"></sf:errors>
		</div>
		<br>
		<input type="submit" value="submit">
	</sf:form>
</body>
</html>