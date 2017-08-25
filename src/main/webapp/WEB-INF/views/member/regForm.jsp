<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>가입</title></head>
<body>
<form:form commandName="registRequest">
<form:errors element="div" />
<p>
	<label for="id">ID: 
	<form:input path="id" /> <form:errors path="id"/>
	</label>
</p>
<p>
	<label for="name">이름: 
	<form:input path="name" /> <form:errors path="name"/>
	</label>
</p>
<p>
	<label for="password">암호: 
	<form:password path="password" /> <form:errors path="password"/>
	</label>
</p>
<p>
	<label for="passwordConfirm">암호확인: 
	<form:password path="passwordConfirm" /> <form:errors path="passwordConfirm"/>
	</label>
</p>
<input type="submit" value="가입">
</form:form>
</body>
</html>