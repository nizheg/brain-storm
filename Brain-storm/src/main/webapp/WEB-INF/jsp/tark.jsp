<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Таркусограммы</title>
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
<a href="<c:url value="/"/>" style="float:right;">На главную</a>
<br/>
<c:if test="${empty firstElemComplement and empty secondElemComplement and (not empty firstSeq) and (not empty secondSeq) and empty anagrams}">
	<c:out value="${firstSeq}"></c:out>
	&nbsp;=&nbsp;
	<c:out value="${secondSeq}"></c:out>
</c:if>

	<form id="tark" action="/tark/diff" method="get">

		<div class="tarktext">
			<textarea id="firstSequence" name="firstSeq" tabindex="1"
				style="width: 100%;" rows="10"><c:out value="${firstSeq}"/></textarea>
			<br />
			<c:if test="${not empty firstElemComplement}">
				<c:url value="/tark/anagram/${firstElemComplement}"
					var="getAnagramsUrl">
					<c:param name="for" value="1" />
				</c:url>
				<a href="${getAnagramsUrl}"
					title="Получить анаграммы">${firstElemComplement}</a>
			</c:if>
		</div>
		<div class="tarktext">
			<textarea id="secondSequence" name="secondSeq" tabindex="2"
				style="width: 100%;" rows="10"><c:out value="${secondSeq}"/></textarea>
			<br />
			<c:if test="${not empty secondElemComplement}">
				<c:url value="/tark/anagram/${secondElemComplement}"
					var="getAnagramsUrl">
					<c:param name="for" value="2" />
				</c:url>
				<a href="${getAnagramsUrl}" title="Получить анаграммы">${secondElemComplement}</a>
			</c:if>
		</div>
		<div class="fullwidth">
			<input style="float: right;" type="submit" value="Посчитать" />
		</div>
	</form>

	<c:if test="${not empty anagrams}">
		<div class="fullwidth">
			<c:choose>
				<c:when test="${forWord == 2}">
					<c:forEach items="${anagrams}" var="item">
						<a href="#"
							onclick="$('#firstSequence').val(function(i, text) { return text + ' ${item}';});">${item}</a>&nbsp;
					</c:forEach>
				</c:when>
				<c:when test="${forWord == 1 }">
					<c:forEach items="${anagrams}" var="item">
						<a href="#"
							onclick="$('#secondSequence').val(function(i, text) { return text + ' ${item}';});">${item}</a>&nbsp;
					</c:forEach>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<a href="<c:url value="/"/>" style="float:right;">На главную</a>

	<script type="text/javascript"
		src="<c:url value="/resources/jquery/jquery-1.11.0.min.js" />"></script>
</body>
</html>
