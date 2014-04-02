<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ГаПоИФиКа</title>
<style type="text/css">
div.tarktext {
	width: 48%;
	float: left;
	position: relative;
	padding: 1px 1%;
}

div.fullwidth {
	width: 98%;
	float: left;
	position: relative;
	padding: 1px 1%;
}
</style>
</head>
<body>
	<a href="<c:url value="/"/>" style="float: right;">На главную</a>
	<br />

	<form id="tark" action="/gapoifika/calc" method="get">
		<input name="in" style="min-width: 200px;" value="${param.in}">
		 <input type="submit" value="Найти" /><br>
		 <label><input type="checkbox" name="isAccurate" <c:if test="${not empty param.isAccurate}">checked</c:if>/>Искать только точные совпадения</label>
		 <br>
		 <%@include file="wordTypeFilter.jsp" %>
	</form>
	<br>
	<c:forEach items="${results}" var="item">
		<span><c:out value="${item}" /></span>
		<br>
	</c:forEach>

	<a href="<c:url value="/"/>" style="float: right;">На главную</a>

	<script type="text/javascript"
		src="<c:url value="/resources/jquery/jquery-1.11.0.min.js" />"></script>
</body>
</html>