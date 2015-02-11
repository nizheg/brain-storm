<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Брайль</title>
</head>
<body>
	<a href="<c:url value="/"/>" style="float: right;">На главную</a>
	<br />

	<form id="braille" action="/braille" method="get">
        <table>
            <tr>
                <td><input name="in1" type="checkbox" <c:if test="${not empty param.in1}">checked</c:if>/></td>
                <td><input name="in2" type="checkbox" <c:if test="${not empty param.in2}">checked</c:if>/></td>
            </tr>
            <tr>
                <td><input name="in3" type="checkbox" <c:if test="${not empty param.in3}">checked</c:if>/></td>
                <td><input name="in4" type="checkbox" <c:if test="${not empty param.in4}">checked</c:if>/></td>
            </tr>
            <tr>
                <td><input name="in5" type="checkbox" <c:if test="${not empty param.in5}">checked</c:if>/></td>
                <td><input name="in6" type="checkbox" <c:if test="${not empty param.in6}">checked</c:if>/>
                </td>
            </tr>
        </table>

		 <input type="submit" value="Найти" /><br>
	</form>
	<br>

    <span>Прямая:</span><br/>
    <c:forEach items="${results}" var="item">
    	<span></spa><c:out value="${item}"/></span>&nbsp;
    </c:forEach>

    <span>Инвертированная:</span><br/>
    <c:forEach items="${invertedResults}" var="item">
        <span></spa><c:out value="${item}"/></span>&nbsp;
    </c:forEach>

	<a href="<c:url value="/"/>" style="float: right;">На главную</a>

	<script type="text/javascript"
		src="<c:url value="/resources/jquery/jquery-1.11.0.min.js" />"></script>
</body>
</html>