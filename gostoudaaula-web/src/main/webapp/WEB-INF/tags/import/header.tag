<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="scripts" required="false"%>
<%@ attribute name="styles" required="false"%>
<c:url var="jsPath" value="/resources/js/" />
<c:url var="cssPath" value="/resources/css/" />
<c:url var="url" value="/" />
<!DOCTYPE html>
<html>
<head>
<title>Gostou da aula?</title>
<meta charset="UTF-8">

<c:forEach items="${scripts}" var="js">
	<script type="text/javascript" src="${jsPath}${fn:trim(js)}"></script>
</c:forEach>
<c:forEach items="${styles}" var="css">
	<link rel="stylesheet" type="text/css" href="${cssPath}${fn:trim(css)}" />
</c:forEach>

<script type="text/javascript" src="${jsPath}bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${cssPath }main.css" />
<link rel="stylesheet" type="text/css"
	href="${cssPath }bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${cssPath }bootstrap-theme.min.css" />
</head>
<body>
	<main class="container-fluid"> <header class="header"></header>