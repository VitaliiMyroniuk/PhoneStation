<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setLocale value="${sessionScope.locale}" scope="session"/>--%>
<fmt:setLocale value="uk_UA" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<fmt:message key="footer.copyright" bundle="${rb}"/>