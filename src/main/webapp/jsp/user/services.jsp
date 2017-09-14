<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Services page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="site-block">
    <div class="header">
        <jsp:include page="/jsp/general/header.jsp"/>
    </div>

    <div class="content-block">
        <div class="menu">
            <jsp:include page="/jsp/user/user_menu.jsp"/>
        </div>

        <div class="main">
            <h3>Services</h3>
            <br>
            <table border="1" cellspacing="0" cellpadding="2">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Edit</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="service" items="${services}">
                    <tr>
                        <td><c:out value="${service.name}"/></td>
                        <td><c:out value="${service.description}"/></td>
                        <td><c:out value="${service.price}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.services.contains(service)}">
                                    <c:out value="active"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="not active"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.services.contains(service)}">
                                    <a href="/controller?query=switch_off_service&service_id=${service.id}">
                                        switch off
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/controller?query=switch_on_service&service_id=${service.id}">
                                        switch on
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="footer">
        <jsp:include page="/jsp/general/footer.jsp"/>
    </div>
</div>
</body>
</html>
