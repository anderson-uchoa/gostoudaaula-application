<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
div {
	width: 100%;
	font-size: 15px;
	background: green;
	color: #fff;
	padding: 20px;
	text-align: center;
}

a {
	display: block;
}
}
</style>
</head>
<body>
	<div>${message}</div>
	${alunos}
	<a href='<c:url value="/" />'>voltar</a>
</body>
</html>