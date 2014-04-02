<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:forEach items="${wordTypes}" var="item">
		<label><input type="checkbox" name="wordType" value="${item.id}" <c:if test="${item.checked}">checked</c:if>>${item.name}</label><br/>
	</c:forEach>		
