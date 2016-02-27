<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/html"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="mask" required="false"%>
<%@ attribute name="type" required="false"%>
<%@ attribute name="cssInput" required="false"%>
<%@ attribute name="cssLabel" required="false"%>

<form:label path="${name}" class="${cssLabel }">${label}</form:label>
<form:input path="${name}" data-mask="${mask}" type="${type }"
	class="${cssInput }" />
