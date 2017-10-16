<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.containsKey('locale') ? cookie['locale'].value : 'en-EN'}"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Page not found</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/WEB-INF/view/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <br>
        <h3><fmt:message key="error.404" bundle="${rb}"/></h3>
    </div>

    <div class="footer">
        <jsp:include page="/WEB-INF/view/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
