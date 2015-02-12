<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Анаграммы</title>
</head>
<body>
	<a href="<c:url value="/"/>" style="float: right;">На главную</a>
	<br />

	<form id="tark" action="/anagram/calc" method="get">
		<label for="in">Анаграмма</label><input id="in" name="in" style="min-width: 200px;" value="${param.in}"/>
		<label for="length">Длина</label><input id="length" name="length" value="${param.length}"/>
		<br/>
		<label for="isAccurate">Точное совпадение</label><input type="checkbox" id="isAccurate" name="isAccurate" <c:if test="${not empty param.isAccurate}">checked</c:if>/><br/>
		<input type="submit" value="Найти" />
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
