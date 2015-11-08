<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gostou da aula?</title>
<style>
form, label, button {
	display: block;
}

button {
	margin: 10px 0;
}
</style>
</head>
<body>
	<h1>Cadastre os alunos</h1>
	<c:url value='/cadastro' var="alunosUrl" />
	<form:form action="${alunosUrl}" method="POST" commandName="cadastro">
		<label>Nome</label>
		<input type="text" name="nome" />
		<label>Sobrenome</label>
		<input type="text" name="sobrenome" />
		<label>Prontuario</label>
		<input type="number" name="prontuario" />
		<label>Senha</label>
		<input type="password" name="senha" />
		<button type="submit">cadastrar</button>
	</form:form>
	<a href='<c:url value="/alunosjson" />'>lista de alunos em json</a>
</body>
</html>