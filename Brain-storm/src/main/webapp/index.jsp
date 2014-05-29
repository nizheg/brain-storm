<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Помощник для МШ</title>
</head>
<body>
	<a href="<c:url value="/tark" />" >Таркусограммы</a><br>
	<a href="<c:url value="/gapoifika"/>">ГаПоИФиКа</a><br>
	<a href="<c:url value="/anagram"/>">Анаграммайзер</a><br>
	Расчлененки по&nbsp;<a href="<c:url value="/dismember/movie"/>">фильмам</a>, <a href="<c:url value="/dismember/word"/>">словам</a><br>
	<a href='<c:url value="/search"><c:param name="wordType" value="word"/></c:url>'>Поиск по маске</a><br>
	
	
</body>
</html>