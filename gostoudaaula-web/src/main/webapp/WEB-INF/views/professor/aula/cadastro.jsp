<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@taglib prefix="import" tagdir="/WEB-INF/tags/import"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<import:header scripts="jquery.js" />

<c:url value='cadastro' var="actionUrl" />

<fieldset class="form-group">
	<legend>Cadastro de aula</legend>
	<form:form action="${actionUrl }" method="POST" commandName="aula"
		role="form">
		<label for="ano">ano letivo</label>
		<form:select path="periodoLetivo.ano" items="${anos}" id="ano"
			cssClass="form-control"></form:select>
		<label for="turmas">turma</label>
		<form:select path="periodoLetivo.turma.descricao" items="${turmas}" id="turmas"
			cssClass="form-control"></form:select>
		<label for="semestres">semestre</label>
		<form:select path="periodoLetivo.semestre" items="${semestres}"
			id="semestre" cssClass="form-control"></form:select>
		<html:input label="data da aula" name="data" type="date"
			cssInput="form-control" />
		<html:input label="matéria" name="periodoLetivo.disciplina.descricao"
			cssInput="form-control"></html:input>
		</br>
		<button class="btn">cadastrar</button>
	</form:form>

</fieldset>

<import:footer />